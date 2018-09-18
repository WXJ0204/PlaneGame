/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.xxx.game;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Administrator
 */
public class MyGameFrame extends Frame{
    
    
    Image bg = GameUtil.getImage("images/bg1_0.jpg");
    Image planeImg = GameUtil.getImage("images/hero0.png");
    
    Plane plane = new Plane(planeImg,250,550);
    Explode ex;
    Date startTime = new Date();      
    Date endTime = new Date();
    double period;
    ArrayList<Shell> shellList = new ArrayList<>();
    
    
    public void paint(Graphics g){       
        g.drawImage(bg, 0, 0, null);
        plane.drawSelf(g);
        
        for(int i=0;i<shellList.size();i++){
            Shell b = shellList.get(i);
            b.draw(g);
            boolean flag = b.getRect().intersects(plane.getRect());
            if(flag){
                plane.live=false;
                if(ex == null){
                ex = new Explode(plane.x, plane.y); 
                endTime = new Date();
                period = endTime.getTime()-startTime.getTime();               
                }               
                ex.draw(g);               
            }
            if(!plane.live){                
                g.setColor(Color.white);
                Font f = new Font("宋体", Font.BOLD, 50);
                g.setFont(f);
                g.drawString("时间"+period/1000+"s", (int)plane.x, (int)plane.y);
            }
        }
    }
    
    class PaintThread extends Thread{

        @Override
        public void run() {
            while(true){
                repaint();
                try {
                    Thread.sleep(40);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MyGameFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }      
    }
    
    class KeyMonitor extends KeyAdapter{

        @Override
        public void keyReleased(KeyEvent ke) {
            plane.subDirection(ke);
        }

        @Override
        public void keyPressed(KeyEvent ke) {
            plane.addDirection(ke);
        }
        
    }
    
    
    
    public void launchFrame(){
        this.setTitle("work");
        this.setVisible(true);
        this.setSize(Constant.GAME_WIDH, Constant.GAME_HEIGHT);
        this.setLocation(300,300);
        
        this.addWindowListener(new WindowAdapter() {//退出程序
            @Override
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
            });
        
        for(int i=0;i<50;i++)
    {//生成50个炮弹
        Shell b = new Shell();
        shellList.add(b);
    }
        
        new PaintThread().start();//启动重画窗口的线程
        addKeyListener(new KeyMonitor());//增加键盘监听
    }
    
    private Image offScreenImage = null;

    public void update(Graphics g) {
        if(offScreenImage == null)
          offScreenImage = this.createImage(Constant.GAME_WIDH,Constant.GAME_HEIGHT);//这是游戏窗口的宽度和高度
     
           Graphics gOff = offScreenImage.getGraphics();
           paint(gOff);
          g.drawImage(offScreenImage, 0, 0, null);
    }   
    
    public static void main(String[] args) {
        MyGameFrame f = new MyGameFrame();
        f.launchFrame();
    }
}
