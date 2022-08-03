import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Callable;

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

        this.theView.addHitButtonListener(new PlayerHit());
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
            theView.updateDealerDialogue("Welcome! Please place your bet");
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

            theView.updateDealerDialogue("Dealing...");

            ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
            executorService.schedule(dealDealerCard, 0, TimeUnit.MILLISECONDS);
            executorService.schedule(dealPlayerCard, 500, TimeUnit.MILLISECONDS);
            executorService.schedule(dealBlankDealerCard, 1000, TimeUnit.MILLISECONDS);
            executorService.schedule(dealPlayerCard, 1500, TimeUnit.MILLISECONDS);
            executorService.schedule(initialDealEnd, 2000,TimeUnit.MILLISECONDS);

        }
        Runnable initialDealEnd = new Runnable() {
            @Override
            public void run() {
                theView.showDecisionButtons();
                theView.updateDealerDialogue(" ");
            }
        };
        Runnable dealBlankDealerCard = new Runnable() {
            @Override
            public void run() {
                theModel.dealerDrawsCard();
                try {
                    theView.addDealerBlankCard();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        };
        Runnable dealDealerCard = new Runnable() {
            @Override
            public void run() {
                theModel.dealerDrawsCard();
                Card [] dealerHand = theModel.getDealerHand();
                theView.addDealerCard(dealerHand, theModel.getDealerHandCounter());
                int handTotal = theModel.getDealerHandTotal();
                theView.updateDealerTotal(handTotal);
            }
        };
        Runnable dealPlayerCard = new Runnable() {
            @Override
            public void run() {
                theModel.playerDrawsCard();
                Card [] playerHand = theModel.getPlayerHand();
                theView.addPlayerCard(playerHand, theModel.getPlayerHandCounter());
                int handTotal = theModel.getPlayerHandTotal();
                theView.updatePlayerTotal(handTotal);
            }
        };
    }
    //Bet cancelled
    class BetCancelled implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            theView.closeBettingUI();
            theView.cancelBet();
            theModel.cancelBet();
        }

    }

    class PlayerHit implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            theModel.playerDrawsCard();
            theView.addPlayerCard(theModel.getPlayerHand(), theModel.getPlayerHandCounter());
            theView.updatePlayerTotal(theModel.getPlayerHandTotal());

            if(theModel.getPlayerHandTotal() > 21){
                theView.updateDealerDialogue("Bust, house wins");
                theView.hideDecisionButtons();

                ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
                executorService.schedule(showHiddenCard,1000,TimeUnit.MILLISECONDS);
                executorService.schedule(newRound,2000,TimeUnit.MILLISECONDS);

            }
        }
        Runnable showHiddenCard = new Runnable() {
            @Override
            public void run() {
                theView.addDealerCard(theModel.getDealerHand(), theModel.getDealerHandCounter());
                theView.updateDealerTotal(theModel.getDealerHandTotal());
            }
        };
        Runnable newRound = new Runnable() {
            @Override
            public void run() {
                theView.updateDealerDialogue("Please place your bet");
                theModel.newRound();
                theView.newRound();

            }
        };

    }
}



