package nettower.screen;

import java.awt.Graphics;

import nettower.*;
import nettower.level.*;

public class GameScreen extends Screen {
    public static final int MAX_HATS = 7;
    
    private static final boolean DEBUG_MODE = false;
    private int xLevel = DEBUG_MODE?8:0;
    private int yLevel = DEBUG_MODE?4:0;

    Level level = new Level(15, 15);

    public boolean mayRespawn = false;
    private int gunLevel = DEBUG_MODE?2:0;
    private int hatCount = 1;

    public GameScreen() {
        Art.map1 = Art.load("/levels.png");

    }

    public void tick(Input input) {
        
    }

    public void render(Graphics g) {
//        g.drawImage(Art.bg, -xLevel*160, -yLevel*120, null);
        //level.render(g);
    }
}
