package pl.yummy.business.dao;

public interface DeliveryAssignmentDAO {

    /*
    Zarządzanie przypisywaniem dostawców
    Cel: Obsługa logiki przypisywania kurierów do zamówień.
    */

    void assignCourierToDelivery(Long deliveryId);

}
