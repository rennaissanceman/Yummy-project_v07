package pl.yummy.domain.view;

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
public class OrderHistoryView {

    //OrderHistory reprezentuje historię realizacji konkretnego zamówienia.
//     gromadzi historię realizacji zamówienia (podobnie jak CarHistory przechowuje historię serwisową samochodu)
//     i zawiera listę zdarzeń (np. poszczególne etapy realizacji zamówienia, płatności, dostawy).

    String orderNumber;  // identyfikator zamówienia
    List<OrderProcessingEvent> orderProcessingEvents; // lista zdarzeń związanych z realizacją zamówienia

    @Value
    @Builder
    @ToString(of = {"eventNumber", "receivedDateTime", "completedDateTime", "customerComment"})
    public static class OrderProcessingEvent {
        String eventNumber;              // unikalny identyfikator zdarzenia (np. numer zdarzenia)
        OffsetDateTime receivedDateTime; // data/godzina przyjęcia zdarzenia (np. rozpoczęcia przetwarzania)
        OffsetDateTime completedDateTime;// data/godzina zakończenia (np. ukończenia realizacji)
        String customerName; // Nazwa klienta (lub jego pełne imię i nazwisko)
        String restaurantName; // Nazwa restauracji
        String menuName; // Nazwa menu, z którego dokonano zamówienia
        String customerComment;          // ewentualny komentarz klienta
        BigDecimal totalAmount; // Łączna kwota zamówienia
        // Opcjonalnie: dodatkowe listy – np. szczegóły płatności, informacje o dostawie itp.
        List<Payment> payments;
        List<Delivery> deliveries;

    }
}
