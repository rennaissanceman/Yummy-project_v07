package pl.yummy.infrastructure.database.repository;

import org.hibernate.Session;
import pl.yummy.business.dao.CustomerDAO;
import pl.yummy.infrastructure.configuration.HibernateUtil;
import pl.yummy.infrastructure.database.entity.CustomerEntity;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CustomerRepository implements CustomerDAO {
    @Override
    public CustomerEntity createCustomer(CustomerEntity customer) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            session.persist(customer);
            session.getTransaction().commit();
            return customer;
        }
    }

    @Override
    public Optional<CustomerEntity> findCustomerById(Long customerId) {
        try(Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "SELECT c FROM CustomerEntity c WHERE c.customerId = :customer_id";
            Optional<CustomerEntity> result = session.createQuery(queryHQL, CustomerEntity.class)
                    .setParameter("customer_id", customerId)
                    .uniqueResultOptional();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public Optional<CustomerEntity> findCustomerByEmail(String email) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "SELECT c FROM CustomerEntity c WHERE c.userAuth.email = :email";
            Optional<CustomerEntity> result = session.createQuery(queryHQL, CustomerEntity.class)
                    .setParameter("email", email)
                    .uniqueResultOptional();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public Optional<CustomerEntity> findCustomerByPhone(String phone) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "SELECT c FROM CustomerEntity c WHERE c.userAuth.phone = :phone";
            Optional<CustomerEntity> result = session.createQuery(queryHQL, CustomerEntity.class)
                    .setParameter("phone", phone)
                    .uniqueResultOptional();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<CustomerEntity> findCustomerByCompanyName(String companyName) {
        try (Session session= HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "SELECT c FROM CustomerEntity c WHERE c.companyName = :company_name";
            List<CustomerEntity> result = session.createQuery(queryHQL, CustomerEntity.class)
                    .setParameter("company_name", companyName)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<CustomerEntity> findCustomerBySurname(String customerSurname) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "SELECT c FROM CustomerEntity c WHERE c.customerSurname = :customer_surname";
            List<CustomerEntity> result = session.createQuery(queryHQL, CustomerEntity.class)
                    .setParameter("customer_surname", customerSurname)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<CustomerEntity> findCustomerByVatNumber(String vatNumber) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "SELECT c FROM CustomerEntity c WHERE c.billingInformation.vatNumber = :vat_number";
            List<CustomerEntity> result = session.createQuery(queryHQL, CustomerEntity.class)
                    .setParameter("vat_number", vatNumber)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<CustomerEntity> findCustomerByCity(String city) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "SELECT c FROM CustomerEntity c " +
                    "JOIN c.customerAddress ca " +
                    "JOIN ca.address a " +
                    "WHERE a.city = :city";
            List<CustomerEntity> result = session.createQuery(queryHQL, CustomerEntity.class)
                    .setParameter("city", city)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<CustomerEntity> findCustomerByStreet(String street) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "SELECT c FROM CustomerEntity c " +
                    "JOIN c.customerAddress ca " +
                    "JOIN ca.address a " +
                    "WHERE a.street = :street";
            List<CustomerEntity> result = session.createQuery(queryHQL, CustomerEntity.class)
                    .setParameter("street", street)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<CustomerEntity> findAllCustomers() {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "FROM CustomerEntity";
            List<CustomerEntity> result = session.createQuery(queryHQL, CustomerEntity.class)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public void updateCustomer(CustomerEntity customer) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            session.merge(customer);
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteCustomer(Long customerId) {
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
