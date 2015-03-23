/**************************************************************************************************
  davkovani/chem/NoSuchChemElementException.java
  @author: Jan Chára
  date: 9.7.2009
  description: MIDlet aplikace
**************************************************************************************************/

package davkovani.chem;


public class NoSuchChemElementException extends Exception
{
/**************************************************************************************************
  deklarace proměnných:
**************************************************************************************************/


/**************************************************************************************************
  konstruktory:
**************************************************************************************************/


    public NoSuchChemElementException(String s) {
        super("symbol " + s + " not defined!\n");
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