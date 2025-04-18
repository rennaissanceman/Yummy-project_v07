package pl.yummy.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemUpdateRequestDTO {

//    Na podstawie RequestMenuItemUpdate
    Long menuItemId;
    Boolean isAvailable;
    BigDecimal price;
    String description;
}
