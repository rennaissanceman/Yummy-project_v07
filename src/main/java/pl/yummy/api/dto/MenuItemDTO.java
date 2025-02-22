package pl.yummy.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemDTO {

//    Reprezentacja pozycji w menu

    Long menuItemId;
    String itemName;
    Long menuId;
    String description;
    Boolean isAvailable;
    String dietType;
    Integer calories;
    String ingredients;
    String portionWeight;
    Integer preparationTime;
    BigDecimal price;
    String imageURL;
    Integer displayOrder;
    OffsetDateTime createdAt;
    OffsetDateTime updatedAt;
}
