package codinglegend.io.graphics;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public class GraphicsUtil {

    public static void drawStringCenteredLeft(Graphics g, String s, int x, int y){
        String[] tl = s.split("\n");
        for (int i = 0; i < tl.length; i++){
            FontMetrics fm = g.getFontMetrics();
            Rectangle2D r = fm.getStringBounds(tl[i], g);
            int Y = y-((int) r.getHeight()) / 2 - ((int)(r.getHeight()+5)*(tl.length-i)/2) + fm.getAscent();
            g.drawString(tl[i], x, Y);
        }
    }

    public static void drawStringCentered(Graphics g, String s, int x, int y){
        String[] tl = s.split("\n");
        for (int i = 0; i < tl.length; i++){
            FontMetrics fm = g.getFontMetrics();
            Rectangle2D r = fm.getStringBounds(tl[i], g);
            int X = x-((int) r.getWidth()) / 2;
            int Y = y-((int) r.getHeight()) / 4 - ((int)(r.getHeight()+16)*(tl.length-i)/4) + fm.getAscent();
            g.drawString(tl[i], X, Y);
        }
    }
}
