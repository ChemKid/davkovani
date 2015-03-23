/**************************************************************************************************
  davkovani/Davkovani.java
  @author: Jan Chára
  date: 9.7.2009
  description: MIDlet aplikace
**************************************************************************************************/

package davkovani.myitems.inlinestringitem;

import javax.microedition.lcdui.*;
//import davkovani.displayables.menulist.*;


public class InlineStringItem extends CustomItem
{
/**************************************************************************************************
  deklarace proměnných:
**************************************************************************************************/

  private final int BACKGROUND_COLOR = 0xffffff;
  private final int COLOR = 0x000000;
  private int backgroundColor;
  private int color;
  private Font font;
  private String string;


/**************************************************************************************************
  konstruktory:
**************************************************************************************************/

  public InlineStringItem(String name) {
    super(name);
    this.font = Font.getDefaultFont();
    this.backgroundColor = this.BACKGROUND_COLOR;
    this.color = this.COLOR;
    this.string = name;
  }


/**************************************************************************************************
  p�ekryt� zd�d�n� nebo p�et�en� zd�d�n� metody:
**************************************************************************************************/

  public void paint(Graphics g, int width, int height) {
  	g.setColor(this.backgroundColor);
  	g.fillRect(0, 0, width - 1, height - 1);
    g.setColor(this.color);
    g.drawString(this.string, 0, 0, 0);
  }

  public int getPrefContentWidth(int width){
    return this.getMinContentWidth();
  }

  public int getPrefContentHeight(int height){
    return this.getMinContentHeight();
  }

  public int getMinContentWidth(){
    return this.font.stringWidth(this.string);
  }

  public int getMinContentHeight(){
    return this.font.getHeight();
  }


/**************************************************************************************************
  vlastn� metody:
**************************************************************************************************/

  public void setBackgroundColor(int backgroundColor) {
  	this.backgroundColor = backgroundColor;
  }
  
  public void setColor(int color) {
  	this.color = color;
  }
  

/**************************************************************************************************
  metody implementovan� p�es rozhran�:
**************************************************************************************************/


}