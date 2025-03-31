package pl.yummy.api.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.yummy.api.dto.CustomerLoginRequestDTO;
import pl.yummy.api.dto.CustomerRegistrationRequestDTO;
import pl.yummy.api.dto.UserAuthDTO;
import pl.yummy.api.dto.mapper.CustomerMapper;
import pl.yummy.api.dto.mapper.UserAuthMapper;
import pl.yummy.business.CustomerService;
import pl.yummy.domain.Customer;

import java.util.Map;


@Controller
@AllArgsConstructor
public class AuthController {

    private static final String AUTH_LOGIN = "/auth/login";
    private static final String AUTH_REGISTER = "/auth/register";

    private final CustomerService customerService;
    private final UserAuthMapper userAuthMapper;
    private final CustomerMapper customerMapper;


    // GET – Wyświetlenie formularza logowania
    @GetMapping(AUTH_LOGIN)
    public ModelAndView showLoginForm() {
        // Używamy domyślnego konstruktora
        CustomerLoginRequestDTO loginDTO = CustomerLoginRequestDTO.buildDefault();
        return new ModelAndView("auth_login", Map.of("loginDTO", loginDTO));
    }

    // POST – Obsługa logowania
    @PostMapping(AUTH_LOGIN)
    public ModelAndView login(
            @Valid @ModelAttribute("loginDTO") CustomerLoginRequestDTO loginDTO,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("auth_login", Map.of("loginDTO", loginDTO));
        }
        // Przykładowa logika uwierzytelnienia – metoda w customerService mogłaby np. weryfikować dane logowania
        Customer customer = customerService.loginCustomer(loginDTO.getEmail(), loginDTO.getPassword());
        // Mapowanie danych uwierzytelnionego użytkownika
        UserAuthDTO userAuthDTO = userAuthMapper.toDTO(customer.getUserAuth());
        return new ModelAndView("auth_profile", Map.of("userAuth", userAuthDTO));
    }

    // GET – Wyświetlenie formularza rejestracji
    @GetMapping(AUTH_REGISTER)
    public ModelAndView showRegistrationForm() {
        CustomerRegistrationRequestDTO registrationDTO = new CustomerRegistrationRequestDTO();
        return new ModelAndView("auth_register", Map.of("registrationDTO", registrationDTO));
    }

    // POST – Obsługa rejestracji
    @PostMapping(AUTH_REGISTER)
    public ModelAndView register(
            @Valid @ModelAttribute("registrationDTO") CustomerRegistrationRequestDTO registrationDTO,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("auth_register", Map.of("registrationDTO", registrationDTO));
        }
        // Mapowanie DTO na obiekt domenowy Customer
        Customer customer = customerMapper.toDomain(registrationDTO);
        customerService.registerCustomer(customer);
        // Po rejestracji, przekierowanie do profilu lub logowania
        return new ModelAndView("redirect:" + AUTH_LOGIN);
    }
}

    /*
    6. AuthController (opcjonalnie)

    Odpowiedzialności:
    - Obsługa autoryzacji i uwierzytelniania użytkowników.
    - Zarządzanie tokenami dostępowymi (np. JWT).

    Przykładowe endpointy:

    - POST /api/auth/login – logowanie użytkownika i wydanie tokenu.
    - POST /api/auth/logout – wylogowanie użytkownika.
    - POST /api/auth/refresh – odświeżenie tokenu autoryzacyjnego.
    */