package pl.yummy.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.yummy.domain.Invoice;
import pl.yummy.infrastructure.database.entity.InvoiceEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {
        OrdersEntityMapper.class, PaymentEntityMapper.class, BillingInformationEntityMapper.class
})
public interface InvoiceEntityMapper {

    Invoice mapFromEntity(InvoiceEntity entity);

    InvoiceEntity mapToEntity(Invoice domain);
}
