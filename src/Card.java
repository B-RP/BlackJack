import java.awt.*;
import java.awt.image.BufferedImage;

public class Card {
    //TODO Change return style
    private final String color;
    private final String suit;
    private final String rank;
    private final BufferedImage img;


    Card(String clr, String st, String rnk, BufferedImage img){

        this.color = clr;
        this.suit = st;
        this.rank = rnk;
        this.img = img;
    }

    private String specialRank(String r) {

        String strRank = switch (r) {
            case "1" -> "ace";
            case "11" -> "jack";
            case "12" -> "queen";
            case "13" -> "king";
            default -> "";
        };

        return strRank;
    }

    public String getRank() {
        return rank;
    }

    public String getColor() {
        return color;
    }

    public String getSuit() {
        return suit;
    }

    public BufferedImage getImg(){return img;}

    public int value() {
        if(rank.equals("11") || rank.equals("12") || rank.equals("13")) {
            return 10;
        }
        else {
            return Integer.parseInt(rank);
        }
    }

    //FOR TESTING
    public String toString() {
        String retStr = "";
        retStr += color + " ";

        if(rank.equals("1") || rank.equals("11") || rank.equals("12") || rank.equals("13")) {
            retStr += specialRank(rank);
        }
        else {
            retStr += rank;
        }

        retStr += " of " + suit;

        return retStr;
    }
}
