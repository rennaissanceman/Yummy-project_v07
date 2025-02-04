package pl.yummy.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.yummy.business.dao.CustomerAccountManagementDAO;
import pl.yummy.infrastructure.database.repository.jpa.UserAuthJpaRepository;

@Repository
@AllArgsConstructor
public class CustomerAccountManagementRepository implements CustomerAccountManagementDAO {

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
