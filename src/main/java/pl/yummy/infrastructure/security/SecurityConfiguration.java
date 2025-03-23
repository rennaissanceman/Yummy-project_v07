package pl.yummy.infrastructure.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*
 * Konfiguracja bezpieczeństwa dla aplikacji Yummy.
 *
 * Definiuje:
 * - Bean PasswordEncoder (BCryptPasswordEncoder) do haszowania haseł.
 * - Bean AuthenticationManager konfigurujący mechanizm uwierzytelniania.
 * - Dwa beany SecurityFilterChain:
 *      • securityEnabled – aktywne zabezpieczenia z przypisaniem uprawnień do endpointów.
 *      • securityDisabled – wyłączenie zabezpieczeń, gdy właściwość "spring.security.enabled" jest ustawiona na "false".
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(
            HttpSecurity http,
            PasswordEncoder passwordEncoder,
            UserDetailsService userDetailService
    ) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

    @Bean
    @ConditionalOnProperty(value = "spring.security.enabled", havingValue = "true", matchIfMissing = true)
    public SecurityFilterChain securityEnabled(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                // Publiczne endpointy – dostępne dla wszystkich
                .requestMatchers(
                        "/", "/home",
                        "/auth/login", "/auth/register",
                        "/customer/register", "/customer/login",
                        "/restaurant/**", "/resources/**", "/images/**"
                ).permitAll()
                // Endpointy administracyjne – dostępne tylko dla ADMIN
                .requestMatchers("/admin/**").hasAuthority("ADMIN")
                // Operacje na koncie – wymagają uwierzytelnienia (każdy zalogowany użytkownik)
                .requestMatchers("/account/**").authenticated()
                // Endpointy dla klientów – poza rejestracją/logowaniem wymagają roli CUSTOMER
                .requestMatchers("/customer/**").hasAuthority("CUSTOMER")
                // Analityka klientów – dostępna dla klientów
                .requestMatchers("/customer/analytics/**").hasAuthority("CUSTOMER")
                // Endpointy dla kurierów
                .requestMatchers("/courier/**").hasAuthority("COURIER")
                // Endpointy dostaw – dostępne dla użytkowników z rolą MANAGER lub DELIVERY (np. pracownicy obsługi dostaw)
                .requestMatchers("/delivery/**").hasAnyAuthority("MANAGER", "DELIVERY")
                // Endpointy zamówień – dostępne dla klientów i menedżerów
                .requestMatchers("/order/**").hasAnyAuthority("CUSTOMER", "MANAGER")
                // Endpointy płatności i paragonów – dostępne dla klientów i menedżerów
                .requestMatchers("/payment/**", "/receipt/**").hasAnyAuthority("CUSTOMER", "MANAGER")
                // Endpointy opinii – wymagają uwierzytelnienia
                .requestMatchers("/feedback/**").authenticated()
                .and()
                .formLogin()
                .loginPage("/auth/login")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/auth/login")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll();

        return http.build();
    }

    @Bean
    @ConditionalOnProperty(value = "spring.security.enabled", havingValue = "false")
    public SecurityFilterChain securityDisabled(HttpSecurity http) throws Exception {
        // Gdy zabezpieczenia są wyłączone – wszystkie żądania są dozwolone
        http.authorizeHttpRequests().anyRequest().permitAll();
        return http.build();
    }
}

    /*
    Wyjaśnienie:
    - PasswordEncoder – definiujemy BCryptPasswordEncoder do haszowania haseł.
    - AuthenticationManager – konfigurujemy managera uwierzytelniania korzystając z serwisu UserDetailsService.
    - SecurityFilterChain (securityEnabled) – gdy zabezpieczenia są włączone („spring.security.enabled” ustawione
    na true lub domyślnie):
        - Endpointy publiczne (np. strona główna, logowanie, rejestracja, wyświetlanie restauracji) są dostępne
        bez uwierzytelnienia.
        - Endpointy z prefiksem /admin/ wymagają roli ADMIN.
        - Endpointy /account/ wymagają, aby użytkownik był zalogowany.
        - Endpointy /customer/ (poza rejestracją i logowaniem) wymagają roli CUSTOMER.
        - Endpointy /courier/ wymagają roli COURIER.
        - Endpointy /delivery/ dostępne są dla użytkowników z rolami MANAGER lub DELIVERY (możesz dostosować tę
        listę w zależności od potrzeb).
        - Endpointy /order/, /payment/, /receipt/ są dostępne dla ról CUSTOMER lub MANAGER.
        - Endpointy /feedback/ wymagają, aby użytkownik był uwierzytelniony.
    - SecurityFilterChain (securityDisabled) – gdy zabezpieczenia są wyłączone, wszystkie żądania są dozwolone.

    Ta konfiguracja stanowi podstawowy szkielet, który możesz dalej rozwijać i dostosowywać do specyficznych potrzeb
    aplikacji Yummy.


    1. Publiczne endpointy (permitAll)
    RequestMatchers:
    "/", "/home", "/auth/login", "/auth/register", "/customer/register", "/customer/login", "/restaurant/**",
    "/resources/**", "/images/**"

    Odpowiadające endpointy:

    HomeController:
    / – strona główna

    - AuthController:
    /auth/login – formularz logowania (GET) oraz logowanie (POST)
    /auth/register – formularz rejestracji (GET) oraz rejestracja (POST)

    - CustomerController:
    /customer/register – formularz rejestracji nowego klienta
    /customer/login – formularz logowania klienta

    - RestaurantController:
    /restaurant/details, /restaurant/cuisine, /restaurant/rating, /restaurant/owner


    - Static resources:
    /resources/** oraz /images/** – dostęp do plików statycznych (np. grafiki, CSS)

    __________________________________________________________________________________________________________________

    2. Endpointy administracyjne (tylko dla ADMIN)
    RequestMatchers:
    "/admin/**"

    Odpowiadające endpointy:

    - AdminController:
    /admin/customers
    /admin/restaurants
    /admin/orders
    /admin/revenue-report
    /admin/delivery-status

    __________________________________________________________________________________________________________________

    3. Operacje na koncie (wymagają uwierzytelnienia)
    RequestMatchers:
    "/account/**"

    Odpowiadające endpointy:

    AccountController:
    /account/reset-password
    /account/check-email

    __________________________________________________________________________________________________________________

    4. Endpointy dla klientów (rola CUSTOMER)
    RequestMatchers:
    "/customer/**"

    Odpowiadające endpointy:

    CustomerController: (poza endpointami rejestracji i logowania, które są już publiczne)
    /customer/profile
    /customer/profile/edit
    /customer/profile/update
    /customer/orders
    /customer/all
    /customer/companies
    /customer/by-company
    /customer/by-surname

    __________________________________________________________________________________________________________________

    5. Analityka klientów (rola CUSTOMER)

    RequestMatchers:
    "/customer/analytics/**"

    Odpowiadające endpointy:

    CustomerAnalyticsController:
    /customer/analytics/activity-history

    __________________________________________________________________________________________________________________

    6. Endpointy dla kurierów (rola COURIER)
    RequestMatchers:
    "/courier/**"

    Odpowiadające endpointy:

    CourierController:
    /courier (lista dostępnych kurierów)
    /courier/details
    /courier/minRating
    /courier/hiredAfter
    /courier/byVehicle

    __________________________________________________________________________________________________________________

    7. Endpointy dostaw (role MANAGER lub DELIVERY)
    RequestMatchers:
    "/delivery/**"

    Odpowiadające endpointy:

    DeliveryController:
    /delivery
    /delivery/details
    /delivery/create
    /delivery/update
    /delivery/delete
    /delivery/courier
    /delivery/status
    /delivery/starting-after
    /delivery/late
    /delivery/by-area
    /delivery/recalculate-fee
    /delivery/status-overview
    /delivery/assign
    /delivery/assign-courier

    __________________________________________________________________________________________________________________

    8. Endpointy zamówień (role CUSTOMER lub MANAGER)
    RequestMatchers:
    "/order/**"

    Odpowiadające endpointy:

    OrderController:
    /order/history
    /order/details
    /order/place
    /order/status
    /order/created-after
    /order/total-amount
    /order/without-delivery
    /order/all
    /order/revenue-report
    /order/mark-completed
    /order/save-order
    /order/process
    /order/update-status
    /order/cancel

    __________________________________________________________________________________________________________________

    9. Endpointy płatności i paragonów (role CUSTOMER lub MANAGER)
    RequestMatchers:
    "/payment/**", "/receipt/**"

    Odpowiadające endpointy:

    PaymentController:
    /payment (formularz płatności)
    /payment/process
    /payment/refund
    /payment/confirmation
    /payment/refundConfirmation
    /payment/processByOrder
    /payment/processByOrderConfirmation


    PaymentControllerExtension:
    /payment/details

    ReceiptController:
    /receipt/details

    __________________________________________________________________________________________________________________

    10. Endpointy opinii (wymagają uwierzytelnienia)
    RequestMatchers:
    "/feedback/**"

    Odpowiadające endpointy:

    FeedbackController:
    /feedback/list
    /feedback/create
    /feedback/save

    __________________________________________________________________________________________________________________
     */
