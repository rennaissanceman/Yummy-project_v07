package pl.yummy.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.yummy.domain.Payment;
import pl.yummy.domain.PaymentMethod;
import pl.yummy.infrastructure.database.entity.PaymentEntity;
import pl.yummy.infrastructure.database.entity.PaymentMethodEntity;
import pl.yummy.infrastructure.database.repository.mapper.enums.PaymentMethodStatusEnumMapper;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = PaymentMethodStatusEnumMapper.class  // tylko ten mapper do enuma
)
public interface PaymentEntityMapper {

    // wskazujemy dokładnie, skąd brać enum i do którego pola domeny:
    @Mapping(source = "paymentMethod.paymentMethodStatus", target = "paymentMethod")
    Payment mapFromEntity(PaymentEntity entity);

    // odwrotnie przy zapisie:
    @Mapping(source = "paymentMethod",            // domenowy enum
            target = "paymentMethod.paymentMethodStatus")
    PaymentEntity mapToEntity(Payment domain);
}
