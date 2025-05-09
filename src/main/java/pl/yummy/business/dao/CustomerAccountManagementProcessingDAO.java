package pl.yummy.business.dao;

public interface CustomerAccountManagementProcessingDAO {

    void resetPassword(Long userId, String newPassword);

    boolean existsByEmail(String email);
}
    /*
    Zarządzanie kontami klientów
    Cel: Obsługa operacji na kontach użytkowników, takich jak:
    - Resetowanie hasła.
    - Zmiana danych kontaktowych.
    - Sprawdzanie istnienia konta.

    _____________________________________________________
    Wstrzykiwany komponent: ProcessingCustomerAccountManagementDAO.
    */

