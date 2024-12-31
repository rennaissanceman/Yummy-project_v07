package pl.yummy.infrastructure.database.repositoryAbsolete;

import org.hibernate.Session;
import pl.yummy.business.daoAbsolete.ZAvailableDeliveryAreaDAO;
import pl.yummy.infrastructure.configuration.HibernateUtil;
import pl.yummy.infrastructure.database.entity.AvailableDeliveryAreaEntity;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ZAvailableDeliveryAreaRepository implements ZAvailableDeliveryAreaDAO {
    @Override
    public AvailableDeliveryAreaEntity createAvailableDeliveryArea(AvailableDeliveryAreaEntity availableDeliveryArea) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            session.persist(availableDeliveryArea);
            session.getTransaction().commit();
            return availableDeliveryArea;
        }
    }

    @Override
    public Optional<AvailableDeliveryAreaEntity> findAvailableDeliveryAreaById(Long availableDeliveryAreaId) {
        try(Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "SELECT a FROM AddressEntity a WHERE a.availableDeliveryAreaId = :available_delivery_area_id";
            Optional<AvailableDeliveryAreaEntity> result = session.createQuery(queryHQL, AvailableDeliveryAreaEntity.class)
                    .setParameter("available_delivery_area_id", availableDeliveryAreaId)
                    .uniqueResultOptional();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<AvailableDeliveryAreaEntity> findAvailableDeliveryAreasByRestaurantId(Long restaurantId) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "SELECT a FROM AvailableDeliveryAreaEntity a " +
                    "JOIN a.restaurant r " +
                    "WHERE r.restaurantId = :restaurant_id";
            List<AvailableDeliveryAreaEntity> result = session.createQuery(queryHQL, AvailableDeliveryAreaEntity.class)
                    .setParameter("restaurant_id", restaurantId)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<AvailableDeliveryAreaEntity> findAllAvailableDeliveryAreas() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "FROM AvailableDeliveryAreaEntity";
            List<AvailableDeliveryAreaEntity> result = session.createQuery(queryHQL, AvailableDeliveryAreaEntity.class)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public AvailableDeliveryAreaEntity updateAvailableDeliveryArea(AvailableDeliveryAreaEntity availableDeliveryArea) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            AvailableDeliveryAreaEntity merge = session.merge(availableDeliveryArea);
            session.getTransaction().commit();
            return merge;
        }
    }

    @Override
    public void deleteAvailableDeliveryArea(Long availableDeliveryAreaId) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "DELETE FROM AvailableDeliveryAreaEntity " +
                    "WHERE availableDeliveryAreaEntityId = :available_delivery_area_id";
            session.createQuery(queryHQL, AvailableDeliveryAreaEntity.class)
                    .setParameter("available_delivery_area_id", availableDeliveryAreaId)
                    .executeUpdate();
        }
    }
}
