package pl.yummy.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.yummy.business.dao.DeliveryDAO;
import pl.yummy.domain.Address;
import pl.yummy.domain.Delivery;
import pl.yummy.domain.DeliveryStatusOverviewView;
import pl.yummy.domain.enums.DeliveryStatusEnumDomain;
import pl.yummy.domain.exception.NotFoundException;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class DeliveryService {

    private final DeliveryDAO deliveryDAO;


    /*
     * Pobiera wszystkie dostawy.
     *
     * @return lista wszystkich dostaw
     */
    @Transactional(readOnly = true)
    public List<Delivery> getAllDeliveries() {
        return deliveryDAO.findAll();
    }

    /*
     * Znajduje dostawę o podanym unikalnym identyfikatorze.
     *
     * @param id unikalny identyfikator dostawy
     * @return dostawa
     * @throws NotFoundException gdy dostawa nie zostanie znaleziona
     */
    @Transactional(readOnly = true)
    public Delivery getDeliveryById(Long id) {
        return deliveryDAO.findById(id)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono dostawy o ID: " + id));
    }

    /*
     * Tworzy nową dostawę.
     * Jeśli numer dostawy nie został ustawiony, generowany jest unikalny numer.
     * Jeśli status dostawy nie został ustawiony, ustawiany jest domyślny status PENDING.
     * Jeśli data rozpoczęcia dostawy nie została ustawiona, ustawiana jest aktualna data i czas.
     *
     * @param delivery obiekt dostawy do utworzenia
     * @return utworzona dostawa
     */
    @Transactional
    public Delivery createDelivery(Delivery delivery) {
        Delivery newDelivery = delivery;
        if (newDelivery.getDeliveryNumber() == null || newDelivery.getDeliveryNumber().isEmpty()) {
            newDelivery = newDelivery.toBuilder().deliveryNumber(generateUniqueDeliveryNumber()).build();
        }
        if (newDelivery.getDeliveryStatus() == null) {
            newDelivery = newDelivery.toBuilder().deliveryStatus(DeliveryStatusEnumDomain.PENDING).build();
        }
        if (newDelivery.getStarTime() == null) {
            newDelivery = newDelivery.toBuilder().starTime(OffsetDateTime.now()).build();
        }
        return deliveryDAO.save(newDelivery);
    }

    /*
     * Aktualizuje istniejącą dostawę.
     * Wyszukuje istniejącą dostawę, a następnie tworzy nową instancję na podstawie poprzedniej,
     * z nadpisanymi polami pochodzącymi z przekazanego obiektu.
     *
     * @param updatedDelivery obiekt dostawy z nowymi danymi
     * @return zaktualizowana dostawa
     */
    @Transactional
    public Delivery updateDelivery(Delivery updatedDelivery) {
        Delivery existing = getDeliveryById(updatedDelivery.getDeliveryId());
        Delivery newDelivery = existing.toBuilder()
                .deliveryNumber(updatedDelivery.getDeliveryNumber())
                .deliveryStatus(updatedDelivery.getDeliveryStatus())
                .starTime(updatedDelivery.getStarTime())
                .endTime(updatedDelivery.getEndTime())
                .estimatedDeliveryTime(updatedDelivery.getEstimatedDeliveryTime())
                .actualDeliveryDateTime(updatedDelivery.getActualDeliveryDateTime())
                .deliveryFee(updatedDelivery.getDeliveryFee())
                .deliveryNotes(updatedDelivery.getDeliveryNotes())
                // Pozostałe pola (orders, availableDeliveryArea, courier) zachowujemy z istniejącego obiektu
                .orders(existing.getOrders())
                .availableDeliveryArea(existing.getAvailableDeliveryArea())
                .courier(existing.getCourier())
                .build();
        return deliveryDAO.save(newDelivery);
    }

    /*
     * Usuwa dostawę o podanym unikalnym identyfikatorze.
     *
     * @param id unikalny identyfikator dostawy do usunięcia
     */
    @Transactional
    public void deleteDelivery(Long id) {
        Delivery existing = getDeliveryById(id);
        deliveryDAO.delete(existing);
    }


    /*
     * Pobiera dostawy przypisane do określonego kuriera.
     *
     * @param courierId identyfikator kuriera
     * @return lista dostaw przypisanych do danego kuriera
     */
    @Transactional(readOnly = true)
    public List<Delivery> findDeliveriesByCourier(Long courierId) {
        return deliveryDAO.findByCourier_CourierId(courierId);
    }

    /*
     * Pobiera dostawy o określonym statusie.
     *
     * @param status status dostawy (np. PENDING, DELIVERED)
     * @return lista dostaw o podanym statusie
     */
    @Transactional(readOnly = true)
    public List<Delivery> findDeliveriesByStatus(DeliveryStatusEnumDomain status) {
        return deliveryDAO.findByDeliveryStatus(status);
    }

    /*
     * Pobiera dostawy, których data rozpoczęcia jest po podanej dacie.
     *
     * @param startTime data, po której dostawy mają się rozpocząć
     * @return lista dostaw rozpoczynających się po podanej dacie
     */
    @Transactional(readOnly = true)
    public List<Delivery> findDeliveriesStartingAfter(OffsetDateTime startTime) {
        return deliveryDAO.findByStarTimeAfter(startTime);
    }

    /*
     * Pobiera dostawy, które są opóźnione – czyli ich rzeczywisty czas dostarczenia jest późniejszy niż planowany,
     * przy czym planowany czas musi być ustawiony.
     *
     * @param actualDeliveryDateTime rzeczywisty czas dostarczenia
     * @return lista dostaw spełniających kryteria opóźnienia
     */
    @Transactional(readOnly = true)
    public List<Delivery> findLateDeliveries(OffsetDateTime actualDeliveryDateTime) {
        return deliveryDAO.findByActualDeliveryDateTimeAfterAndEstimatedDeliveryTimeIsNotNull(actualDeliveryDateTime);
    }

    /*
     * Pobiera dostawy przypisane do określonego obszaru dostawy.
     *
     * @param availableDeliveryAreaId identyfikator obszaru dostawy
     * @return lista dostaw dla danego obszaru
     */
    @Transactional(readOnly = true)
    public List<Delivery> findDeliveriesByAvailableDeliveryArea(Long availableDeliveryAreaId) {
        return deliveryDAO.findByAvailableDeliveryArea_AvailableDeliveryAreaId(availableDeliveryAreaId);
    }

    /*
     * Przelicza opłatę dostawy na podstawie zadanych parametrów.
     * Przykładowa logika: nowa opłata = opłata bazowa + mnożnik.
     *
     * @param deliveryId identyfikator dostawy
     * @param baseFee opłata bazowa
     * @param multiplier mnożnik dodatkowy
     * @return dostawa z zaktualizowaną opłatą
     */
    @Transactional
    public Delivery recalculateDeliveryFee(Long deliveryId, BigDecimal baseFee, BigDecimal multiplier) {
        Delivery delivery = getDeliveryById(deliveryId);
        BigDecimal newFee = baseFee.add(multiplier);
        Delivery newDelivery = delivery.toBuilder()
                .deliveryFee(newFee)
                .build();
        return deliveryDAO.save(newDelivery);
    }

    /*
     * Generuje unikalny numer dostawy.
     *
     * @return unikalny numer dostawy
     */
    private String generateUniqueDeliveryNumber() {
        return "DEL-" + System.currentTimeMillis();
    }

    /*
     * Pobiera statystyki dostaw dla danego obszaru dostawy.
     *
     * @param availableDeliveryAreaId identyfikator obszaru dostawy
     * @return DeliveryStatusOverview zawierający m.in. łączną liczbę dostaw, średni czas dostawy (w minutach),
     *         liczbę dostaw opóźnionych, zakończonych sukcesem i tych będących w tranzycie
     */
    @Transactional(readOnly = true)
    public DeliveryStatusOverviewView getDeliveryStatusOverview(Long availableDeliveryAreaId) {
        // Pobieramy wszystkie dostawy dla danego obszaru
        List<Delivery> deliveries = deliveryDAO.findByAvailableDeliveryArea_AvailableDeliveryAreaId(availableDeliveryAreaId);

        int totalDeliveries = deliveries.size();
        int lateDeliveries = 0;
        int successfulDeliveries = 0;
        int inTransit = 0;
        double totalDeliveryTime = 0;
        int countDeliveryTime = 0;

        String deliveryAreaName = "";
        if (!deliveries.isEmpty()) {
            // Przykładowo ustawiamy nazwę obszaru na podstawie miasta pierwszego dostawy
            Address address = deliveries.get(0).getAvailableDeliveryArea().getAddress();
            if (address != null) {
                deliveryAreaName = address.getCity();
            }
        }

        for (Delivery d : deliveries) {
            // Jeśli dostępne są czasy rzeczywiste i szacowane, obliczamy różnicę (w minutach)
            if (d.getActualDeliveryDateTime() != null && d.getEstimatedDeliveryTime() != null) {
                long diffMinutes = Duration.between(d.getEstimatedDeliveryTime(), d.getActualDeliveryDateTime()).toMinutes();
                totalDeliveryTime += diffMinutes;
                countDeliveryTime++;
                // Jeśli rzeczywisty czas jest późniejszy niż szacowany, dostawa jest opóźniona
                if (diffMinutes > 0) {
                    lateDeliveries++;
                }
            }
            // Zakładamy, że status DELIVERED oznacza udaną dostawę
            if (d.getDeliveryStatus() != null && d.getDeliveryStatus().name().equals("DELIVERED")) {
                successfulDeliveries++;
            }
            // Jeśli status to IN_TRANSIT, zliczamy dostawy będące w tranzycie
            if (d.getDeliveryStatus() != null && d.getDeliveryStatus().name().equals("IN_TRANSIT")) {
                inTransit++;
            }
        }

        double averageDeliveryTime = countDeliveryTime > 0 ? totalDeliveryTime / countDeliveryTime : 0;

        return DeliveryStatusOverviewView.builder()
                .deliveryAreaId(availableDeliveryAreaId)
                .deliveryAreaName(deliveryAreaName)
                .totalDeliveries(totalDeliveries)
                .averageDeliveryTime(averageDeliveryTime)
                .lateDeliveries(lateDeliveries)
                .successfulDeliveries(successfulDeliveries)
                .inTransit(inTransit)
                .build();
    }
}

    /*
    _____________________________________________________________________
    DeliveryService

    - Obsługuje operacje związane z dostawami, takie jak wyszukiwanie dostawy po identyfikatorze, statusie,
    czasie rozpoczęcia, opóźnionych dostawach czy dostawach przypisanych do określonego obszaru;
    umożliwia też zapisywanie i usuwanie dostaw.
    - Wstrzykiwany komponent: DeliveryDAO.

    */
