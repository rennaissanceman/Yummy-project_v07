package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.yummy.api.dto.MenuAnalysisViewDTO;
import pl.yummy.domain.MenuAnalysisView;

@Mapper(componentModel = "spring")
public interface MenuAnalysisViewMapper {

    MenuAnalysisViewDTO toDTO(MenuAnalysisView analysis);

    MenuAnalysisView toDomain(MenuAnalysisViewDTO dto);
}

    /*
    Wyjaśnienie:
    - @Mapper(componentModel = "spring") – Mapper jest rejestrowany jako bean Springa.

    - toDTO/toDomain – Standardowe metody mapujące między domenowym modelem a DTO.

    - Żadne dodatkowe zabezpieczenia lub konwersje nie są potrzebne, ponieważ pola mają te same nazwy i typy.
    */