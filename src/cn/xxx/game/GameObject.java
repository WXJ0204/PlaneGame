/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.xxx.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author Administrator
 */
public class GameObject {
    Image img;
    double x,y;
    int speed;
    int width,height;

    public GameObject(Image img, double x, double y, int speed, int width, int height) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.height = height;
    }

    public GameObject(Image img, double x, double y) {
        this.img = img;
        this.x = x;
        this.y = y;
    }

    public GameObject() {
    }
    
    public Rectangle getRect(){
        return new Rectangle((int)x, (int)y, width, height);
    }
    
    public void drawSelf(Graphics g){
        g.drawImage(img, (int)x, (int)y, null);
    }
}
