package pl.yummy.domain;

import lombok.*;

import java.time.OffsetDateTime;

@With
@Value
@Builder
@EqualsAndHashCode(of = "userAuthId")
@ToString(of = {"userAuthId", "phone", "email", "passwordHash", "salt", "createdAt", "updatedAt"})
public class UserAuth {

    Long userAuthId;
    String phone;
    String email;
    String passwordHash;
    String salt;
    OffsetDateTime createdAt;
    OffsetDateTime updatedAt;
    Owner owner;
    Customer customer;
    Courier courier;
}
