package pl.yummy.business.dao;

import pl.yummy.domain.Feedback;

import java.util.List;
import java.util.Optional;

public interface FeedbackDAO {

    // Pobiera wszystkie opinie dla danej restauracji
    List<Feedback> findByRestaurantId(Long restaurantId);

    // Pobiera wszystkie opinie dla danego kuriera
    List<Feedback> findByCourier_CourierId(Long courierId);

    // Pobiera wszystkie opinie dla danego zamówienia
    List<Feedback> findByOrders_OrdersId(Long ordersId);

    List<Feedback> findByRestaurant_RestaurantId(Long restaurantId);

    Feedback save(Feedback feedback);

    Optional<Feedback> findById(Long feedbackId);
}
