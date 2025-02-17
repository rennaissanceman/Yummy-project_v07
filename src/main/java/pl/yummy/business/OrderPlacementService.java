package pl.yummy.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.yummy.domain.Customer;
import pl.yummy.domain.Invoice;
import pl.yummy.domain.Orders;
import pl.yummy.domain.Restaurant;
import pl.yummy.domain.requests.OrderPlacementRequest;

@Service
@AllArgsConstructor
public class OrderPlacementService {

    private final RestaurantService restaurantService;
    private final CustomerService customerService;
    private final OrderService orderService;

    public Invoice placeOrder(OrderPlacementRequest request) {
        return request.getExistingCustomerEmail().isBlank()
                ? processFirstTimeOrder(request)
                : processReturningCustomerOrder(request);
    }

    private Invoice processFirstTimeOrder(OrderPlacementRequest request) {
        // Znajdź restaurację na podstawie identyfikatora
        Restaurant restaurant = restaurantService.findRestaurant(request.getRestaurantIdentifier());
        // Zbuduj obiekt zamówienia na podstawie danych z requestu i restauracji
        Orders order = orderService.buildOrder(request, restaurant);
        // Utwórz fakturę
        Invoice invoice = orderService.buildInvoice(order, restaurant);
        // Zbuduj nowego klienta wraz z danymi (adres dostawy, dane kontaktowe itp.)
        Customer customer = customerService.buildCustomer(request, invoice);
        // Rejestruj nowego klienta (np. zapis w bazie oraz wystawienie faktury)
        customerService.registerCustomer(customer);
        return invoice;
    }

    private Invoice processReturningCustomerOrder(OrderPlacementRequest request) {
        Customer existingCustomer = customerService.findCustomer(request.getExistingCustomerEmail());
        Restaurant restaurant = restaurantService.findRestaurant(request.getRestaurantIdentifier());
        Orders order = orderService.buildOrder(request, restaurant);
        Invoice invoice = orderService.buildInvoice(order, restaurant);
        // Dodaj nową fakturę do istniejącego klienta i zaktualizuj zamówienie
        existingCustomer.getOrders().add(order);
        customerService.updateCustomer(existingCustomer);
        return invoice;
    }
}

    /*
    1. OrderPlacementService.

    Cel:
    Obsługuje proces składania zamówienia. W zależności od tego, czy klient jest nowy, czy już istnieje,
    buduje obiekt zamówienia, tworzy fakturę oraz rejestruje lub aktualizuje dane klienta.
    */