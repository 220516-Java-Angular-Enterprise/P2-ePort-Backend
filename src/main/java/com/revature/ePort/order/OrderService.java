package com.revature.ePort.order;

import com.revature.ePort.util.annotations.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Inject
    private OrderRepository orderRepository;

    @Inject
    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void newOrder(String shippingAddress){
        orderRepository.newOrder(UUID.randomUUID().toString(),shippingAddress);
    }

    public List<Order> userOrderHistory(String userID){
        return orderRepository.userOrderHistory(userID);
    }

    public List<Order> eportOrderHistory(){
        return orderRepository.eportOrderHistory();
    }
}
