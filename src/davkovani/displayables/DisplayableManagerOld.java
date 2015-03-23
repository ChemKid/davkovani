/**************************************************************************************************
  davkovani/displayables/DisplayableManager.java
  @author: Jan Chara
  date: 9.7.2009
  description: manager of displayables
**************************************************************************************************/

package davkovani.displayables;

import davkovani.Davkovani;
import davkovani.displayables.mydisplayable.MyDisplayable;
import davkovani.displayables.myform.MyForm;
import davkovani.displayables.outputform.OutputForm;
import davkovani.menu.Menu;
import java.util.Enumeration;
import java.util.Vector;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.midlet.MIDlet;


public class DisplayableManagerOld implements CommandListener
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/

    public static final int MAIN_MENU;
    
    private MIDlet midlet;
    private Display display;
    private Vector displayableList;
    private MyDisplayable displayable;
    private MyDisplayable fromDisplayable;
    public InfoForm infoForm = new InfoForm("info form");
    private final static Vector displayableManager;
    
    static {
        int i = 0;
        MAIN_MENU = i++;
        
        displayableManager = new Vector();
    }


/**************************************************************************************************
 * constructors
**************************************************************************************************/

    /**
     * creates an instance of DisplayableManager
     * @param midlet MIDlet attributed to this DisplayableManager by function @see davkovani.displayables.DisplayableManager#getDisplayableManager(MIDlet)
     */
    private DisplayableManagerOld(MIDlet midlet) {
        this.midlet = midlet;
        this.display = Display.getDisplay(midlet);
        this.displayableList = new Vector();
        DisplayableManagerOld.add(this);
    }


/**************************************************************************************************
 * main method:
**************************************************************************************************/

    public static void main(String[] args) {
    }


/**************************************************************************************************
 * overriding of parent or overloaded methods
**************************************************************************************************/


/**************************************************************************************************
 * own methods:
**************************************************************************************************/

    public static DisplayableManagerOld getDisplayableManager(final MIDlet midlet) {
        DisplayableManagerOld dm;
        for (Enumeration e = DisplayableManagerOld.displayableManager.elements(); e.hasMoreElements();) {
            dm = (DisplayableManagerOld)e.nextElement();
            if (midlet == dm.getMIDlet()) {
                return dm;
            }
        }
        dm = new DisplayableManagerOld(midlet);
        return dm;
    }
    
    public static DisplayableManagerOld getDiplayableManager(final MyDisplayable d) throws Throwable {
        DisplayableManagerOld dm;
        for (Enumeration e = DisplayableManagerOld.displayableManager.elements(); e.hasMoreElements();) {
            dm = (DisplayableManagerOld)e.nextElement();
            if (dm.displayableList.contains(d)) {
                return dm;
            }
        }
        throw new Throwable(d + " has no DisplayableManager");
    }
    
    private static void add(final DisplayableManagerOld dm) {
        DisplayableManagerOld.displayableManager.addElement(dm);
    }
    
    private void add(final MyDisplayable d) {
        if (this.displayableList.contains(d)) {
            System.out.println(d + " je jiz ulozen v DisplayableManageru");
            return;
        }
        this.displayableList.addElement(d);
        System.out.println(d + " byl ulozen v DisplayableManageru");
    }
    
    public void startMIDlet() { /// zajistit aby funkce nemohla být volána jinou třídou než MIDlet (možno zakomponovat přímo do třídy MIDlet)
        if (this.midlet instanceof Davkovani) {
            //this.display.setCurrent(((Davkovani)midlet).menuList.getDisplayable());  // 00
            this.display(8);
        }
        else {
            System.out.println("pro midlet " + this.midlet + " nejsou definovány žádné procesy!");
        }
    }
    
    public void display(MyDisplayable displayable, MyDisplayable fromDisplayable) {
        this.displayable = displayable;
        this.fromDisplayable = fromDisplayable;
    }
    
    public void display(MyDisplayable displayable) {
        this.add(displayable);
        if (this.midlet instanceof Davkovani) {
            this.display.setCurrent(displayable.getDisplayable());
        }
        //Display.getDisplay(this.midlet).setCurrent(displayable.getDisplayable());
    }
    
    /**
     * creates (if not exists already) and display on screen a displayable given by index
     * @param index 
     */
    public void display(int index) {
        switch(index) {
            case 0: 
                this.display(new davkovani.displayables.koagulant.overenidavky.OvereniDavky((Davkovani)this.midlet, new Form("ověření dávky síranu")));
                break;
            case 1: 
                this.display(new davkovani.displayables.koagulant.nastavenizdvihu.NastaveniZdvihu((Davkovani)this.midlet, new Form("nastavení zdvihu čerpadla")));
                break;
            case 2: 
                this.display(new OutputForm((Davkovani)this.midlet, new Form("výpis")));
                break;
            case 3: 
                this.display(new OutputForm((Davkovani)this.midlet, new Form("výpis")));
                break;
            case 4:
                this.display(new davkovani.displayables.koagulant.koagulantlist.KoagulantList((Davkovani)this.midlet, new List("síran", Choice.IMPLICIT)));
                System.out.println("byl sem tu fantomas");
                break;
            case 5:
                this.display(new davkovani.displayables.alkalizace.alkalizacelist.AlkalizaceList((Davkovani)this.midlet, new List("alkalizace", Choice.IMPLICIT)));
                break;
            case 6:
                this.display(new davkovani.displayables.mikropisek.mikropiseklist.MikropisekList((Davkovani)this.midlet, new List("mikropísek", Choice.IMPLICIT)));
                break;
            case 7:
                this.display(new davkovani.displayables.flokulant.flokulantlist.FlokulantList((Davkovani)this.midlet, new List("POF", Choice.IMPLICIT)));
                break;
            case 8:
                this.display(new MenuListNew((Davkovani)this.midlet, Menu.DEFAULT_MENU.toMenuList()));
                break;
            case 9:
            case 10:
            case 11:
            case 12:
        }
    }
    
    private MIDlet getMIDlet() {
        return this.midlet;
    }

    public void addDisplayable(final MyDisplayable d) {
        if (d == this.displayable) {
            return;
        }
        if (null == this.displayable) {            
        }
        else {
            if (this.displayable instanceof MyForm) {
                try {
                    ((MyForm)this.displayable).finalize();
                } catch (Throwable t) {
                    /// ...
                }
            }
            this.displayable = null;
            System.gc();
        }
        this.displayable = d;
    }

    public void finalize() {
        if (null == this.displayable) {
            return;
        }
        else {
            if (this.displayable instanceof MyForm) {
                try {
                    ((MyForm)this.displayable).finalize();
                } catch (Throwable t) {
                    /// ...
                }
            }
            this.displayable = null;
            System.gc();
        }
    }
    
    /*private Display getDisplay() {
        return Display.getDisplay(this.d);
    }*/


/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/
    
    public void commandAction(Command c, Displayable d) {
        if (d instanceof MyDisplayableNew) {
            System.out.println(d + " je instancí MyDisplayable");
            ((MyDisplayableNew)d).perform(c);
        }
        else {
            System.out.println(d + " není instancí MyDisplayable");
        }
    }
}