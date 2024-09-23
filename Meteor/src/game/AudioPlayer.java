package game;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class AudioPlayer {
	
	public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	public static Map<String, Music> musicMap = new HashMap<String, Music>();
	
	public static void init() {
		
		try {
			soundMap.put("menu_sound", new Sound("Res/button-3.wav"));
			musicMap.put("music" , new Music("Res/Northern_Lullaby.ogg"));
			soundMap.put("menu_sound1", new Sound("Res/button-4.wav"));
			soundMap.put("menu_sound2", new Sound("Res/button-10.wav"));
		} catch (SlickException e) {
			
			e.printStackTrace();
		}
	
	}

	
	public static Music getMusic(String key) {
		return musicMap.get(key);
	}
	
	
	public static Sound getSound(String key) {
		return soundMap.get(key);
	}


}

