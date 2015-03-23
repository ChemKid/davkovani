/**************************************************************************************************
  davkovani/Davkovani.java
  @author: Jan Ch�ra
  date: 9.7.2009
  description: MIDlet aplikace
**************************************************************************************************/

package davkovani.displayables.menulist;

import davkovani.displayables.DisplayableManagerOld;
import davkovani.displayables.mylist.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.microedition.lcdui.*;


public class MenuList extends MyList
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


/**************************************************************************************************
  konstruktory:
**************************************************************************************************/

  public MenuList(davkovani.Davkovani main, List d) {
      super(main, d);
      this.addCommand(this.cExit);
      this.setCommandListener(this);
      /*00*/this.append("síran", this.setImagePart(this.SIRAN_ICONE_PATH));
      /*01*/this.append("alkalizace", this.setImagePart(this.ALKALIZACE_ICONE_PATH));
      /*02*/this.append("POF", this.setImagePart(this.POF_ICONE_PATH));
      /*03*/this.append("mikropísek", this.setImagePart(this.MIKROPISEK_ICONE_PATH));
      /*04*/this.append("AKTIPHOS", this.setImagePart(this.AKTIPHOS_ICONE_PATH));
      /*05*/this.append("HCl", this.setImagePart(this.HCL_ICONE_PATH));
      /*06*/this.append("NaOH", this.setImagePart(this.NAOH_ICONE_PATH));
      /*07*/this.append("RO", this.setImagePart(this.AKTIPHOS_ICONE_PATH));
      /*08*/this.append("výpis", this.setImagePart(this.VYPIS_ICONE_PATH));
      /*09*///this.append("zkouška", this.setImagePart(this.SETTINGS_ICONE_PATH));
      /*10*///this.append("minimize", this.setImagePart(null));
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
      if (this.d == d) {
          if (this.cExit == c) {
              this.main.exit();
              return;
  	  }
          int selectedIndex=this.getSelectedIndex();
  	  switch(selectedIndex) {
              case 0: 
                  this.main.koagulantList.setFirstSelected();
                  this.main.display(this.main.koagulantList);
                  System.out.println("siran");
                  //DisplayableManager dm = this.main.getDisplayableManager();
                  //dm.display(4);
                  break;
              case 1: 
                  System.out.println("alkalizace");
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