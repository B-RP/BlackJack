import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class CustomScrollBar extends BasicScrollBarUI {


    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        //transparent track
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        g.setColor(new Color(244,179,36));
        g.fillRoundRect(thumbBounds.x, thumbBounds.y, 10, thumbBounds.height, 10, 10);
    }

    @Override
    protected JButton createIncreaseButton(int orientation)
    {
        return new SimpleScrollButton("up");
    }

    @Override
    protected JButton createDecreaseButton(int orientation)
    {
        return new SimpleScrollButton("down");
    }



}
