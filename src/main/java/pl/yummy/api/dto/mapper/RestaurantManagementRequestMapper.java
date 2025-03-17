package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.yummy.api.dto.RestaurantManagementRequestDTO;
import pl.yummy.domain.RestaurantManagementRequest;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestaurantManagementRequestMapper {

    // Mapowanie głównego obiektu
    RestaurantManagementRequestDTO toDTO(RestaurantManagementRequest request);
    RestaurantManagementRequest toDomain(RestaurantManagementRequestDTO dto);

    // Mapowanie zagnieżdżonych typów

    // RestaurantDetails
    RestaurantManagementRequest.RestaurantDetailsInput toDomainRestaurantDetails(RestaurantManagementRequestDTO.RestaurantDetailsDTO dto);
    RestaurantManagementRequestDTO.RestaurantDetailsDTO toDTORestaurantDetails(RestaurantManagementRequest.RestaurantDetailsInput input);

    // MenuItems – mapowanie listy
    List<RestaurantManagementRequest.MenuItemInput> toDomainMenuItems(List<RestaurantManagementRequestDTO.MenuItemDTO> dtos);
    List<RestaurantManagementRequestDTO.MenuItemDTO> toDTOMenuItems(List<RestaurantManagementRequest.MenuItemInput> inputs);

    // DeliveryAddresses – mapowanie listy
    List<RestaurantManagementRequest.DeliveryAddressInput> toDomainDeliveryAddresses(List<RestaurantManagementRequestDTO.DeliveryAddressDTO> dtos);
    List<RestaurantManagementRequestDTO.DeliveryAddressDTO> toDTODeliveryAddresses(List<RestaurantManagementRequest.DeliveryAddressInput> inputs);

    // CustomerReviews – mapowanie listy
    List<RestaurantManagementRequest.CustomerReviewInput> toDomainCustomerReviews(List<RestaurantManagementRequestDTO.CustomerReviewDTO> dtos);
    List<RestaurantManagementRequestDTO.CustomerReviewDTO> toDTOCustomerReviews(List<RestaurantManagementRequest.CustomerReviewInput> inputs);

    // OpeningHours
    RestaurantManagementRequest.OpeningHoursInput toDomainOpeningHours(RestaurantManagementRequestDTO.OpeningHoursDTO dto);
    RestaurantManagementRequestDTO.OpeningHoursDTO toDTOOpeningHours(RestaurantManagementRequest.OpeningHoursInput input);
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