package model;

import model.exceptions.BadInventoryPositionException;
import model.exceptions.BadLocationException;
import model.exceptions.EntityIsDeadException;

public class BlockWorld {
		private static BlockWorld instance;
		private World world;
		
		public static BlockWorld getInstance() {
			if(instance == null) {
				instance = new BlockWorld();
			}
			return instance;
		}
		
		private BlockWorld() {
			world = null;
		}
		
		public World createWorld(long seed, int size,String name) {
			World world = new World(seed, size, name);
			this.world =world;
			return world;
		}
		
		public String showPlayerInfo(Player player){
			String os = "";
			os += player.toString();
			if(world != null) {
				try {
					os+= world.getNeighbourhoodString(player.getLocation());
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}

			return os;
		}
		
		public void movePlayer(Player player, int x, int y, int z) throws EntityIsDeadException, BadLocationException {
			player.move(x, y, z);
			if(world.getItemsAt(player.getLocation()) != null) {
				player.addItemsToInventory(world.getItemsAt(player.getLocation()));
				this.world.removeItemsAt(player.getLocation());	
			}
		}
		
		
		public void selectItem(Player player, int slot) throws BadInventoryPositionException {
			player.selectItem(slot);
		}
		
		public void useItem(Player player, int times) throws EntityIsDeadException {
			player.useItemInHand(times);
		}
		
}
