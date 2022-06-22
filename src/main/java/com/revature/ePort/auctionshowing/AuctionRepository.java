package com.revature.ePort.auctionshowing;

import com.revature.ePort.auctionshowing.dtos.responses.ActiveAuctions;
import com.revature.ePort.auctionshowing.dtos.responses.UserAuctions;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;


@Transactional
public interface AuctionRepository extends CrudRepository<AuctionShowing, String> {

    @Query(value = "select username, title, buyout_bid, starting_bid, starting_date, expiration_date from auction_showing as a \n" +
            "inner join users as u on a.user_id = u.id ", nativeQuery = true)
    List<ActiveAuctions> activeAuctions();

    @Query(value = "select title, buyout_bid, starting_bid, starting_date, expiration_date from auction_showing as a \n" +
            "inner join users as u on a.user_id = u.id where user_id = ?1", nativeQuery = true)
    List<UserAuctions> userAuctions(String userID);

    @Query(value = "insert into auction_showing(id, auction_status, buyout_bid, expiration_date, starting_bid, starting_date,title,scp_id,user_id) \n" +
            "values(?1,?2,?3,?4,?5,?6,?7,?8,?9)", nativeQuery = true)
    void newAuction(String UUID, boolean status,int buyout, Timestamp expirationDate, int startingbid, Timestamp startingDate, String title, String scpId, String userId);

}
