package randomSpawn;
/*
 * Description: This is the event Listener of this plugin
 * 
 * Author: maxxie114
 * 
 * version: 1.0.0
 */

//import randomSpawn;
import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerRespawnEvent;
import cn.nukkit.level.Location;
import cn.nukkit.level.Position;
//import cn.nukkit.plugin.PluginBase;

import java.util.Random;

public class EventListener implements Listener {
	private final randomSpawn rndspawn;
	//private int x;
	//private int y;
	//private int z;
	private Location pos;
	
	public EventListener(randomSpawn rndspawn){
		this.rndspawn = rndspawn;
	}
	
	@EventHandler(ignoreCancelled = false) //watch out
	public void onRespawn(PlayerRespawnEvent event){
	    Player player = event.getPlayer();
	   // player.sendChat("debug: "+ rndspawn.maxRange); //test code
	    //this.getLogger()
	    Random rnd = new Random();
	    int x = rnd.nextInt(rndspawn.maxRange);
	    int z = rnd.nextInt(rndspawn.maxRange);
	    //int y = player.getLevel().getHighestBlockAt(x, z) + 1;
	    int y = 100;
	    //player.sendChat("debug: "+ x + ", " + y + " ," + z);  //test code
	    //pos = new Location(x, y, z, player.getLevel());
	    //player.teleportImmediate(pos);
	    event.setRespawnPosition(new Position(x,y,z,player.getLevel()));
	}
	
	//@EventHandler
	
	
}
