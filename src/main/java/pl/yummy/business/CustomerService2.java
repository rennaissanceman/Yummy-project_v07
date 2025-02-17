package pl.yummy.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.yummy.business.dao.CustomerDAO;
import pl.yummy.domain.Address;
import pl.yummy.domain.BillingInformation;
import pl.yummy.domain.Customer;
import pl.yummy.domain.Invoice;
import pl.yummy.domain.Orders;
import pl.yummy.domain.UserAuth;
import pl.yummy.domain.exception.NotFoundException;
import pl.yummy.domain.requests.OrderPlacementRequest;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CustomerService2 {

    private final CustomerDAO customerDAO;
    // Wstrzyknięcie serwisu obsługującego zamówienia – umożliwia budowanie obiektu zamówienia
    private final OrderService orderService;

    /**
     * Rejestruje nowego klienta poprzez zapisanie obiektu Customer w bazie.
     *
     * @param customer obiekt klienta do rejestracji
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
     * Buduje obiekt klienta na podstawie danych z żądania złożenia zamówienia oraz wystawionej faktury.
     * Przy wykorzystaniu OrderService tworzymy zamówienie, które następnie przypisujemy do klienta.
     *
     * @param request dane z żądania złożenia zamówienia
     * @param invoice wystawiona faktura
     * @return nowy obiekt Customer
     */
    public Customer buildCustomer(OrderPlacementRequest request, Invoice invoice) {
        // Przykładowo budujemy zamówienie korzystając z OrderService.
        // W tym przykładzie zakładamy, że metoda buildOrder wymaga przekazania obiektu restauracji.
        // Dla uproszczenia przekazujemy null – w pełnej implementacji należałoby wyszukać restaurację.
        Orders order = orderService.buildOrder(request, null);

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
                // Dodajemy utworzone zamówienie do zbioru zamówień klienta
                .orders(new HashSet<>(Set.of(order)))
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
}

    /*
    Wyjaśnienie:
    - Wstrzyknięcie OrderService:
    Dodaliśmy pole private final OrderService orderService; do klasy CustomerService.
    Dzięki temu mamy dostęp do metod serwisu obsługującego zamówienia.

    - Metoda buildCustomer:
    Po pobraniu danych z żądania OrderPlacementRequest, zamiast ręcznie tworzyć obiekt zamówienia,
    wywołujemy orderService.buildOrder(request, null). W pełnej implementacji zamiast null należałoby
    przekazać obiekt restauracji. Następnie utworzone zamówienie dodajemy do zbioru zamówień klienta.

    Takie rozwiązanie pozwala na ponowne wykorzystanie istniejącej logiki z OrderService, c
    o poprawia modularność kodu, ułatwia testowanie i utrzymanie aplikacji.
    */
