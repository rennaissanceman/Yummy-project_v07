package pl.yummy.infrastructure.database.repository.processing;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.yummy.business.dao.processing.OrderCancellationDAO;
import pl.yummy.domain.requests.OrderCancellationRequest;
import pl.yummy.infrastructure.database.entity.enums.OrdersStatusEnumEntity;
import pl.yummy.infrastructure.database.repository.jpa.OrdersJpaRepository;

@Repository
@AllArgsConstructor
public class OrderCancellationRepository implements OrderCancellationDAO {

    /*
    Repozytorium do anulowania zamówienia. Przyjmuje OrderCancellationRequest i aktualizuje encję zamówienia,
    zmieniając jej status na anulowany.

    – Służy do anulowania zamówienia. Przyjmuje dane z OrderCancellationRequest, które zawierają numer zamówienia
    oraz przyczynę anulowania, a następnie aktualizuje stan zamówienia (OrdersEntity), zmieniając jego status
    na anulowany i zapisuje informację o przyczynie.
    */

    private final OrdersJpaRepository ordersJpaRepository;

    @Override
    @Transactional
    public void cancelOrder(OrderCancellationRequest request) {
        var ordersEntity = ordersJpaRepository.findByOrdersNumber(request.getOrderNumber())
                .orElseThrow(() -> new RuntimeException("Order not found: " + request.getOrderNumber()));
        ordersEntity.setOrdersStatus(OrdersStatusEnumEntity.CANCELLED_BY_CUSTOMER);
        // Opcjonalnie – zapisz przyczynę anulowania, jeśli encja to umożliwia.
        ordersJpaRepository.saveAndFlush(ordersEntity);
    }
}
