package pl.yummy.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.yummy.business.OrderCancellationService;
import pl.yummy.api.dto.OrderCancellationRequestDTO;
import pl.yummy.api.dto.mapper.OrderCancellationRequestMapper;
import pl.yummy.domain.OrderCancellationRequest;

import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping(OrderCancellationController.BASE_PATH)
public class OrderCancellationController {

    // Statyczne endpointy dla anulowania zamówień
    public static final String BASE_PATH = "/order/cancel";
    public static final String FORM = BASE_PATH + "/form";

    private final OrderCancellationService orderCancellationService;
    private final OrderCancellationRequestMapper orderCancellationRequestMapper;

    /*
     * GET – Wyświetla formularz anulowania zamówienia.
     */
    @GetMapping("/form")
    public ModelAndView showCancellationForm() {
        OrderCancellationRequestDTO dto = new OrderCancellationRequestDTO();
        return new ModelAndView("order_cancel_form", Map.of("cancelDTO", dto));
    }

    /*
     * POST – Przetwarza anulowanie zamówienia.
     */
    @PostMapping(FORM)
    public ModelAndView cancelOrder(@ModelAttribute("cancelDTO") OrderCancellationRequestDTO dto) {
        OrderCancellationRequest request = orderCancellationRequestMapper.toDomain(dto);
        orderCancellationService.cancelOrder(request);
        return new ModelAndView("redirect:/order/all");
    }
}

    /*
    Odpowiada za anulowanie zamówień przez użytkownika lub administratora.
    */