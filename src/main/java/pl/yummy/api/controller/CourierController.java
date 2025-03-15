package pl.yummy.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.yummy.api.dto.CourierDTO;
import pl.yummy.api.dto.mapper.CourierMapper;
import pl.yummy.business.CourierService;
import pl.yummy.domain.Courier;
import pl.yummy.domain.enums.CourierStatusEnumDomain;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class CourierController {

    private static final String COURIER_LIST = "/courier";
    private static final String COURIER_DETAILS = "/courier/details";
    private static final String COURIER_MIN_RATING = "/courier/minRating";
    private static final String COURIER_HIRED_AFTER = "/courier/hiredAfter";
    private static final String COURIER_BY_VEHICLE = "/courier/byVehicle";

    private final CourierService courierService;
    private final CourierMapper courierMapper;

    // Endpoint do pobrania listy dostępnych kurierów
    @GetMapping(COURIER_LIST)
    public ModelAndView listAvailableCouriers() {
        List<Courier> couriers = courierService.findByCourierStatus(CourierStatusEnumDomain.AVAILABLE);
        List<CourierDTO> courierDTOs = couriers.stream()
                .map(courierMapper::toDTO)
                .collect(Collectors.toList());
        Map<String, Object> model = Map.of("couriers", courierDTOs);
        return new ModelAndView("courier_list", model);
    }

    // Endpoint do pobrania szczegółów kuriera na podstawie unikalnego numeru
    @GetMapping(COURIER_DETAILS)
    public ModelAndView courierDetails(@RequestParam("courierNumber") String courierNumber) {
        Courier courier = courierService.findByCourierNumber(courierNumber);
        CourierDTO courierDTO = courierMapper.toDTO(courier);
        Map<String, Object> model = Map.of("courier", courierDTO);
        return new ModelAndView("courier_details", model);
    }

    // Endpoint do pobrania listy kurierów, których średnia ocena jest większa lub równa podanemu progowi
    @GetMapping(COURIER_MIN_RATING)
    public ModelAndView listCouriersWithMinimumRating(@RequestParam("minRating") Double minRating) {
        List<Courier> couriers = courierService.findCouriersWithMinimumRating(minRating);
        List<CourierDTO> courierDTOs = couriers.stream()
                .map(courierMapper::toDTO)
                .collect(Collectors.toList());
        Map<String, Object> model = Map.of("couriers", courierDTOs);
        return new ModelAndView("courier_min_rating", model);
    }

    // Endpoint do pobrania listy kurierów zatrudnionych po określonej dacie
    @GetMapping(COURIER_HIRED_AFTER)
    public ModelAndView listCouriersHiredAfter(
            @RequestParam("hireDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime hireDate) {
        List<Courier> couriers = courierService.findCouriersHiredAfter(hireDate);
        List<CourierDTO> courierDTOs = couriers.stream()
                .map(courierMapper::toDTO)
                .collect(Collectors.toList());
        Map<String, Object> model = Map.of("couriers", courierDTOs);
        return new ModelAndView("courier_hired_after", model);
    }

    // Endpoint do pobrania listy kurierów korzystających z określonego typu pojazdu
    @GetMapping(COURIER_BY_VEHICLE)
    public ModelAndView listCouriersByVehicleType(@RequestParam("vehicleType") String vehicleType) {
        List<Courier> couriers = courierService.findCouriersByVehicleType(vehicleType);
        List<CourierDTO> courierDTOs = couriers.stream()
                .map(courierMapper::toDTO)
                .collect(Collectors.toList());
        Map<String, Object> model = Map.of("couriers", courierDTOs);
        return new ModelAndView("courier_by_vehicle", model);
    }
}

    /*
    CourierController – jeśli chcemy wyodrębnić zarządzanie kurierami od ogólnej logiki dostaw,
    kontroler dedykowany kurierom może obsługiwać rejestrację, aktualizację statusu oraz harmonogramy pracy.

    kontroler udostępnia endpointy do:
    - Pobrania listy dostępnych kurierów
    - Pobrania szczegółów kuriera na podstawie unikalnego numeru
    - Pobrania listy kurierów spełniających kryterium minimalnej oceny
    - Pobrania listy kurierów zatrudnionych po określonej dacie
    - Pobrania listy kurierów korzystających z danego typu pojazdu

    Wyjaśnienie
    - CourierDTO – używany do prezentacji danych kuriera w widoku.
    - CourierMapper – konwertuje obiekty klasy domenowej Courier na CourierDTO.
    - CourierService – udostępnia metody do pobierania danych o kurierach, zgodnie z metodami zawartymi w przesłanej klasie.
    - Endpointy są tworzone za pomocą adnotacji @GetMapping z zadeklarowanymi ścieżkami jako stałymi.
    */