package pl.yummy.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.yummy.domain.CustomerAddress;
import pl.yummy.infrastructure.database.entity.CustomerAddressEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {
        CustomerEntityMapper.class, AvailableDeliveryAreaEntityMapper.class, AddressEntityMapper.class
})
public interface CustomerAddressEntityMapper {

    @Mapping(target = "orders", ignore = true) // Ignorujemy szczegóły zamówień
    CustomerAddress mapFromEntity(CustomerAddressEntity entity);

    @Mapping(target = "orders", ignore = true) // Ignorujemy szczegóły zamówień
    CustomerAddressEntity mapToEntity(CustomerAddress domain);
}
