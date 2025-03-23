package pl.yummy.infrastructure.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YummyUserRepository extends JpaRepository<YummyUserEntity, Long>{

    YummyUserEntity findByUserName(String userName);
}
