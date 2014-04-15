package flyingplane;

import java.awt.Graphics;
import java.awt.Image;

public class PowerUp extends GameVars{
	Controller c;
	int speed=4;
        
	PowerUp( Image img, int x, int y,Controller c )
        {
            super(x, y, img);
            this.c = c;
        }
        
        public void tick()
        {
            y += speed;
        }
        public void render(Graphics g)
        {
            g.drawImage(img, x, y, null);
        }
        
}
