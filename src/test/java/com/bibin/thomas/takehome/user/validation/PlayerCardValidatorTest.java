package com.bibin.thomas.takehome.user.validation;

import com.bibin.thomas.takehome.cards.Card;
import com.bibin.thomas.takehome.cards.CardFace;
import com.bibin.thomas.takehome.cards.CardSuite;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class PlayerCardValidatorTest {

    @Test
    public void testPlayerCardValidator() {

        PlayerCardValidator pcValidator = new PlayerCardValidator("John 9H 7S");
        assertEquals("PlayerName is different:", "John", pcValidator.getPlayerName());

        Set<Card> actualSet = pcValidator.getValidCardSet();
        Set<Card> expectedSet = new HashSet<Card>();

        expectedSet.add(new Card(CardFace.NINE, CardSuite.HEARTS));
        expectedSet.add(new Card(CardFace.SEVEN, CardSuite.SPADES));

        assertEquals("expected and actual card sets differ:", expectedSet, actualSet);
        assertTrue(pcValidator.isValid());
    }

    @Test
    public void testCaseInsensitivePlayerCardValidator() {

        PlayerCardValidator pcValidator = new PlayerCardValidator("John Qh tS");
        assertEquals("PlayerName is different:", "John", pcValidator.getPlayerName());

        Set<Card> actualSet = pcValidator.getValidCardSet();
        Set<Card> expectedSet = new HashSet<Card>();

        expectedSet.add(new Card(CardFace.QUEEN, CardSuite.HEARTS));
        expectedSet.add(new Card(CardFace.TEN, CardSuite.SPADES));

        assertEquals("expected and actual card sets differ:", expectedSet, actualSet);
        assertTrue(pcValidator.isValid());
    }

    @Test
    public void testInvalidPlayerCardValidator() {

        PlayerCardValidator pcValidator = new PlayerCardValidator("John ZZ XY");
        assertEquals("PlayerName is different:", "John", pcValidator.getPlayerName());

        assertFalse(pcValidator.isValid());
    }

    @Test
    public void testSpaceInsensitivePlayerCardValidator() {

        PlayerCardValidator pcValidator = new PlayerCardValidator("       John   Qh tS ");
        assertEquals("PlayerName is different:", "John", pcValidator.getPlayerName());

        Set<Card> actualSet = pcValidator.getValidCardSet();
        Set<Card> expectedSet = new HashSet<Card>();

        expectedSet.add(new Card(CardFace.QUEEN, CardSuite.HEARTS));
        expectedSet.add(new Card(CardFace.TEN, CardSuite.SPADES));

        assertEquals("expected and actual card sets differ:", expectedSet, actualSet);
        assertTrue(pcValidator.isValid());
    }

}