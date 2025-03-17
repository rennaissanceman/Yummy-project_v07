package pl.yummy.api.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.yummy.api.dto.CustomerLoginRequestDTO;
import pl.yummy.api.dto.CustomerRegistrationRequestDTO;
import pl.yummy.api.dto.CustomerUpdateRequestDTO;
import pl.yummy.api.dto.CustomerDTO;
import pl.yummy.api.dto.OrderHistoryViewDTO;
import pl.yummy.api.dto.mapper.CustomerMapper;
import pl.yummy.api.dto.mapper.OrderHistoryViewMapper;
import pl.yummy.business.CustomerService;
import pl.yummy.domain.Customer;
import pl.yummy.domain.OrderHistoryView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class CustomerController {

    private static final String CUSTOMER_REGISTER = "/customer/register";
    private static final String CUSTOMER_LOGIN = "/customer/login";
    private static final String CUSTOMER_PROFILE = "/customer/profile";
    private static final String CUSTOMER_PROFILE_EDIT = "/customer/profile/edit";
    private static final String CUSTOMER_PROFILE_UPDATE = "/customer/profile/update";
    private static final String CUSTOMER_ORDERS = "/customer/orders";
    private static final String CUSTOMER_ALL = "/customer/all";
    private static final String CUSTOMER_COMPANIES = "/customer/companies";
    private static final String CUSTOMER_BY_COMPANY = "/customer/by-company";
    private static final String CUSTOMER_BY_SURNAME = "/customer/by-surname";

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;
    private final OrderHistoryViewMapper orderHistoryViewMapper;

    // GET – Wyświetlenie formularza rejestracji nowego klienta.
    @GetMapping(CUSTOMER_REGISTER)
    public ModelAndView showRegistrationForm() {
        Map<String, Object> model = Map.of(
                "registrationDTO", CustomerRegistrationRequestDTO.buildDefault()
        );
        return new ModelAndView("customer_register", model);
    }

    // POST – Rejestracja nowego klienta.
    @PostMapping(CUSTOMER_REGISTER)
    public ModelAndView registerCustomer(
            @Valid @ModelAttribute("registrationDTO") CustomerRegistrationRequestDTO registrationDTO,
            BindingResult bindingResult) {
        // Sprawdzamy, czy podczas walidacji danych przesłanych z formularza wystąpiły błędy.
        if (bindingResult.hasErrors()) {
            // Jeśli są błędy, zwracamy widok "error", aby poinformować użytkownika o problemie.
            return new ModelAndView("error");
        }
        // Mapujemy dane z DTO na obiekt domenowy Customer.
        Customer customerToRegister = customerMapper.toDomain(registrationDTO);
        // Rejestrujemy klienta przy użyciu serwisu.
        customerService.registerCustomer(customerToRegister);
        // Pobieramy zarejestrowanego klienta na podstawie adresu email (przyjętego z danych logowania).
        Customer registeredCustomer = customerService.findCustomerByEmail(customerToRegister.getUserAuth().getEmail());
        // Mapujemy obiekt domenowy na DTO, który będzie wykorzystywany w widoku profilu.
        CustomerDTO customerDTO = customerMapper.toDTO(registeredCustomer);
        // Przygotowujemy model z danymi klienta.
        Map<String, Object> model = Map.of("customerDTO", customerDTO);
        // Zwracamy widok profilu klienta wraz z modelem.
        return new ModelAndView("customer_profile", model);
    }

    // GET – Wyświetlenie formularza logowania klienta.
    @GetMapping(CUSTOMER_LOGIN)
    public ModelAndView showLoginForm() {
        Map<String, Object> model = Map.of(
                "loginDTO", CustomerLoginRequestDTO.buildDefault()
        );
        return new ModelAndView("customer_login", model);
    }

    // POST – Logowanie klienta.
    @PostMapping(CUSTOMER_LOGIN)
    public ModelAndView loginCustomer(
            @Valid @ModelAttribute("loginDTO") CustomerLoginRequestDTO loginDTO,
            BindingResult bindingResult) {
        // Sprawdzamy, czy wystąpiły błędy walidacji danych logowania.
        if (bindingResult.hasErrors()) {
            // Jeśli walidacja zakończyła się błędem, zwracamy widok "error".
            return new ModelAndView("error");
        }
        // Logujemy klienta, sprawdzając poprawność podanego emaila i hasła.
        Customer customer = customerService.loginCustomer(loginDTO.getEmail(), loginDTO.getPassword());
        // Mapujemy obiekt domenowy klienta na DTO do prezentacji.
        CustomerDTO customerDTO = customerMapper.toDTO(customer);
        // Przygotowujemy model z danymi klienta.
        Map<String, Object> model = Map.of("customerDTO", customerDTO);
        // Zwracamy widok profilu klienta wraz z modelem.
        return new ModelAndView("customer_profile", model);
    }

    // GET – Wyświetlenie profilu klienta.
    @GetMapping(CUSTOMER_PROFILE)
    public ModelAndView getCustomerProfile(@RequestParam("customerNumber") String customerNumber) {
        Customer customer = customerService.findCustomerByNumber(customerNumber);
        CustomerDTO customerDTO = customerMapper.toDTO(customer);
        Map<String, Object> model = Map.of("customerDTO", customerDTO);
        return new ModelAndView("customer_profile", model);
    }

    // GET – Wyświetlenie formularza edycji danych profilu klienta.
    @GetMapping(CUSTOMER_PROFILE_EDIT)
    public ModelAndView showUpdateProfileForm(@RequestParam("customerNumber") String customerNumber) {
        Customer customer = customerService.findCustomerByNumber(customerNumber);
        // Zakładamy, że mapper posiada metodę konwertującą Customer do RequestCustomerUpdateDTO
        CustomerUpdateRequestDTO updateDTO = customerMapper.toUpdateDTO(customer);
        Map<String, Object> model = Map.of("updateDTO", updateDTO);
        return new ModelAndView("customer_update", model);
    }

    // POST – Aktualizacja danych profilu klienta.
    @PostMapping(CUSTOMER_PROFILE_UPDATE)
    public ModelAndView updateCustomerProfile(
            @Valid @ModelAttribute("updateDTO") CustomerUpdateRequestDTO updateDTO,
            BindingResult bindingResult) {
        // Sprawdzamy, czy dane przesłane z formularza aktualizacji profilu są poprawne.
        if (bindingResult.hasErrors()) {
            // Jeśli wystąpiły błędy, zwracamy widok "error".
            return new ModelAndView("error");
        }
        // Mapujemy dane z DTO na obiekt domenowy Customer i aktualizujemy profil klienta.
        customerService.updateCustomer(customerMapper.toDomain(updateDTO));
        // Po pomyślnej aktualizacji, przekierowujemy użytkownika do widoku profilu klienta z numerem klienta.
        return new ModelAndView("redirect:" + CUSTOMER_PROFILE + "?customerNumber=" + updateDTO.getCustomerNumber());
    }

    // GET – Wyświetlenie historii zamówień klienta.
    @GetMapping(CUSTOMER_ORDERS)
    public ModelAndView getCustomerOrderHistory(@RequestParam("customerNumber") String customerNumber) {
        List<OrderHistoryView> orderHistoryList = customerService.getCustomerOrderHistory(customerNumber);
        List<OrderHistoryViewDTO> orderHistoryDTOs = orderHistoryList.stream()
                .map(orderHistoryViewMapper::toDTO)
                .collect(Collectors.toList());
        Map<String, Object> model = Map.of("orderHistoryDTOs", orderHistoryDTOs);
        return new ModelAndView("customer_orders", model);
    }

    // GET – Pobranie listy wszystkich klientów.
    @GetMapping(CUSTOMER_ALL)
    public ModelAndView getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        List<CustomerDTO> customerDTOs = customers.stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
        Map<String, Object> model = Map.of("customerDTOs", customerDTOs);
        return new ModelAndView("customer_all", model);
    }

    // GET – Pobranie listy klientów reprezentujących firmy.
    @GetMapping(CUSTOMER_COMPANIES)
    public ModelAndView getCompanyCustomers() {
        List<Customer> companyCustomers = customerService.findCompanyCustomers();
        List<CustomerDTO> companyCustomerDTOs = companyCustomers.stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
        Map<String, Object> model = Map.of("companyCustomerDTOs", companyCustomerDTOs);
        return new ModelAndView("customer_companies", model);
    }

    // GET – Wyszukanie klientów według nazwy firmy.
    @GetMapping(CUSTOMER_BY_COMPANY)
    public ModelAndView getCustomersByCompanyName(@RequestParam("companyName") String companyName) {
        List<Customer> customers = customerService.findCustomersByCompanyName(companyName);
        List<CustomerDTO> customerDTOs = customers.stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
        Map<String, Object> model = Map.of("customerDTOs", customerDTOs, "companyName", companyName);
        return new ModelAndView("customer_by_company", model);
    }

    // GET – Wyszukanie klientów według nazwiska.
    @GetMapping(CUSTOMER_BY_SURNAME)
    public ModelAndView getCustomersBySurname(@RequestParam("surname") String surname) {
        List<Customer> customers = customerService.findCustomersBySurname(surname);
        List<CustomerDTO> customerDTOs = customers.stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
        Map<String, Object> model = Map.of("customerDTOs", customerDTOs, "surname", surname);
        return new ModelAndView("customer_by_surname", model);
    }
}

    /*
    CustomerController – zarządza rejestracją, logowaniem oraz profilem klienta. Może także odpowiadać za historię
    zamówień, edycję danych osobowych czy ustawienia powiadomień.


    1. CustomerController

    Odpowiedzialności:
    Rejestracja i logowanie klientów.
    Pobieranie i aktualizacja danych profilu klienta.
    Zarządzanie historią zamówień klienta.


    Przykładowe endpointy:
    POST /api/customers/register – rejestracja nowego klienta.
    POST /api/customers/login – logowanie klienta.
    GET /api/customers/{id} – pobranie danych klienta.
    PUT /api/customers/{id} – aktualizacja danych klienta.
    GET /api/customers/{id}/orders – pobranie historii zamówień klienta.

    */