package codinglegend.io.graphics;

import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import codinglegend.math.Vector2D;

/** This class is intended for displaying static images onto the screen */
public class Sprite {

    /** The center X and center Y of the sprite */
    public double x, y;
    public double velX, velY;
    public int width,height;

    /** The rotation of the sprite in degrees */
    public double rotation;

    public double rotationSpeed;

    /** The image representing the sprite */
    public Image image;

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
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        image = i;
    }

    /**Loads the image from the string filepath */
    public static Image loadImage(String imagePath){
        return new ImageIcon(imagePath).getImage();
    }

    /** Sets the x and y of the sprite */
    public void setPos(double x, double y){ this.x = x; this.y = y; }

    /** Sets the velocity vector of the sprite using cartesan coordinates */
    public void setVelocity(double x, double y){ velX = x; velY = y; }

    public void setVelocity(Vector2D v){ velX = v.getX(); velY = v.getY(); }

    /** Sets the velocity of the sprite using a vector with direction and magnitude */
    public void setVelocityVectorDeg(double magnitude, double degrees){
        setVelocityVectorRad(magnitude,Math.toRadians(degrees));
    }

    /** Sets the velocity of the sprite using a vector with direction and magnitude */
    public void setVelocityVectorRad(double magnitude, double radians){
        x = Math.cos(radians);
        y = Math.sin(radians);
    }

    public void addVelocity(double x, double y){ velX += x; velY += y; }
    public void addVelocity(Vector2D v){ velX += v.getX(); velY += v.getY(); }

    /** Adds a new vector to the current velocity vector */
    public void addVelocityVectorDeg(double magnitude, double degrees){
        addVelocityVectorRad(magnitude,Math.toRadians(degrees));
    }

    /** Adds a new vector to the current velocity vector */
    public void addVelocityVectorRad(double magnitude, double radians){
        x += Math.cos(radians);
        y += Math.sin(radians);
    }

    /** Returns a Vector2D of the current velocity vector */
    public Vector2D getVelocityVector(){ return new Vector2D(velX,velY); }

    /** Set the width and height off the sprite */
    public void setSize(int w, int h){ width = w; height = h; }

    /** Check if two sprites are colliding */
    public boolean checkCollision(Sprite s){
        //If this.right > s.left && this.left < s.right && this.bottom > s.top && this.top < s.bottom
        Rectangle collide1 = new Rectangle((int)x+width/2,(int)y+height/2,width,height);
        Rectangle collide2 = new Rectangle((int)s.x-s.width/2,(int)s.y-s.height/2,s.width,s.height);
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
        rotation += rotationSpeed;
    }

    /** Draws the sprite */
    public void draw(Graphics2D g){
        if (image == null) return; //Dont draw if there is no image

        double rotate = Math.toRadians(rotation);

        g.rotate(rotate,x,y); //Rotate the canvas centered on the sprite
        
        g.drawImage(image,(int)x-width/2,(int)y-height/2,width,height,null);

        g.rotate(-rotate,x,y); //Rotate the canvas back to original

    }
}