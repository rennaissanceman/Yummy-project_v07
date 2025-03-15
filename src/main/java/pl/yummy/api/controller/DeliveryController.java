package pl.yummy.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.yummy.api.dto.DeliveryDTO;
import pl.yummy.domain.Delivery;
import pl.yummy.domain.RequestDeliveryAssignment;
import pl.yummy.domain.ViewDeliveryStatusOverview;
import pl.yummy.domain.enums.DeliveryStatusEnumDomain;
import pl.yummy.api.dto.mapper.DeliveryMapper;
import pl.yummy.business.DeliveryAssignmentService;
import pl.yummy.business.DeliveryService;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class DeliveryController {

    private static final String DELIVERY_LIST = "/delivery";
    private static final String DELIVERY_DETAILS = "/delivery/details";
    private static final String DELIVERY_CREATE = "/delivery/create";
    private static final String DELIVERY_UPDATE = "/delivery/update";
    private static final String DELIVERY_DELETE = "/delivery/delete";
    private static final String DELIVERY_BY_COURIER = "/delivery/courier";
    private static final String DELIVERY_BY_STATUS = "/delivery/status";
    private static final String DELIVERY_STARTING_AFTER = "/delivery/starting-after";
    private static final String DELIVERY_LATE = "/delivery/late";
    private static final String DELIVERY_BY_AREA = "/delivery/by-area";
    private static final String DELIVERY_RECALCULATE_FEE = "/delivery/recalculate-fee";
    private static final String DELIVERY_STATUS_OVERVIEW = "/delivery/status-overview";
    private static final String DELIVERY_ASSIGN = "/delivery/assign";
    private static final String DELIVERY_ASSIGN_COURIER = "/delivery/assign-courier";

    private final DeliveryService deliveryService;
    private final DeliveryMapper deliveryMapper;
    private final DeliveryAssignmentService deliveryAssignmentService;

    // GET – Wyświetlenie listy dostaw.
    @GetMapping(DELIVERY_LIST)
    public String listDeliveries(Model model) {
        List<Delivery> deliveries = deliveryService.getAllDeliveries();
        List<DeliveryDTO> deliveryDTOs = deliveries.stream()
                .map(deliveryMapper::toDTO)
                .collect(Collectors.toList());
        model.addAttribute("deliveryDTOs", deliveryDTOs);
        return "delivery_list";
    }

    // GET – Wyświetlenie szczegółów dostawy na podstawie ID.
    @GetMapping(DELIVERY_DETAILS)
    public String deliveryDetails(@RequestParam("id") Long id, Model model) {
        Delivery delivery = deliveryService.getDeliveryById(id);
        DeliveryDTO deliveryDTO = deliveryMapper.toDTO(delivery);
        model.addAttribute("deliveryDTO", deliveryDTO);
        return "delivery_details";
    }

    // GET – Wyświetlenie formularza tworzenia nowej dostawy.
    @GetMapping(DELIVERY_CREATE)
    public String showCreateDeliveryForm(Model model) {
        model.addAttribute("deliveryForm", new DeliveryDTO());
        return "delivery_create";
    }

    // POST – Utworzenie nowej dostawy.
    @PostMapping(DELIVERY_CREATE)
    public String createDelivery(@ModelAttribute("deliveryForm") DeliveryDTO deliveryDTO) {
        // Zakładamy, że DeliveryMapper posiada metodę toDomain (rozszerzoną o tą konwersję)
        Delivery delivery = deliveryMapper.toDomain(deliveryDTO);
        deliveryService.createDelivery(delivery);
        return "redirect:" + DELIVERY_LIST;
    }

    // GET – Wyświetlenie formularza edycji danych dostawy.
    @GetMapping(DELIVERY_UPDATE)
    public String showUpdateDeliveryForm(@RequestParam("id") Long id, Model model) {
        Delivery delivery = deliveryService.getDeliveryById(id);
        DeliveryDTO deliveryDTO = deliveryMapper.toDTO(delivery);
        model.addAttribute("deliveryForm", deliveryDTO);
        return "delivery_update";
    }

    // POST – Aktualizacja danych dostawy.
    @PostMapping(DELIVERY_UPDATE)
    public String updateDelivery(@ModelAttribute("deliveryForm") DeliveryDTO deliveryDTO) {
        Delivery delivery = deliveryMapper.toDomain(deliveryDTO);
        deliveryService.updateDelivery(delivery);
        return "redirect:" + DELIVERY_DETAILS + "?id=" + delivery.getDeliveryId();
    }

    // -------------------- Dodatkowe metody --------------------

    // GET – Usunięcie dostawy.
    @GetMapping(DELIVERY_DELETE)
    public String deleteDelivery(@RequestParam("id") Long id) {
        deliveryService.deleteDelivery(id);
        return "redirect:" + DELIVERY_LIST;
    }

    // GET – Wyświetlenie dostaw przypisanych do danego kuriera.
    @GetMapping(DELIVERY_BY_COURIER)
    public String deliveriesByCourier(@RequestParam("courierId") Long courierId, Model model) {
        List<Delivery> deliveries = deliveryService.findDeliveriesByCourier(courierId);
        List<DeliveryDTO> deliveryDTOs = deliveries.stream()
                .map(deliveryMapper::toDTO)
                .collect(Collectors.toList());
        model.addAttribute("deliveryDTOs", deliveryDTOs);
        model.addAttribute("courierId", courierId);
        return "delivery_by_courier";
    }

    // GET – Wyświetlenie dostaw o określonym statusie.
    @GetMapping(DELIVERY_BY_STATUS)
    public String deliveriesByStatus(@RequestParam("status") String status, Model model) {
        DeliveryStatusEnumDomain statusEnum = DeliveryStatusEnumDomain.valueOf(status.toUpperCase());
        List<Delivery> deliveries = deliveryService.findDeliveriesByStatus(statusEnum);
        List<DeliveryDTO> deliveryDTOs = deliveries.stream()
                .map(deliveryMapper::toDTO)
                .collect(Collectors.toList());
        model.addAttribute("deliveryDTOs", deliveryDTOs);
        model.addAttribute("status", status);
        return "delivery_by_status";
    }

    // GET – Wyświetlenie dostaw rozpoczynających się po podanej dacie.
    @GetMapping(DELIVERY_STARTING_AFTER)
    public String deliveriesStartingAfter(@RequestParam("startTime") String startTimeStr, Model model) {
        OffsetDateTime startTime = OffsetDateTime.parse(startTimeStr);
        List<Delivery> deliveries = deliveryService.findDeliveriesStartingAfter(startTime);
        List<DeliveryDTO> deliveryDTOs = deliveries.stream()
                .map(deliveryMapper::toDTO)
                .collect(Collectors.toList());
        model.addAttribute("deliveryDTOs", deliveryDTOs);
        model.addAttribute("startTime", startTimeStr);
        return "delivery_starting_after";
    }

    // GET – Wyświetlenie dostaw opóźnionych.
    @GetMapping(DELIVERY_LATE)
    public String lateDeliveries(@RequestParam("actualDeliveryDateTime") String actualDeliveryDateTimeStr, Model model) {
        OffsetDateTime actualDeliveryDateTime = OffsetDateTime.parse(actualDeliveryDateTimeStr);
        List<Delivery> deliveries = deliveryService.findLateDeliveries(actualDeliveryDateTime);
        List<DeliveryDTO> deliveryDTOs = deliveries.stream()
                .map(deliveryMapper::toDTO)
                .collect(Collectors.toList());
        model.addAttribute("deliveryDTOs", deliveryDTOs);
        model.addAttribute("actualDeliveryDateTime", actualDeliveryDateTimeStr);
        return "delivery_late";
    }

    // GET – Wyświetlenie dostaw dla danego obszaru dostawy.
    @GetMapping(DELIVERY_BY_AREA)
    public String deliveriesByArea(@RequestParam("areaId") Long areaId, Model model) {
        List<Delivery> deliveries = deliveryService.findDeliveriesByAvailableDeliveryArea(areaId);
        List<DeliveryDTO> deliveryDTOs = deliveries.stream()
                .map(deliveryMapper::toDTO)
                .collect(Collectors.toList());
        model.addAttribute("deliveryDTOs", deliveryDTOs);
        model.addAttribute("areaId", areaId);
        return "delivery_by_area";
    }

    // GET – Wyświetlenie formularza przeliczenia opłaty dostawy.
    @GetMapping(DELIVERY_RECALCULATE_FEE)
    public String showRecalculateFeeForm(Model model) {
        // Formularz powinien umożliwiać podanie: deliveryId, baseFee oraz multiplier.
        return "delivery_recalculate_fee_form";
    }

    // POST – Przeliczenie opłaty dostawy.
    @PostMapping(DELIVERY_RECALCULATE_FEE)
    public String recalculateFee(@RequestParam("deliveryId") Long deliveryId,
                                 @RequestParam("baseFee") String baseFeeStr,
                                 @RequestParam("multiplier") String multiplierStr,
                                 Model model) {
        BigDecimal baseFee = new BigDecimal(baseFeeStr);
        BigDecimal multiplier = new BigDecimal(multiplierStr);
        Delivery updatedDelivery = deliveryService.recalculateDeliveryFee(deliveryId, baseFee, multiplier);
        return "redirect:" + DELIVERY_DETAILS + "?id=" + updatedDelivery.getDeliveryId();
    }

    // GET – Wyświetlenie statystyk dostaw dla danego obszaru.
    @GetMapping(DELIVERY_STATUS_OVERVIEW)
    public String deliveryStatusOverview(@RequestParam("areaId") Long areaId, Model model) {
        ViewDeliveryStatusOverview overview = deliveryService.getDeliveryStatusOverview(areaId);
        model.addAttribute("overview", overview);
        model.addAttribute("areaId", areaId);
        return "delivery_status_overview";
    }

    // -------------------- Integracja z DeliveryAssignmentService --------------------

    // GET – Wyświetlenie formularza przypisania dostawy (w celu przypisania konkretnego kuriera)
    @GetMapping(DELIVERY_ASSIGN)
    public String showDeliveryAssignmentForm(Model model) {
        // Zakładamy, że RequestDeliveryAssignment posiada konstruktor bezargumentowy
        model.addAttribute("deliveryAssignment", new RequestDeliveryAssignment());
        return "delivery_assignment_form";
    }

    // POST – Przetwarzanie formularza przypisania dostawy
    @PostMapping(DELIVERY_ASSIGN)
    public String assignDelivery(@ModelAttribute("deliveryAssignment") RequestDeliveryAssignment request) {
        deliveryAssignmentService.assignDelivery(request);
        return "redirect:" + DELIVERY_LIST;
    }

    // POST – Przypisanie dostępnego kuriera do dostawy (na podstawie identyfikatora dostawy)
    @PostMapping(DELIVERY_ASSIGN_COURIER)
    public String assignCourierToDelivery(@RequestParam("deliveryId") Long deliveryId) {
        deliveryAssignmentService.assignCourierToDelivery(deliveryId);
        return "redirect:" + DELIVERY_DETAILS + "?id=" + deliveryId;
    }
}

    /*
    DeliveryController – zajmuje się przypisywaniem kurierów do zamówień, śledzeniem statusu dostaw oraz ewentualnymi
    zmianami statusu zamówienia (np. przygotowywane, w drodze, dostarczone).

    5. DeliveryController

    Odpowiedzialności:
    - Zarządzanie procesem dostawy.
    - Przypisywanie zleceń do kurierów.
    - Śledzenie statusu dostawy.


    Przykładowe endpointy:
    - GET /api/deliveries/available – lista dostępnych zleceń dostawy.
    - POST /api/deliveries/assign – przypisanie dostawy do kuriera.
    - GET /api/deliveries/{id} – pobranie szczegółów dostawy.
    - PUT /api/deliveries/{id}/update – aktualizacja statusu dostawy.

    */