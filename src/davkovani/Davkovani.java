/**************************************************************************************************
  davkovani/Davkovani.java
  @author: Jan Chara
  date: 9.7.2009
  description: MIDlet aplikace
**************************************************************************************************/

package davkovani;

import davkovani.displayables.DisplayableManagerOld;
import davkovani.displayables.MenuListNew;
import davkovani.displayables.errorform.*;
import davkovani.displayables.menulist.*;
import davkovani.displayables.mydisplayable.*;
import davkovani.displayables.outputform.*;
import davkovani.displayables.preloadscreencanvas.*;
import davkovani.install.Installer;
import davkovani.menu.Menu;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Vector;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import javax.microedition.rms.RecordStore;

/**
 *
 * @author honza
 */
public class Davkovani extends MIDlet implements CommandListener
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/

    private boolean pause;
    private long startTime;
    private int runTimeCount; // počet spuštění MIDletu na daném zařízení
    private Installer installer;
    private Display display;
    private DisplayableManagerOld displayableManager;
    private PreloadScreenCanvas preloadScreenCanvas;
    public MyDisplayable currentDisplayable;
    public MyDisplayable previousDisplayable;
    public MyDisplayable menuList;
    public MyDisplayable errorForm;
    public MyDisplayable koagulantList;
    public MyDisplayable outputForm;
    public MyDisplayable alkalizaceList;
    public MyDisplayable mikropisekList;
    public MyDisplayable flokulantList;
    public MyDisplayable extList;
    public MyDisplayable aktiphosList;
    public MyDisplayable hclList;
    public MyDisplayable naohList;
    public MyDisplayable zkouskaForm;
    public MyDisplayable zkouskaTimeru;
    public MyDisplayable ROList;
    private Vector displayableList;
    private final static Vector displayableManagerList;
    
    static {        
        displayableManagerList = new Vector();
    }


/**************************************************************************************************
 * constructors
**************************************************************************************************/

    public Davkovani() {
        this.pause=false;
        //this.installer = new Installer(this);
        this.display=Display.getDisplay(this);
        displayableList = new Vector();
        //this.displayableManager = new DisplayableManager(this);
        this.preloadScreenCanvas = new PreloadScreenCanvas(this);
        this.menuList = new MenuList(this, new List("dávkování " + this.getVersion(), Choice.IMPLICIT));
        this.errorForm = new ErrorForm(this, new Form("errors"));
        this.outputForm = new OutputForm(this, new Form("výpis"));
        this.koagulantList = new davkovani.displayables.koagulant.koagulantlist.KoagulantList(this, new List("síran", Choice.IMPLICIT));
        this.alkalizaceList = new davkovani.displayables.alkalizace.alkalizacelist.AlkalizaceList(this, new List("alkalizace", Choice.IMPLICIT));
        this.mikropisekList = new davkovani.displayables.mikropisek.mikropiseklist.MikropisekList(this, new List("mikropísek", Choice.IMPLICIT));
        this.flokulantList = new davkovani.displayables.flokulant.flokulantlist.FlokulantList(this, new List("POF", Choice.IMPLICIT));
        this.extList = new davkovani.displayables.extended.extendedlist.ExtendedList(this, new List("extended", Choice.IMPLICIT));
        this.aktiphosList = new davkovani.displayables.aktiphos.aktiphoslist.AktiphosList(this, new List("AKTIPHOS", Choice.IMPLICIT));
        this.hclList = new davkovani.displayables.hcl.hcllist.HclList(this, new List("HCl", Choice.IMPLICIT));
        this.naohList = new davkovani.displayables.naoh.naohlist.NaohList(this, new List("NaOH", Choice.IMPLICIT));
        this.zkouskaForm = new davkovani.displayables.extended.zkouska.ZkouskaForm(this, new Form("zkouška"));
        this.zkouskaTimeru = new davkovani.displayables.extended.zkouska.ZkouskaForm01(this, new Form("zkouška Timeru"));
        this.ROList = new davkovani.displayables.ro.list.ROList(this, new List("RO", Choice.IMPLICIT));
  }


/**************************************************************************************************
 * main method:
     * @param args
**************************************************************************************************/

    public static void main(String[] args) {
        System.out.println("MIDlet says hello!");
    }


/**************************************************************************************************
 * overriding of parent or overloaded methods
**************************************************************************************************/

    /**
     * při volání metody probíhá kontrola zda jsou nainstalovány potřebné proměnné, pokud ne je volána metoda install()
     * @throws MIDletStateChangeException
     */
    public void startApp() throws MIDletStateChangeException {
        if (this.pause) {
            this.pause = false;
            return;
        }
        //this.display(this.menuList);
        //this.install();
        //this.installer.install();
        this.initialize();
        //this.display(this.menuList);
        //Display.getDisplay(this).setCurrent(this.displayableManager.infoForm);
    }

    /**
     *
     * @param k
     */
    public void destroyApp(boolean k) {
        this.displayableManager.finalize();
    }

    /**
     *
     */
    public void pauseApp() {
        if (!this.pause) {
            this.pause=true;
            return;
        }
    }


/**************************************************************************************************
 * own methods:
**************************************************************************************************/

    private void initialize() {
        this.startTime = System.currentTimeMillis();
        this.runTimeCount = 0;
        this.displayableManager = DisplayableManagerOld.getDisplayableManager(this);
        this.display(8);
        //this.displayableManager.startMIDlet();
    }
    
    public void display(int index) {
        switch(index) {
            case 0: 
                this.display(new davkovani.displayables.koagulant.overenidavky.OvereniDavky((Davkovani)this, new Form("ověření dávky síranu")));
                break;
            case 1: 
                this.display(new davkovani.displayables.koagulant.nastavenizdvihu.NastaveniZdvihu((Davkovani)this, new Form("nastavení zdvihu čerpadla")));
                break;
            case 2: 
                this.display(new OutputForm((Davkovani)this, new Form("výpis")));
                break;
            case 3: 
                this.display(new OutputForm((Davkovani)this, new Form("výpis")));
                break;
            case 4:
                this.display(new davkovani.displayables.koagulant.koagulantlist.KoagulantList((Davkovani)this, new List("síran", Choice.IMPLICIT)));
                System.out.println("byl sem tu fantomas");
                break;
            case 5:
                this.display(new davkovani.displayables.alkalizace.alkalizacelist.AlkalizaceList((Davkovani)this, new List("alkalizace", Choice.IMPLICIT)));
                break;
            case 6:
                this.display(new davkovani.displayables.mikropisek.mikropiseklist.MikropisekList((Davkovani)this, new List("mikropísek", Choice.IMPLICIT)));
                break;
            case 7:
                this.display(new davkovani.displayables.flokulant.flokulantlist.FlokulantList((Davkovani)this, new List("POF", Choice.IMPLICIT)));
                break;
            case 8:
                this.display(new MenuListNew((Davkovani)this, Menu.DEFAULT_MENU.toMenuList()));
                break;
            case 9:
            case 10:
            case 11:
            case 12:
        }
    }
    
    public String getVersion() {
        return this.getAppProperty("MIDlet-Version");
    }
    
    /**
     * will return the time when first started actual session
     * @return time when the actual session of this MIDlet started
     */
    public long getStartTime() {
        return this.startTime;
    }
    
    public int getRunTimeCount() {
        return this.runTimeCount;
    }
    
    /**
     * ukončí MIDlet
     */
    public void exit() {
        this.destroyApp(true);
        this.notifyDestroyed();
    }

    /**
     *
     * @param md
     */
    public void display(MyDisplayable md) {
        if (null == md) {
            return;
        }
        //this.displayableManager.addDisplayable(md);
        //md.setFirstSelected();
        this.display.setCurrent(md.getDisplayable());
        this.previousDisplayable = this.currentDisplayable;
        this.currentDisplayable = md;
    }

    /**
     *
     * @return
     */
    public MyDisplayable getCurrentDisplayable() {
        return this.currentDisplayable;
    }

    /**
     *
     * @return
     */
    public MyDisplayable getPreviousDisplayable() {
        return this.previousDisplayable;
    }

    /**
     *
     * @return
     */
    public Display getDisplay() {
        return this.display;
    }

    /**
     *
     * @param errorMessage
     */
    public void showErrorMessage(String errorMessage) {
        ((ErrorForm)this.errorForm).append(errorMessage);
        ((ErrorForm)this.errorForm).append(new Spacer(this.errorForm.getWidth(),15));
        this.display(this.errorForm);
    }

    /**
     *
     * @param s
     */
    public void writeResult(String s) {
        ((OutputForm)this.outputForm).insert(0, new StringItem(null, s));
    }

    private void install() {
        boolean isInstalled = false;
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
            this.display.setCurrent(new Alert("ok", "Verification error", null, AlertType.ERROR),this.menuList.getDisplayable());
            //this.showErrorAlert("nelze načíst data!");
            // nabidnout zda preinstalovat nebo neco
            // upravit: ukončit aplikaci?
        } finally {
            try {
                myRecordStore.closeRecordStore();
            } catch (Throwable t) {
                System.out.println("Chyba pri zavirani record store!");
                //this.showErrorAlert("nelze uzavřít record store!");
                // upravit: ukončit aplikaci?
            }
        }
        if (isInstalled) {
            this.display.setCurrent(new Alert("ok", "Already installed", null, AlertType.INFO),this.menuList.getDisplayable());
            //System.out.println("Already installed!");
            //return;
        }
        else {
            System.out.println("Installation is running...");
            try {
                int part = 0;
                int maxValue = 20;
                this.preloadScreenCanvas.setMaxValue(maxValue);
                this.display.setCurrent(this.preloadScreenCanvas);
                long time = System.currentTimeMillis();
                for (int i = 5000; i > System.currentTimeMillis()-time; ) {
                }
                this.preloadScreenCanvas.setPart(part+=10);
                myRecordStore = null;
                data = null;
                myRecordStore = RecordStore.openRecordStore("installInfo", true);
                BO = new ByteArrayOutputStream();
                DO = new DataOutputStream(BO);
                DO.writeBoolean(true);
                data = BO.toByteArray();
                myRecordStore.addRecord(data,0,data.length);
                System.out.println("Successfully installed!");
                this.preloadScreenCanvas.setPart(part+=10);
                this.display.setCurrent(new Alert("ok", "úspěšně instalováno", null, AlertType.INFO));
            } catch (Throwable t) {
                System.out.println("Nelze ulozit data!");
                this.display.setCurrent(new Alert("ok", "nelze uložit data!", null, AlertType.INFO));
                //this.showErrorAlert("nelze načíst data!");
                // upravit: ukončit aplikaci?
            } finally {
                try {
                    myRecordStore.closeRecordStore();
                } catch (Throwable t) {
                    System.out.println("Chyba pri zavirani record store!");
                    //this.showErrorAlert("nelze uzavřít record store!");
                    // upravit: ukončit aplikaci?
                }
            }
        }
    }

/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/
    
    public void commandAction(Command c, Displayable d) {
        System.out.println("byl jsem zavolan");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}