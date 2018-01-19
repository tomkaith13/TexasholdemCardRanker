package com.bibin.thomas.takehome.game;

import com.bibin.thomas.takehome.Player.Player;
import com.bibin.thomas.takehome.cards.Card;
import com.bibin.thomas.takehome.cards.CardFace;
import com.bibin.thomas.takehome.cards.CardSuite;
import com.bibin.thomas.takehome.deck.CommunityDeck;
import com.bibin.thomas.takehome.user.validation.PlayerCardValidator;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class PokerHandTest {


    @Test
    public void testRoyalFlush() {
//        CommunityDeck communityDeck = new CommunityDeck("KS AS 3H 7C TS");
//
//        Player player = new Player("John", new HashSet<Card>(Arrays.asList(new Card(CardFace.QUEEN, CardSuite.SPADES), new Card(CardFace.JACK, CardSuite.SPADES))));
//        PokerHand pokerHand = new PokerHand(communityDeck, player);
//
//        assertEquals("Rank not equal to royal flush!!", HandRank.ROYAL_FLUSH, pokerHand.getPokerHandRankType());
    }

}