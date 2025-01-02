package pl.yummy.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.OffsetDateTime;
import java.util.Objects;

@With
@Value
@Builder
public class YummyOrderProcessingRequest {

    // Dane zamówienia
    String orderId; // ID zamówienia
    String customerEmail; // Email klienta
    String restaurantName; // Nazwa restauracji
    String menuItemName; // Pozycja z menu
    Integer quantity; // Ilość zamawianych pozycji

    // Szczegóły przetwarzania
    String courierName; // Imię kuriera
    String courierStatus; // Status kuriera (np. "W drodze", "Dostarczono")
    OffsetDateTime expectedDeliveryTime; // Przewidywany czas dostawy
    String paymentStatus; // Status płatności (np. "Zapłacono", "Oczekuje")
    Boolean isCompleted; // Flaga wskazująca, czy zamówienie zostało zrealizowane

    // Metoda pomocnicza do sprawdzania brakujących danych
    public boolean isCourierNotAssigned() {
        return Objects.isNull(courierName) || courierName.isBlank();
    }
}
