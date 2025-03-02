package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.yummy.api.dto.BillingInformationDTO;
import pl.yummy.domain.BillingInformation;

@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public interface BillingInformationMapper {

    @Mapping(source = "customer.customerId", target = "customerId")
    BillingInformationDTO toDTO(final BillingInformation billingInformation);

    // W przypadku mapowania z DTO do domeny,
    // pola, których nie można odwzorować bez dodatkowej logiki (np. Customer i Invoice)
    // są ignorowane. Można je później uzupełnić w serwisie.
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "invoices", ignore = true)
    BillingInformation toDomain(final BillingInformationDTO billingInformationDTO);
}

    /*
    Wyjaśnienie:
    - Adnotacja @Mapper(componentModel = "spring", uses = {AddressMapper.class})
    Umożliwia automatyczne generowanie implementacji mappera przez MapStruct oraz rejestrację beana w kontenerze Spring.
    Dzięki parametrowi uses wykorzystywany jest AddressMapper do mapowania zagnieżdżonego obiektu Address na AddressDTO.

    - Mapowanie pola customer:
    W metodzie toDTO używamy adnotacji @Mapping, aby wyekstrahować identyfikator klienta: customer.customerId
    jest przypisany do pola customerId w DTO.

    - Mapowanie w kierunku DTO → domena:
    W metodzie toDomain ignorujemy pola customer i invoices, ponieważ w DTO mamy tylko identyfikator klienta,
    a lista faktur (invoices) nie występuje. W praktyce te pola mogą być uzupełniane przez logikę biznesową
    lub inne komponenty (np. serwisy).



    Takie rozwiązanie jest spójne ze standardowym podejściem MapStruct, a w razie potrzeby można rozbudować mapper
    o dodatkowe metody czy logikę mapowania.
    */
