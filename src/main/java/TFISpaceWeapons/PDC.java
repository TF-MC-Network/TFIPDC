package TFISpaceWeapons;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Fireball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.checkerframework.checker.nullness.qual.NonNull;

public class PDC implements Listener {

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
		double resVal=eventBlock.getBlockData().getMaterial().getBlastResistance();
		double resComp=0;
		
		if (resVal <= 1)
			resComp=0.50;
		if ((resVal > 1) && (resVal <= 9))
			resComp=0.75;
		if (resVal > 9)
			return;
		
		
		//randomizes outcome of projectile impact based on the resVal determined previously
		if (Math.random() >= resComp)
			eventBlock.setType(Material.AIR);
		
		return;
	}
	
	public static boolean PdcBlackListCheck(Block blk) {
		if(blk.getType() == Material.BEDROCK)
			return true;
		
		return false;
	}
}
