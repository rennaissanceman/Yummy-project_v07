package pl.yummy.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.yummy.business.dao.CustomerDAO;
import pl.yummy.domain.*;
import pl.yummy.domain.exception.NotFoundException;
import pl.yummy.domain.requests.OrderPlacementRequest;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerDAO customerDAO;

    // Jeśli jest dostępny serwis obsługujący zamówienia, można go wstrzyknąć, np.
    // private final OrderService orderService;

    /**
     * Rejestruje nowego klienta poprzez zapisanie obiektu Customer w bazie.
     *
     * @param customer obiekt klienta do zarejestrowania
     */
    @Transactional
    public void registerCustomer(Customer customer) {
        customerDAO.saveCustomer(customer);
    }

    /**
     * Wyszukuje klienta na podstawie adresu email.
     *
     * @param email adres email klienta
     * @return znaleziony klient
     * @throws NotFoundException gdy klient o podanym emailu nie zostanie znaleziony
     */
    @Transactional
    public Customer findCustomer(String email) {
        return customerDAO.findByUserAuth_Email(email)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono klienta o adresie email: " + email));
    }

    /**
     * Buduje obiekt klienta na podstawie danych z żądania złożenia zamówienia i wystawionej faktury.
     * UWAGA: W tym przykładzie metoda buduje klienta bez powiązania z zamówieniami – w pełnej implementacji
     * można by wykorzystać OrderService do zbudowania obiektu zamówienia.
     *
     * @param request dane z żądania złożenia zamówienia
     * @param invoice wystawiona faktura
     * @return nowy obiekt Customer
     */
    public Customer buildCustomer(OrderPlacementRequest request, Invoice invoice) {
        return Customer.builder()
                .customerName(request.getCustomerName())
                .customerSurname(request.getCustomerSurname())
                .customerNumber(UUID.randomUUID().toString())
                .userAuth(UserAuth.builder()
                        .email(request.getCustomerEmail())
                        .phone(request.getCustomerPhone())
                        .build())
                .billingInformation(BillingInformation.builder()
                        .address(Address.builder()
                                .country(request.getCustomerAddressCountry())
                                .city(request.getCustomerAddressCity())
                                .postalCode(request.getCustomerAddressPostalCode())
                                .street(request.getCustomerAddressStreet())
                                .build())
                        .build())
                // Jeśli istnieje logika tworzenia zamówienia, można ją dodać – tutaj przykładowo pusta lista.
                .orders(new HashSet<>())
                .build();
    }

    /**
     * Aktualizuje dane klienta poprzez zapisanie zaktualizowanego obiektu Customer.
     *
     * @param customer klient, którego dane mają zostać zaktualizowane
     */
    @Transactional
    public void updateCustomer(Customer customer) {
        customerDAO.saveCustomer(customer);
    }

    /**
     * Wyszukuje klienta na podstawie unikalnego numeru klienta.
     *
     * @param customerNumber unikalny numer klienta
     * @return klient, jeśli został znaleziony
     * @throws NotFoundException gdy klient o podanym numerze nie zostanie znaleziony
     */
    @Transactional
    public Customer findCustomerByNumber(String customerNumber) {
        return customerDAO.findByCustomerNumber(customerNumber)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono klienta o numerze: " + customerNumber));
    }

    /**
     * Pobiera listę klientów, którzy reprezentują firmy.
     *
     * @return lista klientów, dla których flaga isCompany jest ustawiona na true
     */
    @Transactional
    public List<Customer> findCompanyCustomers() {
        return customerDAO.findByIsCompanyTrue();
    }

    /**
     * Wyszukuje klientów według nazwy firmy.
     *
     * @param companyName nazwa firmy
     * @return lista klientów powiązanych z podaną nazwą firmy
     */
    @Transactional
    public List<Customer> findCustomersByCompanyName(String companyName) {
        return customerDAO.findByCompanyName(companyName);
    }

    /**
     * Wyszukuje klientów na podstawie nazwiska.
     *
     * @param surname nazwisko klienta
     * @return lista klientów o podanym nazwisku
     */
    @Transactional
    public List<Customer> findCustomersBySurname(String surname) {
        return customerDAO.findByCustomerSurname(surname);
    }
}

    /*
    Wyjaśnienie:
    - Metoda registerCustomer(Customer customer) wykorzystuje metodę saveCustomer z CustomerDAO, aby zapisać klienta.
    - Metoda findCustomer(String email) korzysta z metody findByUserAuth_Email z DAO.
    - Dodano nowe metody:
        - findCustomerByNumber(String customerNumber) – wyszukuje klienta na podstawie unikalnego numeru.
        - findCompanyCustomers() – zwraca listę klientów, którzy są firmami.
        - findCustomersByCompanyName(String companyName) – wyszukuje klientów według nazwy firmy.
        - findCustomersBySurname(String surname) – wyszukuje klientów według nazwiska.
    - Metoda buildCustomer tworzy obiekt klienta na podstawie danych z żądania. W tym przykładzie nie wykorzystano
    zależności do budowy zamówienia, ale w pełnej implementacji można wstrzyknąć również OrderService.

    Takie podejście zapewnia, że CustomerService korzysta z wszystkich dostępnych metod z interfejsu CustomerDAO,
    umożliwiając pełną obsługę operacji związanych z klientami w aplikacji Yummy.
    */

    /*
    3. CustomerService.

    Cel:
    Zarządza rejestracją i wyszukiwaniem klientów. Umożliwia rejestrację nowego klienta (na podstawie danych
    z CustomerRegistrationRequest lub OrderPlacementRequest) oraz wyszukiwanie klienta po adresie e-mail.
    */

    /*



    */