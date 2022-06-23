package com.revature.tests;

import com.revature.ePort.auctionshowing.AuctionRepository;
import com.revature.ePort.auctionshowing.AuctionService;
import com.revature.ePort.auctionshowing.AuctionShowing;
import com.revature.ePort.auctionshowing.dtos.requests.NewAuction;
import com.revature.ePort.auctionshowing.dtos.responses.ActiveAuctions;
import com.revature.ePort.auctionshowing.dtos.responses.UserAuctions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuctionTesting {

    private AuctionRepository auctionRepositoryMock;
    private AuctionService  auctionService;
    ActiveAuctions activeAuctionsMock = new ActiveAuctions();
    UserAuctions userAuctionsMock = new UserAuctions();
    NewAuction newAuction = new NewAuction();

    @Before
    public void setup(){
        auctionRepositoryMock = mock(AuctionRepository.class);
        auctionService = new AuctionService(auctionRepositoryMock);
    }

    @Test
    public void testWorkingUserAuctions(){
        List<UserAuctions> userAuctionsList = List.of(userAuctionsMock);
        when(auctionRepositoryMock.userAuctions("c7b43bf5-ae0b-4bc8-95f1-ff80e0b75513")).thenReturn(userAuctionsList);
        Assert.assertEquals(auctionRepositoryMock.userAuctions("c7b43bf5-ae0b-4bc8-95f1-ff80e0b75513"), userAuctionsList);
    }

    @Test
    public void testEmptyUserAuctions(){
        List<UserAuctions> userAuctionsList = List.of(userAuctionsMock);
        when(auctionRepositoryMock.userAuctions("")).thenReturn(userAuctionsList);
        Assert.assertNotEquals(auctionRepositoryMock.userAuctions("c7b43bf5-ae0b-4bc8-95f1-ff80e0b75513"), userAuctionsList);
    }



}