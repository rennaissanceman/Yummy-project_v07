package pl.yummy.business.dao;

public interface YDeliveryAssignmentDAO {

    /*
    Zarządzanie przypisywaniem dostawców
    Cel: Obsługa logiki przypisywania kurierów do zamówień.
    */

    void assignCourierToDelivery(Long deliveryId);

}
