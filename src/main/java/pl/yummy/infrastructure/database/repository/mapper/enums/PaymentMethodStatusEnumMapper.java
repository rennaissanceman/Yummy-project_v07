package pl.yummy.infrastructure.database.repository.mapper.enums;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import pl.yummy.domain.enums.PaymentMethodStatusEnumDomain;
import pl.yummy.infrastructure.database.entity.enums.PaymentMethodStatusEnumEntity;

@Mapper(componentModel = "spring")
public interface PaymentMethodStatusEnumMapper {

    // MapStruct wygeneruje wywołanie valueOf(...) dla Ciebie
    PaymentMethodStatusEnumDomain toDomain(PaymentMethodStatusEnumEntity status);
    PaymentMethodStatusEnumEntity toEntity(PaymentMethodStatusEnumDomain status);
}
