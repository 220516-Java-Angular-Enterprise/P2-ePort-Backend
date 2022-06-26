package com.revature.ePort.bid;

import com.revature.ePort.bid.dtos.requests.NewBid;
import com.revature.ePort.bid.dtos.responses.ViewBidHistory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface BidRepository extends CrudRepository<Bid, String> {

    @Query(value = "insert into bid values(?1,?2,?3,true)", nativeQuery = true)
    void addNewBid(String auctionID, String userID, int amount);

    @Modifying
    @Query(value = "update bid set amount = ?1 where user_id = ?2 and auction_showing_id = ?3",nativeQuery = true)
    void updateBid(int amount, String userId, String auctionId);

    @Query(value = "select * from bid where user_id =?1",nativeQuery = true)
    List<Bid> bidHistory(String userID);

    @Query(value = "select scp_id from auction_showing where id = ?1 and user_id = ?2",nativeQuery = true)
    String auctionExists(String auctionShowingID, String userID);

    @Query(value = "select max(amount) from bid where auction_showing_id=?1",nativeQuery = true)
    int maxAmount(String auctionID);


    /*List<Bid> findByIdAuctionShowingID(String auctionShowingID);
    List<Bid> findByIdUserID(String userID);*/
}
