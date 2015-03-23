/**************************************************************************************************
  davkovani/menu/KeyWord.java
  @author: Jan Chara
  date:
  description: 
**************************************************************************************************/

package davkovani.menu;

import java.util.Vector;

/**
 * 
 * @author honza
 */
public class KeyWord
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/

    private String name;
    
    public static final Vector KeyWordSet = new Vector();
    
    static {
        KeyWordSet.addElement(new KeyWord("cireni"));
    }
    

/**************************************************************************************************
 * constructors
**************************************************************************************************/

    public KeyWord(final String name) {
        this.name = name;
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

/**************************************************************************************************
 * own methods:
**************************************************************************************************/

    /**
     * přidá nové klíčové slovo (KeyWord) do seznamu
     * @param keyWord klíčové slovo
     * @return číslo větší nebo rovno 0 pokud se vložení zdařilo, jinak -1
     */
    public static int add(final KeyWord keyWord) {
        return 0;
    }
    
    /**
     * získá klíčové slovo ze seznamu
     * @param name klíčové slovo
     * @return 
     */
    public static KeyWord get(final String name) {
        return new KeyWord(name); // samozřejmě upravit
    }
    
    
/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/
}