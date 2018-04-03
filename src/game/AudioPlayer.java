package game;

import java.util.HashMap; 
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.Sound;


public class AudioPlayer {

	public static Map<String, Sound> sound = new HashMap<String, Sound>(); 
	public static Map<String, Music> musicMap = new HashMap<String, Music>();
	
	public static void load() {
		
		try {
			sound.put("click", new Sound("res/click.ogg"));
			sound.put("goal", new Sound("res/goal.ogg"));
			sound.put("player", new Sound("res/player.ogg"));
			sound.put("wall", new Sound("res/wall.ogg"));
			
			musicMap.put("music", new Music("res/mario.ogg"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Music getMusic(String key) {
		return musicMap.get(key);
	}
	
	public static Sound getSound(String key) {
		return sound.get(key);
	}
}
