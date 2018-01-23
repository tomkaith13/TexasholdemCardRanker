package com.bibin.thomas.takehome;

import com.bibin.thomas.takehome.Player.Player;
import com.bibin.thomas.takehome.deck.CommunityDeck;
import com.bibin.thomas.takehome.game.GameRanker;
import com.bibin.thomas.takehome.game.HandRank;
import com.bibin.thomas.takehome.game.PokerHand;
import com.bibin.thomas.takehome.user.validation.PlayerCardValidator;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * Unit test for simple TexasHoldemGame.
 */
public class TexasHoldemGameTest {
//    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
//
//    @Before
//    public void setUp() {
//        System.setOut(new PrintStream(outContent));
//        System.setErr(new PrintStream(errContent));
//    }
//
//    @After
//    public void tearDown() {
//        System.setOut(null);
//        System.setErr(null);
//    }


    @Test
    public void testGame() {

//        TexasHoldemGame.main(null);
//        assertEquals("Welcome TexasHoldem!", outContent.toString());
        Set<Player> playerSet = new HashSet<>();
        CommunityDeck communityDeck = new CommunityDeck("KS AS 3H 7C TS");

        PlayerCardValidator playerCardValidator = new PlayerCardValidator("John QS JS");
        playerSet.add(new Player(playerCardValidator.getPlayerName(), playerCardValidator.getValidCardSet()));

        playerCardValidator = new PlayerCardValidator("Joe 5C 9D");
        playerSet.add(new Player(playerCardValidator.getPlayerName(), playerCardValidator.getValidCardSet()));

        playerCardValidator = new PlayerCardValidator("Becky JD QC");
        playerSet.add(new Player(playerCardValidator.getPlayerName(), playerCardValidator.getValidCardSet()));

        playerCardValidator = new PlayerCardValidator("Sam AC KH");
        playerSet.add(new Player(playerCardValidator.getPlayerName(), playerCardValidator.getValidCardSet()));

        GameRanker gameRanker = new GameRanker(communityDeck, playerSet);

        List<PokerHand> pokerHandList = gameRanker.getPlayerHandList();

        PokerHand johnPokerHand = pokerHandList
                .stream()
                .filter(ph -> ph.getPlayer().getName().equals("John"))
                .findFirst().orElse(null);

        assertNotNull(johnPokerHand);

        assertEquals("John rank is not royal flush",
                HandRank.ROYAL_FLUSH,
                johnPokerHand.getPokerHandRankType());

        PokerHand joePokerHand = pokerHandList
                .stream()
                .filter(ph -> ph.getPlayer().getName().equals("Joe"))
                .findFirst()
                .orElse(null);

        assertNotNull(joePokerHand);

        assertEquals("Joe rank is not High Card",
                HandRank.HIGH_CARD,
                joePokerHand.getPokerHandRankType());

        PokerHand beckyPokerHand = pokerHandList
                .stream()
                .filter(ph -> ph.getPlayer().getName().equals("Becky"))
                .findFirst()
                .orElse(null);

        assertNotNull(beckyPokerHand);
        assertEquals("Becky rank is not Stright Ace",
                HandRank.STRAIGHT,
                beckyPokerHand.getPokerHandRankType());

        PokerHand samPokerHand = pokerHandList
                .stream()
                .filter(ph -> ph.getPlayer().getName().equals("Sam"))
                .findFirst()
                .orElse(null);

        assertNotNull(samPokerHand);
        assertEquals("Sam rank is not Two Pair",
                HandRank.TWO_PAIR,
                samPokerHand.getPokerHandRankType());


    }
}
