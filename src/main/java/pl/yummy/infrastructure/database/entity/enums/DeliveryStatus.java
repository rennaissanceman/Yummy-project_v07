package pl.yummy.infrastructure.database.entity.enums;

public enum DeliveryStatus {
    PENDING,
    CONFIRMED,
    PREPARING,
    READY_FOR_PICKUP,
    OUT_FOR_DELIVERY,
    DELIVERED,
    CANCELLED_BY_CUSTOMER,
    CANCELLED_BY_RESTAURANT,
    FAILED_DELIVERY,
    RETURNED


    /*
    Przykładowe statusy dostawy:
    PENDING – Zamówienie złożone, ale jeszcze nieprzyjęte przez restaurację.
    CONFIRMED – Restauracja zaakceptowała zamówienie i rozpoczęła przygotowanie.
    PREPARING – Zamówienie jest w trakcie przygotowania.
    READY_FOR_PICKUP – Zamówienie gotowe do odbioru przez dostawcę.
    OUT_FOR_DELIVERY – Zamówienie jest w drodze do klienta.
    DELIVERED – Zamówienie zostało dostarczone do klienta.
    CANCELLED_BY_CUSTOMER – Zamówienie anulowane przez klienta.
    CANCELLED_BY_RESTAURANT – Zamówienie anulowane przez restaurację (np. brak dostępności składników).
    FAILED_DELIVERY – Nieudana próba dostawy (np. klient nieobecny).
    RETURNED – Zamówienie zostało zwrócone (np. klient odmówił przyjęcia).

    Dodatkowe statusy (opcjonalne):
    AWAITING_PAYMENT – Oczekiwanie na płatność (np. w przypadku płatności online).
    PAYMENT_FAILED – Płatność nie powiodła się.
    REFUNDED – Pieniądze zostały zwrócone klientowi.
    REJECTED – Zamówienie odrzucone przez system lub restaurację (np. z powodu braku dostawców).
    CUSTOMER_NOT_FOUND – Nieudana dostawa z powodu błędnego adresu lub nieobecności klienta.
     */
}
