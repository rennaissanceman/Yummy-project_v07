package pl.yummy.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.yummy.domain.*;
import pl.yummy.infrastructure.database.entity.AddressEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressEntityMapper {


        @Mapping(target = "restaurant", ignore = true)
        @Mapping(target = "availableDeliveryArea", ignore = true)
        @Mapping(target = "deliveryAddress", ignore = true)
        @Mapping(target = "billingInformation", ignore = true)
        Address mapFromEntity(AddressEntity entity);

        @Mapping(target = "restaurant", ignore = true)
        @Mapping(target = "availableDeliveryArea", ignore = true)
        @Mapping(target = "deliveryAddress", ignore = true)
        @Mapping(target = "billingInformation", ignore = true)
        AddressEntity mapToEntity(Address domain);

}
