/**************************************************************************************************
  davkovani/Davkovani.java
  @author: Jan Ch�ra
  date: 9.7.2009
  description: MIDlet aplikace
**************************************************************************************************/

package davkovani.displayables.mylist;

import javax.microedition.lcdui.*;
import davkovani.displayables.mydisplayable.*;


public abstract class MyList extends MyDisplayable
{
/**************************************************************************************************
  deklarace prom�nn�ch:
**************************************************************************************************/

  protected Image imagePart;


/**************************************************************************************************
  konstruktory:
**************************************************************************************************/

  public MyList(davkovani.Davkovani main, List d) {
  	super(d);
  	this.imagePart = null;
  }


/**************************************************************************************************
  p�ekryt� zd�d�n� nebo p�et�en� zd�d�n� metody:
**************************************************************************************************/



/**************************************************************************************************
  vlastn� metody:
**************************************************************************************************/

  public int append(String s, Image i) {
  	return ((List)this.d).append(s, i);
  }
  
  public int getSelectedIndex() {
  	return ((List)this.d).getSelectedIndex();
  }

  public Image setImagePart (String s) {
      Image i;
      try {
          i = Image.createImage(s);
          //java.lang.System.out.println("good\n");
      } catch (Exception e) {
          i = null;
          //java.lang.System.out.println("shit\n");
      }
      return i;
  }

  
/**************************************************************************************************
  metody implementovan� p�es rozhran�:
**************************************************************************************************/

  public void commandAction(Command c, Displayable d) {
  }


}