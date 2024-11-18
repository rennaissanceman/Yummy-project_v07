package pl.yummy.business.dao;

import pl.yummy.infrastructure.database.entity.ZCourierEntity;

import java.util.Optional;

public interface CourierDAO {

    Optional<ZCourierEntity> findByName(String surname);

}
