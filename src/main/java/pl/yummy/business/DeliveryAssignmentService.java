package pl.yummy.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.yummy.business.dao.ProcessingDeliveryAssignmentDAO;
import pl.yummy.domain.RequestDeliveryAssignment;

@Slf4j
@Service
@AllArgsConstructor
public class DeliveryAssignmentService {

    private final ProcessingDeliveryAssignmentDAO processingDeliveryAssignmentDAO;

    /**
     * Przypisuje dostawę do kuriera na podstawie danych zawartych w obiekcie żądania.
     * Metoda deleguje operację do odpowiedniej metody DAO.
     *
     * @param request obiekt żądania przypisania dostawy, zawierający m.in. numer zamówienia, identyfikator kuriera,
     *                datę przypisania oraz komentarz
     */
    @Transactional
    public void assignDelivery(RequestDeliveryAssignment request) {
        log.info("Przypisywanie dostawy dla zamówienia: {}", request.getOrderNumber());
        processingDeliveryAssignmentDAO.assignDelivery(request);
    }

    /**
     * Przypisuje dostępnego kuriera do dostawy na podstawie unikalnego identyfikatora dostawy.
     * Metoda deleguje operację do metody DAO.
     *
     * @param deliveryId unikalny identyfikator dostawy, dla której ma zostać przypisany kurier
     */
    @Transactional
    public void assignCourierToDelivery(Long deliveryId) {
        log.info("Przypisywanie kuriera do dostawy o ID: {}", deliveryId);
        processingDeliveryAssignmentDAO.assignCourierToDelivery(deliveryId);
    }
}

    /*
    7. DeliveryAssignmentService.

    Cel:
    Zarządza przypisywaniem dostępnych kurierów do dostaw. Na podstawie żądania (DeliveryAssignmentRequest)
    przypisuje kuriera do zamówienia.
    */