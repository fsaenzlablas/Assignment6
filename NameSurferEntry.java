/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;
import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

    /* Constructor: NameSurferEntry(line) */
    /**
     * Creates a new NameSurferEntry from a data line as it appears
     * in the data file.  Each line begins with the name, which is
     * followed by integers giving the rank of that name for each
     * decade.
     */
    private String name;
    private int[] values  = new int[NDECADES] ;

    public NameSurferEntry(String line) {
        // You fill this in //
        String[] valuesTmp= line.split(" ");
        int numValues = valuesTmp.length;
        switch(numValues){
            case 0:
                break;
            case 1:
                break;
            case NDECADES:
            case NDECADES+1:
                name =valuesTmp[0];
                for (int i=1;i<numValues;i++){

                    values[i-1]=Integer.parseInt(valuesTmp[i]);
                }
                break;
            default:
                break;
        }

    }

    /* Method: getName() */
    /**
     * Returns the name associated with this entry.
     */
    public String getName() {
        // You need to turn this stub into a real implementation //
        return name;
//		return null;
    }

    /* Method: getRank(decade) */
    /**
     * Returns the rank associated with an entry for a particular
     * decade.  The decade value is an integer indicating how many
     * decades have passed since the first year in the database,
     * which is given by the constant START_DECADE.  If a name does
     * not appear in a decade, the rank value is 0.
     */
    public int getRank(int decade) {
        // You need to turn this stub into a real implementation //
        return values[decade];
//		return 0;
    }

    /* Method: toString() */
    /**
     * Returns a string that makes it easy to see the value of a
     * NameSurferEntry.
     */
    public String toString() {
        // You need to turn this stub into a real implementation //
        String valStr = new String ("");
        for (int i=0;i<values.length;i++){
            valStr+=values[i]+" ";
        }
        return getName()+" ["+valStr+"]";
//		return "";
    }
}

