package pl.yummy.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.yummy.business.dao.OrdersDAO;
import pl.yummy.domain.*;
import pl.yummy.domain.enums.OrdersStatusEnumDomain;
import pl.yummy.domain.exception.NotFoundException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrdersDAO ordersDAO;

    /**
     * Buduje nowe zamówienie na podstawie danych przekazanych w OrderPlacementRequest
     * oraz danych restauracji.
     *
     * @param request dane z żądania złożenia zamówienia
     * @param restaurant obiekt restauracji, dla której składane jest zamówienie
     * @return nowo utworzone zamówienie
     */
    public Orders buildOrder(RequestOrderPlacement request, Restaurant restaurant) {
        // Inicjujemy zamówienie tylko z podstawowymi danymi.
        return Orders.builder()
                .ordersNumber(request.getOrderNumber())
                .ordersDateTime(OffsetDateTime.now())
                .ordersStatus(OrdersStatusEnumDomain.PENDING)
                // Inne pola, takie jak customer czy menu, mogą być uzupełniane przez odpowiednie serwisy.
                .build();
    }

    /**
     * Tworzy obiekt faktury dla danego zamówienia.
     *
     * @param order zamówienie, dla którego tworzymy fakturę
     * @param restaurant restauracja powiązana z zamówieniem
     * @return nowo utworzona faktura
     */
    public Invoice buildInvoice(Orders order, Restaurant restaurant) {
        return Invoice.builder()
                .invoiceNumber(UUID.randomUUID().toString())
                .issueDate(OffsetDateTime.now())
                .saleDate(OffsetDateTime.now())
                .totalAmount(order.getTotalAmount())
                .build();
    }

    /**
     * Wyszukuje aktywne zamówienie o podanym numerze. Zakładamy, że aktywne zamówienia mają status PENDING.
     *
     * @param orderNumber numer zamówienia
     * @return zamówienie o statusie PENDING
     * @throws NotFoundException gdy zamówienie nie zostanie znalezione lub nie jest aktywne
     */
    @Transactional(readOnly = true)
    public Orders findActiveOrder(String orderNumber) {
        Optional<Orders> optionalOrder = ordersDAO.findByOrdersNumber(orderNumber);
        if (optionalOrder.isEmpty() ||
                !optionalOrder.get().getOrdersStatus().equals(OrdersStatusEnumDomain.PENDING)) {
            throw new NotFoundException("Nie znaleziono aktywnego zamówienia o numerze: " + orderNumber);
        }
        return optionalOrder.get();
    }

    /**
     * Buduje obiekt pozycji zamówienia na podstawie danych z żądania przetwarzania.
     * Zakładamy, że pole orderItemIdentifier zawiera identyfikator MenuItem.
     *
     * @param request dane z żądania przetwarzania zamówienia
     * @return obiekt OrdersItem
     */
    public OrdersItem buildOrderItem(RequestOrderProcessing request) {
        BigDecimal unitPrice = BigDecimal.TEN; // przykładowa cena jednostkowa
        BigDecimal totalPrice = unitPrice.multiply(BigDecimal.valueOf(request.getOrderItemQuantity()));
        MenuItem menuItem = MenuItem.builder()
                .menuItemId(Long.valueOf(request.getOrderItemIdentifier()))
                .build();
        return OrdersItem.builder()
                .menuItem(menuItem)
                .quantity(request.getOrderItemQuantity())
                .unitPrice(unitPrice)
                .totalPrice(totalPrice)
                .itemNotes(request.getComment())
                .build();
    }

    /**
     * Aktualizuje status zamówienia na zakończone (np. DELIVERED) i zapisuje zmiany w bazie.
     *
     * @param order zamówienie, które ma zostać zakończone
     * @param processingStartTime czas rozpoczęcia przetwarzania (przykładowo używany do dalszych operacji)
     * @return zaktualizowane zamówienie
     */
    @Transactional
    public Orders markOrderAsCompleted(Orders order, OffsetDateTime processingStartTime) {
        Orders updatedOrder = order.toBuilder()
                .ordersStatus(OrdersStatusEnumDomain.DELIVERED)
                .build();
        return ordersDAO.save(updatedOrder);
    }

    /**
     * Zapisuje zamówienie w bazie.
     *
     * @param order zamówienie do zapisania
     * @return zapisane zamówienie
     */
    @Transactional
    public Orders saveOrder(Orders order) {
        return ordersDAO.save(order);
    }

    /**
     * Wyszukuje wszystkie zamówienia dla danego klienta.
     *
     * @param customerId unikalny identyfikator klienta
     * @return lista zamówień powiązanych z klientem
     */
    @Transactional(readOnly = true)
    public List<Orders> findOrdersByCustomer(Long customerId) {
        return ordersDAO.findByCustomer_CustomerId(customerId);
    }

    /**
     * Wyszukuje zamówienia o określonym statusie.
     *
     * @param status status zamówienia (np. PENDING, DELIVERED)
     * @return lista zamówień o podanym statusie
     */
    @Transactional(readOnly = true)
    public List<Orders> findOrdersByStatus(OrdersStatusEnumDomain status) {
        return ordersDAO.findByOrdersStatus(status);
    }

    /**
     * Wyszukuje zamówienia utworzone po określonej dacie.
     *
     * @param dateTime data, po której zamówienia zostały utworzone
     * @return lista zamówień utworzonych po podanej dacie
     */
    @Transactional(readOnly = true)
    public List<Orders> findOrdersCreatedAfter(OffsetDateTime dateTime) {
        return ordersDAO.findByOrdersDateTimeAfter(dateTime);
    }

    /**
     * Wyszukuje zamówienia o łącznej wartości większej lub równej podanej kwocie.
     *
     * @param totalAmount minimalna łączna wartość zamówienia
     * @return lista zamówień spełniających kryterium wartości
     */
    @Transactional(readOnly = true)
    public List<Orders> findOrdersByTotalAmountGreaterThanEqual(BigDecimal totalAmount) {
        return ordersDAO.findByTotalAmountGreaterThanEqual(totalAmount);
    }

    /**
     * Wyszukuje zamówienia, które nie mają przypisanej dostawy.
     *
     * @return lista zamówień bez dostawy
     */
    @Transactional(readOnly = true)
    public List<Orders> findOrdersWithoutDelivery() {
        return ordersDAO.findOrdersWithoutDelivery();
    }

    @Transactional(readOnly = true)
    public List<Orders> getAllOrders() {
        return ordersDAO.findAll();
    }

    /**
     * Buduje raport przychodowy za podany okres.
     *
     * @param startDate początkowa data raportu
     * @param endDate końcowa data raportu
     * @return obiekt RevenueReport zawierający łączny przychód, liczbę zamówień i średnią wartość zamówienia
     */
    @Transactional
    public ViewRevenueReport getRevenueReport(OffsetDateTime startDate, OffsetDateTime endDate) {
        // Przykładowo pobieramy wszystkie zamówienia z określonego przedziału czasowego, które mają status DELIVERED
        List<Orders> orders = ordersDAO.findByOrdersDateTimeBetween(startDate, endDate);

        BigDecimal totalRevenue = orders.stream()
                .map(Orders::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        int totalOrders = orders.size();

        BigDecimal averageOrderValue = totalOrders > 0
                ? totalRevenue.divide(BigDecimal.valueOf(totalOrders), 2, RoundingMode.HALF_UP)
                : BigDecimal.ZERO;

        return ViewRevenueReport.builder()
                .startDate(startDate)
                .endDate(endDate)
                .totalRevenue(totalRevenue)
                .totalOrders(totalOrders)
                .averageOrderValue(averageOrderValue)
                .build();
    }
}

    /*
    Powyżej przykład implementacji serwisu OrderService w aplikacji Yummy – analogicznie do modułów zamówień
    (CarServiceRequestService) w aplikacji cardealership. W tej wersji OrderService odpowiada za tworzenie (budowanie)
    obiektu zamówienia, wystawianie faktury, wyszukiwanie aktywnego zamówienia, budowanie pozycji zamówienia
    oraz aktualizację statusu zamówienia. Warto przy tym zauważyć, że przyjmujemy, iż interfejs OrdersDAO
    (oraz jego implementacja) udostępnia również metodę zapisu (save), której sygnaturę można dodać do interfejsu,
    nawet jeśli w oryginalnym opisie jej nie ma.
    */

    /*
    Uwagi
    - W powyższej implementacji założono, że interfejs OrdersDAO rozszerza operacje CRUD (np. metodę save(Orders order)),
    co jest typowe przy implementacjach wykorzystujących Spring Data JPA. Jeśli interfejs nie zawiera metody save,
    należałoby go rozszerzyć lub użyć dedykowanego repozytorium.

    - Metody buildOrder oraz buildInvoice są przykładami sposobu inicjowania obiektów domenowych na podstawie danych
    z żądań. W rzeczywistej aplikacji mogą one wymagać dodatkowych operacji walidacyjnych, kalkulacji cen czy powiązań
    z innymi encjami.

    - W metodzie buildOrderItem przyjęto, że identyfikator pozycji zamówienia (orderItemIdentifier) jest przekazywany
    jako ciąg znaków reprezentujący identyfikator MenuItem. W zależności od modelu domenowego konieczne może być
    pobranie pełnych danych pozycji z katalogu produktów.


    Taka implementacja OrderService pozwala na centralizację logiki biznesowej związanej z obsługą zamówień w aplikacji
    Yummy, analogicznie do rozwiązań zastosowanych w module car dealership.
    */