package pl.yummy.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.yummy.business.dao.CustomerDAO;
import pl.yummy.domain.Customer;
import pl.yummy.infrastructure.database.entity.CustomerEntity;
import pl.yummy.infrastructure.database.repository.jpa.CustomerJpaRepository;
import pl.yummy.infrastructure.database.repository.mapper.BillingInformationEntityMapper;
import pl.yummy.infrastructure.database.repository.mapper.CustomerEntityMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class CustomerRepository implements CustomerDAO {

    private final CustomerJpaRepository customerJpaRepository;
    private final CustomerEntityMapper customerEntityMapper;
    private final BillingInformationEntityMapper billingInformationEntityMapper;

    @Override
    public Optional<Customer> findByCustomerNumber(String customerNumber) {
        return customerJpaRepository.findByCustomerNumber(customerNumber)
                .map(entity -> customerEntityMapper.mapFromEntity(entity, billingInformationEntityMapper));
    }

    @Override
    public List<Customer> findByCompanyName(String companyName) {
        return customerJpaRepository.findByCompanyName(companyName).stream()
                .map(e -> customerEntityMapper.mapFromEntity(e, billingInformationEntityMapper))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Customer> findByUserAuth_Email(String email) {
        return customerJpaRepository.findByUserAuth_Email(email)
                .map(e -> customerEntityMapper.mapFromEntity(e, billingInformationEntityMapper));
    }

    @Override
    public List<Customer> findByIsCompanyTrue() {
        return customerJpaRepository.findByIsCompanyTrue().stream()
                .map(e -> customerEntityMapper.mapFromEntity(e, billingInformationEntityMapper))
                .collect(Collectors.toList());
    }

    @Override
    public List<Customer> findByCustomerSurname(String surname) {
        return customerJpaRepository.findByCustomerSurname(surname).stream()
                .map(e -> customerEntityMapper.mapFromEntity(e, billingInformationEntityMapper))
                .collect(Collectors.toList());
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        CustomerEntity toSave = customerEntityMapper.mapToEntity(customer, billingInformationEntityMapper);
        CustomerEntity saved  = customerJpaRepository.save(toSave);
        return customerEntityMapper.mapFromEntity(saved, billingInformationEntityMapper);
    }

    @Override
    public List<Customer> findAll() {
        return customerJpaRepository.findAll().stream()
                .map(e -> customerEntityMapper.mapFromEntity(e, billingInformationEntityMapper))
                .collect(Collectors.toList());
    }
}
