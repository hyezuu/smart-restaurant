package com.est.smartrestaurant.service;

import com.est.smartrestaurant.common.exception.ExceptionCode;
import com.est.smartrestaurant.common.exception.ResourceConflictException;
import com.est.smartrestaurant.common.exception.ResourceNotFoundException;
import com.est.smartrestaurant.domain.entity.Menu;
import com.est.smartrestaurant.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public Menu save(Menu menu) {
        if(menuRepository.existsByName(menu.getName())) {
            throw new ResourceConflictException(ExceptionCode.CUSTOMER_ALREADY_EXISTS);
        }
        return menuRepository.save(menu);
    }

    @Transactional(readOnly = true)
    public Page<Menu> findAll(int page, int size) {
        return menuRepository.findAll(PageRequest.of(page, size));
    }

    @Transactional(readOnly = true)
    public Menu findById(Long id) {
        return menuRepository.findById(id).orElseThrow(()
            -> new ResourceNotFoundException(ExceptionCode.MENU_NOT_FOUND));
    }

    public Menu update(Long id, Menu newMenu){
        Menu menuToUpdate = findById(id);

        menuToUpdate
            .updateName(newMenu.getName())
            .updateCategory(newMenu.getCategory())
            .updatePrice(newMenu.getPrice())
            .updateDescription(newMenu.getDescription());

        return menuRepository.save(menuToUpdate);
    }

    public void delete(Long id) {
        if(!menuRepository.existsById(id)) {
            throw new ResourceNotFoundException(ExceptionCode.MENU_NOT_FOUND);
        }
        menuRepository.deleteById(id);
    }
}
