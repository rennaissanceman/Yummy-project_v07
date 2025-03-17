package pl.yummy.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.yummy.api.dto.CustomerFeedbackRequestDTO;
import pl.yummy.api.dto.CustomerFeedbackViewDTO;
import pl.yummy.api.dto.mapper.FeedbackMapper;
import pl.yummy.business.FeedbackService;

import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class FeedbackController {

    private static final String FEEDBACK_LIST = "/feedback/list";
    private static final String FEEDBACK_CREATE = "/feedback/create";
    private static final String FEEDBACK_SAVE = "/feedback/save";

    private final FeedbackService feedbackService;
    private final FeedbackMapper feedbackMapper;

    // GET – Wyświetla listę opinii (feedback) dla restauracji lub klienta.
    @GetMapping(FEEDBACK_LIST)
    public ModelAndView listFeedback(@RequestParam("restaurantId") Long restaurantId) {
        List<CustomerFeedbackViewDTO> feedbackList = feedbackService
                .getFeedbackForRestaurant(restaurantId)
                .stream()
                .map(feedbackMapper::toDTO)
                .toList();
        Map<String, Object> model = Map.of("feedbackList", feedbackList);
        return new ModelAndView("feedback_list", model);
    }

    // GET – Wyświetla formularz dodania nowej opinii.
    @GetMapping(FEEDBACK_CREATE)
    public ModelAndView showFeedbackForm() {
        // Zakładamy, że RequestCustomerFeedbackDTO.buildDefault() tworzy domyślny obiekt.
        Map<String, Object> model = Map.of("feedbackForm", CustomerFeedbackRequestDTO.buildDefault());
        return new ModelAndView("feedback_form", model);
    }

    // POST – Zapisuje nową opinię.
    @PostMapping(FEEDBACK_SAVE)
    public ModelAndView saveFeedback(@ModelAttribute("feedbackForm") CustomerFeedbackRequestDTO feedbackDTO) {
        feedbackService.saveFeedback(feedbackMapper.toDomain(feedbackDTO));
        return new ModelAndView("redirect:" + FEEDBACK_LIST);
    }
}

    /*
    FeedbackController – do obsługi opinii klientów, recenzji potraw czy restauracji.

    FeedbackController – do zarządzania opiniami i recenzjami, umożliwiając np. zgłaszanie uwag przez klientów
    oraz analizę satysfakcji.
    */