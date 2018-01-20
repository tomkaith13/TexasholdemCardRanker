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

    }

    public void displaySortedPlayerHand() {
        System.out.println("==========================================");
        System.out.println("Player Rankings:");
        for (PokerHand pHand : playerHandList) {
            System.out.println("Player:" + pHand.getPlayer().getName());
            System.out.println("rank:" + pHand.getPokerHandRankType().toString());
            System.out.println("");
        }

    }

    private void generatePlayerPokerHand() {
        for (Player p : playerSet) {
            PokerHand pHand = new PokerHand(communityDeck, p);

            if (pHand.isValid()) {
                playerHandList.add(pHand);
            } else {
                System.out.println("Invalid poker hand.. Ignoring player input");
            }
        }
        Collections.sort(playerHandList);
    }


}
