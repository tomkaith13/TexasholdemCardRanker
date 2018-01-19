package com.bibin.thomas.takehome.game;

import com.bibin.thomas.takehome.Player.Player;
import com.bibin.thomas.takehome.cards.Card;
import com.bibin.thomas.takehome.cards.CardFace;
import com.bibin.thomas.takehome.cards.CardSuite;
import com.bibin.thomas.takehome.deck.CommunityDeck;
import com.bibin.thomas.takehome.globals.GlobalMaps;

import java.util.*;
import java.util.stream.Collectors;

public class PokerHand implements Comparable<PokerHand> {
    private CommunityDeck communityDeck;
    private Player player;
    private HandRank pokerHandRankType;
    private Set<Card> rankedSet;
    private int rank = 0;

    public PokerHand(CommunityDeck communityDeck, Player player) {
        this.communityDeck = communityDeck;
        this.player = player;
        rankedSet = new TreeSet<Card>();

        rankedSet.addAll(communityDeck.getCommunityCardSet());
        rankedSet.addAll(player.getHand());

        calculateHandRank();

    }

    public void calculateHandRank() {

        if (isRoyalFlush()) {
            this.pokerHandRankType = HandRank.ROYAL_FLUSH;
            this.rank = GlobalMaps.handRankMap.get(HandRank.ROYAL_FLUSH);

        } else if (isStraightFlush()) {
            this.pokerHandRankType = HandRank.STRAIGHT_FLUSH;
            this.rank = GlobalMaps.handRankMap.get(HandRank.STRAIGHT_FLUSH);

        } else if (isFourOfKind()) {
            this.pokerHandRankType = HandRank.FOUR_OF_KIND;
            this.rank = GlobalMaps.handRankMap.get(HandRank.FOUR_OF_KIND);

        } else if (isFullHouse()) {
            this.pokerHandRankType = HandRank.FULL_HOUSE;
            this.rank = GlobalMaps.handRankMap.get(HandRank.FULL_HOUSE);

        } else if (isFlush()) {
            this.pokerHandRankType = HandRank.FLUSH;
            this.rank = GlobalMaps.handRankMap.get(HandRank.FLUSH);

        } else if (isStraight()) {
            this.pokerHandRankType = HandRank.STRAIGHT;
            this.rank = GlobalMaps.handRankMap.get(HandRank.STRAIGHT);

        } else if (isThreeOfKind()) {
            this.pokerHandRankType = HandRank.THREE_OF_KIND;
            this.rank = GlobalMaps.handRankMap.get(HandRank.THREE_OF_KIND);

        } else if (isTwoPair()) {
            this.pokerHandRankType = HandRank.TWO_PAIR;
            this.rank = GlobalMaps.handRankMap.get(HandRank.TWO_PAIR);

        } else if (isOnePair()) {
            this.pokerHandRankType = HandRank.ONE_PAIR;
            this.rank = GlobalMaps.handRankMap.get(HandRank.ONE_PAIR);

        } else {
            //high card
            this.pokerHandRankType = HandRank.HIGH_CARD;
            this.rank = GlobalMaps.handRankMap.get(HandRank.HIGH_CARD);
        }

    }

    private boolean isOnePair() {
        return false;
    }

    private boolean isTwoPair() {
        return false;
    }

    private boolean isThreeOfKind() {
        return false;
    }

    private boolean isStraight() {
        return false;
    }

    private boolean isFlush() {
        return false;
    }

    private boolean isFullHouse() {
        return false;
    }

    private boolean isFourOfKind() {
        return false;
    }

    private boolean isStraightFlush() {
        return false;
    }

    private boolean isRoyalFlush() {
        //check if all suits are the same

        Iterator<Card> iter = rankedSet.iterator();

        List<Card> aceList = rankedSet.stream()
                .filter(c -> c.getFace().equals(CardFace.ACE))
                .collect(Collectors.toList());

        if (aceList.isEmpty())
            return false;

        List<Card> kingList = rankedSet.stream()
                .filter(c -> c.getFace().equals(CardFace.KING))
                .collect(Collectors.toList());

        List<Card> queenList = rankedSet.stream()
                .filter(c -> c.getFace().equals(CardFace.QUEEN))
                .collect(Collectors.toList());

        List<Card> jackList = rankedSet.stream()
                .filter(c -> c.getFace().equals(CardFace.JACK))
                .collect(Collectors.toList());

        List<Card> tenList = rankedSet.stream()
                .filter(c -> c.getFace().equals(CardFace.TEN))
                .collect(Collectors.toList());

        for (Card aceCard : aceList) {
            final CardSuite aceCardSuit = aceCard.getSuite();

            if ((kingList.stream().filter(c -> c.getSuite().equals(aceCardSuit)).findAny().isPresent()) &&
                    (queenList.stream().filter(c -> c.getSuite().equals(aceCardSuit)).findAny().isPresent()) &&
                    (jackList.stream().filter(c -> c.getSuite().equals(aceCardSuit)).findAny().isPresent()) &&
                    (tenList.stream().filter(c -> c.getSuite().equals(aceCardSuit)).findAny().isPresent())
                    ) {
                return true;
            }
        }

        return false;
    }

    public int compareTo(PokerHand o) {
        return Integer.compare(rank, o.rank);
    }

    public HandRank getPokerHandRankType() {
        return pokerHandRankType;
    }

    //    @Override
//    public int hashCode() {
//        return Objects.hashCode(player);
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (obj instanceof Player) {
//            Player oPlayer = (Player) obj;
//            if (player.equals(oPlayer))
//                return true;
//        }
//        return false;
//    }


}
