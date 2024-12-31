package pl.yummy.infrastructure.database.repositoryAbsolete;

import org.hibernate.Session;
import pl.yummy.business.daoAbsolete.ZBillingInformationDAO;
import pl.yummy.infrastructure.configuration.HibernateUtil;
import pl.yummy.infrastructure.database.entity.AddressEntity;
import pl.yummy.infrastructure.database.entity.BillingInformationEntity;
import pl.yummy.infrastructure.database.entity.UserAuthEntity;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ZBillingInformationRepository implements ZBillingInformationDAO {
    @Override
    public BillingInformationEntity createBillingInformation(BillingInformationEntity billingInformation) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            session.persist(billingInformation);
            session.getTransaction().commit();
            return billingInformation;
        }
    }


    @Override
    public List<BillingInformationEntity> findBillingInformationByCustomerId(Long customerId) {
        try(Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "FROM BillingInformationEntity b WHERE b.customer.customerId = :customer_id";
            List<BillingInformationEntity> result = session.createQuery(queryHQL, BillingInformationEntity.class)
                    .setParameter("customer_id", customerId)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public Optional<BillingInformationEntity> findBillingInformationById(Long billingInformationId) {
        try(Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "SELECT b FROM BillingInformationEntity b WHERE b.billingInformationId = :billingInformation_id";
            Optional<BillingInformationEntity> result = session.createQuery(queryHQL, BillingInformationEntity.class)
                    .setParameter("billingInformation_id", billingInformationId)
                    .uniqueResultOptional();

            session.getTransaction().commit();
            return result;
        }
    }


    @Override
    public List<BillingInformationEntity> findBillingInformationByCompanyName(String companyName) {
        try(Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "FROM BillingInformationEntity b WHERE b.companyName = :company_name";
            List<BillingInformationEntity> result = session.createQuery(queryHQL, BillingInformationEntity.class)
                    .setParameter("company_name", companyName)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }


    @Override
    public List<BillingInformationEntity> findBillingInformationByAddressId(Long addressId) {
        try(Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "FROM BillingInformationEntity b WHERE b.address.addressId = :address_id";
            List<BillingInformationEntity> result = session.createQuery(queryHQL, BillingInformationEntity.class)
                    .setParameter("address_id", addressId)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }


    @Override
    public List<BillingInformationEntity> findBillingInformationByVatRange(String startVat, String endVat) {
        try(Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "FROM BillingInformationEntity b WHERE b.vatNumber BETWEEN :start_vat AND :end_vat";
            List<BillingInformationEntity> result = session.createQuery(queryHQL, BillingInformationEntity.class)
                    .setParameter("start_vat", startVat)
                    .setParameter("end_vat", endVat)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<BillingInformationEntity> findRecentBillingInformations(int limit) {
        try(Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "FROM BillingInformationEntity b ORDER BY b.billingInformationId DESC";
            List<BillingInformationEntity> result = session.createQuery(queryHQL, BillingInformationEntity.class)
                    .setMaxResults(limit)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<BillingInformationEntity> findAllBillingInformations() {
        try(Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "FROM BillingInformationEntity";
            List<BillingInformationEntity> result = session.createQuery(queryHQL, BillingInformationEntity.class)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }


    @Override
    public BillingInformationEntity updateBillingInformation(BillingInformationEntity billingInformation) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            BillingInformationEntity merge = session.merge(billingInformation);
            session.getTransaction().commit();
            return merge;
        }
    }

    @Override
    public BillingInformationEntity updateBillingAddress(Long billingInformationId, AddressEntity newAddress) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "UPDATE BillingInformationEntity b " +
                    "SET b.address = :newAddress " +
                    "WHERE b.billingInformationId = :billing_information_id";

            session.createQuery(queryHQL, UserAuthEntity.class)
                    .setParameter("address", newAddress)
                    .setParameter("billing_information_id", billingInformationId)
                    .executeUpdate();

            session.getTransaction().commit();
            BillingInformationEntity billingInformation = session.get(BillingInformationEntity.class, billingInformationId);

            return billingInformation;
        }
    }

    @Override
    public void deleteBillingInformation(Long billingInformationId) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "DELETE FROM BillingInformationEntity WHERE billingInformationId = :billing_information_id";

            session.createQuery(queryHQL, BillingInformationEntity.class)
                    .setParameter("billing_information_id", billingInformationId)
                    .executeUpdate();
        }
    }

    @Override
    public void deleteBillingInformationsByCustomerId(Long customerId) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "DELETE FROM BillingInformationEntity b WHERE b.customer.customerId = :customer_id";

            session.createQuery(queryHQL, BillingInformationEntity.class)
                    .setParameter("customer_id", customerId)
                    .executeUpdate();
        }
    }

    @Override
    public boolean existsBillingInformationByVatNumber(String vatNumber) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "SELECT COUNT(b) FROM BillingInformationEntity b WHERE b.vatNumber = :vat_number";

            Integer count = session.createQuery(queryHQL, Integer.class)
                    .setParameter("vat_number", vatNumber)
                    .uniqueResult();

            return count > 0;
        }
    }

    @Override
    public boolean existsBillingInformationByCompanyName(String companyName) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "SELECT COUNT(b) FROM BillingInformationEntity b WHERE b.companyName = :company_name";

            Integer count = session.createQuery(queryHQL, Integer.class)
                    .setParameter("company_name", companyName)
                    .uniqueResult();

            return count > 0;
        }
    }

    @Override
    public long countBillingInformations() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "SELECT COUNT(b) FROM BillingInformationEntity b";

            Long count = session.createQuery(queryHQL, Long.class)
                    .uniqueResult();

            return count;
        }
    }

}
