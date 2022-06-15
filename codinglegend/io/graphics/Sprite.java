package codinglegend.io.graphics;

import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import codinglegend.math.Vector2D;

/** This class is intended for displaying static images onto the screen */
public class Sprite extends GraphicsObject {

    /** The X and Y coordinates of the sprite */
    private double x, y;

    /** The X and Y velocities of the sprite (pixels per frame) */
    private double velX, velY;

    /** The rotation of the sprite in RADIANS */
    private double rotation;

    /** The rotation speed of the sprite in RADIANS*/
    private double rotationSpeed;

    /** The image representing the sprite */
    private Image image;

    /** Blank constructor for a blank sprite */
    public Sprite(){}

    /**Create a sprite whose size will be inherited from the image's width and height */
    public Sprite(String image,double x,double y){
        this(loadImage(image),x,y);
    }

    /**Create a sprite whose size will be inherited from the image's width and height */
    public Sprite(Image i,double x,double y){
        this(i,x,y,i.getWidth(null),i.getHeight(null));
    }

    /**Create a sprite whose size will be inherited from the image's scaled size */
    public Sprite(String image,double x,double y,double scale){
        this(loadImage(image),x,y,scale);
    }
    
    /**Create a sprite whose size will be inherited from the image's scaled size */
    public Sprite(Image i,double x,double y,double scale){
        this(i,x,y,(int)(i.getWidth(null)*scale),(int)(i.getHeight(null)*scale));
    }

    /**Create a sprite with the predetermined position and size */
    public Sprite(String image,double x,double y,int width,int height){
        this(loadImage(image),x,y,width,height);
    }

    /**Create a sprite with the predetermined position and size */
    public Sprite(Image i,double x,double y,int width, int height){
        setBounds((int)x,(int) y,width,height);
        this.x = x;
        this.y = y;
        image = i;
    }

    /**Loads the image from the string filepath */
    public static Image loadImage(String imagePath){
        return new ImageIcon(imagePath).getImage();
    }

    /** Sets the x and y of the sprite */
    public void setX(double x){ setLocation(x,y); }
    public void setY(double y){ setLocation(x,y); }
    public void setLocation(double x, double y){
        this.x = x;
        this.y = y;
        setLocation((int) x, (int) y);
    }

    /** Sets the velocity vector of the sprite using cartesan coordinates */
    public void setVelocityX(double x){ velX = x; }
    public void setVelocityY(double y){ velY = y; }

    public void setVelocity(double x, double y){ velX = x; velY = y; }
    public void setVelocity(Vector2D v){ velX = v.getX(); velY = v.getY(); }

    public void addVelocity(double x, double y){ velX += x; velY += y; }
    public void addVelocity(Vector2D v){ velX += v.getX(); velY += v.getY(); }

    /** Returns a Vector2D of the current velocity vector */
    public Vector2D getVelocityVector(){ return new Vector2D(velX,velY); }

    public Image getImage(){ return image; }
    public void setImage(Image i){
        image = i;
        //repaint();
    }

    /** Sets the rotation of the sprite */
    public void setRotation(double degrees){
        rotation = Math.toRadians(degrees);
    }
    /** Returns the rotation of this object in degrees */
    public double getRotation(){
        return Math.toDegrees(rotation);
    }

    /** Sets the rotation speed of the sprite */
    public void setRotationSpeed(double degrees){
        rotationSpeed = Math.toRadians(degrees);
    }

    /** Check if two sprites are colliding */
    public boolean checkCollision(Sprite s){
        Rectangle collide1 = getBounds();
        Rectangle collide2 = s.getBounds();
        return collide1.intersects(collide2) || collide1.contains(collide2) || collide2.contains(collide1);
    }

    /** Checks the collision with a list of sprites
     * @return an arraylist of sprites this sprite is colliding with
     */
    public ArrayList<Sprite> checkCollisionList(ArrayList<Sprite> sprites){
        ArrayList<Sprite> colliding = new ArrayList<Sprite>();
        for (Sprite s : sprites){
            if (checkCollision(s)) colliding.add(s);
        }
        return colliding;
    }

    /** Update the position and rotation of the sprite based on velocity */
    public void update(){
        x += velX;
        y += velY;
        setLocation(x,y);

        rotation += rotationSpeed;
    }

    /** Draws the sprite */
    public void paintComponent(Graphics g){
        if (image == null) return; //Dont draw if there is no image

        Graphics2D g2 = (Graphics2D) g; //Use graphics 2d to make use of rotation

        g2.rotate(rotation,getWidth()/2.0,getHeight()/2.0); //Rotate the canvas centered on the sprite
        
        g2.drawImage(image,0,0,getWidth(),getHeight(),null);

        g2.rotate(-rotation,getWidth()/2.0,getHeight()/2.0); //Rotate the canvas back to original

    }
}