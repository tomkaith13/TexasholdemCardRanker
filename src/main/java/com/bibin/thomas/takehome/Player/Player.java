package com.bibin.thomas.takehome.Player;

import com.bibin.thomas.takehome.cards.Card;

import java.util.Set;

public class Player {
    private String name;
    private Set<Card> hand;
    private String input;

    public Player(String input) {
        this.input = input;
    }

    public boolean isValid() {
        return true;
    }
}
