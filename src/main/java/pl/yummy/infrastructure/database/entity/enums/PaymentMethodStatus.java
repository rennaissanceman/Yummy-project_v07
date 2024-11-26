package pl.yummy.infrastructure.database.entity.enums;

public enum PaymentMethodStatus {

    ACTIVE("The payment method is active and available for use"),          // Metoda płatności jest aktywna i można jej używać.
    INACTIVE("The payment method is inactive and cannot be used"),         // Metoda płatności jest nieaktywna i nie można jej używać.
    PENDING_APPROVAL("The payment method is pending approval"),            // Metoda płatności oczekuje na zatwierdzenie (np. przez administratora).
    SUSPENDED("The payment method has been suspended"),                    // Metoda płatności została zawieszona (np. z powodu problemów technicznych).
    DEPRECATED("The payment method is deprecated and will be removed"),    // Metoda płatności została oznaczona jako przestarzała i zostanie usunięta.
    UNDER_MAINTENANCE("The payment method is under maintenance"),          // Metoda płatności jest chwilowo niedostępna z powodu prac serwisowych.
    REMOVED("The payment method has been permanently removed");            // Metoda płatności została usunięta z systemu.

    private final String description;

    PaymentMethodStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
