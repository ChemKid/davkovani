/**************************************************************************************************
  davkovani/Davkovani.java
  @author: Jan Chára
  date: 9.7.2009
  description: MIDlet aplikace
**************************************************************************************************/

package davkovani.displayables.aktiphos.aktiphoslist;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.microedition.lcdui.*;
import davkovani.displayables.mylist.*;


public class AktiphosList extends MyList
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/


/**************************************************************************************************
 * constructors
**************************************************************************************************/

    public AktiphosList(davkovani.Davkovani main, List d) {
        super(main, d);
        this.addCommand(this.cBack);
  	this.setCommandListener(this);

  	this.append("nastavení zdvihu", this.imagePart);
  	this.append("nápověda", this.imagePart);
    }


/**************************************************************************************************
 * method main:
**************************************************************************************************/

    public static void main(String[] args) {

    }
    

/**************************************************************************************************
 * overriding of parent or overloaded methods
**************************************************************************************************/


/**************************************************************************************************
 * own methods:
**************************************************************************************************/

  
/**************************************************************************************************
 * interface implemented methods:
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
                    this.main.display(new davkovani.displayables.aktiphos.nastavenizdvihu.NastaveniZdvihu(this.main, new Form("nastavení zdvihu")));
                    //this.main.aktiphosNastaveniZdvihu.setCurrentItem(0);
                    break;
                case 1:
                    this.main.display(new davkovani.displayables.aktiphos.navod.Navod(this.main, new Form("Aktiphos - návod")));
                    //this.main.aktiphosNavod.setCurrentItem(0);
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