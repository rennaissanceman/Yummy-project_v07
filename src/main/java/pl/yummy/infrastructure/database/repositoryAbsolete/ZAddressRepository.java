package pl.yummy.infrastructure.database.repositoryAbsolete;

import org.hibernate.Session;
import pl.yummy.business.daoAbsolete.ZAddressDAO;
import pl.yummy.infrastructure.configuration.HibernateUtil;
import pl.yummy.infrastructure.database.entity.AddressEntity;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ZAddressRepository implements ZAddressDAO {
    @Override
    public AddressEntity createAddress(AddressEntity address) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            session.persist(address);
            session.getTransaction().commit();
            return address;
        }
    }

    @Override
    public Optional<AddressEntity> findAddressById(Long addressId) {
        try(Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "SELECT a FROM AddressEntity a WHERE a.addressId = :address_id";
            Optional<AddressEntity> result = session.createQuery(queryHQL, AddressEntity.class)
                    .setParameter("address_id", addressId)
                    .uniqueResultOptional();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<AddressEntity> findAddressByCity(String city) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "FROM AddressEntity a WHERE a.city = :city";
            List<AddressEntity> result = session.createQuery(queryHQL, AddressEntity.class)
                    .setParameter("city", city)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<AddressEntity> findAddressesByCustomerId(Long customerId) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "SELECT a FROM AddressEntity a " +
                    "JOIN a.deliveryAddress da " +
                    "WHERE da.customer.customerId = :customer_id";
            List<AddressEntity> result = session.createQuery(queryHQL, AddressEntity.class)
                    .setParameter("customer_id", customerId)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<AddressEntity> findAddressesByRestaurantId(Long restaurantId) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "SELECT a FROM AddressEntity a " +
                    "JOIN a.restaurant r " +
                    "WHERE r.restaurantId = :restaurant_id";
            List<AddressEntity> result = session.createQuery(queryHQL, AddressEntity.class)
                    .setParameter("restaurant_id", restaurantId)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<AddressEntity> findAllAddresses() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "FROM AddressEntity";
            List<AddressEntity> result = session.createQuery(queryHQL, AddressEntity.class)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public AddressEntity updateAddress(AddressEntity address) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            AddressEntity merge = session.merge(address);
            session.getTransaction().commit();
            return merge;
        }
    }

    @Override
    public void deleteAddress(Long addressId) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "DELETE FROM AddressEntity WHERE addressId = :address_id";
            session.createQuery(queryHQL, AddressEntity.class)
                    .setParameter("address_id", addressId)
                    .executeUpdate();
        }
    }

    @Override
    public boolean existsAddressByCityAndStreet(String city, String street) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "SELECT COUNT(a) FROM AddressEntity a WHERE a.city = :city AND a.street = :street";

            Integer count = session.createQuery(queryHQL, Integer.class)
                    .setParameter("city", city)
                    .setParameter("street", street)
                    .uniqueResult();

            return count > 0;
        }
    }
}
