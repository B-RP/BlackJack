import java.io.IOException;
import java.util.Arrays;

public class BlackJackModel {
    //Deck
    public Deck currentDeck;

    //Balance and bet amount
    private int balance = 2500;
    private int bet = 0;

    //Player's hand
    public Card [] playerHand = new Card[12];
    public int playerHandCounter = 0;
    public int playerLastCardPlaced = 0;
    public int playerHandTotal = 0;

    //Dealer's hand
    public Card [] dealerHand = new Card [11];
    public int dealerHandCounter = 0;
    public int dealerLastCardPlaced = 0;
    public int dealerHandTotal = 0;

    //utility functions

    private int calcHandTotal(Card[] hand){
        int total = 0;
        boolean ace = false;
        //sort through the hand, add numbers and find aces

        for (Card card : hand) {
            if (card == null) {
                break;
            }
            if (card.value() == 1) {
                ace = true;
            }
            total += card.value();
        }

        if((ace)&&(total < 12)){
            total += 10;
        }

        return total;
    }

    public int getTempHandTotal(Card[] hand, int handCounter){
        int total = 0;
        boolean ace = false;
        //sort through the hand, add numbers and find aces

        for(int i = 0; i < handCounter; i++){
            if(hand[i] == null){
                break;
            }
            if(hand[i].value() == 1){
                ace = true;
            }
            total += hand[i].value();
        }

        if((ace)&&(total < 12)){
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
        dealerLastCardPlaced = 0;
        playerHandCounter = 0;
        playerLastCardPlaced = 0;

        dealerHandTotal = 0;
        playerHandTotal = 0;

        Arrays.fill(playerHand, null);
        Arrays.fill(dealerHand, null);

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
    public void playerDrawsCard() {
        Card p = currentDeck.draw();
        playerHand[playerHandCounter] = p;
        playerHandCounter++;
        playerHandTotal = calcHandTotal(playerHand);
    }

    public Card[] getPlayerHand(){
        return playerHand;
    }
    public int getPlayerHandTotal(){
        return playerHandTotal;
    }
    public int getPlayerHandCounter(){return playerHandCounter;}
    public int getPlayerLastCardPlaced(){
        playerLastCardPlaced++;
        return playerLastCardPlaced -1;
    }

    //Dealer Decision
    public void dealerDrawsCard() {
        Card d = currentDeck.draw();
        dealerHand[dealerHandCounter] = d;
        dealerHandCounter++;
        dealerHandTotal = calcHandTotal(dealerHand);
    }
    public Card[] getDealerHand(){
        return dealerHand;
    }
    public int getDealerHandTotal(){
        return dealerHandTotal;
    }
    public int getDealerHandCounter(){
        return dealerHandCounter;
    }
    public int getDealerLastCardPlaced() {
        return dealerLastCardPlaced-1;
    }
    public void dealerCardPlaced(){
        dealerLastCardPlaced++;
    }

    public void newGame(){
        //Balance and bet amount
        bet = 0;
        balance = 2500;

        //Player's hand
        playerHand = new Card[12];
        playerHandCounter = 0;
        playerLastCardPlaced = 0;
        playerHandTotal = 0;

        //Dealer's hand
        dealerHand = new Card [11];
        dealerHandCounter = 0;
        dealerLastCardPlaced = 0;
        dealerHandTotal = 0;
    }

}
