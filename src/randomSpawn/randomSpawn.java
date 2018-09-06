package randomSpawn;
/*
 * Description: This plugin randomly spawn players at different places with a range specified in config.yml
 * 
 * Version: 3.0.0
 * 
 * Author: maxxie114
 */


import java.io.BufferedReader;
import java.io.BufferedWriter;
//import all dependencies from java libraries
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
	public HashSet<String> playerList = new HashSet<String>(Arrays.asList(""));
	//define private variables
	private Config config;
	private File file;
	//private FileWriter name;
	//private File information;
	//private Config list;
	//When the plugin is loaded
	//private EventListener event; 
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

		//Create playerList.txt
		file = new File("plugins/randomSpawn/playerlist.txt");
		//Check if the parent directory exist, create it if not exist
		if(!file.getParentFile().mkdir()) {
			file.getParentFile().mkdir();
		} 
		//create the playerlist.txt
		try {
			file.createNewFile();
		} catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		
		//get all info
		maxRangeInStr = String.valueOf(config.get("spawnRange"));
		playerList = readPlayerList("plugins/randomSpawn/playerlist.txt");
		

		//save config file
		config.save();


		//set maxRange variable to the max range defined in config.yml
		setMaxRange(getMaxRange(maxRangeInStr));


	}


	//This method Convert String value rangeInStr to Int value
	public int getMaxRange(String rangeInStr) {
		int range = Integer.valueOf(rangeInStr);
		return range;
	}
	//This Set maxRange to the an Int value 
	public void setMaxRange(int range) {
		maxRange = range;
	}

	//This function create an empty file
	@Deprecated
	public void createFile(String filename) {
		try {
			FileWriter writer = new FileWriter(filename);

			BufferedWriter output = new BufferedWriter(writer);
			output.write("");
			output.newLine();
			output.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	} 

	//This function read the file line-by-line and store it into a HashSet
	public HashSet<String> readPlayerList(String filename) {
		HashSet<String> set = new HashSet<>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(filename));
			String line;
			while ((line = in.readLine()) != null) {
				set.add(line);
			}
			in.close();
			return set; 
		} catch (IOException e) {
			e.printStackTrace();
		}
		return set;
	}

	//Another function to store a HashSet into a file
	public void writePlayerList(HashSet<String> set, String filename) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(filename));
			for (String s : set) {
				out.write(s);
				out.newLine();
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Action to do when plugin is disabled
	public void onDisable() {
		this.getLogger().info(TextFormat.RED + "randomSpawn disabled!");
		writePlayerList(playerList, "plugins/randomSpawn/playerlist.txt");
	}

	//just for compile in eclipse
	public static void main(String[] args) {
		System.out.println("compiled");
	}

}
