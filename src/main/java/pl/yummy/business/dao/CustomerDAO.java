package pl.yummy.business.dao;

import pl.yummy.infrastructure.database.entity.CustomerEntity;

import java.util.List;
import java.util.Optional;

public interface CustomerDAO {


    Optional<CustomerEntity> findById(Integer id); // Wyszukiwanie klienta po ID
    Optional<CustomerEntity> findByCustomerNumber(String customerNumber); // Wyszukiwanie numerze klienta
    Optional<CustomerEntity> findByEmail(String email); // Wyszukiwanie po emailu
    Optional<CustomerEntity> findByPhone(String phone);
    List<CustomerEntity> findByCompanyName(String companyName);
    List<CustomerEntity> findByCustomerSurname(String surname);
    List<CustomerEntity> findByVatNumber(String vatNumber);
    List<CustomerEntity> findByCity(String city); // Wyszukaj klientów w określonym mieście
    List<CustomerEntity> findByStreet(String street);
    List<CustomerEntity> findAll(); // Pobranie wszystkich klientów
//    void save(CustomerEntity entity); // Dodanie lub aktualizacja klienta
    CustomerEntity saveCustomer(CustomerEntity entity);
    void deleteById(Integer customerId); // Usunięcie klienta po ID


/*
        CustomerEntity findById(Long id); // Znajdź klienta po ID
        List<CustomerEntity> findAll(); // Pobierz wszystkich klientów
        void save(CustomerEntity customer); // Zapisz lub zaktualizuj klienta
        void deleteById(Long id); // Usuń klienta po ID
        List<CustomerEntity> findByCity(String city); // Znajdź klientów w danym mieście
        CustomerEntity findByEmail(String email); // Znajdź klienta po emailu
*/
}
