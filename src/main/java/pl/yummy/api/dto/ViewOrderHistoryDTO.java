package pl.yummy.api.dto;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;
import lombok.With;
import pl.yummy.domain.Delivery;
import pl.yummy.domain.Payment;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@With
@Value
@Builder
@ToString(of = "orderNumber")
public class ViewOrderHistoryDTO {

    /*
    Prezentuje historię realizacji zamówienia, zawierając listę zdarzeń związanych z przetwarzaniem zamówienia,
    w tym informacje o płatnościach i dostawach. Pozwala na szczegółowy przegląd przebiegu realizacji zamówienia,
    co jest przydatne zarówno dla klienta, jak i dla działu obsługi.
    */

    String orderNumber;
    List<OrderProcessingEvent> orderProcessingEvents;

    @Value
    @Builder
    @ToString(of = {"eventNumber", "receivedDateTime", "completedDateTime", "customerComment"})
    public static class OrderProcessingEvent {
        String eventNumber;
        OffsetDateTime receivedDateTime;
        OffsetDateTime completedDateTime;
        String customerName;
        String restaurantName;
        String menuName;
        String customerComment;
        BigDecimal totalAmount;
        List<Payment> payments;
        List<Delivery> deliveries;
    }
}
