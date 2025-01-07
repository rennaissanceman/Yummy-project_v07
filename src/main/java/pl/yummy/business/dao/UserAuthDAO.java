package pl.yummy.business.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.yummy.infrastructure.database.entity.UserAuthEntity;

import java.util.Optional;

public interface UserAuthDAO {

    Optional<UserAuthEntity> findByEmail(String email);
    Optional<UserAuthEntity> findByPhone(String phone);

    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);

    @Modifying
    @Query("UPDATE UserAuthEntity u SET u.password = :newPassword WHERE u.id = :userAuthId")
    int updatePassword(@Param("userAuthId") Long userAuthId, @Param("newPassword") String newPassword);
}
