package pl.yummy.business.dao;

import pl.yummy.domain.YummyOrderHistory;

import java.time.OffsetDateTime;
import java.util.List;

public interface YummyOrderHistoryDAO {

    List<YummyOrderHistory> findByCustomerName(String customerName);

    List<YummyOrderHistory> findByOrderStatus(String orderStatus);

    List<YummyOrderHistory> findOrdersBetweenDates(OffsetDateTime startDate, OffsetDateTime endDate);
}
