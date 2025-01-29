package pl.yummy.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.yummy.business.dao.MenuDAO;
import pl.yummy.domain.Menu;
import pl.yummy.infrastructure.database.repository.jpa.MenuJpaRepository;
import pl.yummy.infrastructure.database.repository.mapper.MenuEntityMapper;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class MenuRepository implements MenuDAO {


    private final MenuJpaRepository menuJpaRepository;
    private final MenuEntityMapper mapper;

    @Override
    public Optional<Menu> findByMenuName(String menuName) {
        return menuJpaRepository.findByMenuName(menuName)
                .map(mapper::mapFromEntity);
    }

    @Override
    public List<Menu> findByRestaurant_RestaurantId(Long restaurantId) {
        return menuJpaRepository.findByRestaurant_RestaurantId(restaurantId).stream()
                .map(mapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<Menu> findByValidFromBeforeAndValidToAfter(OffsetDateTime fromDate, OffsetDateTime toDate) {
        return menuJpaRepository.findByValidFromBeforeAndValidToAfter(fromDate, toDate).stream()
                .map(mapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<Menu> findByCreatedAtAfter(OffsetDateTime createdAt) {
        return menuJpaRepository.findByCreatedAtAfter(createdAt).stream()
                .map(mapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<Menu> findByDescriptionContainingIgnoreCase(String keyword) {
        return menuJpaRepository.findByDescriptionContainingIgnoreCase(keyword).stream()
                .map(mapper::mapFromEntity)
                .toList();
    }
}
