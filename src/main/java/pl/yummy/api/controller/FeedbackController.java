package pl.yummy.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.yummy.api.dto.CustomerFeedbackRequestDTO;
import pl.yummy.api.dto.FeedbackDTO;
import pl.yummy.api.dto.mapper.FeedbackMapper;
import pl.yummy.business.FeedbackService;
import pl.yummy.domain.Feedback;

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
        List<FeedbackDTO> feedbackList = feedbackService
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
    public ModelAndView saveFeedback(@ModelAttribute("feedbackForm") FeedbackDTO feedbackDTO) {
        feedbackService.saveFeedback(feedbackMapper.toDomain(feedbackDTO));
        return new ModelAndView("redirect:" + FEEDBACK_LIST);
    }

    @PostMapping(FEEDBACK_SAVE)
    public ModelAndView saveFeedback(@ModelAttribute("feedbackForm") CustomerFeedbackRequestDTO feedbackDTO) {
        // Konwersja obiektu DTO na obiekt domenowy przy użyciu mappera
        Feedback feedback = feedbackMapper.toDomain(feedbackDTO);
        feedbackService.saveFeedback(feedback);
        return new ModelAndView("redirect:" + FEEDBACK_LIST);
    }

    @GetMapping("/feedback/details")
    public ModelAndView feedbackDetails(@RequestParam("feedbackId") Long feedbackId) {
        // Przykładowo pobieramy opinię po jej ID
        Feedback feedback = feedbackService.getFeedbackById(feedbackId);
        FeedbackDTO feedbackDTO = feedbackMapper.toDTO(feedback);
        return new ModelAndView("feedback_details", Map.of("feedback", feedbackDTO));
    }

    @GetMapping("/feedback/edit")
    public ModelAndView editFeedback(@RequestParam("feedbackId") Long feedbackId) {
        Feedback feedback = feedbackService.getFeedbackById(feedbackId);
        FeedbackDTO feedbackDTO = feedbackMapper.toDTO(feedback);
        return new ModelAndView("feedback_edit", Map.of("feedback", feedbackDTO));
    }

    @PostMapping("/feedback/edit")
    public ModelAndView updateFeedback(@ModelAttribute("feedback") FeedbackDTO feedbackDTO) {
        Feedback updatedFeedback = feedbackMapper.toDomain(feedbackDTO);
        feedbackService.saveFeedback(updatedFeedback);
        return new ModelAndView("redirect:" + FEEDBACK_LIST);
    }

}

    /*
    FeedbackController – do obsługi opinii klientów, recenzji potraw czy restauracji.

    FeedbackController – do zarządzania opiniami i recenzjami, umożliwiając np. zgłaszanie uwag przez klientów
    oraz analizę satysfakcji.
    */