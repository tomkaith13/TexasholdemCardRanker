package com.bibin.thomas.takehome.cards;

import java.util.Objects;

public class Card {
    private CardSuite suite;
    private CardFace face;

    public Card(CardFace face, CardSuite suite) {
        this.suite = suite;
        this.face = face;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Card) {
            Card otherCard = (Card) obj;

            if (this.face == otherCard.face && this.suite == otherCard.suite)
                return true;
            else
                return false;

        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.suite, this.face);
    }

    public CardSuite getSuite() {
        return suite;
    }

    public CardFace getFace() {
        return face;
    }
}
