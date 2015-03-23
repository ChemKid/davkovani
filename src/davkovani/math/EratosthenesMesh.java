/**************************************************************************************************
  davkovani/Davkovani.java
  @author: Jan Chára
  date: 9.7.2009
  description: MIDlet aplikace
**************************************************************************************************/


package davkovani.math;

import java.lang.Math.*;

public class EratosthenesMesh
{
/**************************************************************************************************
  deklarace proměnných:
**************************************************************************************************/

    private boolean[] mesh;
    private int length;


/**************************************************************************************************
  konstruktory:
**************************************************************************************************/

    public EratosthenesMesh(final int num) throws Throwable {
        this.length = num;
        this.mesh = new boolean[num];
        for (int x=0; x<num; x++) {  // upravit aby rovnou byly vyloučeny sudá čísla
            this.mesh[x] = true;
        }
        int sqrtNum = (int)Math.sqrt(num);
        for (int x=2; x<=sqrtNum; x++) {
            if (this.mesh[x]) {
                for (int xx=x*x; xx<num; xx+=x) {
                    if (mesh[xx])
                        this.mesh[xx] = false;
                }
            }
        }
    }


/**************************************************************************************************
  metoda main:
**************************************************************************************************/

    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        EratosthenesMesh eMesh;
        try {
            eMesh = new EratosthenesMesh(Integer.valueOf(args[0]).intValue());
            time = System.currentTimeMillis() - time;
            System.out.println("time elapsed: " + time + " ms\n");
            /*time = System.currentTimeMillis();
            System.out.println(eMesh.writePrimes());
            time = System.currentTimeMillis() - time;
            System.out.println("time elapsed: " + time + " ms\n");*/
        } catch (Throwable t) {
            System.out.println("error occured");
            System.out.println(t.getMessage());
        }
        /*int b = Integer.MAX_VALUE;
        System.out.println("Max int: " + b + "\n");
        b += 1;
        System.out.println("Max int + 1: " + b + "\n");*/
    }


/**************************************************************************************************
  překrytí zděděné nebo přetížené zděděné metody:
**************************************************************************************************/


/**************************************************************************************************
  vlastní metody:
**************************************************************************************************/

    public boolean isPrime(final int num) throws Throwable {
        return true;
    }

    public int getHighestCommonDivisor(final int num1, final int num2) throws Throwable {
        return 0;
    }

    public int getHighestCommonDivisor(final int[] num) throws Throwable {
        return 0;
    }

    public int getLowestCommonMultiple(final int num1, final int num2) throws Throwable {
        return 0;
    }

    public int getLowestCommonMultiple(final int[] num1) throws Throwable {
        return 0;
    }

    public String writePrimes() {
        String s = "";
        for (int x=0; x<this.mesh.length; x++) {
            s += this.mesh[x] ? (x + " ") : "";
        }
        return s + "\n";
    }

/**************************************************************************************************
  metody implementované přes rozhraní:
**************************************************************************************************/
}
