package pl.yummy.infrastructure.database.entity;

import java.time.OffsetDateTime;

public class zMenuEntity {

    private Integer menuId;
    private zRestaurantEntity restaurant;
    private String menuName;
    private String description;
    private Boolean isActive;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private Integer displayOrder;
}
