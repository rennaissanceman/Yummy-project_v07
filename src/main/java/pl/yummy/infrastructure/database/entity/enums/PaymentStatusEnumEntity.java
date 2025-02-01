package pl.yummy.infrastructure.database.entity.enums;

public enum PaymentStatusEnumEntity {
    PENDING,
    COMPLETED,
    FAILED,
    CANCELLED,
    REFUNDED,
    CHARGEBACK,
    IN_PROGRESS,
    EXPIRED

    /*
    PENDING("Payment is awaiting confirmation"),                 // Oczekuje na zatwierdzenie (np. płatność online w toku).
    COMPLETED("Payment has been successfully completed"),        // Płatność zakończona pomyślnie.
    FAILED("Payment failed due to an error"),                    // Płatność nie powiodła się (np. brak środków).
    CANCELLED("Payment has been cancelled by the user"),         // Płatność anulowana przez użytkownika.
    REFUNDED("Payment has been refunded to the customer"),       // Pieniądze zostały zwrócone klientowi.
    CHARGEBACK("Payment has been reversed by the bank"),         // Obciążenie zwrotne (chargeback) w przypadku sporu.
    IN_PROGRESS("Payment is being processed"),                   // Płatność jest w trakcie przetwarzania.
    EXPIRED("Payment session has expired");                      // Sesja płatności wygasła (np. przekroczono czas na dokonanie płatności).

    private final String description;

    PaymentStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

     */
}
