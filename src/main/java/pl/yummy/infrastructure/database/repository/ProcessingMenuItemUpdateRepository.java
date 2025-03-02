package pl.yummy.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.yummy.business.dao.ProcessingMenuItemUpdateDAO;
import pl.yummy.domain.RequestMenuItemUpdate;
import pl.yummy.infrastructure.database.repository.jpa.MenuItemJpaRepository;
import pl.yummy.infrastructure.database.repository.mapper.MenuItemEntityMapper;

@Repository
@AllArgsConstructor
public class ProcessingMenuItemUpdateRepository implements ProcessingMenuItemUpdateDAO {

    /*
    Repozytorium aktualizujące dane pozycji w menu. Przyjmuje MenuItemUpdateRequest i aktualizuje odpowiednią encję w bazie.

    – Odpowiada za aktualizację pozycji w menu. Przyjmuje obiekt MenuItemUpdateRequest (zawierający
    identyfikator pozycji, nowy status dostępności, cenę oraz opcjonalny opis) i mapuje te dane
    na istniejącą encję pozycji w menu (MenuItemEntity), zapisując zaktualizowane dane.
    */

    private final MenuItemJpaRepository menuItemJpaRepository;
    private final MenuItemEntityMapper menuItemEntityMapper;

    @Override
    @Transactional
    public void updateMenuItem(RequestMenuItemUpdate request) {
        var menuItemEntity = menuItemJpaRepository.findById(request.getMenuItemId())
                .orElseThrow(() -> new RuntimeException("Menu item not found: " + request.getMenuItemId()));
        menuItemEntity.setIsAvailable(request.getIsAvailable());
        menuItemEntity.setPrice(request.getPrice());
        menuItemEntity.setDescription(request.getDescription());
        menuItemJpaRepository.saveAndFlush(menuItemEntity);
    }
}
