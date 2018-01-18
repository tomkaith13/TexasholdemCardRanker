package com.bibin.thomas.takehome;

import com.bibin.thomas.takehome.Player.Player;
import com.bibin.thomas.takehome.deck.CommunityDeck;

import java.io.BufferedReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Main game class which handles stdout and stdin
 */
public class TexasHoldemGame {
    private static CommunityDeck communityDeck;
    private static Set<Player> playerSet = new HashSet<Player>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int lineCount = 0;

        System.out.println("Hello Game");

        while (input.hasNextLine()) {
            if (lineCount == 0) {
                communityDeck = new CommunityDeck(input.nextLine());

                if (communityDeck.isValid() == false) {
                    System.out.println("Invalid Community deck. Please enter valid deck");
                    break;
                }

            } else {
                Player p = new Player(input.nextLine());

                if (p.isValid())
                    playerSet.add(p);
                else {
                    System.out.println("Invalid player card set. Start over");
                    break;
                }
            }
            lineCount++;
        }

    }
}
