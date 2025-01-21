package pl.yummy.business.dao;

import pl.yummy.domain.YummyCustomerActivityHistory;

import java.math.BigDecimal;
import java.util.List;

public interface YummyCustomerActivityHistoryDAO {

    List<YummyCustomerActivityHistory> findByCustomerName(String name);

    List<YummyCustomerActivityHistory> findTopCustomersBySpending(BigDecimal minimumSpending);

    List<YummyCustomerActivityHistory> findRecentActivitiesByCustomerId(Integer customerId);
}
