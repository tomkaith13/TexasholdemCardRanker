package com.bibin.thomas.takehome.game;

import com.bibin.thomas.takehome.Player.Player;
import com.bibin.thomas.takehome.cards.Card;
import com.bibin.thomas.takehome.cards.CardFace;
import com.bibin.thomas.takehome.cards.CardSuite;
import com.bibin.thomas.takehome.deck.CommunityDeck;
import com.bibin.thomas.takehome.globals.GlobalMaps;

import java.util.*;
import java.util.stream.Collectors;

/*
    The class stores the community deck and the player input
    and verifies if they have 7 cards in the set.

    Each hand is finally stored in a list and must be comparable
    to create the ranking.
 */
public class PokerHand implements Comparable<PokerHand> {
    private CommunityDeck communityDeck;
    private Player player;
    private HandRank pokerHandRankType;
    private Set<Card> handSet;
    private boolean isValid = true;

    private int handRank = 0;
    private boolean isHighCardFaceOnePresent = false;
    private boolean isHighCardFaceTwoPresent = false;
    private CardFace highCardFaceOne;
    private CardFace highCardFaceTwo;
    private int highCardRank;

    public PokerHand(CommunityDeck communityDeck, Player player) {
        this.communityDeck = communityDeck;
        this.player = player;
        handSet = new HashSet<>();

        handSet.addAll(communityDeck.getCommunityCardSet());
        handSet.addAll(player.getHand());

        if (handSet.size() != 7) {
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
            this.handRank = GlobalMaps.handRankMap.get(HandRank.ROYAL_FLUSH);

        } else if (isStraightFlush()) {
            this.pokerHandRankType = HandRank.STRAIGHT_FLUSH;
            this.handRank = GlobalMaps.handRankMap.get(HandRank.STRAIGHT_FLUSH);

        } else if (isFourOfKind()) {
            this.pokerHandRankType = HandRank.FOUR_OF_KIND;
            this.handRank = GlobalMaps.handRankMap.get(HandRank.FOUR_OF_KIND);

        } else if (isFullHouse()) {
            this.pokerHandRankType = HandRank.FULL_HOUSE;
            this.handRank = GlobalMaps.handRankMap.get(HandRank.FULL_HOUSE);

        } else if (isFlush()) {
            this.pokerHandRankType = HandRank.FLUSH;
            this.handRank = GlobalMaps.handRankMap.get(HandRank.FLUSH);

        } else if (isStraight()) {
            this.pokerHandRankType = HandRank.STRAIGHT;
            this.handRank = GlobalMaps.handRankMap.get(HandRank.STRAIGHT);

        } else if (isThreeOfKind()) {
            this.pokerHandRankType = HandRank.THREE_OF_KIND;
            this.handRank = GlobalMaps.handRankMap.get(HandRank.THREE_OF_KIND);

        } else if (isTwoPair()) {
            this.pokerHandRankType = HandRank.TWO_PAIR;
            this.handRank = GlobalMaps.handRankMap.get(HandRank.TWO_PAIR);

        } else if (isOnePair()) {
            this.pokerHandRankType = HandRank.ONE_PAIR;
            this.handRank = GlobalMaps.handRankMap.get(HandRank.ONE_PAIR);

        } else {
            //high card
            this.pokerHandRankType = HandRank.HIGH_CARD;
            this.handRank = GlobalMaps.handRankMap.get(HandRank.HIGH_CARD);
            List<Card> handList = new ArrayList<>(handSet);
            Collections.sort(handList);
            isHighCardFaceOnePresent = true;
            highCardFaceOne = handList.get(0).getFace();
            highCardRank = GlobalMaps.faceRankMap.get(highCardFaceOne);

        }

    }

    private boolean isOnePair() {
        for (CardFace cFace : CardFace.values()) {
            if (handSet.stream().filter(c -> c.getFace().equals(cFace)).count() == 2) {
                isHighCardFaceOnePresent = true;
                highCardFaceOne = cFace;
                highCardRank = GlobalMaps.faceRankMap.get(highCardFaceOne);

                return true;
            }
        }
        return false;
    }

    private boolean isTwoPair() {
        boolean onePairFound = false;

        for (CardFace cFace : CardFace.values()) {
            if (!onePairFound &&
                    handSet.stream().filter(c -> c.getFace().equals(cFace)).count() == 2) {
                onePairFound = true;
                isHighCardFaceOnePresent = true;
                highCardFaceOne = cFace;
                highCardRank += GlobalMaps.faceRankMap.get(highCardFaceOne);
                continue;
            }

            if (handSet.stream().filter(c -> c.getFace().equals(cFace)).count() == 2) {
//                twoPairFound = true;
                isHighCardFaceTwoPresent = true;
                highCardFaceTwo = cFace;
                highCardRank += GlobalMaps.faceRankMap.get(highCardFaceTwo);
            }
        }

        return checkIfBothHighCardPresentElseReset();
    }

    private boolean checkIfBothHighCardPresentElseReset() {
        if (isHighCardFaceOnePresent && isHighCardFaceTwoPresent)
            return true;
        else {
            isHighCardFaceOnePresent = false;
            isHighCardFaceTwoPresent = false;
            return false;
        }
    }

    private boolean isThreeOfKind() {
        for (CardFace cFace : CardFace.values()) {
            if (handSet.stream().filter(c -> c.getFace().equals(cFace)).count() == 3) {
                isHighCardFaceOnePresent = true;
                highCardFaceOne = cFace;
                highCardRank = GlobalMaps.faceRankMap.get(highCardFaceOne);
                return true;
            }
        }
        return false;
    }

    private boolean isStraight() {
        List<Card> handList = new ArrayList<>(handSet);
        Collections.sort(handList);

        return detectFlushOrStraightFlushAndRank(handList);
    }

    private boolean isFlush() {
        for (CardSuite cardSuite : CardSuite.values()) {
            if (handSet.stream().filter(c -> c.getSuite().equals(cardSuite)).count() == 5) {
                List<Card> handList = new ArrayList<>(handSet);
                Collections.sort(handList);
                isHighCardFaceOnePresent = true;
                highCardFaceOne = handList.get(0).getFace();
                highCardRank = GlobalMaps.faceRankMap.get(highCardFaceOne);
                return true;
            }
        }
        return false;
    }

    private boolean isFullHouse() {
//        boolean tripleFound = false, pairFound = false;

        for (CardFace cFace : CardFace.values()) {
            if (handSet.stream().filter(c -> c.getFace().equals(cFace)).count() == 3) {
//                tripleFound = true;
                isHighCardFaceOnePresent = true;
                highCardFaceOne = cFace;
                highCardRank += GlobalMaps.faceRankMap.get(highCardFaceOne);
                continue;
            }

            if (handSet.stream().filter(c -> c.getFace().equals(cFace)).count() >= 2) {
//                pairFound = true;
                isHighCardFaceTwoPresent = true;
                highCardFaceTwo = cFace;
                highCardRank += GlobalMaps.faceRankMap.get(highCardFaceTwo);
            }
        }
        return checkIfBothHighCardPresentElseReset();
    }

    private boolean isFourOfKind() {
        for (CardFace cFace : CardFace.values()) {
            if (handSet.stream().filter(c -> c.getFace().equals(cFace)).count() == 4) {
                isHighCardFaceOnePresent = true;
                highCardFaceOne = cFace;
                highCardRank = GlobalMaps.faceRankMap.get(highCardFaceOne);
                return true;
            }
        }
        return false;
    }

    private boolean isStraightFlush() {
        //create a list of all four suites

        List<Card> clubsList = handSet.stream()
                .filter(c -> c.getSuite().equals(CardSuite.CLUBS))
                .collect(Collectors.toList());

        List<Card> diamondsList = handSet.stream()
                .filter(c -> c.getSuite().equals(CardSuite.DIAMONDS))
                .collect(Collectors.toList());
        List<Card> heartsList = handSet.stream()
                .filter(c -> c.getSuite().equals(CardSuite.HEARTS))
                .collect(Collectors.toList());
        List<Card> spadesList = handSet.stream()
                .filter(c -> c.getSuite().equals(CardSuite.SPADES))
                .collect(Collectors.toList());

        if (clubsList.size() < 5 &&
                diamondsList.size() < 5 &&
                heartsList.size() < 5 &&
                spadesList.size() < 5)
            return false;

        if (clubsList.size() >= 5) {
            return detectFlushOrStraightFlushAndRank(clubsList);
        } else if (diamondsList.size() >= 5) {
            return detectFlushOrStraightFlushAndRank(diamondsList);
        }
        if (heartsList.size() >= 5) {
            return detectFlushOrStraightFlushAndRank(heartsList);
        } else {
            return detectFlushOrStraightFlushAndRank(spadesList);
        }

    }

    private boolean detectFlushOrStraightFlushAndRank(List<Card> cardList) {
        Collections.sort(cardList);
        if (foundIncreasingCardSequence(cardList)) {
            isHighCardFaceOnePresent = true;
            highCardFaceOne = cardList.get(0).getFace();
            highCardRank = GlobalMaps.faceRankMap.get(highCardFaceOne);
            return true;
        }
        return false;
    }

    private boolean foundIncreasingCardSequence(List<Card> cardList) {
//        Collections.sort(cardList);

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

        Iterator<Card> iter = handSet.iterator();

        List<Card> aceList = handSet.stream()
                .filter(c -> c.getFace().equals(CardFace.ACE))
                .collect(Collectors.toList());

        if (aceList.isEmpty())
            return false;

        List<Card> kingList = handSet.stream()
                .filter(c -> c.getFace().equals(CardFace.KING))
                .collect(Collectors.toList());

        List<Card> queenList = handSet.stream()
                .filter(c -> c.getFace().equals(CardFace.QUEEN))
                .collect(Collectors.toList());

        List<Card> jackList = handSet.stream()
                .filter(c -> c.getFace().equals(CardFace.JACK))
                .collect(Collectors.toList());

        List<Card> tenList = handSet.stream()
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
        if (handRank != o.handRank)
            return Integer.compare(handRank, o.handRank);
        else
            return Integer.compare(highCardRank, o.highCardRank);

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

    public Player getPlayer() {
        return player;
    }


    public CardFace getHighCardFaceOne() {
        return highCardFaceOne;
    }

    public CardFace getHighCardFaceTwo() {
        return highCardFaceTwo;
    }

    public boolean isHighCardFaceOnePresent() {
        return this.isHighCardFaceOnePresent;
    }

    public boolean isHighCardFaceTwoPresent() {
        return this.isHighCardFaceTwoPresent;
    }
}
