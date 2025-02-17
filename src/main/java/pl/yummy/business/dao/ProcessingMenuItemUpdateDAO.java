package pl.yummy.business.dao;

import pl.yummy.domain.requests.MenuItemUpdateRequest;

public interface ProcessingMenuItemUpdateDAO {

    void updateMenuItem(MenuItemUpdateRequest request);
}
