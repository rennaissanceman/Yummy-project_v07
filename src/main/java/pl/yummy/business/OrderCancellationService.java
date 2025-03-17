package pl.yummy.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.yummy.business.dao.OrderCancellationProcessingDAO;
import pl.yummy.domain.OrderCancellationRequest;

@Service
@AllArgsConstructor
public class OrderCancellationService {

    private final OrderCancellationProcessingDAO orderCancellationProcessingDAO;

    @Transactional
    public void cancelOrder(OrderCancellationRequest request) {
        orderCancellationProcessingDAO.cancelOrder(request);
    }
}

    /*
    OrderCancellationService

    - Umożliwia anulowanie zamówień na podstawie odpowiednich żądań.
    - Wstrzykiwany komponent: ProcessingOrderCancellationDAO.
    */