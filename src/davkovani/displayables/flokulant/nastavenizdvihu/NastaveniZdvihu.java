/**************************************************************************************************
  davkovani/displayables/flokulant/nastavenizdvihu/NastaveniZdvihu.java
  @author: Jan Chara
  date: 9.7.2009
  description: 
**************************************************************************************************/

package davkovani.displayables.flokulant.nastavenizdvihu;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.microedition.lcdui.*;
import davkovani.displayables.myform.*;
import davkovani.myitems.actionlistener.*;


public class NastaveniZdvihu extends MyForm implements ItemStateListener, ActionListener
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/

    /// nadefinovat konstanty, upravit vzorec výpočtu!!!
    private String davkaText;

    private TextField davkaTextField;
    private float davka;


/**************************************************************************************************
 * constructors
**************************************************************************************************/

    public NastaveniZdvihu(davkovani.Davkovani main, Form d) {
        super(main, d, "POF nastavení zdvihu");
        this.addCommand(this.cOk);
        this.addCommand(this.cBack);
        this.addCommand(this.clearFormCommand);
  	this.setCommandListener(this);
        int tfW = this.screenWidth/3;
  	int siW = tfW*2;
        
        /*** vytvoření prvků třídy Item ***/
  	this.davkaTextField = new TextField("požadovaná dávka [mg/l]", this.davkaText,5,TextField.DECIMAL);
        
        /*** layout settings ***/

        /*** pripojeni prvku k Formu ***/
        this.append(this.davkaTextField);
        
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
        this.davkaText = this.davkaTextField.getString();
    }
    

/**************************************************************************************************
 * own methods:
**************************************************************************************************/

    protected void reset() {
        this.davkaTextField.setString(null);
        this.davka = -1;
    }

    public float vypocti() throws Throwable {
        this.readForm();
        if (this.davkaText.equals(null) || this.davkaText.equals("")) {
            throw new Throwable("Nebyla zadána požadovaná dávka!");
        }
        else {
            try {
                this.davka = Float.parseFloat(this.davkaText);
            } catch (Throwable t) {
                throw new Throwable("Dávka zadána v neplatném tvaru!");
            }
        }
        
        /* kontrola zda hodnoty jsou prvky definičního oboru */
        if (0 > this.davka) {
            throw new Exception("dávka nemůže být záporná!\n");
        }
        
        /* výpočet */
        return this.davka*(1f/1000f)*500f*10000f*(1f/(0.5f*55f));
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
                    String resultAlertString = "zdvih = " + this.formatValue(f,1) + " %\n";
                    s = this.d.getTitle() + "\npožadovaná dávka: " + this.davka + " mg/l\n--> " + resultAlertString;
                    this.main.writeResult(s);
                    this.showResultAlert(resultAlertString);
                } catch (Throwable t) {
                    this.showErrorAlert(t.getMessage());
                }
            }
            if (this.cBack == c) {
                this.main.display(this.main.flokulantList);
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

    public void actionPerformed(CustomItem customItem) {
    }

    public void fromDataStream(DataInputStream din) throws Throwable {
        this.davkaText = din.readUTF();
    }

    public void toDataStream(DataOutputStream dout) throws Throwable {
        dout.writeUTF((null == this.davkaText) ? "" : this.davkaText);
    }
}