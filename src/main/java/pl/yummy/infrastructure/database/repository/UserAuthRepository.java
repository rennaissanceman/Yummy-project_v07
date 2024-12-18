package pl.yummy.infrastructure.database.repository;

import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.yummy.business.dao.UserAuthDAO;
import pl.yummy.infrastructure.configuration.HibernateUtil;
import pl.yummy.infrastructure.database.entity.CourierEntity;
import pl.yummy.infrastructure.database.entity.UserAuthEntity;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserAuthRepository implements UserAuthDAO {
    @Override
    public UserAuthEntity createUserAuth(UserAuthEntity userAuth) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            session.persist(session);
            session.getTransaction().commit();
            return userAuth;
        }
    }

    @Override
    public Optional<UserAuthEntity> findUserAuthById(Long userAuthId) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "SELECT u FROM UserAuthEntity u WHERE u.userAuthId = :user_auth_id";
            Optional<UserAuthEntity> result = session.createQuery(queryHQL, UserAuthEntity.class)
                    .setParameter("user_auth_id", userAuthId)
                    .uniqueResultOptional();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public Optional<UserAuthEntity> findUserAuthByEmail(String email) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "SELECT u FROM UserAuthEntity u WHERE u.email = :email";
            Optional<UserAuthEntity> result = session.createQuery(queryHQL, UserAuthEntity.class)
                    .setParameter("email", email)
                    .uniqueResultOptional();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public Optional<UserAuthEntity> findUserAuthByPhone(String phone) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "SELECT u FROM UserAuthEntity u WHERE u.phone = :phone";
            Optional<UserAuthEntity> result = session.createQuery(queryHQL, UserAuthEntity.class)
                    .setParameter("phone", phone)
                    .uniqueResultOptional();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<UserAuthEntity> findAllUsersAuth() {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "FROM UserAuthEntity";

            List<UserAuthEntity> result = session.createQuery(queryHQL, UserAuthEntity.class)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public UserAuthEntity updateUserAuth(UserAuthEntity userAuth) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            UserAuthEntity merge = session.merge(userAuth);
            session.getTransaction().commit();
            return merge;
        }
    }

    @Override
    public UserAuthEntity updatePassword(Long userAuthId, String newPassword) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "UPDATE UserAuthEntity u " +
                    "SET u.passwordHash = :newPassword, u.updatedAt = :updated_at " +
                    "WHERE u.userAuthId = :userAuth_id";

            session.createQuery(queryHQL, UserAuthEntity.class)
                    .setParameter("newPassword", newPassword)
                    .setParameter("updateAt", OffsetDateTime.now())
                    .setParameter("userAuthId", userAuthId)
                    .executeUpdate();

            session.getTransaction().commit();
            UserAuthEntity updateUserAuth = session.get(UserAuthEntity.class, userAuthId);

            return updateUserAuth;
        }
    }

    @Override
    public void deleteUserAuthById(Long userAuthId) {
        try (Session session = HibernateUtil.getSession()){
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "DELETE FROM UserAuthEntity WHERE userAuthId = :user_auth_id";

            session.createQuery(queryHQL, UserAuthEntity.class)
                    .setParameter("user_auth_id", userAuthId)
                    .executeUpdate();
        }
    }


    @Override
    public boolean existsUserAuthByEmail(String email) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "SELECT COUNT(u) FROM UserAuthEntity u WHERE u.email = :email";

            Integer count = session.createQuery(queryHQL, Integer.class)
                    .setParameter("email", email)
                    .uniqueResult();

            return count > 0;
        }
    }

    @Override
    public boolean existsUserAuthByPhone(String phone) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "SELECT COUNT(u) FROM UserAuthEntity u WHERE u.phone = :phone";

            Integer count = session.createQuery(queryHQL, Integer.class)
                    .setParameter("phone", phone)
                    .uniqueResult();

            return count > 0;
        }
    }
}
