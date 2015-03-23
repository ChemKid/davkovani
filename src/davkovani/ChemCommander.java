/**************************************************************************************************
  davkovani/ChemCommander.java
  @author: Jan Chara
  date: 
  description: MIDlet aplikace
**************************************************************************************************/

package davkovani;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;


/**
 *
 * @author honza
 */
public class ChemCommander extends DisplayableManager implements CommandListener
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/

/**************************************************************************************************
 * constructors
**************************************************************************************************/

    public ChemCommander() {
    }


/**************************************************************************************************
 * main method:
     * @param args
**************************************************************************************************/

    public static void main(String[] args) {
        System.out.println("MIDlet says hello!");
    }

/**************************************************************************************************
 * overriding of parent or overloaded methods
**************************************************************************************************/

    protected void initialize() {
        Display.getDisplay(this).setCurrent(new Form("ChemCommander " + this.getMIDletVersion()));
        //this.displayableManager.startMIDlet();
    }
    
    public void display(int id) {
        switch(id) {
            case 0: 
                //this.display(new MyDisplayable(new Form("ověření dávky síranu")));
                break;
        }
    }
    
/**************************************************************************************************
 * own methods:
**************************************************************************************************/

/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/
    
    public void commandAction(Command c, Displayable d) {
        System.out.println("byl jsem zavolan");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}