package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.yummy.api.dto.OwnerDTO;
import pl.yummy.domain.Owner;

@Mapper(componentModel = "spring")
public interface OwnerMapper {

    OwnerDTO toDTO(Owner owner);
}
