/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class Records {
    private int[] records = new int[3];
    private static final String RECORDS_FILE = "records.dat";
    private static final String SPLITTER = ":";
    
    /**
     * Constructor de Records
     */
    public Records() {}
    
    public String getRecord(int map) {
        if (map >= 0 && map < 3) {
            return String.format("%016d", records[map]);
        }
        return null;
    }
    
    public void newRecord(int map, int record) {
        if (map >= 0 && map < 3 && record > records[map]) {
            records[map] = record;
        }
    }
    
    public void saveRecords() {
        try {
            File file = new File(RECORDS_FILE);
            if(!file.exists()) {
                file.createNewFile();
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(getIntoString());
                writer.newLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadRecords() {
        try {
            File file = new File(RECORDS_FILE);
            if(file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    setFromString(reader.readLine());
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getIntoString() {
        return records[0] + SPLITTER + records[1] + SPLITTER + records[2];
    }

    void setFromString(String string) {
        String[] splitted = string.split(SPLITTER);
        records[0] = Integer.parseInt(splitted[0]);
        records[1] = Integer.parseInt(splitted[1]);
        records[2] = Integer.parseInt(splitted[2]);
    }
}