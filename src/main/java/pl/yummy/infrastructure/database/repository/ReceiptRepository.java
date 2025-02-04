package pl.yummy.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.yummy.business.dao.ReceiptDAO;
import pl.yummy.domain.Receipt;
import pl.yummy.infrastructure.database.repository.jpa.ReceiptJpaRepository;
import pl.yummy.infrastructure.database.repository.mapper.ReceiptEntityMapper;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class ReceiptRepository implements ReceiptDAO {

    private final ReceiptJpaRepository receiptJpaRepository;
    private final ReceiptEntityMapper receiptEntityMapper;

    @Override
    public Optional<Receipt> findByReceiptNumber(String receiptNumber) {
        return receiptJpaRepository.findByReceiptNumber(receiptNumber)
                .map(receiptEntityMapper::mapFromEntity);
    }

    @Override
    public List<Receipt> findByOrders_OrdersId(Integer ordersId) {
        return receiptJpaRepository.findByOrders_OrdersId(ordersId)
                .stream()
                .map(receiptEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Receipt> findByIssueDateAfter(OffsetDateTime issueDate) {
        return receiptJpaRepository.findByIssueDateAfter(issueDate)
                .stream()
                .map(receiptEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Receipt> findByTotalAmountGreaterThanEqual(Double totalAmount) {
        return receiptJpaRepository.findByTotalAmountGreaterThanEqual(totalAmount)
                .stream()
                .map(receiptEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Receipt> findByNotesContainingIgnoreCase(String keyword) {
        return receiptJpaRepository.findByNotesContainingIgnoreCase(keyword)
                .stream()
                .map(receiptEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }
}
