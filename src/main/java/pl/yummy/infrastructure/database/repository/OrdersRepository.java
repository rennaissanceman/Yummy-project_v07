package pl.yummy.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.yummy.business.dao.OrdersDAO;

@Repository
@AllArgsConstructor
public class OrdersRepository implements OrdersDAO {

}
