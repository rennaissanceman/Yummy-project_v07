package pl.yummy.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.yummy.business.dao.OwnerDAO;
import pl.yummy.domain.Owner;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OwnerService {

    private final OwnerDAO ownerDAO;

    @Transactional(readOnly = true)
    public Optional<Owner> findByOwnerNumber(String ownerNumber) {
        return ownerDAO.findByOwnerNumber(ownerNumber);
    }

    @Transactional(readOnly = true)
    public Optional<Owner> findByOwnerName(String ownerName) {
        return ownerDAO.findByOwnerName(ownerName);
    }

    @Transactional(readOnly = true)
    public List<Owner> findByMinimumRestaurants(Long minimumRestaurants) {
        return ownerDAO.findByRestaurants_SizeGreaterThanEqual(minimumRestaurants);
    }
}

    /*
    _______________________________________________________________
    OwnerService

    - Umożliwia operacje związane z właścicielami restauracji, np. wyszukiwanie właściciela po numerze,
    nazwie lub na podstawie liczby prowadzonych restauracji.
    - Wstrzykiwany komponent: OwnerDAO.
    */