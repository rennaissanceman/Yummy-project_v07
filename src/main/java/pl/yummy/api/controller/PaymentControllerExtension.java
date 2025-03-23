package pl.yummy.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.yummy.business.PaymentService;
import pl.yummy.api.dto.PaymentDTO;
import pl.yummy.api.dto.mapper.PaymentMapper;
import pl.yummy.domain.Payment;
import pl.yummy.domain.exception.NotFoundException;

import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping(PaymentControllerExtension.BASE_PATH)
public class PaymentControllerExtension {

    // Statyczne endpointy dla operacji płatności
    public static final String BASE_PATH = "/payment";
    public static final String DETAILS = BASE_PATH + "/details";

    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;

    /*
     * GET – Wyświetla szczegóły płatności na podstawie transactionId.
     */
    @GetMapping("/details")
    public ModelAndView paymentDetails(@RequestParam String transactionId) {
        Payment payment = paymentService.findByTransactionId(transactionId)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono płatności"));
        PaymentDTO dto = paymentMapper.toDTO(payment);
        return new ModelAndView("payment_details", Map.of("paymentDTO", dto));
    }
}

    /*
    Rozszerzenie istniejącego PaymentController – dodaje nowy endpoint do pobierania szczegółów płatności.
    */