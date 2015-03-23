/**************************************************************************************************
  davkovani/displayables/MenuListNew.java
  @author: Jan Ch�ra
  date: 9.7.2009
  description: MIDlet aplikace
**************************************************************************************************/

package davkovani.displayables;

import davkovani.displayables.mydisplayable.MyDisplayable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.microedition.lcdui.*;
import davkovani.displayables.mylist.*;


public class MenuListNew extends MyList
{
/**************************************************************************************************
  deklarace prom�nn�ch:
**************************************************************************************************/
    
    private final String SIRAN_ICONE_PATH = "/siran.png";
    private final String ALKALIZACE_ICONE_PATH = "/alkalizace.png";
    private final String POF_ICONE_PATH = "/POF.png";
    private final String MIKROPISEK_ICONE_PATH = "/mikropisek.png";
    private final String AKTIPHOS_ICONE_PATH = "/AKTIPHOS.png";
    private final String HCL_ICONE_PATH = "/hcl.png";
    private final String NAOH_ICONE_PATH = "/naoh.png";
    private final String RO_ICONE_PATH = "/POF.png";
    private final String VYPIS_ICONE_PATH = "/vypis.png";
    private final String SETTINGS_ICONE_PATH = "/zkouska.png";
    
    private class MenuItem {
        String name;
        String imagePartPath;
        MyDisplayable displayable;
        
        MenuItem(String name, String imagePartPath, MyDisplayable displayable) {
            this.name = name;
            this.imagePartPath = imagePartPath;
            this.displayable = displayable;
        }
    }
    
    private MenuItem[] menuItem;

/**************************************************************************************************
  konstruktory:
**************************************************************************************************/

  public MenuListNew(davkovani.Davkovani main, List d) {
      super(main, d);
      this.addCommand(this.cExit);
      this.setCommandListener(this);
  }


/**************************************************************************************************
  překrytí zděděné nebo přetížení zděděné metody:
**************************************************************************************************/


/**************************************************************************************************
  vlastn� metody:
**************************************************************************************************/

  
/**************************************************************************************************
  metody implementovan� p�es rozhran�:
**************************************************************************************************/

  public void commandAction(Command c, Displayable d) {
      System.out.println("command listener");
      if (this.d == d) {
          if (this.cExit == c) {
              System.out.println("jsem zde");
              this.main.exit();
              return;
  	  }
          int selectedIndex=this.getSelectedIndex();
  	  switch(selectedIndex) {
              case 0: 
                  this.main.koagulantList.setFirstSelected();
                  this.main.display(this.main.koagulantList);
                  break;
              case 1: 
                  this.main.alkalizaceList.setFirstSelected();
                  this.main.display(this.main.alkalizaceList);
                  break;
              case 2: 
                  this.main.flokulantList.setFirstSelected();
                  this.main.display(this.main.flokulantList);
                  break;
              case 3: 
                  this.main.mikropisekList.setFirstSelected();
                  this.main.display(this.main.mikropisekList);
                  break;
              case 4: 
                  this.main.aktiphosList.setFirstSelected();
                  this.main.display(this.main.aktiphosList);
                  break;
              case 5: 
                  this.main.hclList.setFirstSelected();
                  this.main.display(this.main.hclList);
                  break;
              case 6: 
                  this.main.naohList.setFirstSelected();
                  this.main.display(this.main.naohList);
                  break;
              case 7:
                  this.main.ROList.setFirstSelected();
                  this.main.display(this.main.ROList);
                  break;
              case 8: 
                  this.main.outputForm.setCurrentItem(0);
                  this.main.display(this.main.outputForm);
                  break;
              case 9:
                  this.main.extList.setFirstSelected();
                  this.main.display(this.main.extList);
                  break;
          }
      }
  }

    public void fromDataStream(DataInputStream din) throws Throwable {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void toDataStream(DataOutputStream dout) throws Throwable {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}