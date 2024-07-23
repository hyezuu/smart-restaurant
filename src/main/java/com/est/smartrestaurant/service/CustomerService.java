package com.est.smartrestaurant.service;

import com.est.smartrestaurant.common.exception.BusinessLogicException;
import com.est.smartrestaurant.common.exception.ExceptionCode;
import com.est.smartrestaurant.domain.entity.Customer;
import com.est.smartrestaurant.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer save(Customer customer) {
        if (customerRepository.existsByPhoneNumber(customer.getPhoneNumber())) {
            throw new BusinessLogicException(ExceptionCode.CUSTOMER_ALREADY_EXISTS);
        }
        return customerRepository.save(customer);
    }

    @Transactional(readOnly = true)
    public Customer findById(Long id) {
        return customerRepository.findById(id).orElseThrow(()
            -> new BusinessLogicException(ExceptionCode.CUSTOMER_NOT_FOUND));
    }

    public Customer update(Long id, Customer newCustomer) {
        Customer customerToUpdate = findById(id);

        customerToUpdate
            .updatePhoneNumber(newCustomer.getPhoneNumber())
            .updateAddress(newCustomer.getAddress());

        return customerRepository.save(customerToUpdate);
    }

    public void delete(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new BusinessLogicException(ExceptionCode.CUSTOMER_NOT_FOUND);
        }
        customerRepository.deleteById(id);
    }
}
