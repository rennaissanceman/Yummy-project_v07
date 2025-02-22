package pl.yummy.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDTO {

//    Dla właściciela restauracji
    Long ownerId;
    String ownerNumber;
    String ownerName;
}
