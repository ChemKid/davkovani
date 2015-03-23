/**************************************************************************************************
  davkovani/Davkovani.java
  @author: Jan Chára
  date: 9.7.2009
  description: MIDlet aplikace
**************************************************************************************************/

package davkovani.displayables.mycanvas;

import javax.microedition.lcdui.*;
import davkovani.displayables.mydisplayable.*;
import davkovani.myitems.actionlistener.*;
import davkovani.myitems.div.Div;


public abstract class MyCanvas extends MyDisplayable implements ItemStateListener, ActionListener
{
/**************************************************************************************************
  deklarace proměnných:
**************************************************************************************************/

  protected Font font;
  protected int screenWidth;


/**************************************************************************************************
  konstruktory:
**************************************************************************************************/

  public MyCanvas(davkovani.Davkovani main, Canvas d) {
  	super(d);
  	this.font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
  	this.screenWidth = this.d.getWidth();
  }


/**************************************************************************************************
  metoda main:
**************************************************************************************************/

  
    public static void main(String[] args) {
        
    }


/**************************************************************************************************
  překrytí zděděných nebo přetížených zděděných metod:
**************************************************************************************************/



/**************************************************************************************************
  vlastní metody:
**************************************************************************************************/

  public int append(Div d) {
  	return 0;//((Form)this.d).append(s);
  }

  public int append(Item i) {
  	return 0;//((Form)this.d).append(i);
  }

  public void setItemStateListener() {
  	//((Form)this.d).setItemStateListener(this);
  }

 /* public Item get(int itemNum) {
  	return ((Form)this.d).get(itemNum);
  }*/

  /*public void deleteAll() {
  	((Form)this.d).deleteAll();
  }

  public void delete(int itemNum) {
        ((Form)this.d).delete(itemNum);
  }

  public int size() {
  	return ((Form)this.d).size();
  }

  public void insert(int itemNum, Item item) {
  	((Form)this.d).insert(itemNum, item);
  }*/

/**************************************************************************************************
  metody implementované přes rozhraní:
**************************************************************************************************/

  public void commandAction(Command c, Displayable d) {
  }

  public void actionPerformed(CustomItem customItem) {
  }
}