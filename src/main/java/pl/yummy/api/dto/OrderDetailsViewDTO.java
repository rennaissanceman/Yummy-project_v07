package pl.yummy.api.dto;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@With
@Value
@Builder
public class OrderDetailsViewDTO {

    /*
     Model szczegółów zamówienia – zawiera dane klienta, listę pozycji zamówienia oraz statusy.
     Dostarcza szczegółowych informacji o konkretnym zamówieniu. Zawiera dane dotyczące zamówienia, takie jak numer,
     data, dane kontaktowe klienta, lista zamówionych pozycji, a także statusy związane z płatnością i dostawą.
     Ten widok jest użyteczny do szczegółowego podglądu zamówienia przez klienta lub pracownika obsługi.
     */

    String orderNumber;
    OffsetDateTime ordersDateTime;
    String customerName;
    String customerEmail;
    String customerPhone;
    List<OrderItemView> orderItems;
    String orderStatus;
    String paymentStatus;
    String deliveryStatus;
    BigDecimal totalAmount;
    String deliveryAddress;

    @With
    @Value
    @Builder
    public static class OrderItemView {
        String itemName;
        Integer quantity;
        BigDecimal unitPrice;
        BigDecimal totalPrice;
    }
}
