/**************************************************************************************************
  davkovani/chem/ChemElementsTable.java
  @author: Jan Chára
  date: 17.3.2011
  description: MIDlet aplikace
**************************************************************************************************/

package davkovani.chem;

//import davkovani.array.MyArray;


public final class ChemElementsTable
{
/**************************************************************************************************
  deklarace proměnných:
**************************************************************************************************/

    final static int LENGTH = 100;
    final static String[] name = new String[ChemElementsTable.LENGTH];
    final static String[] symbol = new String[ChemElementsTable.LENGTH];
    final static float[] atomWeight = new float[ChemElementsTable.LENGTH];
    public static final int PROT_NUMBER;
    public static final int NAME;
    public static final int SYMBOL;
    public static final int ATOM_WEIGHT;

    static {
        int i=0;
        PROT_NUMBER = i;
        NAME = ++i;
        SYMBOL = ++i;
        ATOM_WEIGHT = ++i;
        i = 0;
        name[i]="Hydrogen";       symbol[i]="H";      atomWeight[i]=1.0079f;
        name[++i]="Helium";       symbol[i]="He";     atomWeight[i]=4.0026f;
        name[++i]="lithium";      symbol[i]="Li";     atomWeight[i]=6.941f;
        name[++i]="Beryllium";    symbol[i]="Be";     atomWeight[i]=9.01218f;
        name[++i]="Boron";        symbol[i]="B";      atomWeight[i]=10.81f;
        name[++i]="Carbon";       symbol[i]="C";      atomWeight[i]=12.011f;
        name[++i]="Nitrogen";     symbol[i]="N";      atomWeight[i]=14.0067f;
        name[++i]="Oxygen";       symbol[i]="O";      atomWeight[i]=15.9994f;
        name[++i]="Fluorine";     symbol[i]="F";      atomWeight[i]=18.998403f;
        name[++i]="Neon";         symbol[i]="Ne";     atomWeight[i]=20.179f;
        name[++i]="Sodium";       symbol[i]="Na";     atomWeight[i]=22.98977f;
        name[++i]="Magnesium";    symbol[i]="Mg";     atomWeight[i]=24.305f;
        name[++i]="Aluminium";    symbol[i]="Al";     atomWeight[i]=26.98154f;
        name[++i]="Silicium";     symbol[i]="Si";     atomWeight[i]=28.0855f;
        name[++i]="Phosphorus";   symbol[i]="P";      atomWeight[i]=30.97376f;
        name[++i]="Sulphur";      symbol[i]="S";      atomWeight[i]=32.06f;
        name[++i]="Chlorine";     symbol[i]="Cl";     atomWeight[i]=35.453f;
        name[++i]="Argon";        symbol[i]="Ar";     atomWeight[i]=39.948f;
        name[++i]="Potassium";    symbol[i]="K";      atomWeight[i]=39.0983f;
        name[++i]="Calcium";      symbol[i]="Ca";     atomWeight[i]=40.08f;
        name[++i]="Scandium";     symbol[i]="Sc";     atomWeight[i]=44.9559f;
        name[++i]="Titanium";     symbol[i]="Ti";     atomWeight[i]=47.90f;
        name[++i]="Vanadium";     symbol[i]="V";      atomWeight[i]=50.9414f;
        name[++i]="Chrome";       symbol[i]="Cr";     atomWeight[i]=51.996f;
        name[++i]="Manganum";     symbol[i]="Mn";     atomWeight[i]=54.9380f;
        name[++i]="Iron";         symbol[i]="Fe";     atomWeight[i]=55.847f;
        name[++i]="Cobalt";       symbol[i]="Co";     atomWeight[i]=58.9332f;
        name[++i]="Nickel";       symbol[i]="Ni";     atomWeight[i]=58.70f;
        name[++i]="Copper";       symbol[i]="Cu";     atomWeight[i]=63.546f;
        name[++i]="Zinc";         symbol[i]="Zn";     atomWeight[i]=65.38f;
        name[++i]="Gallium";      symbol[i]="Ga";     atomWeight[i]=69.72f;
        name[++i]="Germanium";    symbol[i]="Ge";     atomWeight[i]=72.59f;
        name[++i]="Arsenic";      symbol[i]="As";     atomWeight[i]=74.9216f;
        name[++i]="Selenium";     symbol[i]="Se";     atomWeight[i]=78.96f;
        name[++i]="Bromine";      symbol[i]="Br";     atomWeight[i]=79.904f;
        name[++i]="Krypton";      symbol[i]="Kr";     atomWeight[i]=83.80f;
        name[++i]="Rubidium";     symbol[i]="Rb";     atomWeight[i]=85.4678f;
        name[++i]="Strontium";    symbol[i]="Sr";     atomWeight[i]=87.62f;
        name[++i]="Yttrium";      symbol[i]="Y";      atomWeight[i]=88.9059f;
        name[++i]="Zirconium";    symbol[i]="Zr";     atomWeight[i]=91.22f;
        name[++i]="Niobium";      symbol[i]="Nb";     atomWeight[i]=92.9064f;
        name[++i]="Molybdenum";   symbol[i]="Mo";     atomWeight[i]=95.94f;
        name[++i]="Technecium";   symbol[i]="Tc";     atomWeight[i]=97f;
        name[++i]="Ruthenium";    symbol[i]="Ru";     atomWeight[i]=101.07f;
        name[++i]="Rhodium";      symbol[i]="Rh";     atomWeight[i]=102.9055f;
        name[++i]="Palladium";    symbol[i]="Pd";     atomWeight[i]=106.4f;
        name[++i]="Silver";       symbol[i]="Ag";     atomWeight[i]=107.868f;
        name[++i]="Cadmium";      symbol[i]="Cd";     atomWeight[i]=112.41f;
        name[++i]="Indium";       symbol[i]="In";     atomWeight[i]=114.82f;
        name[++i]="Tin";          symbol[i]="Sn";     atomWeight[i]=118.69f;
        name[++i]="Antimony";     symbol[i]="Sb";     atomWeight[i]=121.75f;
        name[++i]="Tellurium";    symbol[i]="Te";     atomWeight[i]=127.6f;
        name[++i]="Iodine";       symbol[i]="I";      atomWeight[i]=126.9045f;
        name[++i]="Xenon";        symbol[i]="Xe";     atomWeight[i]=131.3f;
        name[++i]="Cesium";       symbol[i]="Cs";     atomWeight[i]=132.9054f;
        name[++i]="Barium";       symbol[i]="Ba";     atomWeight[i]=137.33f;
        name[++i]="Lanthanum";    symbol[i]="La";     atomWeight[i]=138.9055f;
        name[++i]="Cerium";       symbol[i]="Ce";     atomWeight[i]=140.12f;
        name[++i]="Praseodymium"; symbol[i]="Pr";     atomWeight[i]=140.9077f;
        name[++i]="Neodymium";    symbol[i]="Nd";     atomWeight[i]=144.24f;
        name[++i]="Promethium";   symbol[i]="Pm";     atomWeight[i]=145f;
        name[++i]="Samarium";     symbol[i]="Sm";     atomWeight[i]=150.4f;
        name[++i]="Europium";     symbol[i]="Eu";     atomWeight[i]=151.96f;
        name[++i]="Gadolinium";   symbol[i]="Gd";     atomWeight[i]=157.25f;
        name[++i]="Terbium";      symbol[i]="Tb";     atomWeight[i]=158.9254f;
        name[++i]="Dysprosium";   symbol[i]="Dy";     atomWeight[i]=162.50f;
        name[++i]="Holmium";      symbol[i]="Ho";     atomWeight[i]=164.9304f;
        name[++i]="Erbium";       symbol[i]="Er";     atomWeight[i]=167.26f;
        name[++i]="Thulium";      symbol[i]="Tm";     atomWeight[i]=168.9342f;
        name[++i]="Ytterbium";    symbol[i]="Yb";     atomWeight[i]=173.04f;
        name[++i]="Lutecium";     symbol[i]="Lu";     atomWeight[i]=174.97f;
        name[++i]="Hafnium";      symbol[i]="Hf";     atomWeight[i]=178.49f;
        name[++i]="Tantalum";     symbol[i]="Ta";     atomWeight[i]=180.9479f;
        name[++i]="Tungsten";     symbol[i]="W";      atomWeight[i]=183.85f;
        name[++i]="Rhenium";      symbol[i]="Re";     atomWeight[i]=186.207f;
        name[++i]="Osmium";       symbol[i]="Os";     atomWeight[i]=190.2f;
        name[++i]="Iridium";      symbol[i]="Ir";     atomWeight[i]=192.22f;
        name[++i]="Platinum";     symbol[i]="Pt";     atomWeight[i]=195.09f;
        name[++i]="Gold";         symbol[i]="Au";     atomWeight[i]=196.9665f;
        name[++i]="Mercury";      symbol[i]="Hg";     atomWeight[i]=200.59f;
        name[++i]="Thallium";     symbol[i]="Tl";     atomWeight[i]=204.37f;
        name[++i]="Lead";         symbol[i]="Pb";     atomWeight[i]=207.2f;
        name[++i]="Bismuth";      symbol[i]="Bi";     atomWeight[i]=208.9804f;
        name[++i]="Polonium";     symbol[i]="Po";     atomWeight[i]=209f;
        name[++i]="Astatine";     symbol[i]="At";     atomWeight[i]=210f;
        name[++i]="Radon";        symbol[i]="Rn";     atomWeight[i]=222f;
        name[++i]="Francium";     symbol[i]="Fr";     atomWeight[i]=223f;
        name[++i]="Radium";       symbol[i]="Ra";     atomWeight[i]=226.0254f;
        name[++i]="Actinium";     symbol[i]="Ac";     atomWeight[i]=227.0278f;
        name[++i]="Thorium";      symbol[i]="Th";     atomWeight[i]=232.0381f;
        name[++i]="Protactinium"; symbol[i]="Pa";     atomWeight[i]=231.0359f;
        name[++i]="Uranium";      symbol[i]="U";      atomWeight[i]=238.029f;
        name[++i]="Neptunium";    symbol[i]="Np";     atomWeight[i]=237.0485f;
        name[++i]="Plutonium";    symbol[i]="Pu";     atomWeight[i]=242f;
        name[++i]="Americium";    symbol[i]="Am";     atomWeight[i]=243f;
        name[++i]="Curium";       symbol[i]="Cm";     atomWeight[i]=247f;
        name[++i]="Berkelium";    symbol[i]="Bk";     atomWeight[i]=247f;
        name[++i]="Californium";  symbol[i]="Cf";     atomWeight[i]=251f;
        name[++i]="Einsteinium";  symbol[i]="Es";     atomWeight[i]=254f;
        name[++i]="Fermium";      symbol[i]="Fm";     atomWeight[i]=257f;
        //MyArray.sortUpByInsertMethod(symbol,0,5);
    }


/**************************************************************************************************
  konstruktory:
**************************************************************************************************/


/**************************************************************************************************
  metoda main:
**************************************************************************************************/

    public static void main(String[] args) {
        System.out.println(ChemElementsTable.vyjmenuj(2));
    }


/**************************************************************************************************
  překrytí zděděné nebo přetížené zděděné metody:
**************************************************************************************************/


/**************************************************************************************************
  vlastní metody:
**************************************************************************************************/

    public static String vyjmenuj(int pozice) {
        String s="";
        for (int i=0; i<ChemElementsTable.LENGTH; i++) {
            s += ChemElementsTable.symbol[i] + "\t";
        }
        return s;
    }


    public static int getProtNumber(String s, int property) throws Exception {
        if (ChemElementsTable.SYMBOL == property) {
            for (int i=0; i<ChemElementsTable.LENGTH; i++) {
                if (ChemElementsTable.symbol[i].equals(s)) {
                    return i;
                }
            }
            throw new NoSuchChemElementException(s);
        }
        return 0;
    }

    public static float getAtomWeight(int protNumber) throws Exception {
        return ChemElementsTable.atomWeight[protNumber];
    }


/**************************************************************************************************
  metody implementované přes rozhraní:
**************************************************************************************************/
}
