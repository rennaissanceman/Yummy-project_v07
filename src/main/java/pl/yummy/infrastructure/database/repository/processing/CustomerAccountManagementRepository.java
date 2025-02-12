package pl.yummy.infrastructure.database.repository.processing;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.yummy.business.dao.processing.CustomerAccountManagementDAO;
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

    Zakres odpowiedzialności:
    - Zarządza kontami klientów.
    - Umożliwia resetowanie hasła (metoda resetPassword) – przyjmuje identyfikator użytkownika oraz nowe hasło,
    a następnie wykonuje aktualizację bezpośrednio w bazie.
    - Umożliwia weryfikację, czy dany email już istnieje w systemie (metoda existsByEmail).

    Kluczowe elementy:
    - Wykorzystuje UserAuthJpaRepository do wykonywania operacji na encjach związanych z uwierzytelnianiem.
    - Odpowiada za centralne zarządzanie danymi kont użytkowników, co obejmuje m.in. resetowanie haseł.
    */

}
