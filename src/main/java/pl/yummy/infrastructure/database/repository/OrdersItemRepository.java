package pl.yummy.infrastructure.database.repository;

import org.hibernate.Session;
import pl.yummy.business.dao.OrdersItemDAO;
import pl.yummy.infrastructure.configuration.HibernateUtil;
import pl.yummy.infrastructure.database.entity.OrdersItemEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class OrdersItemRepository implements OrdersItemDAO {


    @Override
    public OrdersItemEntity createOrdersItem(OrdersItemEntity ordersItem) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            session.persist(ordersItem);
            session.getTransaction().commit();
            return ordersItem;
        }
    }

    @Override
    public Optional<OrdersItemEntity> findOrdersItemById(Long ordersItemId) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "SELECT o FROM OrdersItemEntity o WHERE o.ordersItemId = :orders_item_id";
            Optional<OrdersItemEntity> result = session.createQuery(queryHQL, OrdersItemEntity.class)
                    .setParameter("orders_item_id", ordersItemId)
                    .uniqueResultOptional();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<OrdersItemEntity> findAllOrdersItems() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "FROM OrdersItemEntity";
            List<OrdersItemEntity> result = session.createQuery(queryHQL, OrdersItemEntity.class)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }


    @Override
    public List<OrdersItemEntity> findOrdersItemsByOrderId(Long ordersId) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "FROM OrdersItemEntity o WHERE o.orders.ordersId = :orders_id";
            List<OrdersItemEntity> result = session.createQuery(queryHQL, OrdersItemEntity.class)
                    .setParameter("orders_id", ordersId)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<OrdersItemEntity> findOrdersItemsByMenuItemId(Long menuItemId) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "FROM OrdersItemEntity o WHERE o.menuItem.menuItemId = :menu_item_id";
            List<OrdersItemEntity> result = session.createQuery(queryHQL, OrdersItemEntity.class)
                    .setParameter("menu_item_id", menuItemId)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }


    @Override
    public List<OrdersItemEntity> findOrdersItemsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "FROM OrdersItemEntity o WHERE o.unitPrice BETWEEN :minPrice AND :maxPrice";
            List<OrdersItemEntity> result = session.createQuery(queryHQL, OrdersItemEntity.class)
                    .setParameter("minPrice", minPrice)
                    .setParameter("maxPrice", maxPrice)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public OrdersItemEntity updateOrdersItem(OrdersItemEntity ordersItem) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            OrdersItemEntity merge = session.merge(ordersItem);
            session.getTransaction().commit();
            return merge;
        }
    }

    @Override
    public void deleteOrdersItem(Long ordersItemId) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "DELETE FROM OrdersItemEntity WHERE OrdersItemId = :orders_item_id";

            session.createQuery(queryHQL, OrdersItemEntity.class)
                    .setParameter("orders_item_id", ordersItemId)
                    .executeUpdate();
        }
    }

    @Override
    public long countOrdersItemsByOrderId(Long ordersId) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "SELECT COUNT(o) FROM OrdersItemEntity o WHERE o.orders.ordersId = :orders_id";
            ;

            Long count = session.createQuery(queryHQL, Long.class)
                    .setParameter("orders_id", ordersId)
                    .uniqueResult();

            return count;
        }
    }

    @Override
    public BigDecimal calculateTotalPriceByOrderId(Long ordersId) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "SELECT SUM(o.totalPrice) FROM OrdersItemEntity o WHERE o.orders.ordersId = :orders_id";
            ;

            BigDecimal count = session.createQuery(queryHQL, BigDecimal.class)
                    .setParameter("orders_id", ordersId)
                    .uniqueResult();

            return count;
        }
    }


}
