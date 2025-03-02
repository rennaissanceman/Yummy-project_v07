package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.yummy.api.dto.ReceiptDTO;
import pl.yummy.domain.Receipt;

@Mapper(componentModel = "spring", uses = {OffsetDateTimeMapper.class})
public interface ReceiptMapper extends OffsetDateTimeMapper{

    @Mapping(source = "orders.ordersId", target = "ordersId")
    @Mapping(source = "issueDate", target = "issueDate", qualifiedByName = "mapOffsetDateTimeToString")
    @Mapping(source = "saleDate", target = "saleDate", qualifiedByName = "mapOffsetDateTimeToString")
    ReceiptDTO toDTO(Receipt receipt);
}

    /*
    Wyjaśnienie:
    - @Mapper(componentModel = "spring")
    Mapper jest rejestrowany jako bean Springa, co umożliwia łatwe wstrzykiwanie go w innych komponentach.

    - @Mapping(source = "orders.ordersId", target = "ordersId")
    Wyodrębnia identyfikator zamówienia z obiektu orders w domenie i przypisuje go do pola ordersId w DTO.

    - Pozostałe pola są odwzorowywane automatycznie, gdyż ich nazwy i typy są zgodne między klasą domenową a DTO.


    Taka implementacja zapewnia spójne i czytelne mapowanie obiektu Receipt na jego uproszczoną reprezentację ReceiptDTO.
    */