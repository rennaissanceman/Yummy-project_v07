package pl.yummy.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerLoginRequestDTO {

    private String email;
    private String password;

    public static CustomerLoginRequestDTO buildDefault() {
        return CustomerLoginRequestDTO.builder()
                .email("")
                .password("")
                .build();
    }
}
