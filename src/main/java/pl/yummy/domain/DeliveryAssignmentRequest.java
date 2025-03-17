package pl.yummy.domain;

import lombok.*;

import java.time.OffsetDateTime;

@With
@Value
@Builder
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class DeliveryAssignmentRequest {

    String orderNumber;
    String courierIdentifier;  // np. numer lub identyfikator kuriera
    OffsetDateTime assignmentDateTime;
    String comment;            // Opcjonalnie – notatka dla kuriera


}


    /*
    Request/Command DTO – do przypisywania kuriera do zamówienia.

    DeliveryAssignmentRequest.
    Służy do przypisania zamówienia do konkretnego kuriera. W takim DTO można przekazać identyfikator kuriera,
    numer zamówienia, a także ewentualnie datę/godzinę przypisania.

    Przykładowe pola:

    - orderNumber
    - courierIdentifier (np. numer lub ID kuriera)
    - assignmentDateTime (czas przypisania)
    - dodatkowe opcjonalne informacje (np. notatka dla kuriera)
    */
