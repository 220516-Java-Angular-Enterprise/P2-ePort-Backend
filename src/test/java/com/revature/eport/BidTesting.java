package com.revature.eport;

import com.revature.ePort.bid.Bid;
import com.revature.ePort.bid.BidRepository;
import com.revature.ePort.bid.BidService;
import com.revature.ePort.bid.dtos.requests.NewBid;
import com.revature.ePort.util.custom_exception.ResourceConflictException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BidTesting {

    private BidRepository bidRepositoryMock;
    private BidService bidService;
    Bid viewBidHistoryMock = new Bid();
    NewBid newBid = new NewBid();
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

    @Test
    public void invalidBidAmount(){
        BigDecimal maxAmount = new BigDecimal(0);
        when(bidRepositoryMock.maxAmount("9f61db4f-6bf6-4518-bb17-24e1843e147d")).thenReturn(maxAmount);
        newBid = new NewBid("9f61db4f-6bf6-4518-bb17-24e1843e147d", "dc200531-8797-4abb-88f5-21fb23ca15d2", new BigDecimal(10));
        Assert.assertThrows(ResourceConflictException.class, () -> bidService.updateBid(newBid));
    }

    @Test
    public void bidDoesntExists(){
        BigDecimal maxAmount = new BigDecimal(10);
        when(bidRepositoryMock.auctionExists("9f61db4f-6bf6-4518-bb17-", "dc200531-8797-4abb-88f5-21fb23ca15d2")).thenReturn(String.valueOf(true));
        when(bidRepositoryMock.maxAmount("9f61db4f-6bf6-4518-bb17-24e1843e147d")).thenReturn(maxAmount);
        newBid = new NewBid("9f61db4f-6bf6-4518-bb17-24e1843e147d", "dc200531-8797-4abb-88f5-21fb23ca15d2", new BigDecimal(100));
        Assert.assertThrows(ResourceConflictException.class, () -> bidService.updateBid(newBid));
    }

}
