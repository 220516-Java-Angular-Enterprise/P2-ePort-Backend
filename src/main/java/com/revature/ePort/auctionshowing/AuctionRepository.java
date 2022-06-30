package com.revature.ePort.auctionshowing;

import com.revature.ePort.auctionshowing.dtos.responses.ActiveAuctions;
import com.revature.ePort.auctionshowing.dtos.responses.UserAuctions;
import com.revature.ePort.scp.SCP;
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

    /*@Query(value = "select title, buyout_bid, starting_bid, starting_date, expiration_date, max(amount), s.* from auction_showing as a\n" +
            "inner join bid as b on a.id = b.auction_showing_id \n" +
            "inner join scp as s on a.scp_id = s.id \n" +
            "group by title, buyout_bid, starting_bid, starting_date, expiration_date, s.id\n" +
            "having title = ?1", nativeQuery = true)
    UserAuctions detailedAuction(String title);*/

    @Query(value = "select * from auction_showing where title = ?1", nativeQuery = true)
    AuctionShowing auction(String title);

    @Query(value = "select auction_status from auction_showing where id = ?1", nativeQuery = true)
    boolean auctionStatus(String id);

    @Modifying
    @Query(value = "update auction_showing set auction_status = false where id = ?1", nativeQuery = true)
    void disableAuction(String id);

    @Modifying
    @Query(value = "update auction_showing set auction_status = true where id = ?1", nativeQuery = true)
    void enableAuction(String id);

    @Modifying
    @Query(value = "insert into auction_showing(id, auction_status, buyout_bid, expiration_date, number_of_bids ,starting_bid, starting_date,title,scp_id,user_id) " +
            "values(?1,?2,?3,?4,0,?5,?6,?7,?8,?9)", nativeQuery = true)
    void newAuction(String UUID, boolean status, BigDecimal buyout, Timestamp expirationDate, BigDecimal startingbid, Timestamp startingDate, String title, String scpId, String userId);

    @Query(value = "select scp_id from auction_showing where scp_id = ?1", nativeQuery = true)
    String duplicateAuction(String scpID);

    @Query(value = "select title from auction_showing where title = ?1", nativeQuery = true)
    String titleDuplicate(String title);

}
