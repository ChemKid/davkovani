/**************************************************************************************************
  davkovani/displayables/ro/list/ROList.java
  @author: Jan Chára
  date: 9.7.2009
  description: 
**************************************************************************************************/

package davkovani.displayables.ro.list;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.microedition.lcdui.*;
import davkovani.displayables.mylist.*;


public class ROList extends MyList
{
/**************************************************************************************************
  deklarace prom�nn�ch:
**************************************************************************************************/


/**************************************************************************************************
  konstruktory:
**************************************************************************************************/

    public ROList(davkovani.Davkovani main, List d) {
        super(main, d);
  	this.addCommand(this.cBack);
  	this.setCommandListener(this);

        this.append("SDI", this.imagePart);
  	this.append("biocid dávka", this.imagePart);
  	this.append("antiscalant dávka", this.imagePart);
  	//this.append("volba dávky", this.imagePart);
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
                    this.main.display(new davkovani.displayables.ro.SDI(this.main, new Form("SDI")));
                    //this.main.alkalizaceOvereniDavky.setCurrentItem(0);
                    break;
                case 1:
                    this.main.display(new davkovani.displayables.ro.Biocid(this.main, new Form("ověření dávky biocidu")));
                    //this.main.alkalizaceOvereniDavky.setCurrentItem(0);
                    break;
  	  	case 2: 
                    this.main.display(new davkovani.displayables.ro.Antiscalant(this.main, new Form("ověření dávky antiscalantu")));
                    //this.main.alkalizaceNastaveniZdvihu.setCurrentItem(0);
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