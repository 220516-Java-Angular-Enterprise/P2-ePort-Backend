package com.revature.ePort.order;


import com.revature.ePort.order.dtos.requests.NewOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface OrderRepository extends CrudRepository<NewOrder, String> {

    @Query(value = "insert into eport.order values(?1,current_date+30, current_date+15,'shipping',?2)", nativeQuery = true)
    public void newOrder(String id, String shippingAddress);
}
