package pl.yummy.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.yummy.business.dao.RestaurantDAO;
import pl.yummy.domain.Restaurant;
import pl.yummy.domain.enums.CuisineTypeEnumDomain;
import pl.yummy.infrastructure.database.repository.jpa.RestaurantJpaRepository;
import pl.yummy.infrastructure.database.repository.mapper.RestaurantEntityMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class RestaurantRepository implements RestaurantDAO {

    private final RestaurantJpaRepository restaurantJpaRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;

    @Override
    public Optional<Restaurant> findByRestaurantName(String restaurantName) {
        return restaurantJpaRepository.findByRestaurantName(restaurantName)
                .map(restaurantEntityMapper::mapFromEntity);
    }

    @Override
    public List<Restaurant> findByCuisineType(CuisineTypeEnumDomain cuisineType) {
        return restaurantJpaRepository.findByCuisineType(
                        // Zakładamy zgodność nazw enumów
                        Enum.valueOf(
                                restaurantEntityMapper.getCuisineTypeEntityClass(),
                                cuisineType.name()
                        )
                )
                .stream()
                .map(restaurantEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Restaurant> findByAverageRatingGreaterThanEqual(Double minimumRating) {
        return restaurantJpaRepository.findByAverageRatingGreaterThanEqual(minimumRating)
                .stream()
                .map(restaurantEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Restaurant> findByOwner_OwnerId(Long ownerId) {
        return restaurantJpaRepository.findByOwner_OwnerId(ownerId)
                .stream()
                .map(restaurantEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

}
