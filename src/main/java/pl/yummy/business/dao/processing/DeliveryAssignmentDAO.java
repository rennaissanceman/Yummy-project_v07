package pl.yummy.business.dao.processing;

import pl.yummy.domain.requests.DeliveryAssignmentRequest;

public interface DeliveryAssignmentDAO {

    /*
    Zarządzanie przypisywaniem dostawców
    Cel: Obsługa logiki przypisywania kurierów do zamówień.
    */

    void assignDelivery(DeliveryAssignmentRequest request);

    void assignCourierToDelivery(Long deliveryId);

}
