package pl.yummy.business.dao;

import pl.yummy.domain.DeliveryAssignmentRequest;

public interface DeliveryAssignmentProcessingDAO {

    /*
    Zarządzanie przypisywaniem dostawców
    Cel: Obsługa logiki przypisywania kurierów do zamówień.
    */

    void assignDelivery(DeliveryAssignmentRequest request);

    void assignCourierToDelivery(Long deliveryId);



}

    /*
    Wstrzykiwany komponent: ProcessingDeliveryAssignmentDAO.
    */