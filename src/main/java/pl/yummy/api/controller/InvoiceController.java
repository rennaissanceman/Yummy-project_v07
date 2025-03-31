package pl.yummy.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.yummy.business.InvoiceService;
import pl.yummy.api.dto.InvoiceDTO;
import pl.yummy.api.dto.mapper.InvoiceMapper;
import pl.yummy.domain.Invoice;
import pl.yummy.domain.exception.NotFoundException;

import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping(InvoiceController.BASE_PATH)
public class InvoiceController {

    // Statyczne endpointy dla faktur
    public static final String BASE_PATH = "/invoice";
    public static final String DETAILS = BASE_PATH + "/details";

    private final InvoiceService invoiceService;
    private final InvoiceMapper invoiceMapper;

    /*
     * GET – Wyświetla szczegóły faktury na podstawie numeru faktury.
     */
    @GetMapping(DETAILS)
    public ModelAndView invoiceDetails(@RequestParam String invoiceNumber) {
        Invoice invoice = invoiceService.findByInvoiceNumber(invoiceNumber)
                .orElseThrow(() -> new NotFoundException("Faktura nie znaleziona"));
        InvoiceDTO invoiceDTO = invoiceMapper.toDTO(invoice);
        return new ModelAndView("invoice_details", Map.of("invoiceDTO", invoiceDTO));
    }
}

    /*
    Odpowiada za wyświetlanie szczegółów faktur (Invoice) w systemie.
    */