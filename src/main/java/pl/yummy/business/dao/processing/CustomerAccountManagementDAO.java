package pl.yummy.business.dao.processing;

public interface CustomerAccountManagementDAO {

    void resetPassword(Long userId, String newPassword);

    boolean existsByEmail(String email);

    /*
    Zarządzanie kontami klientów
    Cel: Obsługa operacji na kontach użytkowników, takich jak:
    - Resetowanie hasła.
    - Zmiana danych kontaktowych.
    - Sprawdzanie istnienia konta.
    */
}
