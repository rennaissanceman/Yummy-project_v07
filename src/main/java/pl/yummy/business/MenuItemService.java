package pl.yummy.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.yummy.business.dao.MenuItemDAO;
import pl.yummy.domain.MenuItem;
import pl.yummy.domain.enums.DietTypeEnumDomain;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MenuItemService {

    private final MenuItemDAO menuItemDAO;

    @Transactional(readOnly = true)
    public Optional<MenuItem> findByItemName(String itemName) {
        return menuItemDAO.findByItemName(itemName);
    }

    @Transactional(readOnly = true)
    public List<MenuItem> findByMenuId(Long menuId) {
        return menuItemDAO.findByMenu_MenuId(menuId);
    }

    @Transactional(readOnly = true)
    public List<MenuItem> findByDietType(DietTypeEnumDomain dietType) {
        return menuItemDAO.findByDietType(dietType);
    }

    @Transactional(readOnly = true)
    public List<MenuItem> findAvailableItems() {
        return menuItemDAO.findByIsAvailableTrue();
    }

    @Transactional(readOnly = true)
    public List<MenuItem> findByDescription(String keyword) {
        return menuItemDAO.findByDescriptionContainingIgnoreCase(keyword);
    }

}

    /*
    ___________________________________________________________________________
    MenuItemService

    - Odpowiada za operacje na pozycjach w menu, np. wyszukiwanie pozycji po nazwie, dla danego menu, według typu diety,
    dostępności oraz na podstawie opisu.
    - Wstrzykiwany komponent: MenuItemDAO.
    */