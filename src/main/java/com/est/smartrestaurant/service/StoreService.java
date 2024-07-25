package com.est.smartrestaurant.service;

import com.est.smartrestaurant.common.exception.BusinessLogicException;
import com.est.smartrestaurant.common.exception.ExceptionCode;
import com.est.smartrestaurant.domain.entity.Customer;
import com.est.smartrestaurant.domain.entity.Store;
import com.est.smartrestaurant.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public Store save(Store store) {
        if (storeRepository.existsByPhoneNumber(store.getPhoneNumber())) {
            throw new BusinessLogicException(ExceptionCode.STORE_ALREADY_EXISTS);
        }
        return storeRepository.save(store);
    }

    @Transactional(readOnly = true)
    public Store findById(Long id) {
        return storeRepository.findById(id).orElseThrow(()
            -> new BusinessLogicException(ExceptionCode.STORE_NOT_FOUND));
    }


    public Store update(Long id, Store newStore) {
        Store store = findById(id);

        store.update(newStore);

        return storeRepository.save(store);
    }

    public void delete(Long id) {
        if (!storeRepository.existsById(id)) {
            throw new BusinessLogicException(ExceptionCode.STORE_NOT_FOUND);
        }
        storeRepository.deleteById(id);
    }

}
