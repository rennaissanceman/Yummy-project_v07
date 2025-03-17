package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.yummy.api.dto.PaymentProcessingRequestDTO;
import pl.yummy.domain.PaymentProcessingRequest;
import pl.yummy.domain.enums.PaymentMethodStatusEnumDomain;

@Mapper(componentModel = "spring")
public interface PaymentProcessingRequestMapper {

    @Mapping(source = "paymentMethod", target = "paymentMethod", qualifiedByName = "enumToString")
    PaymentProcessingRequestDTO toDTO(PaymentProcessingRequest request);

    @Mapping(source = "paymentMethod", target = "paymentMethod", qualifiedByName = "stringToEnum")
    PaymentProcessingRequest toDomain(PaymentProcessingRequestDTO dto);

    @Named("enumToString")
    default String enumToString(PaymentMethodStatusEnumDomain paymentMethod) {
        return paymentMethod == null ? null : paymentMethod.name();
    }

    @Named("stringToEnum")
    default PaymentMethodStatusEnumDomain stringToEnum(String paymentMethod) {
        return paymentMethod == null ? null : PaymentMethodStatusEnumDomain.valueOf(paymentMethod);
    }
}

    /*
    Wyjaśnienie:
    - @Mapper(componentModel = "spring") – Mapper jest rejestrowany jako bean w kontenerze Springa.

    - toDTO – Metoda mapuje obiekt domenowy RequestPaymentProcessing na DTO RequestPaymentProcessingDTO.
    Pole paymentMethod jest konwertowane przy użyciu kwalifikowanej metody enumToString.

    - toDomain – Metoda mapuje DTO z powrotem na obiekt domenowy, konwertując String na enum przy użyciu
    metody stringToEnum.

    - Metody kwalifikowane – enumToString oraz stringToEnum zabezpieczają mapowanie pola paymentMethod,
    zapewniając, że w przypadku wartości null zostanie zwrócony null lub odpowiednia wartość enuma.


    Takie rozwiązanie jest spójne z podejściem stosowanym w innych mapperach i zapewnia poprawną konwersję między typami.
    */