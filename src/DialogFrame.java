import javax.swing.*;
import java.awt.*;

public class DialogFrame extends JFrame {

    private JPanel mainPanel;

    private JPanel messagePanel;
    private JPanel buttonPanel;

    public DialogFrame(String title, String message){
        super(title);
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.black);
        this.setContentPane(mainPanel);
        this.setPreferredSize(new Dimension(500,500));

    }

    private void buildUI(){
        //Basic Structure
        BoxLayout mainLayout = new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS);
        mainPanel.setLayout(mainLayout);

        messagePanel  = new JPanel();
        messagePanel.setOpaque(false);

        buttonPanel = new JPanel();

        mainPanel.add(messagePanel);
        mainPanel.add(buttonPanel);

    }

}
