package com.bibin.thomas.takehome.globals;

import com.bibin.thomas.takehome.cards.CardFace;
import com.bibin.thomas.takehome.cards.CardSuite;
import com.bibin.thomas.takehome.game.HandRank;

import java.util.HashMap;
import java.util.Map;

public class GlobalMaps {
    public static Map<Character, CardSuite> suiteMap;
    public static Map<Character, CardFace> faceMap;
    public static Map<CardFace, Integer> faceRankMap;
    public static Map<HandRank, Integer> handRankMap;

    static {
        suiteMap = new HashMap<Character, CardSuite>();
        suiteMap.put('H', CardSuite.HEARTS);
        suiteMap.put('D', CardSuite.DIAMONDS);
        suiteMap.put('S', CardSuite.SPADES);
        suiteMap.put('C', CardSuite.CLUBS);

        faceMap = new HashMap<Character, CardFace>();
        faceMap.put('2', CardFace.TWO);
        faceMap.put('3', CardFace.THREE);
        faceMap.put('4', CardFace.FOUR);
        faceMap.put('5', CardFace.FIVE);
        faceMap.put('6', CardFace.SIX);
        faceMap.put('7', CardFace.SEVEN);
        faceMap.put('8', CardFace.EIGHT);
        faceMap.put('9', CardFace.NINE);
        faceMap.put('T', CardFace.TEN);
        faceMap.put('J', CardFace.JACK);
        faceMap.put('Q', CardFace.QUEEN);
        faceMap.put('K', CardFace.KING);
        faceMap.put('A', CardFace.ACE);

        faceRankMap = new HashMap<CardFace, Integer>();
        faceRankMap.put(CardFace.ACE, 0);
        faceRankMap.put(CardFace.KING, 1);
        faceRankMap.put(CardFace.QUEEN, 2);
        faceRankMap.put(CardFace.JACK, 3);
        faceRankMap.put(CardFace.TEN, 4);
        faceRankMap.put(CardFace.NINE, 5);
        faceRankMap.put(CardFace.EIGHT, 6);
        faceRankMap.put(CardFace.SEVEN, 7);
        faceRankMap.put(CardFace.SIX, 8);
        faceRankMap.put(CardFace.FIVE, 9);
        faceRankMap.put(CardFace.FOUR, 10);
        faceRankMap.put(CardFace.THREE, 11);
        faceRankMap.put(CardFace.TWO, 12);

        handRankMap = new HashMap<HandRank, Integer>();
        handRankMap.put(HandRank.ROYAL_FLUSH, 0);
        handRankMap.put(HandRank.STRAIGHT_FLUSH, 1);
        handRankMap.put(HandRank.FOUR_OF_KIND, 2);
        handRankMap.put(HandRank.FULL_HOUSE, 3);
        handRankMap.put(HandRank.FLUSH, 4);
        handRankMap.put(HandRank.STRAIGHT, 5);
        handRankMap.put(HandRank.THREE_OF_KIND, 6);
        handRankMap.put(HandRank.TWO_PAIR, 7);
        handRankMap.put(HandRank.ONE_PAIR, 8);
        handRankMap.put(HandRank.HIGH_CARD, 9);



    }


}
