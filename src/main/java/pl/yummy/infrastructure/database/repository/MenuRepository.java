package pl.yummy.infrastructure.database.repository;

import org.hibernate.Session;
import pl.yummy.business.dao.MenuDAO;
import pl.yummy.infrastructure.configuration.HibernateUtil;
import pl.yummy.infrastructure.database.entity.MenuEntity;
import pl.yummy.infrastructure.database.entity.RestaurantEntity;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class MenuRepository implements MenuDAO {

    @Override
    public MenuEntity createMenu(MenuEntity menu) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            session.persist(menu);
            session.getTransaction().commit();
            return menu;
        }
    }

    @Override
    public Optional<MenuEntity> findMenuById(Long menuId) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "SELECT m FROM MenuEntity m WHERE m.menuId = :menu_id";
            Optional<MenuEntity> result = session.createQuery(queryHQL, MenuEntity.class)
                    .setParameter("menu_id", menuId)
                    .uniqueResultOptional();

            session.getTransaction().commit();
            return result;
        }
    }


    @Override
    public List<MenuEntity> findMenusByRestaurantId(Long restaurantId) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "SELECT m FROM MenuEntity m JOIN FETCH m.restaurant r WHERE r.restaurantId = :restaurant_id";
            List<MenuEntity> result = session.createQuery(queryHQL, MenuEntity.class)
                    .setParameter("restaurant_id", restaurantId)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<MenuEntity> findAllMenus() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "FROM MenuEntity";
            List<MenuEntity> result = session.createQuery(queryHQL, MenuEntity.class)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public void updateMenu(MenuEntity menu) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            session.merge(menu);
            session.getTransaction().commit();

        }
    }

    @Override
    public void deleteMenu(Long menuId) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "DELETE FROM MenuEntity WHERE menuId = :menu_id";
            session.createQuery(queryHQL, MenuEntity.class)
                    .setParameter("menu_id", menuId)
                    .executeUpdate();
        }
    }
}
