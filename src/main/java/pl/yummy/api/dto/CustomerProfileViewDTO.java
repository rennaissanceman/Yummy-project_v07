package pl.yummy.api.dto;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.OffsetDateTime;
import java.util.List;

@With
@Value
@Builder
public class CustomerProfileViewDTO {

    /*
     Model widoku profilu klienta, zawierający dane osobowe, domyślny adres oraz skrócony podgląd zamówień.
     Zawiera dane profilu klienta, takie jak dane osobowe, kontaktowe, domyślny adres oraz skrótowe podsumowanie zamówień.
     Dzięki temu widokowi można zbudować kompleksowy obraz klienta, który może być wyświetlony na stronie
     profilu klienta lub w panelu administracyjnym.
     */
    Integer customerId;
    String customerNumber;
    String customerName;
    String customerSurname;
    String email;
    String defaultAddress;
    List<OrderSummaryView> orderSummaries;
    String companyName;
    String vatNumber;

    @With
    @Value
    @Builder
    public static class OrderSummaryView {
        String orderNumber;
        OffsetDateTime ordersDateTime;
        String orderStatus;
    }
}
