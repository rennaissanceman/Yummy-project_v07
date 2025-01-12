package pl.yummy.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.yummy.domain.UserAuth;
import pl.yummy.infrastructure.database.entity.UserAuthEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserAuthEntityMapper {

    @Mapping(target = "owner", ignore = true) // Ignorujemy powiązanie z właścicielem
    @Mapping(target = "customer", ignore = true) // Ignorujemy powiązanie z klientem
    @Mapping(target = "courier", ignore = true) // Ignorujemy powiązanie z kurierem
    UserAuth mapFromEntity(UserAuthEntity entity);

    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "courier", ignore = true)
    UserAuthEntity mapToEntity(UserAuth domain);
}
