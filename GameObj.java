package flyingplane;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;
import javax.swing.*;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;
import javax.sound.sampled.Clip;

public class GameObj extends JApplet implements Runnable {
    
    private Thread thread; 
    
    private int x = 0, y=0, move = 0;
    private BufferedImage bimg;
    private Controller c;
    int speed = 3, life = 4;
    Random generator = new Random(1234567);
    Island I1, I2, I3;
    MyPlane m1, m2;
    Enemy e1;
    GameEvents gameEvents;
    private ImagePack gi;
    private SoundPack sp;
    KeyControl key;
    boolean gameOver;
    boolean victory = false;
    boolean player2 = false;
    ImageObserver observer;
    Image island1, island2, island3, lives, bullet, power;
    Image []enemy1 = new Image[3];
    Image []enemy2 = new Image[3];
    Image []enemy3 = new Image[3];
    Image []myplane = new Image[3];
    Image []playerBullet = new Image[3];
    Image []explosion = new Image[6];
    Image []boss = new Image[3];
    Image []enemyBullet = new Image[2];
    int enemy_count = 5;
    int enemy_killed = 0;
    Clip explsound;
    public  ArrayList<EntityA> EA;
    public  ArrayList<EntityB> EB;
    public  ArrayList<PowerUp> pwrup;
    Menu ms = new Menu();
    MouseInput mi = new MouseInput();
    public static int HEALTH1 = 65 * 2;   
    public static int HEALTH2 = 65 * 2;  
    PowerUp pu;
    
    public static enum State
    {
        Menu,Game
    };
    
    public static State state = State.Menu;
    
    private void playSound()
    {
        
        AudioPlayer MGP=AudioPlayer.player;
        AudioStream BGM;
        AudioData MD;
        ContinuousAudioDataStream loop=null;
        try
        {
            BGM = new AudioStream(new FileInputStream("src/flyingplane/Resources/background.wav"));
            MD = BGM.getData();
            loop= new ContinuousAudioDataStream(MD);
            
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        MGP.start(loop);
    }
  
    
    public void init() {
        setBackground(Color.white);
        gi = new ImagePack();
        sp = new SoundPack();
        try
        {
        //get all the island Images
        island1 = gi.getImages("Resources/island1.png");          
        island2 = gi.getImages("Resources/island2.png");
        island3 = gi.getImages("Resources/island3.png");
        
        //get Player's Bullet's images
        playerBullet = gi.getPlayerBulletImage("Resources/bulletUp.png","Resources/bulletLeft.png","Resources/bulletRight.png");

        //get Enemy's Bullet's images
        enemyBullet = gi.getEnemyBulletImages("Resources/enemybullet1.png","Resources/enemybullet2.png");
        
        //get My Plane images
        myplane = gi.getPlayerImages("Resources/myplane_1.png","Resources/myplane_2.png","Resources/myplane_3.png");
        
        //get Enemy images
        enemy1 = gi.getEnemyImages("Resources/enemy1_1.png","Resources/enemy1_2.png","Resources/enemy1_3.png");
        
        enemy2 = gi.getEnemyImages("Resources/enemy2_1.png","Resources/enemy2_2.png","Resources/enemy2_3.png");
        
        enemy3 = gi.getEnemyImages("Resources/enemy3_1.png","Resources/enemy3_2.png","Resources/enemy3_3.png");
        
        
        //get Images of BOSS
        boss = gi.getBossImages("Resources/bosse1.png", "Resources/bosse2.png", "Resources/bosse3.png");
        
        //get Explosion images
        explosion = gi.getExplosionImages("Resources/explosion1_1.png",
            							  "Resources/explosion1_2.png",
            							  "Resources/explosion1_3.png",
            							  "Resources/explosion1_4.png",
            							  "Resources/explosion1_5.png",
            							  "Resources/explosion1_6.png");
        lives = gi.getImages("Resources/life.png");
        
        power = gi.getImages("Resources/powerup.png");
        }
        catch(Exception e)
        {
            System.out.println("Error in getting Images: " +  e.getMessage() + " : ");  
            e.printStackTrace();
        }
        
        gameOver = false;
        observer = this;
        
        c =  new Controller(this, gi);
        
        EA = c.getEntityA();
        EB = c.getEntityB();
        pwrup = c.getPower();
        
        //Passing all the parameters to Island Class to Draw Islands in Backgrounds
        I1 = new Island(island1, 100, 100, speed, generator);
        I2 = new Island(island2, 200, 400, speed, generator);
        I3 = new Island(island3, 300, 200, speed, generator);
       
        m1 = new MyPlane(myplane[0], 300, 340, 1 ,HEALTH1, lives, myplane, this,c);
        m2 = new MyPlane(myplane[1], 200, 340, 2 ,HEALTH2, lives, myplane, this,c);
        
        //explsound = sp.getSound("Resources/snd_explosion1.wav");
        
        pu = new PowerUp(power, 300, -100, c);
        
        setFocusable(true);
        key = new KeyControl(m1, m2, c, this, gi);
        
        addKeyListener(key);
       
        addMouseListener(mi);
        playSound();
    }
    
    
    private void tick()
    {
        if(state == State.Game)
        {
            c.tick();
        }
    }
    
    // generates a new color with the specified hue
    public void drawBackGroundWithTileImage(int w, int h, Graphics2D g2) 
    {
        gi = new ImagePack();
        Image sea,menu ;
        sea = gi.getImages("Resources/water.png");
        //menu = gi.getImages("Resources/background_menu.png");
        int TileWidth = sea.getWidth(this);
        int TileHeight = sea.getHeight(this);

        int NumberX = (int) (w / TileWidth);
        int NumberY = (int) (h / TileHeight);

        Image Buffer = createImage(NumberX * TileWidth, NumberY * TileHeight);
        
        for (int i = -1; i <= NumberY; i++) 
        {
            for (int j = 0; j <= NumberX; j++) 
            {
                g2.drawImage(sea, j * TileWidth, i * TileHeight + (move % TileHeight), TileWidth, TileHeight, this);
            }
        }
        move += speed;
    }

    public void drawDemo(int w, int h, Graphics2D g2) {

        
        if(victory)
    	{
        	drawBackGroundWithTileImage(w, h, g2);
                Font fnt1 = new Font("arial",Font.BOLD, 30);
    		g2.setFont(fnt1);
    		g2.setColor(Color.white);
			g2.drawString("YOU WIN", w/2-100, h/2-30);
			Font fnt2 = new Font("arial",Font.BOLD, 20);
			g2.setFont(fnt2);
			g2.drawString("P1:"+ Integer.toString(m1.getscore1()),w/2-80,h/2+10);
                        g2.drawString("P2:"+ Integer.toString(m2.getscore2()),w/2-80,h/2+30);
			
                        g2.drawString("Press R to play again", w/2-80, h/2+70);
			
			if(key.keyY == true)
			{
                        //Image img, int x, int y, int num, int HEALTH, Image lives, Image []player, GameObj gobj, Controller c
		        m1 = new MyPlane(myplane[0],300,360,1,HEALTH1,lives,myplane,this,c);	
		        m2 = new MyPlane(myplane[1],300,360,2,HEALTH2,lives,myplane,this,c);
		        
//		        events = new GameEvents(this,m,n,timeline,key);
//				gameOver = false;
//				player2 = false;
//				victory = false;
			}
    	}
        if (state == State.Game) 
        {
            if(!gameOver)
            {
            drawBackGroundWithTileImage(w, h, g2);
            I1.update(w, h);
            I1.draw(g2, this);

            I2.update(w, h);
            I2.draw(g2, this);

            I3.update(w, h);
            I3.draw(g2, this);

            //Calls MyPlane draw image!
            m1.tick();
            m1.draw(g2, this);
            m2.tick();
            m2.draw(g2, this);
            
            //e1.update(w, h);
            
            c.tick();
            c.render(g2);
                        
            //Player - 1    
            Font fPlayer1 = new Font("ARIAL", Font.BOLD, 10);
            g2.setFont(fPlayer1);
            g2.drawString("PLAYER - 1", 525, 420);
            g2.setColor(Color.gray);
            g2.fillRect(500, 425, 130, 20);
            g2.setColor(Color.green);
            g2.fillRect(500, 425, HEALTH1, 20);
            g2.drawRect(500, 425, HEALTH1, 20);
            
            //Player - 2
            Font fPlayer2 = new Font("ARIAL", Font.BOLD, 10);
            g2.setFont(fPlayer2);
            g2.drawString("PLAYER - 2", 30, 420);
            g2.setColor(Color.gray);
            g2.fillRect(5, 425, 130, 20);
            g2.setColor(Color.green);
            g2.fillRect(5, 425, HEALTH2, 20);
            g2.drawRect(5, 425, HEALTH2, 20);
            
            }
            else
            {
        	drawBackGroundWithTileImage(w, h, g2);
                Font fnt1 = new Font("arial",Font.BOLD, 30);
    		g2.setFont(fnt1);
    		g2.setColor(Color.white);
		g2.drawString("GAME OVER", w/2-100, h/2-30);
		Font fnt2 = new Font("arial",Font.BOLD, 20);
		g2.setFont(fnt2);
		g2.drawString("P1:"+ Integer.toString(m1.getscore1()),w/2-80,h/2+10);
                g2.drawString("P2:"+ Integer.toString(m2.getscore2()),w/2-80,h/2+30);
		g2.drawString("Press R to retry", w/2-80, h/2+70);
			
		if(key.keyY == true)
		{
                     m1 = new MyPlane(myplane[0],300,360,1,HEALTH1,lives,myplane,this,c);	
		     m2 = new MyPlane(myplane[1],300,360,2,HEALTH2,lives,myplane,this,c);
		    
//		    events = new GameEvents(this,m,n,timeline,key);
//				gameOver = false;
//				player2 = false;
			}
            }	
        }
        else if(state == State.Menu)
        {
            ms.render(g2);
        }
    }

    public Graphics2D createGraphics2D(int w, int h) {
        Graphics2D g2 = null;
        if (bimg == null || bimg.getWidth() != w || bimg.getHeight() != h) {
            bimg = (BufferedImage) createImage(w, h);
        }
        g2 = bimg.createGraphics();
        g2.setBackground(getBackground());
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        g2.clearRect(0, 0, w, h);
        return g2;
    }

    public void paint(Graphics g) {
        Dimension d = getSize();
        Graphics2D g2 = createGraphics2D(d.width, d.height);
        drawDemo(d.width, d.height, g2);
        g2.dispose();
        g.drawImage(bimg, 0, 0, this);
    }

    public void start() {
        thread = new Thread(this);
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
    }

    public void run() {
    	
        Thread me = Thread.currentThread();
        while (thread == me) 
        {
            repaint();
            
          try 
          {
                thread.sleep(15);
          } 
          catch (InterruptedException e)
          {
                break;
          }
        }
    }

    public static void main(String argv[]) {
        final GameObj demo = new GameObj();
        demo.init();
        JFrame f = new JFrame("Flying Master");
        f.addWindowListener(new WindowAdapter() {});
        f.getContentPane().add("Center", demo);
        f.pack();
        f.setSize(new Dimension(640, 480));
        f.setVisible(true);
        f.setResizable(false);
        demo.start();   
    }
    public int getEnemy_Count()
    {
        return enemy_count;
    }
    public void setEnemy_Count(int enemy_count)
    {
        this.enemy_count = enemy_count;
    }
     public int getEnemy_Killed()
    {
        return enemy_killed;
    }
    public void setEnemy_Killed(int enemy_killed)
    {
        this.enemy_killed = enemy_killed;
    }

    /**
     * This method is called from within the init() method to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>                        
    // Variables declaration - do not modify                     
    // End of variables declaration                   
}


class Menu
{
    
    public Rectangle playBtn = new Rectangle(250 , 150, 100, 50);
    public Rectangle quitBtn = new Rectangle(250 , 250, 100, 50);
    
    public void render(Graphics g) {
        
            Graphics2D g2 =  (Graphics2D)g;
            Font ftitle = new Font("ARIAL", Font.BOLD, 40);
            g.setFont(ftitle);
            g.setColor(Color.red);
            g.drawString("SHOOTING ARENA", 130 , 100);
            
            Font fPlay = new Font("ARIAL", Font.BOLD, 20);
            g.setFont(fPlay);
            g.drawString("PLAY", playBtn.x + 25, playBtn.y + 30);
            Font fQuit = new Font("ARIAL", Font.BOLD, 20);
            g.setFont(fQuit);
            g.drawString("QUIT", quitBtn.x + 25, quitBtn.y + 30);
            g2.draw(playBtn);
            g2.draw(quitBtn);
        }
}