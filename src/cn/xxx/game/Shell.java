/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.xxx.game;

import java.awt.Graphics;
import java.awt.Color;

/**
 *
 * @author Administrator
 */
public class Shell extends GameObject{
    double degree;
    
    public Shell(){
        x = 200;
        y = 200;
        width = 10;
        height = 10;
        speed = 3;       
        degree = Math.random()*Math.PI*2;
    }
    
    public void draw(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillOval((int)x, (int)y, width, height);
        
        x += speed*Math.cos(degree);
        y += speed*Math.sin(degree);
        
        if(x<0||x>Constant.GAME_WIDH-width){
            degree = Math.PI - degree; 
        }
        
        if(y<0||y>Constant.GAME_HEIGHT-height){
            degree =  - degree; 
        }
        
        g.setColor(c);           
    }
    
    
    
    
}
