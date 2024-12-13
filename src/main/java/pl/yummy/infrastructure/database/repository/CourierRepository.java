package pl.yummy.infrastructure.database.repository;

import org.hibernate.Session;
import pl.yummy.business.dao.CourierDAO;
import pl.yummy.infrastructure.configuration.HibernateUtil;
import pl.yummy.infrastructure.database.entity.CourierEntity;
import pl.yummy.infrastructure.database.entity.DeliveryEntity;
import pl.yummy.infrastructure.database.entity.RestaurantEntity;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CourierRepository implements CourierDAO {

    @Override
    public CourierEntity createCourier(CourierEntity courier) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            session.persist(session);
            session.getTransaction().commit();
            return courier;
        }
    }

    @Override
    public Optional<CourierEntity> findCourierById(Long courierId) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "SELECT c FROM CourierEntity c WHERE c.courierId = :courier_id";
            Optional<CourierEntity> result = session.createQuery(queryHQL, CourierEntity.class)
                    .setParameter("courier_id", courierId)
                    .uniqueResultOptional();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<CourierEntity> findAvailableCouriers() {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "SELECT c FROM CourierEntity c WHERE c.courierStatus = :courier_status";
            List<CourierEntity> result = session.createQuery(queryHQL, CourierEntity.class)
                    .setParameter("courier_status", "Available")
                    .list();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<CourierEntity> findAvailableCouriersByRestaurant(Long restaurantId) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "SELECT DISTINCT c " +
                    "FROM CourierEntity c " +
                    "JOIN c.deliveries d " +
                    "JOIN d.availableDeliveryArea a " +
                    "WHERE a.restaurant.restaurantId = :restaurant_id";
            List<CourierEntity> result = session.createQuery(queryHQL, CourierEntity.class)
                    .setParameter("restaurant_id", restaurantId)
                    .list();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<CourierEntity> findAllCouriers() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "FROM CourierEntity";
            List<CourierEntity> result = session.createQuery(queryHQL, CourierEntity.class)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public CourierEntity updateCourier(CourierEntity courier) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            CourierEntity merge = session.merge(courier);
            session.getTransaction().commit();
            return merge;
        }
    }

    @Override
    public void deleteCourier(Long courierId) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "DELETE FROM CourierEntity WHERE courierId = :courier_id";
            session.createQuery(queryHQL, CourierEntity.class)
                    .setParameter("courier_id", courierId)
                    .executeUpdate();
        }
    }
}
