/**************************************************************************************************
  davkovani/myitems/timeritem01/Time.java
  @author: Jan Chára
  date: 10.10.2012
  description: uchovává číslo typu int do formátu času
**************************************************************************************************/

package davkovani.myitems.timeritem01;

import davkovani.mystring.MyString;


public class Time
{
/**************************************************************************************************
  deklarace proměnných:
**************************************************************************************************/

    private static int i = 0;
    public static final char H = 'h'; public static final int HOURS = Time.i++;
    public static final char M = 'm'; public static final int MINUTES = Time.i++;
    public static final char S = 's'; public static final int SECONDS = Time.i++;
    public static final char O = 'o'; public static final int MILLIS = Time.i++;
    public static final int MILLIS_PER_HOUR = 3600000;
    public static final int MILLIS_PER_MIN = 60000;
    public static final int MILLIS_PER_SEC = 1000;
    public static final char SEPARATOR = ':';
    public static final char POINT = '.';
    public static final char[] FORMAT_CHAR = new char[Time.i];
    public static final int[] MILLIS_PER_TIME = new int[Time.i];
    static {
        Time.i = 0;
        Time.FORMAT_CHAR[Time.i] = Time.H; Time.MILLIS_PER_TIME[Time.i++] = Time.MILLIS_PER_HOUR;
        Time.FORMAT_CHAR[Time.i] = Time.M; Time.MILLIS_PER_TIME[Time.i++] = Time.MILLIS_PER_MIN;
        Time.FORMAT_CHAR[Time.i] = Time.S; Time.MILLIS_PER_TIME[Time.i++] = Time.MILLIS_PER_SEC;
        Time.FORMAT_CHAR[Time.i] = Time.O; Time.MILLIS_PER_TIME[Time.i++] = 1;
    }

    private int[] formatCharLength = new int[Time.i];
    private int[] timeValue = new int[Time.i];
    private int time;
    private String format;
    int h, m, s, ms;


/**************************************************************************************************
  konstruktory:
**************************************************************************************************/

    public Time() {
        this(0);
        /// vytvořit defaultni format
    }

    public Time(int time) {
        this.time = time;
        for (int i=0; i<Time.i; i++) {
            this.formatCharLength[i] = 0;
        }
        /// vytvořit defaultni format
    }

    public Time(final int time, final String format) throws Throwable {
        this.time = time;
        this.setFormat(format); /// dodelat
    }

    public Time(String time) {
        /// dodelat
    }


/**************************************************************************************************
  metoda main:
**************************************************************************************************/

    public static void main(String[] args) {
        System.out.println("testing Time class");
        Time time = new Time();
        try {
            time.setFormat("h2:m3:s2:");
        } catch (Throwable t) {
            System.out.println("chyba:");
            System.out.println(t.getMessage());
        }
        System.out.println("format:\n");
        time.printFormat();
        int tm = 6500;
        time.setTime(tm);
        System.out.println("time = " + tm);
        System.out.println(time.getFormatedTime());
    }

/**************************************************************************************************
  překrytí zděděných nebo přetížených metod:
**************************************************************************************************/


/**************************************************************************************************
  vlastní metody:
**************************************************************************************************/

    public void setTime(int time) {
        this.time = time;
    }

    
    /**
     * 
     * @param format 
     * @throws Throwable 
     */
    public void setFormat(final String format) throws Throwable {
        String[] s = MyString.explode(format, Time.SEPARATOR);
        int l = s.length;
        char c;
        boolean noSuchChar = true;
        int x = 0, d;
        if (l <= 0) {
            throw new Throwable("Invalid time format!"); /// vytvořit třídu Exception
        }
        if (l > Time.i) {
            throw new Throwable("Invalid time format!");
        }
        for (int i=0; i<l; i++) {
            for (int ii=x; ii<Time.i; ii++) {
                if (0 >= (d=s[i].length())) {
                    throw new Throwable("Invalid time format!");
                }
                c = s[i].charAt(0);
                if (Time.FORMAT_CHAR[ii] == c) {
                    noSuchChar = false;
                    s[i] = s[i].substring(1);
                    if (0 >= --d) {
                        this.formatCharLength[ii] = 1;
                    }
                    else {
                        try {
                            this.formatCharLength[ii] = Integer.parseInt(s[i]);
                        } catch (Throwable t) {
                            System.out.println(t.getMessage());
                            System.out.println("Invalid time format!");
                        }
                        if (0 >= this.formatCharLength[ii]) {
                            throw new Throwable("Invalid time format!");
                        }
                        if (3 < this.formatCharLength[ii]) { // platí pouze pro některé členy pole!!! upravit
                            throw new Throwable("Invalid time format!");
                        }
                    }
                    x = ++ii;
                    break;
                }
            }
            if (noSuchChar) {
                throw new Throwable("Invalid time format!"); // invalid format char
            }
            else {
                noSuchChar = true;
            }
        }/// v případě InvalidFormatException vrátit původní formát
    }

    public void printFormat() {
        for (int i=0; i<Time.i; i++) {
            System.out.println(Time.FORMAT_CHAR[i] + ": " + this.formatCharLength[i]);
        }
    }

    public String getFormatedTime() {
        int t = this.time;
        int d, l;
        boolean noStringYet = true;
        String f = "";
        String s = "";
        for (int i=0; i<Time.i; i++) {
            f = "" + (this.timeValue[i] = t / Time.MILLIS_PER_TIME[i]);
            l = this.formatCharLength[i];
            if (0 >= l) {
                continue;
            }
            d = f.length();
            t %= Time.MILLIS_PER_TIME[i];
            if (noStringYet) {
                noStringYet = false;
            }
            else {
                s += Time.SEPARATOR;
            }
            while (l > d) {
                s += "0";
                l--;
            }
            s += f;
        }
        return s;
    }

    public int getTime() {
        return this.time;
    }


/**************************************************************************************************
  interface implemented methods:
**************************************************************************************************/

}

