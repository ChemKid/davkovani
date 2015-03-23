/**************************************************************************************************
  davkovani/displayables/alkalizace/overenidavky/OvereniDavky.java
  @author: Jan Chára
  date: 9.7.2009
  description: formulář pro ověření dávky NaOH
**************************************************************************************************/

package davkovani.displayables.ionex;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.microedition.lcdui.*;
import davkovani.displayables.myform.*;
import davkovani.myitems.actionlistener.*;


public class IonexVolume extends MyForm implements ItemStateListener, ActionListener
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/

    private final float W = 0.5f; // hmotnostní zlomek NaOH
    private final float D = 1.525f; // hustota 50% NaOH
    private final float MAX_IONEX_HEIGHT = 100;
    private final float MIN_IONEX_HEIGHT = -100;
    private String ionexHeightText;

    private TextField ionexHeightTextField;
    private ChoiceGroup ionexTypeChoiceGroup;
    private ChoiceGroup vesselTypeChoiceGroup;
    private float ionexHeight;
    

/**************************************************************************************************
 * constructors
**************************************************************************************************/

    public IonexVolume(davkovani.Davkovani main, Form d) {
        super(main, d, "NaOH_overeni_davky");
        this.addCommand(this.cOk);
        this.addCommand(this.cBack);
        this.setCommandListener(this);
        this.ionexHeightTextField = new TextField("výška ionexu [cm]", this.ionexHeightText, 4, TextField.DECIMAL);

        this.setItemStateListener();
        this.append(this.ionexHeightTextField);
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
        this.ionexHeightText = this.ionexHeightTextField.getString();
    }


/**************************************************************************************************
 * own methods:
**************************************************************************************************/

    protected void reset() {
        this.ionexHeightTextField.setString(null);
        this.ionexHeight = -1;
    }

    public float vypocti() throws Throwable {
        this.readForm();
        if (this.ionexHeightText.equals(null) || this.ionexHeightText.equals("")) {
            throw new Throwable("Nebyla zadána výška ionexu!");
        }

        /* kontrola zda hodnoty jsou prvky definičního oboru */
        if (this.MIN_IONEX_HEIGHT > this.ionexHeight) {
            throw new Exception("Překročena min. výška ionexu!\n");
        }
        if (this.MAX_IONEX_HEIGHT < this.ionexHeight) {
            throw new Exception("Překročena max. výška ionexu!\n");
        }
        /* výpočet */
        return this.ionexHeight;
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
                    s = this.d.getTitle() + "\nvýška ionexu: " + this.ionexHeight + " cm\n--> " + resultAlertString;
                    this.main.writeResult(s);
                    this.showResultAlert(resultAlertString);
                } catch (Throwable t) {
                    this.showErrorAlert(t.getMessage());
                }
            }
            else if (this.cBack == c) {
                this.main.display(this.main.alkalizaceList);
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
    }

    public void actionPerformed(CustomItem customItem) {
    }

    public void fromDataStream(DataInputStream din) throws Throwable {
        this.ionexHeightText = din.readUTF();
    }

    public void toDataStream(DataOutputStream dout) throws Throwable {
        dout.writeUTF((null == this.ionexHeightText) ? "" : this.ionexHeightText);
    }
}
