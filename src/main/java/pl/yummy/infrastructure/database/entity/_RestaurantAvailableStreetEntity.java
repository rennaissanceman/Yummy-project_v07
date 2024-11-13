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
public class _RestaurantAvailableStreetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_available_street_id")
    private Integer restaurantAvailableStreetId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id")
    private _RestaurantEntity restaurant;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "available_street_id")
    private _AvailableStreetEntity availableStreet;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant_available_street")
    private Set<_OrderEntity> orders;
}
