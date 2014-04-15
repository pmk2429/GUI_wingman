package flyingplane;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class GameEvents extends Observable{
       int type;
       Object event;
       KeyControl key;
       GameObj gobj;
       MyPlane m1;
       MyPlane m2;
       int enemy_count=4;
       int enemy_killed=0;
       boolean shooting1 = false;
       boolean shooting2 = false;
       Random r;
       int x , y;
       Image img;       
       

        public static boolean collision(EntityA enta, EntityB entb)
	{
		if(enta.getBounds().intersects(entb.getBounds()))
		{
                    return true;
		}
            
            return false;
	}
	
        public static boolean collision(EntityB entb, EntityA enta)
	{
            
		if(entb.getBounds().intersects(enta.getBounds()))
		{
                    return true;
		}
            return false;
	}
        
       public static boolean collision(PowerUp pwr, MyPlane player) 
	{
		if(pwr.getBounds().intersects(player.getBounds()))
		{
			return true;
		}
		return false;
	}
}

