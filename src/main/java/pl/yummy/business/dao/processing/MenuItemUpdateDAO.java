package pl.yummy.business.dao.processing;

import pl.yummy.domain.requests.MenuItemUpdateRequest;

public interface MenuItemUpdateDAO {

    void updateMenuItem(MenuItemUpdateRequest request);
}
