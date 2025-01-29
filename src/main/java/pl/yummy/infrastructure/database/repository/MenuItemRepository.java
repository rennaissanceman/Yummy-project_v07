package pl.yummy.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.yummy.business.dao.MenuItemDAO;
import pl.yummy.domain.MenuItem;
import pl.yummy.domain.enums.DietTypeEnumDomain;
import pl.yummy.infrastructure.database.repository.jpa.MenuItemJpaRepository;
import pl.yummy.infrastructure.database.repository.mapper.MenuItemEntityMapper;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class MenuItemRepository implements MenuItemDAO {


    private final MenuItemJpaRepository menuItemJpaRepository;
    private final MenuItemEntityMapper mapper;

    @Override
    public Optional<MenuItem> findByItemName(String itemName) {
        return menuItemJpaRepository.findByItemName(itemName)
                .map(mapper::mapFromEntity);
    }

    @Override
    public List<MenuItem> findByMenu_MenuId(Long menuId) {
        return menuItemJpaRepository.findByMenu_MenuId(menuId).stream()
                .map(mapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<MenuItem> findByDietType(DietTypeEnumDomain dietType) {
        return menuItemJpaRepository.findByDietType(dietType).stream()
                .map(mapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<MenuItem> findByIsAvailableTrue() {
        return menuItemJpaRepository.findByIsAvailableTrue().stream()
                .map(mapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<MenuItem> findByDescriptionContainingIgnoreCase(String keyword) {
        return menuItemJpaRepository.findByDescriptionContainingIgnoreCase(keyword).stream()
                .map(mapper::mapFromEntity)
                .toList();
    }
}
