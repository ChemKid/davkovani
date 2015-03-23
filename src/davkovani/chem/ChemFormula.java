/**************************************************************************************************
  davkovani/Davkovani.java
  @author: Jan Chára
  date: 9.7.2009
  description: MIDlet aplikace
**************************************************************************************************/


package davkovani.chem;

//import java.io.*;
import java.util.*;
import davkovani.math.*;


public class ChemFormula
{
/**************************************************************************************************
  deklarace proměnných:
**************************************************************************************************/

    protected String strFormula;                  // vzorec zadaný jako argument konstruktoru
    private String strSummaryFormula;             // sumární vzorec
    private String strElementaryFormula;          // elementární vzorec
    private int moleAmount;                       // látkové množství --> změnit na float
    //private float multiple;                     // násobek elementárního vzorce; např. u C6H12O6 je násobkem číslo 6 (6 x C1H2O6)
    private float molWeight;                      // relativní molekulová hmotnost
    protected int elemCount;                      // počet prvků ve vzorci - odpovídá velikosti pole elem[] a koef[]
    protected int[] elem;                         // prot číslo prvku
    protected int[] koef;                         // celkový koeficient prvku
    protected IntFraction multiple;               // násobek elementárního vzorce; např. u C6H12O6 je násobkem číslo 6 (6 x C1H2O6)

    Vector u,v;


/**************************************************************************************************
  konstruktory:
**************************************************************************************************/

    /////// nutno upravit!!!
    protected ChemFormula() {
        this.elemCount = 0;
        this.strFormula = "";
    }
    
    public ChemFormula(ChemFormula chemFormula) {
        
    }
    
    public ChemFormula(String formulaString) {
        this.getFormula(formulaString);
    }


/**************************************************************************************************
  metoda main:
**************************************************************************************************/

    public static void main(String[] args) {
        long time;
        ChemFormula f = new ChemFormula("H2O");
        /*try {
            cf = new ChemFormula("H2O");
            cf.getKoef(0);
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }*/
    }


/**************************************************************************************************
  překrytí zděděné nebo přetížené zděděné metody:
**************************************************************************************************/

/**************************************************************************************************
  vlastní metody:
**************************************************************************************************/

    public void getFormula(String formulaString) {
        int length = formulaString.length();
        
        for (int i = 0; i < length; i++) {
            
        }
    }
    
    /* vrátí sumární vzorec jako řetězec */
    public String getSummaryFormula() {
        String s="";
        for (Enumeration e = u.elements(), f = v.elements(); e.hasMoreElements() || f.hasMoreElements();) {
            s+=(String)e.nextElement() + (Integer)f.nextElement();
        }
        return s;
    } // getSummaryFormula()


    /* vrátí elementární vzorec jako řetězec */
    public String getElementaryFormula() {
        String s="";
        for (int i=0; i<this.elemCount; i++) {
            s += ChemElementsTable.symbol[this.elem[i]] + this.koef[i];
        }
        return s;
    } // getElementaryFormula()


    /* vrátí molární hmotnost vzorce */
    // přidat podmínku k testování, zda již molWeight nebyla vypočtena 
    public float getMolWeight() throws Throwable {
        this.molWeight = 0f;
        for (int i=0; i<this.elemCount; i++) {
            try {
                this.molWeight += ChemElementsTable.getAtomWeight(this.elem[i])*this.koef[i];
                /*System.out.println("elem[" + i + "] = " + ChemElementsTable.symbol[this.elem[i]] +
                        ", mw = " + ChemElementsTable.atomWeight[this.elem[i]] +
                        ", koef[" + i + "] = " + this.koef[i]);*/
            } catch (Exception e) {
                throw new Throwable("Molar weight cannot be evalued!");
            }
        }
        return this.molWeight = (this.molWeight * (float)this.multiple.doubleValue()); /// přiřadit napřed vracenou hodnotu do molWeight!!!
    } // getMolWeight()


/**************************************************************************************************
  metody implementované přes rozhraní:
**************************************************************************************************/
}
