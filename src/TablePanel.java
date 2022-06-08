import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TablePanel extends JPanel {

    private final Image logo = ImageIO.read(new File("TableText.png"));

    public TablePanel() throws IOException {
        setLayout(new BorderLayout());
        this.setBackground(new Color(28,25,26));


    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(new Color(48,48,48));
        g.drawOval(50, -this.getHeight()+150,(this.getWidth()-100),(this.getWidth() - 100));
        g.fillOval(50,-this.getHeight()+150, this.getWidth()-100,this.getWidth()-100);
        g.drawImage(this.logo, 0, -50, 1440,810,null);
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(1440, 810);
    }

}
