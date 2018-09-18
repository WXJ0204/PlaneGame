/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.xxx.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

/**
 *
 * @author Administrator
 */
public class Plane extends GameObject{

    boolean left,up,right,down;
    boolean live = true;
    
    public Plane(Image img, double x, double y) {
        super(img, x, y);
        speed = 2;
        width = 100;
        height = 100;
    }
   
    @Override
    public void drawSelf(Graphics g) {
        if(live){
        g.drawImage(img, (int)x, (int)y, null);
        if(left) x -= speed;
        if(right) x += speed;
        if(up) y -= speed;
        if(down) y += speed;
        }
       
        
    }
    
    public  void addDirection(KeyEvent e){
        switch(e.getKeyCode()){
            case 37:
                left = true;
                break;
            case 38:
                up = true;
                break;
            case 39:
                right = true;
                break;
            case KeyEvent.VK_DOWN:
                down = true;
                break;
            case KeyEvent.VK_ADD:
                speed = speed+2;
                break;
            case KeyEvent.VK_SUBTRACT:
                if(speed>0)
                speed = speed-2;
                break;
                
        }
    }
    
    public  void subDirection(KeyEvent e){
        switch(e.getKeyCode()){
            case 37:
                left = false;
                break;
            case 38:
                up = false;
                break;
            case 39:
                right = false;
                break;
            case KeyEvent.VK_DOWN:
                down = false;
                break;
            
                
        }
    }
    
    
}
