package pl.yummy.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.yummy.business.ReceiptService;
import pl.yummy.api.dto.ReceiptDTO;
import pl.yummy.api.dto.mapper.ReceiptMapper;
import pl.yummy.domain.Receipt;
import pl.yummy.domain.exception.NotFoundException;

import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping(ReceiptController.BASE_PATH)
public class ReceiptController {

    // Statyczne endpointy dla paragonów/faktur
    public static final String BASE_PATH = "/receipt";
    public static final String DETAILS = BASE_PATH + "/details";

    private final ReceiptService receiptService;
    private final ReceiptMapper receiptMapper;

    /*
     * GET – Wyświetla szczegóły paragonu/faktury na podstawie numeru paragonu.
     */
    @GetMapping("/details")
    public ModelAndView receiptDetails(@RequestParam String receiptNumber) {
        Receipt receipt = receiptService.findByReceiptNumber(receiptNumber)
                .orElseThrow(() -> new NotFoundException("Paragon nie znaleziony"));
        ReceiptDTO dto = receiptMapper.toDTO(receipt);
        return new ModelAndView("receipt_details", Map.of("receiptDTO", dto));
    }
}

    /*
    Odpowiada za wyświetlanie danych paragonu lub faktury fiskalnej.
    */