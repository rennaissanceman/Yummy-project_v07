package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.yummy.api.dto.RequestRestaurantManagementDTO;
import pl.yummy.domain.RequestRestaurantManagement;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RequestRestaurantManagementMapper {

    // Mapowanie głównego obiektu
    RequestRestaurantManagementDTO toDTO(RequestRestaurantManagement request);
    RequestRestaurantManagement toDomain(RequestRestaurantManagementDTO dto);

    // Mapowanie zagnieżdżonych typów

    // RestaurantDetails
    RequestRestaurantManagement.RestaurantDetailsInput toDomainRestaurantDetails(RequestRestaurantManagementDTO.RestaurantDetailsDTO dto);
    RequestRestaurantManagementDTO.RestaurantDetailsDTO toDTORestaurantDetails(RequestRestaurantManagement.RestaurantDetailsInput input);

    // MenuItems – mapowanie listy
    List<RequestRestaurantManagement.MenuItemInput> toDomainMenuItems(List<RequestRestaurantManagementDTO.MenuItemDTO> dtos);
    List<RequestRestaurantManagementDTO.MenuItemDTO> toDTOMenuItems(List<RequestRestaurantManagement.MenuItemInput> inputs);

    // DeliveryAddresses – mapowanie listy
    List<RequestRestaurantManagement.DeliveryAddressInput> toDomainDeliveryAddresses(List<RequestRestaurantManagementDTO.DeliveryAddressDTO> dtos);
    List<RequestRestaurantManagementDTO.DeliveryAddressDTO> toDTODeliveryAddresses(List<RequestRestaurantManagement.DeliveryAddressInput> inputs);

    // CustomerReviews – mapowanie listy
    List<RequestRestaurantManagement.CustomerReviewInput> toDomainCustomerReviews(List<RequestRestaurantManagementDTO.CustomerReviewDTO> dtos);
    List<RequestRestaurantManagementDTO.CustomerReviewDTO> toDTOCustomerReviews(List<RequestRestaurantManagement.CustomerReviewInput> inputs);

    // OpeningHours
    RequestRestaurantManagement.OpeningHoursInput toDomainOpeningHours(RequestRestaurantManagementDTO.OpeningHoursDTO dto);
    RequestRestaurantManagementDTO.OpeningHoursDTO toDTOOpeningHours(RequestRestaurantManagement.OpeningHoursInput input);
}

    /*
    Wyjaśnienie:
    - @Mapper(componentModel = "spring") – Mapper jest rejestrowany jako bean Springa, co ułatwia jego użycie w aplikacji.

    - toDTO/toDomain – Główne metody mapujące między RequestRestaurantManagement a RequestRestaurantManagementDTO.

    - Mapowanie zagnieżdżonych typów – Definiujemy osobne metody mapujące dla każdego zagnieżdżonego
    typu (RestaurantDetails, MenuItem, DeliveryAddress, CustomerReview, OpeningHours).
    Dzięki temu MapStruct będzie w stanie poprawnie odwzorować te typy, nawet jeśli znajdują się one jako zagnieżdżone
    klasy w głównym obiekcie.

    - Lista vs. zbiór – W przypadku list, MapStruct automatycznie mapuje elementy kolekcji,
    o ile dostępna jest metoda mapująca pojedynczy element.
    */