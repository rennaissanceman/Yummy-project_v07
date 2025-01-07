package pl.yummy.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.yummy.business.dao.OrdersDAO;
import pl.yummy.infrastructure.database.entity.OrdersEntity;

import java.util.List;

@Repository
@AllArgsConstructor
public class OrdersRepository implements OrdersDAO {
    @Override
    public List<OrdersEntity> findByCustomerId(Long customerId) {
        return null;
    }
}
