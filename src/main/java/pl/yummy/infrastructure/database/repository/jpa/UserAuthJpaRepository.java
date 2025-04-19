package pl.yummy.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.yummy.infrastructure.database.entity.UserAuthEntity;

import java.util.Optional;

@Repository
public interface UserAuthJpaRepository extends JpaRepository<UserAuthEntity, Long> {

    Optional<UserAuthEntity> findByEmail(String email);
    Optional<UserAuthEntity> findByPhone(String phone);

    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);

    @Modifying
    @Query("UPDATE UserAuthEntity u SET u.passwordHash = :newPassword WHERE u.userAuthId = :userAuthId")
    int updatePassword(Long userAuthId, String newPassword);

}
