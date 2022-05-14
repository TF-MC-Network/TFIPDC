package TFIPDC;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Fireball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import java.util.HashMap;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.checkerframework.checker.nullness.qual.NonNull;

public class DamageThing implements Listener {

	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onProjectileHit(@NonNull ProjectileHitEvent event) {
		
		if (!(event.getEntity() instanceof Fireball))
			return;
		
		Block eventBlock = event.getHitBlock();
		BlockFace eventBlockFace = event.getHitBlockFace();
		
		if (eventBlock == null || eventBlockFace == null)
			return;
		
		//calls blacklist checking function
		//currently unused
		if (PdcBlackListCheck(eventBlock))
			return;
		
		//determines resVal(resistanceValue) based on the blast resistance of a block
		double resVal=0;
		if (eventBlock.getBlockData().getMaterial().getBlastResistance() <= 1)
			resVal=0.50;
		
		if ((eventBlock.getBlockData().getMaterial().getBlastResistance() > 1) && (eventBlock.getBlockData().getMaterial().getBlastResistance() <= 9))
			resVal=0.75;
		
		if (eventBlock.getBlockData().getMaterial().getBlastResistance() > 9)
			return;
		
		//randomizes outcome of projectile impact based on the resVal determined previously
		if (Math.random() >= resVal)
			eventBlock.setType(Material.AIR);
		
		return;
		
	}
	
	
	
	/*
	HashMap<String, Boolean> blocks = new HashMap<String, Boolean>();
	blocks.put("Stone", true);
	
	if(block.isDestructable(blocks.get("stone"))) {
		continue;
	}
	*/
	
	
	//Blacklist for blocks that we want to be considered indestructible to PDC fire later
	//currently unused
	public static boolean PdcBlackListCheck(Block blk) {
		
		if(blk.getType()== Material.BEDROCK)
			return true;
		
		return false;
	}
	
	
	
	
	
}
