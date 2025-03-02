package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.yummy.api.dto.PaymentMethodDTO;
import pl.yummy.domain.PaymentMethod;
import pl.yummy.domain.enums.PaymentMethodStatusEnumDomain;

@Mapper(componentModel = "spring")
public interface PaymentMethodMapper {

    @Mapping(source = "paymentMethodStatus", target = "paymentMethodStatus", qualifiedByName = "mapPaymentMethodStatus")
    PaymentMethodDTO toDTO(PaymentMethod paymentMethod);

    @Named("mapPaymentMethodStatus")
    default String mapPaymentMethodStatus(PaymentMethodStatusEnumDomain status) {
        return status == null ? null : status.name();
    }
}

    /*
    Wyjaśnienie:
    - @Mapper(componentModel = "spring")
    Mapper jest rejestrowany jako bean Springa, co ułatwia jego wstrzykiwanie w innych komponentach.

    - @Mapping dla paymentMethodStatus
    Konwertujemy wartość enuma PaymentMethodStatusEnumDomain na String przy użyciu kwalifikowanej metody
    mapPaymentMethodStatus.

    - Pozostałe pola
    Pola takie jak paymentMethodId, paymentMethodName, description, isActive, createdAt i updatedAt są mapowane
    automatycznie, ponieważ mają zgodne nazwy i typy między domeną a DTO.

    - Ignorowanie pola payment
    Pole payment nie występuje w DTO, dlatego nie jest mapowane.


    Takie rozwiązanie zapewnia spójne i czytelne mapowanie obiektu PaymentMethod na PaymentMethodDTO.
    */