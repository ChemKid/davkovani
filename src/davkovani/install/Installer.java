/**************************************************************************************************
 * davkovani/install/Install.java
 * @author: Jan Chara
 * date: 
 * description: 
**************************************************************************************************/

package davkovani.install;

import davkovani.Davkovani;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.CustomItem;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Item;
import javax.microedition.midlet.MIDlet;
import javax.microedition.rms.RecordStore;


public class Installer implements CommandListener
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/

    private MIDlet midlet;
    private boolean isInstalled;
    private RecordStore myRecordStore;
    private ByteArrayOutputStream BO;
    private ByteArrayInputStream BI;
    private DataOutputStream DO;
    private DataInputStream DI;
    private boolean nacteno;
    private byte[] data;
    
    private Form form;
    private Command doneCommand;
    
    private Alert alert;


/**************************************************************************************************
 * constructors
**************************************************************************************************/

    public Installer(MIDlet midlet) {
        this.midlet = midlet;
        this.alert = new Alert("alert");
        this.alert.setType(AlertType.ERROR);
        this.alert.setTimeout(Alert.FOREVER);
        this.alert.setCommandListener(this);
    } // Install()


/**************************************************************************************************
 * main method:
**************************************************************************************************/

    public static void main(String[] args) {
    } // main(String[])
    

/**************************************************************************************************
 * overriding of parent or overloaded methods
**************************************************************************************************/

        
/**************************************************************************************************
 * own methods:
**************************************************************************************************/

    public void install() {
        boolean isInstalled = false;
        String version = this.getVersion();
        System.out.println("Current version = " + version);
        RecordStore myRecordStore = null;
        ByteArrayInputStream BI;
        DataInputStream DI;
        ByteArrayOutputStream BO;
        DataOutputStream DO;
        byte[] data = null;
        try {
            myRecordStore = RecordStore.openRecordStore("installInfo", true);
            data = myRecordStore.getRecord(1);
            BI = new ByteArrayInputStream(data);
            DI = new DataInputStream(BI);
            isInstalled = DI.readBoolean();
            System.out.println("Verifikovano");
        } catch (Throwable t) {
            System.out.println("Verification error");
            this.alert.setString("Verification error");
            Display.getDisplay(midlet).setCurrent(this.alert);
            // nabidnout zda preinstalovat nebo neco
            // upravit: ukončit aplikaci?
        } finally {
            try {
                myRecordStore.closeRecordStore();
            } catch (Throwable t) {
                System.out.println("Chyba pri zavirani record store po načtení dat!");
                this.alert.setString("Chyba při zavírání record store po načtení dat!");
                Display.getDisplay(midlet).setCurrent(this.alert);
                // upravit: ukončit aplikaci?
            }
        }
        if (isInstalled) {
            this.alert.setString("Již instalováno!");
            Display.getDisplay(midlet).setCurrent(this.alert);
            //this.display.setCurrent(new Alert("ok", "Already installed", null, AlertType.INFO),this.menuList.getDisplayable());
            //System.out.println("Already installed!");
            //return;
        }
        else {
            System.out.println("Installation is running...");
            try {
                myRecordStore = null;
                data = null;
                myRecordStore = RecordStore.openRecordStore("installInfo", true);
                BO = new ByteArrayOutputStream();
                DO = new DataOutputStream(BO);
                DO.writeBoolean(true);
                data = BO.toByteArray();
                myRecordStore.addRecord(data,0,data.length);
                System.out.println("Successfully installed!");
                this.form = new Form("Welcome!");
                this.form.addCommand(this.doneCommand);
                form.setCommandListener(this);
                Display.getDisplay(midlet).setCurrent(this.form);
            } catch (Throwable t) {
                System.out.println("Nelze ulozit data!");
                this.alert.setString("Nelze uložit data!");
                Display.getDisplay(midlet).setCurrent(this.alert);
                //this.showErrorAlert("nelze načíst data!");
                // upravit: ukončit aplikaci?
            } finally {
                try {
                    myRecordStore.closeRecordStore();
                } catch (Throwable t) {
                    System.out.println("Chyba pri zavirani record store po uložení dat!");
                    this.alert.setString("Chyba při zavírání record store po uložení dat!");
                    Display.getDisplay(midlet).setCurrent(this.alert);
                    //this.showErrorAlert("nelze uzavřít record store!");
                    // upravit: ukončit aplikaci?
                }
            }
        }
    } // install()
    
    public void uninstall() {
    } // uninstall()
    

    public String getVersion() {
        String s = this.midlet.getAppProperty("MIDlet-Version");
        return s;
    }
    
    
/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/

        public void commandAction(Command c, Displayable d) {
        if (this.form == d) {
            if (this.doneCommand == c) {
                Display.getDisplay(midlet).setCurrent(((Davkovani)this.midlet).menuList.getDisplayable());
            }
        }
        if (this.alert == d) {
            if (Alert.DISMISS_COMMAND == c) {
                Display.getDisplay(midlet).setCurrent(((Davkovani)this.midlet).menuList.getDisplayable());
            }
        }
    }
    
}