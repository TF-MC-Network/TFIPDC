package TFIPDC;

import java.util.logging.Level;


import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	public static JavaPlugin plugin = null;
	
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
