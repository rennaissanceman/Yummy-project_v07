package pl.yummy.api.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.yummy.api.dto.PaymentDTO;
import pl.yummy.api.dto.PaymentProcessingRequestDTO;
import pl.yummy.api.dto.mapper.PaymentMapper;
import pl.yummy.api.dto.mapper.PaymentProcessingRequestMapper;
import pl.yummy.business.PaymentProcessingService;
import pl.yummy.domain.Payment;
import pl.yummy.domain.PaymentProcessingRequest;

import java.util.Map;

@Controller
@AllArgsConstructor
public class PaymentController {

    private static final String PAYMENT_HOME = "/payment";
    private static final String PAYMENT_PROCESS = "/payment/process";
    private static final String PAYMENT_REFUND = "/payment/refund";
    private static final String PAYMENT_CONFIRMATION = "/payment/confirmation";
    private static final String PAYMENT_REFUND_CONFIRMATION = "/payment/refundConfirmation";
    private static final String PAYMENT_PROCESS_BY_ORDER = "/payment/processByOrder";
    private static final String PAYMENT_PROCESS_BY_ORDER_CONFIRMATION = "/payment/processByOrderConfirmation";

    private final PaymentProcessingService paymentProcessingService;
    private final PaymentProcessingRequestMapper paymentProcessingRequestMapper;
    private final PaymentMapper paymentMapper;

    // GET – Wyświetla formularz przetwarzania płatności (domyślny).
    @GetMapping(PAYMENT_HOME)
    public ModelAndView showPaymentForm() {
        Map<String, Object> model = Map.of("paymentForm", PaymentProcessingRequestDTO.buildDefault());
        return new ModelAndView("payment_form", model);
    }

    // POST – Przetwarza płatność przy użyciu danych z RequestPaymentProcessingDTO.
    @PostMapping(PAYMENT_PROCESS)
    public ModelAndView processPayment(
            @Valid @ModelAttribute("paymentForm") PaymentProcessingRequestDTO paymentDTO,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("error");
        }
        PaymentProcessingRequest request = paymentProcessingRequestMapper.toDomain(paymentDTO);
        paymentProcessingService.processPayment(request);
        Map<String, Object> model = Map.of("message", "Płatność została przetworzona pomyślnie");
        return new ModelAndView(PAYMENT_CONFIRMATION, model);
    }

    // GET – Wyświetla formularz zwrotu płatności.
    @GetMapping(PAYMENT_REFUND)
    public ModelAndView showRefundForm() {
        // W tym przykładzie refundForm opiera się na PaymentDTO.
        Map<String, Object> model = Map.of("refundForm", new PaymentDTO());
        return new ModelAndView("payment_refund_form", model);
    }

    // POST – Przetwarza żądanie zwrotu płatności.
    @PostMapping(PAYMENT_REFUND)
    public ModelAndView refundPayment(@RequestParam("paymentId") Long paymentId) {
        paymentProcessingService.refundPayment(paymentId);
        Map<String, Object> model = Map.of("message", "Zwrot płatności został zrealizowany");
        return new ModelAndView(PAYMENT_REFUND_CONFIRMATION, model);
    }

    // GET – Wyświetla formularz przetwarzania płatności dla konkretnego zamówienia.
    @GetMapping(PAYMENT_PROCESS_BY_ORDER)
    public ModelAndView showProcessPaymentByOrderForm() {
        Map<String, Object> model = Map.of("paymentByOrderForm", new PaymentDTO());
        return new ModelAndView("payment_process_by_order_form", model);
    }

    // POST – Przetwarza płatność dla określonego zamówienia przy użyciu PaymentMapper.toDomain().
    @PostMapping(PAYMENT_PROCESS_BY_ORDER)
    public ModelAndView processPaymentByOrder(
            @RequestParam("orderId") Long orderId,
            @ModelAttribute("paymentByOrderForm") PaymentDTO paymentDTO) {
        Payment payment = paymentMapper.toDomain(paymentDTO);
        paymentProcessingService.processPayment(orderId, payment);
        Map<String, Object> model = Map.of("message", "Płatność dla zamówienia " + orderId + " została przetworzona pomyślnie");
        return new ModelAndView(PAYMENT_PROCESS_BY_ORDER_CONFIRMATION, model);
    }
}


    /*
    PaymentController – odpowiada za procesowanie płatności, generowanie faktur oraz integrację z systemami płatności.
    Dzięki niemu klient będzie mógł dokonać bezpiecznej płatności za zamówienie.

    3. PaymentController

    Odpowiedzialności:
    - Przetwarzanie płatności.
    - Generowanie faktur i paragonów.
    - Pobieranie historii płatności.


    Przykładowe endpointy:
    - POST /api/payments/process – inicjowanie procesu płatności.
    - GET /api/payments/{id} – pobranie szczegółów danej płatności.
    - GET /api/payments/history – lista dokonanych płatności.
    */