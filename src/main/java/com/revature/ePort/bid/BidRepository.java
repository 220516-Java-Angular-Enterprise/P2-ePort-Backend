package com.revature.ePort.bid;

import com.revature.ePort.bid.dtos.requests.NewBid;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


public interface BidRepository extends CrudRepository<Bid, String> {

    @Query(value = "insert into bid values(?1,?2,?3,true,'none'", nativeQuery = true)
    NewBid addNewBid(String auctionID, String userID, int amount);

    @Modifying
    @Query(value = "update bid set amount = ?1 where user_id = ?2 and auction_showing_id = ?3",nativeQuery = true)
    void updateBid(int amount, String userId, String auctionId);
}