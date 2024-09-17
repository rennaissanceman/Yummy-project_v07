package pl.yummy.infrastructure.database.entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class MenuItemEntity {

    private Integer menuItemId;
    private String itemName;
//    private FoodCategoryEntity foodCategory;
    private String description;
    private BigDecimal price;
    private Boolean isAvailable;
    private Integer preparationTime;
    private String imageURL;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private String ingredients;
    private Integer calories;
    private String dietType;



//    private RestaurantEntity restaurant;
    private OrderItemEntity orderItem;

}
