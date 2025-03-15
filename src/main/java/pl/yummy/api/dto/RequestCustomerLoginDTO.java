package pl.yummy.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestCustomerLoginDTO {

    private String email;
    private String password;

    public static RequestCustomerLoginDTO buildDefault() {
        return RequestCustomerLoginDTO.builder()
                .email("")
                .password("")
                .build();
    }
}
