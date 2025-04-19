package pl.yummy.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import pl.yummy.domain.PaymentMethod;
import pl.yummy.domain.enums.PaymentMethodStatusEnumDomain;
import pl.yummy.infrastructure.database.entity.PaymentMethodEntity;
import pl.yummy.infrastructure.database.repository.mapper.enums.PaymentMethodStatusEnumMapper;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = PaymentMethodStatusEnumMapper.class
)
public interface PaymentMethodEntityMapper {

    @Mapping(source = "paymentMethodStatus", target = "paymentMethodStatus")
    @Mapping(target = "payment", ignore = true)      // <<< ignorujemy tę relację
    PaymentMethod mapFromEntity(PaymentMethodEntity entity);

    @Mapping(source = "paymentMethodStatus", target = "paymentMethodStatus")
    @Mapping(target = "payment", ignore = true)      // <<< oraz w drugą stronę
    PaymentMethodEntity mapToEntity(PaymentMethod domain);
}
