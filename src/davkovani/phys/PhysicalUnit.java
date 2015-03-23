/**************************************************************************************************
  davkovani/phys/PhysicalUnit.java
  @author: Jan Chara
  date:
  description: 
**************************************************************************************************/

package davkovani.phys;

/**
 * 
 * @author honza
 */
public class PhysicalUnit
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/

    private static final PhysicalUnit[] SI_UNITS = {
        new PhysicalUnit("m", new PhysicalQuantity("s", "length")),
        new PhysicalUnit("kg", new PhysicalQuantity("m", "weight")),
        new PhysicalUnit("s", new PhysicalQuantity("t", "time")),
        new PhysicalUnit("A", new PhysicalQuantity("I", "electric current")),
        new PhysicalUnit("K", new PhysicalQuantity("T", "temperature")),
        new PhysicalUnit("mol", new PhysicalQuantity("n", "amount of substance")),
        new PhysicalUnit("cd", new PhysicalQuantity("I", "luminous intensity")),
    };
    
    private final PhysicalQuantity physicalQuantity;    // veličina se kterou je tato jednotka spojena
    private final String symbol;                        // symbol jednotky
    

/**************************************************************************************************
 * constructors
**************************************************************************************************/

    /**
     * 
     * @param symbol
     * @param physicalQuantity 
     */
    public PhysicalUnit(String symbol, PhysicalQuantity physicalQuantity) {
        this.symbol = symbol;
        this.physicalQuantity = physicalQuantity;
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

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o instanceof PhysicalUnit == false)
            return false;
        boolean b;
        b = this.symbol == null ? ((PhysicalUnit)o).symbol == null : this.symbol.equals(((PhysicalUnit)o).symbol);
        b &= this.physicalQuantity == null ? ((PhysicalUnit)o).physicalQuantity == null : this.physicalQuantity.equals(((PhysicalUnit)o).physicalQuantity);
        return b;
    }
    
/**************************************************************************************************
 * own methods:
**************************************************************************************************/

    public PhysicalQuantity getPhysicalQuantity() {
        return this.physicalQuantity;
    }
    
    public String getSymbol() {
        return this.symbol;
    }
    
    public boolean isSIUnit(PhysicalUnit physicalUnit) {
        return true; // dodelat
    }
    
/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/
}