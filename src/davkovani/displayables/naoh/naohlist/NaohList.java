/**************************************************************************************************
  davkovani/Davkovani.java
  @author: Jan Ch�ra
  date: 9.7.2009
  description: MIDlet aplikace
**************************************************************************************************/

package davkovani.displayables.naoh.naohlist;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.microedition.lcdui.*;
import davkovani.displayables.mylist.*;


public class NaohList extends MyList
{
/**************************************************************************************************
  deklarace prom�nn�ch:
**************************************************************************************************/


/**************************************************************************************************
  konstruktory:
**************************************************************************************************/

  public NaohList(davkovani.Davkovani main, List d) {
  	super(main, d);
  	this.addCommand(this.cBack);
  	this.setCommandListener(this);

  	this.append("ověření koncentrace", this.imagePart);
  }


/**************************************************************************************************
  p�ekryt� zd�d�n� nebo p�et�en� zd�d�n� metody:
**************************************************************************************************/



/**************************************************************************************************
  vlastn� metody:
**************************************************************************************************/


/**************************************************************************************************
  metody implementovan� p�es rozhran�:
**************************************************************************************************/

  public void commandAction(Command c, Displayable d) {
  	if (this.d == d) {
  	  if (this.cBack == c) {
  	  	this.main.display(this.main.menuList);
  	  	return;
  	  }
  	  int selectedIndex=this.getSelectedIndex();
  	  switch(selectedIndex) {
  	  	case 0: 
                    this.main.display(new davkovani.displayables.naoh.overenikoncentrace.OvereniKoncentrace(this.main, new Form("ověření koncentrace NaOH")));
                    break;
  	  }
  	}
  }

    public void fromDataStream(DataInputStream din) throws Throwable {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void toDataStream(DataOutputStream dout) throws Throwable {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
