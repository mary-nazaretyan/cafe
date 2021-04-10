package com.sflpro.cafe.service;

import java.util.List;

import com.sflpro.cafe.model.dto.CafeOrderDTO;
import com.sflpro.cafe.model.enums.OrderStatus;
import com.sflpro.cafe.model.jpa.CafeOrder;
import com.sflpro.cafe.repository.OrderRepository;
import com.sflpro.cafe.repository.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final TableRepository tableRepository;

    public void save(CafeOrderDTO orderDTO) {

        CafeOrder cafeOrder = new CafeOrder();

        cafeOrder.setStatus(OrderStatus.valueOf(orderDTO.getStatus()));
        cafeOrder.setTable(tableRepository.findById(orderDTO.getTableId()).get());

        orderRepository.save(cafeOrder);
    }

    public void save(CafeOrder updatedOrder, long id) {
        final CafeOrder order = orderRepository.findById(id).get();

        order.setStatus(updatedOrder.getStatus());

        orderRepository.save(order);
    }

    public List<CafeOrder> getOrdersByWaiterId(long waiterId) {
        return orderRepository.findOrderByWaiterId(waiterId);
    }

    public CafeOrder getOrderById(long id) {
        return orderRepository.findById(id).get();
    }

}
