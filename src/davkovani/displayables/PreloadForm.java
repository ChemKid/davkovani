/**************************************************************************************************
  davkovani/displayables/PreloadForm.java;
  @author: Jan Chara
  date: 
  description: 
**************************************************************************************************/

package davkovani.displayables;

import davkovani.myitems.actionlistener.*;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;


public class PreloadForm extends Form implements ActionListener
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/

    protected Command doneCommand;


/**************************************************************************************************
 * constructors
**************************************************************************************************/

    public PreloadForm() {
        super("Welcome!");
        
  	this.doneCommand = new Command("done", Command.EXIT, 1);
        this.addCommand(this.doneCommand);
    }


/**************************************************************************************************
 * main method:
**************************************************************************************************/

    /**
     * 
     * @param args 
     */
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

    public void commandAction(final Command c, final MyDisplayableNew d) {
        if (this == d) {
            if (this.doneCommand == c) {
                //this.main.display(this);
            }
        }
    }

    public void actionPerformed(CustomItem customItem) {
    }
}