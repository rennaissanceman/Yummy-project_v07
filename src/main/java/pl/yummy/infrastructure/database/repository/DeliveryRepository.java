package pl.yummy.infrastructure.database.repository;

import org.hibernate.Session;
import pl.yummy.business.dao.DeliveryDAO;
import pl.yummy.infrastructure.configuration.HibernateUtil;
import pl.yummy.infrastructure.database.entity.DeliveryEntity;
import pl.yummy.infrastructure.database.entity.enums.DeliveryStatus;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DeliveryRepository implements DeliveryDAO {

    @Override
    public DeliveryEntity createDelivery(DeliveryEntity delivery) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            session.persist(delivery);
            session.getTransaction().commit();
            return delivery;
        }
    }

    @Override
    public Optional<DeliveryEntity> findDeliveryById(Long deliveryId) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "SELECT d FROM DeliveryEntity d WHERE d.deliveryId = :delivery_id";
            Optional<DeliveryEntity> result = session.createQuery(queryHQL, DeliveryEntity.class)
                    .setParameter("deliveryId", deliveryId)
                    .uniqueResultOptional();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public Optional<DeliveryEntity> findDeliveryByOrderId(Long orderId) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "SELECT d FROM DeliveryEntity d JOIN FETCH d.orders o WHERE o.ordersId = :orders_id";
            Optional<DeliveryEntity> result = session.createQuery(queryHQL, DeliveryEntity.class)
                    .setParameter("orders_id", orderId)
                    .uniqueResultOptional();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public Optional<DeliveryEntity> findDeliveryByCourierId(Long courierId) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "SELECT d FROM DeliveryEntity d JOIN FETCH d.courier c WHERE c.courierId = :courier_id";
            Optional<DeliveryEntity> result = session.createQuery(queryHQL, DeliveryEntity.class)
                    .setParameter("courier_id", courierId)
                    .uniqueResultOptional();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<DeliveryEntity> findDeliveriesByStatus(List<String> deliveryStatus) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            List<DeliveryStatus> deliveryStatusList = deliveryStatus.stream()
                    .map(status -> DeliveryStatus.valueOf(status.toUpperCase()))
                    .toList();

            String queryHQL = "SELECT d FROM DeliveryEntity d WHERE d.deliveryStatus IN :deliveryStatuses";

            List<DeliveryEntity> result = session.createQuery(queryHQL, DeliveryEntity.class)
                    .setParameter("deliveryStatuses", deliveryStatusList)
                    .list();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public DeliveryEntity updateDelivery(DeliveryEntity delivery) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            DeliveryEntity merge = session.merge(delivery);
            session.getTransaction().commit();
            return merge;
        }
    }


    @Override
    public void deleteDelivery(Long deliveryId) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "DELETE FROM DeliveryEntity WHERE deliveryId = :delivery_id";
            session.createQuery(queryHQL, DeliveryEntity.class)
                    .setParameter("delivery_id", deliveryId)
                    .executeUpdate();
        }
    }
}
