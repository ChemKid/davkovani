/**************************************************************************************************
  davkovani/displayables/MyForm.java;
  @author: Jan Chara
  date: 
  description: 
**************************************************************************************************/

package davkovani.displayables;

import davkovani.io.Serializable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.microedition.lcdui.*;


public abstract class MyFormNew extends Form implements MyDisplayableNew, Serializable
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/

    public static final Font DEFAULT_FONT = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
    //protected Command OKCommand;


/**************************************************************************************************
 * constructors
**************************************************************************************************/

    public MyFormNew(String name) {
        super(name);
        //this.OKCommand = new Command("OK", Command.OK, 2);
        //this.addCommand(this.OKCommand);
        this.addCommand(MyDisplayableNew.OK_COMMAND);
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

    protected abstract void readForm();

    protected abstract void reset();

/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/

    public void finalize() {
        this.readForm();
        //this.write();
    }

    public abstract void perform(Command c);

    public void fromByteArray(byte[] data) throws Throwable {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public byte[] toByteArray() throws Throwable {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void fromDataStream(DataInputStream din) throws Throwable {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void toDataStream(DataOutputStream dout) throws Throwable {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}