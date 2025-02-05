package pl.yummy.domain;

import lombok.*;
import pl.yummy.domain.enums.DietTypeEnumDomain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "menuItemId")
@ToString(of = {"menuItemId", "itemName", "menu", "description", "isAvailable", "dietType", "calories", "ingredients",
        "portionWeight", "preparationTime", "price", "imageURL", "displayOrder", "createdAt", "updatedAt"})
public class MenuItem {

    Long menuItemId;
    String itemName;
    Menu menu;
    String description;
    Boolean isAvailable;
    DietTypeEnumDomain dietType;
    Integer calories;
    String ingredients;
    String portionWeight;
    Integer preparationTime;
    BigDecimal price;
    String imageURL;
    Integer displayOrder;
    OffsetDateTime createdAt;
    OffsetDateTime updatedAt;
    Set<OrdersItem> ordersItems;

    public boolean isAvailable() {
        return Boolean.TRUE.equals(isAvailable);
    }
}
