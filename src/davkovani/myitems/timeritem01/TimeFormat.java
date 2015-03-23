/**************************************************************************************************
  davkovani/myitems/timeritem01/TimeFormat.java
  @author: Jan Chára
  date: 10.10.2012
  description: uchovává informaci o formátování času z třídy Time
**************************************************************************************************/

package davkovani.myitems.timeritem01;

import davkovani.mystring.MyString;


public class TimeFormat
{
/**************************************************************************************************
  deklarace proměnných:
**************************************************************************************************/

    private static int i = 0;
    public static final char H = 'h'; public static final int HOURS = TimeFormat.i++;
    public static final char M = 'm'; public static final int MINUTES = TimeFormat.i++;
    public static final char S = 's'; public static final int SECONDS = TimeFormat.i++;
    public static final char O = 'o'; public static final int MILLIS = TimeFormat.i++;
    public static final int MILLIS_PER_HOUR = 3600000;
    public static final int MILLIS_PER_MIN = 60000;
    public static final int MILLIS_PER_SEC = 1000;
    public static final char SEPARATOR = ':';
    public static final char POINT = '.';
    public static final char[] FORMAT_CHAR = new char[TimeFormat.i];
    public static final int[] MILLIS_PER_TIME = new int[TimeFormat.i];
    static {
        TimeFormat.i = 0;
        TimeFormat.FORMAT_CHAR[TimeFormat.i] = TimeFormat.H; TimeFormat.MILLIS_PER_TIME[TimeFormat.i++] = TimeFormat.MILLIS_PER_HOUR;
        TimeFormat.FORMAT_CHAR[TimeFormat.i] = TimeFormat.M; TimeFormat.MILLIS_PER_TIME[TimeFormat.i++] = TimeFormat.MILLIS_PER_MIN;
        TimeFormat.FORMAT_CHAR[TimeFormat.i] = TimeFormat.S; TimeFormat.MILLIS_PER_TIME[TimeFormat.i++] = TimeFormat.MILLIS_PER_SEC;
        TimeFormat.FORMAT_CHAR[TimeFormat.i] = TimeFormat.O; TimeFormat.MILLIS_PER_TIME[TimeFormat.i++] = 1;
    }

    private int[] formatCharLength = new int[TimeFormat.i];
    private int[] timeValue = new int[TimeFormat.i];
    private int time;
    private String format;
    int h, m, s, ms;


/**************************************************************************************************
  konstruktory:
**************************************************************************************************/

    public TimeFormat() {
        /// vytvořit defaultni format
    }

    public TimeFormat(final String format) throws Throwable {
        this.setFormat(format);
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

    public void setFormat(final String format) throws Throwable {
        String[] s = MyString.explode(format, Time.SEPARATOR);
        int l = s.length;
        char c;
        boolean noSuchChar = true;
        int x = 0, d;
        if (l <= 0) {
            throw new Throwable("Invalid time format!"); /// vytvořit třídu Exception
        }
        if (l > TimeFormat.i) {
            throw new Throwable("Invalid time format!");
        }
        for (int i=0; i<l; i++) {
            for (int ii=x; ii<TimeFormat.i; ii++) {
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
        for (int i=0; i<TimeFormat.i; i++) {
            System.out.println(Time.FORMAT_CHAR[i] + ": " + this.formatCharLength[i]);
        }
    }

    public String format(Time time) {
        int t = this.time;
        int d, l;
        boolean noStringYet = true;
        String f = "";
        String s = "";
        for (int i=0; i<TimeFormat.i; i++) {
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

    public Time parse(String time) {
        return new Time();
    }

    public void setFormat(Time time) {
        
    }


/**************************************************************************************************
  interface implemented methods:
**************************************************************************************************/

}

