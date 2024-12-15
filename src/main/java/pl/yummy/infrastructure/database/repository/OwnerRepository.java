package pl.yummy.infrastructure.database.repository;

import org.hibernate.Session;
import pl.yummy.business.dao.OwnerDAO;
import pl.yummy.infrastructure.configuration.HibernateUtil;
import pl.yummy.infrastructure.database.entity.OwnerEntity;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class OwnerRepository implements OwnerDAO {

    @Override
    public OwnerEntity createOwner(OwnerEntity owner) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            session.persist(session);
            session.getTransaction().commit();
            return owner;
        }
    }

    @Override
    public Optional<OwnerEntity> findOwnerById(Long ownerId) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "SELECT c FROM OwnerEntity o WHERE o.ownerId = :owner_id";
            Optional<OwnerEntity> result = session.createQuery(queryHQL, OwnerEntity.class)
                    .setParameter("owner_id", ownerId)
                    .uniqueResultOptional();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<OwnerEntity> findAllOwner() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "FROM OwnerEntity";
            List<OwnerEntity> result = session.createQuery(queryHQL, OwnerEntity.class)
                    .getResultList();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public OwnerEntity updateOwner(OwnerEntity owner) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            OwnerEntity merge = session.merge(owner);
            session.getTransaction().commit();
            return merge;
        }
    }

    @Override
    public void deleteOwner(Long ownerId) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String queryHQL = "DELETE FROM OwnerEntity WHERE ownerId = :owner_id";
            session.createQuery(queryHQL, OwnerEntity.class)
                    .setParameter("owner_id", ownerId)
                    .executeUpdate();
        }
    }
}
