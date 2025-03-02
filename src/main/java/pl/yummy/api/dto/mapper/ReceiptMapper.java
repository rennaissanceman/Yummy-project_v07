package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.yummy.api.dto.ReceiptDTO;
import pl.yummy.domain.Receipt;

@Mapper(componentModel = "spring")
public interface ReceiptMapper {

    @Mapping(source = "orders.ordersId", target = "ordersId")
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