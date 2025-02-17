package pl.yummy.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.yummy.business.dao.MenuDAO;
import pl.yummy.domain.Menu;
import pl.yummy.domain.exception.NotFoundException;
import pl.yummy.infrastructure.database.repository.jpa.MenuJpaRepository;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class MenuService {

    // Wstrzykujemy jedynie interfejs DAO, dzięki czemu mamy luźniejsze powiązania między warstwami
    private final MenuDAO menuDAO;

    /**
     * Znajduje menu po unikalnej nazwie.
     *
     * @param menuName unikalna nazwa menu
     * @return Menu o podanej nazwie
     * @throws NotFoundException gdy menu o podanej nazwie nie zostanie znalezione
     */
    public Menu getMenuByName(String menuName) {
        return menuDAO.findByMenuName(menuName)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono menu o nazwie: " + menuName));
    }

    /**
     * Pobiera listę menu dla danej restauracji.
     *
     * @param restaurantId identyfikator restauracji
     * @return lista menu powiązanych z restauracją
     */
    public List<Menu> getMenusByRestaurant(Long restaurantId) {
        return menuDAO.findByRestaurant_RestaurantId(restaurantId);
    }

    /**
     * Pobiera menu, których zakres ważności (validFrom/validTo) obejmuje podany przedział czasu.
     *
     * @param startDate początkowa data przedziału
     * @param endDate końcowa data przedziału
     * @return lista menu ważnych w określonym okresie
     */
    public List<Menu> getMenusValidBetween(OffsetDateTime startDate, OffsetDateTime endDate) {
        return menuDAO.findByValidFromBeforeAndValidToAfter(startDate, endDate);
    }

    /**
     * Pobiera menu utworzone po określonej dacie.
     *
     * @param createdAt data, po której menu zostały utworzone
     * @return lista menu utworzonych po podanej dacie
     */
    public List<Menu> getMenusCreatedAfter(OffsetDateTime createdAt) {
        return menuDAO.findByCreatedAtAfter(createdAt);
    }

    /**
     * Wyszukuje menu, których opis zawiera podany słowo kluczowe (bez uwzględniania wielkości liter).
     *
     * @param keyword słowo kluczowe
     * @return lista menu spełniających kryterium wyszukiwania
     */
    public List<Menu> searchMenusByDescription(String keyword) {
        return menuDAO.findByDescriptionContainingIgnoreCase(keyword);
    }

    /**
     * Tworzy nowe menu, ustawiając daty utworzenia i aktualizacji.
     *
     * @param menu obiekt menu do zapisania
     * @return zapisane menu
     */
    @Transactional
    public Menu createMenu(Menu menu) {
        // Ustawiamy daty utworzenia i modyfikacji
        Menu menuToSave = menu.toBuilder()
                .createdAt(OffsetDateTime.now())
                .updatedAt(OffsetDateTime.now())
                .build();
        return menuDAO.save(menuToSave);
    }

    /**
     * Aktualizuje istniejące menu, ustawiając nową datę modyfikacji.
     *
     * @param menu obiekt menu do aktualizacji
     * @return zaktualizowane menu
     */
    @Transactional
    public Menu updateMenu(Menu menu) {
        Menu updatedMenu = menu.toBuilder()
                .updatedAt(OffsetDateTime.now())
                .build();
        return menuDAO.save(updatedMenu);
    }
}

    /*
    5b. RestaurantService & MenuService.

    Cel:
    Te serwisy odpowiadają za pobieranie informacji o restauracjach i menu.

    - RestaurantService: wyszukuje restauracje (np. po nazwie lub identyfikatorze)
    oraz umożliwia pobranie listy restauracji danej kuchni.

    - MenuService: wyszukuje aktualne menu restauracji, a także umożliwia aktualizację pozycji menu
    (analogicznie do MenuItemUpdateService).
    */

    /*
    W powyższym przykładzie zakładamy, że interfejs MenuDAO został rozszerzony o metodę save(Menu menu),
    dzięki której można zapisywać (tworzyć/aktualizować) obiekty Menu. Jeśli interfejs DAO nie posiada takich metod,
    konieczne będzie ich dodanie lub wykorzystanie dedykowanego repozytorium (np. MenuRepository) zapewniającego operacje CRUD.

    Implementacja MenuService dzieli odpowiedzialności: wyszukiwanie menu na podstawie różnych kryteriów
    oraz (opcjonalnie) operacje modyfikacji, co pozwala zachować spójność logiki biznesowej w aplikacji Yummy.
    */