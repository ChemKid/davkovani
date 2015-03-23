/**************************************************************************************************
 * davkovani/displayables/preloadscreencanvas/PreloadScreenCanvas.java
 * @author: Jan Chara
 * date: 18.11.2012
 * description: preloader
**************************************************************************************************/

package davkovani.displayables.preloadscreencanvas;

import davkovani.Davkovani;
import javax.microedition.lcdui.*;


public class PreloadScreenCanvas extends Canvas implements CommandListener
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/

    private Davkovani main;
    private int part;
    private int maxValue;
    private int barWidth;
    private int increment;


/**************************************************************************************************
 * constructors
**************************************************************************************************/

    public PreloadScreenCanvas(Davkovani main) {
        this(main, 0);
    }

    public PreloadScreenCanvas(Davkovani main, int maxValue) {
        this.main = main;
        this.part = 0;
        this.maxValue = 0;
        this.barWidth = 70;
        this.increment = 0;
    }


/**************************************************************************************************
 * main method:
**************************************************************************************************/

    public static void main(String[] args) {

    }


/**************************************************************************************************
 * overriding of parent or overloaded methods
**************************************************************************************************/

    protected void paint(Graphics g) {
        g.setColor(255, 255, 255);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(0, 0, 0);
        g.drawString("Loading ...", 20, 20, Graphics.TOP|Graphics.LEFT);
        g.drawRect(15, 50, this.barWidth, 10);
        g.fillRect(15, 50, this.part, 10);
    }


/**************************************************************************************************
 * own methods:
**************************************************************************************************/

    private void reset() {
    }

    public void setPart(int part) {
        this.part = part;
        this.repaint();
    }

    public void increase() {
        this.part += this.increment;
        this.repaint();
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
        //if(load<69) { load+=6; } else { load=70; }
        //this.increment = 
    }


/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/

    public void commandAction(Command c, Displayable d) {
    }
}