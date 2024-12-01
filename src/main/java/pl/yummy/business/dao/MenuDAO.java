package pl.yummy.business.dao;

import pl.yummy.infrastructure.database.entity.MenuEntity;

import java.util.List;

public interface MenuDAO {
    MenuEntity findById(Long id); // Znajdź menu po ID
    List<MenuEntity> findByRestaurantId(Long restaurantId); // Znajdź menu danej restauracji
    void save(MenuEntity menu); // Zapisz lub zaktualizuj menu
    void deleteById(Long id); // Usuń menu
}

