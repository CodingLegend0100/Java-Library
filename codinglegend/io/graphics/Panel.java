package codinglegend.io.graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/** A JPanel with a premade update and repaint loop for easy use of java graphics.
 * The start method must be called to begin the loop
*/
public class Panel extends JPanel implements Runnable {
    protected int FPS = 60;

    private Thread drawThread;

    public Panel(){
        setLayout(null);
    }

    public Panel(int width, int height){
        this();
        setSize(width,height);
    }

    public void setPreferredSize(int width, int height){
        setPreferredSize(new Dimension(width, height));
    }


    /** Creates a new thread to manage the update and repaint loop for this panel */
    public void start(){
        drawThread = new Thread(this);
        drawThread.start();
    }

    /** Stops updating this panel */
    public void stop(){
        drawThread = null;
    }

    /** The main method handing the update and repaint loop */
    public void run(){
        //How this works
        
        //Calculate nanoseconds between frames by
        //dividing nanoseconds (1 billion) in 1 second by the FPS
        int drawInterval = 1000000000/FPS;
 
        long lastTime = System.nanoTime(); //The last time checked
        long currentTime; //The current time

        long delta = 0; //Nanoseconds since the last frame was drawn
        while(drawThread != null){

            currentTime = System.nanoTime(); //Get the current time
            delta += currentTime - lastTime; //Add time difference to delta
            lastTime = currentTime;

            //If enough time has passed, update and draw the frame
            if (delta >= drawInterval){
                update();
                repaint();
                delta = 0;
            }
        }

    }

    public void update(){}
    public void draw(Graphics2D g){}

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw((Graphics2D)g);
        g.dispose();
    }
}
