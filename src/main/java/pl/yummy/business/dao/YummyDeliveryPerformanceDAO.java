package pl.yummy.business.dao;

import pl.yummy.domain.YummyDeliveryPerformance;

import java.util.List;

public interface YummyDeliveryPerformanceDAO {

    List<YummyDeliveryPerformance> findByDeliveryAreaName(String areaName);

    List<YummyDeliveryPerformance> findByAverageDeliveryTimeLessThan(Double maxAverageTime);

    List<YummyDeliveryPerformance> findAreasWithHighLateDeliveryRate(Double minimumLateRate);
}
