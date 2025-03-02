package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.yummy.api.dto.CustomerAddressDTO;
import pl.yummy.api.dto.CustomerDTO;
import pl.yummy.domain.Customer;
import pl.yummy.domain.CustomerAddress;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {BillingInformationMapper.class, CustomerAddressMapper.class})
public interface CustomerMapper {

    @Mapping(source = "userAuth.email", target = "email")
    @Mapping(source = "customerAddresses", target = "customerAddressDTOList")
    CustomerDTO toDTO(final Customer customer);

    /*
     * Metoda domyślna mapująca zbiór adresów (Set) na listę CustomerAddressDTO.
     * Jeśli przekazany zbiór jest null, zwraca pustą listę.
     */
    default List<CustomerAddressDTO> mapCustomerAddresses(Set<CustomerAddress> addresses) {
        if (addresses == null) {
            return new ArrayList<>();
        }
        return addresses.stream()
                .map(this::toCustomerAddressDTO)
                .collect(Collectors.toList());
    }

    // Metoda mapująca pojedynczy obiekt CustomerAddress na CustomerAddressDTO.
    // Implementacja tej metody zostanie wygenerowana przez MapStruct, korzystając z CustomerAddressMapper
    CustomerAddressDTO toCustomerAddressDTO(CustomerAddress address);
}

    /*
    Wyjaśnienie:
    - @Mapper(componentModel = "spring", uses = {...})
    Mapper jest zarządzany przez Springa. Dzięki atrybutowi uses do mapowania zagnieżdżonych obiektów wykorzystywane są
    mappery: BillingInformationMapper oraz CustomerAddressMapper.

    - Mapowanie pola e-mail:
    Adnotacja @Mapping(source = "userAuth.email", target = "email") powoduje, że adres e-mail z obiektu userAuth
    zostanie przypisany do pola email w DTO.

    - Mapowanie kolekcji adresów:
    Adnotacja @Mapping(source = "customerAddresses", target = "customerAddressDTOList") wykorzystuje domyślną metodę
    mapCustomerAddresses. Ta metoda gwarantuje, że jeśli zbiór adresów jest null, zostanie zwrócona pusta lista,
    a jeśli nie – każdy element zostanie przekonwertowany przy użyciu metody toCustomerAddressDTO.

    - Delegacja do CustomerAddressMapper:
    Metoda toCustomerAddressDTO(CustomerAddress address) powinna być zaimplementowana przez MapStruct, korzystając
    z konfiguracji zawartej w CustomerAddressMapper (przy uwzględnieniu, że został on zadeklarowany w uses).



    Takie podejście zwiększa bezpieczeństwo (brak null pointer exception) oraz zapewnia spójne mapowanie kolekcji
    w obrębie CustomerMapper.
*/