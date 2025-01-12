package pl.yummy.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.yummy.domain.BillingInformation;
import pl.yummy.infrastructure.database.entity.BillingInformationEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {
        CustomerEntityMapper.class, AddressEntityMapper.class
})
public interface BillingInformationEntityMapper {

    @Mapping(target = "invoices", ignore = true) // Ignorujemy szczegóły faktur
    BillingInformation mapFromEntity(BillingInformationEntity entity);

    @Mapping(target = "invoices", ignore = true) // Ignorujemy szczegóły faktur
    BillingInformationEntity mapToEntity(BillingInformation domain);

}
