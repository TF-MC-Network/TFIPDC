package TFIPDC;

import java.util.logging.Level;


import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	public static Main plugin;
	//allows reference to main class for later integration
	
	@Override
	public void onEnable() {
		plugin = this;
		
		Bukkit.getPluginManager().registerEvents(new DamageThing(), this);
	}
	
	@Override
	public void onDisable() {
		plugin = null;
	}
	
	
	
	
}
