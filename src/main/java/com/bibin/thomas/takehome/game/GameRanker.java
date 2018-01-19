package com.bibin.thomas.takehome.game;

import com.bibin.thomas.takehome.Player.Player;
import com.bibin.thomas.takehome.deck.CommunityDeck;

import java.util.*;

// This class does the analysis of the community card and player card
public class GameRanker {

    private CommunityDeck communityDeck;
    private Set<Player> playerSet;
    private List<PokerHand> playerHandList = new LinkedList<PokerHand>();

    public GameRanker(CommunityDeck communityDeck, Set<Player> playerSet) {
        this.communityDeck = communityDeck;
        this.playerSet = playerSet;

        generatePlayerPokerHand();
        displaySortedPlayerHand();
    }

    private void displaySortedPlayerHand() {

    }

    private void generatePlayerPokerHand() {
        for (Player p : playerSet) {
            playerHandList.add(new PokerHand(communityDeck, p));
        }

        Collections.sort(playerHandList);
        playerHandList.size();

    }


    public void printPlayerRank() {
        System.out.println("Player Rankings:");
    }
}
