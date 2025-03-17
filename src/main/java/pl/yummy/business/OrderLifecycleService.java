package pl.yummy.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.yummy.business.dao.OrderLifecycleProcessingDAO;
import pl.yummy.domain.enums.OrdersStatusEnumDomain;

@Slf4j
@Service
@AllArgsConstructor
public class OrderLifecycleService {

    private final OrderLifecycleProcessingDAO orderLifecycleProcessingDAO;

    /**
     * Aktualizuje status zamówienia na podstawie przekazanego identyfikatora oraz nowego statusu.
     *
     * @param orderId  identyfikator zamówienia, którego status ma zostać zaktualizowany
     * @param newStatus nowy status zamówienia, np. PREPARING, READY_FOR_PICKUP, DELIVERED
     */
    @Transactional
    public void updateOrderStatus(Long orderId, OrdersStatusEnumDomain newStatus) {
        log.info("Aktualizacja statusu zamówienia o ID {} na status: {}", orderId, newStatus);
        orderLifecycleProcessingDAO.updateOrderStatus(orderId, newStatus);
    }

    /**
     * Anuluje zamówienie o podanym identyfikatorze.
     *
     * @param orderId identyfikator zamówienia, które ma zostać anulowane
     */
    @Transactional
    public void cancelOrder(Long orderId) {
        log.info("Anulowanie zamówienia o ID {}", orderId);
        orderLifecycleProcessingDAO.cancelOrder(orderId);
    }
}

    /*
    8. OrderLifecycleService.
    Cel:
    Obsługuje pełny cykl życia zamówienia – aktualizację statusu, anulowanie zamówień
    oraz synchronizację statusów dostawy.

    __________________________________________________________
    OrderLifecycleService

    - Zarządza cyklem życia zamówienia, czyli aktualizacją statusu zamówienia i jego ewentualnym anulowaniem.
    - Wstrzykiwany komponent: ProcessingOrderLifecycleProcessingDAO.
    */