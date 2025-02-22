package pl.yummy.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthDTO {

//    Uproszczona reprezentacja danych uwierzytelniających (bez haseł)
    Long userAuthId;
    String phone;
    String email;
    OffsetDateTime createdAt;
    OffsetDateTime updatedAt;
}
