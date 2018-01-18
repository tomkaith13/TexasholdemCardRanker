package com.bibin.thomas.takehome.globals;

import com.bibin.thomas.takehome.cards.CardFace;
import com.bibin.thomas.takehome.cards.CardSuite;

import java.util.HashMap;
import java.util.Map;

public class GlobalMaps {
    public static Map<Character, CardSuite> suiteMap;
    public static Map<Character, CardFace> faceMap;

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

    }


}
