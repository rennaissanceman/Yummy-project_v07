package pl.yummy.domain;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "menuId")
@ToString(of = {"menuId", "restaurant", "menuName", "description", "validFrom", "validTo", "createdAt", "updatedAt"})
public class Menu {

    Integer menuId;
    Restaurant restaurant;
    String menuName;
    String description;
    OffsetDateTime validFrom;
    OffsetDateTime validTo;
    OffsetDateTime createdAt;
    OffsetDateTime updatedAt;
    Set<MenuItem> menuItems;
    Set<Orders> orders;
}
