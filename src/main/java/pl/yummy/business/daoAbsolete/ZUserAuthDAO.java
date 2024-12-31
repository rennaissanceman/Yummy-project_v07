package pl.yummy.business.daoAbsolete;

import pl.yummy.infrastructure.database.entity.UserAuthEntity;

import java.util.List;
import java.util.Optional;

public interface ZUserAuthDAO {

    /* CRUD */
    /* create */
    UserAuthEntity createUserAuth(UserAuthEntity userAuth);


    /* read */
    Optional<UserAuthEntity> findUserAuthById(Long userAuthId);
    Optional<UserAuthEntity> findUserAuthByEmail(String email);
    Optional<UserAuthEntity> findUserAuthByPhone(String phone);
    List<UserAuthEntity> findAllUsersAuth();


    /* update */
    UserAuthEntity updateUserAuth(UserAuthEntity userAuth);
    UserAuthEntity updatePassword(Long userAuthId, String newPassword);


    /* delete */
    void deleteUserAuthById(Long userAuthId);



    boolean existsUserAuthByEmail(String email);

    boolean existsUserAuthByPhone(String phone);

}
