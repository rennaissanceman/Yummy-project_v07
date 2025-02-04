package pl.yummy.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.yummy.business.dao.OwnerDAO;
import pl.yummy.domain.Owner;
import pl.yummy.infrastructure.database.repository.jpa.OwnerJpaRepository;
import pl.yummy.infrastructure.database.repository.mapper.OwnerEntityMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class OwnerRepository implements OwnerDAO {

    private final OwnerJpaRepository ownerJpaRepository;
    private final OwnerEntityMapper ownerEntityMapper;

    @Override
    public Optional<Owner> findByOwnerNumber(String ownerNumber) {
        return ownerJpaRepository.findByOwnerNumber(ownerNumber)
                .map(ownerEntityMapper::mapFromEntity);
    }

    @Override
    public Optional<Owner> findByOwnerName(String ownerName) {
        return ownerJpaRepository.findByOwnerName(ownerName)
                .map(ownerEntityMapper::mapFromEntity);
    }

    @Override
    public List<Owner> findByRestaurants_SizeGreaterThanEqual(Long minimumRestaurants) {
        return ownerJpaRepository.findByRestaurants_SizeGreaterThanEqual(minimumRestaurants)
                .stream()
                .map(ownerEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }
}
