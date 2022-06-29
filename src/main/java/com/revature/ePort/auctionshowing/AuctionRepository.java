package com.revature.ePort.auctionshowing;

import com.revature.ePort.auctionshowing.dtos.responses.ActiveAuctions;
import com.revature.ePort.auctionshowing.dtos.responses.UserAuctions;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


@Transactional
public interface AuctionRepository extends CrudRepository<AuctionShowing, String> {

    @Query(value = "select * from auction_showing where auction_status = true", nativeQuery = true)
    List<AuctionShowing> activeAuctions();

    @Query(value = "select id from scp where name = ?1", nativeQuery = true)
    String returnScpID(String name);

    @Query(value = "select * from auction_showing where user_id = ?1", nativeQuery = true)
    List<AuctionShowing> userAuctions(String userID);

    @Modifying
    @Query(value = "insert into auction_showing(id, auction_status, buyout_bid, expiration_date, number_of_bids ,starting_bid, starting_date,title,scp_id,user_id) " +
            "values(?1,?2,?3,?4,0,?5,?6,?7,?8,?9)", nativeQuery = true)
    void newAuction(String UUID, boolean status, BigDecimal buyout, Timestamp expirationDate, BigDecimal startingbid, Timestamp startingDate, String title, String scpId, String userId);

    @Query(value = "select * from auction_showing where scp_id = ?1", nativeQuery = true)
    AuctionShowing duplicateAuction(String scpID);

}
