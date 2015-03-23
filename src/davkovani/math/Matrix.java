/**************************************************************************************************
  davkovani/math/Matrix.java
  @author: Jan Chára
  date: 2.10.2011
  description: uchovává matici a provádí matematické operace s maticemi
**************************************************************************************************/

package davkovani.math;

import davkovani.array.MyArray;


public final class Matrix
{
/**************************************************************************************************
  deklarace proměnných:
**************************************************************************************************/


    private int rows;
    private int cols;
    private int[][] mtx;
    private int[] row;
    private int[] col;
    private double determinant;
    private boolean determined;

/**************************************************************************************************
  konstruktory:
**************************************************************************************************/


    public Matrix() throws Throwable { // vytvoří nulovou matici
        this(null);
    }


    public Matrix(final int[][] mtx) throws Throwable { /// nutno upravit - kontrola null v jednotlivých sloupcích
        /*if (null == mtx) {
            this.rows = 0;
        }
        else {
            this.rows = mtx.length;
        }
        this.cols = (0 == this.rows) ? this.cols : mtx[0].length;
        this.mtx = new int[this.rows][this.cols];
        this.row = new int[this.rows];
        this.col = new int[this.cols];
        for (int i = 0; i < this.rows; i++) {
            if (mtx[i].length != this.cols)
                throw new Throwable("all vectors of matrix must have the same length!");
            this.row[i] = i;
            for (int j = 0; j < this.cols; j++) {
                this.mtx[i][j] = mtx[i][j];
                if (0 == i) {
                    this.col[j] = j;
                }
            }
        }
        this.determinant = 0;
        this.determined = false;*/
        this.mtx = new int[this.rows = (null==mtx) ? 0 : mtx.length][];
        this.cols = 0;
        this.row = new int[this.rows];
        int c = 0;
        for (int i=0; i < this.rows; i++) {
            if (0 == i) {
                this.cols = (null==mtx[i]) ? 0 : mtx[i].length;
                this.col = new int[this.cols];
            }
            else if (this.cols != (c = (null == mtx[i]) ? 0 : mtx[i].length)) {
                throw new Throwable("all vectors of matrix must have the same length!");
            }
            this.mtx[i] = new int[this.cols];
            this.row[i] = i;
            for (int j = 0; j < this.cols; j++) {
                this.mtx[i][j] = mtx[i][j];
                this.col[j] = j;
            }
        }
        if (0 == this.cols && 0 < this.rows) {
            this.rows = 0;
            this.mtx = new int[this.rows][this.cols];
            this.row = new int[this.rows];
        }
    }

/**************************************************************************************************
  metoda main:
**************************************************************************************************/

    public static void main(String[] args) {
        long time;
        try {
            time = System.currentTimeMillis();
            int[][] mtx = {{1,3,-1},{2,6,0},{0,1,0}};
            Matrix m = new Matrix(mtx);
            time = System.currentTimeMillis() - time;
            System.out.println("time = " + time + " ms");
            System.out.println(m.printMatrix());
            /*int[] key = {0,1,2,3};
            time = System.currentTimeMillis();
            m.sortRows(key);
            time = System.currentTimeMillis() - time;
            System.out.println("time = " + time + " ms");
            System.out.println(m.printMatrix());*/
            /*time = System.currentTimeMillis();
            m.swapRows(0, 2);
            time = System.currentTimeMillis() - time;
            System.out.println("time = " + time + " ms");
            System.out.println(m.printMatrix());
            time = System.currentTimeMillis();
            m.swapCols(1, 2);
            time = System.currentTimeMillis() - time;
            System.out.println("time = " + time + " ms");
            System.out.println(m.printMatrix());*/
        }
        catch (Throwable t) {
            System.out.println("error ocurred!");
            System.out.println(t.getMessage());
        }
    }


/**************************************************************************************************
  překrytí zděděné nebo přetížené zděděné metody:
**************************************************************************************************/


/**************************************************************************************************
  vlastní metody:
**************************************************************************************************/


    public void swapRows(int row1, int row2) throws Throwable {
        int tmp;
        if (0 > row1 || 0 > row2 || this.rows < row1 || this.rows < row2) {
            throw new Throwable("matrix index out of bounds!");
        }
        if (row1 == row2) {
            return;
        }
        tmp = row1;
        this.row[row1] = row2;
        this.row[row2] = tmp;
        for (int i=0; i<this.cols; i++) {
            tmp = this.mtx[row1][i];
            this.mtx[row1][i] = this.mtx[row2][i];
            this.mtx[row2][i] = tmp;
        }
    } // swapRows(int, int)


    public void swapCols(int col1, int col2) throws Throwable {
        int tmp;
        if (0 > col1 || 0 > col2 || this.rows < col1 || this.rows < col2) {
            throw new Throwable("matrix index out of bounds!");
        }
        if (col1 == col2) {
            return;
        }
        tmp = col1;
        this.col[col1] = col2;
        this.col[col2] = tmp;
        for (int i=0; i<this.rows; i++) {
            tmp = this.mtx[i][col1];
            this.mtx[i][col1] = this.mtx[i][col2];
            this.mtx[i][col2] = tmp;
        }
    } // swapCols(int, int)


    private void sortRows(final int[] key) throws Throwable { // dodělat - při prohození řádků je třeba prohodit také indexy řádků
        if (key.length != this.rows) {
            throw new Throwable("sort key array length must be same as matrix rows count");
        }
        boolean[] sorted = new boolean[this.rows];
        MyArray.init(sorted, false);
        int[] tmp = new int[this.rows];
        int tmpI = -1;
        int o;
        for (int i=0; i<this.rows; i++) {
            if (sorted[i]) {
                continue;
            }
            if (i == key[i]) {
                sorted[i] = true;
                continue;
            }
            if (-1 == tmpI) {
                tmp = MyArray.copy(this.mtx[i]);
                tmpI = i;
            }
            o = i;
            while (-1 != tmpI) {
                if (sorted[o]) {
                    throw new Throwable("values in key array have to be unique");
                }
                if (key[o] == tmpI) {
                    this.mtx[o] = MyArray.copy(tmp);
                    sorted[o] = true;
                    tmpI = -1;
                }
                else {
                    this.mtx[o] = MyArray.copy(this.mtx[key[o]]);
                    sorted[o] = true;
                    o = key[o];
                }
                System.out.println("o = " + o);
            }
        }
    }


    public String printMatrix() {
        if (0 == this.rows) {
            return "{0}";
        }
        String s = " \t";
        for (int i=0; i<this.cols; i++) {
            s += "c" + this.col[i] + "\t";
        }
        s += "\n";
        for (int i=0; i<this.rows; i++) {
            s += "r" + this.row[i] + "\t";
            for (int j=0; j<this.cols; j++) {
                s += this.mtx[i][j] + "\t";
            }
            s += "\n";
        }
        return s;
    } // printMatrix()
    
/**************************************************************************************************
  metody implementované přes rozhraní:
**************************************************************************************************/
}
