/**************************************************************************************************
  davkovani/phys/PhysicalPrefix.java
  @author: Jan Chara
  date:
  description: 
**************************************************************************************************/

package davkovani.phys;

import java.util.Hashtable;

/**
 * 
 * @author honza
 */
public class PhysicalPrefix
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/

    private static final PhysicalPrefix[] physicalPrefixes = {
        new PhysicalPrefix("Y", 24),
        new PhysicalPrefix("Z", 21),
        new PhysicalPrefix("E", 18),
        new PhysicalPrefix("P", 15),
        new PhysicalPrefix("T", 12),
        new PhysicalPrefix("G", 9),
        new PhysicalPrefix("M", 6),
        new PhysicalPrefix("k", 3),
        new PhysicalPrefix("h", 2),
        new PhysicalPrefix("da", 1),
        new PhysicalPrefix("", 0),
        new PhysicalPrefix("d", -1),
        new PhysicalPrefix("c", -2),
        new PhysicalPrefix("m", -3),
        new PhysicalPrefix("µ", -6),
        new PhysicalPrefix("n", -9),
        new PhysicalPrefix("p", -12),
        new PhysicalPrefix("f", -15),
        new PhysicalPrefix("a", -18),
        new PhysicalPrefix("z", -21),
        new PhysicalPrefix("y", -24),}; 
    private static final PhysicalPrefix[] POSITIVE_ROW;
    private static final PhysicalPrefix[] NEGATIVE_ROW;
    private final String symbol;                               // symbol předpony
    private int mantissa;                                      // exponent předpony
    private static final int BASE = 10;                        // základ
    public static final int MAX_MANTISSA = 24;
    public static final int MIN_MANTISSA = -24;
    public static final Hashtable TABLE;
    
    static {
        TABLE = new Hashtable();
        String s;
        TABLE.put(s = new String("Y"), new PhysicalPrefix(s, 24));
        TABLE.put(s = new String("Z"), new PhysicalPrefix(s, 21));
        TABLE.put(s = new String("E"), new PhysicalPrefix(s, 18));
        TABLE.put(s = new String("m"), new PhysicalPrefix(s, -3));
        TABLE.put(s = new String("µ"), new PhysicalPrefix(s, -6));
        int i;
        POSITIVE_ROW = new PhysicalPrefix[MAX_MANTISSA + 1];
        i = 1; POSITIVE_ROW[i] = new PhysicalPrefix("da", i);
        i = 2; POSITIVE_ROW[i] = new PhysicalPrefix("h", i);
        i = 3; POSITIVE_ROW[i] = new PhysicalPrefix("k", i);
        i = 6; POSITIVE_ROW[i] = new PhysicalPrefix("M", i);
        i = 9; POSITIVE_ROW[i] = new PhysicalPrefix("G", i);
        i = 12; POSITIVE_ROW[i] = new PhysicalPrefix("T", i);
        i = 15; POSITIVE_ROW[i] = new PhysicalPrefix("P", i);
        i = 18; POSITIVE_ROW[i] = new PhysicalPrefix("E", i);
        i = 21; POSITIVE_ROW[i] = new PhysicalPrefix("Z", i);
        i = 24; POSITIVE_ROW[i] = new PhysicalPrefix("Y", i);
        
        NEGATIVE_ROW = new PhysicalPrefix[-MIN_MANTISSA + 1];
        i = -1; NEGATIVE_ROW[-i] = new PhysicalPrefix("d", i);
        i = -2; NEGATIVE_ROW[-i] = new PhysicalPrefix("c", i);
        i = -3; NEGATIVE_ROW[-i] = new PhysicalPrefix("m", i);
        i = -6; NEGATIVE_ROW[-i] = new PhysicalPrefix("µ", i);
        i = -9; NEGATIVE_ROW[-i] = new PhysicalPrefix("n", i);
        i = -12; NEGATIVE_ROW[-i] = new PhysicalPrefix("p", i);
        i = -15; NEGATIVE_ROW[-i] = new PhysicalPrefix("f", i);
        i = -18; NEGATIVE_ROW[-i] = new PhysicalPrefix("a", i);
        i = -21; NEGATIVE_ROW[-i] = new PhysicalPrefix("z", i);
        i = -24; NEGATIVE_ROW[-i] = new PhysicalPrefix("y", i);
    }

/**************************************************************************************************
 * constructors
**************************************************************************************************/

    /**
     * 
     * @param symbol
     * @param physicalQuantity 
     */
    public PhysicalPrefix(String symbol, int mantissa) {
        this.symbol = symbol;
        this.mantissa = mantissa;
    }


/**************************************************************************************************
 * main method:
**************************************************************************************************/

    /**
     * 
     * @param args 
     */
    public static void main(String[] args) {
        //System.out.println(PhysicalPrefix.TABLE.toString());
        //System.out.println(PhysicalPrefix.getPhysicalPrefix(args[0]));
        System.out.println("PhysicalPrefix class test:");
        System.out.println(PhysicalPrefix.getPhysicalPrefix(Integer.parseInt(args[0])));
    }


/**************************************************************************************************
 * overriding of parent or overloaded methods
**************************************************************************************************/

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o instanceof PhysicalPrefix == false)
            return false;
        boolean b;
        b = this.symbol == null ? ((PhysicalPrefix)o).symbol == null : this.symbol.equals(((PhysicalPrefix)o).symbol);
        b &= this.mantissa == ((PhysicalPrefix)o).mantissa;
        return b;
    }
    
    public String toString() {
        return this.symbol + "[1e" + this.mantissa + "]";
    }
    
/**************************************************************************************************
 * own methods:
**************************************************************************************************/

    public int getMantissa() {
        return this.mantissa;
    }
    
    public String getSymbol() {
        return this.symbol;
    }
    
    public static final PhysicalPrefix getPhysicalPrefix(int mantissa) {
        if (mantissa > 0) {
            for (int i = 1; i < PhysicalPrefix.MAX_MANTISSA + 1; i++) {
                if (i == mantissa) return POSITIVE_ROW[i];
            }
        }
        if (mantissa < -1) {
            mantissa *= -1;
            for (int i = 1; i < -PhysicalPrefix.MIN_MANTISSA + 1; i++) {
                if (i == mantissa) return NEGATIVE_ROW[i];
            }
        }
        return null;
    }
    
    public static final PhysicalPrefix getPhysicalPrefix(final String symbol) {
        return (PhysicalPrefix)PhysicalPrefix.TABLE.get(symbol);
    }
    
/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/
}