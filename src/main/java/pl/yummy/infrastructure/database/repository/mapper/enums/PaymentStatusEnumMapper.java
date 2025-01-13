package pl.yummy.infrastructure.database.repository.mapper.enums;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import pl.yummy.domain.enums.PaymentStatusEnumDomain;
import pl.yummy.infrastructure.database.entity.enums.PaymentStatus;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentStatusEnumMapper {

    @Named("toDomain")
    static PaymentStatusEnumDomain toDomain(PaymentStatus paymentStatus) {
        if (paymentStatus == null) {
            return null;
        }
        return PaymentStatusEnumDomain.valueOf(paymentStatus.name());
    }

    @Named("toEntity")
    static PaymentStatus toEntity(PaymentStatusEnumDomain paymentStatusEnumDomain) {
        if (paymentStatusEnumDomain == null) {
            return null;
        }
        return PaymentStatus.valueOf(paymentStatusEnumDomain.name());
    }
}
