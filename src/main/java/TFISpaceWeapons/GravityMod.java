package TFISpaceWeapons;

import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.checkerframework.checker.nullness.qual.NonNull;

public class GravityMod implements Listener {

	public int i = 0;
	
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onLaunchEvent(@NonNull ProjectileLaunchEvent event) {
		i =0;
		if(Bukkit.getOnlinePlayers().size() == 0)
		return;
		Entity e = event.getEntity();
		Vector vel = e.getVelocity();
		e.setGravity(false);
		new BukkitRunnable() {
   			 public void run() {
					e.setVelocity(vel);
					if(event.getEntity().getTicksLived() > 100) {
						//delete 35 and 36 later -uni
						e.remove();
						this.cancel();
						return;
					}
    		 }
		}.runTaskTimer(Main.plugin, 0L, 20L * 1);	
	}
	//public Vector velt = new Vector();
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void railgunFireEvent(@NonNull EntitySpawnEvent event) {
		EntityType e = event.getEntityType();
		if(Bukkit.getOnlinePlayers().size() == 0)
		return;
		if(!(event.getEntity() instanceof TNTPrimed))
		return;	
		new BukkitRunnable() {
   			 public void run() {
					if(event.getEntity().getVelocity().lengthSquared() > 0.35D) {
						event.getEntity().setGravity(false);
						this.cancel();
						return;
					}
    		 }
		}.runTaskTimer(Main.plugin, 0L, 1L * 1); 
		return;
	}
}
