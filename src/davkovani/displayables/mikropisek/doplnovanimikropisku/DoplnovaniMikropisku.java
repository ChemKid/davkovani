/**************************************************************************************************
  davkovani/displayables/mikropisek/doplnovanimikropisku/DoplnovaniMikropisku.java
  @author: Jan Chara
  date: 9.7.2009
  description: doplňování mikropísku
**************************************************************************************************/

package davkovani.displayables.mikropisek.doplnovanimikropisku;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.microedition.lcdui.*;
import davkovani.displayables.myform.*;
import davkovani.myitems.actionlistener.*;


public class DoplnovaniMikropisku extends MyForm implements ItemStateListener, ActionListener
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/

    private final TextField vykonCiriceTextField;
    private final TextField pozadovanaKoncentraceTextField;
    private final TextField skutecnaKoncentraceTextField;
    private String vykonCiriceText;
    private String pozadovanaKoncentraceText;
    private String skutecnaKoncentraceText;
    private float vykonCirice;
    private float pozadovanaKoncentrace;
    private float skutecnaKoncentrace;
    private float mnozstviNaDoplneni;


/**************************************************************************************************
 * constructors
**************************************************************************************************/

    /**
     * 
     * @param main
     * @param d 
     */
    public DoplnovaniMikropisku(davkovani.Davkovani main, Form d) {
        super(main, d, "mikropisek doplnovani");
        this.addCommand(this.cOk);
  	this.addCommand(this.cBack);
        this.addCommand(this.clearFormCommand);
  	this.setCommandListener(this);
        
        /*** vytvoření prvků třídy Item ***/
  	this.vykonCiriceTextField = new TextField("výkon čiřiče [m3/h]", this.vykonCiriceText, 5, TextField.DECIMAL);
  	this.pozadovanaKoncentraceTextField = new TextField("požadovaná koncentrace [g/l]", this.skutecnaKoncentraceText, 4, TextField.DECIMAL);
  	this.skutecnaKoncentraceTextField = new TextField("skutečná koncentrace [g/l]", this.pozadovanaKoncentraceText, 4, TextField.DECIMAL);

        /*** layout settings ***/
        
        /*** pripojeni prvku k Formu ***/
        this.append(this.vykonCiriceTextField);
        this.append(this.pozadovanaKoncentraceTextField);
        this.append(this.skutecnaKoncentraceTextField);
        
        this.setItemStateListener();
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

    protected void readForm() {
        this.vykonCiriceText = this.vykonCiriceTextField.getString();
        this.skutecnaKoncentraceText = this.skutecnaKoncentraceTextField.getString();
        this.pozadovanaKoncentraceText = this.pozadovanaKoncentraceTextField.getString();
    }
    

/**************************************************************************************************
 * own methods:
**************************************************************************************************/

    protected void reset() {
        this.vykonCiriceTextField.setString(null);
        this.pozadovanaKoncentraceTextField.setString(null);
        this.skutecnaKoncentraceTextField.setString(null);
        this.vykonCirice = -1;
        this.skutecnaKoncentrace = -1;
        this.pozadovanaKoncentrace = -1;
    }

    public float vypocti() throws Throwable {
        this.readForm();
        if (this.vykonCiriceText == null || this.vykonCiriceText.equals("")) {
            throw new Throwable("Nebyl zadán výkon čiřiče!");
        }
        else {
            try {
                this.vykonCirice = Float.parseFloat(this.vykonCiriceText);
            } catch (NumberFormatException t) {
                throw new Throwable("Výkon čiřiče zadán v neplatném tvaru!");
            }
        }
        if (this.skutecnaKoncentraceText == null || this.skutecnaKoncentraceText.equals("")) {
            throw new Throwable("Nebyla zadána aktuální koncentrace!");
        }
        else {
            try {
                this.skutecnaKoncentrace = Float.parseFloat(this.skutecnaKoncentraceText);
            } catch (NumberFormatException t) {
                throw new Throwable("Aktuální koncentrace zadána v neplatném tvaru!");
            }
        }
        if (this.pozadovanaKoncentraceText == null || this.pozadovanaKoncentraceText.equals("")) {
            throw new Throwable("Nebyla zadána požadovaná koncentrace!");
        }
        else {
            try {
                this.pozadovanaKoncentrace = Float.parseFloat(this.pozadovanaKoncentraceText);
            } catch (NumberFormatException t) {
                throw new Throwable("Požadovaná koncentrace zadána v neplatném tvaru!");
            }
        }
        
        /* kontrola zda hodnoty jsou prvky definičního oboru */
        if (0 > this.vykonCirice || 0 > this.pozadovanaKoncentrace || 0 > this.skutecnaKoncentrace) {
            throw new Exception("Zadané hodnoty nesmějí být záporné!\n");
        }
        if (this.pozadovanaKoncentrace < this.skutecnaKoncentrace) {
            throw new Exception("Požadovaná koncentrace mikropísku nesmí být menší než skutečná!\n");
        }
        /* výpočet */
        return this.vykonCirice*(this.pozadovanaKoncentrace - this.skutecnaKoncentrace);
    }
    
    public float delkaChodu() throws Throwable {
        return this.mnozstviNaDoplneni/(1.5f*7.5f);
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
                    String resultAlertString = "Doplň = " + this.formatValue(this.vypocti(),1) + " kg mikropísku\n";
                    s = this.d.getTitle() + "\nvýkon čiřiče: " + this.vykonCirice + " m3/h\npožadovaná koncentrace: " + this.pozadovanaKoncentrace +
                            " g/l\nskutečná koncentrace: " + this.skutecnaKoncentrace + " g/l\n--> doplň " + this.formatValue(this.mnozstviNaDoplneni = this.vypocti(),1) +
                            " kg mikropísku\n--> doba chodu šnekového podavače = " + this.formatValue(this.delkaChodu(),1) + " min";
                    this.main.writeResult(s);
                    this.showResultAlert(resultAlertString);
                } catch (Throwable t) {
                    this.showErrorAlert(t.getMessage());
                }
            }
            if (this.cBack == c) {
                this.main.display(this.main.mikropisekList);
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
        this.vykonCiriceText = din.readUTF();
        this.skutecnaKoncentraceText = din.readUTF();
        this.pozadovanaKoncentraceText = din.readUTF();
    }

    public void toDataStream(DataOutputStream dout) throws Throwable {
        dout.writeUTF((null == this.vykonCiriceText) ? "" : this.vykonCiriceText);
        dout.writeUTF((null == this.skutecnaKoncentraceText) ? "" : this.skutecnaKoncentraceText);
        dout.writeUTF((null == this.pozadovanaKoncentraceText) ? "" : this.pozadovanaKoncentraceText);
    }
}