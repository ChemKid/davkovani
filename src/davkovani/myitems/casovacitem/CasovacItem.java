/**************************************************************************************************
 * davkovani/myitems/casovacitem/CasovacItem.java
 * @author: Jan Chara
 * date: 9.7.2009
 * description: timer customized Item class
**************************************************************************************************/

package davkovani.myitems.casovacitem;

import javax.microedition.lcdui.*;
//import davkovani.displayables.menulist.*;


public class CasovacItem extends CustomItem implements Runnable
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/

  private final int R = 10; // polomer
  private final int SPEED = 1000; // 1000 ms
  private final int BACKGROUND_COLOR = 0x000000;
  private final int COLOR = 0xffffff;
  private int backgroundColor;
  private int color;
  private boolean konec;
  private Thread thread;
  private int i;
  private double x;
  private double y;


/**************************************************************************************************
 * constructors
**************************************************************************************************/

  public CasovacItem(String name) {
    super(name);
    this.backgroundColor = this.BACKGROUND_COLOR;
    this.color = this.COLOR;
    this.konec = true;
    this.i = 0;
    this.x = 1;
    this.y = 0;
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
    g.setColor(0x0000dd);
    g.fillRect(0,0,width, height);
  	g.setColor(this.backgroundColor);
  	g.fillArc(0, 0, width, height, 0, 360);
    g.setColor(this.color);
    int w,h;
    //g.drawString("[" + this.x + ";" + (int)this.y + "]", 20,30,0);
    g.drawLine(width/2, height/2, w=(int)((width/2)*this.x), h=(int)((height/2)*this.y));
    String sX = Integer.toString(w);
    String sY = Integer.toString(h);
    g.drawString("[" + sX + ";" + sY + "]", 20,30,0);
    g.drawString("" + this.i, 20,20,0);
  }

  public int getPrefContentWidth(int width){
    return this.getMinContentWidth();
  }

  public int getPrefContentHeight(int height){
    return this.getMinContentHeight();
  }

  public int getMinContentWidth(){
    return 100;
  }

  public int getMinContentHeight(){
    return 100;
  }
  
  protected void keyPressed(int keyCode) {
  	int i = this.getGameAction(keyCode);
  	if (Canvas.FIRE == i) {
  	  if (this.konec) {
  	  	this.konec = false;
  	  	this.start();
  	  }
  	  else {
  	  	this.konec = true;
  	  }
  	}
  }
  
  public void run() {
  	long time;
  	long startTime = System.currentTimeMillis();
  	long currentTime;
  	while (!this.konec) {
  	  currentTime = System.currentTimeMillis();
  	  time = (currentTime - startTime)/1000;
  	  this.x = 1+Math.sin(Math.toRadians(6)*time);
  	  this.y = 1-Math.cos(Math.toRadians(6)*time);
  	  this.i = (int)time;
  	  repaint();
  	}
  }


/**************************************************************************************************
* own methods:
**************************************************************************************************/

  public void setBackgroundColor(int backgroundColor) {
  	this.backgroundColor = backgroundColor;
  }
  
  public void setColor(int color) {
  	this.color = color;
  }
  
  public void start() {
  	this.thread = new Thread(this);
    this.thread.start();
  }
  

/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/


/**************************************************************************************************
 * nested classes:
**************************************************************************************************/

  private class Spoustec extends Thread {
  	public void run() {
  	    	  
  	}
  }

}