package pl.yummy.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "restaurantId")
@ToString(of = {
        "restaurantId", "restaurantName", "description", "address", "owner", "phone", "email", "website",
        "openingHours", "cuisineType", "averageRating", "ratingCount", "createdAt", "updatedAt"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "restaurant")
public class zRestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Integer restaurantId;

    @Column(name = "restaurant_name", unique = true)
    private String restaurantName;

    @Column(name = "description")
    private String description;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private zAddressEntity address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private zOwnerEntity owner;

    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "website", unique = true)
    private String website;

    @Column(name = "opening_hours")
    private String openingHours;

    @Column(name = "cuisine_type")
    private String cuisineType;

    @Column(name = "average_rating")
    private Double averageRating;

    @Column(name = "rating_count")
    private Integer ratingCount;

    @Column(name = "url", unique = true)
    private String url;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private zMenuEntity menu;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private Set<zOrderEntity> order;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private Set<zDeliveryAreaEntity> deliveryAreas;

}
