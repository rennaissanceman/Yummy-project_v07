package pl.yummy.infrastructure.database.repository.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.yummy.domain.Customer;
import pl.yummy.infrastructure.database.entity.CustomerEntity;
import pl.yummy.infrastructure.database.repository.mapper.enums.PaymentMethodStatusEnumMapper;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {
                UserAuthEntityMapper.class,
                BillingInformationEntityMapper.class,
                CustomerAddressEntityMapper.class,
                PaymentEntityMapper.class,            // <<< tutaj
                PaymentMethodEntityMapper.class,
                PaymentMethodStatusEnumMapper.class   // <<< i tutaj
        }
)
public interface CustomerEntityMapper {

    Customer mapFromEntity(CustomerEntity entity,
                           @Context BillingInformationEntityMapper billingMapper);

    CustomerEntity mapToEntity(Customer domain,
                               @Context BillingInformationEntityMapper billingMapper);
}
