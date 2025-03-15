package pl.yummy.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.yummy.business.dao.ProcessingOrderProcessingDAO;
import pl.yummy.domain.Courier;
import pl.yummy.domain.Orders;
import pl.yummy.domain.OrdersItem;
import pl.yummy.domain.RequestOrderProcessing;

@Slf4j
@Service
@AllArgsConstructor
public class OrderProcessingService {

    private final CourierService courierService;
    private final OrderService orderService;
    private final ProcessingOrderProcessingDAO processingOrderProcessingDAO;
    private final DeliveryAssignmentService deliveryAssignmentService;

    /**
     * Przetwarza zamówienie na podstawie żądania przetwarzania.
     * W zależności od wartości pola processingCode w żądaniu, wywoływana jest odpowiednia metoda:
     * <ul>
     *     <li>"ALTERNATIVE2" – wywoływana jest metoda process2</li>
     *     <li>"ALTERNATIVE3" – wywoływana jest metoda process3</li>
     *     <li>Inne lub brak kodu – domyślnie używana jest metoda process</li>
     * </ul>
     * Dodatkowo, jeśli w żądaniu nie przekazano danych o pozycji zamówienia (itemNotIncluded == true),
     * metoda wywołuje wersję metody bez argumentu OrdersItem.
     * Na końcu, jeśli żądanie wskazuje, że operacja została zakończona (done == true),
     * status zamówienia zostaje zaktualizowany.
     *
     * @param request dane przetwarzania zamówienia
     */
    @Transactional
    public void processOrder(RequestOrderProcessing request) {
        // Wyszukiwanie kuriera oraz aktywnego zamówienia
        Courier courier = courierService.findByCourierNumber(request.getCourierIdentifier());
        Orders order = orderService.findActiveOrder(request.getOrderNumber());

        // Wybór metody przetwarzania na podstawie kodu operacji
        if ("ALTERNATIVE2".equalsIgnoreCase(request.getProcessingCode())) {
            // Użycie metody process2, która wymaga zawsze przekazania obiektu OrdersItem
            OrdersItem orderItem = orderService.buildOrderItem(request);
            log.info("Przetwarzanie zamówienia metodą process2");
            processingOrderProcessingDAO.process2(order, courier, orderItem);
        } else if ("ALTERNATIVE3".equalsIgnoreCase(request.getProcessingCode())) {
            // Użycie metody process3 – zależnie od tego czy przekazano dane o pozycji
            if (request.itemNotIncluded()) {
                log.info("Przetwarzanie zamówienia metodą process3 (bez pozycji)");
                processingOrderProcessingDAO.process3(order, courier);
            } else {
                OrdersItem orderItem = orderService.buildOrderItem(request);
                log.info("Przetwarzanie zamówienia metodą process3 (z pozycją)");
                processingOrderProcessingDAO.process3(order, courier, orderItem);
            }
        } else {
            // Domyślne przetwarzanie – używamy metody process
            if (request.itemNotIncluded()) {
                log.info("Przetwarzanie zamówienia metodą process (bez pozycji)");
                processingOrderProcessingDAO.process(order, courier);
            } else {
                OrdersItem orderItem = orderService.buildOrderItem(request);
                log.info("Przetwarzanie zamówienia metodą process (z pozycją)");
                processingOrderProcessingDAO.process(order, courier, orderItem);
            }
        }

        // Jeśli operacja została oznaczona jako zakończona, aktualizujemy status zamówienia
        if (request.getDone()) {
            log.info("Zakończenie przetwarzania zamówienia, aktualizacja statusu");
            orderService.markOrderAsCompleted(order, request.getProcessingStartTime());
        }
    }
}


    /*
    2. OrderProcessingService.

    Cel:
    Realizuje przetwarzanie zamówienia – pobiera aktywne zamówienie, wyszukuje dostępnego kuriera,
    przypisuje kuriera do dostawy oraz aktualizuje status zamówienia, gdy operacja zostanie zakończona.
    */