package pl.yummy.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.yummy.business.dao.MenuItemUpdateProcessingDAO;
import pl.yummy.domain.MenuItemUpdateRequest;

@Service
@AllArgsConstructor
public class MenuItemUpdateService {

    private final MenuItemUpdateProcessingDAO menuItemUpdateProcessingDAO;

    @Transactional
    public void updateMenuItem(MenuItemUpdateRequest request) {
        menuItemUpdateProcessingDAO.updateMenuItem(request);
    }
}

    /*
    MenuItemUpdateService

    - Obsługuje proces aktualizacji pozycji w menu.
    - Wstrzykiwany komponent: ProcessingMenuItemUpdateDAO.
    */