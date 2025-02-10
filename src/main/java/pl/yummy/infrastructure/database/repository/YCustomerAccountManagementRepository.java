package pl.yummy.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.yummy.business.dao.YCustomerAccountManagementDAO;
import pl.yummy.infrastructure.database.repository.jpa.UserAuthJpaRepository;

@Repository
@AllArgsConstructor
public class YCustomerAccountManagementRepository implements YCustomerAccountManagementDAO {

    private final UserAuthJpaRepository userAuthJpaRepository;

    @Override
    @Transactional
    public void resetPassword(Long userId, String newPassword) {
        userAuthJpaRepository.updatePassword(userId, newPassword);
    }

    @Override
    @Transactional
    public boolean existsByEmail(String email) {
        return userAuthJpaRepository.existsByEmail(email);
    }

    /*
    - Zapewnia centralne zarządzanie kontami klientów.
    - Obsługa resetowania hasła bezpośrednio w bazie.

    Zarządzanie kontami użytkowników --> Resetowanie haseł, weryfikacja konta, zmiana danych
    */

}
