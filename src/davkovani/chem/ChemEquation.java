/**************************************************************************************************
  davkovani/chem/ChemEquation.java
  @author: Jan Chára
  date: 9.7.2009
  description: MIDlet aplikace
**************************************************************************************************/


package davkovani.chem;

//import java.io.*;
import java.util.*;



public class ChemEquation
{
/**************************************************************************************************
  deklarace proměnných:
**************************************************************************************************/

    private String strEquation;                  // rovnice zadaná jako argument konstruktoru
    private int length;                          // délka strEquation
    private int position;                        // odkazuje na znak v rovnici, kde nastala chyba
    private int[][] elem;                        // rovnice převedená na pole int[][]
    private int elemCount;                       // celkový počet chemických prvků v rovnici (odpovídá počtu řádků matice elem
    private int formCount;                       // celkový počet vzorců v rovnici (odpovídá počtu slouců matice elem


/**************************************************************************************************
  konstruktory:
**************************************************************************************************/

    public ChemEquation(String strEquation) /*throws InvalidEquationException*/ {
        this.strEquation = new String(strEquation);
        this.length = this.strEquation.length();
        this.position = 0;
        Vector form = new Vector(10);
        char c=0;
        /*while (this.position<this.length) {
            c = this.strEquation.charAt(this.position);
            if (' '==c) {
                try {
                    form.addElement(new ChemFormula(this.strEquation.substring(0,this.position)));
                }
                catch (InvalidFormulaException ife) {
                    // throw new InvalidEquationException();
                }
            }
            this.position++;
        }*/
        this.elem = new int[0][0];
    }


/**************************************************************************************************
  metoda main:
**************************************************************************************************/

    public static void main(String[] args) {
        ChemEquation ce = new ChemEquation("Na + H2O = NaOH + H2");
        System.out.println(ce.printMatrix());
    }


/**************************************************************************************************
  překrytí zděděné nebo přetížené zděděné metody:
**************************************************************************************************/


/**************************************************************************************************
  vlastní metody:
**************************************************************************************************/

    public String getEquation() {
        return this.strEquation;
    }

    public String printMatrix() {
        String s="";
        for (int i=0; i<this.formCount; i++) {
            for (int j=0; j<this.elemCount; j++) {
                s += this.elem + "\t";
            }
            s += "\n";
        }
        return s;
    }

    public void evaluate() {
        
    }


/**************************************************************************************************
  metody implementované přes rozhraní:
**************************************************************************************************/
}
