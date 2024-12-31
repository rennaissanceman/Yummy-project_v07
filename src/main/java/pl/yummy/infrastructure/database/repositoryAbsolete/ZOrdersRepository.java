package pl.yummy.infrastructure.database.repositoryAbsolete;

import org.hibernate.Session;
import pl.yummy.business.daoAbsolete.ZOrdersDAO;
import pl.yummy.infrastructure.configuration.HibernateUtil;
import pl.yummy.infrastructure.database.entity.OrdersEntity;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ZOrdersRepository implements ZOrdersDAO {


    @Override
    public OrdersEntity createOrders(OrdersEntity order) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            session.persist(order);
            session.getTransaction().commit();
            return order;
        }
    }



    @Override
    public Optional<OrdersEntity> findOrdersById(Long ordersId) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "SELECT o FROM OrdersEntity o WHERE o.ordersId = :orders_id";
            Optional<OrdersEntity> result = session.createQuery(queryHQL, OrdersEntity.class)
                    .setParameter("orders_id", ordersId)
                    .uniqueResultOptional();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<OrdersEntity> findOrdersByCustomerId(Long customerId) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "SELECT o FROM OrdersEntity o JOIN FETCH o.customer c WHERE c.customerId = :customer_id";
            List<OrdersEntity> result = session.createQuery(queryHQL, OrdersEntity.class)
                    .setParameter("customer_id", customerId)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public void updateOrder(OrdersEntity order) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            session.merge(order);
            session.getTransaction().commit();
        }
    }


    @Override
    public void deleteOrder(Long orderId) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "DELETE FROM OrdersEntity WHERE ordersId = :orders_id";
            session.createQuery(queryHQL, OrdersEntity.class)
                    .setParameter("orders_id", orderId)
                    .executeUpdate();
        }
    }
}
