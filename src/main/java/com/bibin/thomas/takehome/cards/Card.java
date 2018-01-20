package com.bibin.thomas.takehome.cards;

import com.bibin.thomas.takehome.globals.GlobalMaps;

import java.util.Objects;

public class Card implements Comparable<Card> {
    private CardSuite suite;
    private CardFace face;
    private int faceRank;

    public Card(CardFace face, CardSuite suite) {
        this.suite = suite;
        this.face = face;
        this.faceRank = GlobalMaps.faceRankMap.get(face);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Card) {
            Card otherCard = (Card) obj;

            return this.face == otherCard.face && this.suite == otherCard.suite;

        }
        return false;
    }

    public int compareTo(Card o) {
        return Integer.compare(this.faceRank, o.faceRank);
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

    public int getFaceRank() {
        return faceRank;
    }
}
