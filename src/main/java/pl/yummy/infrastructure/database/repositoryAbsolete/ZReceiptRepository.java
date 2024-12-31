package pl.yummy.infrastructure.database.repositoryAbsolete;

import org.hibernate.Session;
import pl.yummy.business.daoAbsolete.ZReceiptDAO;
import pl.yummy.infrastructure.configuration.HibernateUtil;
import pl.yummy.infrastructure.database.entity.ReceiptEntity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ZReceiptRepository implements ZReceiptDAO {

    @Override
    public ReceiptEntity createReceipt(ReceiptEntity receipt) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            session.persist(receipt);
            session.getTransaction().commit();
            return receipt;
        }
    }

    @Override
    public Optional<ReceiptEntity> findReceiptById(Long receiptId) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "SELECT r FROM ReceiptEntity r WHERE r.receiptId = :receipt_id";
            Optional<ReceiptEntity> result = session.createQuery(queryHQL, ReceiptEntity.class)
                    .setParameter("receipt_id", receiptId)
                    .uniqueResultOptional();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<ReceiptEntity> findAllReceipts() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "FROM ReceiptEntity";
            List<ReceiptEntity> result = session.createQuery(queryHQL, ReceiptEntity.class)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public Optional<ReceiptEntity> findReceiptByReceiptNumber(String receiptNumber) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "FROM ReceiptEntity r WHERE r.receiptNumber = :receipt_number";
            Optional<ReceiptEntity> result = session.createQuery(queryHQL, ReceiptEntity.class)
                    .setParameter("receipt_number", receiptNumber)
                    .uniqueResultOptional();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<ReceiptEntity> findReceiptsByOrderId(Long ordersId) {
        try(Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "FROM ReceiptEntity r WHERE r.orders.ordersId = :orders_id";
            List<ReceiptEntity> result = session.createQuery(queryHQL, ReceiptEntity.class)
                    .setParameter("orders_id", ordersId)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<ReceiptEntity> findReceiptsByIssueDateRange(OffsetDateTime startDate, OffsetDateTime endDate) {
        try(Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "FROM ReceiptEntity r WHERE r.issueDate BETWEEN :startDate AND :endDate";
            List<ReceiptEntity> result = session.createQuery(queryHQL, ReceiptEntity.class)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }


    @Override
    public ReceiptEntity updateReceipt(ReceiptEntity receipt) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            ReceiptEntity merge = session.merge(receipt);
            session.getTransaction().commit();
            return merge;
        }
    }

    @Override
    public void deleteReceipt(Long receiptId) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "DELETE FROM ReceiptEntity WHERE receiptId = :receipt_id";

            session.createQuery(queryHQL, ReceiptEntity.class)
                    .setParameter("receipt_id", receiptId)
                    .executeUpdate();
        }
    }

    @Override
    public boolean existsReceiptByReceiptNumber(String receiptNumber) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "SELECT COUNT(r) FROM ReceiptEntity r WHERE r.receiptNumber = :receipt_number";

            Integer count = session.createQuery(queryHQL, Integer.class)
                    .setParameter("receipt_number", receiptNumber)
                    .uniqueResult();

            return count > 0;
        }
    }


    @Override
    public BigDecimal calculateTotalTaxAmountByDateRange(OffsetDateTime startDate, OffsetDateTime endDate) {
        try(Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "SELECT SUM(r.taxAmount) FROM ReceiptEntity r WHERE r.issueDate BETWEEN :startDate AND :endDate";
            BigDecimal result = session.createQuery(queryHQL, BigDecimal.class)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .uniqueResult();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public BigDecimal countReceiptsByDateRange(OffsetDateTime startDate, OffsetDateTime endDate) {
        try(Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "SELECT COUNT(r) FROM ReceiptEntity r WHERE r.issueDate BETWEEN :startDate AND :endDate";
            BigDecimal result = session.createQuery(queryHQL, BigDecimal.class)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .uniqueResult();

            session.getTransaction().commit();
            return result;
        }
    }
}

