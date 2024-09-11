import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class WelcomePanel extends JPanel {
    private final Image welcomeImg = ImageIO.read(new File("WelcomeText.png"));

    public WelcomePanel() throws IOException {
        setLayout(new BorderLayout());
        setBackground(new Color(28,25,26));
    }
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(this.welcomeImg, 0, 0, 1440,810,null);
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(1440, 810);
    }

}
