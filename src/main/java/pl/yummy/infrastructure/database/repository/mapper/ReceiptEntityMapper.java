package pl.yummy.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.yummy.domain.Receipt;
import pl.yummy.infrastructure.database.entity.ReceiptEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {
        OrdersEntityMapper.class, PaymentEntityMapper.class
})
public interface ReceiptEntityMapper {

    Receipt mapFromEntity(ReceiptEntity entity);

    ReceiptEntity mapToEntity(Receipt domain);
}
