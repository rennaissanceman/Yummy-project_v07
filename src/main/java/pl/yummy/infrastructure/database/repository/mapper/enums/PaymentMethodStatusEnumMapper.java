package pl.yummy.infrastructure.database.repository.mapper.enums;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import pl.yummy.domain.enums.PaymentMethodStatusEnumDomain;
import pl.yummy.infrastructure.database.entity.enums.PaymentMethodStatus;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentMethodStatusEnumMapper {

    @Named("toDomain")
    static PaymentMethodStatusEnumDomain toDomain(PaymentMethodStatus paymentMethodStatus) {
        if (paymentMethodStatus == null) {
            return null;
        }
        return PaymentMethodStatusEnumDomain.valueOf(paymentMethodStatus.name());
    }

    @Named("toEntity")
    static PaymentMethodStatus toEntity(PaymentMethodStatusEnumDomain paymentMethodStatusEnumDomain) {
        if (paymentMethodStatusEnumDomain == null) {
            return null;
        }
        return PaymentMethodStatus.valueOf(paymentMethodStatusEnumDomain.name());
    }
}
