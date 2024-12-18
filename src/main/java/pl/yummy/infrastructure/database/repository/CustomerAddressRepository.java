package pl.yummy.infrastructure.database.repository;

import org.hibernate.Session;
import pl.yummy.business.dao.CustomerAddressDAO;
import pl.yummy.infrastructure.configuration.HibernateUtil;
import pl.yummy.infrastructure.database.entity.AvailableDeliveryAreaEntity;
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
        return null;
    }

    @Override
    public List<CustomerAddressEntity> findCustomerAddressesByCustomerId(Long customerId) {
        return null;
    }

    @Override
    public Optional<CustomerAddressEntity> findDefaultCustomerAddressByCustomerId(Long customerId) {
        return Optional.empty();
    }

    @Override
    public CustomerAddressEntity updateCustomerAddress(CustomerAddressEntity customerAddress) {
        return null;
    }

    @Override
    public void deleteCustomerAddress(Long customerAddressId) {

    }

    @Override
    public void deleteCustomerAddressesByCustomerId(Long customerId) {

    }

    @Override
    public boolean existsDefaultCustomerAddressByCustomerId(Long customerId) {
        return false;
    }

    @Override
    public long countCustomerAddressesByCustomerId(Long customerId) {
        return 0;
    }

    //----------------------------------------------------------------------



        @Override
        public CustomerAddressEntity updateCustomerAddress(CustomerAddressEntity customerAddress) {
            Transaction transaction = null;
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();
                CustomerAddressEntity updatedAddress = (CustomerAddressEntity) session.merge(customerAddress);
                transaction.commit();
                return updatedAddress;
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                e.printStackTrace();
                throw new RuntimeException("Error updating customer address", e);
            }
        }



        @Override
        public List<CustomerAddressEntity> getAllCustomerAddresses() {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                String hql = "FROM CustomerAddressEntity";
                return session.createQuery(hql, CustomerAddressEntity.class).list();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Error retrieving all customer addresses", e);
            }
        }

        @Override
        public List<CustomerAddressEntity> findCustomerAddressesByCustomerId(Integer customerId) {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                String hql = "FROM CustomerAddressEntity c WHERE c.customer.customerId = :customerId";
                Query<CustomerAddressEntity> query = session.createQuery(hql, CustomerAddressEntity.class);
                query.setParameter("customerId", customerId);
                return query.list();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Error finding customer addresses by customer ID", e);
            }
        }

        @Override
        public Optional<CustomerAddressEntity> findDefaultCustomerAddressByCustomerId(Integer customerId) {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                String hql = "FROM CustomerAddressEntity c WHERE c.customer.customerId = :customerId AND c.isDefault = true";
                Query<CustomerAddressEntity> query = session.createQuery(hql, CustomerAddressEntity.class);
                query.setParameter("customerId", customerId);
                return query.uniqueResultOptional();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Error finding default customer address by customer ID", e);
            }
        }

        @Override
        public void deleteCustomerAddress(Integer customerAddressId) {
            Transaction transaction = null;
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();
                CustomerAddressEntity address = session.get(CustomerAddressEntity.class, customerAddressId);
                if (address != null) {
                    session.delete(address);
                } else {
                    throw new RuntimeException("CustomerAddressEntity with ID " + customerAddressId + " does not exist.");
                }
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                e.printStackTrace();
                throw new RuntimeException("Error deleting customer address", e);
            }
        }

        @Override
        public void deleteCustomerAddressesByCustomerId(Integer customerId) {
            Transaction transaction = null;
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();
                String hql = "DELETE FROM CustomerAddressEntity c WHERE c.customer.customerId = :customerId";
                Query query = session.createQuery(hql);
                query.setParameter("customerId", customerId);
                query.executeUpdate();
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                e.printStackTrace();
                throw new RuntimeException("Error deleting customer addresses by customer ID", e);
            }
        }

        @Override
        public boolean existsDefaultCustomerAddressByCustomerId(Integer customerId) {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                String hql = "SELECT COUNT(c) FROM CustomerAddressEntity c WHERE c.customer.customerId = :customerId AND c.isDefault = true";
                Long count = session.createQuery(hql, Long.class)
                        .setParameter("customerId", customerId)
                        .uniqueResult();
                return count > 0;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Error checking existence of default customer address by customer ID", e);
            }
        }

        @Override
        public long countCustomerAddressesByCustomerId(Integer customerId) {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                String hql = "SELECT COUNT(c) FROM CustomerAddressEntity c WHERE c.customer.customerId = :customerId";
                Long count = session.createQuery(hql, Long.class)
                        .setParameter("customerId", customerId)
                        .uniqueResult();
                return count != null ? count : 0L;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Error counting customer addresses by customer ID", e);
            }
        }



}
