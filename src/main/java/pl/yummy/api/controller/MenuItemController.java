package pl.yummy.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.yummy.business.MenuItemService;
import pl.yummy.api.dto.MenuItemDTO;
import pl.yummy.api.dto.mapper.MenuItemMapper;
import pl.yummy.domain.MenuItem;
import pl.yummy.domain.enums.DietTypeEnumDomain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping(MenuItemController.BASE_PATH)
public class MenuItemController {

    // Statyczna definicja endpointów dla pozycji w menu
    public static final String BASE_PATH = "/menu-item";
    public static final String LIST = BASE_PATH + "/list";

    private final MenuItemService menuItemService;
    private final MenuItemMapper menuItemMapper;

    /*
     * GET – Wyświetla listę dostępnych pozycji w menu.
     * Jeśli podany jest parametr "dietType", filtruje wyniki według typu diety.
     */
    @GetMapping("/list")
    public ModelAndView listMenuItems(@RequestParam(required = false) String dietType) {
        List<MenuItem> items;
        if (dietType != null && !dietType.isEmpty()) {
            items = menuItemService.findByDietType(DietTypeEnumDomain.valueOf(dietType.toUpperCase()));
        } else {
            items = menuItemService.findAvailableItems();
        }
        List<MenuItemDTO> dtoList = items.stream()
                .map(menuItemMapper::toDTO)
                .collect(Collectors.toList());
        return new ModelAndView("menu_item_list", Map.of("menuItems", dtoList));
    }
}

    /*
    Odpowiada za prezentację dostępnych pozycji menu, z opcjonalnym filtrowaniem po typie diety.
    */