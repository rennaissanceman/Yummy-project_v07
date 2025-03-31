package pl.yummy.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

//    Zawiera podstawowe dane adresowe

    Long addressId;
    String country;
    String city;
    String postalCode;
    String street;

    // Metoda pomocnicza zwracająca sformatowany adres
    public String getFormattedAddress() {
        StringBuilder formatted = new StringBuilder();
        if (street != null && !street.isEmpty()) {
            formatted.append(street);
        }
        if (postalCode != null && !postalCode.isEmpty()) {
            formatted.append(", ").append(postalCode);
        }
        if (city != null && !city.isEmpty()) {
            formatted.append(" ").append(city);
        }
        if (country != null && !country.isEmpty()) {
            formatted.append(", ").append(country);
        }
        return formatted.toString();
    }

}
