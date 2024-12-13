package pl.yummy.infrastructure.database.repository;

import org.hibernate.Session;
import pl.yummy.business.dao.PaymentDAO;
import pl.yummy.infrastructure.configuration.HibernateUtil;
import pl.yummy.infrastructure.database.entity.DeliveryEntity;
import pl.yummy.infrastructure.database.entity.PaymentEntity;
import pl.yummy.infrastructure.database.entity.enums.DeliveryStatus;
import pl.yummy.infrastructure.database.entity.enums.PaymentStatus;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PaymentRepository implements PaymentDAO {

    @Override
    public PaymentEntity createPayment(PaymentEntity payment) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            session.persist(payment);
            session.getTransaction().commit();
            return payment;
        }
    }

    @Override
    public Optional<PaymentEntity> findPaymentById(Long paymentId) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "SELECT p FROM PaymentEntity p WHERE p.paymentId = :payment_id";
            Optional<PaymentEntity> result = session.createQuery(queryHQL, PaymentEntity.class)
                    .setParameter("payment_id", paymentId)
                    .uniqueResultOptional();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public Optional<PaymentEntity> findPaymentByOrder(Long ordersId) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "SELECT p FROM PaymentEntity p JOIN FETCH p.orders o WHERE o.ordersId = :orders_id";
            Optional<PaymentEntity> result = session.createQuery(queryHQL, PaymentEntity.class)
                    .setParameter("orders_id", ordersId)
                    .uniqueResultOptional();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<PaymentEntity> findPaymentByStatus(List<String> paymentStatus) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            List<PaymentStatus> paymentStatusList = paymentStatus.stream()
                    .map(status -> PaymentStatus.valueOf(status.toUpperCase()))
                    .toList();

            String queryHQL = "SELECT p FROM PaymentEntity p WHERE p.paymentStatus IN :paymentStatuses";
            List<PaymentEntity> result = session.createQuery(queryHQL, PaymentEntity.class)
                    .setParameter("paymentStatuses", paymentStatusList)
                    .list();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public PaymentEntity updatePayment(PaymentEntity payment) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            PaymentEntity merge = session.merge(payment);
            session.getTransaction().commit();
            return merge;
        }
    }

    @Override
    public void deletePayment(Long paymentId) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "DELETE FROM PaymentEntity WHERE paymentId = :payment_id";
            session.createQuery(queryHQL, PaymentEntity.class)
                    .setParameter("payment_id", paymentId)
                    .executeUpdate();
        }
    }
}
