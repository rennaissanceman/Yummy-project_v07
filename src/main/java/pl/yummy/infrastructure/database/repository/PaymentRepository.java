package pl.yummy.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.yummy.business.dao.PaymentDAO;
import pl.yummy.domain.Payment;
import pl.yummy.domain.enums.PaymentStatusEnumDomain;
import pl.yummy.infrastructure.database.repository.jpa.PaymentJpaRepository;
import pl.yummy.infrastructure.database.repository.mapper.PaymentEntityMapper;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class PaymentRepository implements PaymentDAO {


    private final PaymentJpaRepository paymentJpaRepository;
    private final PaymentEntityMapper paymentEntityMapper;

    @Override
    public Optional<Payment> findByTransactionId(String transactionId) {
        return paymentJpaRepository.findByTransactionId(transactionId)
                .map(paymentEntityMapper::mapFromEntity);
    }

    @Override
    public List<Payment> findByOrders_OrdersId(Long ordersId) {
        return paymentJpaRepository.findByOrders_OrdersId(ordersId)
                .stream()
                .map(paymentEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Payment> findByPaymentStatus(PaymentStatusEnumDomain paymentStatus) {
        return paymentJpaRepository.findByPaymentStatus(
                        Enum.valueOf(
                                paymentEntityMapper.getPaymentStatusEntityClass(),
                                paymentStatus.name()
                        )
                )
                .stream()
                .map(paymentEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Payment> findByCreatedAtAfter(OffsetDateTime createdAt) {
        return paymentJpaRepository.findByCreatedAtAfter(createdAt)
                .stream()
                .map(paymentEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Payment> findByAmountGreaterThanEqual(Double amount) {
        return paymentJpaRepository.findByAmountGreaterThanEqual(amount)
                .stream()
                .map(paymentEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }
}
