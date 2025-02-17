package pl.yummy.business.dao;

import pl.yummy.domain.enums.OrdersStatusEnumDomain;

public interface ProcessingOrderLifecycleProcessingDAO {

/*
Obsługa pełnego cyklu życia zamówienia
Cel: Obsługa procesów związanych z cyklem życia zamówienia, takich jak:
- Aktualizacja statusu zamówienia (np. "Przygotowywane", "Gotowe do odbioru").
- Obsługa zmian statusów w zależności od dostawy.
- Możliwość anulowania zamówienia.

*/

    void updateOrderStatus(Long orderId, OrdersStatusEnumDomain newStatus);

    void cancelOrder(Long orderId);
}
