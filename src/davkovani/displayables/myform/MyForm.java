/**************************************************************************************************
  davkovani/displayables/myform/MyForm.java;
  @author: Jan Chara
  date: 9.7.2009
  description: parent class
**************************************************************************************************/

package davkovani.displayables.myform;

import javax.microedition.lcdui.*;
import davkovani.displayables.mydisplayable.*;
import davkovani.myitems.actionlistener.*;
import javax.microedition.rms.RecordStore;


public abstract class MyForm extends MyDisplayable implements ItemStateListener, ActionListener
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/

    public final String RECORD_STORE_NAME = "neco";
    protected Font font;
    protected int screenWidth;
    protected boolean isActive;
    private String recordStoreName;
    protected Alert errorAlert;
    protected Alert resultAlert;
    protected Command continueCommand;
    protected Command clearFormCommand;
    protected Command doneCommand;


/**************************************************************************************************
 * constructors
**************************************************************************************************/

    public MyForm(davkovani.Davkovani main, Form d) {
        this(main, d, null);
    }

    
    /**
     * 
     * @param main hlavni MIDlet
     * @param d 
     * @param recordStoreName nazev Recorstore kde jsou uchovavany promenne tridy
     */
    public MyForm(davkovani.Davkovani main, Form d, String recordStoreName) {
        super(d);
        this.isActive = false;
        /////////////////////
        if (null == recordStoreName) {
            /// nutno ošetřit!
        }
        else {
            this.recordStoreName = recordStoreName;
            try {
                this.read();
                //this.main.writeResult("úspěšně načteno!");
            } catch (Throwable t) {
                //this.main.writeResult("chyba při načítání record store!");
            }
        }
        //////////////////////
  	this.font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
  	this.screenWidth = this.d.getWidth();
        this.continueCommand = new Command("continue", Command.BACK, 1);
        this.clearFormCommand = new Command("reset", Command.STOP, 2);
        this.doneCommand = new Command("done", Command.BACK, 2);

        /*** vytvoření prvků třídy Item ***/
        this.errorAlert = new Alert("error!");
        this.errorAlert.setType(AlertType.ERROR);
        this.errorAlert.setTimeout(Alert.FOREVER);
        this.resultAlert = new Alert(this.getClass().getName());
        this.resultAlert.setType(AlertType.INFO);
        this.resultAlert.addCommand(this.continueCommand);
        this.resultAlert.addCommand(this.doneCommand);
        this.resultAlert.setCommandListener(this);
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

    public int append(String s) {
        return ((Form)this.d).append(s);
    }

    public int append(Item i) {
        return ((Form)this.d).append(i);
    }

    public void setItemStateListener() {
        ((Form)this.d).setItemStateListener(this);
    }

    public void showErrorAlert(final String errorMessageString) {
        this.errorAlert.setString(errorMessageString);
        this.main.getDisplay().setCurrent(this.errorAlert);
        // dodělat: připojit serrorMessageString k errorFormu
    }

    public void showResultAlert(final String resultString) {
        this.resultAlert.setString(resultString);
        this.main.getDisplay().setCurrent(this.resultAlert);
    }

    public Item get(int itemNum) {
        return ((Form)this.d).get(itemNum);
    }

    public void deleteAll() {
        ((Form)this.d).deleteAll();
    }

    public void delete(int itemNum) {
        ((Form)this.d).delete(itemNum);
    }

    public int size() {
        return ((Form)this.d).size();
    }

    public void insert(int itemNum, Item item) {
        ((Form)this.d).insert(itemNum, item);
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void finalize() throws Throwable {
        this.readForm();
        this.write();
    }

    //protected abstract void setDefault();

    protected abstract void readForm();

    protected abstract void reset();

    public void read() throws Throwable {
        RecordStore myRecordStore = null;
        byte[] data = null;
        int numRecords;
        try {
            myRecordStore = RecordStore.openRecordStore(this.recordStoreName, false);
            //this.main.writeResult("record store " + myRecordStore.getName() + " otevřen pro čtení.");
            //this.main.writeResult("počet záznamů: " + myRecordStore.getNumRecords());
            numRecords = myRecordStore.getNumRecords();
            if (0 == numRecords) {
                //this.main.writeResult("record store je prázdný!");
            }
            else {
                data = myRecordStore.getRecord(1);
            }
            this.fromByteArray(data);
            //this.main.writeResult("záznam nacten!");
        } catch (Throwable t) {
            //this.main.writeResult("nelze načíst data!" + t);
        } finally {
            try {
                myRecordStore.closeRecordStore();
                //this.main.writeResult("record store uzavřen pro čtení.");
            } catch (Throwable t) {
                //this.main.writeResult("nelze uzavřít record store pro čtení!");
            }
        }
    } // read()


    public void write() throws Throwable {
        RecordStore myRecordStore = null;
        byte[] data = null;
        int numRecords;
        try {
            myRecordStore = RecordStore.openRecordStore(this.recordStoreName, true);
            //this.main.writeResult("record store " + myRecordStore.getName() + " otevřen pro zápis.");
            //this.main.writeResult("počet záznamů: " + myRecordStore.getNumRecords());
            data = this.toByteArray();
            //this.main.writeResult("data:" + data);
            numRecords = myRecordStore.getNumRecords();
            if (0 == numRecords) {
                myRecordStore.addRecord(data,0,data.length);
            }
            else {
                myRecordStore.setRecord(1,data,0,data.length);
            }
            //this.main.writeResult("záznam uložen!");
            //this.main.writeResult("počet záznamů: " + myRecordStore.getNumRecords());
        } catch (Throwable t) {
            //this.main.writeResult("nelze uložit data!" + t);
        } finally {
            try {
                myRecordStore.closeRecordStore();
                //this.main.writeResult("record store uzavřen pro zápis.");
            } catch (Throwable t) {
                //this.main.writeResult("nelze uzavřít record store pro zápis!");
            }
        }
    } // write(String)
    

/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/

    public void commandAction(final Command c, final Displayable d) {
        if (this.resultAlert == d) {
            if (this.doneCommand == c) {
                this.main.display(this);
                this.reset();
            }
            else if (this.continueCommand == c) {
                this.main.display(this);
            }
        }
    }

    public void actionPerformed(CustomItem customItem) {
    }
}