package com.est.smartrestaurant.service;

import com.est.smartrestaurant.common.exception.BusinessLogicException;
import com.est.smartrestaurant.common.exception.ExceptionCode;
import com.est.smartrestaurant.domain.dto.PopularMenuItemDTO;
import com.est.smartrestaurant.domain.entity.Menu;
import com.est.smartrestaurant.repository.MenuRepository;
import com.est.smartrestaurant.repository.OrderItemRepository;
import com.est.smartrestaurant.repository.OrderRepository;
import com.est.smartrestaurant.repository.PopularMenuItemProjection;
import java.util.List;
import java.util.stream.Collectors;
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
    private final OrderItemRepository orderItemRepository;

    public Menu save(Menu menu) {
        if(menuRepository.existsByName(menu.getName())) {
            throw new BusinessLogicException(ExceptionCode.MENU_ALREADY_EXISTS);
        }
        return menuRepository.save(menu);
    }

    @Transactional(readOnly = true)
    public Page<Menu> findAllByCategory(String category, int page, int size) {
        return menuRepository.findAllByCategory(category, PageRequest.of(page, size));
    }

    @Transactional(readOnly = true)
    public Menu findById(Long id) {
        return menuRepository.findById(id).orElseThrow(()
            -> new BusinessLogicException(ExceptionCode.MENU_NOT_FOUND));
    }

    public List<PopularMenuItemDTO> getPopularMenuItem() {
        List<PopularMenuItemProjection> popularItems = orderItemRepository.findPopularMenuItems();
        return popularItems.stream()
            .map(item -> new PopularMenuItemDTO(
                item.getMenuItemId(),
                item.getTotalQuantity()))
            .collect(Collectors.toList());
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
            throw new BusinessLogicException(ExceptionCode.MENU_NOT_FOUND);
        }
        menuRepository.deleteById(id);
    }
}
