package flyingplane;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;

public class GameVars {
    int x, y;
    Image img;
    
    public GameVars(int x, int y, Image img)
    {
        this.x = x;
        this.y = y;
        this.img = img;
        
    }
    public void draw(Graphics2D g, int w, int h)//print
    {
        g.drawImage(img, x, y, null);
    }
    
    public Rectangle getBounds()
    {
        return new Rectangle( x, y, img.getWidth(null) - 3, img.getHeight(null) - 2);
    }
    
   
}
