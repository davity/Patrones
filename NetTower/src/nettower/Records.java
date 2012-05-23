
package nettower;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Records {
    private int[] records = new int[3];
    private static final String RECORDS_FILE = "records.dat";
    private static final String SPLITTER = ":";
    
    /**
     * Constructor de Records
     */
    public Records() {}
    
    /**
     * Devuelve la puntuacion de un mapa en un formato concreto
     * 
     * @param map
     * @return String
     */
    public String getRecord(int map) {
        if (map >= 0 && map < 3) {
            return String.format("% 16d", records[map]);
        }
        return null;
    }
    
    /**
     * Dado un mapa y un record, si el record dado es mayor que el record
     * actual para ese mapa, sustituye el viejo record por el dado
     * 
     * @param map
     * @param record 
     */
    public void newRecord(int map, int record) {
        if (map >= 0 && map < 3 && record > records[map]) {
            records[map] = record;
        }
    }
    
    /**
     * Guarda los records de los mapas en un archivo
     */
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
    
    /**
     * Carga los records de los mapas de un archivo
     */
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
    
    /**
     * Devuelve los records de los mapas en una cadena de texto separando
     * cada record con un separador
     * 
     * @return String
     */
    public String getIntoString() {
        return records[0] + SPLITTER + records[1] + SPLITTER + records[2];
    }

    /**
     * Dada una cadena, carga los records de los distintos mapas a partir
     * de la misma, separando cada record de la cadena dada por un separador
     * 
     * @param string 
     */
    void setFromString(String string) {
        String[] splitted = string.split(SPLITTER);
        records[0] = Integer.parseInt(splitted[0]);
        records[1] = Integer.parseInt(splitted[1]);
        records[2] = Integer.parseInt(splitted[2]);
    }
}