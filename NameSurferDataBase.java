/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import java.io.BufferedReader;
import java.io.IOException;

import java.io.FileReader;

import java.util.HashMap;

public class NameSurferDataBase implements NameSurferConstants {

    /* Constructor: NameSurferDataBase(filename) */
    /**
     * Creates a new NameSurferDataBase and initializes it using the
     * data in the specified file.  The constructor throws an error
     * exception if the requested file does not exist or if an error
     * occurs as the file is being read.
     */
    private HashMap<String, NameSurferEntry> nameDB = new HashMap<String,NameSurferEntry>();


    public NameSurferDataBase(String filename) {


        BufferedReader rd = null;
        NameSurferEntry entry;
        try {
            rd = new BufferedReader(new FileReader(filename));



            try {
                while (true) {
                    String line = rd.readLine();
                    if (line == null)
                        break;
                    entry = new NameSurferEntry(line);
                    String keyName = entry.getName().toUpperCase();
                    NameSurferEntry entry2 =nameDB.put(keyName, entry);
                }

            } catch (IOException ex) {

            }

            rd.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /* Method: findEntry(name) */
    /**
     * Returns the NameSurferEntry associated with this name, if one
     * exists.  If the name does not appear in the database, this
     * method returns null.
     */
    public NameSurferEntry findEntry(String name) {
        return (NameSurferEntry)nameDB.get(name);
    }
}



