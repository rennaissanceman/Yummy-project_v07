package pl.yummy.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.yummy.business.dao.UserAuthDAO;
import pl.yummy.infrastructure.database.entity.UserAuthEntity;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserAuthRepository implements UserAuthDAO {
    @Override
    public Optional<UserAuthEntity> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<UserAuthEntity> findByPhone(String phone) {
        return Optional.empty();
    }

    @Override
    public boolean existsByEmail(String email) {
        return false;
    }

    @Override
    public boolean existsByPhone(String phone) {
        return false;
    }

    @Override
    public int updatePassword(Long userAuthId, String newPassword) {
        return 0;
    }
}
