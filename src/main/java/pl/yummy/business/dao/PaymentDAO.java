package pl.yummy.business.dao;

import pl.yummy.infrastructure.database.entity.PaymentEntity;

import java.util.List;

public interface PaymentDAO {
    PaymentEntity findById(Long id); // Znajdź płatność po ID
    List<PaymentEntity> findByCustomerId(Long customerId); // Znajdź płatności klienta
    List<PaymentEntity> findByStatus(String status); // Znajdź płatności po statusie
    void save(PaymentEntity payment); // Zapisz lub zaktualizuj płatność
    void deleteById(Long id); // Usuń płatność
}

