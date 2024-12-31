package pl.yummy.domain.enums;

public enum OrdersStatusEnumDomain {

    PENDING,
    CONFIRMED,
    PREPARING,
    READY_FOR_PICKUP,
    OUT_FOR_DELIVERY,
    DELIVERED,
    CANCELLED_BY_CUSTOMER,
    CANCELLED_BY_RESTAURANT,
    FAILED_DELIVERY,
    RETURNED,
    REFUNDED
}
