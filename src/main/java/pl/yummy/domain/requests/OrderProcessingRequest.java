package pl.yummy.domain.requests;

import lombok.Builder;
import lombok.Value;
import lombok.With;
import pl.yummy.domain.enums.DeliveryStatusEnumDomain;
import pl.yummy.domain.enums.PaymentStatusEnumDomain;

import java.time.OffsetDateTime;
import java.util.Objects;

@With
@Value
@Builder
public class OrderProcessingRequest {

//    zawiera informacje o realizacji zamówienia (np. identyfikator kuriera, numer zamówienia, informacje
//    o konkretnej pozycji zamówienia, kod operacji przetwarzania, czas obsługi, komentarz oraz flagę ukończenia)
//    zbiera dane potrzebne do przetwarzania zamówienia (analogicznie do CarServiceProcessingRequest),
//    zawierając informacje o kurierze, numerze zamówienia, szczegółach pozycji zamówienia, kodzie operacji,
//    czasie przetwarzania, komentarzu oraz fladze zakończenia.


    String courierIdentifier;   // identyfikator kuriera, analogicznie do mechanicPesel
    String orderNumber;         // numer zamówienia – analogicznie do carVin
    String orderItemIdentifier; // identyfikator pozycji zamówienia, np. jako odpowiednik numeru seryjnego części
    Integer orderItemQuantity;  // ilość danej pozycji – analogicznie do partQuantity
    String processingCode;      // kod operacji (np. rodzaj przetwarzania zamówienia), analogicznie do serviceCode
    Integer processingTime;     // czas realizacji (np. czas przygotowania lub dostawy), analogicznie do hours
    String comment;             // komentarz związany z przetwarzaniem
    Boolean done;               // flaga oznaczająca, czy operacja została zakończona

    // Uproszczony DTO do inicjowania procesu przetwarzania zamówienia – bez metod obliczeniowych
    DeliveryStatusEnumDomain desiredDeliveryStatus;
    PaymentStatusEnumDomain desiredPaymentStatus;
    OffsetDateTime processingStartTime;
    String processingNotes;

    public boolean itemNotIncluded() {
        return Objects.isNull(getOrderItemIdentifier())
                || Objects.isNull(getOrderItemQuantity())
                || "NONE".equals(getOrderItemIdentifier());
    }
}
