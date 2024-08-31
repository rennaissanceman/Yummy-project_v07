package pl.yummy.infrastructure.database.entity;

import java.math.BigDecimal;

public class MenuItemEntity {

    private Integer menuItemId;
    private String description;
    private BigDecimal price;
    private FoodCategoryEntity foodCategory;
    private RestaurantEntity restaurant;
    private String imagePath;
}
