package pl.yummy.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.yummy.infrastructure.database.entity.enums.FoodCategory;

import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "foodId")
@ToString(of = {
        "foodId", "foodName", "description", "foodCategory", "displayOrder", "isActive", "createdAt", "updatedAt",
        "imageURL", "restaurant"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "food")
public class FoodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private Integer foodId;

    @Column(name = "food_name", nullable = false, unique = true)
    private String foodName;

    @Column(name = "description")
    private String description;

    @Column(name = "food_category", nullable = false, unique = true)
    private FoodCategory foodCategory;

    @Column(name = "display_order")
    private Integer displayOrder;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    @Column(name = "image_URL", nullable = false, unique = true)
    private String imageURL;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "food")
    private Set<MenuItemEntity> menuItems;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "food")
    private Set<OrderItemEntity> orderItems;

}
