package pl.yummy.business.dao;

import pl.yummy.domain.Delivery;
import pl.yummy.domain.enums.DeliveryStatusEnumDomain;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface DeliveryDAO {


    /**
     * Znajduje dostawę o podanym unikalnym identyfikatorze.
     *
     * @param id unikalny identyfikator dostawy
     * @return Optional zawierający znalezioną dostawę lub pusty, jeśli nie istnieje
     */
    Optional<Delivery> findById(Long id);

    /**
     * Pobiera listę wszystkich dostaw.
     *
     * @return lista wszystkich dostaw
     */
    List<Delivery> findAll();

    /**
     * Zapisuje nową lub zaktualizowaną dostawę.
     *
     * @param delivery obiekt dostawy do zapisania
     * @return zapisany obiekt dostawy
     */
    Delivery save(Delivery delivery);

    /**
     * Usuwa podaną dostawę.
     *
     * @param delivery obiekt dostawy do usunięcia
     */
    void delete(Delivery delivery);

    /*
     * Znajduje dostawy powiązane z określonym kurierem.
     *
     * @param courierId identyfikator kuriera
     * @return lista dostaw przypisanych do danego kuriera
     */
    List<Delivery> findByCourier_CourierId(Long courierId);


    /*
     * Znajduje dostawy o określonym statusie.
     *
     * @param deliveryStatus status dostawy (np. PENDING, DELIVERED)
     * @return lista dostaw o podanym statusie
     */
    List<Delivery> findByDeliveryStatus(DeliveryStatusEnumDomain deliveryStatus);


    /*
     * Znajduje dostawy, których data rozpoczęcia jest po podanej dacie.
     *
     * @param startTime data, po której dostawy powinny się rozpocząć
     * @return lista dostaw rozpoczynających się po podanej dacie
     */
    List<Delivery> findByStarTimeAfter(OffsetDateTime startTime);


    /*
     * Znajduje dostawy, które są opóźnione – czyli ich rzeczywisty czas dostarczenia jest późniejszy niż planowany,
     * przy czym planowany czas musi być ustawiony.
     *
     * @param actualDeliveryDateTime rzeczywisty czas dostarczenia
     * @return lista dostaw spełniających kryteria opóźnienia
     */
    List<Delivery> findByActualDeliveryDateTimeAfterAndEstimatedDeliveryTimeIsNotNull(OffsetDateTime actualDeliveryDateTime);



    /*
     * Znajduje dostawy przypisane do określonego obszaru dostawy.
     *
     * @param availableDeliveryAreaId identyfikator obszaru dostawy
     * @return lista dostaw dla danego obszaru
     */
    List<Delivery> findByAvailableDeliveryArea_AvailableDeliveryAreaId(Long availableDeliveryAreaId);

}



