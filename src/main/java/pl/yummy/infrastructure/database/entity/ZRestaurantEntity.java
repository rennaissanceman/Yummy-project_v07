package pl.yummy.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "restaurantId")
@ToString(of = {
        "restaurantId", "restaurantName", "description", "address", "owner", "contactDetails", "website",
        "openingHours", "cuisineType", "averageRating", "ratingCount", "createdAt", "updatedAt"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "restaurant")
public class ZRestaurantEntity {

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
    private ZAddressEntity address;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private OwnerEntity owner;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_details_id")
    private ZContactDetailsEntity contactDetails;

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

    @Column(name = "logoURL", unique = true)
    private String logoURL;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private Set<ZRestaurantAvailableStreetEntity> restaurantAvailableStreets;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private Set<ZFoodEntity> foods;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private ZMenuEntity menu;


}
