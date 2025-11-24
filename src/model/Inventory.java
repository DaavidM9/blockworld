/**
 * 
 * @author David Molina de Francisco
 *  dmdf1 DNI: 01940514G
 */
package model;

import java.util.ArrayList;
import java.util.List;

import model.exceptions.BadInventoryPositionException;



/**
 * The Class Inventory.
 */
public class Inventory {
	
	/** The items. */
	private List<ItemStack> items;
	
	/** The in hand. */
	private ItemStack inHand;
	
	
	/**
	 * Instantiates a new inventory.
	 */
	public Inventory() {
		items =new ArrayList<ItemStack>();
		inHand = null;
	}
	
	/**
	 * Adds the item.
	 *
	 * @param item the item
	 * @return the int
	 */
	public int addItem(ItemStack item) {
		this.items.add(item);
		return item.getAmount();
	}
	
	/**
	 * Clear.
	 */
	public void clear() {
		this.items.clear();
		this.inHand = null;
	}
	

	/**
	 * Clear.
	 *
	 * @param slot the slot
	 * @throws BadInventoryPositionException the bad inventory position exception
	 */
	public void clear(int slot) throws BadInventoryPositionException{
		if(slot >= getSize()){
			throw new BadInventoryPositionException(slot);
		}
		this.items.remove(slot);	
	}
	
	/**
	 * First.
	 *
	 * @param type the type
	 * @return the int
	 */
	public int first(Material type) {
		for(int i = 0; i < items.size(); i++) {
			if(items.get(i).getType() == type){
				return i;
			}
		}
		return -1;
	}
	
	
	/**
	 * Gets the item.
	 *
	 * @param slot the slot
	 * @return the item
	 */
	public ItemStack getItem(int slot) {
		if(slot >= getSize() || slot < 0){
			return null;
		}
		
		return this.items.get(slot);
	}
	
	/**
	 * Gets the item in hand.
	 *
	 * @return the item in hand
	 */
	public ItemStack getItemInHand() {
		if(inHand  == null) {
			return null;
		}
		
		return inHand;
	}
	
	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public int getSize() {
		return this.items.size();
	}
	
	/**
	 * Sets the item.
	 *
	 * @param pos the pos
	 * @param items the items
	 * @throws BadInventoryPositionException the bad inventory position exception
	 */
	public void setItem(int pos, ItemStack items) throws BadInventoryPositionException{
		if(pos >= getSize()) {
			throw new BadInventoryPositionException(pos);
		}

		
		this.items.set(pos, items);
	}
	
	/**
	 * Sets the item in hand.
	 *
	 * @param items the new item in hand
	 */
	public void setItemInHand(ItemStack items) {
		this.inHand = items;
	}

	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		String os = "";
		if(inHand == null) {
			os += "(inHand=null,[";
		}else {
			os += "(inHand=" + inHand.toString() + ",[";
		}
		
		for(int i = 0; i < items.size(); i++) {
			if(i+1 == items.size()) {
				os += items.get(i).toString();
			}else {
				os += items.get(i).toString() + ", ";
			}
		}
		os += "])";
		
		return os;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((inHand == null) ? 0 : inHand.hashCode());
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		return result;
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inventory other = (Inventory) obj;
		if (inHand == null) {
			if (other.inHand != null)
				return false;
		} else if (!inHand.equals(other.inHand))
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		return true;
	}

	
}
