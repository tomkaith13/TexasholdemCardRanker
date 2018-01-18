package com.bibin.thomas.takehome.user.validation;

import com.bibin.thomas.takehome.cards.Card;
import com.bibin.thomas.takehome.cards.CardFace;
import com.bibin.thomas.takehome.cards.CardSuite;
import com.bibin.thomas.takehome.globals.GlobalMaps;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlayerCardValidator {
    private String input;
    private String playerName;
    private Set<Card> validCardSet;
    private boolean isValid = true;

    public PlayerCardValidator(String input) {
        validCardSet = new HashSet<Card>();
        this.input = input.trim();
        List<String> cardStringList = cardSplitter();
        this.playerName = cardStringList.get(0);

        cardStringList = cardStringList.subList(1, cardStringList.size());

        if (cardStringList.size() < 2) {
            isValid = false;
            return;
        }

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

    public String getPlayerName() {
        return playerName;
    }

    public Set<Card> getValidCardSet() {
        return validCardSet;
    }

    private List<String> cardSplitter() {
        return Arrays.asList(input.split(" +"));
    }

    public boolean isValid() {
        return isValid;
    }
}
