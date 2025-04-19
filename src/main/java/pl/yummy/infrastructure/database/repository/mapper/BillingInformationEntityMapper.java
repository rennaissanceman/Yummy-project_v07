package pl.yummy.infrastructure.database.repository.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.yummy.domain.BillingInformation;
import pl.yummy.infrastructure.database.entity.BillingInformationEntity;
import pl.yummy.infrastructure.database.repository.mapper.enums.PaymentMethodStatusEnumMapper;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {
                CustomerEntityMapper.class,
                AddressEntityMapper.class,
                PaymentMethodStatusEnumMapper.class,
                PaymentEntityMapper.class,
                InvoiceEntityMapper.class
        }
)
public interface BillingInformationEntityMapper {


    @Mapping(target = "invoices", ignore = true)
    BillingInformation mapFromEntity(BillingInformationEntity entity, @Context CustomerEntityMapper customerMapper);

    @Mapping(target = "invoices", ignore = true)
    BillingInformationEntity mapToEntity(BillingInformation domain, @Context CustomerEntityMapper customerMapper);

}
