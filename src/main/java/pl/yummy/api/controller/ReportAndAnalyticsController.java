package pl.yummy.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.yummy.api.dto.ReportDTO;
import pl.yummy.api.dto.mapper.ReportMapper;
import pl.yummy.business.ReportService;

@Controller
@AllArgsConstructor
public class ReportAndAnalyticsController {

    private static final String REPORT_PAGE = "/reports";

    private final ReportService reportService;
    private final ReportMapper reportMapper;

    @GetMapping(value = REPORT_PAGE)
    public String showReports(Model model) {
        var reports = reportService.generateReports().stream()
                .map(reportMapper::map)
                .toList();
        model.addAttribute("reports", reports);
        return "report_page";
    }
}

    /*
    ReportController – do generowania raportów i analiz, takich jak statystyki sprzedaży czy wydajności dostaw.

    ReportController / AnalyticsController – do generowania raportów i analiz,
    takich jak statystyki sprzedaży, zamówień czy wydajności dostaw.
    */