package com.bibin.thomas.takehome;

import com.bibin.thomas.takehome.Player.Player;
import com.bibin.thomas.takehome.deck.CommunityDeck;
import com.bibin.thomas.takehome.game.GameRanker;
import com.bibin.thomas.takehome.user.validation.PlayerCardValidator;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Main game class which handles stdout and stdin
 */
public class TexasHoldemGame {
    //user to store the community cards
    private static CommunityDeck communityDeck;

    //used to store the player info and cards
    private static Set<Player> playerSet = new HashSet<>();

    // the actual game ranker which takes communityDec and playerSet info
    // and ranks the cards
    private static GameRanker gameRanker;


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //linecount used to differentiate community deck vs player hand
        int lineCount = 0;

        displayStartMessage();
        while (input.hasNextLine()) {
            if (lineCount == 0) {
                communityDeck = new CommunityDeck(input.nextLine());

                if (!communityDeck.isValid()) {
                    System.out.println("Invalid Community deck. Please enter valid deck");
                    return;
                }
            } else {
                PlayerCardValidator pcValidator = new PlayerCardValidator(input.nextLine());

                //Only valid player data is considered
                if (!pcValidator.isValid()) {
                    System.out.println("Invalid Player data.");
                    return;
                }
                Player p = new Player(pcValidator.getPlayerName(), pcValidator.getValidCardSet());
                playerSet.add(p);
            }
            lineCount++;
        } //end while
        gameRanker = new GameRanker(communityDeck, playerSet);
        gameRanker.displayPlayerHandRankings();
    }

    private static void displayStartMessage() {
        String greeting = "Welcome to Poker hand ranker!!\n"
                + "The first line will contain the five community cards.\n"
                + "Each line after that will have a player's name followed by their two cards.\n\n"
                + "Cards will be identified using two characters.\n"
                + "First will be the face, followed by the suit.\n"
                + "The characters used to represent the cards include the digits 2 through 9 \n"
                + "Other Char:\n"
                + "\n" +
                "Character\tRepresents\n" +
                "T\t\t\t10\n" +
                "J\t\t\tJack\n" +
                "Q\t\t\tQueen\n" +
                "K\t\t\tKing\n" +
                "A\t\t\tAce\n" +
                "H\t\t\tHearts\n" +
                "S\t\t\tSpades\n" +
                "D\t\t\tDiamonds\n" +
                "C\t\t\tClubs\n\n" +
                "Enter your data:";
        System.out.println(greeting);

    }
}
