package randomSpawn;
/*
 * Description: This plugin randomly spawn players at different places with a range specified in config.yml
 * 
 * Version: 2.0.0
 * 
 * Author: maxxie114
 */


//import all dependencies from java libraries
import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.nio.charset.Charset;
import java.util.LinkedHashMap;
//import java.util.Set;

//import com.google.common.io.Files;

//import all dependencies from nukkitx
import cn.nukkit.level.Position;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;

//Create class
public class randomSpawn extends PluginBase{
	//define public variables
	public int maxRange;
	public String maxRangeInStr;
	public int rnge;
	public Position worldspawn;
	public EventListener listener;
	public Object playerList = new HashSet<String>(Arrays.asList(""));
	//define private variables
	private Config config;
	private Config list;
	//When the plugin is loaded
	
	@Override
	public void onLoad() {
		this.getLogger().info(TextFormat.GREEN + "randomSpawn is loaded!");
		
	}
	//When the plugin is enabled
	@SuppressWarnings({ "deprecation" })
	@Override
	public void onEnable() {
		//register EventListener so it will work
		this.getServer().getPluginManager().registerEvents(new EventListener(this), this);
		this.getLogger().info(TextFormat.YELLOW + "randomSpawn enabled!");
		//get the world spawn as a position object
		worldspawn = this.getServer().getDefaultLevel().getSpawnLocation();

		//Create playerList.yml
		this.list = new Config(new File(this.getDataFolder(), "playerList.yml"), Config.YAML);
		
		list.set("existingPlayers", playerList);
		
		//Create config.yml
  		this.config = new Config(
				new File(this.getDataFolder(), "config.yml"),
 				Config.YAML,
 				
				new LinkedHashMap<String, Object>(){
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					//create key spawnRange with default input 900
					{
						put("spawnRange","900");
					}
				});
  		
  		//get all info
  		maxRangeInStr = String.valueOf(config.get("spawnRange"));
  		playerList = list.get("existingPlayers");
  		

  		//save config file
		config.save();
		list.save();
		
		
		//set maxRange variable to the max range defined in config.yml
  		setMaxRange(getMaxRange(maxRangeInStr));
  		
		
	}
	
	
	/**
	* This method convert the maxRange in string to int
	*@param rangeInStr   the string to be converted
	*@return             the stirng got converted to an int and returned
	*
	*/
	public int getMaxRange(String rangeInStr) {
		//Integer newInt = new Integer(maxRangeInStr);
		int range = Integer.valueOf(rangeInStr);
		return range;
	}

	//This Set maxRange to the an Int value 
	public void setMaxRange(int range) {
		maxRange = range;
	}
	
	//This function store a HashSet into a file
	public void writePlayerList() {
		//Create another yml file for existing player data
		//list.set("existingPlayers", playerList);
  		list.save();
	}
	
	//Action to do when plugin is disabled
	public void onDisable() {
		this.getLogger().info(TextFormat.RED + "randomSpawn disabled!");
		this.getLogger().info(TextFormat.RED + "Debug: " + playerList.toString()); //test code
		writePlayerList();
	}
	
	//just for compile in eclipse
	public static void main(String[] args) {
		System.out.println("compiled");
	}

}
