/**************************************************************************************************
  davkovani/Davkovani.java
  @author: Jan Chára
  date: 9.7.2009
  description: MIDlet aplikace
**************************************************************************************************/

package davkovani.displayables.mikropisek.overenikoncentrace;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.microedition.lcdui.*;
import davkovani.displayables.myform.*;
import davkovani.myitems.timeritem01.*;
import davkovani.myitems.actionlistener.*;


public class OvereniKoncentrace extends MyForm implements ItemStateListener, ActionListener
{
/**************************************************************************************************
  deklarace proměnných:
**************************************************************************************************/

    private int apw3Choice;
    private String vykonCiriceText;
    private String objemVzorkuText;
    private String objemMikropiskuText;
    private ChoiceGroup apw3ChoiceGroup;
    private TextField vykonCiriceTextField;
    private TextField objemVzorkuTextField;
    private TextField objemMikropiskuTextField;
    private TimerItem01 timeTimerItem;
    private float vykonCirice;
    private float objemVzorku;
    private float objemMikropisku;
    private long startTime;
    private int time;


/**************************************************************************************************
  konstruktory:
**************************************************************************************************/

    public OvereniKoncentrace(davkovani.Davkovani main, Form d) {
        super(main, d, "mikropisek");
        this.addCommand(this.cOk);
        this.addCommand(this.cBack);
        this.addCommand(this.clearFormCommand);
        this.setCommandListener(this);

        /*** vytvoření prvků třídy Item ***/
        this.apw3ChoiceGroup = new ChoiceGroup("", Choice.EXCLUSIVE);
        this.apw3ChoiceGroup.append("APW3", null);
        this.apw3ChoiceGroup.append("APW4", null);
        this.apw3ChoiceGroup.setSelectedIndex(this.apw3Choice, true);
        this.vykonCiriceTextField = new TextField("výkon čiřiče [m3/h]", this.vykonCiriceText, 5, TextField.DECIMAL);
        this.objemVzorkuTextField = new TextField("objem vzorku [ml]", this.objemVzorkuText, 6, TextField.DECIMAL);
        this.objemMikropiskuTextField = new TextField("objem mikropísku [ml]", this.objemMikropiskuText, 6, TextField.DECIMAL);
        this.timeTimerItem = new TimerItem01("měření času nátoku", this.screenWidth);
        this.timeTimerItem.setBackgroundColor(0x6666cc);
        this.timeTimerItem.setActionListener(this);
        this.setItemStateListener();

        /*** layout settings ***/

        /*** pripojeni prvku k Formu ***/
        this.append(this.apw3ChoiceGroup);
        this.append(this.vykonCiriceTextField);
        this.append(this.objemVzorkuTextField);
        this.append(this.objemMikropiskuTextField);
        this.append(this.timeTimerItem);

        this.vykonCirice = -1;
        this.objemVzorku = -1;
        this.objemMikropisku = -1;
        
        if (this.startTime > 0) {
            this.timeTimerItem.setStartTime(this.startTime);
            this.timeTimerItem.start();
        }
        this.time = -1;
    }


/**************************************************************************************************
  překryté zděděné nebo přetížené zděděné metody:
**************************************************************************************************/

    protected void readForm() {
        this.apw3Choice = this.apw3ChoiceGroup.getSelectedIndex();
        this.vykonCiriceText = this.vykonCiriceTextField.getString();
        this.objemVzorkuText = this.objemVzorkuTextField.getString();
        this.objemMikropiskuText = this.objemMikropiskuTextField.getString();
        this.startTime = this.timeTimerItem.getStartTime();
        this.time = this.timeTimerItem.getTime();
    }
    

/**************************************************************************************************
  vlastní metody:
**************************************************************************************************/

    protected void reset() {
        this.vykonCiriceTextField.setString(null);
        this.objemVzorkuTextField.setString(null);
        this.objemMikropiskuTextField.setString(null);
        this.timeTimerItem.reset();
        this.vykonCirice = -1;
        this.objemVzorku = -1;
        this.objemMikropisku = -1;
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
        if (this.objemVzorkuText == null || this.objemVzorkuText.equals("")) {
            throw new Throwable("Nebyl zadán objem vzorku");
        }
        else {
            try {
                this.objemVzorku = Float.parseFloat(this.objemVzorkuText);
            } catch (Throwable t) {
                throw new Throwable("Objem vzorku zadán v neplatném tvaru");
            }
        }
        if (this.objemMikropiskuText == null || this.objemMikropiskuText.equals("")) {
            throw new Throwable("Nebyl zadán objem mikropísku");
        }
        else {
            try {
                this.objemMikropisku = Float.parseFloat(this.objemMikropiskuText);
            } catch (Throwable t) {
                throw new Throwable("Objem mikropísku zadán v neplatném tvaru");
            }
        }

        /* kontrola zda hodnoty jsou prvky definičního oboru */
        if (0 > this.objemVzorku || 0 > this.objemMikropisku || 0 > this.vykonCirice) {
            throw new Exception("zadané hodnoty nesmějí být záporné!\n");
        }
        if (this.objemMikropisku >= this.objemVzorku) {
            throw new Exception("objem mikropísku musí být menší než objem vzorku");
        }
        if (0 == this.vykonCirice) {
            throw new Exception("výkon čiřiče nesmí být roven 0!\n");
        }
        if (this.time < 0) {
            throw new Exception("chyba v měření času! je chybou SW!\n");
        }
        /* výpočet */
        int x = (0 == this.apw3Choice) ? 10 : 20;
        return (this.objemVzorku*(1/1000f)*this.objemMikropisku*1.4f*(this.objemVzorku/this.time)*3.6f)*(1/((0 == this.apw3Choice) ? this.vykonCirice : this.vykonCirice/2));
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
                    String resultAlertString = "dávka = " + this.formatValue(f,1) + " g/l\n";
                    s = this.d.getTitle() + "\nvýkon " + ((0==this.apw3Choice) ? this.apw3ChoiceGroup.getString(0) : this.apw3ChoiceGroup.getString(1)) + " " + this.vykonCirice + " m3/h\nvydávkovaný objem: " + this.objemVzorku
                            + "ml\nobjem mikropísku: " + this.objemMikropisku + " ml\nčas: " + this.formatValue(this.time/1000f, 1) + "s\n--> ";
                    s += resultAlertString;
                    this.main.writeResult(s);
                    this.showResultAlert(resultAlertString);
                } catch (Throwable t) {
                    this.showErrorAlert(((Exception)t).getMessage());
                }
            }
            else if (this.cBack == c) {
                this.main.display(this.main.mikropisekList);
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
        this.apw3Choice = din.readInt();
        //this.main.writeResult("typ čiřiče = " + ((0==this.apw3Choice) ? "APW3" : "APW4") + "\n");
        this.vykonCiriceText = din.readUTF();
        //this.main.writeResult("výkon čiřiče = " + ((null == this.vykonCiriceText) ? "null" : this.vykonCiriceText) + "\n");
        this.objemVzorkuText = din.readUTF();
        //this.main.writeResult("objem vzorku = " + ((null == this.objemVzorkuText) ? "null" : this.objemVzorkuText) + "\n");
        this.objemMikropiskuText = din.readUTF();
        //this.main.writeResult("objem mikropísku = " + ((null == this.objemMikropiskuText) ? "null" : this.objemMikropiskuText) + "\n");
        this.startTime = din.readLong();
        //this.main.writeResult("čas = " + this.startTime + "\n");
    }

    public void toDataStream(DataOutputStream dout) throws Throwable {
        dout.writeInt(this.apw3Choice);
        //this.main.writeResult("typ čiřiče = " + ((0==this.apw3Choice) ? "APW3" : "APW4") + "\n");
        dout.writeUTF((null == this.vykonCiriceText) ? "" : this.vykonCiriceText);
        //this.main.writeResult("výkon čiřiče = " + ((null == this.vykonCiriceText) ? "null" : this.vykonCiriceText) + "\n");
        dout.writeUTF((null == this.objemVzorkuText) ? "" : this.objemVzorkuText);
        //this.main.writeResult("objem vzorku = " + ((null == this.objemVzorkuText) ? "null" : this.objemVzorkuText) + "\n");
        dout.writeUTF((null == this.objemMikropiskuText) ? "" : this.objemMikropiskuText);
        //this.main.writeResult("objem mikropísku = " + ((null == this.objemMikropiskuText) ? "null" : this.objemMikropiskuText) + "\n");
        dout.writeLong(this.startTime);
        //this.main.writeResult("čas = " + this.startTime + "\n");
    }
}