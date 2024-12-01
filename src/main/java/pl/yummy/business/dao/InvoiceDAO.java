package pl.yummy.business.dao;

import pl.yummy.infrastructure.database.entity.InvoiceEntity;

import java.util.List;

public interface InvoiceDAO {
    InvoiceEntity findById(Long id); // Znajdź fakturę po ID
    List<InvoiceEntity> findByCustomerId(Long customerId); // Znajdź faktury klienta
    void save(InvoiceEntity invoice); // Zapisz lub zaktualizuj fakturę
    void deleteById(Long id); // Usuń fakturę
}

