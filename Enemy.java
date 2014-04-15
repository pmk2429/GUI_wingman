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

public class Enemy extends GameVars implements EntityB{

        int gen, sizeX, sizeY,x,y;
        Random r = new Random();
        boolean show;	
	private int speed = r.nextInt(2) + 1;
        GameObj gobj;
        Controller c;
        
        //Enemy Constructor
        Enemy(Image img,int x, int y, int type, Controller c, GameObj gobj)
        {
            super(x,y, img);
            try
            {
            if(type ==4)
                this.speed = 1;
            else
                this.speed = speed;
            this.gen = gen;
            this.show = true;
            this.gobj = gobj;
            this.c = c;
            sizeX = img.getWidth(null);
            sizeY = img.getHeight(null);
            
            if(gobj == null )
                    System.out.println("GameObj object is NULL!");
            if(this == null )
                    System.out.println("EntityB Object is NULL!");
            }
            catch(Exception e)
            {
                System.out.println("Error in Enemy Constructor: --> " + e.getMessage());
                e.printStackTrace();
            }
            
       }
       
        
	public void tick()
        {
                y += speed;
                if( y > 480)
                {
                   speed = r.nextInt(2) + 1;
                   x = r.nextInt(640);
                   y = -20;
                }      
                
                for(int i=0; i<gobj.EA.size(); i++)
                {
                    EntityA tempEnt = gobj.EA.get(i);
                    if(GameEvents.collision(this, tempEnt))
                    {
                        c.removeEntity(this);
                    }
                }
        }
	public void render(Graphics g)
        {
            g.drawImage(img, x, y, null);
        }
        public int getX() 
        {
            return x;
        }
        public int getY()
        {
           return y;
        }
        public Rectangle getBounds()
        {
            return new Rectangle(x, y, img.getWidth(null) - 5,img.getHeight(null)-5);
        }
}
