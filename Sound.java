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
import java.io.*;
import sun.audio.*;


public class Sound extends GameObj{
	
	//Play different sounds for Background music, bullets, Explosions, Collision and when BOSS comes
	public void getSound()
	{
            AudioPlayer AP = AudioPlayer.player;
            AudioStream AS;
            AudioData AD;
            ContinuousAudioDataStream loop=null;
        try
        {
            AS = new AudioStream(new FileInputStream("src/javaapplication6/Resources/background.wav"));
            AD = AS.getData();
            loop= new ContinuousAudioDataStream(AD);
            
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        AP.start(loop);
        }
}
