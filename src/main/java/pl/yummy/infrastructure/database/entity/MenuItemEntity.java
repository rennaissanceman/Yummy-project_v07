package pl.yummy.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "menuItemId")
@ToString(of = {
        "menuItemId", "itemName", "description", "price", "food", "menu", "isAvailable", "preparationTime", "imageURL",
        "createdAt", "updatedAt", "ingredients", "calories", "dietType", "portionWeight"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "menu_item")
public class MenuItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menuItemId")
    private Integer menuItemId;

    @Column(name = "item_name", nullable = false, unique = true)
    private String itemName;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "food_id")
    private FoodEntity food;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_id")
    private MenuEntity menu;

    @Column(name = "isAvailable", nullable = false)
    private Boolean isAvailable;

    @Column(name = "preparationTime", nullable = false)
    private Integer preparationTime;

    @Column(name = "imageURL", nullable = false, unique = true)
    private String imageURL;

    @Column(name = "createdAt")
    private OffsetDateTime createdAt;

    @Column(name = "updatedAt")
    private OffsetDateTime updatedAt;

    @Column(name = "ingredients")
    private String ingredients;

    @Column(name = "calories")
    private Integer calories;

    @Column(name = "dietType")
    private String dietType;

    @Column(name = "portionWeight")
    private String portionWeight;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menu_item")
    private Set<OrderItemEntity> orderItems;

}
