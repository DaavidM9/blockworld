/**
 * 
 * @author David Molina de Francisco
 *  dmdf1 DNI: 01940514G
 */
package model;
import model.exceptions.StackSizeException;
import model.exceptions.WrongMaterialException;


/**
 * The Class Block.
 */
public class Block {
	
	/** The type. */
	private Material type;
	
	/** The drops. */
	private ItemStack drops;
	
	/**
	 * Instantiates a new block.
	 *
	 * @param type the type
	 * @throws WrongMaterialException the wrong material exception
	 */
	public Block(Material type) throws WrongMaterialException{
		if(!type.isBlock()) {
			throw new WrongMaterialException(type);
		}
		this.type = type;
		this.drops= null;
	}
	
	
	/**
	 * Instantiates a new block.
	 *
	 * @param b the b
	 * @throws WrongMaterialException the wrong material exception
	 */
	public Block(Block b) throws WrongMaterialException {
		if(!b.type.isBlock()) {
			throw new WrongMaterialException(b.type);
		}
		this.type = b.type;
		this.drops = b.drops;
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public Material getType() {
		return type;
	}
	
	/**
	 * Gets the drops.
	 *
	 * @return the drops
	 */
	public ItemStack getDrops() {
		return drops;
	}
	
	/**
	 * Sets the drops.
	 *
	 * @param type the type
	 * @param amount the amount
	 * @throws StackSizeException the stack size exception
	 */
	public void setDrops(Material type, int amount) throws StackSizeException{
		if(amount  >  ItemStack.MAX_STACK_SIZE || amount < 1) {
			throw new StackSizeException();
		}
		
		if(this.type != Material.CHEST && amount != 1) {
			throw new StackSizeException();
		}
		
		ItemStack is = new ItemStack(type, amount);
		this.drops = is;
	}


	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "[" + type + "]";
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
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Block other = (Block) obj;
		if (type != other.type)
			return false;
		return true;
	}
	
	
	
}
