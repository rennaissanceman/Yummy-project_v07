package pl.yummy.business;

public class X3_DeliveryService {
}


    /*
    3. Serwis obsługi dostaw.

    Odpowiada za:

    - Przypisanie zamówienia do kuriera.
    - Aktualizację statusu dostawy.
    - Sprawdzanie statusu dostaw.


    Metody:

    assignCourierToDelivery(Long deliveryId) → void
    updateDeliveryStatus(Long deliveryId, DeliveryStatusEnumDomain newStatus) → void
    getDeliveriesByCourier(Long courierId) → List<Delivery>
    */