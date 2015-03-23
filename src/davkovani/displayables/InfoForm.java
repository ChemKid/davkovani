/**************************************************************************************************
  davkovani/displayables/InfoCanvas.java
  @author: Jan Chára
  date: 
  description: 
**************************************************************************************************/

package davkovani.displayables;

import java.io.IOException;
import javax.microedition.lcdui.*;


public class InfoForm extends MyFormNew
{
/**************************************************************************************************
  deklarace proměnných:
**************************************************************************************************/

    private static final ImageItem LOGO;
    private static final String LOGO_PATH; // odstranit zabírá místo!!
    
    static {
        LOGO_PATH = "/beaker.png";
        Image i  = null;
        try {
            i = Image.createImage(LOGO_PATH);
        } catch (IOException t) {
            ///  zápis do error logu
        }
        LOGO = new ImageItem("", i, Item.LAYOUT_LEFT, "");
    }

/**************************************************************************************************
  konstruktory:
**************************************************************************************************/

    public InfoForm(String name) {
        super(name);
    }
    
/**************************************************************************************************
  překrytí zděděné nebo přetíení zděděné metody:
**************************************************************************************************/
    
    protected void readForm() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    protected void reset() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
/**************************************************************************************************
  vlastní metody:
**************************************************************************************************/

/**************************************************************************************************
  metody implementované přes rozhraní:
**************************************************************************************************/
    
    public void perform(Command c) {
        if(MyDisplayableNew.OK_COMMAND == c) {
            System.out.println("OKCommand invoked");
        }
    }
}