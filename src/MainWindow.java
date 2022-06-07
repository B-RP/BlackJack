import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;

public class MainWindow extends JFrame{

    private JPanel container;

    private JPanel welcomePanel;
    private JPanel tablePanel;


    private JPanel playerCardPanel;
    private FlowLayout handLayout;
    private JPanel betPanel;

    private JPanel betBox;
    private JLabel betAmount;

    private JLabel fundLabel;

    private Container glassPane;

    private JTextArea placeBetButton;


    public MainWindow(String title) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
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

    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        JFrame frame = new MainWindow("BlackJack");
        frame.setVisible(true);
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

        DecisionButton playButton = new DecisionButton("Play");
        midPanel.add(playButton);

        //Bottom Panel
        JPanel botPanel = new JPanel();
        botPanel.setOpaque(false);
        welcomePanel.add(botPanel);

        //BUTTON EVENT LISTENER
        playButton.addActionListener(e -> {
            //change player's view
            container.remove(welcomePanel);
            container.add(tablePanel);

            try {
            //Declare a new Deck
                Data.currentDeck = new Deck();
                Data.currentDeck.shuffle();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void createUIComponents() throws IOException {
        //Grid Layout
        GridLayout grid = new GridLayout(5,1);
        grid.setHgap(10);
        grid.setVgap(10);

        tablePanel.setLayout(grid);

        //------PANEL 1 DEALERS HAND----------------
        JPanel panel1 = new JPanel();
        FlowLayout flow1 = new FlowLayout(FlowLayout.CENTER);
        flow1.setHgap(5);
        flow1.setVgap(20);
        panel1.setLayout(flow1);
        panel1.setOpaque(false);
        tablePanel.add(panel1);

        BufferedImage cardPic = ImageIO.read(new File("Cards/k1.png"));
        JLabel card = new JLabel(new ImageIcon(cardPic.getScaledInstance(84,117, Image.SCALE_SMOOTH)));
        JLabel card2 = new JLabel(new ImageIcon(cardPic.getScaledInstance(84,117, Image.SCALE_SMOOTH)));
        panel1.add(card);
        panel1.add(card2);

        //------PANEL 2 DECK AND DEALER DIALOGUE------------
        FlowLayout panel2Layout = new FlowLayout(FlowLayout.CENTER);
        JPanel panel2 = new JPanel(panel2Layout);
        panel2Layout.setHgap(300);
        panel2.setOpaque(false);

            //Filler Panel
            JPanel fillerDealerPanel = new JPanel();
            fillerDealerPanel.setPreferredSize(new Dimension(100,200 ));
            fillerDealerPanel.setOpaque(false);

            //Dealer Dialogue
            JPanel dealerDioPanel = new JPanel();
            dealerDioPanel.setPreferredSize(new Dimension(300,200));
            dealerDioPanel.setOpaque(false);
                JLabel dealerDialogue = new JLabel("Please place your bet");
                dealerDialogue.setForeground(new Color(244,179,36));
                dealerDialogue.setFont(new Font("Arial", Font.BOLD,20));

                dealerDioPanel.add(dealerDialogue);

            //Deck Panel
            JPanel deckPanel = new JPanel();
            deckPanel.setPreferredSize(new Dimension(100,200));
            deckPanel.setOpaque(false);
                BufferedImage deckPic = ImageIO.read(new File("DeckVisual.png"));
                JLabel deck = new JLabel(new ImageIcon(deckPic));

                deckPanel.add(deck);

        panel2.add(fillerDealerPanel);
        panel2.add(dealerDioPanel);
        panel2.add(deckPanel);


        tablePanel.add(panel2);

        ////------Panel 3 - EMPTY---------------
        JPanel panel3 = new JPanel();
        panel3.setPreferredSize(new Dimension(1440,150));
        panel3.setOpaque(false);
        tablePanel.add(panel3);

        ////------Panel 4 - PLAYER HAND---------------
        JPanel panel4 = new JPanel();
        FlowLayout flow4 = new FlowLayout(FlowLayout.CENTER);
        flow4.setHgap(10);
        flow4.setVgap(10);
        panel4.setLayout(flow4);
        panel4.setOpaque(false);

            //Player's cards
            playerCardPanel = new JPanel();
            playerCardPanel.setPreferredSize(new Dimension(210, 137));
            playerCardPanel.setBackground(new Color(28, 25, 26));

            handLayout = new FlowLayout(FlowLayout.CENTER);
            playerCardPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            handLayout.setHgap(5);
            handLayout.setVgap(10);
            playerCardPanel.setLayout(handLayout);
            playerCardPanel.setOpaque(true);


            //Player's Bet
            betPanel = new JPanel();
            BoxLayout boxLayout = new BoxLayout(betPanel, BoxLayout.PAGE_AXIS);
            betPanel.setLayout(boxLayout);
            betPanel.setPreferredSize(new Dimension(84,130));
            betPanel.setOpaque(false);
            betPanel.add(Box.createHorizontalGlue());

                betBox = new JPanel();
                FlowLayout betBoxLayout = new FlowLayout();
                betBox.setLayout(betBoxLayout);
                betBoxLayout.setHgap(-30);
                betBox.setOpaque(false);
                Border yellowBorder = BorderFactory.createLineBorder(new Color(244,179,36));
                betBox.setBorder(yellowBorder);
                betPanel.add(betBox);
                    placeBetButton = new JTextArea("CLICK TO PLACE BET");
                    placeBetButton.setFont(new Font("Arial", Font.BOLD,13));
                    placeBetButton.setForeground(Color.gray);
                    placeBetButton.setSize(new Dimension(80,130));
                    placeBetButton.setWrapStyleWord(true);
                    placeBetButton.setLineWrap(true);
                    placeBetButton.setEditable(false);
                    placeBetButton.setOpaque(false);
                    placeBetButton.setFocusable(false);

                    betBox.add(placeBetButton);

                betAmount = new JLabel(Integer.toString(Data.bet));
                betAmount.setFont(new Font("Arial", Font.PLAIN,15));
                betAmount.setAlignmentX(Component.CENTER_ALIGNMENT);
                betAmount.setForeground(new Color(244,179,36));
                //betPanel.add(Box.createRigidArea(new Dimension(30,0)));
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
                fundLabel = new JLabel("Funds: 2000");
                fundLabel.setForeground(new Color(244,179,36));
                fundLabel.setFont(new Font("Arial", Font.BOLD,30));
                fundLabelPanel.add(fundLabel, BorderLayout.WEST);

            panel5.add(fundLabelPanel);

            //BUTTONS PANEL
            JPanel buttonPanel = new JPanel();
            buttonPanel.setPreferredSize(new Dimension((getWidth()/3 - 30), getHeight()));
            buttonPanel.setOpaque(false);
                DecisionButton splitButton = new DecisionButton("SPLIT");
                DecisionButton hitButton = new DecisionButton("HIT");
                DecisionButton standButton = new DecisionButton("STAND");

                buttonPanel.add(splitButton);
                buttonPanel.add(hitButton);
                buttonPanel.add(standButton);

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
        //EVENT LISTENERS
        //HIT
        hitButton.addActionListener(e -> {
            decisionHit();
        });

        //PLACE BET
        placeBetButton.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                glassPane.setVisible(true);
                placeBetButton.setVisible(false);

            }
        });

        //Navigation
        homeButton.addActionListener(e -> {
            container.remove(tablePanel);
            container.add(welcomePanel);
            validate();
            repaint();
        });


    }

    private void decisionHit(){
        Card p = Data.currentDeck.draw();
        Data.addPlayerCard(p);
        handLayout.setHgap(-65);
        playerCardPanel.removeAll();
        for(int i = Data.playerHandCounter; i >= 0; i--){
            if(!(Data.playerHand[i] == null)){
                JLabel cardG = new JLabel(new ImageIcon(Data.playerHand[i].getImg().getScaledInstance(84,117, Image.SCALE_SMOOTH)));
                playerCardPanel.add(cardG);
            }
        }

        validate();
        repaint();

    }

    private void betUIComponents(){
        glassPane.setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel glassContentPanel = new JPanel();
        BoxLayout glassBetLayout = new BoxLayout(glassContentPanel, BoxLayout.Y_AXIS);
        glassContentPanel.setLayout(glassBetLayout);
        glassContentPanel.setOpaque(false);


            Icon chipButtonIcon1 = (new ImageIcon("ChipButtons/1.png"));

            JButton chipButton1 = new JButton(chipButtonIcon1);
            chipButton1.setFocusPainted(false);
            chipButton1.setBorderPainted(false);
            chipButton1.setContentAreaFilled(false);
            chipButton1.setAlignmentX(Component.CENTER_ALIGNMENT);

            Icon chipButtonIcon5 = new ImageIcon("ChipButtons/5.png");
            JButton chipButton5 = new JButton(chipButtonIcon5);
            chipButton5.setFocusPainted(false);
            chipButton5.setBorderPainted(false);
            chipButton5.setContentAreaFilled(false);
            chipButton5.setAlignmentX(Component.CENTER_ALIGNMENT);

            Icon chipButtonIcon50 = new ImageIcon("ChipButtons/50.png");
            JButton chipButton50 = new JButton(chipButtonIcon50);
            chipButton50.setFocusPainted(false);
            chipButton50.setBorderPainted(false);
            chipButton50.setContentAreaFilled(false);
            chipButton50.setAlignmentX(Component.CENTER_ALIGNMENT);

            Icon chipButtonIcon500 = new ImageIcon("ChipButtons/500.png");
            JButton chipButton500 = new JButton(chipButtonIcon500);
            chipButton500.setFocusPainted(false);
            chipButton500.setBorderPainted(false);
            chipButton500.setContentAreaFilled(false);
            chipButton500.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel betButtons = new JPanel();
            betButtons.setOpaque(false);
                DecisionButton cancelButton = new DecisionButton("Cancel");
                cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                DecisionButton acceptButton = new DecisionButton("Accept");
                acceptButton.setAlignmentX(Component.CENTER_ALIGNMENT);


            //Event listeners
            //buttons
            cancelButton.addActionListener(e -> {
                glassPane.setVisible(false);
                placeBetButton.setVisible(true);
            });
            acceptButton.addActionListener(e -> {
                glassPane.setVisible(false);
                placeBetButton.setVisible(false);
            });
            //chip buttons
            chipButton1.addActionListener(e -> {
                Data.bet += 1;
                betAmount.setText(Integer.toString(Data.bet));
                JLabel chip1 = new JLabel(new ImageIcon("Chips/1.png"));
                betBox.add(chip1);
            });
            chipButton5.addActionListener(e -> {
                Data.bet += 5;
                betAmount.setText(Integer.toString(Data.bet));
            });
            chipButton50.addActionListener(e -> {
                Data.bet += 50;
                betAmount.setText(Integer.toString(Data.bet));
            });
            chipButton500.addActionListener(e -> {
                Data.bet += 500;
                betAmount.setText(Integer.toString(Data.bet));
            });


            glassContentPanel.add(chipButton1);
            glassContentPanel.add(chipButton5);
            glassContentPanel.add(chipButton50);
            glassContentPanel.add(chipButton500);

            glassContentPanel.add(betButtons);
                glassContentPanel.add(cancelButton);
                glassContentPanel.add(Box.createRigidArea(new Dimension(0,10)));
                glassContentPanel.add(acceptButton);

        glassPane.add(glassContentPanel);
    }

    public static void music () throws LineUnavailableException, UnsupportedAudioFileException, IOException {

        File url = new File("Music/thejazzpiano.wav");
        Clip clip = AudioSystem.getClip();
        // getAudioInputStream() also accepts a File or InputStream
        AudioInputStream ais = AudioSystem.getAudioInputStream(url);
        clip.open(ais);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }


}

