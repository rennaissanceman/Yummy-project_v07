package pl.yummy.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.yummy.business.dao.CourierDAO;
import pl.yummy.domain.Courier;
import pl.yummy.domain.enums.CourierStatusEnumDomain;
import pl.yummy.domain.exception.NotFoundException;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CourierService {

    private final CourierDAO courierDAO;

    /*
     * Znajduje kuriera na podstawie unikalnego numeru kuriera.
     *
     * @param courierNumber unikalny identyfikator (numer) kuriera
     * @return kurier, którego numer odpowiada podanemu identyfikatorowi
     * @throws NotFoundException gdy kurier nie zostanie znaleziony
     */
    @Transactional
    public Courier findByCourierNumber(String courierNumber) {
        return courierDAO.findByCourierNumber(courierNumber)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono kuriera o numerze: " + courierNumber));
    }

    /*
     * Pobiera listę wszystkich kurierów, których status to AVAILABLE.
     *
     * @return lista dostępnych kurierów
     */
    @Transactional
    public List<Courier> findByCourierStatus(CourierStatusEnumDomain status) {
        return courierDAO.findByCourierStatus(CourierStatusEnumDomain.AVAILABLE);
    }


    /*
     * Pobiera listę kurierów, których średnia ocena jest większa lub równa podanemu progowi.
     *
     * @param minimumRating minimalna średnia ocena, którą musi spełniać kurier
     * @return lista kurierów spełniających kryterium minimalnej oceny
     */
    @Transactional
    public List<Courier> findCouriersWithMinimumRating(Double minimumRating) {
        return courierDAO.findByAverageRatingsGreaterThanEqual(minimumRating);
    }

    /*
     * Pobiera listę kurierów zatrudnionych po podanej dacie.
     *
     * @param hireDate data, po której zatrudnieni są kurierzy
     * @return lista kurierów, którzy zostali zatrudnieni po określonej dacie
     */
    @Transactional
    public List<Courier> findCouriersHiredAfter(OffsetDateTime hireDate) {
        return courierDAO.findByHireDateAfter(hireDate);
    }

    /*
     * Pobiera listę kurierów korzystających z określonego typu pojazdu.
     *
     * @param vehicleType typ pojazdu, z którego korzysta kurier (np. "motocykl", "samochód")
     * @return lista kurierów używających pojazdu o podanym typie
     */
    @Transactional
    public List<Courier> findCouriersByVehicleType(String vehicleType) {
        return courierDAO.findByVehicleType(vehicleType);
    }
}


    /*
    4. CourierService

    Cel:
    Analogicznie do MechanicService w aplikacji cardealership, serwis ten wyszukuje dostępnych kurierów
    oraz konkretnych kurierów (np. na podstawie identyfikatora).
    */

    /*
    Podsumowanie
    Przedstawione serwisy w aplikacji Yummy – od składania zamówień, przez ich przetwarzanie,
    zarządzanie klientami i restauracjami, aż po obsługę płatności i dostaw – odpowiadają za rozdzielenie
    logiki biznesowej na mniejsze, wyspecjalizowane bloki. Każdy serwis korzysta z odpowiednich DAO (repozytoriów)
    i mapperów do konwersji między encjami a obiektami domenowymi, dzięki czemu aplikacja jest modularna,
    łatwa do testowania i utrzymania.

    W powyższych przykładach kodu zastosowano adnotacje Spring (np. @Service, @Transactional) oraz podejście oparte
    o konstruktorową injekcję zależności, co jest zgodne z dobrymi praktykami projektowymi.

    Takie podejście pozwala na spójne odwzorowanie logiki biznesowej z aplikacji cardealership w kontekście domeny Yummy.

    ___________________________________________
    CourierService

    - Odpowiada za operacje związane z kurierami, np. wyszukiwanie kuriera po numerze, statusie, średniej ocenie,
    dacie zatrudnienia czy typie pojazdu.
    - Wstrzykiwany komponent: CourierDAO.

    */