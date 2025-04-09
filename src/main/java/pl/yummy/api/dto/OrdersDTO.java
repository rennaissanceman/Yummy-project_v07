package pl.yummy.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDTO {

//    Kluczowe dane zamówienia
    Long ordersId;
    String ordersNumber;
    CustomerDTO customer;
    Long menuId;
    String ordersDateTime;
    String ordersStatus;
    String ordersDescription;
    BigDecimal totalAmount;
    Integer ordersRating;
    Long availableDeliveryAreaId; //(opcjonalnie)
    Long customerAddressId; //(opcjonalnie)
    PaymentDTO payment; //(opcjonalnie)
    InvoiceDTO invoice; //(opcjonalnie)
    DeliveryDTO delivery; //(opcjonalnie)
    List<OrderItemDTO> ordersItems;
}
