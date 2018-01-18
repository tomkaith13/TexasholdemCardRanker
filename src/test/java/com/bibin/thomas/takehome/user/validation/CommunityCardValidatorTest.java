package com.bibin.thomas.takehome.user.validation;

import com.bibin.thomas.takehome.cards.Card;
import com.bibin.thomas.takehome.cards.CardFace;
import com.bibin.thomas.takehome.cards.CardSuite;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class CommunityCardValidatorTest {


    @Test
    public void testCommunityCardValidation() {
        CommunityCardValidator communityCardValidator
                = new CommunityCardValidator("KS AD 3H 7C TD");

        Set<Card> actualSet = communityCardValidator.getValidCardSet();

        Set<Card> expectedCardSet = new HashSet<Card>();
        expectedCardSet.add(new Card(CardFace.KING, CardSuite.SPADES));
        expectedCardSet.add(new Card(CardFace.ACE, CardSuite.DIAMONDS));
        expectedCardSet.add(new Card(CardFace.THREE, CardSuite.HEARTS));
        expectedCardSet.add(new Card(CardFace.SEVEN, CardSuite.CLUBS));
        expectedCardSet.add(new Card(CardFace.TEN, CardSuite.DIAMONDS));

        assertEquals("Actual and expected not equal", expectedCardSet, actualSet);
        assertTrue(communityCardValidator.isValid());
    }

    @Test
    public void testEmptyCommunityCardValidation() {
        CommunityCardValidator communityCardValidator
                = new CommunityCardValidator("");

        Set<Card> actualSet = communityCardValidator.getValidCardSet();

        Set<Card> expectedCardSet = new HashSet<Card>();
        expectedCardSet.add(new Card(CardFace.KING, CardSuite.SPADES));
        expectedCardSet.add(new Card(CardFace.ACE, CardSuite.DIAMONDS));
        expectedCardSet.add(new Card(CardFace.THREE, CardSuite.HEARTS));
        expectedCardSet.add(new Card(CardFace.SEVEN, CardSuite.CLUBS));
        expectedCardSet.add(new Card(CardFace.TEN, CardSuite.DIAMONDS));

        assertNotEquals("Actual and expected equal", expectedCardSet, actualSet);
        assertFalse(communityCardValidator.isValid());
    }

    @Test
    public void testExtraSpacesCommunityCardValidation() {
        CommunityCardValidator communityCardValidator
                = new CommunityCardValidator("KS    AD  3H TD       7C");

        Set<Card> actualSet = communityCardValidator.getValidCardSet();

        Set<Card> expectedCardSet = new HashSet<Card>();
        expectedCardSet.add(new Card(CardFace.KING, CardSuite.SPADES));
        expectedCardSet.add(new Card(CardFace.ACE, CardSuite.DIAMONDS));
        expectedCardSet.add(new Card(CardFace.THREE, CardSuite.HEARTS));
        expectedCardSet.add(new Card(CardFace.SEVEN, CardSuite.CLUBS));
        expectedCardSet.add(new Card(CardFace.TEN, CardSuite.DIAMONDS));

        assertEquals("Actual and expected not equal", expectedCardSet, actualSet);
        assertTrue(communityCardValidator.isValid());
    }


    @Test
    public void testLowercaseCommunityCardValidation() {
        CommunityCardValidator communityCardValidator
                = new CommunityCardValidator("ks    AD  3h TD       7c");

        Set<Card> actualSet = communityCardValidator.getValidCardSet();

        Set<Card> expectedCardSet = new HashSet<Card>();
        expectedCardSet.add(new Card(CardFace.KING, CardSuite.SPADES));
        expectedCardSet.add(new Card(CardFace.ACE, CardSuite.DIAMONDS));
        expectedCardSet.add(new Card(CardFace.THREE, CardSuite.HEARTS));
        expectedCardSet.add(new Card(CardFace.SEVEN, CardSuite.CLUBS));
        expectedCardSet.add(new Card(CardFace.TEN, CardSuite.DIAMONDS));

        assertEquals("Actual and expected not equal", expectedCardSet, actualSet);
        assertTrue(communityCardValidator.isValid());
    }

    @Test
    public void testInvalidCommunityCardValidator() {

        CommunityCardValidator ccValidator
                = new CommunityCardValidator("ZZ 3H XY");

        assertFalse(ccValidator.isValid());
    }

}