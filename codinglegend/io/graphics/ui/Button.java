package codinglegend.io.graphics.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import codinglegend.io.graphics.GraphicsObject;
import codinglegend.io.graphics.GraphicsUtil;

/** A simpler and easier to understand button class than JButton */
public class Button extends GraphicsObject implements MouseListener, MouseMotionListener {

    private ButtonController controller = new ButtonController(){};

    private boolean mouseOver = false;
    private boolean mouseDown = false;

    public ButtonStyle defaultStyle = new ButtonStyle().setBorder(Color.BLACK,2).setArcSize(3);
    public ButtonStyle hoverStyle = new ButtonStyle().setBackgroundColor(new Color(200,200,200)).setBorder(Color.BLACK, 3).setArcSize(3);
    public ButtonStyle clickStyle = new ButtonStyle().setBackgroundColor(Color.LIGHT_GRAY).setBorder(Color.GRAY,3).setArcSize(3);

    String text = "";

    public Button(String text){
        //Rectangle2D bounds =  getGraphics().getFontMetrics().getStringBounds(text,getGraphics());
        //setSize((int)bounds.getWidth(),(int)bounds.getHeight());
        this.text = text;
        initialize();
    }

    public Button(String text, int x, int y){
        this(text);
        setPos(x,y);
    }

    public Button(int x, int y, int width, int height){
        initialize();
    }

    public Button(String text, int x, int y, int width, int height){
        this(text);
        setBounds(x, y, width, height);

    }

    public Button(String text, ButtonStyle s){
        this(text);
        defaultStyle = s;
    }

    public Button(String text, int x, int y, ButtonStyle s){
        this(text,x,y);
        defaultStyle = s;
    }

    public Button(int x, int y, int width, int height,ButtonStyle s){
        this(x,y,width,height);
        defaultStyle = s;
    }

    public Button(String text, int x, int y, int width, int height, ButtonStyle s){
        this(text,x,y,width,height);
        defaultStyle = s;

    }

    private void initialize(){
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    /** Sets all button styles (the default style, the hover style, and the click style) to this style */
    public void setStyle(ButtonStyle s){
        defaultStyle = s;
        hoverStyle = s;
        clickStyle = s;
    }

    public void setButtonController(ButtonController c){
        controller = c;
    }

    public ButtonController getButtonController(){ return controller; }

    public void setPos(int x, int y){
        setBounds(x,y,getWidth(),getHeight());
    }

    public void shrink(int px){
        grow(-px);
    }

    public void grow(int px){
        setBounds(getX()-px,getY()-px,getWidth()+2*px,getHeight()+2*px);
    }

    public void paintComponent(Graphics g){
        ButtonStyle style = (mouseDown) ? clickStyle : (mouseOver) ? hoverStyle : defaultStyle;

        //Draw the border
        if (style.border != null){
            g.setColor(style.border);
            g.fillRoundRect(0,0, getWidth(), getHeight(), style.arcWidth, style.arcHeight);
        }

        //Draw the background
        if (style.background != null){
            g.setColor(style.background);
            g.fillRoundRect(style.borderLeft, style.borderTop, //Position
                        getWidth()-style.borderLeft-style.borderRight, getHeight()-style.borderTop-style.borderBottom, //Size
                        style.arcWidth, style.arcHeight //Arc Size
                        );
        }

        //Draw the text
        if (style.fontColor != null) g.setColor(style.fontColor);
        if (style.font != null) g.setFont(style.font);
        GraphicsUtil.drawStringCentered(g, text, getWidth()/2, getHeight()/2);
    }

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        controller.buttonPressed();
        mouseDown = true;
        repaint();
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (mouseOver) controller.buttonClicked();
        mouseDown = false;
        repaint();
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {        
        controller.mouseEntered();
        mouseOver = true;
        repaint();
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        controller.mouseExited();
        mouseOver = false;
        repaint();
        
    }
}
