package model.exceptions;

public class EntityIsDeadException extends Exception{
	public EntityIsDeadException() {
		super("You have died");
	}
}
