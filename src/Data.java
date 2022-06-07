public class Data {
    public static Deck currentDeck;


    //Data on player and dealer hands
    public static Card [] playerHand = new Card[11];
    public static int playerHandCounter = 0;

    public static Card [] dealerHand = new Card [11];
    public static int dealerHandCounter = 0;

    public static int playerTotal = 0;
    public static int dealerTotal = 0;

    //Player fund and bet amount
    public static int balance;
    public static int bet = 0;

    public static void addPlayerCard(Card p) {
        playerHand[playerHandCounter] = p;
        playerHandCounter++;
    }

    public static void addDealerCard(Card d) {
        dealerHand[dealerHandCounter] = d;
        dealerHandCounter++;
    }

    //TESTING FUNCTIONS

    public static void showHands(){
        System.out.println("Dealers hand: ");
        for(int i = 0; i < playerHand.length; i++){
            if(!(playerHand[i] == null)){
                System.out.println(playerHand[i]);
            }
        }
    }
}
