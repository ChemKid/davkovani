/**************************************************************************************************
  davkovani/phys/PhysicalValue.java
  @author: Jan Chara
  date:
  description: 
**************************************************************************************************/

package davkovani.phys;

/**
 * 
 * @author honza
 */
public class PhysicalValue
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/
 
    private float value;                            // hodnota
    private final PhysicalUnit physicalUnit;        // jednotka
    

/**************************************************************************************************
 * constructors
**************************************************************************************************/

    /**
     * 
     * @param symbol
     * @param physicalQuantity 
     */
    public PhysicalValue(float value, PhysicalUnit physicalUnit) {
        this.value = value;
        this.physicalUnit = physicalUnit;
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
        if (o instanceof PhysicalValue == false)
            return false;
        boolean b;
        b = this.value == (((PhysicalValue)o).value);
        b &= this.physicalUnit == null ? ((PhysicalValue)o).physicalUnit == null : this.physicalUnit.equals(((PhysicalValue)o).physicalUnit);
        return b;
    }
    
    public String toString() {
        return "" + this.value + this.physicalUnit;
    }
    
/**************************************************************************************************
 * own methods:
**************************************************************************************************/

    public float getValue() {
        return this.value;
    }
    
    public final PhysicalUnit getUnit() {
        return this.physicalUnit;
    }
    
/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/
}