package pl.yummy.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.yummy.domain.PaymentMethod;
import pl.yummy.infrastructure.database.entity.PaymentMethodEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentMethodEntityMapper {

    @Mapping(target = "payment", ignore = true) // Ignorujemy powiązanie z płatnością
    PaymentMethod mapFromEntity(PaymentMethodEntity entity);

    @Mapping(target = "payment", ignore = true)
    PaymentMethodEntity mapToEntity(PaymentMethod domain);
}
