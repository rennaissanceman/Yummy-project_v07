package pl.yummy.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.yummy.business.AccountManagementService;

import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping(AccountController.BASE_PATH)
public class AccountController {

    // Statyczna definicja endpointów dla operacji na koncie
    public static final String BASE_PATH = "/account";
    public static final String RESET_PASSWORD = BASE_PATH + "/reset-password";
    public static final String CHECK_EMAIL = BASE_PATH + "/check-email";

    private final AccountManagementService accountManagementService;

    /**
     * POST – Resetuje hasło użytkownika.
     */
    @PostMapping(RESET_PASSWORD)
    public ModelAndView resetPassword(@RequestParam Long userId, @RequestParam String newPassword) {
        accountManagementService.resetPassword(userId, newPassword);
        return new ModelAndView("account_reset_success", Map.of("message", "Hasło zostało zresetowane."));
    }

    /**
     * GET – Sprawdza, czy podany email istnieje w systemie.
     */
    @GetMapping(CHECK_EMAIL)
    public ModelAndView checkEmail(@RequestParam String email) {
        boolean exists = accountManagementService.existsByEmail(email);
        return new ModelAndView("account_email_check", Map.of("exists", exists, "email", email));
    }
}

    /*
    Odpowiada za operacje związane z zarządzaniem kontem użytkownika (np. reset hasła, sprawdzanie istnienia emaila).
    */