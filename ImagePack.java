package flyingplane;

import java.awt.Image;
import java.net.URL;
import sun.awt.image.*;

import javax.swing.JApplet;

public class ImagePack extends JApplet{

    

    private Image img;
    private Image []imgs;
    
    public Image getImages(String path)
    {
        try
        {
            URL url = ImagePack.class.getResource(path);
            img = getToolkit().getImage(url);
        }
        catch (Exception e) 
        {
            System.out.println("Image - Error: " + e.getMessage());
            e.printStackTrace();
        }
        return img;
    }
    
    public Image[] getEnemyBulletImages(String path1, String path2)
    {
        imgs = new Image[2];
        try
        {
            URL url1 = ImagePack.class.getResource(path1);
            imgs[0] = getToolkit().getImage(url1);
            URL url2 = ImagePack.class.getResource(path2);
            imgs[1] = getToolkit().getImage(url2);
        }
        catch (Exception e) 
        {
            System.out.println("Enemy Bullet Images - Error: " + e.getMessage());
            e.printStackTrace();
        }
        return imgs;
    }
    
    //Method id used to get all the Player Plane Images
    public Image[] getPlayerImages(String path1, String path2, String path3)
    {
        imgs = new Image[3];
        try
        {
            
            URL url1 = ImagePack.class.getResource(path1);
            imgs[0] = getToolkit().getImage(url1);
            URL url2 = ImagePack.class.getResource(path1);
            imgs[1] = getToolkit().getImage(url1);
            URL url3 = ImagePack.class.getResource(path2);
            imgs[2] = getToolkit().getImage(url3);
        }
        catch(Exception e)
        {
            System.out.println("Player Plane Image - Error: " + e.getMessage());
            e.printStackTrace();
        }
        return imgs;
    }
    
    //Method to get all the Enemy Images
    public Image[] getEnemyImages(String path1, String path2, String path3)
    {
        imgs = new Image[3];
        try
        {
            URL url1 = ImagePack.class.getResource(path1);
            imgs[0] = getToolkit().getImage(url1);
            URL url2 = ImagePack.class.getResource(path2);
            imgs[1] = getToolkit().getImage(url2);
            URL url3 = ImagePack.class.getResource(path3);
            imgs[2] = getToolkit().getImage(url3);
        }
        catch(Exception e)
        {
            System.out.println("Enemy Image - Error: " + e.getMessage());
            e.printStackTrace();
        }
        return imgs;
    }
    
    //Method to get Bullet Images:
    public Image[] getPlayerBulletImage(String path1, String path2, String path3)
    {
        imgs = new Image[3];
        try
        {
            URL url1 = ImagePack.class.getResource(path1);
            imgs[0] = getToolkit().getImage(url1);
            URL url2 = ImagePack.class.getResource(path2);
            imgs[1] = getToolkit().getImage(url2);
            URL url3 = ImagePack.class.getResource(path3);
            imgs[2] = getToolkit().getImage(url3);
        }
        catch(Exception e)
        {
            System.out.println("Player's Bullet Image - Error: " + e.getMessage());
            e.printStackTrace();
        }
        return imgs;
    }
    
    
    //Method to get Explosion Images:
    public Image[] getExplosionImages(String path1, String path2, String path3, String path4, String path5, String path6)
    {
        imgs = new Image[6];
        try
        {
            URL url1 = ImagePack.class.getResource(path1);
            imgs[0] = getToolkit().getImage(url1);
            URL url2 = ImagePack.class.getResource(path2);
            imgs[1] = getToolkit().getImage(url2);
            URL url3 = ImagePack.class.getResource(path3);
            imgs[2] = getToolkit().getImage(url3);
            URL url4 = ImagePack.class.getResource(path4);
            imgs[3] = getToolkit().getImage(url4);
            URL url5 = ImagePack.class.getResource(path5);
            imgs[4] = getToolkit().getImage(url5);
            URL url6 = ImagePack.class.getResource(path6);
            imgs[5] = getToolkit().getImage(url6);
        }
        catch(Exception e)
        {
            System.out.println("Explosion Image - Error: " + e.getMessage());
            e.printStackTrace();
        }
        return imgs;
    }
    
    //Method to get BOSS Images:
    public Image[] getBossImages(String path1, String path2, String path3)
    {
        imgs = new Image[3];
        try
        {
            URL url1 = ImagePack.class.getResource(path1);
            imgs[0] = getToolkit().getImage(url1);
            URL url2 = ImagePack.class.getResource(path2);
            imgs[1] = getToolkit().getImage(url2);
            URL url3 = ImagePack.class.getResource(path3);
            imgs[2] = getToolkit().getImage(url3);
        }
        catch(Exception e)
        {
            System.out.println("Boss's Image - Error: " + e.getMessage());
            e.printStackTrace();
        }
        return imgs;
    }
    
}