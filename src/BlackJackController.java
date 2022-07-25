import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class BlackJackController {

    private BlackJackView theView;
    private BlackJackModel theModel;

    BlackJackController(BlackJackView theView, BlackJackModel theModel){
        this.theView = theView;
        this.theModel = theModel;

        this.theView.addEnterGameListener(new PlayButtonPressed());
        this.theView.addPlaceBetListener(new SelectBetPressed());
        this.theView.addChip1Listener(new Chip1Pressed());
        this.theView.addChip5Listener(new Chip5Pressed());
        this.theView.addChip50Listener(new Chip50Pressed());
        this.theView.addChip500Listener(new Chip500Pressed());

        this.theView.addAcceptBetListener(new BetAccepted());
        this.theView.addCancelBetListener(new BetCancelled());
    }

    //Entering Game initially
    class PlayButtonPressed implements ActionListener {

        public void actionPerformed(ActionEvent e){
            theView.enterGame();
            try {
                theModel.gameStart();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            theView.updateBalance(theModel.getBalance());
        }

    }
    //Enter betting UI
    class SelectBetPressed implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            int balance = theModel.getBalance();
            theView.showChips(balance);
        }
        public void mousePressed(MouseEvent e) {

        }
        @Override
        public void mouseReleased(MouseEvent e) {

        }
        @Override
        public void mouseEntered(MouseEvent e) {

        }
        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    //User selects their bet
    class Chip1Pressed implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            //add 1 to the total bet stored in the model
            theModel.addToBet(1);
            //get the total from the model
            int totalBet = theModel.getBet();
            //place the chips visually on the view
            theView.addChips(totalBet);

            //update the chips shown to user to prevent going over thhe balance
            int remainingBalance = theModel.getBalance() - theModel.getBet();
            theView.updateChipButtons(remainingBalance);
        }
        public void mousePressed(MouseEvent e) {

        }
        @Override
        public void mouseReleased(MouseEvent e) {

        }
        @Override
        public void mouseEntered(MouseEvent e) {

        }
        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    class Chip5Pressed implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            //add 5 to the total bet stored in the model
            theModel.addToBet(5);
            //get the total from the model
            int totalBet = theModel.getBet();
            //place the chips visually on the view
            theView.addChips(totalBet);

            //update the chips shown to user to prevent going over thhe balance
            int remainingBalance = theModel.getBalance() - theModel.getBet();
            theView.updateChipButtons(remainingBalance);
        }
        public void mousePressed(MouseEvent e) {

        }
        @Override
        public void mouseReleased(MouseEvent e) {

        }
        @Override
        public void mouseEntered(MouseEvent e) {

        }
        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    class Chip50Pressed implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            //add 5 to the total bet stored in the model
            theModel.addToBet(50);
            //get the total from the model
            int totalBet = theModel.getBet();
            //place the chips visually on the view
            theView.addChips(totalBet);

            //update the chips shown to user to prevent going over thhe balance
            int remainingBalance = theModel.getBalance() - theModel.getBet();
            theView.updateChipButtons(remainingBalance);
        }
        public void mousePressed(MouseEvent e) {

        }
        @Override
        public void mouseReleased(MouseEvent e) {

        }
        @Override
        public void mouseEntered(MouseEvent e) {

        }
        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    class Chip500Pressed implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            //add 1 to the total bet stored in the model
            theModel.addToBet(500);
            //get the total from the model
            int totalBet = theModel.getBet();
            //place the chips visually on the view
            theView.addChips(totalBet);

            //update the chips shown to user to prevent going over thhe balance
            int remainingBalance = theModel.getBalance() - theModel.getBet();
            theView.updateChipButtons(remainingBalance);
        }
        public void mousePressed(MouseEvent e) {

        }
        @Override
        public void mouseReleased(MouseEvent e) {

        }
        @Override
        public void mouseEntered(MouseEvent e) {

        }
        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    //Bet accepted
    class BetAccepted implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            theView.closeBettingUI();
            theModel.takeFromBalance(theModel.getBet());
            int newBalance = theModel.getBalance();
            theView.updateBalance(newBalance);
            //INITIAL DEAL !!!!!!!!
        }
    }
    //Bet cancelled
    class BetCancelled implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            theView.closeBettingUI();
            theView.cancelBet();
        }

    }
}



