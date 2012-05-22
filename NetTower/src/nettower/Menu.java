/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower;

import java.applet.Applet;
import java.awt.BorderLayout;

/**
 *
 * @author David
 */
public class Menu extends Applet {
    public Menu() {
        add(new Button(), BorderLayout.CENTER);
        add(new Button(), BorderLayout.CENTER);
        add(new Button(), BorderLayout.CENTER);
    }
}