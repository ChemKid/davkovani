/**************************************************************************************************
  davkovani/displayables/mydisplayable/MyDisplayable.java
  @author: Jan Chára
  date: 9.7.2009
  description: rodičovská třída všech elementů zobrazitelných třídou Davkovani
**************************************************************************************************/

package davkovani.displayables.mydisplayable;

import davkovani.*;
import davkovani.displayables.storeable.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Vector;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;


public abstract class MyDisplayable implements CommandListener, Storeable
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/

    protected Displayable d;
    private MIDlet midlet;
    protected Davkovani main;
    protected Command cOk;
    protected Command cBack;
    protected Command cExit;
    protected static final Vector myDisplayableList;
    
    static {
        myDisplayableList = new Vector();
    }


/**************************************************************************************************
 * constructors
**************************************************************************************************/

    public MyDisplayable(Displayable d) {
        this.d = d;
        this.cOk = new Command("ok", Command.OK, 1);
        this.cBack = new Command("back", Command.BACK, 2);
        this.cExit = new Command("exit", Command.EXIT, 2);
    }


/**************************************************************************************************
 * main method:
**************************************************************************************************/

  
/**************************************************************************************************
 * overriding of parent or overloaded methods
**************************************************************************************************/


/**************************************************************************************************
 * own methods:
**************************************************************************************************/

    public Displayable getDisplayable() {
        return this.d;
    }
    
    public void setMIDlet(MIDlet midlet) throws Throwable {
        if (null == midlet) {
            this.midlet = midlet;
        }
        else if (midlet == this.midlet) {
            return;
        }
        else {
            throw new Throwable("MIDlet is already assigned to this MyDisplayable!");
        }
    }

    public void addCommand(Command c) {
        this.d.addCommand(c);
    }

    public void setCommandListener(CommandListener cl) {
        this.d.setCommandListener(cl);
    }

    public int getWidth() {
        return this.d.getWidth();
    }

    public void setFirstSelected() {
        if (this.d instanceof List) {
            ((List)this.d).setSelectedIndex(0, true);
        }
    }

    public void setCurrentItem(int itemNum) {
        if (this.d instanceof Form) {
            try {
                //this.main.getDisplay().setCurrentItem(((Form)this.d).get(itemNum));
            } catch (Throwable t) {
                return; // pokud neni ve formu zadny item tak funkce nic nedela --> zapis do error log
            }
        }
    }

    public String formatValue(float f, int precision) {
        String s = Float.toString(f);
        String zbytek;
        int i = s.indexOf('.');
        if (0>precision) {
            precision = 0;
        }
        if (-1 == i) {
            zbytek = null;
        }
        else {
            zbytek = s.substring(i+1, s.length());
            s = s.substring(0, i);
        }
        if (0==precision) {
            return s;
        }
        if (zbytek.length() > precision) {
            zbytek = zbytek.substring(0, precision);
        }
        precision -= zbytek.length();
        for (int x=0; x<precision; x++) {
            zbytek += "0";
        }
        return s + "." + zbytek;
    }

    //protected abstract void finalize() throws Throwable;
  
  
/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/

    public void fromByteArray(byte data[]) throws Throwable {
        ByteArrayInputStream bin = new ByteArrayInputStream(data);
        DataInputStream din = new DataInputStream(bin);
        this.fromDataStream(din);
        din.close();
    }

    public byte[] toByteArray() throws Throwable {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        DataOutputStream dout = new DataOutputStream(bout);
        this.toDataStream(dout);
        dout.close();
        return bout.toByteArray();
    }

    public abstract void fromDataStream(DataInputStream din) throws Throwable;

    public abstract void toDataStream(DataOutputStream dout) throws Throwable;

}