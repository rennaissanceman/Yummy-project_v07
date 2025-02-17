package pl.yummy.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.yummy.business.dao.MenuDAO;
import pl.yummy.domain.Customer;
import pl.yummy.domain.Menu;
import pl.yummy.infrastructure.database.entity.CustomerEntity;
import pl.yummy.infrastructure.database.entity.MenuEntity;
import pl.yummy.infrastructure.database.repository.jpa.MenuJpaRepository;
import pl.yummy.infrastructure.database.repository.mapper.MenuEntityMapper;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class MenuRepository implements MenuDAO {


    private final MenuJpaRepository menuJpaRepository;
    private final MenuEntityMapper menuEntityMapper;;

    @Override
    public Optional<Menu> findByMenuName(String menuName) {
        return menuJpaRepository.findByMenuName(menuName)
                .map(menuEntityMapper::mapFromEntity);
    }

    @Override
    public List<Menu> findByRestaurant_RestaurantId(Long restaurantId) {
        return menuJpaRepository.findByRestaurant_RestaurantId(restaurantId)
                .stream()
                .map(menuEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Menu> findByValidFromBeforeAndValidToAfter(OffsetDateTime startDate, OffsetDateTime endDate) {
        return menuJpaRepository.findByValidFromBeforeAndValidToAfter(startDate, endDate)
                .stream()
                .map(menuEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Menu> findByCreatedAtAfter(OffsetDateTime createdAt) {
        return menuJpaRepository.findByCreatedAtAfter(createdAt)
                .stream()
                .map(menuEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Menu> findByDescriptionContainingIgnoreCase(String keyword) {
        return menuJpaRepository.findByDescriptionContainingIgnoreCase(keyword)
                .stream()
                .map(menuEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Menu save(Menu menu) {
        MenuEntity toSave = menuEntityMapper.mapToEntity(menu);
        MenuEntity saved = menuJpaRepository.save(toSave);
        return menuEntityMapper.mapFromEntity(saved);
    }
}
