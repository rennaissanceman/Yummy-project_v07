package pl.yummy.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.yummy.business.dao.RestaurantDAO;
import pl.yummy.domain.Restaurant;
import pl.yummy.domain.enums.CuisineTypeEnumDomain;
import pl.yummy.domain.exception.NotFoundException;

import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantService {

    private final RestaurantDAO restaurantDAO;

    /**
     * Znajduje restaurację na podstawie unikalnego identyfikatora,
     * który w tym przypadku jest interpretowany jako nazwa restauracji.
     *
     * @param restaurantIdentifier unikalny identyfikator (nazwa) restauracji
     * @return Restaurant odpowiadająca podanej nazwie
     * @throws NotFoundException gdy restauracja nie zostanie znaleziona
     */
    public Restaurant findRestaurant(String restaurantIdentifier) {
        return restaurantDAO.findByRestaurantName(restaurantIdentifier)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono restauracji: " + restaurantIdentifier));
    }

    /**
     * Pobiera listę restauracji o określonym typie kuchni.
     *
     * @param cuisineType typ kuchni (np. ITALIAN, CHINESE)
     * @return lista restauracji, które oferują daną kuchnię
     */
    public List<Restaurant> getRestaurantsByCuisine(CuisineTypeEnumDomain cuisineType) {
        return restaurantDAO.findByCuisineType(cuisineType);
    }

    /**
     * Pobiera listę restauracji, których średnia ocena jest większa lub równa podanemu minimalnemu ratingowi.
     *
     * @param minimumRating minimalna średnia ocena restauracji
     * @return lista restauracji spełniających warunek minimalnej oceny
     */
    public List<Restaurant> getRestaurantsWithMinimumRating(Double minimumRating) {
        return restaurantDAO.findByAverageRatingGreaterThanEqual(minimumRating);
    }

    /**
     * Pobiera listę restauracji należących do właściciela o podanym identyfikatorze.
     *
     * @param ownerId unikalny identyfikator właściciela restauracji
     * @return lista restauracji, których właścicielem jest wskazany właściciel
     */
    public List<Restaurant> getRestaurantsByOwner(Long ownerId) {
        return restaurantDAO.findByOwner_OwnerId(ownerId);
    }
}

    /*
    5. RestaurantService & MenuService.

    Cel:
    Te serwisy odpowiadają za pobieranie informacji o restauracjach i menu.

    - RestaurantService: wyszukuje restauracje (np. po nazwie lub identyfikatorze)
    oraz umożliwia pobranie listy restauracji danej kuchni.
    - MenuService: wyszukuje aktualne menu restauracji,
    a także umożliwia aktualizację pozycji menu (analogicznie do MenuItemUpdateService).
    */