package com.revature.ePort.order;


import com.revature.ePort.order.dtos.requests.NewOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface OrderRepository extends CrudRepository<Order, String> {

    @Query(value = "insert into eport.order values(?1,current_date+30, current_date+15,'shipping',?2)", nativeQuery = true)
    void newOrder(String id, String shippingAddress);

    @Query(value = "select o.id, o.order_arrived, order_sent, o.order_status, o.shipped_address  from eport.order as o inner join bid as b\n" +
            "on o.id = b.orderid where b.user_id = ?1", nativeQuery = true)
    List<Order> userOrderHistory(String userID);

    @Query(value = "select * from eport.order", nativeQuery = true)
    List<Order> eportOrderHistory();
}
