package pl.yummy.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuDTO {

//    Dla menu restauracji:

    Long menuId;
    Long restaurantId;
    String menuName;
    String description;
    String validFrom;
    String validTo;
    String createdAt;
    String updatedAt;
    List<MenuItemDTO> menuItems;

}
