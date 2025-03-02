package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.yummy.api.dto.PaymentDTO;
import pl.yummy.domain.Payment;
import pl.yummy.domain.enums.PaymentMethodStatusEnumDomain;
import pl.yummy.domain.enums.PaymentStatusEnumDomain;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(source = "orders.ordersId", target = "ordersId")
    @Mapping(source = "paymentMethod", target = "paymentMethod", qualifiedByName = "mapPaymentMethod")
    @Mapping(source = "paymentStatus", target = "paymentStatus", qualifiedByName = "mapPaymentStatus")
    PaymentDTO toDTO(Payment payment);

    @Named("mapPaymentMethod")
    default String mapPaymentMethod(PaymentMethodStatusEnumDomain paymentMethod) {
        return paymentMethod == null ? null : paymentMethod.name();
    }

    @Named("mapPaymentStatus")
    default String mapPaymentStatus(PaymentStatusEnumDomain paymentStatus) {
        return paymentStatus == null ? null : paymentStatus.name();
    }
}

    /*
    Wyjaśnienie:
    - @Mapper(componentModel = "spring") – Mapper jest zarządzany przez Springa, dzięki czemu jego implementacja
    zostaje automatycznie zarejestrowana jako bean.

    - Mapping ordersId:
    @Mapping(source = "orders.ordersId", target = "ordersId") – wyodrębnia identyfikator zamówienia z obiektu orders
    w domenie.

    - Mapping pól enum:
    Używamy kwalifikowanych metod mapPaymentMethod oraz mapPaymentStatus, które konwertują enuma na String,
    zabezpieczając przed null.

    - Pozostałe pola (paymentId, amount, transactionId, createdAt, updatedAt, comment) są odwzorowywane automatycznie,
    gdyż mają zgodne nazwy i typy.


    Taka implementacja zapewnia spójne i bezpieczne mapowanie obiektu Payment na PaymentDTO.
    */