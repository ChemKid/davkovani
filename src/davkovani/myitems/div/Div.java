/**************************************************************************************************
  davkovani/Davkovani.java
  @author: Jan Chára
  date: 9.7.2009
  description: MIDlet aplikace
**************************************************************************************************/

package davkovani.myitems.div;

import java.util.Vector;



public class Div
{
/**************************************************************************************************
  deklarace proměnných:
**************************************************************************************************/

    private String title; /* titulek */
    private Vector div; /* seznam Div-ů obsažených uvnitř tohoto divu */
    private int x,y; /* souřadnice levého horního rohu divu */
    private int width, height; /* výška a šířka divu */
    public static int DEFAULT_X = 0;
    public static int DEFAULT_Y = 0;
    public static int DEFAULT_WIDTH = 0;
    public static int DEFAULT_HEIGHT = 0;


/**************************************************************************************************
  konstruktory:
**************************************************************************************************/

  public Div(final String title) {
      this.title = new String(title);
  }


/**************************************************************************************************
  metoda main:
**************************************************************************************************/


    public static void main(String[] args) {

    }


/**************************************************************************************************
  překrytí zděděných nebo přetížených zděděných metod:
**************************************************************************************************/



/**************************************************************************************************
  vlastní metody:
**************************************************************************************************/

    /* nastaví šířku divu */
    public void setWidth(int width) {
        this.width = width;
    } // setWidth(int)


    /* nastaví výšku divu */
    public void setHeight(int height) {
        this.height = height;
    } // setHeight(int)


    /* nastaví umístění levého horního rohu divu zleva proti obalovacímu divu */
    public void setX(int x) {
        this.x = x;
    } // setX(int)


    /* nastaví umístění levého horního rohu divu zhora proti obalovacímu divu */
    public void setY(int y) {
        this.y = y;
    } // setY(int)


/**************************************************************************************************
  metody implementované přes rozhraní:
**************************************************************************************************/


}