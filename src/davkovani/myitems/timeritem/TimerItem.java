/**************************************************************************************************
  davkovani/Davkovani.java
  @author: Jan Ch�ra
  date: 9.7.2009
  description: MIDlet aplikace
**************************************************************************************************/

package davkovani.myitems.timeritem;

import javax.microedition.lcdui.*;
import davkovani.displayables.myform.*;


public class TimerItem extends CustomItem implements Runnable
{
/**************************************************************************************************
  deklarace proměnných:
**************************************************************************************************/

  public static final int DISPLAY_COLOR = 0xffffff;
  public static final int COLOR = 0xffffff;
  public static final int BACKGROUND_COLOR = 0x0000cc;
  private Font font;
  private int time;
  private long startTime;
  private long endTime;
  private MyForm listener; 
  private double x;
  private double y;
  private int i;
  private boolean konec;
  private Thread thread;
  private int color;
  private int backgroundColor;
  private int displayColor;
  private int margin;
  private int minWidth;
  private int requiredHeight;


/**************************************************************************************************
  konstruktory:
**************************************************************************************************/

  public TimerItem(String name) {
  	super(name);
  }

  public TimerItem(String name, int minWidth) {
    super(name);
    this.font = Font.getDefaultFont();
    this.time = -1;
    this.startTime = -1;
    this.backgroundColor = this.BACKGROUND_COLOR;
    this.color = this.COLOR;
    this.displayColor = this.DISPLAY_COLOR;
    this.konec = true;
    this.i = 0;
    this.x = 1;
    this.y = 0;
    this.margin = 0;
    this.minWidth = minWidth;
    this.requiredHeight = this.font.getHeight();
  }


/**************************************************************************************************
  překrytí zděděných nebo přetížených metod:
**************************************************************************************************/

  public void paint(Graphics g, int width, int height) {
  	g.setColor(this.backgroundColor);
    g.fillRect(0,0,width, height);
  	g.setColor(this.displayColor);
  	this.margin = width/5;
  	int w,h;
  	g.drawArc(this.margin, 0, width - (2*margin), height, 0, 360);
    g.setColor(this.color);
    w = (width - (2*margin))/2;
    h = height/2;
    g.drawLine(width/2, h, (int)(this.margin + (w*this.x)), (int)(h*this.y));
    g.drawString("" + this.i, 05,00,00);
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
  	int i = this.getGameAction(keyCode);
  	if (Canvas.FIRE == i) {
  	  if (this.konec) {	  	
  	  	this.konec = false;
  	  	this.start();
  	  }
  	  else {
  	  	this.startTime = System.currentTimeMillis();
        this.konec = true;
  	  }
  	  this.repaint();
  	}
  }
  
  public void setActionListener(MyForm f) {
  	this.listener = f;
  }
  
  public void run() {
  	this.startTime = System.currentTimeMillis();
  	while (!this.konec) {
  	  this.endTime = System.currentTimeMillis();
  	  time = (int)(this.endTime - this.startTime);
  	  this.i = time/1000;
  	  this.x = 1+Math.sin(Math.toRadians(6)*i);
  	  this.y = 1-Math.cos(Math.toRadians(6)*i);
  	  repaint();
  	}
  	this.startTime = -1;
  	this.listener.actionPerformed(this);  
  }


/**************************************************************************************************
  vlastní metody:
**************************************************************************************************/

  public int getTime() {
  	return this.time;
  }
  
  public int stop() {
  	this.konec = true;
  	this.x = 1;
  	this.y = 0;
  	this.i = 0;
  	if (0 > this.startTime) {
  	  repaint();
  	  return (-1);
  	}
  	this.endTime = System.currentTimeMillis();
  	this.time = (int)(this.endTime - this.startTime);
  	this.startTime = -1;
  	repaint();
  	return this.time;
  }
  
  public void start() {
  	this.thread = new Thread(this);
    this.thread.start();
  }
  
  public void setHeight(int height) {
  	this.requiredHeight = height;
  }
  
  public void setBackgroundColor(int backgroundColor) {
  	this.backgroundColor = backgroundColor;
  }
  
  public void setColor(int color) {
  	this.color = color;
  }
  
  public void setDisplayColor(int displayColor) {
  	this.displayColor = displayColor;
  }


/**************************************************************************************************
  interface implemented methods:
**************************************************************************************************/

}