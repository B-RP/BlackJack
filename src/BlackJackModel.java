import java.io.IOException;

public class BlackJackModel {
    //Deck
    public static Deck currentDeck;

    //Balance and bet amount
    private static int balance = 2500;
    private static int bet = 0;

    //Player's hand
    public static Card [] playerHand = new Card[11];
    public static int playerHandCounter = 0;
    public static int playerHandTotal = 0;

    //Dealer's hand
    public static Card [] dealerHand = new Card [11];
    public static int dealerHandCounter = 0;
    public static int dealerHandTotal = 0;

    //utility functions
    private static int calcHandTotal(Card[] hand){
        int total = 0;
        boolean ace = false;
        //sort through the hand, add numbers and find aces
        for(int i = 0; i < hand.length; i++){
            if(hand[i].value() == 1){
                ace = true;
            }
            if(hand[i].value() == 0){
                break;
            }
            total += hand[i].value();
        }

        if((ace == true)&&(total < 12)){
            total += 10;
        }

        return total;
    }




    //Game Stages
    public void gameStart() throws IOException {
        currentDeck = new Deck();
        currentDeck.shuffle();
    }
    public void newRound(){
        bet = 0;
        dealerHandCounter = 0;
        playerHandCounter = 0;

    }
    public int getBalance(){
        return balance;
    }

    public void addToBalance(int amount){
        balance += amount;
    }

    public void takeFromBalance(int amount){
        balance -= amount;
    }

    public void addToBet(int amount){
        bet += amount;
    }

    public void cancelBet(){
        bet = 0;
    }

    public int getBet(){
        return bet;
    }

    //Player Decision
    public static Card playerDrawsCard() {
        Card p = currentDeck.draw();
        playerHand[playerHandCounter] = p;
        playerHandCounter++;
        playerHandTotal = calcHandTotal(playerHand);
        return p;
    }

    public int getPlayerHandTotal(){
        return playerHandTotal;
    }


    //Dealer Decision
    public static void addDealerCard(Card d) {
        dealerHand[dealerHandCounter] = d;
        dealerHandCounter++;
    }

}
