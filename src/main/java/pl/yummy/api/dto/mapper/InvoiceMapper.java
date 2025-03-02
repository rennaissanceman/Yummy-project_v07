package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.yummy.api.dto.InvoiceDTO;
import pl.yummy.domain.Invoice;

@Mapper(componentModel = "spring", uses = {OffsetDateTimeMapper.class})
public interface InvoiceMapper extends OffsetDateTimeMapper{

    @Mapping(source = "issueDate", target = "issueDate", qualifiedByName = "mapOffsetDateTimeToString")
    @Mapping(source = "saleDate", target = "saleDate", qualifiedByName = "mapOffsetDateTimeToString")
    @Mapping(source = "dueDate", target = "dueDate", qualifiedByName = "mapOffsetDateTimeToString")
    InvoiceDTO toDTO(Invoice invoice);
}

    /*
    Wyjaśnienie:
    - @Mapper(componentModel = "spring") – Dzięki temu mapper jest rejestrowany jako bean Springa i może być
    wstrzykiwany w innych komponentach.

    - Metoda toDTO(Invoice invoice) automatycznie mapuje pola o takich samych nazwach i typach (invoiceId,
     invoiceNumber, issueDate, saleDate, totalAmount, netAmount, taxAmount, taxRate, dueDate, issuerSignature).

    - Pola, które występują w domenie, ale nie są częścią DTO (orders, payment, billingInformation, notes),
    są pomijane, ponieważ nie mają odpowiadających im pól w InvoiceDTO.


    Dzięki takiemu rozwiązaniu uzyskujemy prostą konwersję obiektu Invoice na jego uproszczoną reprezentację InvoiceDTO.
    */