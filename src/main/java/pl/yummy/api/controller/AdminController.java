package pl.yummy.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.yummy.api.dto.CustomerDTO;
import pl.yummy.api.dto.OrdersDTO;
import pl.yummy.api.dto.RestaurantDTO;
import pl.yummy.api.dto.RevenueReportViewDTO;
import pl.yummy.api.dto.DeliveryStatusOverviewViewDTO;
import pl.yummy.api.dto.mapper.CustomerMapper;
import pl.yummy.api.dto.mapper.OrdersMapper;
import pl.yummy.api.dto.mapper.RestaurantMapper;
import pl.yummy.api.dto.mapper.RevenueReportViewMapper;
import pl.yummy.api.dto.mapper.DeliveryStatusOverviewViewMapper;
import pl.yummy.business.CustomerService;
import pl.yummy.business.OrderService;
import pl.yummy.business.RestaurantService;
import pl.yummy.business.DeliveryService;
import pl.yummy.domain.Customer;
import pl.yummy.domain.Orders;
import pl.yummy.domain.Restaurant;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class AdminController {

    // Definicje endpointów jako statyczne stałe.
    private static final String ADMIN_CUSTOMERS = "/admin/customers";
    private static final String ADMIN_RESTAURANTS = "/admin/restaurants";
    private static final String ADMIN_ORDERS = "/admin/orders";
    private static final String ADMIN_REVENUE_REPORT = "/admin/revenue-report";
    private static final String ADMIN_DELIVERY_STATUS = "/admin/delivery-status";

    private final CustomerService customerService;
    private final RestaurantService restaurantService;
    private final OrderService orderService;
    private final DeliveryService deliveryService;

    private final CustomerMapper customerMapper;
    private final RestaurantMapper restaurantMapper;
    private final OrdersMapper ordersMapper;
    private final RevenueReportViewMapper revenueReportMapper;
    private final DeliveryStatusOverviewViewMapper deliveryStatusOverviewMapper;

    /**
     * GET – Wyświetla listę wszystkich klientów.
     */
    @GetMapping(ADMIN_CUSTOMERS)
    public ModelAndView listCustomers() {
        // Zakładamy, że CustomerService posiada metodę getAllCustomers()
        List<Customer> customers = customerService.getAllCustomers();
        List<CustomerDTO> customerDTOs = customers.stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
        Map<String, Object> model = Map.of("customers", customerDTOs);
        return new ModelAndView("admin_customers", model);
    }

    /**
     * GET – Wyświetla listę wszystkich restauracji.
     */
    @GetMapping(ADMIN_RESTAURANTS)
    public ModelAndView listRestaurants() {
        // Zakładamy, że RestaurantService posiada metodę getAllRestaurants()
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        List<RestaurantDTO> restaurantDTOs = restaurants.stream()
                .map(restaurantMapper::toDTO)
                .collect(Collectors.toList());
        Map<String, Object> model = Map.of("restaurants", restaurantDTOs);
        return new ModelAndView("admin_restaurants", model);
    }

    /**
     * GET – Wyświetla listę wszystkich zamówień.
     */
    @GetMapping(ADMIN_ORDERS)
    public ModelAndView listOrders() {
        // Zakładamy, że OrderService posiada metodę getAllOrders()
        List<Orders> orders = orderService.getAllOrders();
        List<OrdersDTO> ordersDTOs = orders.stream()
                .map(ordersMapper::toDTO)
                .collect(Collectors.toList());
        Map<String, Object> model = Map.of("orders", ordersDTOs);
        return new ModelAndView("admin_orders", model);
    }

    /**
     * GET – Generuje raport przychodowy w zadanym przedziale czasowym.
     *
     * @param startDateStr początkowa data raportu w formacie "yyyy-MM-dd HH:mm:ss"
     * @param endDateStr   końcowa data raportu w formacie "yyyy-MM-dd HH:mm:ss"
     */
    @GetMapping(ADMIN_REVENUE_REPORT)
    public ModelAndView revenueReport(@RequestParam("startDate") String startDateStr,
                                      @RequestParam("endDate") String endDateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        OffsetDateTime startDate = OffsetDateTime.parse(startDateStr, formatter);
        OffsetDateTime endDate = OffsetDateTime.parse(endDateStr, formatter);
        // Zakładamy, że OrderService posiada metodę getRevenueReport(OffsetDateTime, OffsetDateTime)
        var revenueReport = orderService.getRevenueReport(startDate, endDate);
        RevenueReportViewDTO reportDTO = revenueReportMapper.toDTO(revenueReport);
        Map<String, Object> model = Map.of("revenueReport", reportDTO);
        return new ModelAndView("admin_revenue_report", model);
    }

    /**
     * GET – Wyświetla przegląd statusu dostaw dla danego obszaru.
     *
     * @param availableDeliveryAreaId identyfikator obszaru dostawy
     */
    @GetMapping(ADMIN_DELIVERY_STATUS)
    public ModelAndView deliveryStatusOverview(@RequestParam("availableDeliveryAreaId") Long availableDeliveryAreaId) {
        // Zakładamy, że DeliveryService posiada metodę getDeliveryStatusOverview(Long)
        var overview = deliveryService.getDeliveryStatusOverview(availableDeliveryAreaId);
        DeliveryStatusOverviewViewDTO overviewDTO = deliveryStatusOverviewMapper.toDTO(overview);
        Map<String, Object> model = Map.of("deliveryStatusOverview", overviewDTO);
        return new ModelAndView("admin_delivery_status", model);
    }
}

    /*
    (Opcjonalnie) AdminController – jeśli aplikacja przewiduje panel administracyjny, warto stworzyć kontroler
    umożliwiający zarządzanie restauracjami, menu, zamówieniami czy użytkownikami.

    AdminController – do zarządzania administracyjnymi funkcjami systemu, takimi jak moderacja użytkowników,
    zarządzanie restauracjami czy kontrola nad procesami płatności i dostaw.
    */