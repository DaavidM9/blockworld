package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class Material_PreP2Test {

	final  Material typesAlumno[]=Material.values();
	final  Material materias[] = { Material.BEDROCK, Material.CHEST, Material.SAND, Material.DIRT, Material.GRASS, 
			Material.STONE, Material.GRANITE, Material.OBSIDIAN, Material.WATER_BUCKET, 
			Material.APPLE, Material.BREAD, Material.BEEF, Material.IRON_SHOVEL,Material.IRON_PICKAXE, 
			Material.WOOD_SWORD, Material.IRON_SWORD, };
	
	final double values[]= {-1, 0.1, 0.5, 0.5, 0.6, 1.5, 1.5, 5, 1, 4, 5, 8, 0.2, 0.5, 1, 2};
	final char symbols[]= {'*','C','a','d','g','s','r','o','W','A','B','F','>','^','i','I'};

	/**
	 * Comprueba el orden de los tipos de materias del Enum del alumno
	 */
	@Test
	public void testTypes() {
		for (int i = 0; i<=15;i++) {
			  assertEquals (materias[i], typesAlumno[i]);  
		}
	}

	/**
	 * Comprueba que isBlock() devuelve cierto sólo para los materiales que son de tipo bloque
	 */
	@Test
	public void testIsBlock() {
		for (int i = 0; i<=15;i++) {
		  if ( (i>=0) && (i<=7) )
			  assertTrue(materias[i].isBlock());
		  else 
			  assertFalse(materias[i].isBlock());
		}
	}

	/**
	 * Comprueba que isEdible() devuelve cierto sólo para los materiales que son comida
	 */
	@Test
	public void testIsEdible() {
		for (int i = 0; i<=15;i++) {
			  if ( materias[i].isEdible() )
				  assertTrue(materias[i].isEdible());
			  else 
				  assertFalse(materias[i].isEdible());
			}
	}

	/**
	 * Comprueba que isTool() devuelve cierto sólo para los materiales que son herramientas
	 */
	@Test
	public void testIsTool() {
		for (int i = 0; i<=15;i++) {
			  if ( materias[i].isTool() )
				  assertTrue(materias[i].isTool());
			  else 
				  assertFalse(materias[i].isTool());
			}
	}

	/**
	 * Comprueba que isWeapon() devuelve cierto sólo para los materiales que son armas
	 */
	@Test
	public void testIsWeapon() {
		for (int i = 0; i<=15;i++) {
			  if ( materias[i].isWeapon() )
				  assertTrue(materias[i].isWeapon());
			  else 
				  assertFalse(materias[i].isWeapon());
			}
	}

	/*
	 * Comprueba que getValue() devuelve los valores correctos para todos los materiales.
	 */
	@Test
	public void testGetValue() {
		for (int i = 0; i<=15;i++) {
			  if ( materias[i].getValue() != values[i] )
			  {
				  fail("mal");
			  }
			}
	}

	/*
	 * Comprueba que getSymbol() devuelve los símbolos correctos para todos los materiales.
	 */
	@Test
	public void testGetSymbol() {
		for (int i = 0; i<=15;i++) {
			  if ( materias[i].getSymbol() != symbols[i] )
			  {
				  fail("mal");
			  }
			}
	}

	@Test
	public void testGetRandomItem1() {
		for (int i = 0; i<=15;i++) {
			Material mat = Material.getRandomItem(i,i);
			assertTrue (materias[i]==mat);
		}
	}

	//Indices fuera del rango
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testGetRandomItem2() {
		
			 Material.getRandomItem(16,17);
	}
	
	//Indices fuera del rango
		@Test(expected = ArrayIndexOutOfBoundsException.class)
		public void testGetRandomItem3() {
			
				 Material.getRandomItem(-5,-1);
		}
	
	//Indice superior menor que el inferior
	@Test(expected = IllegalArgumentException.class)
	public void TestGetRandomItem4() {
		 Material.getRandomItem(7,6);
	}

}
