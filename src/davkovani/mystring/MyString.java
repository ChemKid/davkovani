/**************************************************************************************************
  davkovani/mystring/MyString.java
  @author: Jan Chára
  date: 04.10.2012
  description: práce s řetězci
**************************************************************************************************/

package davkovani.mystring;

import java.util.Vector;


public final class MyString
{
/**************************************************************************************************
  deklarace proměnných:
**************************************************************************************************/



/**************************************************************************************************
  konstruktory:
**************************************************************************************************/

    private MyString() {
        // není možno vytvořit instatnci třídy
    }


/**************************************************************************************************
  metoda main:
**************************************************************************************************/

    public static void main(String[] args) {
        String s = ":H:N:O3:";
        String[] ex;
        int l;
        ex = MyString.explode(s, ':');
        l = ex.length;
        System.out.println("testing method explode(String, char):");
        System.out.println("before explosion: " + s);
        System.out.println("after explosion: ");
        for (int i=0; i<l; i++) {
            System.out.println(ex[i]);
        }
    }

/**************************************************************************************************
  překrytí zděděných nebo přetížených metod:
**************************************************************************************************/


/**************************************************************************************************
  vlastní metody:
**************************************************************************************************/

    public static String[] explode(final String s, final char separator) {
        if (null == s) {
            return null;
        }
        Vector v = new Vector(10,10);
        String f = new String();
        String[] frag;
        int l = s.length();
        int n = 0;
        for (int i=0; i<l; i++) {
            if (separator == s.charAt(i)) {
                n++;
                v.addElement(f);
                f = new String();
                continue;
            }
            f += s.charAt(i);
        }
        v.addElement(f);
        v.trimToSize();
        frag = new String[v.size()];
        v.copyInto(frag);
        return frag;
    }


/**************************************************************************************************
  interface implemented methods:
**************************************************************************************************/

}

