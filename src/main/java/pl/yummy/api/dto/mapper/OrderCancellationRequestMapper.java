package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.yummy.api.dto.OrderCancellationRequestDTO;
import pl.yummy.domain.OrderCancellationRequest;

@Mapper(componentModel = "spring")
public interface OrderCancellationRequestMapper {

    OrderCancellationRequestDTO toDTO(OrderCancellationRequest orderCancellationRequest);

    OrderCancellationRequest toDomain(OrderCancellationRequestDTO orderCancellationRequestDTO);
}

    /*
    Wyjaśnienie:
    - @Mapper(componentModel = "spring") – Mapper jest rejestrowany jako bean Springa, co ułatwia wstrzykiwanie go
    w innych komponentach.

    - Metody toDTO i toDomain konwertują obiekty między warstwą domenową a DTO.

    - Ponieważ pola w obu klasach mają identyczne nazwy i typy, nie są potrzebne dodatkowe adnotacje @Mapping.


    Takie rozwiązanie jest wystarczające i optymalne dla tego przypadku.
    */