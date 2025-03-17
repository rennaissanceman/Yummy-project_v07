package pl.yummy.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.yummy.business.dao.OrderProcessingDAO;
import pl.yummy.domain.Courier;
import pl.yummy.domain.Orders;
import pl.yummy.domain.OrdersItem;
import pl.yummy.infrastructure.database.entity.MenuItemEntity;
import pl.yummy.infrastructure.database.entity.OrdersEntity;
import pl.yummy.infrastructure.database.entity.OrdersItemEntity;
import pl.yummy.infrastructure.database.repository.jpa.CourierJpaRepository;
import pl.yummy.infrastructure.database.repository.jpa.MenuItemJpaRepository;
import pl.yummy.infrastructure.database.repository.jpa.OrdersItemJpaRepository;
import pl.yummy.infrastructure.database.repository.jpa.OrdersJpaRepository;
import pl.yummy.infrastructure.database.repository.mapper.CourierEntityMapper;
import pl.yummy.infrastructure.database.repository.mapper.OrdersEntityMapper;
import pl.yummy.infrastructure.database.repository.mapper.OrdersItemEntityMapper;

import java.util.Objects;

@Repository
@AllArgsConstructor
public class OrderProcessingRepository implements OrderProcessingDAO {

    private final OrdersJpaRepository ordersJpaRepository;
    private final CourierJpaRepository courierJpaRepository;
    private final MenuItemJpaRepository menuItemJpaRepository;
    private final OrdersItemJpaRepository ordersItemJpaRepository;

    private final OrdersEntityMapper ordersEntityMapper;
    private final CourierEntityMapper courierEntityMapper;
    private final OrdersItemEntityMapper ordersItemEntityMapper;


    /*
    odpowiada za:

    - Zapisanie (lub aktualizację) danych kuriera (przez mapowanie obiektu domenowego Courier na encję).
    - Aktualizację stanu zamówienia, np. ustawienie daty ukończenia dostawy, jeśli takie dane są przekazane.
    - Obsługę przetwarzania konkretnej pozycji zamówienia (OrdersItem) – wówczas najpierw zapisujemy informacje dotyczące
    pozycji (przy użyciu odpowiedniego mappera i repozytorium dla pozycji zamówienia), a następnie wykonujemy podstawowy
    proces realizacji zamówienia (czyli operację związaną z kurierem).
    */



    @Override
    @Transactional
    public void process(Orders order, Courier courier) {
        // Mapowanie obiektu domenowego Courier na encję i zapis do bazy
        var courierEntity = courierEntityMapper.mapToEntity(courier);
        courierJpaRepository.saveAndFlush(courierEntity);

        // Jeśli obiekt Orders posiada ustawioną datę rzeczywistej dostawy,
        // pobieramy encję zamówienia i aktualizujemy jej stan (np. data ukończenia dostawy)
        if (order.getDelivery() != null &&
                Objects.nonNull(order.getDelivery().getActualDeliveryDateTime())) {

            OrdersEntity ordersEntity = ordersJpaRepository
                    .findById(order.getOrdersId())
                    .orElseThrow(() -> new RuntimeException("Nie znaleziono zamówienia o id: " + order.getOrdersId()));

            if (ordersEntity.getDelivery() != null) {
                ordersEntity.getDelivery().setActualDeliveryDateTime(order.getDelivery().getActualDeliveryDateTime());
                ordersJpaRepository.saveAndFlush(ordersEntity);
            }
        }
    }

    @Override
    @Transactional
    public void process2(Orders orders, Courier courier, OrdersItem ordersItem) {
        MenuItemEntity menuItemEntity = menuItemJpaRepository.findById(ordersItem.getMenuItem().getMenuItemId())
                .orElseThrow(() -> new RuntimeException("Nie znaleziono pozycji menu o id: " + ordersItem.getMenuItem().getMenuItemId()));


    }

    @Override
    @Transactional
    public void process(Orders order, Courier courier, OrdersItem ordersItem) {
        // Wyszukiwanie encji MenuItem powiązanej z daną pozycją zamówienia
        MenuItemEntity menuItemEntity = menuItemJpaRepository.findById(
                ordersItem.getMenuItem().getMenuItemId()
        ).orElseThrow(() -> new RuntimeException("Nie znaleziono pozycji menu o id: " + ordersItem.getMenuItem().getMenuItemId()));

        // Mapowanie obiektu OrdersItem do encji oraz ustawienie odniesienia do MenuItemEntity
        OrdersItemEntity ordersItemEntity = ordersItemEntityMapper.mapToEntity(ordersItem);
        ordersItemEntity.setMenuItem(menuItemEntity);
        ordersItemJpaRepository.saveAndFlush(ordersItemEntity);

        // Następnie wykonujemy podstawowy proces realizacji zamówienia (przy użyciu danych kuriera)
        process(order, courier);
    }



    @Override
    @Transactional
    public void process3(Orders order, Courier courier) {
        // Mapowanie obiektu domenowego Courier na encję i zapis do bazy
        var courierEntity = courierEntityMapper.mapToEntity(courier);
        courierJpaRepository.saveAndFlush(courierEntity);



        // Jeśli obiekt domenowy Orders ma powiązany obiekt Delivery i posiada ustawioną datę rzeczywistego dostarczenia,
        // pobieramy encję zamówienia i aktualizujemy powiązaną DeliveryEntity.
        if (order.getDelivery() != null &&
                Objects.nonNull(order.getDelivery().getActualDeliveryDateTime())) {

            OrdersEntity ordersEntity = ordersJpaRepository.findById(order.getOrdersId())
                    .orElseThrow(() -> new RuntimeException("Nie znaleziono zamówienia o id: " + order.getOrdersId()));

            // Pobieramy DeliveryEntity powiązaną z zamówieniem
            if (ordersEntity.getDelivery() != null) {
                ordersEntity.getDelivery().setActualDeliveryDateTime(
                        order.getDelivery().getActualDeliveryDateTime()
                );
                ordersJpaRepository.saveAndFlush(ordersEntity);
            }
        }
    }

    @Override
    @Transactional
    public void process3(Orders order, Courier courier, OrdersItem ordersItem) {
        // Wyszukiwanie encji produktu (np. MenuItem) powiązanego z pozycją zamówienia
        MenuItemEntity menuItemEntity = menuItemJpaRepository
                .findById(ordersItem.getMenuItem().getMenuItemId())
                .orElseThrow();

        // Mapowanie obiektu OrdersItem do encji i ustawienie odniesienia do MenuItemEntity
        OrdersItemEntity ordersItemEntity = ordersItemEntityMapper.mapToEntity(ordersItem);
        ordersItemEntity.setMenuItem(menuItemEntity);
        ordersItemJpaRepository.saveAndFlush(ordersItemEntity);

        // Następnie wykonujemy podstawowy proces przetwarzania zamówienia (z kurierem)
        process3(order, courier);
    }



}
