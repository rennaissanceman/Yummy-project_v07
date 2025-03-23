package pl.yummy.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import pl.yummy.domain.exception.NotFoundException;
import pl.yummy.domain.exception.ProcessingException;

import java.util.Optional;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /*
     * Globalna obsługa wszystkich nieprzechwyconych wyjątków.
     * Zwraca widok "error" z komunikatem o błędzie.
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex) {
        String message = String.format("Wystąpił nieoczekiwany błąd: [%s]", ex.getMessage());
        log.error(message, ex);
        ModelAndView modelView = new ModelAndView("error");
        modelView.addObject("errorMessage", message);
        return modelView;
    }

    /*
     * Obsługa wyjątku NotFoundException.
     * Ustawia status HTTP 404 (Not Found) oraz zwraca widok "error".
     */
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleNotFoundException(NotFoundException ex) {
        String message = String.format("Nie znaleziono zasobu: [%s]", ex.getMessage());
        log.error(message, ex);
        ModelAndView modelView = new ModelAndView("error");
        modelView.addObject("errorMessage", message);
        return modelView;
    }

    /*
     * Obsługa wyjątku ProcessingException.
     * Ustawia status HTTP 500 (Internal Server Error) oraz zwraca widok "error".
     */
    @ExceptionHandler(ProcessingException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleProcessingException(ProcessingException ex) {
        String message = String.format("Błąd przetwarzania: [%s]", ex.getMessage());
        log.error(message, ex);
        ModelAndView modelView = new ModelAndView("error");
        modelView.addObject("errorMessage", message);
        return modelView;
    }

    /*
     * Obsługa wyjątku BindException.
     * Ustawia status HTTP 400 (Bad Request) oraz zwraca widok "error".
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handleBindException(BindException ex) {
        String message = String.format("Błędne dane wejściowe: pole [%s], wartość [%s]",
                Optional.ofNullable(ex.getFieldError())
                        .map(FieldError::getField)
                        .orElse("nieznane"),
                Optional.ofNullable(ex.getFieldError())
                        .map(FieldError::getRejectedValue)
                        .orElse("nieznana"));
        log.error(message, ex);
        ModelAndView modelView = new ModelAndView("error");
        modelView.addObject("errorMessage", message);
        return modelView;
    }
}


    /*
    GlobalExceptionHandler – (lub ExceptionController) – kontroler globalnej obsługi wyjątków. Dzięki niemu można
    centralnie zarządzać błędami aplikacji (np. błędy walidacji, wyjątki biznesowe) i prezentować użytkownikowi spójne
    komunikaty błędów.
    */