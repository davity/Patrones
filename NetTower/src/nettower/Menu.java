/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower;

import java.applet.Applet;
import java.awt.Event;
import java.awt.Graphics;

/**
 *
 * @author David
 */
public class Menu extends Applet {
    public Menu() {}
    
    @Override
    public void paint(Graphics g) {
        g.drawImage(Art.mainMenu, 0, 0, null);
    }
    
    @Override
    public boolean mouseUp(Event e, int x, int y) {
        String itemPushed;
        
        itemPushed = getItemPushed(x, y);
        switch (itemPushed){
            case "map1":
                Main.getInstance().startGame();
                break;
            case "map2":
                
                break;
            case "map3":
                
                break;
            case "inst":
                
                break;
            case "exit":
                
                break;
            default:
                    break;
        }
        
        
        return true;
    }
    
    private String getItemPushed(int x, int y) {
        String item = "";
        
        if (x >= 46 && x <= 198 && y >= 135 && y < 342) item = "map1";
        else if (x >= 246 && x <= 398 && y >= 135 && y < 342) item = "map2";
        else if (x >= 446 && x <= 598 && y >= 135 && y < 342) item = "map3";
        else if (x >= 32 && x <= 178 && y >= 369 && y < 415) item = "inst";
        else if (x >= 457 && x <= 566 && y >= 369 && y < 414) item = "exit";
        else item = "";
        
        return null;
    }
}