package flyingplane;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import sun.audio.*;


	public class SoundPack{
		
                private Clip sound;
		
		public Clip getSound(String path)
		{
	        try
	        {
		        URL url = this.getClass().getClassLoader().getResource(path);
		        AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
		        sound = AudioSystem.getClip();
		        sound.open(audioIn);
		       
	        } catch(Exception e)
	        {
	        	System.out.println("Error in loading sound: ---> " + e.getMessage());
                        e.printStackTrace();
	        }
	        
	        return sound;
			
		}
		
	}
