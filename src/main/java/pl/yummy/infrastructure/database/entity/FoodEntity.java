package pl.yummy.infrastructure.database.entity;

import pl.yummy.infrastructure.database.entity.enums.FoodCategory;

import java.time.OffsetDateTime;

public class FoodEntity {

    private Integer foodId;
    private String foodName;
    private String description;
    private FoodCategory foodCategory;
    private Integer displayOrder;
    private Boolean isActive;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private String imageURL;
    private RestaurantEntity restaurant;




}
