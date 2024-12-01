package pl.yummy.business.dao;

import pl.yummy.infrastructure.database.entity.RestaurantEntity;

import java.util.List;

public interface RestaurantDAO {
    RestaurantEntity findById(Long id); // Znajdź restaurację po ID
    List<RestaurantEntity> findAll(); // Pobierz wszystkie restauracje
    List<RestaurantEntity> findByCuisineType(String cuisineType); // Znajdź restauracje po typie kuchni
    void save(RestaurantEntity restaurant); // Zapisz lub zaktualizuj restaurację
    void deleteById(Long id); // Usuń restaurację
}

