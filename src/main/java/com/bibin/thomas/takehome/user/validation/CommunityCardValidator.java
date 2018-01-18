package com.bibin.thomas.takehome.user.validation;

import com.bibin.thomas.takehome.cards.Card;
import com.bibin.thomas.takehome.cards.CardFace;
import com.bibin.thomas.takehome.cards.CardSuite;
import com.bibin.thomas.takehome.globals.GlobalMaps;

import java.util.*;

public class CommunityCardValidator {
    private String input;
    private boolean isValid = true;
    Set<Card> validCardSet;


    public CommunityCardValidator(String input) {
        validCardSet = new HashSet<Card>();
        this.input = input.trim();
        List<String> cardStringList = cardSplitter();

        for (String cardString : cardStringList) {
            if (cardString.length() != 2) {
                isValid = false;
                return;
            } else {

                Character charFace = cardString.charAt(0);
                Character charSuit = cardString.charAt(1);

                if (Character.isAlphabetic(charFace)) {
                    charFace = Character.toUpperCase(charFace);
                }

                if (Character.isAlphabetic(charSuit)) {
                    charSuit = Character.toUpperCase(charSuit);
                }

                CardFace face = GlobalMaps.faceMap.get(charFace);
                CardSuite suite = GlobalMaps.suiteMap.get(charSuit);

                if (face == null || suite == null) {
                    isValid = false;
                    return;
                }

                Card c = new Card(face, suite);
                validCardSet.add(c);
            }

        }
    }

    public boolean isValid() {
        return this.isValid;
    }

    private List<String> cardSplitter() {
        return Arrays.asList(input.split(" +"));
    }

    public Set<Card> getValidCardSet() {
        return validCardSet;
    }
}
