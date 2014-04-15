package flyingplane;

import java.awt.*;
import java.awt.event.*;
import java.awt.Image.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import javax.swing.*;
import java.util.*;
import java.util.concurrent.*;


public class Controller{
	
        long startTimeMS = System.currentTimeMillis();
	long startTimeSec = TimeUnit.MILLISECONDS.toSeconds(startTimeMS);
	long currentTime = 0;
	long currentMin = 0;
	long currentHour = 0;
	ArrayList<EntityA> EA = new ArrayList<EntityA>();
        ArrayList<EntityB> EB = new ArrayList<EntityB>();
        ArrayList<Enemy> enem = new ArrayList<Enemy>();
        ArrayList<Explosion> exp = new ArrayList<Explosion>();
        ArrayList<PowerUp> pwr = new ArrayList<PowerUp>();
	EntityA enta;
        EntityB entb;
        GameObj gobj;
	Random gen = new Random();
        ImagePack gi;
       
        boolean boss = false;
        
	public Controller(GameObj gobj, ImagePack gi )
        {
            try
            {
                this.gobj = gobj;
                this.gi = gi;
                
            }
            catch(Exception e)
            {
                System.out.println("Controller Constructor Error: --> " + e.getMessage());
                e.printStackTrace();
            }
            
        }
        
        public void TimeLine()
        {
            
//        int day = (int)TimeUnit.SECONDS.toDays(startTimeSec);        
//        long hours = TimeUnit.SECONDS.toHours(startTimeSec) - TimeUnit.SECONDS.toHours(TimeUnit.SECONDS.toDays(startTimeSec));
//        long minute = TimeUnit.SECONDS.toMinutes(startTimeSec) - TimeUnit.SECONDS.toMinutes(TimeUnit.SECONDS.toHours(startTimeSec));
//        long second = TimeUnit.SECONDS.toSeconds(startTimeSec) - TimeUnit.SECONDS.toSeconds(TimeUnit.SECONDS.toMinutes(startTimeSec));
//
//        System.out.println("Day " + day + " Hour " + hours + " Minute " + minute + " Seconds " + second);
        
        
        currentTime = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) - startTimeSec;
        //System.out.println("Current time: " + currentTime);
        
        
        
        if(currentTime >= 60)
		{
			startTimeMS = System.currentTimeMillis();
			startTimeSec = TimeUnit.MILLISECONDS.toSeconds(startTimeMS);
			currentTime = 0;
			currentMin++;
		}
		if(currentMin >=60)
		{
			currentHour++;
			currentMin = 0;
			currentTime = 0;
		}

		
		if(currentMin < 1)
		{
                        //System.out.println("CurrenMin: " + currentMin);
                    
			if(currentMin == 0 && currentTime <10 && boss == false)
			{
				boss = true;
				addEnemy(1,18);
			}
			
			
			if(EB.size() < 5)
			{
                            System.out.println("EB.size = " + EB.size());
                            addEnemy(4,10);	//number, type
			}
                        if(currentMin == 0 && currentTime == 10 && pwr.size() == 0)
			{
				PowerUp tempP = new PowerUp(gobj.power,340,0,this);
				pwr.add(tempP);
			}
		}
		else if (currentMin ==0 && currentTime == 50)
		{
			if(EB.size() <= 5)
			{
				addEnemy(3,16);
				addEnemy(2,15);
			}
		}
		else if (currentMin == 1 && currentTime == 20)
		{
			if(EB.size() <= 5)
			{
				addEnemy(4,16);
			}
		}
		else if (currentMin == 2)
		{
			if(currentMin == 3 && currentTime <10 && boss == false)
			{
				boss = true;
				addEnemy(1,16);
			}
		}
		else if (currentMin > 4)
		{
			if(EB.size() == 0)
			{
				gobj.victory = true;
			}
		}
        }
	
        //method to add enemy:
        public void addEnemy(int enemType, int enemy_count)
        {
            try
            {
            for(int i=0; i < enemy_count ;i++)
            {
                if(enemType == 1)
			{
                            Enemy e1 = new Enemy(gobj.enemy1[0] , gen.nextInt(560),-20, 1, this, gobj);
                            addEntity(e1);
                            
                            EnemyBullet eb1 = new EnemyBullet(gobj.enemyBullet[0], e1.getX(), e1.getY(), 1, this, gobj);
                            addEntity(eb1);
                        }
               else if (enemType == 2)
			{
                            Enemy e2 = new Enemy(gobj.enemy2[0] , gen.nextInt(560),-20, 2, this, gobj);
                            addEntity(e2);
                            EnemyBullet eb2 = new EnemyBullet(gobj.enemyBullet[0], e2.getX(), e2.getY(), 1, this, gobj);
                            addEntity(eb2);
			}
               else if (enemType == 3)
			{
                            Enemy e3 = new Enemy(gobj.enemy3[0] , gen.nextInt(560),-20, 3, this, gobj);
                            addEntity(e3);
                            EnemyBullet eb3 = new EnemyBullet(gobj.enemyBullet[0], e3.getX(), e3.getY(), 1, this, gobj);
                            addEntity(eb3);
			}
              else if (enemType == 4)
			{
                            Enemy boss = new Enemy(gobj.boss[0] , 300, -20, 4, this, gobj);
			    addEntity(boss);
                            EnemyBullet bossB = new EnemyBullet(gobj.enemyBullet[0], boss.getX(), boss.getY(), 1, this, gobj);
                            addEntity(bossB);
			}
            }
            }
            catch(Exception e)
            {
                System.out.println("Error in adding Enemy: --> " + e.getMessage());
                e.printStackTrace();
            }
        }
        
	public void tick()
        {
            for(int i=0; i< EA.size() ; i++)
            {
                enta = EA.get(i);
                
                enta.tick();
            }
            for(int i=0; i< EB.size() ; i++)
            {
                entb = EB.get(i);
                
                entb.tick();
            }
            TimeLine();
            
        }
        
        
	public void render(Graphics g)
        {
            
            for(int i=0; i< EA.size() ; i++)
            {
                enta = EA.get(i);
                
                enta.render(g);
            }
           
            for(int i=0; i< EB.size() ; i++)
            {
                entb = EB.get(i);
                entb.render(g);
            }
            for(int i=0; i < pwr.size();i++)
            {
                
		PowerUp temp = pwr.get(i);
                temp.tick();
                temp.render(g);
            }
            
            for(int i=0; i < exp.size();i++)
            {
		Explosion temp = exp.get(i);
                temp.draw(g);
		temp.tick();
	    }
            
            Font fnt1 = new Font("Times New Roman",Font.BOLD, 16);
            g.setFont(fnt1);
            g.setColor(Color.red);
            
		if(currentMin < 10)
			g.drawString(Long.toString(currentHour)+":0"+Long.toString(currentMin)+":" + Long.toString(currentTime), 300, 18);
		else
                        g.drawString(Long.toString(currentHour)+":"+Long.toString(currentMin)+":" +Long.toString(currentTime), 300, 18);
		
		if(gobj.m1.death == true && gobj.player2 == false || gobj.m1.death == true && gobj.m2.death == true)
		{
			gobj.gameOver = true;
		}
        }
        public void addPower(PowerUp block)
        {
            pwr.add(block);
        }
        public void removePower(PowerUp block)
        {
            pwr.remove(block);
        }
        
	public void addEntity(EntityA block)
        {
            EA.add(block);
        }
	
        public void removeEntity(EntityA block)
        {
            EA.remove(block);
        }
        
        public void addEntity(EntityB block)
        {
            EB.add(block);
        }
	
        public void removeEntity(EntityB block)
        {
            
            Explosion expl = new Explosion(block.getX(), block.getY(), gobj.explosion[0], 2, this, gobj.explosion, gobj.explsound);
            
		if(block.getY() < 480)
		{
			expl.sound();
		}
                int pi = gen.nextInt(125);
		
                System.out.println(pi);
		
                if(pi == 3)
		{
			PowerUp tempP = new PowerUp(gobj.power,block.getX(),block.getY(),this);
			pwr.add(tempP);
		}
                
		EB.remove(block);
                exp.add(expl);
               
            
        }
        public void removeExplosion(Explosion e)
	{
		exp.remove(e);
	}
        public ArrayList<EntityA> getEntityA()
        {
            return EA;
        }
        
        public ArrayList<EntityB> getEntityB()
        {
            return EB;
        }
        
        public ArrayList<PowerUp> getPower()
        {
            return pwr;
        }

       
        
}
