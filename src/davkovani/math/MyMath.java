/**************************************************************************************************
  davkovani/math/MyMath.java
  @author: Jan Chára
  date: 9.7.2009
  description: MIDlet aplikace
**************************************************************************************************/

package davkovani.math;


public final class MyMath
{
/**************************************************************************************************
  deklarace proměnných:
**************************************************************************************************/


    public static int MAX_PRECISION; // maximální stupeň Taylorova polynomu používaného při výpočtu
    public static double LN_2 = 0.69314718;

/**************************************************************************************************
  konstruktory:
**************************************************************************************************/


/**************************************************************************************************
  metoda main:
**************************************************************************************************/

    public static void main(String[] args) {
        long time;
        int[] num = new int[args.length];
        /*for (int i=0; i<num.length; i++) {
            num[i] = Integer.parseInt(args[i]);
        }*/
        double[] dnum = new double[args.length];
        for (int j=0; j<num.length; j++) {
            dnum[j] = Double.parseDouble(args[j]);
        }
        String s;
        try {
            //////// zkouška fce ln() 
            /*
            time = System.currentTimeMillis();
            double ln = MyMath.ln(num[0]);
            time = System.currentTimeMillis() - time;
            System.out.println("time = " + time + "ms");
            System.out.println("ln " + num[0] + " = " + ln);
            time = System.currentTimeMillis();
            ln = MyMath.log(num[0]);
            time = System.currentTimeMillis() - time;
            System.out.println("time = " + time + "ms");
            System.out.println("log " + num[0] + " = " + ln);
            */

            ///////// zkouška fce gcd() a lcm()
            time = System.currentTimeMillis();
            double gcd;
            double lcm;
            /*gcd = MyMath.gcd(num[0], num[1]);
            //gcd = MyMath.gcd(1200, 200e10);
            time = System.currentTimeMillis() - time;
            System.out.println("gcd(" + num[0] + ", " + num[1] + ") = " + gcd);
            System.out.println("time = " + time + " ms");
            time = System.currentTimeMillis();
            lcm = MyMath.lcm(num[0], num[1]);
            time = System.currentTimeMillis() - time;
            System.out.println("lcm(" + num[0] + ", " + num[1] + ") = " + lcm);
            System.out.println("time = " + time + " ms");*/
            /*time = System.currentTimeMillis() - time;
            gcd = MyMath.gcd(num);
            time = System.currentTimeMillis() - time;
            s = "";
            for (int i=0; i<num.length; i++) {
                s += num[i] + ", ";
            }
            System.out.println("gcd(" + s + ") = " + gcd);*/
            double l_max = (double)Long.MAX_VALUE;
            System.out.println("l-max = " + l_max);
            double i_max = (double)Integer.MAX_VALUE;
            System.out.println("i-max = " + i_max);
            double x = i_max * i_max;
            System.out.println("x = " + x);
            double rezerva = l_max - x;
            System.out.println("rezerva = " + rezerva);
            System.out.println("floor(" + dnum[0] + ") = " + Math.floor(dnum[0]));
            System.out.println("dnum1/dnum2 = " + dnum[0]/dnum[1]);
            System.out.println("dnum1%dnum2 = " + dnum[0]%dnum[1]);
            System.out.println("dnum1 - (dnum1 / dnum2) = " + (dnum[0] - (dnum[0] / dnum[1])));
            gcd = MyMath.gcd(dnum[0], dnum[1]);
            System.out.println("gcd(" + dnum[0] + ", " + dnum[1] + ") = " + gcd);
            time = System.currentTimeMillis() - time;
            lcm = MyMath.lcm(dnum[0], dnum[1]);
            time = System.currentTimeMillis() - time;
            System.out.println("lcm(" + dnum[0] + ", " + dnum[1] + ") = " + lcm);
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }


/**************************************************************************************************
  překrytí zděděné nebo přetížené zděděné metody:
**************************************************************************************************/


/**************************************************************************************************
  vlastní metody:
**************************************************************************************************/

    public static float pow(final float x, final int exp) throws Throwable {
        float f;
        if (0 > exp) {
            throw new IllegalArgumentException("negative exponents not allowed by this function");
        }
        if (0 == exp) {
            return 1f;
        }
        try {
            f = x;
            for (int i=1;i<exp;i++) {
                f *= x;
            }
        } catch (Throwable t) {
            throw new Exception(t.getMessage());
        }
        return f;
    } // funkci nutno upravit i pro záporné exponenty a ošetřit překročení MAX resp MIN float hodnoty


    /* vrátí největšího společného dělitele celých čísel typu int obsažených v poli num */
    public static int gcd(int[] num) throws Throwable {
        if (null == num) {
            throw new Throwable("int[] array is empty");
        }
        int gcd,z,r;
        gcd = z = r = num[0];
        for (int i=1; i<num.length; i++) {
            if (1 == gcd || (-1) == gcd) return gcd;
            if (num[i] < gcd) {
                z = gcd;
                gcd = num[i];
            }
            else {
                z = num[i];
            }
            while (0 != (r = z % gcd)) {
                z = gcd;
                gcd = r;
            }
        }
        return gcd;
    }

    /* vrátí největšího společného dělitele dvou celých čísel typu int */
    public static int gcd(int num1, int num2) {
        int tmp;
        while (num2 != 0) {
            tmp = num1;
            num1 = num2;
            num2 = tmp % num2;
        }
        return num1;
    } // gcd(int, int)


    /* vrátí největšího společného dělitele dvou celých čísel typu long */
    public static long gcd(long num1, long num2) throws Throwable {
        /// testování zda jsou num1 i num2 celými čísly:
        long tmp;
        while (num2 != 0) {
            tmp = num1;
            num1 = num2;
            num2 = tmp % num2;
        }
        return num1;
    } // gcd(long, long)


    /* vrátí největšího společného dělitele dvou celých čísel typu double */
    public static double gcd(double num1, double num2) throws Throwable {
        /// testování zda jsou num1 i num2 celými čísly:
        double tmp;
        if ((tmp = num1) != (num1 = Math.floor(num1))) {
            throw new Throwable("argument num1 is not mathematical integer!");
        }
        if ((tmp = num2) != (num2 = Math.floor(num2))) {
            throw new Throwable("argument num2 is not mathematical integer!");
        }
        while (num2 != 0) {
            tmp = num1;
            num1 = num2;
            num2 = tmp % num2;
        }
        return num1;
    } // gcd(double, double)
    //// ošetřit chyby které vznikají ztrátou přesnosti čísla typu double


    /* vrátí nejmenší společný násobek čísel n1 a n2 */
    public static int lcm(int num1, int num2) throws Throwable {
        int gcd;
        double tmp = ((double)num1 * (double)num2) / (gcd = MyMath.gcd(num1, num2));
        if (Integer.MAX_VALUE < tmp || Integer.MIN_VALUE > tmp) {
            throw new Throwable("lowest common multiplyer is out of integer values range");
        }
        return (num1 * num2) / gcd;
    } // lcm(int, int)


    /* vrátí nejmenší společný násobek čísel n1 a n2 */
    public static long lcm(long num1, long num2) throws Throwable {
        long gcd;
        double tmp = ((double)num1 * (double)num2) / (gcd = MyMath.gcd(num1, num2));
        if (Long.MAX_VALUE < tmp || Long.MIN_VALUE > tmp) {
            throw new Throwable("lowest common multiplyer is out of long values range");
        }
        return (num1 * num2) / gcd;
    } // lcm(long, long)


    /* vrátí nejmenší společný násobek čísel n1 a n2 */
    public static double lcm(double num1, double num2) throws Throwable {
        /// testování zda jsou num1 i num2 celými čísly:
        double tmp;
        if ((tmp = num1) != (num1 = Math.floor(num1))) {
            throw new Throwable("argument num1 is not mathematical integer!");
        }
        if ((tmp = num2) != (num2 = Math.floor(num2))) {
            throw new Throwable("argument num2 is not mathematical integer!");
        }
        tmp = MyMath.gcd(num1, num2);
        num1 = num1/tmp;
        // testování zda součin num1 a num2 nepřekračuje Double.MAX_VALUE nebo Double.MIN_VALUE
        if (Double.isInfinite(tmp = num1 * num2)) {
            throw new Throwable("lowest common multiplyer of " + num1 + " and " + num2 + " is out of double values range");
        }
        return tmp;
    } // lcm(double, double)


    public static double ln(double n) throws Throwable {
        final int MAX = 4/3;
        final int MIN = 2/3;
        int k = 0;
        double pol;
        int sign = -1;
        double tmp;
        if (n<=0) {
            throw new Throwable("Cannot algorithm numbers equal or minor zero");
        }
        while (n > MAX) {
            n/=2;
            k++;
        }
        while (n < MIN) {
            n/=0.5;
            k--;
        }
        n-=1;
        pol = n;
        tmp = n;
        // výpočet polynomu
        for (int x=2; x<=20; x++) {
            pol += sign*(tmp*=n)/x;
            sign *= -1;
        }
        return pol + k*MyMath.LN_2;
    }


    public static double log(double n) throws Throwable {
        return MyMath.ln(n)/MyMath.ln(10);
    }

/**************************************************************************************************
  metody implementované přes rozhraní:
**************************************************************************************************/
}
