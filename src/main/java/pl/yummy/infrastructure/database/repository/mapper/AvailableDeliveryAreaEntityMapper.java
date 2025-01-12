package pl.yummy.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.yummy.domain.AvailableDeliveryArea;
import pl.yummy.infrastructure.database.entity.AvailableDeliveryAreaEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {
        RestaurantEntityMapper.class, AddressEntityMapper.class
})
public interface AvailableDeliveryAreaEntityMapper {

        @Mapping(target = "customerAddresses", ignore = true) // Ignorujemy adresy klientów
        @Mapping(target = "orders", ignore = true)           // Ignorujemy zamówienia
        @Mapping(target = "deliveries", ignore = true)       // Ignorujemy dostawy
        AvailableDeliveryArea mapFromEntity(AvailableDeliveryAreaEntity entity);

        @Mapping(target = "customerAddresses", ignore = true)
        @Mapping(target = "orders", ignore = true)
        @Mapping(target = "deliveries", ignore = true)
        AvailableDeliveryAreaEntity mapToEntity(AvailableDeliveryArea domain);
}
