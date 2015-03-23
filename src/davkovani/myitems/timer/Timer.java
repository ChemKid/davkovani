/**************************************************************************************************
  davkovani/myitems/timer/timer.java
  @author: Jan Chára
  date: 15.9.2012
  description: časovač
**************************************************************************************************/

package davkovani.myitems.timer;

public class Timer {
/**************************************************************************************************
  deklarace proměnných:
**************************************************************************************************/


    public static int MILLIS_IN_SECS = 1000;
    public static int MILLIS_IN_MIN = 60000;
    public static int MILLIS_IN_HOUR = 360000;
    private long startTime;
    private int time;
    private boolean pause;
    private boolean stopped;

/**************************************************************************************************
  konstruktory:
**************************************************************************************************/


    public Timer() {
        this(0);
    }

    public Timer(int startTime) {
        this.time = startTime;
        this.stopped = true;
        this.startTime = 0;
    }

/**************************************************************************************************
  metoda main:
**************************************************************************************************/

    public static void main(String[] args) {
        Timer timer = new Timer();
        long t = System.currentTimeMillis();
        int d = 0;
        timer.start();
        while ((d = (int)(System.currentTimeMillis() - t)/1000) < 2) {
        }
        //timer.getTime();
        //System.out.println(Timer.writeTime(timer,""));
        while ((d = (int)(System.currentTimeMillis() - t)/1000) < 1) {
        }
        timer.stop();
        //System.out.println(timer.getTime());
        System.out.println(Timer.writeTime(timer,""));
        System.out.println(Integer.toString(1, 6));
    }


/**************************************************************************************************
  překrytí zděděné nebo přetížené zděděné metody:
**************************************************************************************************/

/**************************************************************************************************
  vlastní metody:
**************************************************************************************************/


    public void start() {
        if (!stopped) return;
        this.startTime = System.currentTimeMillis();
        this.stopped = false;
    }

    public void pause() {
        if (stopped) return;
        this.time += (int)(System.currentTimeMillis() - this.startTime);
    }

    public void stop() {
        if (stopped) return;
        this.time += (int)(System.currentTimeMillis() - this.startTime);
        this.time = 0;
    }

    public int getTime() {
        return this.time = (int)System.currentTimeMillis() - this.time;
    }

    public int getMinsRest() {
        return this.time / Timer.MILLIS_IN_MIN;
    }

    public int getSecsRest() {
        return this.time / Timer.MILLIS_IN_SECS;
    }

    public int getMillisRest() {
        return this.time % Timer.MILLIS_IN_SECS;
    }

    public static String writeTime(Timer timer,String format) {
        return "" + timer.time;
        //return timer.getMinsRest() + ":" + timer.getSecsRest() + ":" + timer.getMillisRest();
    }

/**************************************************************************************************
  metody implementované přes rozhraní:
**************************************************************************************************/
}
