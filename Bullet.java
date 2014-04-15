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

public class Bullet extends GameVars implements EntityA{
	
        ImagePack gi;
        GameObj gobj;
        Controller c;
        int num;

        Bullet(int x, int y, Image img, int num, Controller c, GameObj gobj)
        {
            super(x, y, img);
            
            this.gobj = gobj;
            this.c = c;
            this.num = num;
            
            if(gobj == null )
                    System.out.println("GameObj object is NULL!");
            else if(this == null )
                    System.out.println("Bullet Object is NULL!");
            else if(c == null )
                    System.out.println("Controller Object is NULL!");
            
        }
	
	public void tick()
        {
            y -= 5;
            if(num == 1)
            {
            for(int i=0 ; i<gobj.EB.size(); i++)
            {
                EntityB tempEnt = gobj.EB.get(i);
                if(GameEvents.collision(this, tempEnt))
                {
                    c.removeEntity(this);
                    MyPlane.score1 +=10;
                   
                }
            }
            }
            else if(num ==2)
            {
            for(int i=0 ; i<gobj.EB.size(); i++)
            {
                EntityB tempEnt = gobj.EB.get(i);
                if(GameEvents.collision(this, tempEnt))
                {
                    c.removeEntity(this);
                    MyPlane.score2 +=10;
                }
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
            return new Rectangle(x, y, img.getWidth(null),img.getHeight(null));
        }
}

class EnemyBullet extends Enemy implements EntityB
{
	BufferedImage strongBullet, weakBullet;
        private int speed = 3;
        Controller c;
        GameObj gobj;
        
        EnemyBullet(Image img, int x, int y, int num, Controller c, GameObj gobj)
        {
            super(img, x, y, num, c, gobj);
            this.c = c;
            this.gobj = gobj;
        }
	
	public void tick()
        {
            y+=speed;
        }
	
	public void render(Graphics g)
        {
            g.drawImage(img, x, y, null);
        }

}
