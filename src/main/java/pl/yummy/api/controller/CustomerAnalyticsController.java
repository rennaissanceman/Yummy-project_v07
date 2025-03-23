package pl.yummy.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.yummy.business.CustomerService;
import pl.yummy.api.dto.CustomerActivityHistoryViewDTO;
import pl.yummy.api.dto.mapper.CustomerActivityHistoryViewMapper;
import pl.yummy.domain.CustomerActivityHistoryView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping(CustomerAnalyticsController.BASE_PATH)
public class CustomerAnalyticsController {

    // Statyczne endpointy dla analityki klientów
    public static final String BASE_PATH = "/customer/analytics";
    public static final String ACTIVITY_HISTORY = BASE_PATH + "/activity-history";

    private final CustomerService customerService;
    private final CustomerActivityHistoryViewMapper historyMapper;

    /**
     * GET – Wyświetla historię aktywności klienta (łączna liczba zamówień, łączny wydatek, lista ostatnich zamówień)
     * na podstawie numeru klienta.
     */
    @GetMapping("/activity-history")
    public ModelAndView customerActivityHistory(@RequestParam("customerNumber") String customerNumber) {
        List<CustomerActivityHistoryView> historyList = customerService.getCustomerActivityHistory(customerNumber);
        List<CustomerActivityHistoryViewDTO> dtos = historyList.stream()
                .map(historyMapper::toDTO)
                .collect(Collectors.toList());
        return new ModelAndView("customer_activity_history", Map.of("historyList", dtos));
    }
}

    /*
    Odpowiada za prezentację analityki klienta, wyświetlając historię aktywności i podsumowanie zamówień.
    */