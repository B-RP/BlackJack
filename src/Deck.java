import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {

    private Card[] deck;
    private int position = 0;

    //construct the deck
    Deck() throws IOException {

        deck = new Card[52];
        fillDeck();
    }

    //fill the deck with cards
    private void fillDeck() throws IOException {

        //fill clubs
        for(int i = 0; i < (deck.length/4); i++) {
            String imgName = "Cards/k" + (i+1) + ".png";
            deck[i] = new Card("black", "clubs", String.valueOf(i+1),
                    ImageIO.read(new FileInputStream(imgName)));
        }
        //fill spades
        for(int i = 0; i < (deck.length/4); i++) {
            String imgName = "Cards/p" + (i+1) + ".png";
            deck[i+13] = new Card("black", "spades", String.valueOf(i+1),
                    ImageIO.read(new FileInputStream(imgName)));
        }
        //fill hearts
        for(int i = 0; i < (deck.length/4); i++) {
            String imgName = "Cards/s" + (i+1) + ".png";
            deck[i+26] = new Card("red", "hearts", String.valueOf(i+1),
                    ImageIO.read(new FileInputStream(imgName)));
        }
        //fill diamonds
        for(int i = 0; i < (deck.length/4); i++) {
            String imgName = "Cards/l" + (i+1) + ".png";
            deck[i+39] = new Card("red", "diamonds", String.valueOf(i+1),
                    ImageIO.read(new FileInputStream(imgName)));
        }
    }

    //shuffle the deck,
    public void shuffle() {
        List<Card> list = Arrays.asList(deck);
        Collections.shuffle(list);
        position = 0;
    }

    //draw the next card on the deck
    public Card draw() {
        if(position == 52 ) {
            shuffle();
        }
        int p = position;
        position++;
        return deck[p];
    }

    //Print every card on the deck - for testing
    public String toString() {
        String retStr = "";

        for (int i = 0; i < deck.length; i++) {
            retStr += i+1 + "\t";
            retStr += deck[i];
            retStr += "\n";
        }

        return retStr;
    }


}
