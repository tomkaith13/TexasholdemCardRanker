package com.bibin.thomas.takehome.game;

import com.bibin.thomas.takehome.Player.Player;
import com.bibin.thomas.takehome.deck.CommunityDeck;

import java.util.*;

// This class does the analysis of the community card and player card
public class GameRanker {

    private CommunityDeck communityDeck;
    private Set<Player> playerSet;

    //this list is the one used to derive the ranking of each hand
    private List<PokerHand> playerHandList = new ArrayList<>();

    public GameRanker(CommunityDeck communityDeck, Set<Player> playerSet) {
        this.communityDeck = communityDeck;
        this.playerSet = playerSet;

        generatePlayerPokerHand();

    }

    // function for displayng ranking
    public void displayPlayerHandRankings() {
        System.out.println("==========================================");
        System.out.println("Player Rankings:");
        int rank = 1;
        for (PokerHand pHand : playerHandList) {
            System.out.print(rank);
            System.out.print(" " + pHand.getPlayer().getName());
            System.out.print(" " + pHand.getPokerHandRankType().toString());
            if (pHand.isHighCardFaceOnePresent() && pHand.isHighCardFaceTwoPresent())
                System.out.println(" " + pHand.getHighCardFaceOne() + " " + pHand.getHighCardFaceTwo());
            else if (pHand.isHighCardFaceOnePresent())
                System.out.println(" " + pHand.getHighCardFaceOne());
            else
                System.out.println("");

            rank++;

        }

    }

    // used to generate pokerhand for each player adn store in playerHandList.
    private void generatePlayerPokerHand() {
        for (Player p : playerSet) {
            PokerHand pHand = new PokerHand(communityDeck, p);

            if (pHand.isValid()) {
                playerHandList.add(pHand);
            } else {
                System.out.println("Invalid poker hand.. Ignoring player input");
            }
        }

        //sort the hands once so that it can be displayed correctly
        Collections.sort(playerHandList);
    }


}
