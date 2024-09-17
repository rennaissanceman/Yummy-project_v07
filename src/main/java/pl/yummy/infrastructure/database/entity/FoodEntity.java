package pl.yummy.infrastructure.database.entity;

import java.time.OffsetDateTime;

public class FoodEntity {

    private Integer foodCategoryId;
    private String foodCategoryName;
    private RestaurantEntity restaurant;
    private String description;
    private String cuisineType;
    private Integer displayOrder;
/*    private String dietType;*/
    private Boolean isActive;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private String imageURL;




}
