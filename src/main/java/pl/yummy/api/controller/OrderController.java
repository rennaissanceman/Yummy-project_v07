package pl.yummy.api.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.yummy.api.dto.OrdersDTO;
import pl.yummy.api.dto.RequestOrderPlacementDTO;
import pl.yummy.api.dto.mapper.OrdersMapper;
import pl.yummy.api.dto.mapper.RequestOrderPlacementMapper;
import pl.yummy.business.OrderLifecycleService;
import pl.yummy.business.OrderPlacementService;
import pl.yummy.business.OrderProcessingService;
import pl.yummy.business.OrderService;
import pl.yummy.domain.Orders;
import pl.yummy.domain.RequestOrderPlacement;
import pl.yummy.domain.RequestOrderProcessing;
import pl.yummy.domain.enums.OrdersStatusEnumDomain;
import pl.yummy.domain.ViewRevenueReport;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class OrderController {

    // Definicje endpointów
    private static final String ORDER_HISTORY = "/order/history";
    private static final String ORDER_DETAILS = "/order/details";
    private static final String ORDER_PLACE = "/order/place";
    private static final String ORDER_STATUS = "/order/status";
    private static final String ORDER_CREATED_AFTER = "/order/created-after";
    private static final String ORDER_TOTAL_AMOUNT = "/order/total-amount";
    private static final String ORDER_WITHOUT_DELIVERY = "/order/without-delivery";
    private static final String ORDER_ALL = "/order/all";
    private static final String ORDER_REVENUE_REPORT = "/order/revenue-report";
    private static final String ORDER_MARK_COMPLETED = "/order/mark-completed";
    private static final String ORDER_SAVE = "/order/save-order";
    // Nowe endpointy
    private static final String ORDER_PROCESS = "/order/process";
    private static final String ORDER_UPDATE_STATUS = "/order/update-status";
    private static final String ORDER_CANCEL = "/order/cancel";

    private final OrderService orderService;
    private final OrderPlacementService orderPlacementService;
    private final OrdersMapper ordersMapper;
    private final RequestOrderPlacementMapper requestOrderPlacementMapper;
    // Nowe serwisy
    private final OrderProcessingService orderProcessingService;
    private final OrderLifecycleService orderLifecycleService;

    // GET – Wyświetlenie historii zamówień klienta.
    @GetMapping(ORDER_HISTORY)
    public ModelAndView orderHistory(@RequestParam("customerId") Long customerId) {
        // Pobieramy listę zamówień dla danego klienta i mapujemy je na DTO.
        List<OrdersDTO> ordersDTOList = orderService.findOrdersByCustomer(customerId)
                .stream()
                .map(ordersMapper::toDTO)
                .collect(Collectors.toList());
        // Przygotowujemy model danych z listą DTO zamówień.
        Map<String, Object> model = Map.of("ordersDTOList", ordersDTOList);
        // Zwracamy widok "order_history" wraz z modelem.
        return new ModelAndView("order_history", model);
    }

    // GET – Wyświetlenie szczegółów zamówienia.
    @GetMapping(ORDER_DETAILS)
    public ModelAndView orderDetails(@RequestParam("orderNumber") String orderNumber) {
        // Pobieramy szczegóły zamówienia na podstawie numeru zamówienia.
        var order = orderService.findActiveOrder(orderNumber);
        var orderDTO = ordersMapper.toDTO(order);
        // Przygotowujemy model danych z DTO zamówienia.
        Map<String, Object> model = Map.of("orderDTO", orderDTO);
        // Zwracamy widok "order_details" wraz z modelem.
        return new ModelAndView("order_details", model);
    }

    // GET – Wyświetlenie formularza składania zamówienia.
    @GetMapping(ORDER_PLACE)
    public ModelAndView showOrderPlacementForm() {
        // Używamy metody buildDefault() do utworzenia domyślnego obiektu DTO do składania zamówienia.
        Map<String, Object> model = Map.of("orderPlacementDTO", RequestOrderPlacementDTO.buildDefault());
        // Zwracamy widok formularza "order_place" wraz z modelem.
        return new ModelAndView("order_place", model);
    }

    // POST – Składanie zamówienia.
    @PostMapping(ORDER_PLACE)
    public ModelAndView placeOrder(
            @Valid @ModelAttribute("orderPlacementDTO") RequestOrderPlacementDTO requestDTO,
            BindingResult bindingResult) {
        // Sprawdzamy, czy dane przesłane z formularza są poprawne.
        if (bindingResult.hasErrors()) {
            // Jeśli wystąpiły błędy walidacji, zwracamy widok "error".
            return new ModelAndView("error");
        }
        // Mapujemy DTO na obiekt domenowy.
        RequestOrderPlacement domainRequest = requestOrderPlacementMapper.toDomain(requestDTO);
        // Proces składania zamówienia – metoda placeOrder zwraca wystawioną fakturę.
        var invoice = orderPlacementService.placeOrder(domainRequest);
        // Przygotowujemy model danych z wystawioną fakturą.
        Map<String, Object> model = Map.of("invoice", invoice);
        // Zwracamy widok potwierdzający złożenie zamówienia "order_place_done" wraz z modelem.
        return new ModelAndView("order_place_done", model);
    }

    // -------------------- Dodatkowe endpointy z OrderService --------------------

    // GET – Wyszukuje zamówienia o określonym statusie.
    @GetMapping(ORDER_STATUS)
    public ModelAndView ordersByStatus(@RequestParam("status") String status) {
        OrdersStatusEnumDomain statusEnum = OrdersStatusEnumDomain.valueOf(status.toUpperCase());
        List<Orders> orders = orderService.findOrdersByStatus(statusEnum);
        List<OrdersDTO> ordersDTOList = orders.stream()
                .map(ordersMapper::toDTO)
                .collect(Collectors.toList());
        Map<String, Object> model = Map.of("ordersDTOList", ordersDTOList, "status", status);
        return new ModelAndView("order_status", model);
    }

    // GET – Wyszukuje zamówienia utworzone po określonej dacie.
    @GetMapping(ORDER_CREATED_AFTER)
    public ModelAndView ordersCreatedAfter(@RequestParam("dateTime") String dateTimeStr) {
        OffsetDateTime dateTime = OffsetDateTime.parse(dateTimeStr);
        List<Orders> orders = orderService.findOrdersCreatedAfter(dateTime);
        List<OrdersDTO> ordersDTOList = orders.stream()
                .map(ordersMapper::toDTO)
                .collect(Collectors.toList());
        Map<String, Object> model = Map.of("ordersDTOList", ordersDTOList, "dateTime", dateTimeStr);
        return new ModelAndView("order_created_after", model);
    }

    // GET – Wyszukuje zamówienia o łącznej wartości większej lub równej podanej kwocie.
    @GetMapping(ORDER_TOTAL_AMOUNT)
    public ModelAndView ordersByTotalAmount(@RequestParam("amount") String amountStr) {
        BigDecimal amount = new BigDecimal(amountStr);
        List<Orders> orders = orderService.findOrdersByTotalAmountGreaterThanEqual(amount);
        List<OrdersDTO> ordersDTOList = orders.stream()
                .map(ordersMapper::toDTO)
                .collect(Collectors.toList());
        Map<String, Object> model = Map.of("ordersDTOList", ordersDTOList, "amount", amount);
        return new ModelAndView("order_total_amount", model);
    }

    // GET – Wyszukuje zamówienia, które nie mają przypisanej dostawy.
    @GetMapping(ORDER_WITHOUT_DELIVERY)
    public ModelAndView ordersWithoutDelivery() {
        List<Orders> orders = orderService.findOrdersWithoutDelivery();
        List<OrdersDTO> ordersDTOList = orders.stream()
                .map(ordersMapper::toDTO)
                .collect(Collectors.toList());
        Map<String, Object> model = Map.of("ordersDTOList", ordersDTOList);
        return new ModelAndView("order_without_delivery", model);
    }

    // GET – Pobiera wszystkie zamówienia.
    @GetMapping(ORDER_ALL)
    public ModelAndView allOrders() {
        List<Orders> orders = orderService.getAllOrders();
        List<OrdersDTO> ordersDTOList = orders.stream()
                .map(ordersMapper::toDTO)
                .collect(Collectors.toList());
        Map<String, Object> model = Map.of("ordersDTOList", ordersDTOList);
        return new ModelAndView("order_all", model);
    }

    // GET – Generuje raport przychodowy za podany okres.
    @GetMapping(ORDER_REVENUE_REPORT)
    public ModelAndView revenueReport(@RequestParam("startDate") String startDateStr,
                                      @RequestParam("endDate") String endDateStr) {
        OffsetDateTime startDate = OffsetDateTime.parse(startDateStr);
        OffsetDateTime endDate = OffsetDateTime.parse(endDateStr);
        ViewRevenueReport report = orderService.getRevenueReport(startDate, endDate);
        Map<String, Object> model = Map.of("report", report, "startDate", startDateStr, "endDate", endDateStr);
        return new ModelAndView("order_revenue_report", model);
    }

    // POST – Oznacza zamówienie jako zakończone.
    @PostMapping(ORDER_MARK_COMPLETED)
    public ModelAndView markOrderAsCompleted(@RequestParam("orderNumber") String orderNumber,
                                             @RequestParam("processingStartTime") String processingStartTimeStr) {
        OffsetDateTime processingStartTime = OffsetDateTime.parse(processingStartTimeStr);
        Orders order = orderService.findActiveOrder(orderNumber);
        Orders updatedOrder = orderService.markOrderAsCompleted(order, processingStartTime);
        Map<String, Object> model = Map.of("orderDTO", ordersMapper.toDTO(updatedOrder));
        return new ModelAndView("order_mark_completed", model);
    }

    // POST – Zapisuje (aktualizuje) zamówienie w bazie.
    @PostMapping(ORDER_SAVE)
    public ModelAndView saveOrder(@ModelAttribute("orderDTO") OrdersDTO orderDTO) {
        Orders order = ordersMapper.toDomain(orderDTO);
        Orders savedOrder = orderService.saveOrder(order);
        Map<String, Object> model = Map.of("orderDTO", ordersMapper.toDTO(savedOrder));
        return new ModelAndView("order_saved", model);
    }

    // -------------------- Integracja z OrderProcessingService --------------------

    // POST – Przetwarzanie zamówienia na podstawie żądania przetwarzania.
    // Zakładamy, że dane formularza są wiązane do obiektu RequestOrderProcessing.
    @PostMapping(ORDER_PROCESS)
    public ModelAndView processOrder(@ModelAttribute("orderProcessingRequest") RequestOrderProcessing request) {
        orderProcessingService.processOrder(request);
        return new ModelAndView("redirect:" + ORDER_DETAILS + "?orderNumber=" + request.getOrderNumber());
    }

    // -------------------- Integracja z OrderLifecycleService --------------------

    // POST – Aktualizacja statusu zamówienia.
    @PostMapping(ORDER_UPDATE_STATUS)
    public ModelAndView updateOrderStatus(@RequestParam("orderId") Long orderId,
                                          @RequestParam("newStatus") String newStatus) {
        OrdersStatusEnumDomain statusEnum = OrdersStatusEnumDomain.valueOf(newStatus.toUpperCase());
        orderLifecycleService.updateOrderStatus(orderId, statusEnum);
        return new ModelAndView("redirect:" + ORDER_ALL);
    }

    // POST – Anulowanie zamówienia.
    @PostMapping(ORDER_CANCEL)
    public ModelAndView cancelOrder(@RequestParam("orderId") Long orderId) {
        orderLifecycleService.cancelOrder(orderId);
        return new ModelAndView("redirect:" + ORDER_ALL);
    }
}

    /*
    OrderController – obsługuje składanie, przeglądanie i potwierdzanie zamówień. Może pobierać listę zamówień,
    prezentować szczegóły konkretnego zamówienia oraz przyjmować nowe zamówienia od klientów.


    2. OrderController

    Odpowiedzialności:
    - Składanie zamówień.
    - Pobieranie szczegółów zamówienia.
    - Anulowanie zamówienia oraz śledzenie statusu.


    Przykładowe endpointy:
    - POST /api/orders – składanie nowego zamówienia.
    - GET /api/orders/{id} – pobranie szczegółów konkretnego zamówienia.
    - PUT /api/orders/{id}/cancel – anulowanie zamówienia.
    - GET /api/orders – lista zamówień (np. z możliwością filtrowania po statusie).
    */
