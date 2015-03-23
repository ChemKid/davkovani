/**************************************************************************************************
 * davkovani/displayables.outputform/OutputForm.java
 * @author: Jan Chara
 * date: 9.7.2009
 * description: output form
**************************************************************************************************/

package davkovani.displayables.outputform;

import javax.microedition.lcdui.*;
import davkovani.displayables.myform.*;
import javax.microedition.rms.*;
import java.io.*;
import java.util.*;


public class OutputForm extends MyForm implements RecordFilter, RecordComparator
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/

    private RecordStore myRecordStore;
    private ByteArrayOutputStream BO;
    private ByteArrayInputStream BI;
    private DataOutputStream DO;
    private DataInputStream DI;
    private boolean nacteno;
    private byte[] data;


/**************************************************************************************************
 * constructors
**************************************************************************************************/

    public OutputForm(davkovani.Davkovani main, Form d) {
        super(main, d);
        this.addCommand(this.cBack);
        this.setCommandListener(this);
        this.BO = new ByteArrayOutputStream();
        this.DO = new DataOutputStream(this.BO);
        this.nacteno = false;
        this.nactiVypis();
    }


/**************************************************************************************************
 * main method:
**************************************************************************************************/

    public static void main(String[] args) {

    }


/**************************************************************************************************
 * overriding of parent or overloaded methods
**************************************************************************************************/

    public int append(String s) {
        if (nacteno) {
            try {
                RecordStore.deleteRecordStore("vypis");
            } catch (Exception e) {}
            this.deleteAll();
            this.nacteno = false;
        }
        this.addRecord(s);
        super.append(s);
        return super.append(new Spacer(this.getWidth(),15));
    }

    public void insert(int itemNum, Item item) {
        if (nacteno) {
            try {
                RecordStore.deleteRecordStore("vypis");
            } catch (Exception e) {}
            this.deleteAll();
            this.nacteno = false;
        }
        if (item instanceof StringItem) {
            this.addRecord(((StringItem)item).getText());
        }
        super.insert(itemNum, new Spacer(this.getWidth(),15));
  	super.insert(itemNum, item);
    }

    public void destroy() {
    }

    protected void reset() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected void readForm() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


/**************************************************************************************************
 * own methods:
**************************************************************************************************/

    private void nactiVypis() {
        int i;
  	RecordStore rs = null;
  	RecordEnumeration re = null;
        Date date = null;
        Calendar cal;
        String time;
        int dd, mm, rr, hh, min;
  	try {
            rs = RecordStore.openRecordStore("vypis", true);
            re=rs.enumerateRecords(null,null,true);
            re.rebuild();
            i = re.numRecords();
            date = new Date(rs.getLastModified());
            cal = Calendar.getInstance();
            cal.setTime(date);
            dd = cal.get(Calendar.DAY_OF_MONTH);
            mm = cal.get(Calendar.MONTH)+1;
            rr = cal.get(Calendar.YEAR);
            hh = cal.get(Calendar.HOUR_OF_DAY);
            min = cal.get(Calendar.MINUTE);
            time = dd + "." + mm + "." + rr + " ";
            time += (hh<10? "0": "")+hh + ":" + (min<10? "0": "")+min + "\n";
            super.append("naposled přidáno:\n");
            super.append(time);
            //super.append("naposledy pridano: " + new Date(rs.getLastModified()).toString() + "\n");
            super.append(new Spacer(this.getWidth(),15));
            for(int x=1;x<=i;x++) {
                this.data = re.nextRecord();
                this.BI = new ByteArrayInputStream(this.data);
                this.DI = new DataInputStream(BI);
                super.append(DI.readUTF() + "\n");
                super.append(new Spacer(this.getWidth(),15));
            }
        } catch (Exception e) {
            super.append("nelze načíst data!\n");
            super.append(e.toString());
        } finally {
            this.nacteno = true;
            try {
                rs.closeRecordStore();
            } catch (Exception e) {}
        }
    }

    private void addRecord(String s) {
        byte[] data;
        RecordStore rs = null;
        try {
            rs = RecordStore.openRecordStore("vypis", true);
            this.BO = new ByteArrayOutputStream();
            this.DO = new DataOutputStream(this.BO);
            this.DO.writeUTF(s);
            data = this.BO.toByteArray();
            rs.addRecord(data,0,data.length);
        } catch (Exception e) {
            System.out.println(e.toString()); /// osetrit jinak
        } finally {
            try {
                rs.closeRecordStore();
            } catch (Exception e) {}
        }
    }


/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/

    public void commandAction(Command c, Displayable d) {
        if (this.d == d) {
            if (this.cBack == c) {
                this.main.display(this.main.getPreviousDisplayable());
            }
        }
    }

    public void itemStateChanged(Item item) {
    }

    public int compare(byte[] rec1, byte[] rec2)
    {
        if (rec1[0]==rec2[0]) return RecordComparator.EQUIVALENT;else
            if (rec2[0]>rec1[0])  return RecordComparator.PRECEDES;else
                return RecordComparator.FOLLOWS;
    }

    public boolean matches(byte[] data) {
        return true;
    }

    public void fromDataStream(DataInputStream din) throws Throwable {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void toDataStream(DataOutputStream dout) throws Throwable {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}