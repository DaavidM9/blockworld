/**
 * 
 * @author David Molina de Francisco
 *  dmdf1 DNI: 01940514G
 */
package model;

import java.util.Iterator;
import java.util.Set;

import model.exceptions.BadInventoryPositionException;
import model.exceptions.BadLocationException;
import model.exceptions.EntityIsDeadException;
import model.exceptions.StackSizeException;


/**
 * The Class Player.
 */
public class Player {
	
	/** The name. */
	private String name;
	
	/** The health. */
	private double health;
	
	/** The food level. */
	private double foodLevel;
	
	/** The inventory. */
	private Inventory inventory;
	
	/** The loc. */
	private Location loc;
	
	/** The Constant MAX_FOODLEVEL. */
	public final static double MAX_FOODLEVEL = 20;
	
	/** The Constant MAX_HEALTH. */
	public final static double MAX_HEALTH = 20;
	
	
	/**
	 * Instantiates a new player.

	 * @param name the name
	 * @param world the world
	 */
	
	public Player(String name, World world){
		try {	
				this.name = name;
				Location location = new Location(world, 0, 0 ,0);
				loc = world.getHighestLocationAt(location);
				ItemStack is = new ItemStack(Material.WOOD_SWORD, 1);
				inventory = new Inventory();
				inventory.setItemInHand(is);
				foodLevel = MAX_FOODLEVEL;
				health = MAX_HEALTH;
		} catch (BadLocationException e) {
		} catch (StackSizeException e) {e.printStackTrace();}
		
		
	}



	/**
	 * Gets the name.
	 * @return the name
	 */
	public String getName() {
		return name;
	}



	/**
	 * Gets the health.
	 * @return the health
	 */
	public double getHealth() {
		return health;
	}



	/**
	 * Gets the food level.
	 * @return the food level
	 */
	public double getFoodLevel() {
		return foodLevel;
	}



	/**
	 * Gets the location.
	 * @return the location
	 */
	public Location getLocation() {
		return loc;
	}
	
	/**
	 * Gets the inventory size.
	 * @return the inventory size
	 */
	public int getInventorySize() {
		return this.inventory.getSize();
	}
	
	/**
	 * Sets the health.
	 * @param health the new health
	 */
	public void setHealth(double health) {
		if(health > MAX_HEALTH) {
			health = MAX_HEALTH;
		}
		this.health = health;
	}
	
	/**
	 * Sets the food level.
	 * @param foodLevel the new food level
	 */
	public void setFoodLevel(double foodLevel) {
		if(foodLevel > MAX_FOODLEVEL) {
			foodLevel = MAX_FOODLEVEL;
		}

		this.foodLevel = foodLevel;
	}
	
	/**
	 * Checks if is dead.
	 * @return true, if is dead
	 */
	public boolean isDead() {
		if(health <= 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Move.
	 * @param dx the dx
	 * @param dy the dy
	 * @param dz the dz
	 * @return the location
	 * @throws EntityIsDeadException the entity is dead exception
	 * @throws BadLocationException the bad location exception
	 */
	public Location move(int dx, int dy, int dz) throws EntityIsDeadException,BadLocationException{
		if(dx > 1 || dx < -1 ||dy > 1 || dy < -1 ||dz > 1 || dz < -1) {
			throw new BadLocationException("Wrong location!");
		}
		
		Set<Location> SetLoc = this.loc.getNeighborhood();
		double x = loc.getX() + dx;
		double y = loc.getY() + dy;
		double z = loc.getZ() + dz;
		Location loc = new Location(this.loc.getWorld(), x, y, z);
		
		Iterator<Location> it = SetLoc.iterator();
		
		while(it.hasNext()) {
			it.next();
			if(!this.isDead()) {
				if(loc.isFree() && Location.check(loc)) {
					if(loc.equals(it)) {
						break;
					}
				}else {
					throw new BadLocationException("Wrong location!");
				}
			}else {
				throw new EntityIsDeadException();
			}
		}
		
		decreaseFoodLevel(0.05);
		this.loc = loc;
		return loc;
	}
	
	
	/**
	 * Use item in hand.
	 * @param times the times
	 * @throws EntityIsDeadException the entity is dead exception
	 */
	public void useItemInHand(int times) throws EntityIsDeadException{
		int amount = this.inventory.getItemInHand().getAmount();
		double value = this.inventory.getItemInHand().getType().getValue();
		if(!isDead()) {
			//Si no está muerto
			if(times > 0) {
				//Times positivo
				if(this.inventory.getItemInHand() != null) {
					//Tiene algo en la mano
					if(this.inventory.getItemInHand().getType().isEdible()) {
						//Si es comida
						if(times < amount) {
							for(int i = 0; i < times; i++) {
								increaseFoodLevel(value);
							}
							try {
								this.inventory.getItemInHand().setAmount(amount - times);
							} catch (StackSizeException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}else {//times >= amount
							for(int i = 0; i < amount; i++) {
								increaseFoodLevel(value);
							}
							try {
								this.inventory.getItemInHand().setAmount(0);
							} catch (StackSizeException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}else{//si no es comida
						decreaseFoodLevel(0.1*times);
					}
				}else {
					//No tiene nada en la mano
				}
			}else {
				//times <= 0
				throw new IllegalArgumentException();
			}
		}else {//El jugador está muerto
			throw new EntityIsDeadException();
		}		
	}
	
	/**
	 * Increase food level.
	 *
	 * @param d the d
	 */
	public void increaseFoodLevel(double d) {
		double exceso = foodLevel + d;
		if(exceso > MAX_FOODLEVEL) {
			double dif = exceso - MAX_FOODLEVEL;
			setFoodLevel(MAX_FOODLEVEL);
			setHealth(this.health + dif);
		}else{
			setFoodLevel(exceso);
		}
	}
	
	
	/**
	 * Decrease food level.
	 *
	 * @param d the d
	 */
	public void decreaseFoodLevel(double d) {
		double exceso = foodLevel - d;
		
		if(exceso < 0) {
			setFoodLevel(0);
			setHealth(this.health + exceso);
		}else{
			setFoodLevel(exceso);
		}
	}
	
	/**
	 * Select item.
	 *
	 * @param pos the pos
	 * @throws BadInventoryPositionException the bad inventory position exception
	 */
	public void selectItem(int pos) throws BadInventoryPositionException{
		
		if(pos >= inventory.getSize()){
			throw new BadInventoryPositionException(pos);
		}
		
		if(this.inventory.getItemInHand() != null) {
			ItemStack is = this.inventory.getItem(pos);
			this.inventory.setItemInHand(is);
		}else {
			ItemStack is = this.inventory.getItem(pos);
			ItemStack aux = this.inventory.getItemInHand();
			inventory.setItem(pos, aux);
			inventory.setItemInHand(is);
		}
	}
	
	/**
	 * Adds the items to inventory.
	 *
	 * @param items the items
	 */
	public void addItemsToInventory(ItemStack items) {
		inventory.addItem(items);
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		String os = "";
		os += "Name="+ name + "\n" + loc.toString() + "\nHealth=" + health + 
				"\nFood level=" + foodLevel + "\nInventory=" + inventory + "\n";
		return os;
	}
	
	
}
