package pl.yummy.infrastructure.database.repository;

import org.hibernate.Session;
import pl.yummy.business.dao.MenuItemDAO;
import pl.yummy.infrastructure.configuration.HibernateUtil;
import pl.yummy.infrastructure.database.entity.BillingInformationEntity;
import pl.yummy.infrastructure.database.entity.CustomerAddressEntity;
import pl.yummy.infrastructure.database.entity.MenuItemEntity;
import pl.yummy.infrastructure.database.entity.enums.DietType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class MenuItemRepository implements MenuItemDAO {

    @Override
    public MenuItemEntity createMenuItem(MenuItemEntity menuItem) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            session.persist(menuItem);
            session.getTransaction().commit();
            return menuItem;
        }
    }

    @Override
    public Optional<MenuItemEntity> findMenuItemById(Long menuItemId) {
        try(Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "SELECT m FROM MenuItemEntity m WHERE m.menuItemId = :menu_item_id";
            Optional<MenuItemEntity> result = session.createQuery(queryHQL, MenuItemEntity.class)
                    .setParameter("menu_item_id", menuItemId)
                    .uniqueResultOptional();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<MenuItemEntity> findMenuItemsByAvailability(Boolean isAvailable) {
        try(Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "FROM MenuItemEntity m WHERE m.isAvailable = :is_available";
            List<MenuItemEntity> result = session.createQuery(queryHQL, MenuItemEntity.class)
                    .setParameter("is_available", isAvailable)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }


    @Override
    public List<MenuItemEntity> findMenuItemsByDietType(DietType dietType) {
        try(Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "FROM MenuItemEntity m WHERE m.dietType = :diet_type";
            List<MenuItemEntity> result = session.createQuery(queryHQL, MenuItemEntity.class)
                    .setParameter("diet_type", dietType)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }


    @Override
    public List<MenuItemEntity> findMenuItemsByMenuId(Long menuId) {
        try(Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "FROM MenuItemEntity m WHERE m.menuId.menuId = :menu_id";
            List<MenuItemEntity> result = session.createQuery(queryHQL, MenuItemEntity.class)
                    .setParameter("menu_id", menuId)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }


    @Override
    public List<MenuItemEntity> findMenuItemsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        try(Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "FROM MenuItemEntity m WHERE m.price BETWEEN :minPrice AND :maxPrice";
            List<MenuItemEntity> result = session.createQuery(queryHQL, MenuItemEntity.class)
                    .setParameter("minPrice", minPrice)
                    .setParameter("maxPrice", maxPrice)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }


    @Override
    public List<MenuItemEntity> findAllMenuItems() {
        try(Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "FROM MenuItemEntity";
            List<MenuItemEntity> result = session.createQuery(queryHQL, MenuItemEntity.class)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public MenuItemEntity updateMenuItem(MenuItemEntity menuItem) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            MenuItemEntity merge = session.merge(menuItem);
            session.getTransaction().commit();
            return merge;
        }
    }


    @Override
    public void deleteMenuItem(Long menuItemId) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "DELETE FROM MenuItemEntity WHERE menuItemId = :menu_item_id";

            session.createQuery(queryHQL, BillingInformationEntity.class)
                    .setParameter("menu_item_id", menuItemId)
                    .executeUpdate();
        }
    }

    @Override
    public boolean existsMenuItemByName(String itemName) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "SELECT COUNT(m) FROM MenuItemEntity m WHERE m.itemName = :item_name";

            Integer count = session.createQuery(queryHQL, Integer.class)
                    .setParameter("item_name", itemName)
                    .uniqueResult();

            return count > 0;
        }
    }

    @Override
    public long countMenuItemsByMenuId(Long menuId) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "SELECT COUNT(m) FROM MenuItemEntity m WHERE m.menuId.menuId = :menu_id";

            Long count = session.createQuery(queryHQL, Long.class)
                    .setParameter("menu_id", menuId)
                    .uniqueResult();

            return count;
        }
    }
}
