import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.*;

public class BlackJackView extends JFrame{
    private final JPanel container;

    private final JPanel welcomePanel;
    private JPanel tablePanel;

    private JPanel playerCardPanel;
    private FlowLayout handLayout;
    private JPanel betPanel;

    private JPanel betBox;
    private JLabel betAmount;

    private JLabel fundLabel;

    private final Container glassPane;

    private JPanel visualBetTop;
    private JPanel visualBetBot;

    private JPanel placeBetButtonPanel;
    private JPanel visualBetPanel;

    private DecisionButton playButton;

    private ChipButton chipButton1;
    private ChipButton chipButton5;
    private ChipButton chipButton50;
    private ChipButton chipButton500;

    private DecisionButton acceptButton;
    private DecisionButton cancelButton;

    private DecisionButton betButton;

    private DecisionButton splitButton;
    private DecisionButton standButton;
    private DecisionButton hitButton;

    private JLabel dealerDialogue;

    private JPanel dealerCardPanel;

    private TitledBorder dealerCardsBorder;
    private TitledBorder playerCardsBorder;

    private FlowLayout dealerHandLayout;


    public BlackJackView(String title) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Main Panels-- container holds tablePanel and welcomePanel
        container = new JPanel(new CardLayout());
        tablePanel = new TablePanel();
        welcomePanel = new WelcomePanel();


        container.add(welcomePanel, "card1");
        container.add(tablePanel, "card2");

        CardLayout cardLayout = (CardLayout) container.getLayout();
        cardLayout.show(container, "Card 2");

        this.setGlassPane(new JComponent(){
            @Override
            protected void paintComponent(Graphics g){
                g.setColor(new Color (0,0,0,0));
                g.fillRect(0,0, getWidth(), getHeight());
            }

        });
        glassPane = (Container) this.getGlassPane();


        this.setContentPane(container);
        this.setResizable(false);
        this.pack();
        music();
        createWelcomeUIComponents();
        createUIComponents();
        betUIComponents();
    }

    private void createWelcomeUIComponents(){
        GridLayout grid = new GridLayout(3,1);
        grid.setHgap(10);
        grid.setVgap(10);

        welcomePanel.setLayout(grid);

        //Top Panel
        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        welcomePanel.add(topPanel);

        //Middle Panel
        JPanel midPanel = new JPanel();
        midPanel.setOpaque(false);
        FlowLayout flow1 = new FlowLayout(FlowLayout.CENTER);
        flow1.setHgap(5);
        flow1.setVgap(175);
        midPanel.setLayout(flow1);

        welcomePanel.add(midPanel);

        playButton = new DecisionButton("Play");
        midPanel.add(playButton);

        //Bottom Panel
        JPanel botPanel = new JPanel();
        botPanel.setOpaque(false);
        welcomePanel.add(botPanel);

    }

    private void createUIComponents() throws IOException {
        //Grid layout for container panel
        GridLayout grid = new GridLayout(5,1);
        grid.setHgap(10);
        grid.setVgap(10);
        tablePanel.setLayout(grid);

        /*----------------------------------------
        ------PANEL 1 DEALERS HAND----------------
        ------------------------------------------*/
        JPanel panel1 = new JPanel();
        FlowLayout flow1 = new FlowLayout(FlowLayout.CENTER);
        flow1.setHgap(5);
        flow1.setVgap(5);
        panel1.setLayout(flow1);
        panel1.setOpaque(false);
        tablePanel.add(panel1);

        //Dealer cards panel
        dealerCardPanel = new JPanel();
        dealerCardPanel.setPreferredSize(new Dimension(210, 150));
        dealerCardPanel.setBackground(new Color(28, 25, 26));
        dealerCardsBorder = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(),
                " ", TitledBorder.CENTER,TitledBorder.BOTTOM, new Font("Arial", Font.PLAIN,15),
                new Color(244,179,36));
        dealerCardPanel.setBorder(dealerCardsBorder);

        dealerHandLayout = new FlowLayout(FlowLayout.CENTER);
        dealerCardPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        dealerHandLayout.setHgap(5);
        dealerHandLayout.setVgap(5);
        dealerCardPanel.setLayout(dealerHandLayout);


        panel1.add(dealerCardPanel);

        /*------------------------------------------------
        ------PANEL 2 DECK AND DEALER DIALOGUE------------
         ------------------------------------------------*/
        FlowLayout panel2Layout = new FlowLayout(FlowLayout.CENTER);
        JPanel panel2 = new JPanel(panel2Layout);
        panel2Layout.setHgap(300);
        panel2.setOpaque(false);

        //Filler Panel
        JPanel fillerDealerPanel = new JPanel();
        fillerDealerPanel.setPreferredSize(new Dimension(100,130 ));
        fillerDealerPanel.setOpaque(false);

        //Dealer Dialogue
        JPanel dealerDioPanel = new JPanel();
        dealerDioPanel.setPreferredSize(new Dimension(300,130));
        dealerDioPanel.setOpaque(false);
        dealerDialogue = new JLabel();
        dealerDialogue.setForeground(new Color(244,179,36));
        dealerDialogue.setFont(new Font("Arial", Font.BOLD,20));

        dealerDioPanel.add(dealerDialogue);

        //Deck Panel
        JPanel deckPanel = new JPanel();
        deckPanel.setPreferredSize(new Dimension(100,130));
        deckPanel.setOpaque(false);
        BufferedImage deckPic = ImageIO.read(new File("DeckVisual.png"));
        JLabel deck = new JLabel(new ImageIcon(deckPic));

        deckPanel.add(deck);

        panel2.add(fillerDealerPanel);
        panel2.add(dealerDioPanel);
        panel2.add(deckPanel);


        tablePanel.add(panel2);

        /*---------------------------------------------------
         *------Panel 3 - EMPTY ----------------------------*
         ---------------------------------------------------*/
        JPanel panel3 = new JPanel();
        panel3.setPreferredSize(new Dimension(1440,100));
        panel3.setOpaque(false);
        tablePanel.add(panel3);

        /*---------------------------------------------------
         *------Panel 4 - PLAYER HAND AND BET---------------*
         ---------------------------------------------------*/
        JPanel panel4 = new JPanel();
        FlowLayout flow4 = new FlowLayout(FlowLayout.CENTER);
        panel4.setPreferredSize(new Dimension(1440,200));
        flow4.setHgap(10);
        flow4.setVgap(0);
        panel4.setLayout(flow4);
        panel4.setOpaque(false);

        //Player's cards panel
        playerCardPanel = new JPanel();
        playerCardPanel.setPreferredSize(new Dimension(210, 150));
        playerCardPanel.setBackground(new Color(28, 25, 26));
        playerCardsBorder = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(),
                " ", TitledBorder.CENTER,TitledBorder.BOTTOM, new Font("Arial", Font.PLAIN,15),
                new Color(244,179,36));
        playerCardPanel.setBorder(playerCardsBorder);

        handLayout = new FlowLayout(FlowLayout.CENTER);
        playerCardPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        handLayout.setHgap(5);
        handLayout.setVgap(5);
        playerCardPanel.setLayout(handLayout);

        //Player's Bet Panel
        betPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(betPanel, BoxLayout.PAGE_AXIS);
        betPanel.setLayout(boxLayout);
        betPanel.setPreferredSize(new Dimension(90,130));
        betPanel.setOpaque(false);
        betPanel.add(Box.createHorizontalGlue());

        betBox = new JPanel();
        CardLayout betBoxLayout = new CardLayout();
        betBox.setLayout(betBoxLayout);
        betBox.setOpaque(false);
        Border yellowBorder = BorderFactory.createLineBorder(new Color(244,179,36));
        betBox.setBorder(yellowBorder);
        betPanel.add(betBox);

        visualBetPanel = new JPanel(new GridLayout(2,1));
        visualBetPanel.setOpaque(false);
        FlowLayout visualBetLayout = new FlowLayout();
        visualBetLayout.setHgap(-30);
        visualBetTop = new JPanel(visualBetLayout);
        visualBetTop.setOpaque(false);
        visualBetBot = new JPanel(visualBetLayout);
        visualBetBot.setOpaque(false);

        visualBetPanel.add(visualBetTop);
        visualBetPanel.add(visualBetBot);

        betBox.add(visualBetPanel, "card1");

        betAmount = new JLabel("0");
        betAmount.setFont(new Font("Arial", Font.PLAIN,15));
        betAmount.setAlignmentX(Component.CENTER_ALIGNMENT);
        betAmount.setForeground(new Color(244,179,36));
        betPanel.add(betAmount);

        //filler panel
        JPanel fillerPanel = new JPanel();
        fillerPanel.setPreferredSize(new Dimension(84,130));
        fillerPanel.setOpaque(false);

        panel4.add(betPanel);
        panel4.add(playerCardPanel);
        panel4.add(fillerPanel);

        tablePanel.add(panel4);

        //------Panel 5 - BUTTONS---------------
        JPanel panel5 = new JPanel();
        FlowLayout flow5 = new FlowLayout(FlowLayout.CENTER);
        flow5.setHgap(20);
        flow5.setVgap(50);
        panel5.setLayout(flow5);

        panel5.setOpaque(false);

        //FUNDS Panel
        FlowLayout fundPanelLayout = new FlowLayout(FlowLayout.LEFT);
        JPanel fundLabelPanel = new JPanel(fundPanelLayout);

        fundPanelLayout.setVgap(40);
        fundLabelPanel.setPreferredSize(new Dimension((getWidth()/3 - 30), getHeight()));
        fundLabelPanel.setOpaque(false);
        fundLabel = new JLabel();
        fundLabel.setForeground(new Color(244,179,36));
        fundLabel.setFont(new Font("Arial", Font.BOLD,30));
        fundLabelPanel.add(fundLabel, BorderLayout.WEST);


        panel5.add(fundLabelPanel);

        //BUTTONS PANEL
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension((getWidth()/3 - 30), getHeight()));
        buttonPanel.setOpaque(false);
        splitButton = new DecisionButton("SPLIT");
        splitButton.setVisible(false);
        hitButton = new DecisionButton("HIT");
        hitButton.setVisible(false);
        standButton = new DecisionButton("STAND");
        standButton.setVisible(false);
        betButton = new DecisionButton("PLACE BET");
        betButton.setPreferredSize(new Dimension(160,70));
        betButton.setVisible(true);

        buttonPanel.add(splitButton);
        buttonPanel.add(hitButton);
        buttonPanel.add(standButton);
        buttonPanel.add(betButton);

        panel5.add(buttonPanel);

        //NAVIGATION PANEL
        JPanel navigationPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        navigationPanel.setPreferredSize(new Dimension((getWidth()/3 - 30), getHeight()));
        navigationPanel.setOpaque(false);

        Icon homeButtonIcon = new ImageIcon("NavigationButtons/home.png");
        JButton homeButton = new JButton(homeButtonIcon);

        homeButton.setFocusPainted(false);
        homeButton.setBorderPainted(false);
        homeButton.setContentAreaFilled(false);

        navigationPanel.add(homeButton);

        Icon soundButtonIcon = new ImageIcon("NavigationButtons/sound.png");
        JButton soundButton = new JButton(soundButtonIcon);

        soundButton.setFocusPainted(false);
        soundButton.setBorderPainted(false);
        soundButton.setContentAreaFilled(false);

        navigationPanel.add(soundButton);

        panel5.add(navigationPanel);


        tablePanel.add(panel5);

        //Navigation
        homeButton.addActionListener(e -> {
            container.remove(tablePanel);
            container.add(welcomePanel);
            validate();
            repaint();
        });
    }

    //GLASS PANEL
    private void betUIComponents(){
        glassPane.setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel glassContentPanel = new JPanel();
        BoxLayout glassBetLayout = new BoxLayout(glassContentPanel, BoxLayout.Y_AXIS);
        glassContentPanel.setLayout(glassBetLayout);
        glassContentPanel.setOpaque(false);

        JPanel chipButtonPanel = new JPanel();
        BoxLayout chipButtonLayout = new BoxLayout(chipButtonPanel, BoxLayout.Y_AXIS);
        chipButtonPanel.setLayout(chipButtonLayout);
        chipButtonPanel.setOpaque(false);

        Icon chipButtonIcon1 = (new ImageIcon("ChipButtons/1.png"));
        chipButton1 = new ChipButton(chipButtonIcon1);
        chipButtonPanel.add(chipButton1);
        chipButton1.setVisible(false);

        Icon chipButtonIcon5 = new ImageIcon("ChipButtons/5.png");
        chipButton5 = new ChipButton(chipButtonIcon5);
        chipButtonPanel.add(chipButton5);
        chipButton5.setVisible(false);

        Icon chipButtonIcon50 = new ImageIcon("ChipButtons/50.png");
        chipButton50 = new ChipButton(chipButtonIcon50);
        chipButtonPanel.add(chipButton50);
        chipButton50.setVisible(false);

        Icon chipButtonIcon500 = new ImageIcon("ChipButtons/500.png");
        chipButton500 = new ChipButton(chipButtonIcon500);
        chipButtonPanel.add(chipButton500);
        chipButton500.setVisible(false);

        JScrollPane scrollPane = new JScrollPane(chipButtonPanel);
        scrollPane.createVerticalScrollBar();
        scrollPane.getVerticalScrollBar().setUI(new CustomScrollBar());
        scrollPane.getVerticalScrollBar().setOpaque(false);
        scrollPane.setPreferredSize(new Dimension(200,430));
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());


        glassContentPanel.add(scrollPane);

        JPanel betButtons = new JPanel();
        betButtons.setOpaque(false);
        cancelButton = new DecisionButton("Cancel");
        cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        acceptButton = new DecisionButton("Accept");
        acceptButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        glassContentPanel.add(betButtons);
        glassContentPanel.add(cancelButton);
        glassContentPanel.add(Box.createRigidArea(new Dimension(0,10)));
        glassContentPanel.add(acceptButton);

        glassPane.add(glassContentPanel);
    }


    //MUSIC
    public static void music () throws LineUnavailableException, UnsupportedAudioFileException, IOException {

        File url = new File("Music/thejazzpiano.wav");
        Clip clip = AudioSystem.getClip();
        AudioInputStream ais = AudioSystem.getAudioInputStream(url);
        clip.open(ais);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }


    //RESPONSIVE VIEW FUNCTIONS

    public void addEnterGameListener(ActionListener listenForPlayButton){
        playButton.addActionListener(listenForPlayButton);
    }

    public void enterGame(){
        //change player's view
        container.remove(welcomePanel);
        container.add(tablePanel);
    }

    public void updateDealerDialogue(String text){
        dealerDialogue.setText(text);
    }

    public void addPlaceBetListener(MouseListener listenForPlaceBetButton){
        betButton.addMouseListener(listenForPlaceBetButton);
    }
    public void showChips(int balance)
    {
        glassPane.setVisible(true);
        betBox.add(visualBetPanel);
        betButton.setVisible(false);

        if(balance >= 500){
            chipButton500.setVisible(true);
        }
        if(balance >= 50){
            chipButton50.setVisible(true);
        }
        if(balance >= 5){
            chipButton5.setVisible(true);
        }
        if(balance >= 1){
            chipButton1.setVisible(true);
        }
    }

    public void addChip1Listener(MouseListener listenForChipButton){
        chipButton1.addMouseListener(listenForChipButton);
    }
    public void addChip5Listener(MouseListener listenForChipButton){
        chipButton5.addMouseListener(listenForChipButton);
    }
    public void addChip50Listener(MouseListener listenForChipButton){
        chipButton50.addMouseListener(listenForChipButton);
    }
    public void addChip500Listener(MouseListener listenForChipButton){
        chipButton500.addMouseListener(listenForChipButton);
    }

    public void addChips(int amount){
        visualBetTop.removeAll();
        visualBetTop.repaint();
        visualBetBot.removeAll();
        visualBetBot.repaint();
        System.out.println("adding chips");
        //optimize chip usage
        int localBet = amount;
        int [] chipsArr = new int[8];
        int chipsArrCounter = 0;

        while(localBet > 0){
            if(chipsArrCounter == 8){
                break;
            }
            if(localBet >= 500){
                chipsArr[chipsArrCounter] = 500;
                chipsArrCounter++;
                localBet -= 500;
            }
            else if(localBet >= 50){
                chipsArr[chipsArrCounter] = 50;
                chipsArrCounter++;
                localBet -= 50;
            }
            else if(localBet >= 5){
                chipsArr[chipsArrCounter] = 5;
                chipsArrCounter++;
                localBet -= 5;
            }
            else{
                chipsArr[chipsArrCounter] = 1;
                chipsArrCounter++;
                localBet -= 1;
            }
        }
        //place chips on table
        //tops section
        for(int i = 0; i < chipsArr.length/2; i++){
            if(chipsArr[i] == 0){
                break;
            }
            System.out.println(chipsArr[i]);
            JLabel chip = new JLabel(new ImageIcon("Chips/"+ chipsArr[i]+".png"));
            visualBetTop.add(chip);
            visualBetBot.revalidate();
            visualBetTop.repaint();
        }
        //bottom section
        for(int i = 4; i < chipsArr.length; i++){
            if(chipsArr[i] == 0){
                break;
            }
            else if(i == 7){
                JLabel plus = new JLabel(new ImageIcon("Chips/+.png"));
                visualBetBot.add(plus);
            }
            else {
                JLabel chip = new JLabel(new ImageIcon("Chips/" + chipsArr[i] + ".png"));
                visualBetBot.add(chip);
            }
        }
        betAmount.setText(String.valueOf(amount));
    }

    public void updateChipButtons(int balance){
        chipButton500.setVisible(balance >= 500);
        chipButton50.setVisible(balance >= 50);
        chipButton5.setVisible(balance >= 5);
        chipButton1.setVisible(balance >= 1);
    }

    public void addAcceptBetListener(ActionListener listenForAcceptButton){
        acceptButton.addActionListener(listenForAcceptButton);
    }

    public void addCancelBetListener(ActionListener listenForCancelButton){
        cancelButton.addActionListener(listenForCancelButton);
    }

    public void closeBettingUI(){
        glassPane.setVisible(false);
    }

    public void cancelBet(){
        betBox.remove(visualBetPanel);
        visualBetBot.removeAll();
        visualBetBot.revalidate();
        visualBetTop.removeAll();
        visualBetTop.revalidate();
        betAmount.setText(String.valueOf(0));
    }

    public void updateBalance(int newBalance){
        fundLabel.setText(String.valueOf(newBalance));
        fundLabel.repaint();
    }

    public void addPlayerCard(Card [] hand, int numOfCards){
        if(numOfCards >= 3){
            handLayout.setHgap(-65);
        }
        if(numOfCards >= 7){
            System.out.println("the index is 7");
            handLayout.setHgap(-73);
        }
        playerCardPanel.removeAll();

        for(int i = numOfCards-1; i >= 0; i--){
            JLabel cardG = new JLabel(new ImageIcon(hand[i].getImg().getScaledInstance(84,117, Image.SCALE_SMOOTH)));
            playerCardPanel.add(cardG);
        }
        revalidate();
        repaint();
    }

    public void addDealerCard(Card [] hand, int numOfCards){
        if(numOfCards >= 2){
            dealerCardPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        if(numOfCards >= 3){
            handLayout.setHgap(-65);
        }
        if(numOfCards >= 7){
            handLayout.setHgap(-73);
        }
        dealerCardPanel.removeAll();

        for(int i = numOfCards-1; i >= 0; i--){
            JLabel cardG = new JLabel(new ImageIcon(hand[i].getImg().getScaledInstance(84,117, Image.SCALE_SMOOTH)));
            dealerCardPanel.add(cardG);
        }
        revalidate();
        repaint();
    }
    public void addDealerBlankCard() throws IOException {
        Image blankCard = ImageIO.read(new File("Cards/b0.png"));
        JLabel cardG = new JLabel(new ImageIcon(blankCard.getScaledInstance(84,117, Image.SCALE_SMOOTH)));
        dealerCardPanel.add(cardG);
        revalidate();
        repaint();
    }

    public void updateDealerTotal(int total){
        dealerCardsBorder.setTitle(String.valueOf(total));
    }

    public void updatePlayerTotal(int total){
        playerCardsBorder.setTitle(String.valueOf(total));
    }

    public void showDecisionButtons(){
        hitButton.setVisible(true);
        standButton.setVisible(true);
    }
    public void showSplitButton(){
        splitButton.setVisible(true);
    }
    public void hideDecisionButtons(){
        hitButton.setVisible(false);
        standButton.setVisible(false);
        splitButton.setVisible(false);
    }


    public void addHitButtonListener(ActionListener listenForHit){
        hitButton.addActionListener(listenForHit);
    }

    public void newRound(){
        //remove cards
        dealerCardPanel.removeAll();
        dealerCardPanel.revalidate();
        dealerCardPanel.repaint();
        playerCardPanel.removeAll();
        playerCardPanel.revalidate();
        playerCardPanel.repaint();
        dealerCardsBorder.setTitle(" ");
        playerCardsBorder.setTitle(" ");
        dealerCardPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        handLayout.setHgap(5);

        //remove bet
        betBox.remove(visualBetPanel);
        visualBetBot.removeAll();
        visualBetBot.revalidate();
        visualBetBot.repaint();
        visualBetTop.removeAll();
        visualBetTop.revalidate();
        visualBetTop.repaint();
        betAmount.setText(String.valueOf(0));

        //
        betButton.setVisible(true);



    }












}

