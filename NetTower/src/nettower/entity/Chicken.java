/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.entity;

import java.awt.Color;
import java.awt.Graphics;
import nettower.*;
import java.util.ArrayList;
import java.awt.Point;

/**
 *
 * @author David Moran Diaz
 */
public class Chicken extends Entity {

    public int life;
    public double speed;
    public Invasion invasion;
    ArrayList route;
    boolean[] looking;
    int jumpLength;
    int jump;

    public Chicken(int iniLife, double iniSpeed, Invasion i, int x, int y, ArrayList iniRoute) {
        super(x, y);
        this.life = iniLife;
        this.speed = iniSpeed * 40;
        this.invasion = i;
        this.art = Art.chicken;
        this.route = iniRoute;
        looking = new boolean[2];
        jumpLength = 0;
        this.jump = 0;
    }

    public void damage(int damage) {
        if (life > 0) {
            life -= damage;
            if (life <= 0) {
                this.die();
            }
        }
    }
    
    //Muy similar a Shoot.step(), quizás deberiamos fusionarlas
    public void advance() {
        if(jump == 0) {
            double tmpx, tmpy;

            if (route.size() <= 0) {
                this.remove();
                return;
            }
            tmpx = ((Point)route.get(0)).x - x;
            tmpy = ((Point)route.get(0)).y - y;
            this.jumpLength = (int)speed / 40;
            if (tmpx == 0) {
                tmpx = Math.abs(tmpy);
                if (tmpx <= speed) {
                    this.jumpLength = (int)tmpx / 40;
                    route.remove(0);
                }
                this.looking[0] = false;
                if (tmpy >= 0) {

                    this.looking[1] = false;
                }
                else {
                    this.looking[1] = true;
                }
            }
            else {
                tmpy = Math.abs(tmpx);
                if (tmpy <= speed) {
                    this.jumpLength = (int)tmpy / 40;
                    route.remove(0);
                }
                this.looking[0] = true;
                if (tmpx >= 0) {

                    this.looking[1] = false;
                }
                else {
                    this.looking[1] = true;
                }
            }
            jump = 48;
            if (looking[0]) {
                if (looking[1]) {
                    this.art = Art.chickenI;
                }
                else {
                    this.art = Art.chicken;
                }
            }
            else {
                if (this.art == Art.chicken) {
                    this.art = Art.chickenI;
                }
                else {
                    this.art = Art.chicken;
                }
            }
        }
        if (jump <= 40) {
            if (looking[0]) {
                if (looking[1]) {
                    x -= jumpLength;
                    this.art = Art.chickenI;
                }
                else {
                    x += jumpLength;
                    this.art = Art.chicken;
                }
                if (jump > 20) {
                    y--;
                }
                else {
                    y++;
                }
            }
            else {
                if (looking[1]) {
                    y -= jumpLength;
                }
                else {
                    y += jumpLength;
                }
                if (jump > 20) {
                    y--;
                }
                else {
                    y++;
                }
            }
        }
        jump--;
    }
    
    public void remove() {
        invasion.delete(this);
    }

    public void die() {
        remove();
    }
}
