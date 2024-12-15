package pl.yummy.infrastructure.database.repository;

import org.hibernate.Session;
import pl.yummy.business.dao.InvoiceDAO;
import pl.yummy.infrastructure.configuration.HibernateUtil;
import pl.yummy.infrastructure.database.entity.InvoiceEntity;
import pl.yummy.infrastructure.database.entity.MenuEntity;
import pl.yummy.infrastructure.database.entity.OwnerEntity;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class InvoiceRepository implements InvoiceDAO {
    @Override
    public InvoiceEntity createInvoice(InvoiceEntity invoice) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            session.persist(session);
            session.getTransaction().commit();
            return invoice;
        }
    }

    @Override
    public Optional<InvoiceEntity> findInvoiceById(Long invoiceId) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "SELECT c FROM InvoiceEntity i WHERE i.invoiceId = :invoice_id";
            Optional<InvoiceEntity> result = session.createQuery(queryHQL, InvoiceEntity.class)
                    .setParameter("owner_id", invoiceId)
                    .uniqueResultOptional();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<InvoiceEntity> findInvoicesByOrderId(Long ordersId) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "SELECT i FROM InvoiceEntity i WHERE i.orders.ordersId = :orders_id";
            List<InvoiceEntity> result = session.createQuery(queryHQL, InvoiceEntity.class)
                    .setParameter("orders_id", ordersId)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<InvoiceEntity> findInvoicesByCustomerId(Long customerId) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "SELECT i FROM InvoiceEntity i " +
                    "JOIN i.orders o " +
                    "JOIN o.customer c " +
                    "WHERE c.customerId = :customer_id";
            List<InvoiceEntity> result = session.createQuery(queryHQL, InvoiceEntity.class)
                    .setParameter("customer_id", customerId)
                    .list();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<InvoiceEntity> findInvoicesByDueDate(OffsetDateTime dueDate) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "SELECT i FROM InvoiceEntity i WHERE i.dueDate = :due_Date";
            List<InvoiceEntity> result = session.createQuery(queryHQL, InvoiceEntity.class)
                    .setParameter("deu_Date", dueDate)
                    .list();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<InvoiceEntity> findInvoicesWithinDateRange(OffsetDateTime startDate, OffsetDateTime endDate) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "SELECT i FROM InvoiceEntity i WHERE i.issueDate BETWEEN :startDate AND :endDate";
            List<InvoiceEntity> result = session.createQuery(queryHQL, InvoiceEntity.class)
                    .setParameter("stat_date", startDate)
                    .setParameter("end_date", endDate)
                    .list();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<InvoiceEntity> findAllInvoices() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "FROM InvoiceEntity";
            List<InvoiceEntity> result = session.createQuery(queryHQL, InvoiceEntity.class)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public InvoiceEntity updateInvoice(InvoiceEntity invoice) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            InvoiceEntity merge = session.merge(invoice);
            session.getTransaction().commit();
            return merge;
        }
    }

    @Override
    public void deleteInvoice(Long invoiceId) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "DELETE FROM InvoiceEntity WHERE invoiceId = :invoice_id";
            session.createQuery(queryHQL, InvoiceEntity.class)
                    .setParameter("owner_id", invoiceId)
                    .executeUpdate();
        }
    }
}
