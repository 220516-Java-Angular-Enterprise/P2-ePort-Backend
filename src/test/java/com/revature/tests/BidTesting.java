package com.revature.tests;

import com.revature.ePort.auctionshowing.AuctionRepository;
import com.revature.ePort.auctionshowing.AuctionService;
import com.revature.ePort.auctionshowing.AuctionShowing;
import com.revature.ePort.auctionshowing.dtos.responses.ActiveAuctions;
import com.revature.ePort.auctionshowing.dtos.responses.UserAuctions;
import com.revature.ePort.bid.Bid;
import com.revature.ePort.bid.BidRepository;
import com.revature.ePort.bid.BidService;
import com.revature.ePort.bid.dtos.responses.ViewBidHistory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BidTesting {

    private BidRepository bidRepositoryMock;
    private BidService bidService;
    Bid viewBidHistoryMock = new Bid();

    @Before
    public void setup(){
        bidRepositoryMock = mock(BidRepository.class);
        bidService = new BidService(bidRepositoryMock);
    }

    @Test
    public void viewAcceptedBidHistory(){
        List<Bid> bidHistoryList = List.of(viewBidHistoryMock);
        when(bidRepositoryMock.bidHistory("c7b43bf5-ae0b-4bc8-95f1-ff80e0b75513")).thenReturn(bidHistoryList);
        Assert.assertEquals(bidRepositoryMock.bidHistory("c7b43bf5-ae0b-4bc8-95f1-ff80e0b75513"), bidHistoryList);
    }

    @Test
    public void viewEmptyBidHistory(){
        List<Bid> bidHistoryList = List.of(viewBidHistoryMock);
        when(bidRepositoryMock.bidHistory("")).thenReturn(bidHistoryList);
        Assert.assertNotEquals(bidRepositoryMock.bidHistory("c7b43bf5-ae0b-4bc8-95f1-ff80e0b75513"), bidHistoryList);
    }

}
