package pl.yummy.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.math.BigDecimal;
import java.util.List;

@With
@Value
@Builder
public class YummyOrderSummary {

    String customerName;
    String restaurantName;
    List<MenuItem> menuItems;
    BigDecimal totalAmount;
    String estimatedDeliveryTime;

}
