import javax.swing.*;
import java.awt.*;

public class ChipButton extends JButton {

    ChipButton(Icon icon){
        super(icon);
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setAlignmentX(Component.CENTER_ALIGNMENT);

    }



}
