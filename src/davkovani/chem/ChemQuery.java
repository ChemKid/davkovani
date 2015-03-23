/**************************************************************************************************
  davkovani/chem/ChemQuery.java
  @author: Jan Chára
  date: 3.8.2011
  description: MIDlet aplikace
**************************************************************************************************/

package davkovani.chem;

import java.util.*;
import davkovani.math.*;
import davkovani.array.*;


public final class ChemQuery
{
/**************************************************************************************************
  deklarace proměnných:
**************************************************************************************************/


    private String query;
    private int position;
    private int length;

/**************************************************************************************************
  konstruktory:
**************************************************************************************************/


    public ChemQuery(String query) {
        this.position = 0;
        this.query = (null == query) ? "" : query;
        this.length = this.query.length();
    }

    public ChemQuery() {
        this("");
    }

/**************************************************************************************************
  metoda main:
**************************************************************************************************/

    public static void main(String[] args) {
        long time;
        try {
            /*time = System.currentTimeMillis();
            IntFraction f = ChemQuery.getNumber(args[0]);
            time = System.currentTimeMillis() - time;
            if (null == f) {
                System.out.println("null"); // oštřit stav null!!!
            }
            else {
                System.out.println(f.print(1));
            }
            System.out.println("time: " + time + " ms");*/
            ChemQuery form = new ChemQuery(args[0]);
            time = System.currentTimeMillis();
            ChemFormula f = form.getFormula();
            time = System.currentTimeMillis() - time;
            if (null == f) {
                System.out.println("formula is null!");
            }
            else {
                System.out.println(form.getString());
            }
            System.out.println("mw = " + f.getMolWeight() + " kg/mol");
            System.out.println("elem formula: " + f.getElementaryFormula());
            System.out.println("time = " + time + "ms");
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


    public static IntFraction getNumber(String s) throws Throwable {
        ChemQuery tmp = new ChemQuery();
        tmp.query = s;
        tmp.length = tmp.query.length();
        try
        {
            return tmp.getNumber();
        } catch (Throwable t) {
            throw new Throwable(t.getMessage());
        }
        //// if (null == Number) --- > throw new Throwable !!!!
    }


    private String getString() {
        String s = "";
        char c = 0;
        for (; this.position < this.length; this.position++) {
            c = this.query.charAt(this.position);
            if (Character.isLowerCase(c) || Character.isUpperCase(c)) {
                s += c;
            }
            else {
                break;
            }
        }
        return s;
    }

    private IntFraction getNumber() throws Throwable {
        final char POINT = '.';
        final char EXP = 'e';
        final char MINUS = '-';
        final char SLASH = '/';
        final int MAX_EXP = (int)MyMath.log(Integer.MAX_VALUE);
        char c = 0;
        char prev = 0;
        long total = 0;
        long numerator = 0;
        long denominator = 1;
        long exp = 0;
        long num = 0;
        int radix = 10;
        int sign = 1;
        int startPosition = this.position;
        int order = 0;
        float tmp = 1;
        boolean point = false;
        boolean exponent = false;
        boolean slashed = false;
        boolean doubleSlashed = false;
        boolean start = true;
        IntFraction f;
        for (; this.position < this.length; this.position++) {
            c = this.query.charAt(this.position);
            if (Character.isDigit(c)) {
                if (exponent) {
                    exp = exp*10 + sign * Character.digit(c, radix);
                    System.out.println("exp = " + exp);
                    if (exp > MAX_EXP || exp < -(MAX_EXP)) {
                        throw new Throwable("value is out of integer value range!");
                    }
                }
                else {
                    num = num*10 + sign * Character.digit(c, radix);
                    order ++;
                    if (num > Integer.MAX_VALUE || num < Integer.MIN_VALUE) {
                        throw new Throwable("value is out of integer value range!");
                    }
                    if ((tmp = MyMath.pow(10,order)) > Integer.MAX_VALUE || tmp < Integer.MIN_VALUE) {
                        throw new Throwable("value is out of integer value range!");
                    }
                }
            }
            else if (POINT == c) {
                if (start || point || slashed || exponent || MINUS == prev) {
                    throw new InvalidChemQueryException(this.query, this.position);
                }
                point = true;
                total = num;
                num = 0;
                sign = 1;
                order = 0;
                //////// dodelat
            }
            else if (SLASH == c) {
                if (start || point || doubleSlashed || exponent || MINUS == prev || SLASH == prev) {
                    throw new InvalidChemQueryException(this.query, this.position);
                }
                if (slashed) {
                    doubleSlashed = true;
                    total = numerator;
                    numerator = num;
                    num = 0;
                    sign = 1;
                    order = 0;
                }
                else {
                    slashed = true;
                    numerator = num;
                    num = 0;
                    sign = 1;
                    order = 0;
                }
            }
            else if (MINUS == c) {
                if (Character.isDigit(prev) || MINUS == prev || POINT == prev) {
                    throw new InvalidChemQueryException(this.query, this.position);
                }
                sign = (-1);
            }
            else if (EXP == c) { // dodělat překračování int hodnot např. 400e9
                if (start || exponent || MINUS == prev || SLASH == prev || POINT == prev) {
                    throw new InvalidChemQueryException(this.query, this.position);
                }
                if (point) {
                    denominator = (int)tmp;
                    numerator = num;
                }
                else if (slashed) {
                    denominator = num;
                }
                else {
                    total = num;
                }
                exponent = true;
                sign = 1;
                num = 0;
                order = 0;
            }
            else {
                break;
            }
            if (start) {
                start = false;
            }
            prev = c;
        }
        if (startPosition == this.position) {
            return null; // prázdný zlomek
        }
        if (!Character.isDigit(c)) {
            //return null;
        }
        if (exponent) {
            /*if (point) {
               denominator = (int)tmp;
               numerator = num;
            }
            else if (slashed) {
               denominator = num;
            }
            else {
               total = num;
            }*/
            /// dodelat
        }
        else if (point) {
            denominator = (int)tmp;
            System.out.println("denominator = " + denominator);
            numerator = num;
        }
        else if (slashed) {
            denominator = num;
        }
        else {
            total = num;
        }
        f = new IntFraction((int)total, (int)numerator, (int)denominator);
        if (exp < 0) {
            exp *= -1;
            f.multiply(new IntFraction(1, (int)MyMath.pow(10,(int)exp))); // vytvořit fci iPow(int, int)
        }
        else {
            f.multiply(new IntFraction((int)MyMath.pow(10,(int)exp)));
        }
        return f;
    } // getNumber()


    public IntFraction getKoef() throws Throwable {
        IntFraction n = this.getNumber();
        return (null == n) ? new IntFraction(1,1) : n;
    }


    public String getQuery() {
        return this.query;
    }


    public static ChemFormula getFormula(String s) throws Throwable { // throws Throwable!!!
        ChemQuery tmp = new ChemQuery();
        tmp.query = s;
        tmp.length = tmp.query.length();
        return tmp.getFormula();
    }


    private String getElement() {
        char c = 0;
        String s = "" + this.query.charAt(this.position);
        if (++this.position < this.length && Character.isLowerCase(c=this.query.charAt(this.position))) {
            this.position++;
            s += c;
        }
        return s;
    }


    private ChemFormula getFormula() throws Throwable { // throws Throwable!!!
        ChemFormula f = new ChemFormula();
        IntFraction m_b = new IntFraction(1);
        IntFraction[] kf;
        Vector elem = new Vector(5,5);
        Vector koef = new Vector(5,5);
        Vector b_elem = new Vector(5,5);
        char c = 0;
        int x = this.position;
        int n, d;


        ////////////////////////////////////////
        x = 0;
        int OB = 0;
        IntFraction ff;
        Vector mm = new Vector(5,5);
        mm.addElement(this.getKoef());
        while(this.position < this.length) {
            c = this.query.charAt(this.position);
            if ('*' == c) {
                this.position++;
                mm.removeElementAt(mm.size()-1);
                if (0 == OB) {
                    mm.addElement(this.getKoef());
                }
                else {
                    ff = new IntFraction((IntFraction)mm.lastElement());
                    ff.multiply(this.getKoef());
                    mm.addElement(ff);
                }
                System.out.println("point: \ti = " + this.position +
                        ", mm = " + ((IntFraction)mm.lastElement()).print(0));
            }
            else if ('(' == c || '[' == c || '{' == c) {
                this.position++;
                OB++;
                b_elem.addElement(new Integer(elem.size()));
                //b_mult.addElement(m);
                ff = new IntFraction((IntFraction)mm.lastElement());
                ff.multiply(this.getKoef());
                mm.addElement(ff);
                /*System.out.println("OB: \ti = " + this.position +
                        ", mm = " + ((IntFraction)mm.lastElement()).print(0) +
                        ", b_elem = " + ((Integer)b_elem.lastElement()).intValue());*/
            }
            else if (')' == c || ']' == c || '}' == c) {
                this.position++;
                m_b = this.getKoef();
                if (0 > --OB) {
                    // uzavírací závorka navíc
                    throw new InvalidFormulaException(this.query, this.position);
                }
                mm.removeElementAt(mm.size()-1);
                //System.out.println("mb = " + m_b.print(0));
                for (x = ((Integer)b_elem.lastElement()).intValue(); x < koef.size(); x++) {
                    //ff = new IntFraction((IntFraction)b_mult.lastElement());
                    //ff.multiply(m_b);
                    ((IntFraction)koef.elementAt(x)).multiply(m_b);
                }
                b_elem.removeElementAt(b_elem.size()-1);
                //b_mult.removeElementAt(b_mult.size()-1);
                /*System.out.println("CB: \ti = " + this.position +
                        ", mm = " + ((IntFraction)mm.lastElement()).print(0) +
                        ", m_b = " + m_b.print(0));*/
            }
            else {
                elem.addElement(this.getElement());
                ff = new IntFraction((IntFraction)mm.lastElement());
                ff.multiply(this.getKoef());
                koef.addElement(ff);
                /*System.out.println("elem: \ti = " + this.position  +
                        ", elem = " + (String)elem.lastElement() +
                        ", koef" + ((IntFraction)koef.lastElement()).print(0));*/
            }
        }
        if (0 != OB) {
            // ve vzorci nestejný počet závorek
            throw new InvalidFormulaException(this.query, this.position);
        }
        //System.out.println();

        ////////////////////////////
        /* sečtení stejných prvků + jejich setřídění */
        String s = "";
        f.elemCount = elem.size();
        if (0==f.elemCount) {  // pokud vzorec neobsahuje žádné prvky
            ///// zajistit aby se vracel prázdný vzorec, tj. obsahuje prvek null o mw = 0
            throw new Throwable("formula does not contain any elements!");
            //return new ChemFormula();
        }
        for (int i=0;i<f.elemCount;i++) {
            s = new String((String)elem.elementAt(i));
            x = elem.lastIndexOf(s);
            while (x>i) {
                elem.removeElementAt(x);
                ff = ((IntFraction)koef.elementAt(x));
                koef.removeElementAt(x);
                ff.add((IntFraction)koef.elementAt(i));
                koef.setElementAt(ff, i);
                f.elemCount--;
                x = elem.lastIndexOf(s);
            }
        }
        f.elem = new int[f.elemCount];
        f.koef = new int[f.elemCount];
        kf = new IntFraction[f.elemCount];
        for (int i=0; i<f.elemCount; i++) {
            try {
                f.elem[i] = ChemElementsTable.getProtNumber((String)elem.elementAt(i), ChemElementsTable.SYMBOL);
            } catch (Exception e) {
                throw new InvalidFormulaException(f.strFormula, (String)elem.elementAt(i));
            }
            kf[i] = (IntFraction)koef.elementAt(i);
        }
        /*for (int i=0; i < f.elemCount; i++) {
            System.out.println("elem[" + i + "] = " + ChemElementsTable.symbol[f.elem[i]] + ", koef[" + i + "] = " + kf[i].print(0));
        }*/
        n = kf[0].getNumerator();
        d = kf[0].getDenominator();
        for (int i=1; i<f.elemCount; i++) {
            n = MyMath.gcd(n, kf[i].getNumerator());
            d = MyMath.lcm(d, kf[i].getDenominator());
            f.koef[i] = i;
        }
        f.multiple = new IntFraction(n, d);
        System.out.println("multiple = " + f.multiple.print(0));
        ff = new IntFraction(f.multiple);
        ff.invert();
        for (int i=0; i<f.elemCount; i++) {
            kf[i].multiply(ff);
            f.koef[i] = kf[i].intValue();
            //System.out.println("koef[" + i + "] = " + f.koef[i]);
        }
        
        /////////////////////////////////

        return f;
    } // ChemFormula getFormula()


    /* převede řetězec query představující příkaz do své vnitřní stuktury */
    /// funkce pouze pro ladění, později zrušit
    private void analyzeQuery() throws Throwable {
        Vector tmp = new Vector(5,5);
        int n = 0;
        boolean space = false;
        char c = 0;

        while(this.position < this.length) {
            c = this.query.charAt(this.position);
            if ('('==c) {
                n++;
                tmp.addElement(new Vector(10,10));
            }
            else if (')'==c) {
                n--;
            }
            else if ('+'==c) {
                
            }
            else if ('-'==c) {
                
            }
            else if ('='==c) {
                
            }
            else if (' '==c) {
                if (!space) {
                    space = true;
                }
            }
            this.position++;
        }
        if (0 != n) {
            throw new InvalidChemQueryException(this.query, this.position);
        }
    } // analyzeQuery()


/**************************************************************************************************
  metody implementované přes rozhraní:
**************************************************************************************************/
}
