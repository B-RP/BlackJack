import java.io.IOException;

public class Game {

    public static void start() throws IOException {
        Deck playingDeck = new Deck();
        playingDeck.shuffle();

        Data.currentDeck = playingDeck;

        newRound();

    }

    //GAME PROCEDURE
    private static void newRound(){
        if (Data.currentDeck.position < 22){
            //todo add dealer message
            Data.currentDeck.shuffle();
        }

        Data.addDealerCard(Data.currentDeck.draw());
        Data.addPlayerCard(Data.currentDeck.draw());

        Data.addDealerCard(Data.currentDeck.draw());
        Data.addPlayerCard(Data.currentDeck.draw());

    }
}
