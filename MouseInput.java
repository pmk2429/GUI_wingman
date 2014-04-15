
package flyingplane;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener{

    
    public void mouseClicked(MouseEvent e) {
        
    }

    
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        /*
          public Rectangle playBtn = new Rectangle(250 , 150, 100, 50);
          public Rectangle quitBtn = new Rectangle(250 , 250, 100, 50);
         
         */
        //Play Button
        if(mx>=250 && mx<= 350)
        {
            if(my>=150 && my<=200)
            {
                System.out.println("Reached");
                GameObj.state = GameObj.State.Game;
            }
        }
        
        //Quit Button
        if(mx>=250 && mx<= 350)
        {
            if(my>=250 && my<=300)
            {
                   System.exit(1);
            }
        }
    }

    
    public void mouseReleased(MouseEvent e) {
    
    }

    
    public void mouseEntered(MouseEvent e) {
    
    }

    
    public void mouseExited(MouseEvent e) {
    
    }
    
}
