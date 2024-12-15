package pl.yummy.business.dao;

import pl.yummy.infrastructure.database.entity.OwnerEntity;

import java.util.List;
import java.util.Optional;

public interface OwnerDAO {

    /* CRUD */
    /* create */
    OwnerEntity createOwner(OwnerEntity owner);

    /* read */
    Optional<OwnerEntity> findOwnerById(Long ownerId);
    List<OwnerEntity> findAllOwner();

    /* update */
    OwnerEntity updateOwner(OwnerEntity owner);

    /* delete */
    void deleteOwner(Long ownerId);
}

