package randomSpawn;
/*
 * Description: This plugin randomly spawn players at different places with a range specified in config.yml
 * 
 * Version: 1.0.0
 * 
 * Author: maxxie114
 */



import java.io.File;
import java.util.LinkedHashMap;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;


public class randomSpawn extends PluginBase{
	
	public int maxRange;
	public String maxRangeInStr;
	public int rnge;
	
	//private EventListener event; 
	@Override
	public void onLoad() {
		this.getLogger().info(TextFormat.GREEN + "randomSpawn is loaded!");
		
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onEnable() {
		
		this .getServer().getPluginManager().registerEvents(new EventListener(this), this);
		this.getLogger().info(TextFormat.YELLOW + "randomSpawn enabled!");
		
		
		//this.getLogger().info(String.valueOf(this.getDataFolder().mkdirs()));
		
//		this.getServer().getScheduler().scheduleRepeatingTask(new BroadcastPluginTask(this), 200);
		
//		this.saveResource("test.txt");
		
		
  		Config config = new Config(
				new File(this.getDataFolder(), "config.yml"),
 				Config.YAML,
 				
				new LinkedHashMap<String, Object>(){
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;


					{
						put("spawnRange","900");
					}
				});
  		
  		
  		maxRangeInStr = String.valueOf(config.get("spawnRange"));
  		
//  		this.getLogger().info(String.valueOf(getMaxRange(maxRangeInStr)));  //test code
  		//this.getLogger().info("this is just a test");  //test code
  		config.save();
  		
  		setMaxRange(getMaxRange(maxRangeInStr));
  		
		
	}
	
	
	
	public int getMaxRange(String rangeInStr) {
		//Integer newInt = new Integer(maxRangeInStr);
		int range = Integer.valueOf(rangeInStr);
		return range;
	}
	
	public void setMaxRange(int range) {
		maxRange = range;
	}
	
	
	
	public void onDisable() {
		this.getLogger().info(TextFormat.RED + "randomSpawn disabled!");
	}
	

}
