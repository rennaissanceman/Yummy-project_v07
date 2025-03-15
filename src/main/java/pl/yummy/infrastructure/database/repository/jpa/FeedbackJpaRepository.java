package pl.yummy.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.yummy.infrastructure.database.entity.FeedbackEntity;

import java.util.List;

@Repository
public interface FeedbackJpaRepository extends JpaRepository<FeedbackEntity, Long> {

    List<FeedbackEntity> findByRestaurantId(Long restaurantId);
    List<FeedbackEntity> findByCourier_CourierId(Long courierId);
    List<FeedbackEntity> findByOrders_OrdersId(Long ordersId);
    List<FeedbackEntity> findByRestaurant_RestaurantId(Long restaurantId);
}
