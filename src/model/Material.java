/**
 * 
 * @author David Molina de Francisco
 *  dmdf1 DNI: 01940514G
 */
package model;

import java.util.Random;


/**
 * The Enum Material.
 */
public enum Material {
	
	/** The bedrock. */
	//Bloques
	BEDROCK(-1, '*'),
	
	/** The chest. */
	CHEST(0.1, 'C'),
	
	/** The sand. */
	SAND(0.5, 'a'),
	
	/** The dirt. */
	DIRT(0.5, 'd'),
	
	/** The grass. */
	GRASS(0.6, 'g'),
	
	/** The stone. */
	STONE(1.5, 's'),
	
	/** The granite. */
	GRANITE(1.5, 'r'),
	
	/** The obsidian. */
	OBSIDIAN(5, 'o'),
	
	/** The water bucket. */
	//Comidas
	WATER_BUCKET(1, 'W'),
	
	/** The apple. */
	APPLE(4, 'A'),
	
	/** The bread. */
	BREAD(5, 'B'),
	
	/** The beef. */
	BEEF(8, 'F'),
	
	/** The iron shovel. */
	//Herramientas
	IRON_SHOVEL(0.2, '>'),
	
	/** The iron pickaxe. */
	IRON_PICKAXE(0.5, '^'),
	
	/** The wood sword. */
	//Armas
	WOOD_SWORD(1, 'i'),
	
	/** The iron sword. */
	IRON_SWORD(2, 'I');
	
	
	/** Valor del material. */
	private double value;
	
	/** Simbolo del material. */
	private char symbol; 
	
	/**
	 * Constructor privado.
	 *
	 * @param value the value
	 * @param symbol the symbol
	 */
	Material(double value, char symbol){
		this.value = value;
		this.symbol = symbol;
	}
	
	
	 /** The rng. */
 	static Random rng = new Random(1L);
	
	/**
	 * Si es un bloque devuelve true.
	 *
	 * @return boolean
	 */
	public boolean isBlock() {
		switch(symbol) {
			case 'C': 
				return true;
			case '*':
				return true;
			case 'a':
				return true;
			case 'd':
				return true;
			case 'g':
				return true;
			case 's':
				return true;
			case 'r':
				return true;
			case 'o':
				return true;
			default:
				return false;				
		}
	}
	
	/**
	 * Si es una comida devuelve true.
	 *
	 * @return boolean
	 */
	public boolean isEdible() {
		switch(symbol) {
			case 'A': 
				return true;
			case 'W': 
				return true;
			case 'B': 
				return true;
			case 'F': 
				return true;
			default:
				return false;
		}
	}
	
	/**
	 * Si es una herramienta devuelve true.
	 *
	 * @return boolean
	 */
	public boolean isTool() {
		if(symbol == '>' || symbol == '^') {
			return true;
		}
		return false;
	}
	
	/**
	 * Si es un arma devuelve true.
	 *
	 * @return boolean
	 */
	public boolean isWeapon() {
		switch(symbol) {
			case 'i': 
				return true;
			case 'I': 
				return true;
			default:
				return false;
		}
	}
	
	/**
	 * Getter.
	 *
	 * @return value
	 */
	public double getValue() {
		return value;
	}
	
	/**
	 * Getter.
	 *
	 * @return symbol
	 */
	public char getSymbol() {
		return symbol;
	}
	
	/**
	 * Devuelve un material al azar entre first y last.
	 *
	 * @param first the first
	 * @param last the last
	 * @return Material
	 */
    public static Material getRandomItem(int first, int last) {
        int i = rng.nextInt(last-first+1)+first;
        return values()[i];
    }
}




