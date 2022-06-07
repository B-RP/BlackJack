import javax.swing.*;
import java.awt.*;

public class DecisionButton extends JButton {

    public DecisionButton(String text){
        super(text);
        this.setBackground(new Color(48,48,48));
        this.setForeground(new Color(244,179,36));
        this.setFont(new Font("Arial", Font.BOLD,20));
        this.setPreferredSize(new Dimension(120,70));
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        super.setContentAreaFilled(false);

    }

    @Override
    public void paint(Graphics g){

        if(getModel().isPressed()){
            g.setColor(new Color(244,179,36));
            setForeground(new Color(48,48,48));
        }
        else{
            g.setColor(new Color(48,48,48));
            setForeground(new Color(244,179,36));
        }

        g.fillRect(0,0, getWidth(), getHeight());
        super.paintComponent(g);

    }

}
