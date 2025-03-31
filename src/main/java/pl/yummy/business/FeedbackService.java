package pl.yummy.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.yummy.business.dao.FeedbackDAO;
import pl.yummy.domain.Feedback;
import pl.yummy.domain.exception.NotFoundException;

import java.util.List;

@Service
@AllArgsConstructor
public class FeedbackService {

    private final FeedbackDAO feedbackDAO;

    /**
     * Pobiera listę opinii dla danej restauracji przy użyciu metody findByRestaurantId.
     *
     * @param restaurantId identyfikator restauracji
     * @return lista opinii
     */
    @Transactional(readOnly = true)
    public List<Feedback> getFeedbackForRestaurant(Long restaurantId) {
        return feedbackDAO.findByRestaurantId(restaurantId);
    }

    /**
     * Pobiera listę opinii dla danej restauracji przy użyciu metody findByRestaurant_RestaurantId.
     *
     * @param restaurantId identyfikator restauracji
     * @return lista opinii
     */
    @Transactional(readOnly = true)
    public List<Feedback> getFeedbackForRestaurantAlternative(Long restaurantId) {
        return feedbackDAO.findByRestaurant_RestaurantId(restaurantId);
    }

    /**
     * Pobiera listę opinii dla danego kuriera.
     *
     * @param courierId identyfikator kuriera
     * @return lista opinii
     */
    @Transactional(readOnly = true)
    public List<Feedback> getFeedbackForCourier(Long courierId) {
        return feedbackDAO.findByCourier_CourierId(courierId);
    }

    /**
     * Pobiera listę opinii dla danego zamówienia.
     *
     * @param ordersId identyfikator zamówienia
     * @return lista opinii
     */
    @Transactional(readOnly = true)
    public List<Feedback> getFeedbackForOrder(Long ordersId) {
        return feedbackDAO.findByOrders_OrdersId(ordersId);
    }

    /**
     * Zapisuje opinię w bazie.
     *
     * @param feedback encja opinii do zapisania
     * @return zapisana opinia
     */
    @Transactional
    public Feedback saveFeedback(Feedback feedback) {
        return feedbackDAO.save(feedback);
    }

    @Transactional(readOnly = true)
    public Feedback getFeedbackById(Long feedbackId) {
        return feedbackDAO.findById(feedbackId)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono opinii o ID: " + feedbackId));
    }

}
