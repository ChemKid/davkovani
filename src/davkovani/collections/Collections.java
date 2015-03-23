/**************************************************************************************************
  davkovani/collections/Collections.java
  @author: Jan Chára
  date: 9.7.2009
  description: rodičovská třída všech elementů zobrazitelných třídou Davkovani
**************************************************************************************************/

package davkovani.collections;

import java.util.Vector;

public final class Collections
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/

/**************************************************************************************************
 * constructors
**************************************************************************************************/

    private Collections() {
    }

/**************************************************************************************************
 * main method:
**************************************************************************************************/

    public static void main(String[] args) {
        String s = Collections.class.toString();
        System.out.println("class " + s.substring(s.lastIndexOf('.') + 1) + " testing:");
        Vector v = new Vector();
        v.addElement("ahoj");
        v.addElement(null);
        v.addElement("hola");
        System.out.println(v);
        Collections.trimNullElements(v);
        System.out.println("trimmed: " + v);
    }
    
/**************************************************************************************************
 * overriding of parent or overloaded methods
**************************************************************************************************/

/**************************************************************************************************
 * own methods:
**************************************************************************************************/

    public static int trimNullElements(Vector vector) {
        int size = vector.size();
        int nIndex = 0;
        int nCount = 0;
        for (int i = 0; i < size; i++) {
             if (null == vector.elementAt(i)) {
                 nIndex = i;
                 nCount++;
             }
        }
        return 0;
    }
    
/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/
}