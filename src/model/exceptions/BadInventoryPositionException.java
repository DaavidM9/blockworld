package model.exceptions;

public class BadInventoryPositionException extends Exception{
	public BadInventoryPositionException(int pos) {
		super("Position"+ pos + " it's wrong!");
	}
}
