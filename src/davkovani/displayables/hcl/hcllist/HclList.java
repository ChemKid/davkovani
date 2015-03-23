/**************************************************************************************************
  davkovani/displayables/hcl/hcllist/HclList.java
  @author: Jan Chara
  date: 
  description: 
**************************************************************************************************/

package davkovani.displayables.hcl.hcllist;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.microedition.lcdui.*;
import davkovani.displayables.mylist.*;


public class HclList extends MyList
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/


/**************************************************************************************************
 * constructors
**************************************************************************************************/

  public HclList(davkovani.Davkovani main, List d) {
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
                    this.main.display(new davkovani.displayables.hcl.overenikoncentrace.OvereniKoncentrace(this.main, new Form("ověření koncentrace HCl")));
                    //this.main.hclOvereniKoncentrace.setCurrentItem(0);
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
