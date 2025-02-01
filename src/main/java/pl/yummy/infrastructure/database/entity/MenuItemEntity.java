package pl.yummy.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.yummy.infrastructure.database.entity.enums.DietTypeEnumEntity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "menuItemId")
@ToString(of = {
        "menuItemId", "itemName", "menu","description", "isAvailable", "dietType", "calories", "ingredients",
        "portionWeight", "preparationTime", "price", "imageURL", "displayOrder","createdAt", "updatedAt"})
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu")
    private MenuEntity menu;

    @Column(name = "description")
    private String description;

    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable;

    @Column(name = "diet_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private DietTypeEnumEntity dietType;

    @Column(name = "calories")
    private Integer calories;

    @Column(name = "ingredients")
    private String ingredients;

    @Column(name = "portionWeight")
    private String portionWeight;

    @Column(name = "preparationTime")
    private Integer preparationTime;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "imageURL", nullable = false, unique = true)
    private String imageURL;

    @Column(name = "display_order", nullable = false)
    private Integer displayOrder;

    @Column(name = "createdAt")
    private OffsetDateTime createdAt;

    @Column(name = "updatedAt")
    private OffsetDateTime updatedAt;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menu_item")
    private Set<OrdersItemEntity> ordersItems;

}
