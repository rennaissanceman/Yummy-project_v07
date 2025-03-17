package pl.yummy.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.yummy.business.dao.ReviewDAO;
import pl.yummy.domain.Review;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReviewService {

    private final ReviewDAO reviewDAO;

    public Review createReview(Review review) {
        return reviewDAO.createReview(review);
    }

    public Review updateReview(Review review) {
        return reviewDAO.updateReview(review);
    }

    public void deleteReview(Long reviewId) {
        reviewDAO.deleteReview(reviewId);
    }

    public Optional<Review> findById(Long reviewId) {
        return reviewDAO.findById(reviewId);
    }

    public List<Review> findByRestaurantId(Long restaurantId) {
        return reviewDAO.findByRestaurantId(restaurantId);
    }

    public List<Review> findByCourierId(Long courierId) {
        return reviewDAO.findByCourierId(courierId);
    }

    public List<Review> findByCustomerId(Long customerId) {
        return reviewDAO.findByCustomerId(customerId);
    }

    public double calculateAverageRatingForRestaurant(Long restaurantId) {
        return reviewDAO.calculateAverageRatingForRestaurant(restaurantId);
    }

    public double calculateAverageRatingForCourier(Long courierId) {
        return reviewDAO.calculateAverageRatingForCourier(courierId);
    }
}

    /*
    ReviewService

    - Zarządza opiniami – tworzenie, aktualizacja, usuwanie opinii, wyszukiwanie opinii dla restauracji,
    kuriera czy klienta, a także obliczanie średniej oceny.
    - Wstrzykiwany komponent: ReviewDAO.
    */