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
import java.util.LinkedHashMap;

//import all dependencies from nukkitx
import cn.nukkit.level.Position;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;

//Create class
public class randomSpawn extends PluginBase{
	//define variables
	public int maxRange;
	public String maxRangeInStr;
	public int rnge;
	public Position worldspawn;
	
	//When the plugin is loaded
	@Override
	public void onLoad() {
		this.getLogger().info(TextFormat.GREEN + "randomSpawn is loaded!");
		
	}
	
	//Action to perform When the plugin is enabled
	@SuppressWarnings("deprecation")
	@Override
	public void onEnable() {
		//register EventListener so it will work
		this .getServer().getPluginManager().registerEvents(new EventListener(this), this);
		this.getLogger().info(TextFormat.YELLOW + "randomSpawn enabled!");
		
		//get the world spawn as a position object and save it in worldspawn
		worldspawn = this.getServer().getDefaultLevel().getSpawnLocation();
		
		//Create config.yml
  		Config config = new Config(
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
  		
  		//get SpawnRange as a string and store it in maxRangeInStr
  		maxRangeInStr = String.valueOf(config.get("spawnRange"));
  		
  		//save config file
		config.save();
  		
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
		int range = Integer.valueOf(rangeInStr);
		return range;
	}
	/*
	*This method set the max spawn radius of the plugin
	*@param range  The range to be set to
	*/
	public void setMaxRange(int range) {
		maxRange = range;
	}
	
	
	//Action to do when plugin is disabled
	public void onDisable() {
		this.getLogger().info(TextFormat.RED + "randomSpawn disabled!");
	}
	

}
