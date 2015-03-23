/**************************************************************************************************
  davkovani/davkovani/displayables/extended/zkouska/ZkouskaForm01.java
  @author: Jan Chára
  date: 9.7.2009
  description: MIDlet aplikace
**************************************************************************************************/

package davkovani.displayables.extended.zkouska;

import javax.microedition.lcdui.*;
import davkovani.displayables.myform.*;
import davkovani.myitems.actionlistener.*;
import davkovani.myitems.timeritem01.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.microedition.rms.RecordStore;


public class ZkouskaForm01 extends MyForm implements ItemStateListener, ActionListener
{
/**************************************************************************************************
  deklarace proměnných:
**************************************************************************************************/

    private RecordStore myRecordStore;
    private ByteArrayOutputStream BO;
    private ByteArrayInputStream BI;
    private DataOutputStream DO;
    private DataInputStream DI;

    private final String STRING_ITEM_TEXT = "čas: ";
    private Time time;
    private String format;
    private TimerItem01 timerItem;
    private TextField timeTextField;
    private TextField formatTextField;
    private StringItem timeStringItem;


/**************************************************************************************************
  konstruktory:
**************************************************************************************************/

  public ZkouskaForm01(davkovani.Davkovani main, Form d) {
      super(main, d);
      this.BO = new ByteArrayOutputStream();
      this.DO = new DataOutputStream(this.BO);
      this.addCommand(this.cOk);
      this.addCommand(this.cBack);
      this.setCommandListener(this);

      /*** vytvoření prvků třídy Item ***/
      this.timerItem = new TimerItem01("měření času", this.screenWidth);
      this.timeTextField = new TextField("zadej čas v ms :", null, 20, TextField.NUMERIC);
      this.formatTextField = new TextField("zadej formát:", null, 20, TextField.ANY);
      this.timeStringItem = new StringItem(null, this.STRING_ITEM_TEXT);

      this.timerItem.setActionListener(this);

      /*** pripojeni prvku k Formu ***/
      this.append(this.timerItem);
      this.append(this.timeTextField);
      this.append(this.formatTextField);
      this.append(this.timeStringItem);

      this.setItemStateListener();
  }


/**************************************************************************************************
  překrytí zděděné nebo přetíení zděděné metody:
**************************************************************************************************/

    protected void readForm() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    

/**************************************************************************************************
  vlastní metody:
**************************************************************************************************/

  protected void reset() {
  }

  public float vypocti() throws Throwable {
      return 0f;
  }


/**************************************************************************************************
  metody implementované přes rozhraní:
**************************************************************************************************/

  public void commandAction(Command c, Displayable d) {
      if (this.d == d) {
          if (this.cOk == c) {
              try {
                  this.format = this.formatTextField.getString();
                  this.time = new Time(Integer.parseInt(this.timeTextField.getString()));
                  this.time.setFormat(format);
                  this.timerItem.setFormat(this.format);
                  this.timeStringItem.setText(this.STRING_ITEM_TEXT + this.time.getFormatedTime());
              } catch (Throwable t) {
                  this.main.showErrorMessage("chyba!\n" + t.getMessage());
              }
          }
          if (this.cBack == c) {
              this.reset();
              this.main.display(this.main.extList);
          }
      }
  }

  public void itemStateChanged(Item item) {
  }

  public void actionPerformed(CustomItem customItem) {

  }

    public void fromDataStream(DataInputStream din) throws Throwable {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void toDataStream(DataOutputStream dout) throws Throwable {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
