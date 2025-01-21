package pl.yummy.business.dao;

import pl.yummy.domain.YummyMenuAnalysis;

import java.math.BigDecimal;
import java.util.List;

public interface YummyMenuAnalysisDAO {

    List<YummyMenuAnalysis> findTopSellingItems();

    List<YummyMenuAnalysis> findItemsByRevenueGreaterThan(BigDecimal revenueThreshold);

    List<YummyMenuAnalysis> findPopularItems();
}
