package com.revature.eport;

import com.revature.ePort.auctionshowing.AuctionRepository;
import com.revature.ePort.auctionshowing.AuctionService;
import com.revature.ePort.auctionshowing.AuctionShowing;
import com.revature.ePort.auctionshowing.dtos.requests.NewAuction;
import com.revature.ePort.auctionshowing.dtos.responses.ActiveAuctions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuctionTesting {

    private AuctionRepository auctionRepositoryMock;
    private AuctionService  auctionService;
    ActiveAuctions activeAuctionsMock = new ActiveAuctions();
    AuctionShowing userAuctionsMock = new AuctionShowing();
    NewAuction newAuction = new NewAuction();

    @Before
    public void setup(){
        auctionRepositoryMock = mock(AuctionRepository.class);
        auctionService = new AuctionService(auctionRepositoryMock);
    }

    @Test
    public void testWorkingUserAuctions(){
        List<AuctionShowing> userAuctionsList = List.of(userAuctionsMock);
        when(auctionRepositoryMock.userAuctions("c7b43bf5-ae0b-4bc8-95f1-ff80e0b75513")).thenReturn(userAuctionsList);
        Assert.assertEquals(auctionRepositoryMock.userAuctions("c7b43bf5-ae0b-4bc8-95f1-ff80e0b75513"), userAuctionsList);
    }

    @Test
    public void testEmptyUserAuctions(){
        List<AuctionShowing> userAuctionsList = List.of(userAuctionsMock);
        when(auctionRepositoryMock.userAuctions("")).thenReturn(userAuctionsList);
        Assert.assertNotEquals(auctionRepositoryMock.userAuctions("c7b43bf5-ae0b-4bc8-95f1-ff80e0b75513"), userAuctionsList);
    }



}
