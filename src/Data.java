public class Data {
    public static Deck currentDeck;


    //PLAYER'S HAND
    public static Card [] playerHand = new Card[11];
    public static int playerHandCounter = 0;


    public static Card [] dealerHand = new Card [11];
    public static int dealerHandCounter = 0;

    public static int playerHandTotal = 0;
    public static int dealerHandTotal = 0;

    //Player fund and bet amount
    public static int balance = 2500;
    public static int bet = 0;

    public static void addPlayerCard(Card p) {
        playerHand[playerHandCounter] = p;
        playerHandCounter++;
    }

    public static void addDealerCard(Card d) {
        dealerHand[dealerHandCounter] = d;
        dealerHandCounter++;
    }

}
