/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.entity;

import java.awt.Point;
import nettower.Art;

/**
 *
 * @author David
 */
public class Cursor extends Entity {
    public Cursor() {
        super(Art.cursor, 16, new Point.Double(16, 16));
    }
    
    @Override
    public void step() {}

    @Override
    public void remove() {}
}
