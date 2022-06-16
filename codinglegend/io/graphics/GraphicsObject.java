package codinglegend.io.graphics;

import java.awt.Graphics;
import javax.swing.JComponent;

public abstract class GraphicsObject extends JComponent {

    public void setX(int x){
        setLocation(x,getY());
    }
    public void setY(int y){
        setLocation(getX(),y);
    }

    public void setWidth(int w){
        setSize(w,getHeight());
    }

    public void setHeight(int h){
        setSize(getWidth(),h);
    }

    public abstract void update();
    public abstract void paintComponent(Graphics g);
}
