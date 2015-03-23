/**************************************************************************************************
  davkovani/displayables/extended/zkouska/ZkouskaForm.java
  @author: Jan Chára
  date: 
  description: 
**************************************************************************************************/

package davkovani.displayables.extended.zkouska;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.microedition.lcdui.*;
import davkovani.displayables.myform.*;
import davkovani.myitems.actionlistener.*;
//import davkovani.math.Permutation;
import davkovani.chem.*;


public class ZkouskaForm extends MyForm implements ItemStateListener, ActionListener
{
/**************************************************************************************************
  deklarace proměnných:
**************************************************************************************************/

    //private Permutation permutation;
    /*private TextField tfPermutation;
    private int intPerm;
    private int tfPermutationNumber;*/
    private ChemFormula cf;
    private TextField tfChemFormula;
    private int cfNum;


/**************************************************************************************************
  konstruktory:
**************************************************************************************************/

    public ZkouskaForm(davkovani.Davkovani main, Form d) {
        super(main, d);
  	this.addCommand(this.cOk);
  	this.addCommand(this.cBack);
  	this.setCommandListener(this);

        /*this.tfPermutation = new TextField("permutace", null, 8, TextField.NUMERIC);
        this.tfPermutationNumber = this.append(this.tfPermutation);*/
        this.append(main.getAppProperty("MIDlet-Version"));
        this.tfChemFormula = new TextField("formula", null, 40, TextField.ANY);
        this.cfNum = this.append(this.tfChemFormula);
    }


/**************************************************************************************************
  překrytí zděděné nebo přetíení zděděné metody:
**************************************************************************************************/

    protected void readForm() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    

/**************************************************************************************************
  vlastní metody:
**************************************************************************************************/

    protected void reset() {
        //this.tfPermutation.setString(null);
        //this.permutation = null;
        this.tfChemFormula.setString(null);
    }

    public float vypocti() throws Throwable {
        return 0f;
    }


/**************************************************************************************************
  metody implementované přes rozhraní:
**************************************************************************************************/

    public void commandAction(Command c, Displayable d) {
        if (this.d == d) {
            if (this.cOk == c) {
                String s;
                try {
                    this.cf = ChemQuery.getFormula(this.tfChemFormula.getString());
                } catch (Throwable t) {
                    this.main.showErrorMessage("chyba: " + t.getMessage());
                    this.reset();
                    return;
                }
                try {
                    this.delete(this.cfNum);
                    this.append(this.tfChemFormula.getString() + "\n");
                    this.append("mw = " + cf.getMolWeight() + "\n");
                    this.cfNum = append(this.tfChemFormula);
                } catch (Throwable t) {
                    this.main.showErrorMessage(t.getMessage());
                }
                this.reset();
            }
            if (this.cBack == c) {
                this.reset();
                this.main.display(this.main.extList);
            }
        }
    }

    public void itemStateChanged(Item item) {
    }

    public void actionPerformed(CustomItem customItem) {
    }
    
    public void fromDataStream(DataInputStream din) throws Throwable {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void toDataStream(DataOutputStream dout) throws Throwable {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}