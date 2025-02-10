package pl.yummy.business.dao;

import pl.yummy.domain.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewDAO {

    // Tworzenie nowej opinii
    Review createReview(Review review);

    // Aktualizacja istniejącej opinii
    Review updateReview(Review review);

    // Usuwanie opinii po identyfikatorze
    void deleteReview(Long reviewId);

    // Pobranie opinii po identyfikatorze
    Optional<Review> findById(Long reviewId);

    // Pobranie wszystkich opinii dla danej restauracji
    List<Review> findByRestaurantId(Long restaurantId);

    // Pobranie wszystkich opinii dla danego kuriera
    List<Review> findByCourierId(Long courierId);

    // Pobranie wszystkich opinii od danego klienta
    List<Review> findByCustomerId(Long customerId);

    // Obliczenie średniej oceny dla restauracji
    double calculateAverageRatingForRestaurant(Long restaurantId);

    // Obliczenie średniej oceny dla kuriera
    double calculateAverageRatingForCourier(Long courierId);
}
