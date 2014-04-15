
package flyingplane;

import java.awt.Graphics;
import java.awt.Rectangle;


public interface EntityA {
    
    public void tick();
    public void render(Graphics g);
    public Rectangle getBounds();
    public int getX();
    public int getY();
   
}
