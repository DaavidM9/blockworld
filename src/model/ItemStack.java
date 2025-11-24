/**
 * 
 * @author David Molina de Francisco
 *  dmdf1 DNI: 01940514G
 */
package model;

import model.exceptions.StackSizeException;

/**
 * David Molina de Francisco.
 *
 * @author dmdf1
 */
public class ItemStack {
	
	/** tipo de material. */
	private Material type;
	
	/** cantidad de items. */
	private int amount;
	
	/** maximo de items por stack. */
	public static int MAX_STACK_SIZE = 64;
	
	
	/**
	 * Constructor.
	 *
	 * @param type the type
	 * @param amount the amount
	 * @throws StackSizeException the stack size exception
	 */
	public ItemStack(Material type, int amount) throws StackSizeException{
		this.type = type;
		setAmount(amount);
	}
	
	
	/**
	 * Constructor copia.
	 *
	 * @param is the is
	 * @throws StackSizeException the stack size exception
	 */
	public ItemStack(ItemStack is) throws StackSizeException{
		this.type = is.type;
		setAmount(is.amount);
	}
	
	
	/**
	 * Getter.
	 *
	 * @return type
	 */
	public Material getType() {
		return type;
	}
	
	
	/**
	 * Getter.
	 *
	 * @return amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * Asigna amount si su valor es correcto.
	 *
	 * @param amount the new amount
	 * @throws StackSizeException the stack size exception
	 */
	public void setAmount(int amount) throws StackSizeException{
		if(amount < 1 ||  amount > MAX_STACK_SIZE) {
			throw new StackSizeException();
		}
		
		if(type.isTool() || type.isWeapon()) {
			if(amount != 1) {
				throw new StackSizeException();
			}
		}
		
		
		this.amount = amount;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		return "(" + type.toString() + "," + amount + ")";
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
		result = prime * result + amount;
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
		ItemStack other = (ItemStack) obj;
		if (amount != other.amount)
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	
}
