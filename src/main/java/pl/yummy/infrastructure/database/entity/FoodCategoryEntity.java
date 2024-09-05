package pl.yummy.infrastructure.database.entity;

import java.time.OffsetDateTime;

public class FoodCategoryEntity {

    private Integer foodCategoryId;
    private String foodCategoryName;
    private String description;
    private String cuisineType;
    private Integer displayOrder;
/*    private String dietType;*/
    private Boolean isActive;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private String imageURL;


    private MenuItemEntity menuItem;

}
