package pl.yummy.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class YummyMenuManagementRequest {

    String restaurantName;
    String action; // "ADD", "UPDATE", "DELETE"
    MenuItem menuItem;
}
