package pl.yummy.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "availableStreetId")
@ToString(of = {"availableStreetId", "availableStreet", "availableCity"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "available_street")
public class AvailableStreetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "available_street_id")
    private Integer availableStreetId;

    @Column(name = "available_street")
    private String availableStreet;

    @Column(name = "available_city")
    private String availableCity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "available_street")
    private Set<RestaurantAvailableStreetEntity> restaurantAvailableStreets;


}
