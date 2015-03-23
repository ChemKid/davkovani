/**************************************************************************************************
  davkovani/Davkovani.java
  @author: Jan Chára
  date: 9.7.2009
  description: MIDlet aplikace
**************************************************************************************************/

package davkovani.array;


public final class MyArray
{
/**************************************************************************************************
  deklarace proměnných:
**************************************************************************************************/


/**************************************************************************************************
  konstruktory:
**************************************************************************************************/


/**************************************************************************************************
  metoda main:
**************************************************************************************************/

    public static void main(String[] args) {
    }



/**************************************************************************************************
  překrytí zděděné nebo přetížené zděděné metody:
**************************************************************************************************/


/**************************************************************************************************
  vlastní metody:
**************************************************************************************************/

    public static void sortUpByInsertMethod(int[] array, int start, int end) throws Throwable {
        if (end>=array.length || start<0 || start>end) {
            throw new Throwable("spatne zadane meze");
        }
        int i, j, x;

        for(i = start; i <= end; i++) {
            x = array[i];
            j = i;
            while ((j > start) && (x < array[j-1])) {
                array[j] = array[j-1];
                j--;
            }
            array[j] = x;
        }
    } // vzestupně setřídí pole int[] insert metodou

    public static void SortDownByInsertMethod(int[] array, int start, int end) throws Throwable {
        
    } // sestupně setřídí pole int[] insert metodou

    public static void sortUpByBubbleMethod(int[] array, int start, int end) throws Throwable {
        
    } // vzestupně setřídí pole int[] bubble metodou

    public static void SortDownByBubbleMethod(int[] array, int start, int end) throws Throwable {

    } // sestupně setřídí pole int[] insert metodou

    public static int[] copy(final int[] array) {
        int[] copy = new int[array.length];
        for (int i=0;i<copy.length;i++) {
            copy[i]=array[i];
        }
        return copy;
    }

    public static boolean[] copy(final boolean[] array) {
        boolean[] copy = new boolean[array.length];
        for (int i=0;i<copy.length;i++) {
            copy[i]=array[i];
        }
        return copy;
    }

    public static void init(final boolean[] array, boolean n) {
        for (int i=0;i<array.length;i++) {
            array[i] = n;
        }
    }


/**************************************************************************************************
  metody implementované přes rozhraní:
**************************************************************************************************/
}
