/*-------------------------------------------------------------------------------------------------
  davkovani/displayables/sdi/SDI.java
  date: 22.9.2012
  description: formulář pro měření SDI
-------------------------------------------------------------------------------------------------*/

package davkovani.displayables.ro;

import davkovani.displayables.myform.*;
import davkovani.myitems.actionlistener.*;
import davkovani.myitems.timeritem01.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Date;
import javax.microedition.io.ConnectionNotFoundException;
import javax.microedition.io.PushRegistry;
import javax.microedition.lcdui.*;

/**
 * formulář pro měření SDI
 * @author honza
 */
public class SDI extends MyForm implements ItemStateListener, ActionListener, ItemCommandListener
{
/*-------------------------------------------------------------------------------------------------
  variables declaration:
-------------------------------------------------------------------------------------------------*/

    private int t1;
    private int t2;
    private int t;
    private int alertTime;
    private long alertTimeDelay;
    private String infoText;
    private String alertTimeText;
    private long sampleStartTime;
    private long testStartTime;
    private final String MIDLET_CLASS_NAME;
    public static final int DEFAULT_ALERT_TIME_DELAY = 15 * 60 * 1000; // ms
    private final String INFO_START_TEXT = "zahajte měření";
    private final String ALERT_TIME_TEXT = "výstraha po";
    private boolean isT1Loaded;
    private boolean isT2Loaded;
    private boolean isCalculateCommand;
    private final TimerItem01 timePerTestTimerItem01;
    private final TimerItem01 timePerSampleTimerItem01;
    private final TextField alertTextField;
    private final StringItem infoStringItem;
    private final StringItem alertTimeStringItem;
    private final StringItem confirmStringItem;
    private final Alert SDIAlert;
    private final Gauge alertTimeGauge;
    DateField alertDateField;
    private final Command setAlertCommand;


/*-------------------------------------------------------------------------------------------------
  constructors:
-------------------------------------------------------------------------------------------------*/

    /**
     * inicializuje formulář
     * @param main MIDlet aplikace
     * @param d 
     */
    public SDI(davkovani.Davkovani main, Form d) {
        super(main, d, "SDI");
        // variables settings (nacteni z record store vs. default!!!!) - upravit
        this.isCalculateCommand = false;
        // The Class name is needed for the registration in PushRegistry
        this.MIDLET_CLASS_NAME = this.main.getClass().getName();
        this.setAlertCommand = new Command("set alert", Command.OK, 2);
        this.addCommand(this.cOk);
        this.addCommand(this.cBack);
        this.addCommand(this.clearFormCommand);
        //this.addCommand(this.setAlertCommand);
        this.setCommandListener(this);
        //this.isT1Loaded = false;
        //this.isT2Loaded = false;

        /*** vytvoření prvků třídy Item ***/
        this.timePerTestTimerItem01 = new TimerItem01("doba testu", this.screenWidth);
        this.timePerSampleTimerItem01 = new TimerItem01("měření času", this.screenWidth);
        this.infoStringItem = new StringItem(null, this.infoText);
        this.alertTextField = new TextField(null, null, 4, TextField.DECIMAL);
        this.alertTextField.addCommand(this.setAlertCommand);
        this.alertTextField.setItemCommandListener(this);
        this.SDIAlert = new Alert("SDI - upozornění");
        this.alertTimeStringItem = new StringItem(null, this.ALERT_TIME_TEXT);
        this.confirmStringItem = new StringItem(null, "OK", Item.BUTTON);
        this.alertDateField = new DateField(null, DateField.TIME);
        this.alertTimeGauge = new Gauge(null, false, 15, 0);

        this.timePerTestTimerItem01.setActionListener(this);
        this.timePerTestTimerItem01.setInteractionMode(TimerItem01.STATIC);
        try {
            this.timePerTestTimerItem01.setFormat("mm:ss");
        } catch (Throwable t) { // změnit na RuntimeException aby ji nebylo nutno osetrovat!!!!
            // format se nezmeni
            System.out.println("format setting failed" + t.getMessage());
        }
        this.timePerSampleTimerItem01.setActionListener(this);
        this.SDIAlert.setType(AlertType.ALARM);

        /*** layout settings ***/
        this.infoStringItem.setLayout(Item.LAYOUT_EXPAND | Item.LAYOUT_NEWLINE_AFTER);
        this.alertTimeStringItem.setLayout(Item.LAYOUT_EXPAND | Item.LAYOUT_NEWLINE_AFTER);
        this.alertTextField.setLayout(Item.LAYOUT_LEFT);
        //this.alertDateField.setLayout(Item.LAYOUT_RIGHT);
        this.alertTimeGauge.setLayout(Item.LAYOUT_EXPAND | Item.LAYOUT_NEWLINE_AFTER);

        /*** pripojeni prvku k Formu ***/
        //this.reset();
        this.append(this.timePerSampleTimerItem01);
        this.append(this.infoStringItem);
        this.append(this.timePerTestTimerItem01);
        this.append(this.alertTimeStringItem);
        //this.append(this.confirmStringItem);
        this.append(this.alertTextField);
        //this.append(this.alertDateField);
        //this.append(this.alertTimeGauge);

        this.timePerSampleTimerItem01.setLayout(Item.BUTTON);

        this.setItemStateListener();

        System.out.println();
        System.out.println("start settings:");
        System.out.println("infoText = " + this.infoText);
        System.out.println("sampleStartTime = " + this.sampleStartTime);
        System.out.println("testStartTime = " + this.testStartTime);
        System.out.println("isT1Loaded = " + this.isT1Loaded);
        System.out.println("isT2Loaded = " + this.isT2Loaded);
        System.out.println("t1 = " + this.t1);
        System.out.println("t2 = " + this.t2);
        if (this.testStartTime > 0) {
            this.timePerTestTimerItem01.setStartTime(this.testStartTime);
            this.timePerTestTimerItem01.start();
        }
        if (this.sampleStartTime > 0) {
            this.timePerSampleTimerItem01.setStartTime(this.sampleStartTime);
            this.timePerSampleTimerItem01.start();
            //this.timePerTestTimerItem01.setStartTime(this.testStartTime);
            //this.timePerTestTimerItem01.start();
        }
    }


/*-------------------------------------------------------------------------------------------------
  main method:
-------------------------------------------------------------------------------------------------*/

    /**
     * metoda pro testování třídy v příkazovém řádku
     * @param args 
     */
    public static void main(String[] args) {
    }
    

/*-------------------------------------------------------------------------------------------------
  overriding of parent or overloaded methods:
-------------------------------------------------------------------------------------------------*/

    /**
     * načte hodnoty z formuláře a uloží do vnitřních proměnných
     */
    protected void readForm() {
        if (this.timePerSampleTimerItem01.isRunning()) {
            if (this.isT1Loaded) {
                this.t2 = this.timePerSampleTimerItem01.getTime();
                this.t = this.timePerTestTimerItem01.getTime(); /// vysunout před if()???
                if (false == this.isT2Loaded && this.isCalculateCommand) {
                    this.isT2Loaded = true;
                    this.isCalculateCommand = false;
                }
            }
        }
        this.sampleStartTime = this.timePerSampleTimerItem01.getStartTime();
        this.testStartTime = this.timePerTestTimerItem01.getStartTime();
    }

/*-------------------------------------------------------------------------------------------------
  own methods:
-------------------------------------------------------------------------------------------------*/

    /**
     * zresetuje hodnoty ve formuláři, tj. nastaví na výchozí v okamžiku inicilizace formuláře
     */
    protected void reset() {
        this.timePerSampleTimerItem01.reset();
        this.timePerTestTimerItem01.reset();
        this.infoStringItem.setText(this.infoText = this.INFO_START_TEXT);
        this.t1 = this.t2 = this.t = -1;
        this.isT1Loaded = false;
        this.isT2Loaded = false;
    }

    /**
     * 
     * @return hodnota SDI ze zadaných parametrů
     * @throws Throwable
     */
    public float vypocti() throws Throwable {
        this.isCalculateCommand = true;
        this.readForm();
        /* kontrola zda hodnoty jsou prvky definičního oboru */
        if (false == this.isT1Loaded) {
            throw new Exception("Nebyl změřen t1!\n");
        }
        if (false == this.isT2Loaded) {
            throw new Exception("Nebyl změřen t2!\n");
        }
        if (this.t1 <= 0) {
            throw new Exception("t1 musí být větší než 0!\n");
        }
        if (this.t2 <= 0) {
            throw new Exception("t2 musí být větší než 0!\n");
        }
        if (this.t <= 0) {
            throw new Exception("t musí být větší než 0!\n");
        }
        if (this.t1 > this.t2) {
            throw new Exception("t1 musí být menší než t2!\n");
        }
        /* výpočet */
        return 6e6f * (1 - (float)this.t1 / this.t2) / ((float)this.t);
    }

    
    /**
     * Nastaví čas při kterém bude vyhlášen alarm
     * @param timePeriodToAutoStart časová prodleva od začátku spuštění timeru v minutách
     */
    private void registerTimerAlarm(int alertTime) {
	    // Set the launch time to current time + the specified period
	    long timeToWakeUp = System.currentTimeMillis() + (alertTime * 60000);
            Date date = new Date(timeToWakeUp);
 
	    try {
	        PushRegistry.registerAlarm(this.MIDLET_CLASS_NAME, timeToWakeUp);
                this.alertTimeGauge.setMaxValue((int)timeToWakeUp);
                Alert a = new Alert(null, "Upozornění bude spuštěno za " + alertTime + " mins\n", null, null);
                a.setTimeout(Alert.FOREVER);
                Display.getDisplay(this.main).setCurrent(a);
	        //this.showErrorAlert("Alert will come after " + alertTime + " mins\n"); // změnit typ alarmu
	    } catch (ClassNotFoundException ex) {
	        this.showErrorAlert("Alarm registration failed!\n" + ex.getMessage());
	    } catch (ConnectionNotFoundException ex) {
	        this.showErrorAlert("Alarm registration failed!\n" + ex.getMessage());
	    }
	}


/*-------------------------------------------------------------------------------------------------
  interface implemented methods:
-------------------------------------------------------------------------------------------------*/

    /**
     * přiřadí reakce k jednotlivým příkazům
     * @param c
     * @param d 
     */
    public void commandAction(Command c, Displayable d) {
        if (this.d == d) {
            if (this.cOk == c) {
                String s;
                float f;
                try {
                    f = this.vypocti();
                    String resultAlertString = "SDI = " + this.formatValue(f,1) + "\n";
                    s = this.d.getTitle() + "\nt1: " + this.t1/1000 + " s\nt2: " + this.t2/1000
                            + " s\nT: " + this.t/60000 + " min\n--> " + resultAlertString;
                    this.main.writeResult(s);
                    this.showResultAlert(resultAlertString);
                } catch (Throwable t) {
                    this.showErrorAlert(((Exception)t).getMessage());
                }
            }
            else if (this.cBack == c) {
                this.main.display(this.main.ROList);
            }
            else if (this.clearFormCommand == c) {
                this.reset();
            }
            else if (this.setAlertCommand == c) {
                
            }
        }
        else if (this.resultAlert == d) {
            if (this.doneCommand == c) {
                this.main.display(this);
                this.reset();
            }
            else if (this.continueCommand == c) {
                this.main.display(this);
            }
        }
    }
    
    /**
     * 
     * @param c 
     * @param i 
     */
    public void commandAction(Command c, Item i) {
        if (this.alertTextField == i) {
            System.out.println("alertTextField command");
            if (this.setAlertCommand == c) {
                System.out.println("setting alert time...");
                String s;
                int timeDelay;
                try {
                    s = this.alertTextField.getString();
                    timeDelay = Integer.parseInt(s);
                    this.registerTimerAlarm(timeDelay); /// změnit typ alarmu
                } catch (NumberFormatException e) {
                    this.showErrorAlert("Chyba v zadání času výstrahy\n" + ((Exception)e).getMessage());
                }
            }
        }
    }

    /**
     * 
     * @param item
     */
    public void itemStateChanged(Item item) {
        if (this.timePerSampleTimerItem01 == item) {
            boolean isRunningTimePerSample = this.timePerSampleTimerItem01.isRunning();
            boolean isRunningTimePerTest = this.timePerTestTimerItem01.isRunning();
            if (isRunningTimePerSample) {
                if (isRunningTimePerTest) {
                    if (false == this.isT1Loaded) {
                        this.infoStringItem.setText(this.infoText = "měří se t1");
                    }
                    else {
                        this.infoStringItem.setText(this.infoText = "měří se t2");
                    }
                }
                else { /// jaktoze nastava tato podminka
                    this.timePerTestTimerItem01.start();
                    this.infoStringItem.setText(this.infoText = "měří se t1");
                    System.out.println("Unexpected condition");
                }
            }
            else {
                if (false == this.isT1Loaded) {
                    this.t1 = this.timePerSampleTimerItem01.getTime();
                    this.infoStringItem.setText(this.infoText = ("t1 = " + this.t1));
                    this.isT1Loaded = true;
                }
                else {
                    this.t2 = this.timePerSampleTimerItem01.getTime();
                    this.infoStringItem.setText(this.infoText = ("t2 = " + this.t2));
                    this.t = this.timePerTestTimerItem01.getTime();
                    this.isT2Loaded = true;
                }
            }
        }
        else if (this.alertTextField == item) {
            //this.alertTime = Integer.parseInt(this.alertTextField.getString());
            //this.alertDateField.setDate(new Date(this.timePerTestTimerItem01.getStartTime() + this.alertTime));
        }
    }

    /**
     * 
     * @param customItem 
     */
    public void actionPerformed(CustomItem customItem) {
    }

    /**
     *
     * @param din
     * @throws Throwable
     */
    public void fromDataStream(DataInputStream din) throws Throwable {
        this.infoText = din.readUTF();
        //this.main.writeResult("infoText = " + ((null == this.infoText) ? "null" : this.infoText) + "\n");
        this.sampleStartTime = din.readLong();
        //this.main.writeResult("sampleStartTime = " + this.sampleStartTime + "\n");
        this.testStartTime = din.readLong();
        //this.main.writeResult("testStartTime = " + this.testStartTime + "\n");
        this.t1 = din.readInt();
        //this.main.writeResult("t1 = " + this.t1 + "\n");
        this.t2 = din.readInt();
        //this.main.writeResult("t2 = " + this.t2 + "\n");
        this.isT1Loaded = din.readBoolean();
        this.isT2Loaded = din.readBoolean();
    }

    /**
     *
     * @param dout
     * @throws Throwable
     */
    public void toDataStream(DataOutputStream dout) throws Throwable {
        dout.writeUTF((null == this.infoText) ? "" : this.infoText);
        //this.main.writeResult("infoText = " + ((null == this.infoText) ? "null" : this.infoText) + "\n");
        dout.writeLong(this.sampleStartTime);
        //this.main.writeResult("sampleStartTime = " + this.sampleStartTime + "\n");
        dout.writeLong(this.testStartTime);
        //this.main.writeResult("testStartTime = " + this.testStartTime + "\n");
        dout.writeInt(this.t1);
        //this.main.writeResult("t1 = " + this.t1 + "\n");
        dout.writeInt(this.t2);
        //this.main.writeResult("t2 = " + this.t2 + "\n");
        dout.writeBoolean(this.isT1Loaded);
        dout.writeBoolean(this.isT2Loaded);
    }
}

