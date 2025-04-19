package pl.yummy.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.yummy.domain.CustomerAddress;
import pl.yummy.infrastructure.database.entity.CustomerAddressEntity;
import pl.yummy.infrastructure.database.repository.mapper.enums.PaymentMethodStatusEnumMapper;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {
                CustomerEntityMapper.class,
                AvailableDeliveryAreaEntityMapper.class,
                AddressEntityMapper.class,
                PaymentEntityMapper.class,              // żeby zmapować płatność
                PaymentMethodEntityMapper.class,        // żeby zmapować metodę płatności
                PaymentMethodStatusEnumMapper.class     // żeby zmapować enum statusów
        }
)
public interface CustomerAddressEntityMapper {

    @Mapping(target = "orders", ignore = true)
    CustomerAddress mapFromEntity(CustomerAddressEntity entity);
    @Mapping(target = "orders", ignore = true)
    CustomerAddressEntity mapToEntity(CustomerAddress domain);
}
