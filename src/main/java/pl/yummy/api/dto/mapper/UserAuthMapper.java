package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.yummy.api.dto.UserAuthDTO;
import pl.yummy.domain.UserAuth;

@Mapper(componentModel = "spring")
public interface UserAuthMapper {

    UserAuthDTO toDTO(UserAuth userAuth);
}

    /*
    Wyjaśnienie:
    - Prostota mapowania:
    DTO zawiera tylko podstawowe pola (userAuthId, phone, email, createdAt, updatedAt), które bezpośrednio odpowiadają
    polom w obiekcie domenowym UserAuth.

    Pominięcie wrażliwych danych:
    - Pola takie jak passwordHash, salt oraz powiązania z Owner, Customer i Courier nie są mapowane, co jest zgodne
    z celem uproszczonej reprezentacji danych uwierzytelniających.

    Integracja ze Springiem:
    - Dzięki @Mapper(componentModel = "spring") mapper jest rejestrowany jako bean Springa, co ułatwia
    jego wstrzykiwanie w innych komponentach.



    Jeśli w przyszłości pojawi się potrzeba mapowania danych wejściowych (DTO → domena), można rozważyć dodanie metody
    toDomain, jednak w przypadku danych uwierzytelniających często wystarcza mapowanie wyłącznie z domeny do DTO.
    */