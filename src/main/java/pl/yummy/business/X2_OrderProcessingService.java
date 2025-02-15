package pl.yummy.business;

public class X2_OrderProcessingService {
}

    /*
    2. Serwis przetwarzania zamówień.


    Odpowiada za:

    - Zmianę statusu zamówienia w zależności od procesu.
    - Obsługę cyklu życia zamówienia (np. PENDING → PREPARING → DELIVERED).
    - Obsługę powiązanych dostaw.


    Metody:

    processOrder(Orders orders, Courier courier) → void
    processOrderItem(Orders orders, OrdersItem ordersItem) → void
    finalizeOrder(Long orderId) → void
    assignCourierToOrder(Long orderId, Long courierId) → void
    */
