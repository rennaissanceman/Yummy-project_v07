package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.yummy.api.dto.CustomerAddressDTO;
import pl.yummy.api.dto.CustomerDTO;
import pl.yummy.api.dto.CustomerRegistrationRequestDTO;
import pl.yummy.api.dto.CustomerUpdateRequestDTO;
import pl.yummy.domain.Customer;
import pl.yummy.domain.CustomerAddress;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        uses = {BillingInformationMapper.class, CustomerAddressMapper.class},
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface CustomerMapper {



    // Mapujemy tylko pola, które istnieją – inne ignorujemy
    @Mapping(source = "userAuth.email", target = "email")
    @Mapping(source = "customerAddresses", target = "customerAddressDTOList")
    @Mapping(target = "defaultAddress", ignore = true)
    @Mapping(target = "phone", ignore = true)
    @Mapping(target = "orderSummaries", ignore = true)
    @Mapping(target = "vatNumber", ignore = true)
    CustomerDTO toDTO(final Customer customer);

    default List<CustomerAddressDTO> mapCustomerAddresses(Set<CustomerAddress> addresses) {
        if (addresses == null) {
            return new ArrayList<>();
        }
        return addresses.stream()
                .map(this::toCustomerAddressDTO)
                .collect(Collectors.toList());
    }

    // Implementacja tej metody zostanie wygenerowana przez MapStruct z CustomerAddressMapper
    CustomerAddressDTO toCustomerAddressDTO(CustomerAddress address);

    // Mapowanie z rejestracji – ignorujemy pola, których nie mamy w DTO lub ustawiamy domyślne
    @Mapping(target = "customerId", ignore = true)
    @Mapping(target = "customerNumber", ignore = true)
    @Mapping(target = "userAuth", ignore = true)
    @Mapping(target = "billingInformation", ignore = true)
    @Mapping(target = "customerAddresses", ignore = true)
    @Mapping(target = "orders", ignore = true)
    Customer toDomain(CustomerRegistrationRequestDTO registrationDTO);

    default Set<CustomerAddress> mapCustomerAddressDTOList(List<CustomerAddressDTO> dtos) {
        if (dtos == null) {
            return new HashSet<>();
        }
        return dtos.stream()
                .map(this::toCustomerAddress)
                .collect(Collectors.toSet());
    }

    // Mapowanie pojedynczego adresu – implementacja wygenerowana przez CustomerAddressMapper
    CustomerAddress toCustomerAddress(final CustomerAddressDTO dto);

    // Mapowanie aktualizacji profilu – ignorujemy pola, których nie podajemy w aktualizacji
    @Mapping(target = "customerId", ignore = true)
    @Mapping(target = "userAuth", ignore = true)
    @Mapping(target = "customerAddresses", ignore = true)
    @Mapping(target = "orders", ignore = true)
    Customer toDomain(final CustomerUpdateRequestDTO updateDTO);

    // Mapowanie domeny na DTO aktualizacji – ignorujemy pola nieużywane w aktualizacji
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "phone", ignore = true)
    @Mapping(target = "customerAddressDTOList", ignore = true)
    CustomerUpdateRequestDTO toUpdateDTO(Customer customer);

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