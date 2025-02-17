package pl.yummy.business.dao;

import pl.yummy.domain.requests.DeliveryAssignmentRequest;

public interface ProcessingDeliveryAssignmentDAO {

    /*
    Zarządzanie przypisywaniem dostawców
    Cel: Obsługa logiki przypisywania kurierów do zamówień.
    */

    void assignDelivery(DeliveryAssignmentRequest request);

    void assignCourierToDelivery(Long deliveryId);

}
