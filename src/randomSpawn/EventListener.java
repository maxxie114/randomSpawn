package randomSpawn;
/*
 * Description: This is the event Listener of this plugin
 * 
 * Author: maxxie114
 * 
 * version: 2.0.0
 */

//import all dependencies from nukkitx
import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerRespawnEvent;
import cn.nukkit.level.Position;

//import dependencies from java.util for random number generation
import java.util.Random;

//create a class
public class EventListener implements Listener {
	// define final variables
	private final randomSpawn rndspawn;

	// define mutable variables
	private int x;
	private int y;
	private int z;
	private boolean isJoin;
	
	//Constructor method
	public EventListener(randomSpawn rndspawn) {
		this.rndspawn = rndspawn;
	}

	//When player join
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		//Uses a boolean value to disable onRespawn event when players join
		isJoin = true;
	}

	//When players Respawn
	@EventHandler 
	public void onRespawn(PlayerRespawnEvent event) {
		//This if statement prevent all players from being teleported to random places when they join
		if (!isJoin) {
			Player player = event.getPlayer();
			//This if statement prevent players with a spawnpoint set by a bed from losing their spawnpoint
			if (player.getSpawn().getX() == rndspawn.worldspawn.getX()
					&& player.getSpawn().getZ() == rndspawn.worldspawn.getZ()) {
				//Create a random number generator
				Random rnd = new Random();
				//define x, y, and z positions
				x = rnd.nextInt(rndspawn.maxRange);
				z = rnd.nextInt(rndspawn.maxRange);
				y = player.getLevel().getHeightMap(x, z) + 3;
				//set the random respawn positions
				event.setRespawnPosition(new Position(x, y, z, player.getLevel()));
			}
		} else {
			/*Set isJoin to false so that players can be randomly teleported the after
			the first respawn event(playerJoin is the fist time)*/
			isJoin = false;
		}
	}


}
