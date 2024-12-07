package pl.yummy.infrastructure.database.repository;

import org.hibernate.Session;
import pl.yummy.business.dao.RestaurantDAO;
import pl.yummy.infrastructure.configuration.HibernateUtil;
import pl.yummy.infrastructure.database.entity.CustomerEntity;
import pl.yummy.infrastructure.database.entity.RestaurantEntity;
import pl.yummy.infrastructure.database.entity.enums.CuisineType;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class RestaurantRepository implements RestaurantDAO {
    @Override
    public RestaurantEntity createRestaurant(RestaurantEntity restaurant) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            session.persist(restaurant);
            session.getTransaction().commit();
            return restaurant;
        }
    }

    @Override
    public Optional<RestaurantEntity> findRestaurantById(Long restaurantId) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "SELECT r FROM RestaurantEntity r WHERE r.restaurantId = :restaurant_id";
            Optional<RestaurantEntity> result = session.createQuery(queryHQL, RestaurantEntity.class)
                    .setParameter("restaurant_id", restaurantId)
                    .uniqueResultOptional();

            session.getTransaction().commit();
            return result;
        }
    }


    @Override
    public List<RestaurantEntity> findRestaurantsByCuisineType(List<String> cuisineTypes) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            Collection<CuisineType> enumCuisineTypes = cuisineTypes.stream()
                    .map(type -> CuisineType.valueOf(type.toUpperCase()))
                    .toList();
            String queryHQL = "SELECT r FROM RestaurantEntity r WHERE r.cuisineType IN :cuisineTypes";

            List<RestaurantEntity> result = session.createQuery(queryHQL, RestaurantEntity.class)
                    .setParameter("cuisineTypes", enumCuisineTypes)
                    .list();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<RestaurantEntity> findRestaurantsByCity(String city) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "SELECT r FROM RestaurantEntity r " +
                    "JOIN FETCH r.address a " +
                    "WHERE a.city = :city";
            List<RestaurantEntity> result = session.createQuery(queryHQL, RestaurantEntity.class)
                    .setParameter("city", city)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<RestaurantEntity> findRestaurantsByStreet(String street) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "SELECT r FROM RestaurantEntity r WHERE r.address.street LIKE :street";
            List<RestaurantEntity> result = session.createQuery(queryHQL, RestaurantEntity.class)
                    .setParameter("street", "%" + street + "%")
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<RestaurantEntity> findAllRestaurants() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String queryHQL = "FROM RestaurantEntity";
            List<RestaurantEntity> result = session.createQuery(queryHQL, RestaurantEntity.class)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public void updateRestaurant(RestaurantEntity restaurant) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            session.merge(restaurant);
            session.getTransaction().commit();

        }
    }

    @Override
    public void deleteRestaurant(Long restaurantId) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "DELETE FROM RestaurantEntity WHERE restaurantId = :restaurant_id";
            session.createQuery(queryHQL, RestaurantEntity.class)
                    .setParameter("restaurant_id", restaurantId)
                    .executeUpdate();
        }
    }
}
