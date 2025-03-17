package pl.yummy.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.yummy.business.dao.CustomerAccountManagementProcessingDAO;

@Service
@AllArgsConstructor
public class AccountManagementService {

    private final CustomerAccountManagementProcessingDAO customerAccountManagementProcessingDAO;

    @Transactional
    public void resetPassword(Long userId, String newPassword) {
        customerAccountManagementProcessingDAO.resetPassword(userId, newPassword);
    }

    @Transactional
    public boolean existsByEmail(String email) {
        return customerAccountManagementProcessingDAO.existsByEmail(email);
    }
}

    /*
    ______________________________________________________________________________
    AccountManagementService

    - Odpowiada za operacje związane z zarządzaniem kontem klienta, np. resetowanie hasła czy sprawdzanie,
    czy adres email już istnieje.
    - Wstrzykiwany komponent: ProcessingCustomerAccountManagementDAO.
    */