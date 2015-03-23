/**************************************************************************************************
  davkovani/displayables/koagulant/koagulantlist/KoagulantList.java
  @author: Jan Chara
  date: 9.7.2009
  description: 
**************************************************************************************************/

package davkovani.displayables.koagulant.koagulantlist;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.microedition.lcdui.*;
import davkovani.displayables.mylist.*;


public class KoagulantList extends MyList
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/


/**************************************************************************************************
 * constructors
**************************************************************************************************/

    public KoagulantList(davkovani.Davkovani main, List d) {
        super(main, d);
  	this.addCommand(this.cBack);
  	this.setCommandListener(this);

  	this.append("ověření dávky", this.imagePart);
  	this.append("nastavení zdvihu", this.imagePart);
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
                    this.main.display(new davkovani.displayables.koagulant.overenidavky.OvereniDavky(this.main, new Form("ověření dávky síranu")));
                    break;
  	  	case 1: 
                    this.main.display(new davkovani.displayables.koagulant.nastavenizdvihu.NastaveniZdvihu(this.main, new Form("nastavení zdvihu čerpadla")));
                    break;
                case 2: 
                    this.main.display(new davkovani.displayables.koagulant.overenikoncentrace.OvereniKoncentrace(this.main, new Form("ověření koncentrace síranu")));
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