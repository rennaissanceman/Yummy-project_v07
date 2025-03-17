package pl.yummy.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@With
@Value
@Builder
public class OrderCancellationRequest {

    String orderNumber;
    String cancellationReason;
}


    /*
    Request/Command DTO – do anulowania zamówienia.

    OrderCancellationRequest.
    Komenda do anulowania zamówienia, która może zawierać numer zamówienia oraz powód anulowania.

    Przykładowe pola:

    - orderNumber
    - cancellationReason
    */
