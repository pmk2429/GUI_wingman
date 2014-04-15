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

public class MyPlane extends GameVars implements EntityA {

                public boolean death = false;
                int boom, count = 0, num, chance = 2;
                public static int score1 = 0, score2=0;
                ImageObserver obs;
		int velX = 0, velY = 0, HEALTH;
                GameObj gobj;
                Controller c;
                Image lives;
                Image[] player = new Image[3];
                
		
		MyPlane(Image img, int x, int y, int num, int HEALTH, Image lives, Image []player, GameObj gobj, Controller c) 
                {
                    super(x, y, img);
                    
                    this.gobj = gobj;
                    this.c = c;
                    this.num = num;
                    this.HEALTH = HEALTH;
                    this.lives = lives;
                    this.player = player;
                    
                    
                }
                
                public void draw(Graphics g, ImageObserver obs) 
                {
                    
                    Font fntP1 = new Font("arial", Font.BOLD, 20);
                    g.setFont(fntP1);
                    g.setColor(Color.white);
                    int healthX=0;
                    int healthY=0;
                     
                     if(count < 3)
                     {
    			img = player[count];
    			count++;
                     }
                     if(count >= 3)
                     {
    			count = 0;
    			img = player[count];
                     }
                    
                     
                     
                     
                     //////////Draw Planes//////////
                     
                     g.drawImage(img, x, y, obs);
                     
                     ///////////////////////////////
                     
                    //For Player-1
                    if(num == 1)
                    {
                        healthX = 560-135;
                        healthY = 400-38;
                        g.drawString("P1:" + Integer.toString(score1), 580, 23);
                        if(chance == 2)
                        {
                            g.drawImage(lives, 555, 385, null);
                            g.drawImage(lives, 585, 385, null);
                        }
                        else if(chance == 1)
                        {
                            g.drawImage(lives, 580, 385,null);
                        }
                     }
                    else if(num == 2)
                    {
                        healthX = 0+105;
                        healthY = 400-38;
                        g.drawString("P2:" + Integer.toString(score2), 10, 23);
                        
                        if(chance == 2)
                        {
                            g.drawImage(lives, 15, 385, null);
                            g.drawImage(lives, 45, 385, null);
                        }
                        else if(chance == 1)
                        {
                            g.drawImage(lives, 580, 385,null);
                        }
                    }
                    
                    
                    else
                    {
                        death = true;
                    }
                }
		public void render(Graphics g)
		{
			
		}
                public void tick()
                {
                    x += velX;
                    y += velY;
                    if(x <=0)
                        x =0;
                    if(x >= 570)
                        x = 570;
                    if(y <=0)
                        y =0;
                    if(y >= 400)
                        y = 400;
                    
                    if(num == 1)
                    {
                        //for Collision
                        for(int i=0 ;i < gobj.EB.size(); i++)
                         {
                          EntityB tempEnt = gobj.EB.get(i);
                          if(GameEvents.collision(this, tempEnt))
                          {
                                c.removeEntity(tempEnt);
                                GameObj.HEALTH1 -= 10;
                          }
                        }
                        
                        //for Power
                        for(int i=0; i<gobj.pwrup.size(); i++)
                        {
                            PowerUp pw = gobj.pwrup.get(i);
                            if(GameEvents.collision(pw, this))
                            {
                                c.removePower(pw);
                                GameObj.HEALTH1 = 130;
                            }
                        }
                        
                    }
                    else if(num==2)
                    {
                          //for Collision
                          for(int i=0 ;i < gobj.EB.size(); i++)
                          {
                          EntityB tempEnt = gobj.EB.get(i);
                          if(GameEvents.collision(this, tempEnt))
                          {
                              c.removeEntity(tempEnt);
                              GameObj.HEALTH2 -= 10;
                              
                          }
                          }
                          
                          //for Power
                          for(int i=0; i<gobj.pwrup.size(); i++)
                          {
                                PowerUp pw = gobj.pwrup.get(i);
                                if(GameEvents.collision(pw, this))
                                {
                                    c.removePower(pw);
                                    GameObj.HEALTH2 =130;
                                }
                            }
                    }
                }
                
		public int getX()
                {
                    return x;
                }		
		public int getY()
                {
                    return y;
                }
                public void setX(int x)
                {
                    this.x = x;
                }
		
		public void setY(int y)
                {
                    this.y = y;
                }
                
                public Rectangle getBounds()
                {
                     return new Rectangle(x, y, img.getWidth(null),img.getHeight(null));
                }
		
		public void setVelocityX(int velX)
                {
                    this.velX = velX;
                }
		
		public void setVelocityY(int velY)
                {
                    this.velY = velY;
                }
		public int getscore1()
		{
			return score1;
		}
                public int getscore2()
		{
			return score2;
		}
		public void rotate(double degree){}
		
		public void setHealth(int health){}
		
		public void powerUp(int powerVal){}
}
