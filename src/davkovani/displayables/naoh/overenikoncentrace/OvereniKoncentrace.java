/**************************************************************************************************
  davkovani/displayables/naoh/overenikoncentrace/OvereniKoncentrace.java
  @author: Jan Chara
  date: 
  description: 
**************************************************************************************************/

package davkovani.displayables.naoh.overenikoncentrace;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.microedition.lcdui.*;
import davkovani.displayables.myform.*;
import davkovani.myitems.actionlistener.*;
import davkovani.math.*;


public class OvereniKoncentrace extends MyForm implements ItemStateListener, ActionListener
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/

    private final float M_WEIGHT = 39.998f; // relativn� molek. hmotnost HCl
    private final float DENSITY_LOWER = 1.0095f; // nejnižšší zadavatelná hustota
    private final float DENSITY_UPPER = 1.6f; // nejvyšší zadavatelná hustota

    private TextField hustotaTextField;
    private String hustotaText;
    private float hustota;


/**************************************************************************************************
 * constructors
**************************************************************************************************/

    public OvereniKoncentrace(davkovani.Davkovani main, Form d) {
        super(main, d, "NaOH ověření koncentrace");
        this.addCommand(this.cOk);
  	this.addCommand(this.cBack);
        this.addCommand(this.clearFormCommand);
  	this.setCommandListener(this);
  	int tfW = this.screenWidth/3;
  	int siW = tfW*2;
  	
        /*** vytvoření prvků třídy Item ***/
  	this.hustotaTextField = new TextField("naměřená hustota [g/cm3]", this.hustotaText, 6, TextField.DECIMAL);

        /*** layout settings ***/

        /*** pripojeni prvku k Formu ***/
        this.append(this.hustotaTextField);
        
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
        this.hustotaText = this.hustotaTextField.getString();
    }
    

/**************************************************************************************************
 * own methods:
**************************************************************************************************/

    protected void reset() {
        this.hustotaTextField.setString(null);
        this.hustota = -1;
    }

    public float vypocti() throws Throwable {
        this.readForm();
        if (this.hustotaText.equals(null) || this.hustotaText.equals("")) {
            throw new Throwable("Nebyla zadána hustota!");
        }
        else {
            try {
                this.hustota = Float.parseFloat(this.hustotaText);
            } catch (Throwable t) {
                throw new Throwable("Hustota zadána v neplatném tvaru!");
            }
        }
        
        /* kontrola zda hodnoty jsou prvky definičního oboru */
        if (this.DENSITY_LOWER > this.hustota || this.DENSITY_UPPER < this.hustota) {
            throw new Exception("Pro zadanou hodnotu hustoty není koncentrace definována\n");
        }
        
        /* výpočet */
        return (-447.397059f*MyMath.pow(this.hustota,5) + 2845.992024f*MyMath.pow(this.hustota,4) - 7160.507572f*MyMath.pow(this.hustota,3)
                + 8920.870895f*MyMath.pow(this.hustota,2) - 5419.10645f*this.hustota + 1260.313951f);
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
                    String resultAlertString = "koncentrace = " + this.formatValue(f,1) + " %\n";
                    s = this.d.getTitle() + "\nhustota: " + this.hustota + " g/cm3\n--> " + resultAlertString;
                    this.main.writeResult(s);
                    this.showResultAlert(resultAlertString);
                } catch (Throwable t) {
                    this.showErrorAlert(t.getMessage());
                }
            }
            if (this.cBack == c) {
                this.main.display(this.main.naohList);
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
    }

    public void fromDataStream(DataInputStream din) throws Throwable {
        this.hustotaText = din.readUTF();
    }

    public void toDataStream(DataOutputStream dout) throws Throwable {
        dout.writeUTF((null == this.hustotaText) ? "" : this.hustotaText);
    }
}