package pl.yummy.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.yummy.business.dao.CustomerDAO;

@Repository
@AllArgsConstructor
public class CustomerRepository implements CustomerDAO {

}
