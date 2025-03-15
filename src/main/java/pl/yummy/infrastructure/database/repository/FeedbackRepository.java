package pl.yummy.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.yummy.business.dao.FeedbackDAO;
import pl.yummy.domain.Feedback;
import pl.yummy.infrastructure.database.repository.jpa.FeedbackJpaRepository;
import pl.yummy.infrastructure.database.repository.mapper.FeedbackEntityMapper;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class FeedbackRepository implements FeedbackDAO {
    private final FeedbackJpaRepository feedbackJpaRepository;
    private final FeedbackEntityMapper feedbackEntityMapper;

    @Override
    public List<Feedback> findByRestaurantId(Long restaurantId) {
        return feedbackJpaRepository.findByRestaurantId(restaurantId)
                .stream()
                .map(feedbackEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Feedback> findByCourier_CourierId(Long courierId) {
        return feedbackJpaRepository.findByCourier_CourierId(courierId)
                .stream()
                .map(feedbackEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Feedback> findByOrders_OrdersId(Long ordersId) {
        return feedbackJpaRepository.findByOrders_OrdersId(ordersId)
                .stream()
                .map(feedbackEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Feedback> findByRestaurant_RestaurantId(Long restaurantId) {
        return feedbackJpaRepository.findByRestaurant_RestaurantId(restaurantId)
                .stream()
                .map(feedbackEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Feedback save(Feedback feedback) {
        var entity = feedbackEntityMapper.mapToEntity(feedback);
        var savedEntity = feedbackJpaRepository.save(entity);
        return feedbackEntityMapper.mapFromEntity(savedEntity);
    }
}
