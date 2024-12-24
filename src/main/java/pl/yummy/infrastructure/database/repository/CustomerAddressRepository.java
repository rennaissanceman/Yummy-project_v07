package pl.yummy.infrastructure.database.repository;

import org.hibernate.Session;
import pl.yummy.business.dao.CustomerAddressDAO;
import pl.yummy.infrastructure.configuration.HibernateUtil;
import pl.yummy.infrastructure.database.entity.CustomerAddressEntity;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CustomerAddressRepository implements CustomerAddressDAO {


    @Override
    public CustomerAddressEntity createCustomerAddress(CustomerAddressEntity customerAddress) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            session.persist(customerAddress);
            session.getTransaction().commit();
            return customerAddress;
        }
    }

    @Override
    public Optional<CustomerAddressEntity> findCustomerAddressById(Long customerAddressId) {
        try(Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "SELECT c FROM CustomerAddressEntity c WHERE c.customerAddressId = :customer_address_id";
            Optional<CustomerAddressEntity> result = session.createQuery(queryHQL, CustomerAddressEntity.class)
                    .setParameter("customer_address_id", customerAddressId)
                    .uniqueResultOptional();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<CustomerAddressEntity> findAllCustomerAddresses() {
        try(Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "FROM CustomerAddressEntity";
            List<CustomerAddressEntity> result = session.createQuery(queryHQL, CustomerAddressEntity.class)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }


    @Override
    public List<CustomerAddressEntity> findCustomerAddressesByCustomerId(Long customerId) {
        try(Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "FROM CustomerAddressEntity c WHERE c.customer.customerId = :customer_id";
            List<CustomerAddressEntity> result = session.createQuery(queryHQL, CustomerAddressEntity.class)
                    .setParameter("customer_id", customerId)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }


    @Override
    public Optional<CustomerAddressEntity> findDefaultCustomerAddressByCustomerId(Long customerId) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "FROM CustomerAddressEntity c WHERE c.customer.customerId = :customer_id AND c.isDefault = true";
            Optional<CustomerAddressEntity> result = session.createQuery(queryHQL, CustomerAddressEntity.class)
                    .setParameter("customer_id", customerId)
                    .uniqueResultOptional();

            session.getTransaction().commit();
            return result;
        }
    }


    @Override
    public CustomerAddressEntity updateCustomerAddress(CustomerAddressEntity customerAddress) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            CustomerAddressEntity merge = session.merge(customerAddress);
            session.getTransaction().commit();
            return merge;
        }
    }

    @Override
    public void deleteCustomerAddress(Long customerAddressId) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "DELETE FROM CustomerAddressEntity WHERE customerAddressId = :customer_address_id";

            session.createQuery(queryHQL, CustomerAddressEntity.class)
                    .setParameter("customer_address_id", customerAddressId)
                    .executeUpdate();
        }
    }

    @Override
    public void deleteCustomerAddressesByCustomerId(Long customerId) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "DELETE FROM CustomerAddressEntity c WHERE c.customer.customerId = :customer_id";

            session.createQuery(queryHQL, CustomerAddressEntity.class)
                    .setParameter("customer_id", customerId)
                    .executeUpdate();
        }
    }


    @Override
    public boolean existsDefaultCustomerAddressByCustomerId(Long customerId) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "SELECT COUNT(c) FROM CustomerAddressEntity c WHERE c.customer.customerId = :customer_id AND c.isDefault = true";

            Integer count = session.createQuery(queryHQL, Integer.class)
                    .setParameter("customer_id", customerId)
                    .uniqueResult();

            return count > 0;
        }
    }

    @Override
    public long countCustomerAddressesByCustomerId(Long customerId) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "SELECT COUNT(c) FROM CustomerAddressEntity c WHERE c.customer.customerId = :customer_id";;

            Long count = session.createQuery(queryHQL, Long.class)
                    .setParameter("customer_id", customerId)
                    .uniqueResult();

            return count;
        }
    }
}
