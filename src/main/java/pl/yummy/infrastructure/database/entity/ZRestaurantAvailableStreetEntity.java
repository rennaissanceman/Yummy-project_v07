package pl.yummy.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "restaurantAvailableStreetId")
@ToString(of = {"restaurantAvailableStreetId", "restaurant", "availableStreet"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "restaurant_available_street")
public class ZRestaurantAvailableStreetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_available_street_id")
    private Integer restaurantAvailableStreetId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private ZRestaurantEntity restaurant;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "available_street_id")
    private ZAvailableStreetEntity availableStreet;



    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant_available_street")
    private Set<ZOrderEntity> orders;
}
