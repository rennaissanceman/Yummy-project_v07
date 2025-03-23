package pl.yummy.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.yummy.business.OwnerService;
import pl.yummy.api.dto.OwnerDTO;
import pl.yummy.api.dto.mapper.OwnerMapper;
import pl.yummy.domain.Owner;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping(OwnerController.BASE_PATH)
public class OwnerController {

    // Statyczne endpointy dla właścicieli restauracji
    public static final String BASE_PATH = "/owner";
    public static final String ALL = BASE_PATH + "/all";

    private final OwnerService ownerService;
    private final OwnerMapper ownerMapper;

    /*
     * GET – Wyświetla listę wszystkich właścicieli restauracji.
     */
    @GetMapping("/all")
    public ModelAndView listOwners() {
        // Przykładowo pobieramy wszystkich właścicieli (metoda findByMinimumRestaurants(0L))
        List<Owner> owners = ownerService.findByMinimumRestaurants(0L);
        List<OwnerDTO> dtos = owners.stream()
                .map(ownerMapper::toDTO)
                .collect(Collectors.toList());
        return new ModelAndView("owner_list", Map.of("owners", dtos));
    }
}

    /*
    Umożliwia przeglądanie właścicieli restauracji oraz wyszukiwanie ich na podstawie kryteriów
    (np. minimalna liczba restauracji).
    */