package codinglegend.io.graphics.ui;

import java.awt.Color;
import java.awt.Font;

public class ButtonStyle {
    
    //Background
    public Color background = Color.WHITE;


    //Border
    public Color border = Color.BLACK;

    public int borderTop = 0;
    public int borderBottom = 0;
    public int borderLeft = 0;
    public int borderRight = 0;

    public int arcWidth = 0;
    public int arcHeight = 0;


    //Font
    public Font font;
    public Color fontColor = Color.BLACK;

    /** Returns a copy of this buttonstyle */
    public ButtonStyle clone(){
        return new ButtonStyle()
            .setBackgroundColor(background)
            .setBorder(border,borderTop,borderBottom,borderLeft,borderRight)
            .setArcSize(arcWidth, arcHeight);
    }

    public ButtonStyle setBackgroundColor(Color bg){
        background = bg;
        return this;
    }

    public ButtonStyle setBorder(Color c, int borderWidth){
        setBorderColor(c);
        setBorderWidth(borderWidth);
        return this;
    }

    public ButtonStyle setBorder(Color c, int top, int bottom, int left, int right){
        border = c;
        return setBorderWidth(top,bottom,left,right);
    }

    public ButtonStyle setBorderColor(Color c){
        border = c;
        return this;
    }

    public ButtonStyle setBorderWidth(int w){
        setBorderWidth(w,w,w,w);
        return this;
    }

    public ButtonStyle setBorderWidth(int top, int bottom, int left, int right){
        borderTop = top;
        borderBottom = bottom;
        borderLeft = left;
        borderRight = right;
        return this;
    }

    public ButtonStyle setArcWidth(int w){
        arcWidth = w;
        return this;
    }

    public ButtonStyle setArcHeight(int h){
        arcHeight = h;
        return this;
    }

    public ButtonStyle setArcSize(int size){
        return setArcSize(size,size);
    }

    public ButtonStyle setArcSize(int width, int height){
        arcWidth = width;
        arcHeight = height;
        return this;
    }


}
