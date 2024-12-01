package pl.yummy.infrastructure.database.repository;

import org.hibernate.Session;
import pl.yummy.business.dao.CustomerDAO;
import pl.yummy.infrastructure.configuration.HibernateUtil;
import pl.yummy.infrastructure.database.entity.CustomerEntity;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CustomerRepository implements CustomerDAO {


    @Override
    public Optional<CustomerEntity> findById(Integer customerId) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction().commit();

            String query = "SELECT c FROM CustomerEntity c WHERE c.customerId = :customer_id";
            Optional<CustomerEntity> result = session.createQuery(query, CustomerEntity.class)
                    .setParameter("customer_id", customerId)
                    .uniqueResultOptional();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public Optional<CustomerEntity> findByCustomerNumber(String customerNumber) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction().commit();

            String query = "SELECT c FROM CustomerEntity c WHERE c.customerNumber = :customer_number";
            Optional<CustomerEntity> result = session.createQuery(query, CustomerEntity.class)
                    .setParameter("customer_number", customerNumber)
                    .uniqueResultOptional();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public Optional<CustomerEntity> findByEmail(String email) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String query = "SELECT c FROM CustomerEntity c WHERE c.userAuth.email = :email";
            Optional<CustomerEntity> result = session.createQuery(query, CustomerEntity.class)
                    .setParameter("email", email)
                    .uniqueResultOptional();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public Optional<CustomerEntity> findByPhone(String phone) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String query = "SELECT c FROM CustomerEntity c WHERE c.userAuth.phone = :phone";
            Optional<CustomerEntity> result = session.createQuery(query, CustomerEntity.class)
                    .setParameter("phone", phone)
                    .uniqueResultOptional();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<CustomerEntity> findByCompanyName(String companyName) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String query = "SELECT c FROM CustomerEntity c WHERE c.companyName = :company_name";
            List<CustomerEntity> result = session.createQuery(query, CustomerEntity.class)
                    .setParameter("company_name", companyName)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<CustomerEntity> findByCustomerSurname(String customerSurname) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String query = "SELECT c FROM CustomerEntity c WHERE c.customerSurname = :customer_surname";
            List<CustomerEntity> result = session.createQuery(query, CustomerEntity.class)
                    .setParameter("customer_surname", customerSurname)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<CustomerEntity> findByVatNumber(String vatNumber) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String query = "SELECT c FROM CustomerEntity c WHERE c.billingInformation.vatNumber = :vat_number";
            List<CustomerEntity> result = session.createQuery(query, CustomerEntity.class)
                    .setParameter("vat_number", vatNumber)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<CustomerEntity> findByCity(String city) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String query = "SELECT c FROM CustomerEntity c " +
                    "JOIN c.customerAddress ca " +
                    "JOIN ca.address a " +
                    "WHERE a.city = :city";
            List<CustomerEntity> result = session.createQuery(query, CustomerEntity.class)
                    .setParameter("city", city)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<CustomerEntity> findByStreet(String street) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String query = "SELECT c FROM CustomerEntity c " +
                    "JOIN c.customerAddress ca " +
                    "JOIN ca.address a " +
                    "WHERE a.street = :street";
            List<CustomerEntity> result = session.createQuery(query, CustomerEntity.class)
                    .setParameter("street", street)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<CustomerEntity> findAll() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String query = "FROM CustomerEntity";
            List<CustomerEntity> result = session.createQuery(query, CustomerEntity.class)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public CustomerEntity saveCustomer(CustomerEntity entity) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
            return entity;
        }
    }

    @Override
    public void deleteById(Integer customerId) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "DELETE FROM CustomerEntity WHERE customerId = :customer_id";
            session.createQuery(queryHQL, CustomerEntity.class)
                    .setParameter("customer_id", customerId)
                    .executeUpdate();
        }
    }


}
