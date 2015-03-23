/**************************************************************************************************
  davkovani/displayables/aktiphos/nastavenizdvihu/NastaveniZdvihu.java
  @author: Jan Chara
  date: 9.7.2009
  description: aktiphos - nastavení zdvihu
**************************************************************************************************/

package davkovani.displayables.aktiphos.nastavenizdvihu;

import javax.microedition.lcdui.*;
import davkovani.displayables.myform.*;
import davkovani.myitems.actionlistener.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;


public class NastaveniZdvihu extends MyForm implements ItemStateListener, ActionListener
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/

    private float aktualniKonc;
    private float pozadovanaKonc;
    private float aktualniZdvih;
    private String aktualniKoncText;
    private String pozadovanaKoncText;
    private String aktualniZdvihText;
    private TextField aktualniKoncTextField;
    private TextField pozadovanaKoncTextField;
    private TextField aktualniZdvihTextField;


/**************************************************************************************************
 * constructors
**************************************************************************************************/

    public NastaveniZdvihu(davkovani.Davkovani main, Form d) {
        super(main, d, "aktiphos");
        this.addCommand(this.cOk);
        this.addCommand(this.cBack);
        this.addCommand(this.clearFormCommand);
        this.setCommandListener(this);
        
        /*** vytvoření prvků třídy Item ***/
        this.aktualniKoncTextField = new TextField("aktuální koncentrace [ppm]", this.aktualniKoncText,5,TextField.DECIMAL);
        this.aktualniZdvihTextField = new TextField("aktuální zdvih [%]", this.aktualniZdvihText,5,TextField.DECIMAL);
        this.pozadovanaKoncTextField = new TextField("požadovaná koncentrace [ppm]", this.pozadovanaKoncText,5,TextField.DECIMAL);
        
        /*** pripojeni prvku k Formu ***/
        this.append(this.aktualniKoncTextField);
        this.append(this.aktualniZdvihTextField);
        this.append(this.pozadovanaKoncTextField);
        
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
        this.aktualniKoncText = this.aktualniKoncTextField.getString();
        this.pozadovanaKoncText = this.pozadovanaKoncTextField.getString();
        this.aktualniZdvihText = this.aktualniZdvihTextField.getString();
    }

/**************************************************************************************************
 * own methods:
**************************************************************************************************/

    protected void reset() {
        this.aktualniKoncTextField.setString(null);
        this.aktualniZdvihTextField.setString(null);
        this.pozadovanaKoncTextField.setString(null);
        this.aktualniKonc = -1;
        this.aktualniZdvih = -1;
        this.pozadovanaKonc = -1;
    }

    public float vypocti() throws Throwable { // implementovat do MyForm, překrytí: super.vypocti();
        this.readForm();
        if (this.aktualniKoncText == null || this.aktualniKoncText.equals("")) {
            throw new Throwable("Nebyla zadána aktuální koncentrace");
        }
        else {
            try {
                this.aktualniKonc = Float.parseFloat(this.aktualniKoncText);
            } catch (Throwable t) {
                throw new Throwable("Aktuální koncentrace zadána v neplatném tvaru");
            }
        }
        if (this.pozadovanaKoncText == null || this.pozadovanaKoncText.equals("")) {
            throw new Throwable("Nebyla zadána požadovaná koncentrace");
        }
        else {
            try {
                this.pozadovanaKonc = Float.parseFloat(this.pozadovanaKoncText);
            } catch (Throwable t) {
                throw new Throwable("Požadovaná koncentrace zadána v neplatném tvaru");
            }
        }
        if (this.aktualniZdvihText == null || this.aktualniZdvihText.equals("")) {
            throw new Throwable("Nebyl zadán aktuální zdvih");
        }
        else {
            try {
                this.aktualniZdvih = Float.parseFloat(this.aktualniZdvihText);
            } catch (Throwable t) {
                throw new Throwable("Aktuální zdvih zadán v neplatném tvaru");
            }
        }

        /* kontrola zda hodnoty jsou prvky definičního oboru */
        if (0 > this.aktualniKonc || 0 > this.pozadovanaKonc) {
            throw new Exception("koncentrace nemůže být záporná!\n");
        }
        else if (0 > this.aktualniZdvih || 100 < this.aktualniZdvih) {
            throw new Exception("zdvih musí být v rozmezí hodnot 0 - 100!\n");
        }
        /* výpočet */
        float davka = (this.pozadovanaKonc/this.aktualniKonc)*this.aktualniZdvih;
        return davka > 100.0f ? 100.0f : davka;
    }
    
    public void setDefault() {        
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
                    String resultAlertString = "zdvih = " + this.formatValue(f,1) + "%\n";
                    s = this.d.getTitle() + "\naktuální koncentrace: " + this.aktualniKonc + "ppm\npožadovaná koncentrace: " + this.pozadovanaKonc + "ppm\naktualni zdvih: " + this.aktualniZdvih + "%\n--> " + resultAlertString;
                    this.main.writeResult(s);
                    this.showResultAlert(resultAlertString);
                } catch (Throwable t) {
                    this.showErrorAlert(t.getMessage());
                }
            }
            else if (this.cBack == c) {
                this.main.display(this.main.aktiphosList);
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

    public void fromDataStream(DataInputStream din) throws Throwable { /// implementovat v rozhraní
        this.aktualniKoncText = din.readUTF();
        //this.main.writeResult("aktualni konc = " + ((null == this.aktualniKoncText) ? "null" : this.aktualniKoncText) + "\n");
        this.aktualniZdvihText = din.readUTF();
        //this.main.writeResult("aktualni zdvih = " + ((null == this.aktualniZdvihText) ? "null" : this.aktualniZdvihText) + "\n");
        this.pozadovanaKoncText = din.readUTF();
        //this.main.writeResult("pozadovana konc = " + ((null == this.pozadovanaKoncText) ? "null" : this.pozadovanaKoncText) + "\n");
    }

    public void toDataStream(DataOutputStream dout) throws Throwable { /// implementovat v rozhraní
        dout.writeUTF((null == this.aktualniKoncText) ? "" : this.aktualniKoncText);
        //this.main.writeResult("aktualni konc = " + ((null == this.aktualniKoncText) ? "null" : this.aktualniKoncText) + "\n");
        dout.writeUTF((null == this.aktualniZdvihText) ? "" : this.aktualniZdvihText);
        //this.main.writeResult("aktualni zdvih = " + ((null == this.aktualniZdvihText) ? "null" : this.aktualniZdvihText) + "\n");
        dout.writeUTF((null == this.pozadovanaKoncText) ? "" : this.pozadovanaKoncText);
        //this.main.writeResult("pozadovana konc = " + ((null == this.pozadovanaKoncText) ? "null" : this.pozadovanaKoncText) + "\n");
    }
}