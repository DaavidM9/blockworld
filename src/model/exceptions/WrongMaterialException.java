package model.exceptions;
import model.Material;

public class WrongMaterialException extends Exception{
	public WrongMaterialException(Material m) {
		super(" Wrong material " + m + "!");
	}
}
