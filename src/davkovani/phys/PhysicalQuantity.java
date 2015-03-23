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
public class PhysicalQuantity
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/

    private String symbol;
    private String name;
    
    /*public static final PhysicalQuantity[] SI_QUANTITY = {
        new PhysicalQuantity("s", "length"),
        new PhysicalQuantity("m", "weight"),
        new PhysicalQuantity("t", "time"), 
        new PhysicalQuantity("I", "electric current"),
        new PhysicalQuantity("T", "temperature"),
        new PhysicalQuantity("n", "amount of substance"),
        new PhysicalQuantity("I", "luminous intensity"),
    };*/

/**************************************************************************************************
 * constructors
**************************************************************************************************/

    public PhysicalQuantity(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }
    
    public PhysicalQuantity(String symbol) {
        this(symbol, null);
    }


/**************************************************************************************************
 * main method:
**************************************************************************************************/

    public static void main(String[] args) {
    }


/**************************************************************************************************
 * overriding of parent or overloaded methods
**************************************************************************************************/

/**************************************************************************************************
 * own methods:
**************************************************************************************************/

/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/
}