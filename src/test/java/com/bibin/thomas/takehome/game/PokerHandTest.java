package com.bibin.thomas.takehome.game;

import com.bibin.thomas.takehome.Player.Player;
import com.bibin.thomas.takehome.cards.Card;
import com.bibin.thomas.takehome.cards.CardFace;
import com.bibin.thomas.takehome.cards.CardSuite;
import com.bibin.thomas.takehome.deck.CommunityDeck;
import org.junit.Test;


import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;


public class PokerHandTest {


    @Test
    public void testRoyalFlush1() {
        CommunityDeck communityDeck = new CommunityDeck("KS AS 3H 7C TS");

        Player player = new Player("John", new HashSet<>(Arrays.asList(new Card(CardFace.QUEEN, CardSuite.SPADES), new Card(CardFace.JACK, CardSuite.SPADES))));
        PokerHand pokerHand = new PokerHand(communityDeck, player);

        assertEquals("Rank not equal to royal flush!!", HandRank.ROYAL_FLUSH, pokerHand.getPokerHandRankType());
    }

    @Test
    public void testRoyalFlush2() {
        CommunityDeck communityDeck = new CommunityDeck("3H 7C TD QD JD");

        Player player = new Player("John", new HashSet<>(Arrays.asList(new Card(CardFace.KING, CardSuite.DIAMONDS), new Card(CardFace.ACE, CardSuite.DIAMONDS))));
        PokerHand pokerHand = new PokerHand(communityDeck, player);

        assertEquals("Rank not equal to royal flush!!", HandRank.ROYAL_FLUSH, pokerHand.getPokerHandRankType());
    }

    @Test
    public void testNotRoyalFlush1() {
        CommunityDeck communityDeck = new CommunityDeck("3H 7C TD QS JD");

        Player player = new Player("John", new HashSet<>(Arrays.asList(new Card(CardFace.KING, CardSuite.DIAMONDS), new Card(CardFace.ACE, CardSuite.DIAMONDS))));
        PokerHand pokerHand = new PokerHand(communityDeck, player);

        assertNotEquals("Rank equal to royal flush!!", HandRank.ROYAL_FLUSH, pokerHand.getPokerHandRankType());
    }

    @Test
    public void testStraightFlush1() {
        CommunityDeck communityDeck = new CommunityDeck("KS 6S 3S 7S TS");

        Player player = new Player("John", new HashSet<>(Arrays.asList(new Card(CardFace.FOUR, CardSuite.SPADES), new Card(CardFace.FIVE, CardSuite.SPADES))));
        PokerHand pokerHand = new PokerHand(communityDeck, player);

        assertEquals("Rank not equal to straight flush!!", HandRank.STRAIGHT_FLUSH, pokerHand.getPokerHandRankType());
    }

    @Test
    public void testInvalidStraightFlush1() {
        CommunityDeck communityDeck = new CommunityDeck("KS 2S 3S 7S TS");

        Player player = new Player("John", new HashSet<>(Arrays.asList(new Card(CardFace.FOUR, CardSuite.SPADES), new Card(CardFace.FIVE, CardSuite.SPADES))));
        PokerHand pokerHand = new PokerHand(communityDeck, player);

        assertNotEquals("Rank equal to straight flush!!", HandRank.STRAIGHT_FLUSH, pokerHand.getPokerHandRankType());
    }

    @Test
    public void testInvalidStraightFlush2() {
        CommunityDeck communityDeck = new CommunityDeck("KS 2S 3S 7C TC");

        Player player = new Player("John", new HashSet<>(Arrays.asList(new Card(CardFace.FOUR, CardSuite.SPADES), new Card(CardFace.FIVE, CardSuite.SPADES))));
        PokerHand pokerHand = new PokerHand(communityDeck, player);

        assertNotEquals("Rank equal to straight flush!!", HandRank.STRAIGHT_FLUSH, pokerHand.getPokerHandRankType());
    }

    @Test
    public void testFourOfKind() {
        CommunityDeck communityDeck = new CommunityDeck("TS 6D 3S 7C TC");
        Player player = new Player("John", new HashSet<>(Arrays.asList(new Card(CardFace.TEN, CardSuite.HEARTS), new Card(CardFace.TEN, CardSuite.DIAMONDS))));
        PokerHand pokerHand = new PokerHand(communityDeck, player);
        assertTrue(pokerHand.isValid());
        assertEquals("Rank is not Four of a Kind", HandRank.FOUR_OF_KIND, pokerHand.getPokerHandRankType());
    }

    @Test
    public void testInvalidFourOfKind() {
        CommunityDeck communityDeck = new CommunityDeck("TS 6D 3S 7C 9C");
        Player player = new Player("John", new HashSet<>(Arrays.asList(new Card(CardFace.TEN, CardSuite.HEARTS), new Card(CardFace.TEN, CardSuite.DIAMONDS))));
        PokerHand pokerHand = new PokerHand(communityDeck, player);
        assertTrue(pokerHand.isValid());
        assertNotEquals("Rank is Four of a Kind", HandRank.FOUR_OF_KIND, pokerHand.getPokerHandRankType());
    }

    @Test
    public void testFullHouse() {
        CommunityDeck communityDeck = new CommunityDeck("7S 7D 3S 7C 9C");
        Player player = new Player("John", new HashSet<>(Arrays.asList(new Card(CardFace.TEN, CardSuite.HEARTS), new Card(CardFace.TEN, CardSuite.DIAMONDS))));
        PokerHand pokerHand = new PokerHand(communityDeck, player);

        assertTrue(pokerHand.isValid());
        assertEquals("Rank is Full House", HandRank.FULL_HOUSE, pokerHand.getPokerHandRankType());
    }

    @Test
    public void testInvalidFullHouse() {
        CommunityDeck communityDeck = new CommunityDeck("TS 6D 3S 7C 9C");
        Player player = new Player("John", new HashSet<>(Arrays.asList(new Card(CardFace.TEN, CardSuite.HEARTS), new Card(CardFace.TEN, CardSuite.DIAMONDS))));
        PokerHand pokerHand = new PokerHand(communityDeck, player);
        assertTrue(pokerHand.isValid());
        assertNotEquals("Rank is Four of a Kind", HandRank.FULL_HOUSE, pokerHand.getPokerHandRankType());
    }

    @Test
    public void testFlush() {
        CommunityDeck communityDeck = new CommunityDeck("7S 2D 3S 4C 9S");
        Player player = new Player("John", new HashSet<>(Arrays.asList(new Card(CardFace.SIX, CardSuite.SPADES), new Card(CardFace.TEN, CardSuite.SPADES))));
        PokerHand pokerHand = new PokerHand(communityDeck, player);

        assertTrue(pokerHand.isValid());
        assertEquals("Rank is Flush", HandRank.FLUSH, pokerHand.getPokerHandRankType());
    }

    @Test
    public void testInvalidFlush() {
        CommunityDeck communityDeck = new CommunityDeck("KS 6S 3S 7S TS");

        Player player = new Player("John", new HashSet<>(Arrays.asList(new Card(CardFace.FOUR, CardSuite.SPADES), new Card(CardFace.FIVE, CardSuite.SPADES))));
        PokerHand pokerHand = new PokerHand(communityDeck, player);

        assertNotEquals("Rank not equal to flush!!", HandRank.FLUSH, pokerHand.getPokerHandRankType());
    }

    @Test
    public void testStraight() {
        CommunityDeck communityDeck = new CommunityDeck("7S 8D 3S 4C 9S");
        Player player = new Player("John", new HashSet<>(Arrays.asList(new Card(CardFace.SIX, CardSuite.SPADES), new Card(CardFace.TEN, CardSuite.HEARTS))));
        PokerHand pokerHand = new PokerHand(communityDeck, player);

        assertTrue(pokerHand.isValid());
        assertEquals("Rank is Straight", HandRank.STRAIGHT, pokerHand.getPokerHandRankType());
    }

    @Test
    public void testInvalidStraight() {
        CommunityDeck communityDeck = new CommunityDeck("KS 6S 3S 7S TS");

        Player player = new Player("John", new HashSet<>(Arrays.asList(new Card(CardFace.FOUR, CardSuite.SPADES), new Card(CardFace.FIVE, CardSuite.SPADES))));
        PokerHand pokerHand = new PokerHand(communityDeck, player);

        assertNotEquals("Rank equal to straight !!", HandRank.STRAIGHT, pokerHand.getPokerHandRankType());
    }

    @Test
    public void testThreeOfKind() {
        CommunityDeck communityDeck = new CommunityDeck("TS 6D 3S 7C 9C");
        Player player = new Player("John", new HashSet<>(Arrays.asList(new Card(CardFace.TEN, CardSuite.HEARTS), new Card(CardFace.TEN, CardSuite.DIAMONDS))));
        PokerHand pokerHand = new PokerHand(communityDeck, player);
        assertTrue(pokerHand.isValid());
        assertEquals("Rank is not Three of a Kind", HandRank.THREE_OF_KIND, pokerHand.getPokerHandRankType());
    }

    @Test
    public void testInvalidThreeOfKind() {
        CommunityDeck communityDeck = new CommunityDeck("TS 6D 3S 7C TC");
        Player player = new Player("John", new HashSet<>(Arrays.asList(new Card(CardFace.TEN, CardSuite.HEARTS), new Card(CardFace.TEN, CardSuite.DIAMONDS))));
        PokerHand pokerHand = new PokerHand(communityDeck, player);
        assertTrue(pokerHand.isValid());
        assertNotEquals("Rank is Three of a Kind", HandRank.THREE_OF_KIND, pokerHand.getPokerHandRankType());
    }

    @Test
    public void testTwoPair() {
        CommunityDeck communityDeck = new CommunityDeck("7S 4D 3S 7C 9C");
        Player player = new Player("John", new HashSet<>(Arrays.asList(new Card(CardFace.TEN, CardSuite.HEARTS), new Card(CardFace.TEN, CardSuite.DIAMONDS))));
        PokerHand pokerHand = new PokerHand(communityDeck, player);

        assertTrue(pokerHand.isValid());
        assertEquals("Rank is not Two Pair", HandRank.TWO_PAIR, pokerHand.getPokerHandRankType());
    }

    @Test
    public void testInvalidTwoPair() {
        CommunityDeck communityDeck = new CommunityDeck("7S 7D 3S 7C 9C");
        Player player = new Player("John", new HashSet<>(Arrays.asList(new Card(CardFace.TEN, CardSuite.HEARTS), new Card(CardFace.TEN, CardSuite.DIAMONDS))));
        PokerHand pokerHand = new PokerHand(communityDeck, player);

        assertTrue(pokerHand.isValid());
        assertNotEquals("Rank is Two Pair", HandRank.TWO_PAIR, pokerHand.getPokerHandRankType());
    }

    @Test
    public void testOnePair() {
        CommunityDeck communityDeck = new CommunityDeck("7S 4D 3S 6C 9C");
        Player player = new Player("John", new HashSet<>(Arrays.asList(new Card(CardFace.TEN, CardSuite.HEARTS), new Card(CardFace.TEN, CardSuite.DIAMONDS))));
        PokerHand pokerHand = new PokerHand(communityDeck, player);

        assertTrue(pokerHand.isValid());
        assertEquals("Rank is not One Pair", HandRank.ONE_PAIR, pokerHand.getPokerHandRankType());
        assertEquals("high card is not ten:", CardFace.TEN, pokerHand.getHighCardFaceOne());
    }

    @Test
    public void testInvalidOnePair() {
        CommunityDeck communityDeck = new CommunityDeck("7S 4D 3S 7C 9C");
        Player player = new Player("John", new HashSet<>(Arrays.asList(new Card(CardFace.TEN, CardSuite.HEARTS), new Card(CardFace.TEN, CardSuite.DIAMONDS))));
        PokerHand pokerHand = new PokerHand(communityDeck, player);

        assertTrue(pokerHand.isValid());
        assertNotEquals("Rank is One Pair", HandRank.ONE_PAIR, pokerHand.getPokerHandRankType());
        assertNotEquals("high card is ten:", CardFace.TEN, pokerHand.getHighCardFaceOne());
    }


}