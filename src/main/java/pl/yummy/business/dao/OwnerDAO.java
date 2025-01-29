package pl.yummy.business.dao;

import pl.yummy.domain.Owner;

import java.util.List;
import java.util.Optional;

public interface OwnerDAO {

    // Find an owner by their unique owner number
    Optional<Owner> findByOwnerNumber(String ownerNumber);

    // Find an owner by their name
    Optional<Owner> findByOwnerName(String ownerName);

    // Find all owners who have more than a specified number of restaurants
    List<Owner> findByRestaurants_SizeGreaterThanEqual(Long minimumRestaurants);


}
