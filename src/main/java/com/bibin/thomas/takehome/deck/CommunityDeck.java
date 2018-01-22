package com.bibin.thomas.takehome.deck;

import com.bibin.thomas.takehome.cards.Card;
import com.bibin.thomas.takehome.user.validation.CommunityCardValidator;

import java.util.Set;

public class CommunityDeck {
    private String inputString;
    private Set<Card> communityCardSet;
    private boolean isValid;

    public CommunityDeck(String userInput) {
        this.inputString = userInput;
        CommunityCardValidator communityCardValidator =
                new CommunityCardValidator(this.inputString);

        if (communityCardValidator.isValid()) {
            this.communityCardSet = communityCardValidator.getValidCardSet();
            this.isValid = true;
        } else {
            this.isValid = false;
        }
    }

    public boolean isValid() {
        return isValid;
    }

    public Set<Card> getCommunityCardSet() {
        return communityCardSet;
    }
}
