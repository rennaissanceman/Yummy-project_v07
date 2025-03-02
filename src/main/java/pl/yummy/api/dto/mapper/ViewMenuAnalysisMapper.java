package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.yummy.api.dto.ViewMenuAnalysisDTO;
import pl.yummy.domain.ViewMenuAnalysis;

@Mapper(componentModel = "spring")
public interface ViewMenuAnalysisMapper {

    ViewMenuAnalysisDTO toDTO(ViewMenuAnalysis analysis);

    ViewMenuAnalysis toDomain(ViewMenuAnalysisDTO dto);
}

    /*
    Wyjaśnienie:
    - @Mapper(componentModel = "spring") – Mapper jest rejestrowany jako bean Springa.

    - toDTO/toDomain – Standardowe metody mapujące między domenowym modelem a DTO.

    - Żadne dodatkowe zabezpieczenia lub konwersje nie są potrzebne, ponieważ pola mają te same nazwy i typy.
    */