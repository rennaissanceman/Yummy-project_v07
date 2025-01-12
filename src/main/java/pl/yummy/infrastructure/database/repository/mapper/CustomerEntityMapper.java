package pl.yummy.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.yummy.domain.Customer;
import pl.yummy.infrastructure.database.entity.CustomerEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {
        UserAuthEntityMapper.class, BillingInformationEntityMapper.class, CustomerAddressEntityMapper.class
})
public interface CustomerEntityMapper {

    @Mapping(target = "orders", ignore = true) // Ignorujemy szczegóły zamówień
    Customer mapFromEntity(CustomerEntity entity);

    @Mapping(target = "orders", ignore = true) // Ignorujemy szczegóły zamówień
    CustomerEntity mapToEntity(Customer domain);
}
