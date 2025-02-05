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
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class MenuItemRepository implements MenuItemDAO {


    private final MenuItemJpaRepository menuItemJpaRepository;
    private final MenuItemEntityMapper menuItemEntityMapper;

    @Override
    public Optional<MenuItem> findByItemName(String itemName) {
        return menuItemJpaRepository.findByItemName(itemName)
                .map(menuItemEntityMapper::mapFromEntity);
    }

    @Override
    public List<MenuItem> findByMenu_MenuId(Long menuId) {
        return menuItemJpaRepository.findByMenu_MenuId(menuId)
                .stream()
                .map(menuItemEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<MenuItem> findByDietType(DietTypeEnumDomain dietType) {
        return menuItemJpaRepository.findByDietType(
                        // Zakładamy zgodność nazw enumów:
                        Enum.valueOf(
                                menuItemEntityMapper.getDietTypeEntityClass(),
                                dietType.name()
                        )
                )
                .stream()
                .map(menuItemEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<MenuItem> findByIsAvailableTrue() {
        return menuItemJpaRepository.findByIsAvailableTrue()
                .stream()
                .map(menuItemEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<MenuItem> findByDescriptionContainingIgnoreCase(String keyword) {
        return menuItemJpaRepository.findByDescriptionContainingIgnoreCase(keyword)
                .stream()
                .map(menuItemEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }
}
