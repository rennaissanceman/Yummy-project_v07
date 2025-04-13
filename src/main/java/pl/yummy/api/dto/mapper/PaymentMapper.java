package pl.yummy.api.dto.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.yummy.api.dto.PaymentDTO;
import pl.yummy.domain.Orders;
import pl.yummy.domain.Payment;
import pl.yummy.domain.enums.PaymentMethodStatusEnumDomain;
import pl.yummy.domain.enums.PaymentStatusEnumDomain;

import java.time.OffsetDateTime;

@Mapper(componentModel = "spring", uses = {OffsetDateTimeMapper.class})
public interface PaymentMapper extends OffsetDateTimeMapper{

    // Nadpisujemy metody konwersji z OffsetDateTimeMapper z unikalnymi kwalifikatorami
    @Override
    @Named("paymentMapOffsetDateTimeToString")
    default String mapOffsetDateTimeToString(OffsetDateTime offsetDateTime) {
        return OffsetDateTimeMapper.super.mapOffsetDateTimeToString(offsetDateTime);
    }

    @Override
    @Named("paymentMapStringToOffsetDateTime")
    default OffsetDateTime mapStringToOffsetDateTime(String dateTimeString) {
        return OffsetDateTimeMapper.super.mapStringToOffsetDateTime(dateTimeString);
    }

    @Mapping(source = "orders.ordersId", target = "ordersId")
    @Mapping(source = "paymentMethod", target = "paymentMethod", qualifiedByName = "mapPaymentMethod")
    @Mapping(source = "paymentStatus", target = "paymentStatus", qualifiedByName = "mapPaymentStatus")
    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "paymentMapOffsetDateTimeToString")
    @Mapping(source = "updatedAt", target = "updatedAt", qualifiedByName = "paymentMapOffsetDateTimeToString")
    PaymentDTO toDTO(Payment payment);

    // Mapowanie odwrotne z PaymentDTO do Payment.
    // Ignorujemy pola "invoice" oraz "receipt", bo nie ma ich w DTO
    @InheritInverseConfiguration(name = "toDTO")
    @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "paymentMapStringToOffsetDateTime")
    @Mapping(target = "updatedAt", source = "updatedAt", qualifiedByName = "paymentMapStringToOffsetDateTime")
    @Mapping(target = "orders", source = "ordersId", qualifiedByName = "mapOrdersIdToOrders")
    @Mapping(target = "paymentMethod", source = "paymentMethod", qualifiedByName = "mapPaymentMethodToEnum")
    @Mapping(target = "paymentStatus", source = "paymentStatus", qualifiedByName = "mapPaymentStatusToEnum")
    @Mapping(target = "invoice", ignore = true)
    @Mapping(target = "receipt", ignore = true)
    Payment toDomain(PaymentDTO paymentDTO);

    @Named("mapPaymentMethod")
    default String mapPaymentMethod(PaymentMethodStatusEnumDomain paymentMethod) {
        return paymentMethod == null ? null : paymentMethod.name();
    }

    @Named("mapPaymentMethodToEnum")
    default PaymentMethodStatusEnumDomain mapPaymentMethodToEnum(String paymentMethod) {
        return paymentMethod == null ? null : PaymentMethodStatusEnumDomain.valueOf(paymentMethod);
    }

    @Named("mapPaymentStatus")
    default String mapPaymentStatus(PaymentStatusEnumDomain paymentStatus) {
        return paymentStatus == null ? null : paymentStatus.name();
    }

    @Named("mapPaymentStatusToEnum")
    default PaymentStatusEnumDomain mapPaymentStatusToEnum(String paymentStatus) {
        return paymentStatus == null ? null : PaymentStatusEnumDomain.valueOf(paymentStatus);
    }

    @Named("mapOrdersIdToOrders")
    default Orders mapOrdersIdToOrders(Long ordersId) {
        if (ordersId == null) {
            return null;
        }
        return Orders.builder().ordersId(ordersId).build();
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