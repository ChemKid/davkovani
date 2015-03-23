/**************************************************************************************************
  davkovani/displayables/alkalizace/overenidavky/OvereniDavky.java
  @author: Jan Chára
  date: 9.7.2009
  description: formulář pro ověření dávky NaOH
**************************************************************************************************/

package davkovani.displayables.alkalizace.overenidavky;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.microedition.lcdui.*;
import davkovani.displayables.myform.*;
import davkovani.myitems.timeritem01.*;
import davkovani.myitems.actionlistener.*;


public class OvereniDavky extends MyForm implements ItemStateListener, ActionListener
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/

    private final float W = 0.5f; // hmotnostní zlomek NaOH
    private final float D = 1.525f; // hustota 50% NaOH
    private String vykonCiriceText;
    private String objemText;
    
    private TextField vykonCiriceTextField;
    private TextField objemTextField;
    private TimerItem01 timeTimerItem;
    private float vykonCirice;
    private float objem;
    private long startTime;
    private int time;


/**************************************************************************************************
 * constructors
**************************************************************************************************/

    public OvereniDavky(davkovani.Davkovani main, Form d) {
        super(main, d, "NaOH_overeni_davky");
        this.addCommand(this.cOk);
        this.addCommand(this.cBack);
        this.addCommand(this.clearFormCommand);
        this.setCommandListener(this);
        
        /*** vytvoření prvků třídy Item ***/
        this.vykonCiriceTextField = new TextField("výkon čiřiče [m3/h]", this.vykonCiriceText, 4, TextField.DECIMAL);
        this.objemTextField = new TextField("vydávkovaný objem [ml]", this.objemText, 4, TextField.DECIMAL);
        this.timeTimerItem = new TimerItem01("měření času", this.screenWidth);
        this.timeTimerItem.setActionListener(this);
        this.timeTimerItem.setBackgroundColor(0x663366);
        this.setItemStateListener();
        
        /*** pripojeni prvku k Formu ***/
        this.append(this.vykonCiriceTextField);
        this.append(this.objemTextField);
        this.append(this.timeTimerItem);

        if (this.startTime > 0) {
            this.timeTimerItem.setStartTime(this.startTime);
            this.timeTimerItem.start();
        }
        this.time = -1;
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
        this.vykonCiriceText = this.vykonCiriceTextField.getString();
        this.objemText = this.objemTextField.getString();
        this.startTime = this.timeTimerItem.getStartTime();
        this.time = this.timeTimerItem.getTime();
    }


/**************************************************************************************************
 * own methods:
**************************************************************************************************/

    protected void reset() {
        this.vykonCiriceTextField.setString(null);
        this.objemTextField.setString(null);
        this.timeTimerItem.reset();
        this.vykonCirice = -1;
        this.objem = -1;
        this.time = -1;
    }

    public float vypocti() throws Throwable {
        this.readForm();
        if (this.vykonCiriceText.equals(null) || this.vykonCiriceText.equals("")) {
            throw new Throwable("Nebyl zadán výkon čiřiče");
        }
        else {
            try {
                this.vykonCirice = Float.parseFloat(this.vykonCiriceText);
            } catch (Throwable t) {
                throw new Throwable("Výkon čiřiče zadán v neplatném tvaru");
            }
        }
        if (this.objemText.equals(null) || this.objemText.equals("")) {
            throw new Throwable("Nebyl zadán objem vzorku");
        }
        else {
            try {
                this.objem = Float.parseFloat(this.objemText);
            } catch (Throwable t) {
                throw new Throwable("Objem vzorku zadán v neplatném tvaru");
            }
        }

        /* kontrola zda hodnoty jsou prvky definičního oboru */
        if (0 > this.objem || 0 > this.vykonCirice) {
            throw new Exception("zadané hodnoty nesmějí být záporné!\n");
        }
        if (0 == this.vykonCirice) {
            throw new Exception("výkon čiřiče nesmí být roven 0!\n");
        }
        if (this.time < 0) {
            throw new Exception("chyba v měření času! je chybou SW!\n");
        }
        /* výpočet */
        return this.objem*this.D*3600000*(1/(float)this.time)*this.W*(1/this.vykonCirice);
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
                    s = this.d.getTitle() + "\nvýkon čiřiče: " + this.vykonCirice + " m3/h\nvydávkovaný objem: " + this.objem
                            + " ml\nčas: " + this.formatValue(this.time/1000f, 1) + " s\n--> " + resultAlertString;
                    this.main.writeResult(s);
                    this.showResultAlert(resultAlertString);
                } catch (Throwable t) {
                    this.showErrorAlert(t.getMessage());
                }
            }
            else if (this.cBack == c) {
                this.main.display(this.main.alkalizaceList);
            }
            else if (this.clearFormCommand == c) {
                this.reset();
            }
        }
        else if (this.resultAlert == d) {
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
    }

    public void actionPerformed(CustomItem customItem) {
        if (this.timeTimerItem == customItem) {
            this.time = this.timeTimerItem.getTime();
        }
    }

    public void fromDataStream(DataInputStream din) throws Throwable {
        this.vykonCiriceText = din.readUTF();
        //this.main.writeResult("výkon čiřiče = " + ((null == this.vykonCiriceText) ? "null" : this.vykonCiriceText) + "\n");
        this.objemText = din.readUTF();
        //this.main.writeResult("objem = " + ((null == this.objemText) ? "null" : this.objemText) + "\n");
        this.startTime = din.readLong();
        //this.main.writeResult("čas = " + this.startTime + "\n");
    }

    public void toDataStream(DataOutputStream dout) throws Throwable {
        dout.writeUTF((null == this.vykonCiriceText) ? "" : this.vykonCiriceText);
        //this.main.writeResult("výkon čiřiče = " + ((null == this.vykonCiriceText) ? "null" : this.vykonCiriceText) + "\n");
        dout.writeUTF((null == this.objemText) ? "" : this.objemText);
        //this.main.writeResult("objem = " + ((null == this.objemText) ? "null" : this.objemText) + "\n");
        dout.writeLong(this.startTime);
        //this.main.writeResult("čas = " + this.startTime + "\n");
    }
}