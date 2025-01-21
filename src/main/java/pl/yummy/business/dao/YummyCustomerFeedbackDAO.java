package pl.yummy.business.dao;

import pl.yummy.domain.YummyCustomerFeedback;

import java.util.List;

public interface YummyCustomerFeedbackDAO {

    List<YummyCustomerFeedback> findByRestaurantName(String restaurantName);

    List<YummyCustomerFeedback> findPositiveFeedback();

    List<YummyCustomerFeedback> findFeedbackByCustomerName(String customerName);
}
