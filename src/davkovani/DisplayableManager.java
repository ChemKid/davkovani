/**************************************************************************************************
  davkovani/DisplayableManager.java
  @author: Jan Chara
  date: 
  description: MIDlet aplikace, spolecny predek pro MIDlety v teto MIDlet suite
**************************************************************************************************/

package davkovani;

import davkovani.displayables.mydisplayable.*;
import java.util.Vector;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

/**
 *
 * @author honza
 */
public abstract class DisplayableManager extends MIDlet implements CommandListener
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/

    private boolean pause;
    private long startTime;
    private Vector displayableList;
    private final static Vector displayableManagerList;
    
    static {
        displayableManagerList = new Vector();
    }


/**************************************************************************************************
 * constructors
**************************************************************************************************/

    public DisplayableManager() {
        this.pause=false;
        displayableList = new Vector();
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

    /**
     * při volání metody probíhá kontrola zda jsou nainstalovány potřebné proměnné, pokud ne je volána metoda install()
     * @throws MIDletStateChangeException
     */
    public void startApp() throws MIDletStateChangeException {
        if (this.pause) {
            this.pause = false;
            return;
        }
        this.startTime = System.currentTimeMillis();
        this.initialize();
    }

    /**
     *
     * @param k
     */
    public void destroyApp(boolean k) {
        System.out.println("doba spusteni MIDletu: " + (System.currentTimeMillis() - this.startTime) + " ms");
        //this.displayableManager.finalize();
    }

    /**
     *
     */
    public void pauseApp() {
        if (!this.pause) {
            this.pause=true;
        }
    }


/**************************************************************************************************
 * own methods:
**************************************************************************************************/

    protected abstract void initialize();
    
    public abstract void display(int id);
    
    public void finalize() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /*public static MIDlet getMIDlet(MyDisplayable md) {
        return new MIDlet();
    }*/
    
    public String getMIDletVersion() {
        return this.getAppProperty("MIDlet-Version");
    }
    
    /**
     * will return the time when first started actual session
     * @return time when the actual session of this MIDlet started
     */
    public long getStartTime() {
        return this.startTime;
    }
    
    /**
     * ukončí MIDlet
     */
    public void exit() {
        this.destroyApp(true);
        this.notifyDestroyed();
    }

    /**
     *
     * @param md
     */
    public void display(MyDisplayable md) {
        if (null == md) {
            return;
        }
        Display.getDisplay(this).setCurrent(md.getDisplayable());
        try {
            md.setMIDlet(this);
        } catch (Throwable t) {
            System.out.println("Cannot assign MIDlet to MyDisplayable!");
            System.out.println(t.getMessage());
        }
    }

/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/
    
    public void commandAction(Command c, Displayable d) {
        System.out.println("byl jsem zavolan");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}