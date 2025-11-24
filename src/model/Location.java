/**
 * 
 * @author David Molina de Francisco
 *  dmdf1 DNI: 01940514G
 */
package model;

import java.util.HashSet;
import java.util.Set;

import model.exceptions.BadLocationException;


/**
 * The Class Location.
 */
public class Location {
	
	/** Nombre del mundo. */
	private World w;
	
	/**  x. */
	private double x;
	
	/**  y. */
	private double y;
	
	/**  z. */
	private double z;
	
	/** Altura maxima. */
	public final static double UPPER_Y_VALUE = 255;

	/** Nivel del mar. */
	public final static double SEA_LEVEL = 63;

	
	/**
	 * Constructor.
	 *
	 * @param w w
	 * @param x x
	 * @param y y
	 * @param z z
	 */
	public Location(World w, double x, double y, double z) {
		setWorld(w);
	    setX(x);
	    setY(y);
	    setZ(z);
	}
	
	/**
	 * Constructor copia.
	 *
	 * @param loc loc
	 */
	public Location(Location loc) {
		  w = loc.w;
		  x = loc.x;
		  y = loc.y;
		  z = loc.z;
	}

	/**
	 * Adds the.
	 *
	 * @param loc the loc
	 * @return the location
	 */
	public Location add(Location loc){
	    if (loc.w != w) 
	    	System.err.println("Cannot add Locations of differing worlds.");
	    else {
	        x += loc.x;
	        setY(y + loc.y);
	        z += loc.z;
	    }
	    return this;
	}

	
	/**
	 * Distance.
	 *
	 * @param loc the loc
	 * @return the double
	 */
	public double distance(Location loc){
		if (loc.getWorld() == null || getWorld() == null) {
	        System.err.println("Cannot measure distance to a null world");
	        return -1.0;
	    } else if (loc.getWorld() != getWorld()) {
	        System.err.println("Cannot measure distance between " + w.getName() + " and " + loc.w.getName());
	        return -1.0;
	    }
	    
	    double dx = x - loc.x;
	    double dy = y - loc.y;
	    double dz = z - loc.z;
	    return Math.sqrt(dx*dx + dy*dy + dz*dz);
	}

	/**
	 * Gets the world.
	 *
	 * @return the world
	 */
	public World getWorld(){
		return w;
	}
	
	/**
	 * Getter x.
	 *
	 * @return x
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * Getter y.
	 *
	 * @return y
	 */
	
	public double getY() {
		return y;
	}
	
	/**
	 * Getter z.
	 *
	 * @return z
	 */
	public double getZ() {
		return z;
	}
	
	/**
	 * Setter x.
	 *
	 * @param w the new world
	 */

	public void setWorld(World w){
		this.w = w;
	}
	
	/**
	 * Sets the x.
	 *
	 * @param x the new x
	 */
	public void setX(double x) {
		this.x = x;
	}
	
	/**
	 * Setter y.
	 *
	 * @param y the new y
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	/**
	 * Setter z.
	 *
	 * @param z the new z
	 */
	public void setZ(double z) {
		this.z = z;
	}
	
	/**
	 * longitud.
	 *
	 * @return lenght
	 */
	public double length() {
		return Math.sqrt(x*x + y*y + z*z);
	}
	
	/**
	 * Multiply.
	 *
	 * @param factor the factor
	 * @return the location
	 */
	public Location multiply(double factor) {
		  x *= factor;
		  setY(y * factor);
		  z *= factor;
		  return this;
	}
	
	/**
	 * Substract.
	 *
	 * @param loc the loc
	 * @return the location
	 */
	public Location substract(Location loc) {
	    if (loc.w != w) 
	    	System.err.println("Cannot substract Locations of differing worlds.");
	    else {
	        x -= loc.x;
	        setY(y - loc.y);
	        z -= loc.z;
	        }    
	    return this;
	}

	/**
	 * Zero.
	 *
	 * @return the location
	 */
	public Location zero() {
	    x = y = z = 0.0;
	    return this;
	}
		
	
	/**
	 * Below.
	 *
	 * @return the location
	 * @throws BadLocationException the bad location exception
	 */
	public Location below() throws BadLocationException{
		if(this.y <= 0) {
			throw new BadLocationException("Wrong location!");
		}
		Location  loc = new Location(w, x, y-1, z);  
		return loc;
	}
	
	/**
	 * Above.
	 *
	 * @return the location
	 * @throws BadLocationException the bad location exception
	 */
	public Location above() throws BadLocationException{
		if(this.y == UPPER_Y_VALUE) {
			throw new BadLocationException("Wrong location!");
		}
		Location  loc = new Location(w, x, y+1, z);  
		return loc;
	}
	
	/**
	 * Checks if is free.
	 *
	 * @return true, if is free
	 * @throws BadLocationException 
	 */
	
	public boolean isFree(){
		if(this.w == null) {
			return false;
		}
		try {
			return w.isFree(this);
			
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * Check.
	 *
	 * @param w the w
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 * @return true, if successful
	 */
	public static boolean check(World w, double x, double y, double z) {
		
		if(w == null) {
			return true;
		}
		if(w.getSize() % 2 == 0) {
			if((x > w.getSize()/2) || (x < w.getSize()/-2 + 1)) {
				return false;
			}
			if((z > w.getSize()/2) || (x < w.getSize()/-2 + 1)) {
				return false;
			}
		}
		if(w.getSize() % 2 == 1) {
			if((x > w.getSize()/2) || (x < (w.getSize()) /-2)) {
				return false;
			}
			if((z > w.getSize()/2) || (x < (w.getSize()) /-2)) {
				return false;
			}
		}
		
		if(y > 255 || y < 0) {
			return false;
		}
		return true;
	}

	
	/**
	 * Check.
	 *
	 * @param loc the loc
	 * @return true, if successful
	 */
	public static boolean check(Location loc) {
		
		if(loc.w == null) {
			return true;
		}
		if(loc.w.getSize() % 2 == 0) {
			if((loc.x > loc.w.getSize()/2) || (loc.x < loc.w.getSize()/-2 + 1)) {
				return false;
			}
			if((loc.z > loc.w.getSize()/2) || (loc.x < loc.w.getSize()/-2 + 1)) {
				return false;
			}
		}
		if(loc.w.getSize() % 2 == 1) {
			if((loc.x > loc.w.getSize()/2) || (loc.x < (loc.w.getSize()) /-2)) {
				return false;
			}
			if((loc.z > loc.w.getSize()/2) || (loc.x < (loc.w.getSize()) /-2)) {
				return false;
			}
		}
		
		if(loc.y > 255 || loc.y < 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * Gets the neighborhood.
	 *
	 * @return the neighborhood
	 */
	public Set<Location> getNeighborhood(){
		Set<Location> SetLoc = new HashSet<Location>();
		
		for(int i = -1; i <= 1; i++) {
			for(int j = 1; j >= -1; j--) {
				for(int k = -1; k <= 1; k++) {
					Location LocAux = new Location(this.w, this.x+k, this.y+j, this.z+i);
					
					if(LocAux.getWorld() == null){
						if(i != 0 || j != 0 || k != 0) {
							SetLoc.add(LocAux);
						}
					}else {
						if(check(LocAux)) {
							SetLoc.add(LocAux);
						}
					}
				}
			}
		}
		
		return SetLoc;
	}
	

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		if(w == null) {
			return "Location{world=NULL,x=" + x + ",y=" + y + ",z=" + z + "}";
		}
		return "Location{world=" + w.toString() + ",x=" + x + ",y=" + y + ",z=" + z + "}";
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
		result = prime * result + ((w == null) ? 0 : w.hashCode());
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(z);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Location other = (Location) obj;
		if (w == null) {
			if (other.w != null)
				return false;
		} else if (!w.equals(other.w))
			return false;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z))
			return false;
		return true;
	}	
	
	
	

}
	
