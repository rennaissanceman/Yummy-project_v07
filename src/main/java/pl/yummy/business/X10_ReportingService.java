package pl.yummy.business;

public class X10_ReportingService {
}


    /*
    10. Serwis raportowania.

    Odpowiada za:

    - Generowanie raportów sprzedaży.
    - Pobieranie analiz statystycznych restauracji.
    - Śledzenie wydajności dostaw.


    Metody:

    generateRevenueReport(OffsetDateTime start, OffsetDateTime end) → RevenueReportView
    getRestaurantStatistics(Long restaurantId) → RestaurantStatisticsView
    getDeliveryPerformance(Long deliveryAreaId) → DeliveryPerformanceView
    */