package pl.yummy.domain;

import lombok.*;
import pl.yummy.domain.enums.DeliveryStatusEnumDomain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@With
@Value
@Builder
@EqualsAndHashCode(of = "deliveryId")
@ToString(of = {"deliveryId", "deliveryNumber", "orders", "availableDeliveryArea", "courier", "deliveryStatus",
        "starTime", "endTime", "estimatedDeliveryTime", "actualDeliveryDateTime", "deliveryFee", "deliveryNotes"})
public class Delivery {

    Integer deliveryId;
    String deliveryNumber;
    Orders orders;
    AvailableDeliveryArea availableDeliveryArea;
    Courier courier;
    DeliveryStatusEnumDomain deliveryStatus;
    OffsetDateTime starTime;
    OffsetDateTime endTime;
    OffsetDateTime estimatedDeliveryTime;
    OffsetDateTime actualDeliveryDateTime;
    BigDecimal deliveryFee;
    String deliveryNotes;

    public boolean shouldBeDispatched() {
        return orders != null
                && availableDeliveryArea != null
                && courier != null
                && deliveryStatus == DeliveryStatusEnumDomain.PENDING
                && starTime != null
                && deliveryFee != null && deliveryFee.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isLate() {
        return actualDeliveryDateTime != null && estimatedDeliveryTime != null
                && actualDeliveryDateTime.isAfter(estimatedDeliveryTime);
    }
}
