package com.bibin.thomas.takehome.Player;

import com.bibin.thomas.takehome.cards.Card;

import java.util.Objects;
import java.util.Set;

public class Player {
    private String name;
    private Set<Card> hand;

    public Player(String name, Set<Card> hand) {
        this.name = name;
        this.hand = hand;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Player) {
            Player otherObj = (Player) obj;

            if (name.equals(otherObj.name) && hand.equals(otherObj.hand))
                return true;
        }
        return false;
    }

    public Set<Card> getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }
}
