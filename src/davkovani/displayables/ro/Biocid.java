/**************************************************************************************************
 * davkovani/displayables/ro/Biocid.java
 * @author: Jan Chara
 * date: 
 * description: 
**************************************************************************************************/

package davkovani.displayables.ro;

import javax.microedition.lcdui.*;
import davkovani.displayables.myform.*;
import davkovani.myitems.timeritem01.*;
import davkovani.myitems.actionlistener.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;


public class Biocid extends MyForm implements ItemStateListener, ActionListener
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/

    private final float D = 1.16f; // hustota biocidu
    private final String INFO_START_TEXT = "měření času";
    private String vykonROText;
    private String objemText;

    private final TextField vykonROTextField;
    private final TextField objemTextField;
    private final TimerItem01 timeTimerItem;
    private final StringItem timeStringItem;
    private float vykonRO;
    private float objem;
    private long startTime;
    private int time;


/**************************************************************************************************
 * constructors
**************************************************************************************************/

    public Biocid(davkovani.Davkovani main, Form d) {
        super(main, d, "biocid oveření dávky");
  	this.addCommand(this.cOk);
  	this.addCommand(this.cBack);
        this.addCommand(this.clearFormCommand);
  	this.setCommandListener(this);
  	int tfW = this.screenWidth/3;
  	int siW = tfW*2;

        /*** vytvoření prvků třídy Item ***/
  	this.vykonROTextField = new TextField("průtok FV [m3/h]", this.vykonROText, 10, TextField.DECIMAL);
  	this.objemTextField = new TextField("vydávkovaný objem [ml]", this.objemText, 4, TextField.DECIMAL);
  	this.timeTimerItem = new TimerItem01("měření času", this.screenWidth);
        this.timeStringItem = new StringItem(null, this.INFO_START_TEXT);
  	this.timeTimerItem.setBackgroundColor(0xcc6600);
  	this.timeTimerItem.setActionListener(this);
  	
        /*** layout settings ***/

        /*** pripojeni prvku k Formu ***/
        this.append(this.vykonROTextField);
        this.append(this.objemTextField);
        this.append(this.timeTimerItem);
        //this.append(this.timeStringItem);

        if (this.startTime > 0) {
            this.timeTimerItem.setStartTime(this.startTime);
            this.timeTimerItem.start();
        }
        this.time = -1;
        this.setItemStateListener();
    }


/**************************************************************************************************
 * main method:
**************************************************************************************************/

    public static void main(String[] args) {
    }
    

/**************************************************************************************************
 * overriding of parent or overloaded methods
**************************************************************************************************/

    protected void readForm() {
        this.vykonROText = this.vykonROTextField.getString();
        this.objemText = this.objemTextField.getString();
        this.startTime = this.timeTimerItem.getStartTime();
        this.time = this.timeTimerItem.getTime();
    }
    

/**************************************************************************************************
 * own methods:
**************************************************************************************************/

    protected void reset() {
        this.vykonROTextField.setString(null);
  	this.objemTextField.setString(null);
  	this.timeTimerItem.reset();
        this.startTime = this.timeTimerItem.getStartTime();
        this.timeStringItem.setText(this.INFO_START_TEXT);
  	this.vykonRO = -1;
  	this.objem = -1;
  	this.time = -1;
    }

    public float vypocti() throws Throwable {
        this.readForm();
        if (this.vykonROText.equals(null) || this.vykonROText.equals("")) {
            throw new Throwable("Nebyl zadán výkon RO");
        }
        else {
            try {
                this.vykonRO = Float.parseFloat(this.vykonROText);
            } catch (Throwable t) {
                throw new Throwable("Výkon RO zadán v neplatném tvaru");
            }
        }
        if (this.objemText.equals(null) || this.objemText.equals("")) {
            throw new Throwable("Nebyl zadán vydávkovaný objem");
        }
        else {
            try {
                this.objem = Float.parseFloat(this.objemText);
            } catch (Throwable t) {
                throw new Throwable("Objem zadán v neplatném tvaru");
            }
        }

        /* kontrola zda hodnoty jsou prvky definičního oboru */
        if (0 > this.objem || 0 > this.vykonRO) {
            throw new Exception("zadané hodnoty nesmějí být záporné!\n");
        }
        if (0 == this.vykonRO) {
            throw new Exception("výkon RO nesmi být roven 0!\n");
        }
        if (this.time < 0) {
            throw new Exception("chyba v měření času! je chybou SW!\n");
        }
        /* výpočet */
        return (36e5f*this.objem*this.D)/(this.vykonRO*this.time);
    }


/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/

    public void commandAction(Command c, Displayable d) {
        if (this.d == d) {
            if (this.cOk == c) {
                String s;
                float f;
                try {
                    f = this.vypocti();
                    String resultAlertString = "dávka = " + this.formatValue(f,1) + " mg/l\n";
                    s = this.d.getTitle() + "\nvýkon: " + this.vykonRO + " m3/h\nobjem: " + this.objem
          	    + " ml\nčas: " + this.formatValue(this.time/1000f,1) + " s\n--> " + resultAlertString;
                    this.main.writeResult(s);
                    this.showResultAlert(resultAlertString);
                } catch (Throwable t) {
                    this.showErrorAlert(t.getMessage());
                }
            }
            else if (this.cBack == c) {
                this.main.display(this.main.ROList);
            }
            else if (this.clearFormCommand == c) {
                this.reset();
            }
        }
        else if (this.resultAlert == d) {
            System.out.println("command from resultAlert!!");
            if (this.doneCommand == c) {
                this.main.display(this);
                this.reset();
            }
            else if (this.continueCommand == c) {
                this.main.display(this);
            }
        }
    }

    public void itemStateChanged(Item item) {
        if (this.timeTimerItem == item) {
            if (this.timeTimerItem.isRunning()) {
                this.timeStringItem.setText("měří se čas ...");
            }
            else {
                this.timeStringItem.setText("změřen čas: " + this.timeTimerItem.getTime());
            }
        }
    }

    public void actionPerformed(CustomItem customItem) {
        if (this.timeTimerItem == customItem) {
            this.time = this.timeTimerItem.getTime();
        }
    }

    public void fromDataStream(DataInputStream din) throws Throwable {
        this.vykonROText = din.readUTF();
        //this.main.writeResult("výkon čiřiče = " + ((null == this.vykonCiriceText) ? "null" : this.vykonCiriceText) + "\n");
        this.objemText = din.readUTF();
        //this.main.writeResult("hladina 1 = " + ((null == this.hladina1Text) ? "null" : this.hladina1Text) + "\n");
        this.startTime = din.readLong();
        //this.main.writeResult("čas = " + this.startTime + "\n");
    }

    public void toDataStream(DataOutputStream dout) throws Throwable {
        dout.writeUTF((null == this.vykonROText) ? "" : this.vykonROText);
        //this.main.writeResult("výkon čiřiče = " + ((null == this.vykonCiriceText) ? "null" : this.vykonCiriceText) + "\n");
        dout.writeUTF((null == this.objemText) ? "" : this.objemText);
        //this.main.writeResult("hladina 1 = " + ((null == this.hladina1Text) ? "null" : this.hladina1Text) + "\n");
        dout.writeLong(this.startTime);
        //this.main.writeResult("čas = " + this.startTime + "\n");
    }
}