package com.Tubes.VapeConnects.service;

import com.Tubes.VapeConnects.model.OrderItem;
import com.Tubes.VapeConnects.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;
    
    public Integer getTotalQuantityByProductId(Long productId) {
        Integer total = orderItemRepository.getTotalQuantityByProductId(productId);
        return total != null ? total : 0;
    }
    
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

}
