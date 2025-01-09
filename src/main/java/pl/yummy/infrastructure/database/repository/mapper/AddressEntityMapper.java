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


/*        default Address mapFromEntityWithDetails(AddressEntity entity) {
            return mapFromEntity(entity)
                    .withRestaurant(entity.getRestaurant() != null ?
                            pl.yummy.domain.Restaurant.builder()
                                    .id(entity.getRestaurant().getId())
                                    .name(entity.getRestaurant().getName())
                                    .build() : null)
                    .withAvailableDeliveryArea(entity.getAvailableDeliveryArea() != null ?
                            pl.yummy.domain.AvailableDeliveryArea.builder()
                                    .areaId(entity.getAvailableDeliveryArea().getAreaId())
                                    .areaName(entity.getAvailableDeliveryArea().getAreaName())
                                    .build() : null)
                    .withDeliveryAddress(entity.getDeliveryAddress() != null ?
                            pl.yummy.domain.CustomerAddress.builder()
                                    .customerId(entity.getDeliveryAddress().getCustomerId())
                                    .addressDetails(entity.getDeliveryAddress().getDetails())
                                    .build() : null)
                    .withBillingInformation(entity.getBillingInformation() != null ?
                            pl.yummy.domain.BillingInformation.builder()
                                    .infoId(entity.getBillingInformation().getInfoId())
                                    .details(entity.getBillingInformation().getDetails())
                                    .build() : null);
        }*/

        @Mapping(target = "restaurant.address", ignore = true)
        @Mapping(target = "availableDeliveryArea.address", ignore = true)
        @Mapping(target = "deliveryAddress.address", ignore = true)
        @Mapping(target = "billingInformation.address", ignore = true)
        AddressEntity mapToEntity(Address address);

}
