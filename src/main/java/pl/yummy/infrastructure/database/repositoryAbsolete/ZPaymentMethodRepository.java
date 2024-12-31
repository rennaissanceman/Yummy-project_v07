package pl.yummy.infrastructure.database.repositoryAbsolete;

import org.hibernate.Session;
import pl.yummy.business.daoAbsolete.ZPaymentMethodDAO;
import pl.yummy.infrastructure.configuration.HibernateUtil;
import pl.yummy.infrastructure.database.entity.OrdersItemEntity;
import pl.yummy.infrastructure.database.entity.PaymentMethodEntity;
import pl.yummy.infrastructure.database.entity.enums.PaymentMethodStatus;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ZPaymentMethodRepository implements ZPaymentMethodDAO {



    @Override
    public PaymentMethodEntity createPaymentMethod(PaymentMethodEntity paymentMethod) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            session.persist(paymentMethod);
            session.getTransaction().commit();
            return paymentMethod;
        }
    }

    @Override
    public Optional<PaymentMethodEntity> findPaymentMethodById(Long paymentMethodId) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "SELECT p FROM PaymentMethodEntity p WHERE p.paymentMethodId = :payment_method_id";
            Optional<PaymentMethodEntity> result = session.createQuery(queryHQL, PaymentMethodEntity.class)
                    .setParameter("payment_method_id", paymentMethodId)
                    .uniqueResultOptional();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<PaymentMethodEntity> findAllPaymentMethods() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "FROM PaymentMethodEntity";
            List<PaymentMethodEntity> result = session.createQuery(queryHQL, PaymentMethodEntity.class)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<PaymentMethodEntity> findPaymentMethodsByActiveStatus(Boolean isActive) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "FROM PaymentMethodEntity p WHERE p.isActive = :is_active";
            List<PaymentMethodEntity> result = session.createQuery(queryHQL, PaymentMethodEntity.class)
                    .setParameter("is_active", isActive)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<PaymentMethodEntity> findPaymentMethodsByStatus(PaymentMethodStatus paymentMethodStatus) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "FROM PaymentMethodEntity p WHERE p.paymentMethodStatus = :payment_method_status";
            List<PaymentMethodEntity> result = session.createQuery(queryHQL, PaymentMethodEntity.class)
                    .setParameter("payment_method_status", paymentMethodStatus)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public PaymentMethodEntity updatePaymentMethod(PaymentMethodEntity paymentMethod) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            PaymentMethodEntity merge = session.merge(paymentMethod);
            session.getTransaction().commit();
            return merge;
        }
    }

    @Override
    public void deletePaymentMethod(Long paymentMethodId) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "DELETE FROM PaymentMethodEntity WHERE PaymentMethodId = :payment_method_id";

            session.createQuery(queryHQL, OrdersItemEntity.class)
                    .setParameter("payment_method_id", paymentMethodId)
                    .executeUpdate();
        }
    }

    @Override
    public boolean existsPaymentMethodByName(String paymentMethodName) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "SELECT COUNT(p) FROM PaymentMethodEntity p WHERE p.paymentMethodName = :payment_method_name";

            Integer count = session.createQuery(queryHQL, Integer.class)
                    .setParameter("payment_method_name", paymentMethodName)
                    .uniqueResult();

            return count > 0;
        }
    }

    @Override
    public long countActivePaymentMethods() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "SELECT COUNT(p) FROM PaymentMethodEntity p WHERE p.isActive = true";
            ;

            Long count = session.createQuery(queryHQL, Long.class)
                    .uniqueResult();

            return count;
        }
    }
}
