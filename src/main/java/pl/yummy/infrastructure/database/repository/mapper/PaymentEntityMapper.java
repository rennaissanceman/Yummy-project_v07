package pl.yummy.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.yummy.domain.Payment;
import pl.yummy.infrastructure.database.entity.PaymentEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentEntityMapper {

    @Mapping(target = "orders", ignore = true) // Ignorujemy zamówienie
    @Mapping(target = "paymentMethod", ignore = true) // Ignorujemy metodę płatności
    @Mapping(target = "invoice", ignore = true) // Ignorujemy fakturę
    @Mapping(target = "receipt", ignore = true) // Ignorujemy paragon
    Payment mapFromEntity(PaymentEntity entity);

    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "paymentMethod", ignore = true)
    @Mapping(target = "invoice", ignore = true)
    @Mapping(target = "receipt", ignore = true)
    PaymentEntity mapToEntity(Payment domain);
}
