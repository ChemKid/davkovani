/**************************************************************************************************
  davkovani/math/IntFraction.java
  @author: Jan Chára
  date: 26.7.2011
  description: MIDlet aplikace
**************************************************************************************************/

package davkovani.math;


public final class IntFraction
{
/**************************************************************************************************
  deklarace proměnných:
**************************************************************************************************/


    private int numerator;
    private int denominator;
    public static final int NORMAL_MODE = 0;
    public static final int MIXED_NUMBER_MODE = 1;

/**************************************************************************************************
  konstruktory:
**************************************************************************************************/


    public IntFraction(int numerator) throws Throwable {
        this(numerator, 1);
    }

    public IntFraction(int numerator, int denominator) throws Throwable {
        if (0 == denominator) {
            throw new Throwable("denominator cannot be 0"); // upravit aby se dalo pracovat s int v celém rozsahu hodnot
        }
        this.numerator = numerator;
        this.denominator = denominator;
        this.shorten();
    }

    public IntFraction(int total, int numerator, int denominator) throws Throwable {
        this(numerator, denominator);
        int sign = ((this.numerator < 0 && total < 0) || (this.numerator >= 0 && total >= 0)) ? 1 : (-1);
        long tmp = sign*(Math.abs((long)total * (long)this.denominator) + Math.abs((long)this.numerator));
        if (Integer.MIN_VALUE > tmp) {
            throw new Throwable("nominator cannot be lower than integer min value!");
        }
        if (Integer.MAX_VALUE < tmp) {
            throw new Throwable("nominator cannot be bigger than integer max value!");
        }
        this.numerator = (int)tmp;
    }

    public IntFraction(final IntFraction f) {
        this.numerator = f.numerator;
        this.denominator = f.denominator;
    }

/**************************************************************************************************
  metoda main:
**************************************************************************************************/

    public static void main(String[] args) {
        long time;
        IntFraction f1;
        IntFraction f2;
        int[] num = new int[args.length];
        for (int i=0; i<num.length; i++) {
            num[i] = Integer.parseInt(args[i]);
        }
        try {
            /*time = System.currentTimeMillis();
            f = new IntFraction(num[0], num[1]);
            time = System.currentTimeMillis() - time;
            System.out.println(f.print(IntFraction.MIXED_NUMBER_MODE));
            System.out.println("time = " + time);
            time = System.currentTimeMillis();
            f.multiply(new IntFraction(num[2], num[3]));
            time = System.currentTimeMillis() - time;
            System.out.println(f.print(IntFraction.MIXED_NUMBER_MODE));
            System.out.println("time = " + time);*/
            /*time = System.currentTimeMillis();
            f.add(num[2]);
            time = System.currentTimeMillis() - time;
            System.out.println(f.print(IntFraction.MIXED_NUMBER_MODE));
            System.out.println("time = " + time);*/
            /* zkouška fce add() */
            f1 = new IntFraction(num[0], num[1]);
            System.out.println("f1 = " + f1.print(0));
            f2 = new IntFraction(num[2], num[3]);
            System.out.println("f2 = " + f2.print(0));
            time = System.currentTimeMillis();
            f1.add(f2);
            time = System.currentTimeMillis() - time;
            System.out.println("f1 + f2 = " + f1.print(1));
            System.out.println("time = " + time);
        } catch (Throwable t) {
            System.out.println("error occured!");
            System.out.println(t.getMessage());
        }
    }


/**************************************************************************************************
  překrytí zděděné nebo přetížené zděděné metody:
**************************************************************************************************/


/**************************************************************************************************
  vlastní metody:
**************************************************************************************************/


    private void shorten() {
        int tmp = MyMath.gcd(this.denominator, this.numerator);
        this.denominator /= tmp;
        this.numerator /= tmp;
    }

    /* vytiskne řetězec reprezentující IntFraction podle zadaného kritéria - modu
       normal mode - vytiskne jako zlomek, např. 1/2, 3/2, -7/5 ap.
       mixed number mode - vytiskne jako smíšené číslo, např. 2/1/3, -7/2/5
    */
    public String print(int mode) throws Throwable {
        String s = "";
        if (IntFraction.NORMAL_MODE == mode) {
            s = Math.abs((long)this.numerator) + "/" + Math.abs((long)this.denominator);
        }
        else if (IntFraction.MIXED_NUMBER_MODE == mode) {
            s = Math.abs((long)(this.numerator / this.denominator)) + "/" + Math.abs((long)(this.numerator % this.denominator)) + "/" + Math.abs((long)this.denominator);
        }
        else {
            throw new Throwable("illegal fraction mode number");
        }
        return this.getSign() > 0 ? s : "-" + s;
    } // print(int)

    /* vrací znaménko zlomku */
    public int getSign() {
        if ((this.numerator >= 0 && this.denominator >= 0) || (this.numerator < 0 && this.denominator < 0))
            return 1;
        return (-1);
    } // getSign()

    /* ke zlomku IntFraction přičte celé číslo */
    public void add(final int num) throws Throwable {
        long tmp;
        tmp = num * this.denominator + this.numerator;
        if (tmp < Integer.MIN_VALUE) {
            throw new Throwable("nominator cannot be lower than integer min value!");
        }
        if (tmp > Integer.MAX_VALUE) {
            throw new Throwable("nominator cannot be bigger than integer max value!");
        }
        this.numerator = (int)tmp;
    } // add(int)


    /* ke zlomku IntFraction přičte IntFraction f */
    public void add(final IntFraction f) throws Throwable {
        long d = MyMath.lcm(this.denominator, f.denominator);
        long n = (d/this.denominator)*this.numerator + (d/f.denominator)*f.numerator;
        long x = MyMath.gcd(d,n);
        d /= x;
        n /= x;
        if (d > Integer.MAX_VALUE || d < Integer.MIN_VALUE) {
            throw new Throwable("integer value range exceeded!");
        }
        if (n > Integer.MAX_VALUE || n < Integer.MIN_VALUE) {
            throw new Throwable("integer value range exceeded!");
        }
        this.numerator = (int)n;
        this.denominator = (int)d;
    } // add(IntFraction)


    /* zlomek násobí zlomkem f */
    public void multiply(final IntFraction f) throws Throwable {
        float n;
        float d;
        IntFraction f1 = new IntFraction(f.numerator, this.denominator);
        IntFraction f2 = new IntFraction(this.numerator, f.denominator);
        f1.shorten();
        f2.shorten();
        n = f1.numerator * f2.numerator;
        d = f1.denominator * f2.denominator;
        if(n > Integer.MAX_VALUE || n < Integer.MIN_VALUE || d > Integer.MAX_VALUE || d < Integer.MIN_VALUE) {
            throw new Throwable("integer value range exceeded!");
        }
        this.numerator = (int)n;
        this.denominator = (int)d;
    } // multiply(IntFraction)


    /* vytvoří převrácenou hodnotu zlomku záměnou čitatele a jmenovatele */
    public void invert() {
        int tmp = this.numerator;
        this.numerator = this.denominator;
        this.denominator = tmp;
    } // invert()


    /* převede zlomek IntFraction na číslo typu double */
    public double doubleValue() {
        return (double)this.numerator / (double)this.denominator;
    }


    /* převede zlomek IntFraction na číslo typu int,
     vyhodí chybu pokud jmenovatel není roven 1*/
    public int intValue() throws Throwable {
        if (1 == this.denominator) {
            return this.numerator;
        }
        throw new Throwable("fraction " + this.print(0) + " is not convertible to int value!");
    }


    /* vrátí hodnotu svého čitatele */
    public int getNumerator() {
        return this.numerator;
    } // getNumerator()


    /* vrátí hodnotu svého jmenovatele */
    public int getDenominator() {
        return this.denominator;
    } // getNumerator()

/**************************************************************************************************
  metody implementované přes rozhraní:
**************************************************************************************************/
}
