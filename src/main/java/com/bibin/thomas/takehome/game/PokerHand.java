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
    private boolean isValid = true;
    private int rank = 0;

    public PokerHand(CommunityDeck communityDeck, Player player) {
        this.communityDeck = communityDeck;
        this.player = player;
        rankedSet = new HashSet<>();

        rankedSet.addAll(communityDeck.getCommunityCardSet());
        rankedSet.addAll(player.getHand());

        if (rankedSet.size() != 7) {
            System.out.println("Error in Player:" + player.getName());
            System.out.println("Duplicates in community cards and player cards");
            isValid = false;
            return;
        }

        calculateHandRank();

    }

    private void calculateHandRank() {

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
        boolean tripleFound = false, pairFound = false;

        for (CardFace cFace : CardFace.values()) {
            if (rankedSet.stream().filter(c -> c.getFace().equals(cFace)).count() == 3) {
                tripleFound = true;
                continue;
            }

            if (rankedSet.stream().filter(c -> c.getFace().equals(cFace)).count() >= 2) {
                pairFound = true;
            }
        }
        return tripleFound && pairFound;
    }

    private boolean isFourOfKind() {
        for (CardFace cFace : CardFace.values()) {
            if (rankedSet.stream().filter(c -> c.getFace().equals(cFace)).count() == 4)
                return true;
        }
        return false;
    }

    private boolean isStraightFlush() {
        //create a list of all four suites

        List<Card> clubsList = rankedSet.stream()
                .filter(c -> c.getSuite().equals(CardSuite.CLUBS))
                .collect(Collectors.toList());

        List<Card> diamondsList = rankedSet.stream()
                .filter(c -> c.getSuite().equals(CardSuite.DIAMONDS))
                .collect(Collectors.toList());
        List<Card> heartsList = rankedSet.stream()
                .filter(c -> c.getSuite().equals(CardSuite.HEARTS))
                .collect(Collectors.toList());
        List<Card> spadesList = rankedSet.stream()
                .filter(c -> c.getSuite().equals(CardSuite.SPADES))
                .collect(Collectors.toList());

        if (clubsList.size() < 5 &&
                diamondsList.size() < 5 &&
                heartsList.size() < 5 &&
                spadesList.size() < 5)
            return false;

        if (clubsList.size() >= 5) {
            if (foundIncreasingCardSequence(clubsList))
                return true;
        } else if (diamondsList.size() >= 5) {
            if (foundIncreasingCardSequence(diamondsList))
                return true;
        }
        if (heartsList.size() >= 5) {
            return foundIncreasingCardSequence(heartsList);
        } else if (spadesList.size() >= 5) {
            return foundIncreasingCardSequence(spadesList);
        }

        return false;
    }

    private boolean foundIncreasingCardSequence(List<Card> cardList) {
        Collections.sort(cardList);

        for (int i = 0; i <= cardList.size() - 5; i++) {
            List<Card> subList = cardList.subList(i, i + 5);

            //using arithmetic progression to figure out if increasing by one
            // the formula for ap sum is :  ap_sum = n/2 * [2a +(n-1)*d] where d = 1 and n = 5
            int sum = subList.stream().mapToInt(Card::getFaceRank).sum();
            int firstCardRank = subList.get(0).getFaceRank();

            int apSum = (5 * ((2 * firstCardRank) + 4)) / 2;

            if (sum == apSum)
                return true;
        }
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

            if ((kingList.stream().anyMatch(c -> c.getSuite().equals(aceCardSuit))) &&
                    (queenList.stream().anyMatch(c -> c.getSuite().equals(aceCardSuit))) &&
                    (jackList.stream().anyMatch(c -> c.getSuite().equals(aceCardSuit))) &&
                    (tenList.stream().anyMatch(c -> c.getSuite().equals(aceCardSuit)))
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

    public boolean isValid() {
        return isValid;
    }

}
