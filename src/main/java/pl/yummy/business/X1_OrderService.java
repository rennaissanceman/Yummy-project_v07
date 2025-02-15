package pl.yummy.business;

public class X1_OrderService {


}


    /*
    1.  Serwis zamówień.

    Odpowiada za:

    - Tworzenie nowych zamówień.
    - Walidację danych zamówienia.
    - Obliczanie całkowitej kwoty zamówienia.
    - Przypisanie zamówienia do klienta i restauracji.
    - Aktualizację statusu zamówienia.
    - Obsługę anulowania zamówienia.


    Metody:

    placeOrder(OrderPlacementRequest request) → OrderResponse
    calculateTotalAmount(List<OrderItemRequest> items) → BigDecimal
    cancelOrder(OrderCancellationRequest request) → void
    updateOrderStatus(Long orderId, OrdersStatusEnumDomain newStatus) → void
    findOrdersByCustomer(Long customerId) → List<Orders>
    */