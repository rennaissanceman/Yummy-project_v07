package pl.yummy.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.yummy.business.RestaurantService;
import pl.yummy.domain.Restaurant;
import pl.yummy.domain.enums.CuisineTypeEnumDomain;

import java.util.Map;

@Controller
@AllArgsConstructor
public class RestaurantController {

    private static final String RESTAURANT_DETAILS = "/restaurant/details";
    private static final String RESTAURANT_BY_CUISINE = "/restaurant/cuisine";
    private static final String RESTAURANT_BY_RATING = "/restaurant/rating";
    private static final String RESTAURANT_BY_OWNER = "/restaurant/owner";

    private final RestaurantService restaurantService;

    /**
     * GET – Wyświetla szczegóły restauracji na podstawie identyfikatora (tutaj nazwa restauracji).
     *
     * @param restaurantIdentifier unikalny identyfikator (nazwa) restauracji
     * @return ModelAndView z widokiem "restaurant_details" oraz modelem zawierającym obiekt Restaurant
     */
    @GetMapping(RESTAURANT_DETAILS)
    public ModelAndView restaurantDetails(@RequestParam("restaurantIdentifier") String restaurantIdentifier) {
        Restaurant restaurant = restaurantService.findRestaurant(restaurantIdentifier);
        Map<String, Object> model = Map.of("restaurant", restaurant);
        return new ModelAndView("restaurant_details", model);
    }

    /**
     * GET – Wyświetla listę restauracji dla danego typu kuchni.
     *
     * @param cuisineType typ kuchni jako String (np. "ITALIAN")
     * @return ModelAndView z widokiem "restaurant_list" oraz modelem zawierającym listę restauracji
     */
    @GetMapping(RESTAURANT_BY_CUISINE)
    public ModelAndView restaurantsByCuisine(@RequestParam("cuisineType") String cuisineType) {
        CuisineTypeEnumDomain cuisineEnum = CuisineTypeEnumDomain.valueOf(cuisineType.toUpperCase());
        var restaurants = restaurantService.getRestaurantsByCuisine(cuisineEnum);
        Map<String, Object> model = Map.of("restaurants", restaurants);
        return new ModelAndView("restaurant_list", model);
    }

    /**
     * GET – Wyświetla listę restauracji, których średnia ocena jest większa lub równa podanemu progowi.
     *
     * @param minimumRating minimalna średnia ocena
     * @return ModelAndView z widokiem "restaurant_list" oraz modelem zawierającym listę restauracji
     */
    @GetMapping(RESTAURANT_BY_RATING)
    public ModelAndView restaurantsByRating(@RequestParam("minimumRating") Double minimumRating) {
        var restaurants = restaurantService.getRestaurantsWithMinimumRating(minimumRating);
        Map<String, Object> model = Map.of("restaurants", restaurants);
        return new ModelAndView("restaurant_list", model);
    }

    /**
     * GET – Wyświetla listę restauracji należących do określonego właściciela.
     *
     * @param ownerId unikalny identyfikator właściciela restauracji
     * @return ModelAndView z widokiem "restaurant_list" oraz modelem zawierającym listę restauracji
     */
    @GetMapping(RESTAURANT_BY_OWNER)
    public ModelAndView restaurantsByOwner(@RequestParam("ownerId") Long ownerId) {
        var restaurants = restaurantService.getRestaurantsByOwner(ownerId);
        Map<String, Object> model = Map.of("restaurants", restaurants);
        return new ModelAndView("restaurant_list", model);
    }
}

    /*
    RestaurantController – prezentuje informacje o restauracjach, ich menu oraz szczegóły dotyczące poszczególnych dań.
    Umożliwia np. przeglądanie listy dostępnych lokali oraz wyświetlanie szczegółowych opisów.



    4. RestaurantController

    Odpowiedzialności:
    - Zarządzanie informacjami o restauracjach.
    - Pobieranie menu i aktualizacja oferty.
    - Zarządzanie recenzjami i ocenami.


    Przykładowe endpointy:
    - GET /api/restaurants – lista dostępnych restauracji.
    - GET /api/restaurants/{id} – szczegóły restauracji.
    - POST /api/restaurants – rejestracja nowej restauracji (dla właścicieli).
    - PUT /api/restaurants/{id} – aktualizacja informacji o restauracji.
    - GET /api/restaurants/{id}/menu – pobranie menu danej restauracji.
    - PUT /api/restaurants/{id}/menu – aktualizacja menu.
    */