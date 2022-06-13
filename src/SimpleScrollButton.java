import javax.swing.*;
import java.awt.*;

public class SimpleScrollButton extends JButton {
    private String orientation = "";
    private Shape triangle;

    SimpleScrollButton(String o){
        this.orientation = o;
        this.setFocusPainted(false);
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        triangle = createTriangle();
    }

    public void paintBorder( Graphics g ) {
        g.setColor(new Color(244,179,36));
        ((Graphics2D)g).draw(triangle);
    }
    public void paintComponent( Graphics g ) {
        g.setColor(new Color(244,179,36));
        ((Graphics2D)g).fill(triangle);
    }

    public boolean contains(int x, int y) {
        return triangle.contains(x, y);
    }

    private Shape createTriangle() {
        Polygon p = new Polygon();
        if(orientation.equals("up")){
            p.addPoint( 0   , 0 );
            p.addPoint( 5 , 10   );
            p.addPoint( 10 ,0  );
        }
        else{
            p.addPoint( 0   , 10 );
            p.addPoint( 5 , 0   );
            p.addPoint( 10 ,10  );
        }
        return p;
    }

}
