package pl.yummy.business.dao;

import pl.yummy.infrastructure.database.entity.CourierEntity;

import java.util.Optional;

public interface CourierDAO {

    Optional<CourierEntity> findByName(String surname);

}
