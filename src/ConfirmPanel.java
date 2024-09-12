
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class ConfirmPanel extends JPanel{
    
    public ConfirmPanel(){
        
        setBackground(new Color(28,25,26));
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(300, 200);
    }
}
