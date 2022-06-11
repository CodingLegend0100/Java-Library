package codinglegend.io.graphics.ui;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import codinglegend.io.graphics.GraphicsUtil;

/** A simpler and easier to understand button class than JButton */
public class Button extends Component implements MouseListener, MouseMotionListener {

    private ButtonController controller = new ButtonController(){};

    public ButtonStyle style = new ButtonStyle();

    // private ButtonStyle defaultStyle = new ButtonStyle();
    // private ButtonStyle hoverStyle = new ButtonStyle();
    // private ButtonStyle clickStyle = new ButtonStyle();

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
        style = s;
    }

    public Button(String text, int x, int y, ButtonStyle s){
        this(text,x,y);
        style = s;
    }

    public Button(int x, int y, int width, int height,ButtonStyle s){
        this(x,y,width,height);
        style = s;
    }

    public Button(String text, int x, int y, int width, int height, ButtonStyle s){
        this(text,x,y,width,height);
        style = s;

    }

    private void initialize(){
        addMouseListener(this);
        addMouseMotionListener(this);
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

    public void paint(Graphics g){
        
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
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getComponent() == this) controller.mouseEntered();
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getComponent() == this) controller.mouseExited();
        
    }
}
