/**************************************************************************************************
  davkovani/menu/Menu.java
  @author: Jan Chara
  date:
  description: 
**************************************************************************************************/

package davkovani.menu;

import java.util.Vector;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.List;

/**
 * 
 * @author honza
 */
public class Menu
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/

    private String name;
    private int id;
    private Image imagePart;
    private boolean isDir;
    private Vector menuItem;
    private Menu previousMenu;
    
    private class MenuItem {
        String name;
    }
    
    public static final Menu DEFAULT_MENU;
    
    static {
        DEFAULT_MENU = new Menu("main menu", 0);
        DEFAULT_MENU.add(new Menu("siran", 0));
        DEFAULT_MENU.add(new Menu("NaOH", 0));
        //DEFAULT_MENU.add(new Menu("POF", 0));
        Menu menuItem = new Menu("POF", 0);
        menuItem.add(new Menu("overeni davky", 0));
        menuItem.add(new Menu("overeni koncentrace", 0));
        menuItem.add(new Menu("nastaveni zdvihu", 0));
        DEFAULT_MENU.add(menuItem);
    }

/**************************************************************************************************
 * constructors
**************************************************************************************************/

    private Menu() {
        this.menuItem = new Vector();
        this.previousMenu = null;
    }
    
    public Menu(String name, Image imagePart, int id) {
        this();
        this.name = name;
        this.imagePart = imagePart;
        this.id = id;
    }
    
    public Menu(String name, int id) {
        this(name, null, id);
    }


/**************************************************************************************************
 * main method:
**************************************************************************************************/

    /**
     * 
     * @param args 
     */
    public static void main(String[] args) {
        System.out.println(Menu.DEFAULT_MENU.toString());
        Vector strVector = new Vector();
        strVector.addElement("menu");
        //strVector.addElement("siran");
        //strVector.addElement("overeni davky");
        //System.out.println("pokus: " + Menu.toString(strVector));
    }


/**************************************************************************************************
 * overriding of parent or overloaded methods
**************************************************************************************************/

    /**
     * 
     * @return 
     */
    public String toString() {
        String s = "";
        Menu m = this;
        Menu next = m;
        Vector strVector = new Vector();
        Vector rowVector = new Vector();
        int row = 0;
        int col = 0;
        boolean isNext = false;
        int loopCounter = 0;
        do {
            int size = m.menuItem.size();
            System.out.println("size = " + size);
            strVector.addElement(m.name);
            System.out.println("pridano: " + m.name);
            for (int i = row; i < size; i++) {
                next = (Menu)m.menuItem.elementAt(i);
                if (next.isEmpty()) {
                    System.out.println(next.name + " is empty");
                    s += Menu.toString(strVector) + "\\" + next.name + "\n";
                    System.out.println("cesta: " + Menu.toString(strVector) + "\\" + next.name);
                    continue;
                }
                else {
                    System.out.println(next.name + " is not empty");
                    col++;
                    m = next;
                    rowVector.addElement(new Integer(i));
                    strVector.addElement(next.name);
                    size = m.menuItem.size(); // zakomentovat
                    i = 0;
                    isNext = true;
                    break;
                }
            }
            if (isNext) {
                isNext = false;
                continue;
            }
            m = m.previousMenu;
            col--;
            if (false == rowVector.isEmpty()) {
                row = ((Integer)rowVector.lastElement()).intValue() + 1;
                rowVector.removeElementAt(rowVector.size() - 1);
            }
            if (null == m) {
                System.out.println("previous = null");
            }
            loopCounter ++;
            if (20 < loopCounter) {
                System.out.println("loopCounter break!");
                break;
            }
        } while(false == (null == m));
        return "menu:\n" + s;
    }

    
/**************************************************************************************************
 * own methods:
**************************************************************************************************/
    
    public void add(Menu menuItem) {
        this.menuItem.addElement(menuItem); // pridat kontrolu na jedinecnost prvku
        menuItem.previousMenu = this;
    }
    
    public void remove(Menu menuItem) {
        // dodelat
    }
    
    public boolean isEmpty() {
        return this.menuItem.isEmpty();
    }
    
    private static final String toString(Vector strList) {
        int size = strList.size() - 1;
        String s = "";
        for (int i=0; i < size; i++) {
            s += strList.elementAt(i) + "\\";
        }
        if (0 <= size) {
            s += strList.lastElement();
        }
        return s;
    }
    
    public final List toMenuList() {
        List list = new List(this.name, List.IMPLICIT);
        int size = this.menuItem.size();
        Menu menuItem;
        for (int i = 0; i < size; i++) {
            menuItem = (Menu)this.menuItem.elementAt(i);
            list.append(menuItem.name, menuItem.imagePart);
        }
        return list;
    }
    
/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/
}