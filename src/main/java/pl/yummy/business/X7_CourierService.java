package pl.yummy.business;

public class X7_CourierService {
}


    /*
    7. Serwis obsługi kurierów.

    Odpowiada za:

    - Zarządzanie kurierami.
    - Przydzielanie dostaw do kurierów.
    - Aktualizację statusu kuriera.


    Metody:

    assignCourierToOrder(Long orderId, Long courierId) → void
    updateCourierStatus(Long courierId, CourierStatusEnumDomain newStatus) → void
    getAvailableCouriers() → List<Courier>
    */