package pl.yummy.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestOrderPlacementDTO {

//    Na podstawie RequestOrderPlacement
    String existingCustomerEmail;
    String customerName;
    String customerSurname;
    String customerPhone;
    String customerEmail;
    String customerAddressCountry;
    String customerAddressCity;
    String customerAddressPostalCode;
    String customerAddressStreet;
    String orderNumber;
    String restaurantIdentifier;

    // Metoda buildDefault() tworzy domyślną instancję DTO z pustymi wartościami.
    public static RequestOrderPlacementDTO buildDefault() {
        return RequestOrderPlacementDTO.builder()
                .existingCustomerEmail("")
                .customerName("")
                .customerSurname("")
                .customerPhone("")
                .customerEmail("")
                .customerAddressCountry("")
                .customerAddressCity("")
                .customerAddressPostalCode("")
                .customerAddressStreet("")
                .orderNumber("")
                .restaurantIdentifier("")
                .build();
    }
}
