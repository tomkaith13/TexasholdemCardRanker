package com.bibin.thomas.takehome.game;

import com.bibin.thomas.takehome.Player.Player;
import com.bibin.thomas.takehome.deck.CommunityDeck;

import java.util.*;

// This class does the analysis of the community card and player card
public class GameRanker {

    private CommunityDeck communityDeck;
    private Set<Player> playerSet;
    private List<PokerHand> playerHandList = new LinkedList<>();

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
            PokerHand pHand = new PokerHand(communityDeck, p);

            if (pHand.isValid()) {
                playerHandList.add(pHand);
            } else {
                break;
            }
        }

        Collections.sort(playerHandList);
        playerHandList.size();

    }


    public void printPlayerRank() {
        System.out.println("Player Rankings:");
    }
}
