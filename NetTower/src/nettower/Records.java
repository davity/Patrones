/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower;

import java.io.Serializable;

/**
 *
 * @author David
 */
public class Records implements Serializable {
    int[] record = new int[3];

    public Records() {}
    
    public String save() {
        return record[1] + ":" + record[1] + ":" + record[2];
    }

    void load(String records) {
        String[] split = records.split(":");
        record[0] = Integer.parseInt(split[0]);
        record[1] = Integer.parseInt(split[0]);
        record[2] = Integer.parseInt(split[0]);
    }
}
