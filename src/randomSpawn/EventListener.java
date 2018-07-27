package randomSpawn;
/*
 * Description: This is the event Listener of this plugin
 * 
 * Author: maxxie114
 * 
 * version: 1.0.0
 */

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerRespawnEvent;
import cn.nukkit.level.Position;


import java.util.Random;

public class EventListener implements Listener {
	//final variables
	private final randomSpawn rndspawn;
	
	//mutable variables
	private int x;
	private int y;
	private int z;

	
	public EventListener(randomSpawn rndspawn){
		this.rndspawn = rndspawn;
	}
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent event){
	    Player player = event.getPlayer();
	    //player.sendChat("debug: "+ ); //test code
	    Random rnd = new Random();
	    x = rnd.nextInt(rndspawn.maxRange);
	    z = rnd.nextInt(rndspawn.maxRange);
	    y = player.getLevel().getHeightMap(x, z) + 3;
	    //y = 100
	    //pos = new Location(x, y, z, player.getLevel());
	    //player.teleportImmediate(pos);
	    event.setRespawnPosition(new Position(x,y,z,player.getLevel()));
	}
	
	//@EventHandler
	
	
}
