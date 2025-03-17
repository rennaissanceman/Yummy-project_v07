package pl.yummy.infrastructure.database.repository;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.yummy.business.dao.OrderLifecycleProcessingDAO;
import pl.yummy.domain.enums.OrdersStatusEnumDomain;
import pl.yummy.infrastructure.database.entity.OrdersEntity;
import pl.yummy.infrastructure.database.entity.enums.DeliveryStatusEnumEntity;
import pl.yummy.infrastructure.database.entity.enums.OrdersStatusEnumEntity;
import pl.yummy.infrastructure.database.repository.jpa.DeliveryJpaRepository;
import pl.yummy.infrastructure.database.repository.jpa.OrdersJpaRepository;

@Repository
@AllArgsConstructor
public class OrderLifecycleProcessingRepository implements OrderLifecycleProcessingDAO {

    private final OrdersJpaRepository ordersJpaRepository;
    private final DeliveryJpaRepository deliveryJpaRepository;

    @Transactional
    public void updateOrderStatus(Long orderId, OrdersStatusEnumDomain newStatus) {
        OrdersEntity ordersEntity = ordersJpaRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        ordersEntity.setOrdersStatus(OrdersStatusEnumEntity.valueOf(newStatus.name()));
        ordersJpaRepository.save(ordersEntity);
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        OrdersEntity ordersEntity = ordersJpaRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        ordersEntity.setOrdersStatus(OrdersStatusEnumEntity.CANCELLED_BY_CUSTOMER);
        ordersJpaRepository.save(ordersEntity);

        // Jeśli zamówienie ma przypisaną dostawę, anuluj ją również
        deliveryJpaRepository.findByCourier_CourierId(Long.valueOf(ordersEntity.getOrdersId()))
                .forEach(delivery -> {
                    delivery.setDeliveryStatus(DeliveryStatusEnumEntity.CANCELLED);
                    deliveryJpaRepository.save(delivery);
                });
    }

    /*
    - Obsługa pełnego cyklu życia zamówienia w jednym repozytorium.
    - Możliwość anulowania zamówień i aktualizacji ich statusów.

    Obsługa pełnego cyklu zamówienia  --> Aktualizacja statusu, anulowanie zamówień

    Zakres odpowiedzialności:
    - Odpowiada za pełen cykl życia zamówienia.
    - Umożliwia aktualizację statusu zamówienia (metoda updateOrderStatus):
    - Pobiera encję zamówienia na podstawie identyfikatora.
    - Uaktualnia status zamówienia zgodnie z przekazanym nowym stanem (np. zmiana statusu z „PENDING” na „CONFIRMED”).
    - Umożliwia anulowanie zamówienia (metoda cancelOrder):
    - Ustawia status zamówienia na CANCELLED_BY_CUSTOMER.
    - Dodatkowo, jeżeli zamówieniu przypisana jest dostawa, repozytorium wyszukuje tę dostawę i ustawia jej status na CANCELLED.

    Kluczowe elementy:
    - Wykorzystuje OrdersJpaRepository do operacji na zamówieniach oraz DeliveryJpaRepository do operacji na dostawach.
    - Metody są opakowane w transakcje (adnotacja @Transactional), co gwarantuje spójność operacji przy
    aktualizacji stanu zamówienia oraz ewentualnej dostawy.
    */
}
