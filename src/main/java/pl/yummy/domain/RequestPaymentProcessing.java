package pl.yummy.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;
import pl.yummy.domain.enums.PaymentMethodStatusEnumDomain;

import java.math.BigDecimal;

@With
@Value
@Builder
public class RequestPaymentProcessing {

    Long orderId;
    String orderNumber;
    PaymentMethodStatusEnumDomain paymentMethod; // Można użyć również enumu
    BigDecimal amount;
    String transactionId; // Opcjonalne – np. jeśli przekazywane przez system płatności
    String comment;       // Dodatkowy komentarz, jeśli potrzebny
}


    /*
    Request/Command DTO – do inicjowania płatności.

    PaymentProcessingRequest.
    Komenda inicjująca proces płatności za zamówienie. Może zbierać informacje niezbędne do realizacji transakcji,
    np. numer zamówienia, metodę płatności, kwotę, identyfikator transakcji czy dodatkowe komentarze.

    Przykładowe pola:

    - orderNumber
    - paymentMethod (np. kod lub identyfikator metody płatności)
    - amount
    - transactionId (opcjonalnie, jeżeli generowany po stronie systemu lub od dostawcy płatności)
    - dodatkowe informacje, np. komentarz
    */
