package pl.yummy.infrastructure.database.repository.mapper.enums;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import pl.yummy.domain.enums.PaymentMethodStatusEnumDomain;
import pl.yummy.infrastructure.database.entity.enums.PaymentMethodStatusEnumEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentMethodStatusEnumMapper {

    @Named("toDomain")
    static PaymentMethodStatusEnumDomain toDomain(PaymentMethodStatusEnumEntity paymentMethodStatus) {
        if (paymentMethodStatus == null) {
            return null;
        }
        return PaymentMethodStatusEnumDomain.valueOf(paymentMethodStatus.name());
    }

    @Named("toEntity")
    static PaymentMethodStatusEnumEntity toEntity(PaymentMethodStatusEnumDomain paymentMethodStatusEnumDomain) {
        if (paymentMethodStatusEnumDomain == null) {
            return null;
        }
        return PaymentMethodStatusEnumEntity.valueOf(paymentMethodStatusEnumDomain.name());
    }
}
