/**************************************************************************************************
  davkovani/Davkovani.java
  @author: Jan Chára
  date: 9.7.2009
  description: MIDlet aplikace
**************************************************************************************************/

package davkovani.math;

import davkovani.array.*;


public class Permutation
{
/**************************************************************************************************
  deklarace proměnných:
**************************************************************************************************/

    private int[] elem;
    private int elemNumber;
    private int inversionNumber;


/**************************************************************************************************
  konstruktory:
**************************************************************************************************/

    public Permutation() {
        this.elemNumber = 0;
        this.elem = null;
        this.inversionNumber = 0;
    }

    public Permutation(Permutation p) {
        this.elem=MyArray.copy(p.elem);
        this.elemNumber=this.elem.length;
        this.inversionNumber=p.inversionNumber;
    }

    public Permutation(int[] elem) throws Throwable {
        this.elem = elem;
        this.elemNumber = elem.length;
        try {
            this.validate();
        } catch (Throwable t) {
            throw t;
        }
    }

    public Permutation(int intPerm) throws Throwable {
        if (intPerm < 0 || intPerm > Integer.MAX_VALUE) {
            throw new Throwable("invalid integer value for instantiation of Permutation() class");
        }
        this.elemNumber = Integer.toString(intPerm).length();
        this.elem = new int[this.elemNumber];
        for (int i=this.elemNumber-1;i>=0;i--) {
            this.elem[i] = intPerm%10;
            intPerm /= 10;
        }
        try {
            this.validate();
        } catch (Throwable t) {
            throw t;
        }
    }


/**************************************************************************************************
  metoda main:
**************************************************************************************************/

    public static void main(String[] args) {
    }



/**************************************************************************************************
  překrytí zděděné nebo přetížené zděděné metody:
**************************************************************************************************/

    public String toString() {
        if (null==this.elem) {
            return "permutace {}";
        }
        String permString="permutace {";
        for (int i=0;i<this.elemNumber-1;i++) {
            permString += Integer.toString(this.elem[i]) + ",";
        }
        permString += this.elem[this.elemNumber-1] + "}";
        return permString;
    }

    /*public boolean equals(Object o) {
        return true;
    }

    public int hashCode() {
        return 0;
    }*/


/**************************************************************************************************
  vlastní metody:
**************************************************************************************************/

    public int toInt() {
        return 0;
    }

    public static Permutation getNextPermutation(final Permutation p) throws Throwable {
        int[] help = MyArray.copy(p.elem);
        int max = help.length-1;
        //System.out.println("max = " + max + "\n");
        for (int i=help.length-2;i>=0;i--) {
            if (help[i] > help[max]) {
                max = i;
            }
            else {
                break;
            }
        }
        if (0==max) {
            return p;
        }
        try {
            MyArray.sortUpByInsertMethod(help, max, help.length-1);
        } catch (Throwable t) {
            throw t;
        }
        int x=max-1;
        int y=max-1;
        boolean init=false;
        for (int i=max;i<help.length;i++) {
            if ((help[y]<help[i]) && ((help[i]<help[x]) || !init)) {
                x=i;
                init=true;
            }
        }
        y=help[max-1];
        help[max-1]=help[x];
        help[x]=y;
        Permutation pm = new Permutation();
        pm.elem = help;
        pm.elemNumber = help.length;
        pm.inversionNumber = p.inversionNumber - (((pm.elemNumber-max)*(pm.elemNumber-max-1))/2) + 1;
        return pm;
    } // vrátí další permutaci v pořadí, pokud již taková neexistuje vrátí předanou permutaci

    private void validate() throws Throwable {
        this.inversionNumber = 0;
        for (int i=0;i<this.elemNumber;i++) {
            for (int ii=i+1;ii<this.elemNumber;ii++) {
                if (this.elem[i] == this.elem[ii]) {
                    throw new Throwable("permutation cannot contain any 2 same numbers");
                }
                if (this.elem[i]>this.elem[ii]) {
                    this.inversionNumber++;
                }
            }
        }
    }

    public int getInversionNumber() {
        return this.inversionNumber;
    }


/**************************************************************************************************
  metody implementované přes rozhraní:
**************************************************************************************************/
}
