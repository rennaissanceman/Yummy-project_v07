package pl.yummy.business.dao;

import pl.yummy.domain.RequestDeliveryAssignment;

public interface ProcessingDeliveryAssignmentDAO {

    /*
    Zarządzanie przypisywaniem dostawców
    Cel: Obsługa logiki przypisywania kurierów do zamówień.
    */

    void assignDelivery(RequestDeliveryAssignment request);

    void assignCourierToDelivery(Long deliveryId);

}
