/**************************************************************************************************
  davkovani/displayables/flokulant/overenidavky/OvereniDavky.java
  @author: Jan Chara
  date: 9.7.2009
  description: MIDlet aplikace
**************************************************************************************************/

package davkovani.displayables.flokulant.overenidavky;

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

    private String vykonCiriceText;
    private String hladina1Text;
    private String hladina2Text;
    private TextField vykonCiriceTextField;
    private TextField hladina1TextField;
    private TextField hladina2TextField;
    private TimerItem01 timeTimerItem;
    private float vykonCirice;
    private float hladina1;
    private float hladina2;
    private long startTime;
    private int time;


/**************************************************************************************************
 * constructors
**************************************************************************************************/

    public OvereniDavky(davkovani.Davkovani main, Form d) {
        super(main, d, "POF");
        this.addCommand(this.cOk);
        this.addCommand(this.cBack);
        this.addCommand(this.clearFormCommand);
        this.setCommandListener(this);
        int tfW = this.screenWidth/3;
  	int siW = tfW*2;
        
        /*** vytvoření prvků třídy Item ***/
  	this.vykonCiriceTextField = new TextField("výkon čiřiče [m3/h]", this.vykonCiriceText, 4, TextField.DECIMAL);
  	this.hladina1TextField = new TextField("horní hladina [mm]", this.hladina1Text, 4, TextField.DECIMAL);
  	this.hladina2TextField = new TextField("dolní hladina [mm]:", this.hladina2Text, 4, TextField.DECIMAL);
  	this.timeTimerItem = new TimerItem01("měření času", this.screenWidth);
  	this.timeTimerItem.setBackgroundColor(0x6666cc);
  	this.timeTimerItem.setActionListener(this);
  	this.setItemStateListener();

        /*** pripojeni prvku k Formu ***/
        this.append(this.vykonCiriceTextField);
        this.append(this.hladina1TextField);
        this.append(this.hladina2TextField);
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
        this.hladina1Text = this.hladina1TextField.getString();
        this.hladina2Text = this.hladina2TextField.getString();
        this.startTime = this.timeTimerItem.getStartTime();
        this.time = this.timeTimerItem.getTime();
    }
    

/**************************************************************************************************
 * own methods:
**************************************************************************************************/

    protected void reset() {
        this.vykonCiriceTextField.setString(null);
        this.hladina1TextField.setString(null);
        this.hladina2TextField.setString(null);
        this.timeTimerItem.reset();
        this.vykonCirice = -1;
        this.hladina1 = -1;
        this.hladina2 = -1;
        this.time = -1;
    }
    
    public float vypocti() throws Throwable {
        this.readForm();
        if (this.vykonCiriceText == null || this.vykonCiriceText.equals("")) {
            throw new Throwable("Nebyl zadán výkon čiřiče");
        }
        else {
            try {
                this.vykonCirice = Float.parseFloat(this.vykonCiriceText);
            } catch (Throwable t) {
                throw new Throwable("Výkon čiřiče zadán v neplatném tvaru");
            }
        }
        if (this.hladina1Text == null || this.hladina1Text.equals("")) {
            throw new Throwable("Nebyla zadána hladina 1");
        }
        else {
            try {
                this.hladina1 = Float.parseFloat(this.hladina1Text);
            } catch (Throwable t) {
                throw new Throwable("Hladina 1 zadána v neplatném tvaru");
            }
        }
        if (this.hladina2Text == null || this.hladina2Text.equals("")) {
            throw new Throwable("Nebyla zadána hladina 2");
        }
        else {
            try {
                this.hladina2 = Float.parseFloat(this.hladina2Text);
            } catch (Throwable t) {
                throw new Throwable("Hladina 2 zadána v neplatném tvaru");
            }
        }


        /* kontrola zda hodnoty jsou prvky definičního oboru */
        if (0 > this.hladina1 || 0 > this.hladina2 || 0 > this.vykonCirice) {
            throw new Exception("zadané hodnoty nesmějí být záporné!\n");
        }
        if (0 == this.vykonCirice) {
            throw new Exception("výkon čiřiče nesmí být roven 0!\n");
        }
        if (this.hladina1 <= this.hladina2) {
            throw new Exception("horní hladina musí být vyšši než spodní!\n");
        }
        if (this.time < 0) {
            throw new Exception("chyba v měření času! je chybou SW!\n");
        }
        /* výpočet */
        return (this.hladina1 - this.hladina2)*360f*59f*(1f/(float)this.time)*(1f/this.vykonCirice)*2f;
    } // vypocti()


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
                    s = this.d.getTitle() + "\nvýkon: " + this.vykonCirice + " m3/h\nrozdíl hladin: " + (this.hladina1 - this.hladina2)
                    + " mm\nčas: " + this.formatValue(this.time/1000f,1) + " s\n--> " + resultAlertString;
                    this.main.writeResult(s);
                    this.showResultAlert(resultAlertString);
                } catch (Throwable t) {
                    this.showErrorAlert(t.getMessage());
                }
            }
            else if (this.cBack == c) {
                this.main.display(this.main.flokulantList);
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
        this.hladina1Text = din.readUTF();
        //this.main.writeResult("hladina 1 = " + ((null == this.hladina1Text) ? "null" : this.hladina1Text) + "\n");
        this.hladina2Text = din.readUTF();
        //this.main.writeResult("hladina 2 = " + ((null == this.hladina2Text) ? "null" : this.hladina2Text) + "\n");
        this.startTime = din.readLong();
        //this.main.writeResult("čas = " + this.startTime + "\n");
    }

    public void toDataStream(DataOutputStream dout) throws Throwable {
        dout.writeUTF((null == this.vykonCiriceText) ? "" : this.vykonCiriceText);
        //this.main.writeResult("výkon čiřiče = " + ((null == this.vykonCiriceText) ? "null" : this.vykonCiriceText) + "\n");
        dout.writeUTF((null == this.hladina1Text) ? "" : this.hladina1Text);
        //this.main.writeResult("hladina 1 = " + ((null == this.hladina1Text) ? "null" : this.hladina1Text) + "\n");
        dout.writeUTF((null == this.hladina2Text) ? "" : this.hladina2Text);
        //this.main.writeResult("hladina 2 = " + ((null == this.hladina2Text) ? "null" : this.hladina2Text) + "\n");
        dout.writeLong(this.startTime);
        //this.main.writeResult("čas = " + this.startTime + "\n");
    }
}