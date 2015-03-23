/**************************************************************************************************
 * davkovani.myitems.timeritem01/TimerItem01.java
 * @author: Jan Chara
 * date: 20.9.2012
 * description: displays time
**************************************************************************************************/

package davkovani.myitems.timeritem01;

import javax.microedition.lcdui.*;
import davkovani.displayables.myform.*;


public class TimerItem01 extends CustomItem implements Runnable
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/

    public static final int DISPLAY_COLOR = 0xffffff;
    public static final int COLOR = 0xffffff;
    public static final int BACKGROUND_COLOR = 0x0000cc;
    public static final boolean STATIC = true;
    public static final boolean INTERACTIVE = false;
    private Displayable displayable;
    private boolean isStatic;
    private Font font;
    private Time t;
    private int time;
    private long startTime;
    private MyForm listener;
    private boolean konec;
    private boolean isInterrupted;
    private Thread thread;
    private int color;
    private int backgroundColor;
    private int displayColor;
    private int margin;
    private int minWidth;
    private int requiredHeight;


/**************************************************************************************************
 * constructors
**************************************************************************************************/

    public TimerItem01(final String name) { /// doupravit
        super(name);
    }

    public TimerItem01(final String name, Displayable displayable) { /// doupravit
        super(name);
        this.displayable = displayable; ///// použít showNotify() !!!!!
    }

    public TimerItem01(final String name, final int minWidth) {
        this(name, minWidth, TimerItem01.INTERACTIVE);
    }

    /*public TimerItem01(final String name, TimeFormat timeFormat) {
        this(name,  TimerItem01.INTERACTIVE);
    }*/

    public TimerItem01(final String name, final int minWidth, final boolean isStatic) {
        super(name);
        this.font = Font.getDefaultFont();
        //this.timeFormat = ...;
        this.time = 0;
        this.startTime = -1;
        this.backgroundColor = TimerItem01.BACKGROUND_COLOR;
        this.color = TimerItem01.COLOR;
        this.displayColor = TimerItem01.DISPLAY_COLOR;
        this.konec = true;
        this.margin = 0;
        this.minWidth = minWidth;
        this.requiredHeight = this.font.getHeight();
        this.isStatic = isStatic;
        this.isInterrupted = false;
    }


/**************************************************************************************************
 * main method:
**************************************************************************************************/

    public static void main(String[] args) {
        
    }
    

/**************************************************************************************************
 * overriding of parent or overloaded methods
**************************************************************************************************/

    public void paint(Graphics g, int width, int height) {
        g.setColor(this.backgroundColor);
        g.fillRect(0,0,width, height);
        g.setColor(this.displayColor);
        g.drawString("" + TimerItem01.getTime(this.time, ""), 05,00,00);
    }

    public int getPrefContentWidth(int width){
        return this.getMinContentWidth();
    }

    public int getPrefContentHeight(int height){
        return this.getMinContentHeight();
    }

    public int getMinContentWidth(){
        return this.minWidth;
    }

    public int getMinContentHeight(){
        return requiredHeight;
    }

    protected void keyPressed(int keyCode) {
        if (this.isStatic) return;
        int i = this.getGameAction(keyCode);
        if (Canvas.FIRE == i) {
            if (this.konec) {
                this.start();
            }
            else {
                this.stop();
            }
            this.notifyStateChanged();
            this.repaint();
        }
    }

    protected void showNotify() {
        if (null == this.thread) {
            return;
        }
        this.isInterrupted = false;
        //this.thread.notify();
        this.thread = new Thread(this);
        this.thread.start();
        //if (this.thread.isAlive()) {
            //this.thread.start();
        //}
    }

    protected void hideNotify() {
        if (null == this.thread) {
            return;
        }
        this.isInterrupted = true;
    }

    public void setActionListener(MyForm f) {
        this.listener = f;
    }

    public void run() {
        while (!this.konec) {
            if (this.isInterrupted) {
                return;
                //continue;
            }
            this.time = (int)(System.currentTimeMillis() - this.startTime);
            repaint();
            Thread.yield();
        }
        this.listener.actionPerformed(this);
    }


/**************************************************************************************************
 * own methods:
**************************************************************************************************/

    public int getTime() {
        // if (!this.stopped) return System.cuurentTimeMillis() - this.startTime();
        return this.time;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public int stop() {
        if (this.konec) return this.time;
        this.time = (int)(System.currentTimeMillis() - this.startTime);
        this.konec = true;
        if (0 > this.startTime) {
            repaint();
            return (-1);
        }
        this.time = (int)(System.currentTimeMillis() - this.startTime);
        this.startTime = -1;
        repaint();
        return this.time;
    }

    public void start() {
        if (!this.konec) {
            return;
        } // čas je již měřen
        if (-1 == this.startTime) {
            this.startTime = System.currentTimeMillis();
        }
        this.konec = false;
        this.thread = new Thread(this);
        this.thread.start();
    }

    public void reset() {
        this.time = 0;
        this.startTime = -1;
        this.konec = true;
        repaint();
    }

    public boolean isRunning() {
        return this.konec ? false : true;
    }

    public void setStartTime(long time) {
        this.startTime = time;
    }

    public void setHeight(final int height) {
        this.requiredHeight = height;
    }

    public void setWidth(final int minWidth) {
        this.minWidth = minWidth;
    }

    public void setBackgroundColor(final int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setColor(final int color) {
        this.color = color;
    }

    public void setDisplayColor(final int displayColor) {
        this.displayColor = displayColor;
    }

    public void setFormat(final String format) throws Throwable {
        this.t = new Time(format);
    }

    public void setInteractionMode(final boolean interactionMode) {
        this.isStatic = interactionMode;
    }

    public static String getTime(long time, final String format) {
        final int MILLIS_IN_SECS = 1000;
        final int MILLIS_IN_MINS = 60000;
        final int MILLIS_IN_HOUR = 3600000;
        final int MILLIS_IN_DAY = 86400000;
        String s = "";
        s += time/MILLIS_IN_HOUR + " : ";
        time %= MILLIS_IN_HOUR;
        s += time/MILLIS_IN_MINS + " : ";
        time %= MILLIS_IN_MINS;
        s += time/MILLIS_IN_SECS + " : ";
        time %= MILLIS_IN_SECS;
        s += time;
        return s;
    }


/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/

}
