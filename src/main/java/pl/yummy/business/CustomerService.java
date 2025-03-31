package pl.yummy.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.yummy.business.dao.CustomerDAO;
import pl.yummy.domain.*;
import pl.yummy.domain.exception.NotFoundException;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerDAO customerDAO;
    private final OrderService orderService; // Zakładamy, że OrderService posiada metodę findOrdersByCustomer


    // Jeśli jest dostępny serwis obsługujący zamówienia, można go wstrzyknąć, np.
    // private final OrderService orderService;

    /*
     * Rejestruje nowego klienta poprzez zapisanie obiektu Customer w bazie.
     *
     * @param customer obiekt klienta do zarejestrowania
     */
    @Transactional
    public void registerCustomer(Customer customer) {
        customerDAO.saveCustomer(customer);
    }

    /*
     * Metoda logowania klienta.
     * Wyszukuje klienta po emailu, a następnie sprawdza, czy hasło przekazane w żądaniu
     * zgadza się z zapisanym w obiekcie UserAuth.
     *
     * Zakładamy, że w klasie UserAuth hasło jest przechowywane w polu 'passwordHash'
     * i dostępne przez metodę getPasswordHash().
     *
     * @param email adres email klienta
     * @param password hasło klienta
     * @return obiekt Customer, jeśli logowanie się powiodło
     * @throws RuntimeException jeśli hasło jest niepoprawne
     */
    @Transactional
    public Customer loginCustomer(String email, String password) {
        // Wyszukiwanie klienta po emailu – jeśli nie znaleziono, metoda findCustomer wyrzuci NotFoundException.
        Customer customer = findCustomer(email);

        UserAuth userAuth = customer.getUserAuth();
        // Weryfikacja hasła przy użyciu metody getPasswordHash(), która zwraca zapisane hasło (np. w postaci zaszyfrowanej).
        if (userAuth == null || !userAuth.getPasswordHash().equals(password)) {
            throw new RuntimeException("Błędne dane logowania");
        }
        return customer;
    }


    public Customer findCustomerByEmail(String email) {
        // Pobranie klienta z bazy danych na podstawie emaila
        return customerDAO.findByUserAuth_Email(email)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono klienta o podanym emailu"));
    }


    /*
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


    /*
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

    /*
     * Aktualizuje dane klienta poprzez zapisanie zaktualizowanego obiektu Customer.
     *
     * @param customer klient, którego dane mają zostać zaktualizowane
     */
    @Transactional
    public void updateCustomer(Customer customer) {
        customerDAO.saveCustomer(customer);
    }

    /*
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

    /*
     * Pobiera listę klientów, którzy reprezentują firmy.
     *
     * @return lista klientów, dla których flaga isCompany jest ustawiona na true
     */
    @Transactional
    public List<Customer> findCompanyCustomers() {
        return customerDAO.findByIsCompanyTrue();
    }

    /*
     * Wyszukuje klientów według nazwy firmy.
     *
     * @param companyName nazwa firmy
     * @return lista klientów powiązanych z podaną nazwą firmy
     */
    @Transactional
    public List<Customer> findCustomersByCompanyName(String companyName) {
        return customerDAO.findByCompanyName(companyName);
    }

    /*
     * Wyszukuje klientów na podstawie nazwiska.
     *
     * @param surname nazwisko klienta
     * @return lista klientów o podanym nazwisku
     */
    @Transactional
    public List<Customer> findCustomersBySurname(String surname) {
        return customerDAO.findByCustomerSurname(surname);
    }

    // Dodajemy metodę getCustomerActivityHistory, której wcześniej brakowało
    /*
     * GET – Pobiera historię aktywności klienta:
     * sumuje łączną liczbę zamówień, łączny wydatek oraz wybiera ostatnie zamówienia (np. 5 najnowszych).
     */
    @Transactional
    public List<CustomerActivityHistoryView> getCustomerActivityHistory(String customerNumber) {
        Customer customer = findCustomerByNumber(customerNumber);
        List<Orders> ordersList = orderService.findOrdersByCustomer(customer.getCustomerId());
        int totalOrders = ordersList.size();
        BigDecimal totalSpent = ordersList.stream()
                .map(Orders::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Sortujemy zamówienia malejąco według daty i wybieramy 5 najnowszych
        List<Orders> sortedOrders = new ArrayList<>(ordersList);
        sortedOrders.sort(Comparator.comparing(Orders::getOrdersDateTime).reversed());
        List<CustomerActivityHistoryView.RecentOrder> recentOrders = sortedOrders.stream()
                .limit(5)
                .map(order -> CustomerActivityHistoryView.RecentOrder.builder()
                        .orderId(order.getOrdersId().intValue()) // zakładamy konwersję Long->Integer
                        .orderDate(order.getOrdersDateTime())
                        .status(order.getOrdersStatus().name())
                        .amount(order.getTotalAmount())
                        .build())
                .collect(Collectors.toList());

        CustomerActivityHistoryView view = CustomerActivityHistoryView.builder()
                .customerId(customer.getCustomerId().intValue())
                .customerName(customer.getCustomerName())
                .customerSurname(customer.getCustomerSurname())
                .totalOrders(totalOrders)
                .totalSpent(totalSpent)
                .recentOrders(recentOrders)
                .build();
        return List.of(view);
    }


    /*
     * Pobiera historię zamówień klienta na podstawie numeru klienta.
     * W tej uproszczonej implementacji zamówienia są pobierane przy użyciu OrderService,
     * a następnie mapowane na ViewOrderHistory, gdzie dla uproszczenia lista zdarzeń pozostaje pusta.
     *
     * @param customerNumber unikalny numer klienta
     * @return lista historii zamówień
     */
    @Transactional
    public List<OrderHistoryView> getCustomerOrderHistory(String customerNumber) {
        Customer customer = findCustomerByNumber(customerNumber);
        List<Orders> ordersList = orderService.findOrdersByCustomer(customer.getCustomerId());
        return ordersList.stream()
                .map(order -> OrderHistoryView.builder()
                        .orderNumber(order.getOrdersNumber())
                        // Przyjmujemy, że zdarzenia przetwarzania zostaną uzupełnione w przyszłości – na razie pusta lista
                        .orderProcessingEvents(Collections.emptyList())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<Customer> getAllCustomers() {
        return customerDAO.findAll();
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

    ________________________________________________________
    CustomerService

    - Zarządza danymi klientów, wyszukiwaniem klienta po numerze, emailu, nazwisku, firmie, zapisywaniem nowego klienta
    oraz pobieraniem listy klientów.
    - Wstrzykiwany komponent: CustomerDAO.

    */