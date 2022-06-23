package com.revature.ePort.order;


import com.revature.ePort.order.dtos.requests.NewOrder;
import com.revature.ePort.util.annotations.Inject;
import org.hibernate.engine.spi.ManagedEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Inject
    private final OrderService orderService;

    @Inject
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody String newOrder(@RequestBody NewOrder newOrder){

        return newOrder.getShipped_address();
    }
}
