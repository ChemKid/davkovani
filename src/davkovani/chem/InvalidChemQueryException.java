/**************************************************************************************************
  davkovani/chem/InvalidChemQueryException.java
  @author: Jan Chára
  date: 5.8.2011
  description: Exception class
**************************************************************************************************/

package davkovani.chem;


public class InvalidChemQueryException extends Exception
{
/**************************************************************************************************
  deklarace proměnných:
**************************************************************************************************/


/**************************************************************************************************
  konstruktory:
**************************************************************************************************/

    public InvalidChemQueryException(String s, int position) {
        super("error at char no. " + (position+1) + " in formula " + s + "\n");
    }

    public InvalidChemQueryException(String s, String symbol) {
        super("symbol " + symbol + " in formula " + s + " not defined\n");
    }

    public InvalidChemQueryException(String s) {
        super("error in formula " + s + "\n");
    }


/**************************************************************************************************
  metoda main:
**************************************************************************************************/


/**************************************************************************************************
  překrytí zděděné nebo přetížené zděděné metody:
**************************************************************************************************/


/**************************************************************************************************
  vlastní metody:
**************************************************************************************************/


/**************************************************************************************************
  metody implementované přes rozhraní:
**************************************************************************************************/
}