import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class BlackJackMain {

    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        BlackJackView view = new BlackJackView("BlackJack");
        BlackJackModel model = new BlackJackModel();
        BlackJackController controller = new BlackJackController(view,model);

        view.setVisible(true);
    }
}
