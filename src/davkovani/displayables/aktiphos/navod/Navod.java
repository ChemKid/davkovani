/**************************************************************************************************
  davkovani/Navod.java
  @author: Jan Ch�ra
  date: 9.7.2009
  description: MIDlet aplikace
**************************************************************************************************/

package davkovani.displayables.aktiphos.navod;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.microedition.lcdui.*;
import davkovani.displayables.myform.*;
import davkovani.myitems.actionlistener.*;


public class Navod extends MyForm implements ItemStateListener, ActionListener
{
/**************************************************************************************************
  deklarace prom�nn�ch:
**************************************************************************************************/
 

/**************************************************************************************************
  konstruktory:
**************************************************************************************************/

  public Navod(davkovani.Davkovani main, Form d) {
  	super(main, d);
        this.append("\"Požadovaná koncentrace\" musí být rovna hodnotě uvedené v souboru R:\\\\CHEMIE\\INFORM\\AKTIPHOS.xls jako optimalní dávka." +
                "Do pole \"aktualni koncentrace\" se zadá poslední naměřená hodnota koncentrace AKTIPHOSU v chladicim okruhu B4, která je uvedena ve stejném souboru.");
  	this.addCommand(this.cBack);
  	this.setCommandListener(this);
  }


/**************************************************************************************************
  p�ekryt� zd�d�n� nebo p�et�en� zd�d�n� metody:
**************************************************************************************************/

    protected void reset() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected void readForm() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


/**************************************************************************************************
  vlastn� metody:
**************************************************************************************************/


/**************************************************************************************************
  metody implementovan� p�es rozhran�:
**************************************************************************************************/

  public void commandAction(Command c, Displayable d) {
  	if (this.d == d) {
  	  if (this.cBack == c) {
  	  	this.main.display(this.main.aktiphosList);
  	  }
  	}
  }

  public void itemStateChanged(Item item) {
  }

  public void actionPerformed(CustomItem customItem) {
  }

    public void fromDataStream(DataInputStream din) throws Throwable {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void toDataStream(DataOutputStream dout) throws Throwable {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}