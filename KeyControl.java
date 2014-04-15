package flyingplane;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import javax.swing.*;


public class KeyControl extends KeyAdapter  {
	
       
        MyPlane m1,m2;
        Controller c;
        GameObj game;
        ImagePack gi;
        Image img;
        boolean shooting = false;
        boolean keyY;
        
        public KeyControl(MyPlane m1,MyPlane m2, Controller c, GameObj game, ImagePack gi)
        {
            try
            {
                       
            this.c = c;
            this.m1 = m1;
            this.m2 = m2;
            this.game = game;
            this.gi = gi;
            this.img = gi.getImages("Resources/bulletUp.png");
           
            }
            catch(Exception e)
            {
                System.out.println("KeyControl Controller Error :---> " + e.getMessage());
            }
        }
                
            public void keyPressed(KeyEvent e)
            {
                try
                {
                    int key = e.getKeyCode();
            
                    //Player 1
                    if(key == KeyEvent.VK_LEFT) 
                         m1.setVelocityX(-5);
                    else if(key == KeyEvent.VK_RIGHT) 
                         m1.setVelocityX(5);
                    else if(key == KeyEvent.VK_UP) 
                        m1.setVelocityY(-5);
                    else if(key == KeyEvent.VK_DOWN) 
                        m1.setVelocityY(5);
                    else if(key == KeyEvent.VK_SPACE && !shooting)
                    {
                        shooting = true;
                        Bullet temp = new Bullet(m1.getX(),m1.getY(),img,1, c, game);
                        c.addEntity(temp);
                    }
                    
                    //Player 2
                    if(key == KeyEvent.VK_A) 
                    {   
                        //System.out.println("Getting");
                         m2.setVelocityX(-5);
                    }
                    else if(key == KeyEvent.VK_D) 
                         m2.setVelocityX(5);
                    else if(key == KeyEvent.VK_W) 
                        m2.setVelocityY(-5);
                    else if(key == KeyEvent.VK_S) 
                        m2.setVelocityY(5);
                    else if(key == KeyEvent.VK_F && !shooting)
                    {
                        shooting = true;
                        Bullet temp = new Bullet(m2.getX(),m2.getY(),img,2, c, game);
                        c.addEntity(temp);
                    }
                    
                }
                catch(Exception ev)
                {
                    System.out.println("KeyPressed event Error :--->  " + ev.getMessage());
                    ev.printStackTrace();
                }
            }
            
            //To set the plane to current position after setting Velocity
            public void keyReleased(KeyEvent e)
            {
                try
                {
                    int key = e.getKeyCode();
                    
                    //Player 1
                    if(key == KeyEvent.VK_LEFT) 
                         m1.setVelocityX(0);
                    else if(key == KeyEvent.VK_RIGHT) 
                         m1.setVelocityX(0);
                    else if(key == KeyEvent.VK_UP) 
                        m1.setVelocityY(0);
                    else if(key == KeyEvent.VK_DOWN) 
                        m1.setVelocityY(0);
                    else if(key == KeyEvent.VK_SPACE)
                        shooting = false;
                    
                    //Player 2
                    if(key == KeyEvent.VK_A) 
                         m2.setVelocityX(0);
                    else if(key == KeyEvent.VK_D) 
                         m2.setVelocityX(0);
                    else if(key == KeyEvent.VK_W) 
                        m2.setVelocityY(0);
                    else if(key == KeyEvent.VK_S) 
                        m2.setVelocityY(0);
                    else if(key == KeyEvent.VK_F)
                        shooting = false;
                }
                catch(Exception ev)
                {
                    System.out.println("KeyReleased event Error :---> " +ev.getMessage());
                    ev.printStackTrace();
                }
            }
}
        
