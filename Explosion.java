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
import java.util.*;
import javax.sound.sampled.Clip;

public class Explosion extends GameVars{
        
    Image[] explosion = new Image[6];
    Clip explsound;
    Controller c;
    int count = 0, speed;
    
    
    
    Explosion(int x, int y, Image img, int speed, Controller c, Image[] explosion, Clip explsound)
    {
        super(x,y,img);
        this.explsound = explsound;
        this.c = c;
        this.speed = speed;
        this.explosion = explosion;
        
    }
    public void draw(Graphics g)
    {
            g.drawImage(img, x, y, null);
    }
    public void tick()
    {
                y+=speed;
		
		if(y >= 400)
		{
			c.removeExplosion(this);
		}
		
		if(count < 6)
		{
			img = explosion[count];
			count++;
		}
		if(count == 6)
		{
			img = null;
		}
		
    }
    public void sound()
    {
		//explsound.loop(1);
    }
	
    
    
}
