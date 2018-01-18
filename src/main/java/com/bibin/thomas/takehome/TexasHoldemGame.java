package com.bibin.thomas.takehome;

import com.bibin.thomas.takehome.Player.Player;
import com.bibin.thomas.takehome.deck.CommunityDeck;
import com.bibin.thomas.takehome.game.GameRanker;
import com.bibin.thomas.takehome.user.validation.PlayerCardValidator;

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
    private static GameRanker gameRanker;


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //linecount can be used to give data about valid vs invalid data
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
                PlayerCardValidator pcValidator = new PlayerCardValidator(input.nextLine());

                //Only valid player data is considered
                if (pcValidator.isValid() == false) {
                    System.out.println("Invalid Player data.");
                    continue;
                }

                Player p = new Player(pcValidator.getPlayerName(), pcValidator.getValidCardSet());
                playerSet.add(p);
            }
            lineCount++;
        } //end while

        gameRanker = new GameRanker(communityDeck, playerSet);
        gameRanker.printPlayerRank();
    }
}
