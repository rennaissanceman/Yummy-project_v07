package pl.yummy.infrastructure.database.entity.enums;

public enum DeliveryStatus {

    PENDING,
    ASSIGNED,
    PICKED_UP,
    IN_TRANSIT,
    DELIVERED,
    FAILED_DELIVERY,
    RETURNED,
    CANCELLED


    /*
    PENDING("Delivery is awaiting assignment to a courier"),               // Dostawa oczekuje na przypisanie kuriera.
    ASSIGNED("Courier has been assigned to the delivery"),                 // Kurier został przypisany do dostawy.
    PICKED_UP("Courier has picked up the order from the restaurant"),      // Kurier odebrał zamówienie z restauracji.
    IN_TRANSIT("Order is on its way to the customer"),                     // Zamówienie jest w drodze do klienta.
    DELIVERED("Order has been successfully delivered to the customer"),    // Zamówienie zostało pomyślnie dostarczone.
    FAILED_DELIVERY("Delivery failed (e.g., customer was not available)"), // Dostawa nie powiodła się.
    RETURNED("Order was returned (e.g., customer refused delivery)"),      // Zamówienie zostało zwrócone.
    CANCELLED("Delivery has been cancelled");                              // Dostawa została anulowana.


    private final String description;

    DeliveryStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

     */
/*
    PENDING,                    // Zamówienie złożone, ale jeszcze nieprzyjęte przez restaurację.
    CONFIRMED,                  // Restauracja zaakceptowała zamówienie i rozpoczęła przygotowanie.
    PREPARING,                  // Zamówienie jest w trakcie przygotowania.
    READY_FOR_PICKUP,           // Zamówienie gotowe do odbioru przez dostawcę.
    OUT_FOR_DELIVERY,           // Zamówienie jest w drodze do klienta.
    DELIVERED,                  // Zamówienie zostało dostarczone do klienta.
    CANCELLED_BY_CUSTOMER,      // Zamówienie anulowane przez klienta.
    CANCELLED_BY_RESTAURANT,    // Zamówienie anulowane przez restaurację (np. brak dostępności składników).
    FAILED_DELIVERY,            // Nieudana próba dostawy (np. klient nieobecny).
    RETURNED,                   // Zamówienie zostało zwrócone (np. klient odmówił przyjęcia).
    OPTIONAL_AWAITING_PAYMENT,  // Oczekiwanie na płatność (np. w przypadku płatności online).
    OPTIONAL_PAYMENT_FAILED,    // Płatność nie powiodła się.
    OPTIONAL_REFUNDED,          // Pieniądze zostały zwrócone klientowi.
    OPTIONAL_REJECTED,          // Zamówienie odrzucone przez system lub restaurację (np. z powodu braku dostawców).
    OPTIONAL_CUSTOMER_NOT_FOUND // Nieudana dostawa z powodu błędnego adresu lub nieobecności klienta.
*/
}
