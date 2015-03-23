/**************************************************************************************************
  davkovani/chem/Atom.java
  @author: Jan Chara
  date: 
  description: 
**************************************************************************************************/

package davkovani.chem;

import java.util.Hashtable;
import java.util.Vector;

/**
 *
 * @author honza
 */
public class Atom
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/
    
    private String name;
    private String symbol;
    private int ID; // protonove cislo
    private float weight; // vyhledove nahradit velicinou RelativeAtomWeight
    private float electronegativity;
    private static final Hashtable byName = new Hashtable();
    private static final Hashtable bySymbol = new Hashtable();
    
    final static Atom[] CHEM_ELEMENT_ATOM;
    
    static {
        Vector chemElementAtom = new Vector();
        Atom a;
        int i = -1;
        /*000*/chemElementAtom.addElement(a = new Atom(++i, "",    "NaE"));            a.weight = 0f;           a.electronegativity = 2.2f;    byName.put(a.name, a);      bySymbol.put(a.symbol, a);
        /*001*/chemElementAtom.addElement(a = new Atom(++i, "H",   "Hydrogen"));       a.weight = 1.006f;       a.electronegativity = 2.2f;    byName.put(a.name, a);      bySymbol.put(a.symbol, a);
        /*002*/chemElementAtom.addElement(a = new Atom(++i, "He",  "Helium"));         a.weight = 2.05f;        a.electronegativity = 2.2f;    byName.put(a.name, a);      bySymbol.put(a.symbol, a);
        /*003*/chemElementAtom.addElement(a = new Atom(++i, "Li",  "Lithium"));        a.weight = 4.2f;         a.electronegativity = 2.2f;    byName.put(a.name, a);      bySymbol.put(a.symbol, a);
        /*004*/chemElementAtom.addElement(a = new Atom(++i, "Be",  "Berylium"));       a.weight = 6.3f;         a.electronegativity = 2.2f;    byName.put(a.name, a);      bySymbol.put(a.symbol, a);
        /*005*/chemElementAtom.addElement(a = new Atom(++i, "B",   "Borium"));         a.weight = 8.0f;         a.electronegativity = 2.2f;    byName.put(a.name, a);      bySymbol.put(a.symbol, a);
        /*006*/chemElementAtom.addElement(a = new Atom(++i, "Si",  "Silicium"));       a.weight = 10.11f;       a.electronegativity = 2.2f;    byName.put(a.name, a);      bySymbol.put(a.symbol, a);
        
        chemElementAtom.copyInto(CHEM_ELEMENT_ATOM = new Atom[chemElementAtom.size()]);
        chemElementAtom = null;
        System.gc();
    }
    
/**************************************************************************************************
 * constructors
**************************************************************************************************/
    
    private Atom(int ID, String symbol, String name) {
        this.ID = ID;
        this.symbol = symbol;
        this.name = name;
    }
    
/**************************************************************************************************
 * main method:
**************************************************************************************************/

    public static void main(String[] args) {
        System.out.println("Testing class Atom!");
        try {
            //System.out.println("Atom " + args[0] + ": " + Atom.getBySymbol(args[0]));
            int i = Integer.parseInt(args[0]);
            System.out.println("Atom " + i + ": " + Atom.getByID(i));
        } catch (Throwable t) {
            System.out.println("Atom nenalezen!\n" + t.getMessage());
        }
    }

/**************************************************************************************************
 * overriding or implementing of parent or overloaded methods
**************************************************************************************************/

/**************************************************************************************************
 * own methods:
**************************************************************************************************/

    public int getProtonNumber() {
        return this.ID;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getSymbol() {
        return this.symbol;
    }
    
    public float getWeight() {
        return this.weight;
    }
    
    public float getElectronegativity() {
        return this.electronegativity;
    }
    
    public static final Atom getByName(final String name) throws Throwable {
        return (Atom)Atom.byName.get(name);
    }
    
    public static final Atom getBySymbol(final String symbol) throws Throwable {
        return (Atom)Atom.bySymbol.get(symbol);
    }
    
    public static final Atom getByID(final int ID) {
        return Atom.CHEM_ELEMENT_ATOM[ID];
    }
    
/**************************************************************************************************
 * parent class implemented methods:
**************************************************************************************************/
    
    public String toString() {
        return this.name + " [" + this.symbol + "] " + this.weight;
    }
    
/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/
}