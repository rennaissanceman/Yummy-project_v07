package pl.yummy.infrastructure.database.entity.enums;

public enum OrdersStatusEnumEntity {
    PENDING,
    CONFIRMED,
    PREPARING,
    READY_FOR_PICKUP,
    OUT_FOR_DELIVERY,
    DELIVERED,
    PAID,
    CANCELLED_BY_CUSTOMER,
    CANCELLED_BY_RESTAURANT,
    FAILED_DELIVERY,
    RETURNED,
    REFUNDED


    /*
    PENDING("Order is pending approval"),   // Zamówienie zostało złożone, oczekuje na potwierdzenie.
    CONFIRMED("Order has been confirmed"),  // Zamówienie zostało potwierdzone przez restaurację.
    PREPARING("Order is being prepared"),   // Restauracja przygotowuje zamówienie.
    READY_FOR_PICKUP("Order is ready for pickup"),  // Zamówienie jest gotowe do odbioru przez dostawcę.
    OUT_FOR_DELIVERY("Order is out for delivery"),  // Zamówienie jest w drodze do klienta.
    DELIVERED("Order has been delivered"),  // Zamówienie zostało dostarczone do klienta.
    CANCELLED_BY_CUSTOMER("Order was cancelled by the customer"),   // Zamówienie zostało anulowane przez klienta.
    CANCELLED_BY_RESTAURANT("Order was cancelled by the restaurant"),   // Zamówienie zostało anulowane przez restaurację.
    FAILED_DELIVERY("Delivery failed"), // Dostawa nie powiodła się (np. klient nieobecny).
    RETURNED("Order was returned"), // Zamówienie zostało zwrócone.
    REFUNDED("Order was refunded");     // Pieniądze zostały zwrócone klientowi.

    private final String description;

    OrdersStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

     */
}
