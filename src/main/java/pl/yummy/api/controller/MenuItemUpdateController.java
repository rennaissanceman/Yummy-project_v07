package pl.yummy.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.yummy.business.MenuItemUpdateService;
import pl.yummy.api.dto.MenuItemUpdateRequestDTO;
import pl.yummy.api.dto.mapper.MenuItemUpdateRequestMapper;
import pl.yummy.domain.MenuItemUpdateRequest;

import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping(MenuItemUpdateController.BASE_PATH)
public class MenuItemUpdateController {

    // Statyczne endpointy dla aktualizacji pozycji menu
    public static final String BASE_PATH = "/menu-item-update";
    public static final String FORM = BASE_PATH + "/form";
    public static final String SUBMIT = BASE_PATH + "/submit";

    private final MenuItemUpdateService menuItemUpdateService;
    private final MenuItemUpdateRequestMapper menuItemUpdateRequestMapper;

    /*
     * GET – Wyświetla formularz edycji pozycji menu.
     */
    @GetMapping("/form")
    public ModelAndView showUpdateForm() {
        MenuItemUpdateRequestDTO dto = new MenuItemUpdateRequestDTO();
        return new ModelAndView("menu_item_update_form", Map.of("updateDTO", dto));
    }

    /*
     * POST – Przetwarza aktualizację pozycji menu.
     */
    @PostMapping("/submit")
    public ModelAndView updateMenuItem(@ModelAttribute("updateDTO") MenuItemUpdateRequestDTO dto) {
        MenuItemUpdateRequest request = menuItemUpdateRequestMapper.toDomain(dto);
        menuItemUpdateService.updateMenuItem(request);
        return new ModelAndView("redirect:" + MenuItemController.LIST);
    }
}

    /*
    Odpowiada za aktualizację danych pozycji menu (np. cena, dostępność, opis).
    */