package pl.yummy.infrastructure.database.repository.processing;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.yummy.business.dao.processing.OrderPlacementDAO;
import pl.yummy.domain.Orders;
import pl.yummy.domain.requests.OrderPlacementRequest;
import pl.yummy.infrastructure.database.repository.jpa.OrdersJpaRepository;
import pl.yummy.infrastructure.database.repository.mapper.OrdersEntityMapper;

@Repository
@AllArgsConstructor
public class OrderPlacementRepository implements OrderPlacementDAO {

    /*
    Repozytorium do składania zamówienia. Przyjmuje OrderPlacementRequest i tworzy nowe zamówienie
    (lub aktualizuje istniejące).
    */

    private final OrdersJpaRepository ordersJpaRepository;
    private final OrdersEntityMapper ordersEntityMapper;

    @Override
    @Transactional
    public void placeOrder(OrderPlacementRequest request) {
        // Mapujemy dane z requestu do domenowego obiektu Orders.
        Orders order = Orders.builder()
                .ordersNumber(request.getOrderNumber())
                // Ustawiamy dane klienta, adres dostawy, numer restauracji itd.
                // (W praktyce prawdopodobnie wywołamy dedykowany serwis, który zbuduje obiekt Orders)
                .ordersDateTime(java.time.OffsetDateTime.now())
                .totalAmount(null) // Obliczane w serwisie, np. na podstawie pozycji
                .build();
        var ordersEntity = ordersEntityMapper.mapToEntity(order);
        ordersJpaRepository.saveAndFlush(ordersEntity);
    }
}
