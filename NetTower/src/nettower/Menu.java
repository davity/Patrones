/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author David
 */
public class Menu extends Applet {
    public Menu() {}
    
    @Override
    public void paint(Graphics g) {
        g.drawImage(Art.mainMenu, 0, 0, this);
        g.setColor(Color.BLUE);
        g.setFont(new Font("Arial", Font.BOLD, 15));
        g.drawString("Mejor puntuación:", 54, 262);
        g.drawString("Mejor puntuación:", 254, 262);
        g.drawString("Mejor puntuación:", 454, 262);
        g.drawString(Main.getInstance().getRecord(0), 54, 292);
        g.drawString(Main.getInstance().getRecord(1), 254, 292);
        g.drawString(Main.getInstance().getRecord(2), 454, 292);
    }
    
    @Override
    public boolean mouseUp(Event e, int x, int y) {
        int itemPressed;
        
        itemPressed = getItemPressed(x, y);
        switch (itemPressed) {
            case 1:
                Main.getInstance().startGame(0);
                return true;
            case 2:
                Main.getInstance().startGame(1);
                return true;
            case 3:
                Main.getInstance().startGame(2);
                return true;
            case 4:
                Main.getInstance().showHelp();
                return true;
            case 5:
                Main.getInstance().exit();
                return true;
            default:
                return false;
        }
    }
    
    private int getItemPressed(int x, int y) {
        if (x >= 46 && x <= 198 && y >= 135 && y < 342) return 1;
        if (x >= 246 && x <= 398 && y >= 135 && y < 342) return 2;
        if (x >= 446 && x <= 598 && y >= 135 && y < 342) return 3;
        if (x >= 32 && x <= 178 && y >= 369 && y < 415) return 4;
        if (x >= 457 && x <= 566 && y >= 369 && y < 414) return 5;
        return 0;
    }
}