/**************************************************************************************************
  davkovani/Davkovani.java
  @author: Jan Ch�ra
  date: 9.7.2009
  description: MIDlet aplikace
**************************************************************************************************/

package davkovani.displayables.alkalizace.nastavenizdvihu;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.microedition.lcdui.*;
import davkovani.displayables.myform.*;
import davkovani.myitems.actionlistener.*;


public class NastaveniZdvihu extends MyForm implements ItemStateListener, ActionListener
{
/**************************************************************************************************
  deklarace prom�nn�ch:
**************************************************************************************************/

    private final float W = 0.50f; // hmotnostn� zlomek NaOH
    private final float D = 1.525f; // hustota 50% NaOH
    private String davkaText;

    private TextField davkaTextField;
    private float davka;


/**************************************************************************************************
  konstruktory:
**************************************************************************************************/

    public NastaveniZdvihu(davkovani.Davkovani main, Form d) {
        super(main, d, "NaOH_zdvih");
  	this.addCommand(this.cOk);
  	this.addCommand(this.cBack);
        this.addCommand(this.clearFormCommand);
  	this.setCommandListener(this);
        
        /*** vytvoření prvků třídy Item ***/
  	this.davkaTextField = new TextField("požadovaná dávka [mg/l]", this.davkaText,5,TextField.DECIMAL);

        /*** pripojeni prvku k Formu ***/
        this.append(this.davkaTextField);
        
        this.setItemStateListener();
    }


/**************************************************************************************************
  p�ekryt� zd�d�n� nebo p�et�en� zd�d�n� metody:
**************************************************************************************************/

    protected void readForm() {
        this.davkaText = this.davkaTextField.getString();
    }
    

/**************************************************************************************************
  vlastn� metody:
**************************************************************************************************/

    protected void reset() {
        this.davkaTextField.setString(null);
        this.davka = -1;
    }

    public float vypocti() throws Throwable {
        this.readForm();
        if (this.davkaText == null || this.davkaText.equals("")) {
            throw new Throwable("Nebyla zadána požadovaná dávka!");
        }
        else {
            try {
                this.davka = Float.parseFloat(this.davkaText);
            } catch (Throwable t) {
                throw new Throwable("Dávka zádána v neplatném tvaru!");
            }
        }
        
        /* kontrola zda hodnoty jsou prvky definičního oboru */
        if (0 > this.davka) {
            throw new Exception("dávka nemůze být záporná!\n");
        }
        
        /* výpočet */
        return this.davka*(1/this.W)*(1/this.D)*240;
    }


/**************************************************************************************************
  metody implementovan� p�es rozhran�:
**************************************************************************************************/

    public void commandAction(Command c, Displayable d) {
        if (this.d == d) {
            if (this.cOk == c) {
                String s;
                float f;
                try {
                    f = this.vypocti();
                    String resultAlertString = "max výkon čerpadla = " + this.formatValue(this.vypocti(),1) + " ml/h\n";
                    s = this.d.getTitle() + "\npožadovaná dávka: " + this.davka + " mg/l\n--> " + resultAlertString;
                    this.main.writeResult(s);
                    this.showResultAlert(resultAlertString);
                } catch (Throwable t) {
                    this.showErrorAlert(((Exception)t).getMessage());
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
    }

    public void fromDataStream(DataInputStream din) throws Throwable {
        this.davkaText = din.readUTF();
        this.main.writeResult("dávka = " + ((null == this.davkaText) ? "null" : this.davkaText) + "\n");
    }

    public void toDataStream(DataOutputStream dout) throws Throwable {
        dout.writeUTF((null == this.davkaText) ? "" : this.davkaText);
        this.main.writeResult("dávka = " + ((null == this.davkaText) ? "null" : this.davkaText) + "\n");
    }
}