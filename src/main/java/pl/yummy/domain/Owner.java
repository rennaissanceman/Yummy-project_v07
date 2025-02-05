package pl.yummy.domain;

import lombok.*;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "ownerId")
@ToString(of = {"ownerId", "ownerNumber", "userAuth"})
public class Owner {

    Long ownerId;
    String ownerNumber;
    String ownerName;
    UserAuth userAuth;
    Set<Restaurant> restaurants;

}
