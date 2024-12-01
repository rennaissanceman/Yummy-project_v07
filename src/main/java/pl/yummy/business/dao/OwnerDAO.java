package pl.yummy.business.dao;

import pl.yummy.infrastructure.database.entity.OwnerEntity;

import java.util.Optional;

public interface OwnerDAO {
    OwnerEntity findById(Long id); // Znajdź właściciela po ID
    Optional<OwnerEntity> findByRestaurantId(Long restaurantId); // Znajdź właściciela danej restauracji
    void save(OwnerEntity owner); // Zapisz lub zaktualizuj właściciela
    void deleteById(Long id); // Usuń właściciela
}

