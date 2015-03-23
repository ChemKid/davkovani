/**************************************************************************************************
 * davkovani/displayables/errorform/ErrorForm.java
 * @author: Jan Chara
 * date: 9.7.2009
 * description: form intended to append error messages
**************************************************************************************************/

package davkovani.displayables.errorform;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.microedition.lcdui.*;
import davkovani.displayables.myform.*;


public class ErrorForm extends MyForm
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/



/**************************************************************************************************
 * constructors
**************************************************************************************************/

    public ErrorForm(davkovani.Davkovani main, Form d) {
        super(main, d);
  	this.addCommand(this.cBack);
  	this.setCommandListener(this);
    }


/**************************************************************************************************
 * main method:
**************************************************************************************************/

    public static void main(String[] args) {

    }
    

/**************************************************************************************************
 * overriding of parent or overloaded methods
**************************************************************************************************/

    protected void reset() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    protected void readForm() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


/**************************************************************************************************
 * own methods:
**************************************************************************************************/



/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/

    public void commandAction(Command c, Displayable d) {
        if (this.d == d) {
            if (this.cBack == c) {
                this.main.display(this.main.getPreviousDisplayable());
            }
        }
    }

    public void itemStateChanged(Item item) {
    }

    public void fromDataStream(DataInputStream din) throws Throwable {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void toDataStream(DataOutputStream dout) throws Throwable {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}