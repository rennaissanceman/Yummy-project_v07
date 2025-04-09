package pl.yummy.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.yummy.domain.Orders;
import pl.yummy.domain.enums.PaymentMethodStatusEnumDomain;
import pl.yummy.infrastructure.database.entity.OrdersEntity;
import pl.yummy.infrastructure.database.entity.PaymentMethodEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrdersEntityMapper {

    @Mapping(target = "payment", ignore = true)    // Ignorujemy płatność
    @Mapping(target = "invoice", ignore = true)    // Ignorujemy fakturę
    @Mapping(target = "receipt", ignore = true)    // Ignorujemy paragon
    @Mapping(target = "delivery", ignore = true)   // Ignorujemy dostawę
    @Mapping(target = "ordersItems", ignore = true) // Ignorujemy pozycje zamówienia
    Orders mapFromEntity(OrdersEntity entity);

    @Mapping(target = "payment", ignore = true)
    @Mapping(target = "invoice", ignore = true)
    @Mapping(target = "receipt", ignore = true)
    @Mapping(target = "delivery", ignore = true)
    @Mapping(target = "ordersItems", ignore = true)
    OrdersEntity mapToEntity(Orders domain);

    /**
     * Metoda mapująca PaymentMethodEntity na PaymentMethodStatusEnumDomain.
     * Zakładamy, że PaymentMethodEntity posiada metodę {@code getMethod()} zwracającą nazwę metody płatności,
     * która odpowiada stałej w PaymentMethodStatusEnumDomain.
     *
     * @param value obiekt PaymentMethodEntity z warstwy bazy danych
     * @return odpowiadający element enum PaymentMethodStatusEnumDomain lub null, jeśli wejście jest null
     */
    default PaymentMethodStatusEnumDomain map(PaymentMethodEntity value) {
        if (value == null) {
            return null;
        }
        // Przykładowa logika konwersji:
        return PaymentMethodStatusEnumDomain.valueOf(value.getMethod());
    }
}
