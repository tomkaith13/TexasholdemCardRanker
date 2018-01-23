 ========== Texas Holdem Card Ranker ==========
 This is a simple Texas Holdem Card Ranker program that ranks the cards based on
  community cards and the player input.

 The first line will contain the five community cards.
 Each line after that will have a player's name followed by their two cards.

 Cards will be identified using two characters.
 First will be the face, followed by the suit.
 The characters used to represent the cards include the digits 2 through 9
 Other Char:

 Character       Represents
 T                       10
 J                       Jack
 Q                       Queen
 K                       King
 A                       Ace
 H                       Hearts
 S                       Spades
 D                       Diamonds
 C                       Clubs



 Build Instructions:
 ===================

 The project requires JDK 1.8 and Maven installed in your respective machine.
 Once those are installed, all you need to do is download the code
 and run:
    mvn package
    This will build the jar package in target folder which the user can run

 To execute the code, simply run:
 java -cp target/texasholdem.arterys-1.0-SNAPSHOT.jar com.bibin.thomas.takehome.TexasHoldemGame

Once you enter the input, just enter Ctrl+d for the program to accept the input.


Assumptions:
============

Since this program takes input from stdin, any invalid data entry will not let you proceed and will
result in immediate termination of the program.

Once the user enters the valid community cards, the program starts accepting and validating user cards.
Once user cards are parsed and validated, the program moves to creating the rankings.

Two Players with the same name AND same hand cannot exist.

During the ranking phase, the program discards any generatable pokerHand which does not have 7 valid cards
(5 from Community Deck and 2 frm each User). If the user and community have duplicate cards, the User is ignored and
not added to the ranking set.



Design:
=======
TexasHoldemGame is the class which has the main method. This is the first method which runs.
After displaying the user greeting, the program accepts user input line-by-line, starting with
the Community Card deck of 5 cards. The CommunityDeck object is created after the data is validated
via CommunityCardValidator class.

Once the CommunityDeck Object is created, the next few lines move to Player Object creation.
After the data is validated using PlayerCardValidator class, the Player information is stored in the Player
Object.

All valid Player objects are added to a PlayerSet which uses the hashCode as Player name and their respective hands.
Once this data is fed into the program and EOF has been sent, the program creates the GameRanker Object.
This Object taking in the CommunityDeck and playerSet to sort each player hand in descending ranking.

Ranking is done by calculating the sum of the cards in the totalHandSet (player cards + community cards)
if the specific rank is found. The rank logic is embedded into the PokerHand class itself.

 ========= Tried Test Samples =============
 Input:
 KS AD 3H 7C TD
 John 9H 7S
 Sam AC KH
 Becky JD QC

 Output:
Player Rankings:
1 Becky STRAIGHT ACE
2 Sam TWO_PAIR KING ACE
3 John ONE_PAIR SEVEN


Input:
TS 6D 6S 7C TC
John TH TD
Joe 6H  6C

Output:
Player Rankings:
1 John FOUR_OF_KIND TEN
2 Joe FOUR_OF_KIND SIX


Input:
TS 6D 6S 7C TC
John TH TD
Joe 6H  6C
Jack AH AD
Jill 2C 2H

Output:
Player Rankings:
1 John FOUR_OF_KIND TEN
2 Joe FOUR_OF_KIND SIX
3 Jack TWO_PAIR SIX ACE
4 Jill TWO_PAIR TWO TEN

Input:
TS 6D 2S TH AC
John 6H 4C
Joe 3C 9D
Jack 4D KH

Output:
Player Rankings:
1 John TWO_PAIR SIX TEN
2 Jack ONE_PAIR TEN
3 Joe ONE_PAIR TEN


Input:
KS AS 3H 7C TS
John QS JS
Joe 5C 9D
Becky JD QC
Sam AC KH

Output:
Player Rankings:
1 John ROYAL_FLUSH
2 Becky STRAIGHT ACE
3 Sam TWO_PAIR KING ACE
4 Joe HIGH_CARD NINE






