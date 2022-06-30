package com.revature.ePort.bid;

import com.revature.ePort.bid.dtos.requests.NewBid;
import com.revature.ePort.bid.dtos.responses.ViewBidHistory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Transactional
public interface BidRepository extends CrudRepository<Bid, String> {

    @Modifying
    @Query(value = "insert into bid values(?1,?2,?3,true)", nativeQuery = true)
    void addNewBid(String auctionID, String userID, BigDecimal amount);

    @Modifying
    @Query(value = "update bid set amount = ?1 where user_id = ?2 and auction_showing_id = ?3",nativeQuery = true)
    void updateBid(BigDecimal amount, String userId, String auctionId);

    @Query(value = "select * from bid where user_id =?1",nativeQuery = true)
    List<Bid> bidHistory(String userID);

    @Query(value = "select scp_id from auction_showing where id = ?1",nativeQuery = true)
    String auctionExists(String auctionShowingID);

    @Query(value = "select auction_showing_id from bid where auction_showing_id = ?1 and user_id = ?2", nativeQuery = true)
    String bidExists(String auctionID, String userID);

    @Query(value = "select auction_status from auction_showing where id = ?1", nativeQuery = true)
    boolean auctionIsActive(String id);

    @Query(value = "select max(amount) from bid where auction_showing_id=?1",nativeQuery = true)
    BigDecimal maxAmount(String auctionID);

    @Query(value = "select buyout_bid from auction_showing where id = ?1", nativeQuery = true)
    BigDecimal buyOut(String auctionID);

    @Modifying
    @Query(value = "update auction_showing set auction_status = false where id = ?1", nativeQuery = true)
    void closeAuction(String auctionId);

    @Modifying
    @Query(value = "insert into eport.order values(?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
    void createNewOrder(String id, Timestamp orderArrived, Timestamp order_sent, String status, String address);

    @Query(value = "select shipping_address from users where id = ?1", nativeQuery = true)
    String retrieveShippingAddress(String userID);

    @Modifying
    @Query(value = "update bid set orderid = ?1 where auction_showing_id = ?2", nativeQuery = true)
    void orderIdOnBid(String id, String auctionID);

    @Modifying
    @Query(value = "update users set funds = funds-?1 where id = ?2", nativeQuery = true)
    void removeFunds(BigDecimal amount, String id);

}
