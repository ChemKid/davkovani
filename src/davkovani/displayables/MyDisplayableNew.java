/**************************************************************************************************
  davkovani/displayables/Displayable.java
  @author: Jan Chara
  date:
  description: 
**************************************************************************************************/

package davkovani.displayables;

import javax.microedition.lcdui.Command;

/**
 * rozhraní pro sjednocení tříd Form a Canvas
 * @author honza
 */
public interface MyDisplayableNew
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/

    public static final Command OK_COMMAND = new Command("OK", Command.OK, 0);
    public static final Command RESET_COMMAND = new Command("reset", Command.CANCEL, 1);
    public static final Command BACK_COMMAND = new Command("back", Command.BACK, 2);
    public static final Command CONTINUE_COMMAND = new Command("continue", Command.OK, 1);
    
/**************************************************************************************************
 * overriding of parent or overloaded methods
**************************************************************************************************/

/**************************************************************************************************
 * own methods:
**************************************************************************************************/

    /**
     * uvolnění zdrojů zabíraných danou displayable
     */
    public void finalize();
    
    /**
     * vykoná definovanou akci podle připojeného příkazu
     * @param c příkaz podle něhož je vykonána akce
     */
    public void perform(Command c);
    
/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/
}
