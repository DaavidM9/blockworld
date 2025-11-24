package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.exceptions.StackSizeException;

public class ItemStack_PreP2Test {

	ItemStack is;
	
	@Before
	public void setUp() throws Exception {
		 is = new ItemStack(Material.APPLE, ItemStack.MAX_STACK_SIZE);
	}

	
	//Prueba el constructor y los getters
	@Test
	public void testConstructorItemStackAndGetters() {
		try {
			assertEquals ("is.type = APPLE", Material.APPLE, is.getType());
			assertEquals ("is.amount = 10", 64, is.getAmount());
		} catch (Exception e) {
			fail ("No debía haber lanzado la excepción "+e.getClass().getName());
		}
		
	}
	
	//TODO
	//Prueba la excepcion StackSizeException para amount=0
	@Test (expected = StackSizeException.class)
	public void testConstructorItemStackthrowException1() throws StackSizeException {
			ItemStack it2 = new ItemStack(Material.APPLE,0);
			fail("No puede llegar aqui");

	}
	
	//TODO
	//Prueba la excepcion StackSizeException para amount>MAX_STACK_SIZE
	@Test (expected = StackSizeException.class)
	public void testConstructorItemStackthrowException2() throws StackSizeException {

			ItemStack it2 = new ItemStack(Material.APPLE,ItemStack.MAX_STACK_SIZE+2);
			fail("No puede llegar aqui");

	}

	
	/* comprueba: 
	  Si amount>1: lanza excepción si type no es comida o bloque */ 
	@Test 
	public void testConstructorItemStackthrowException3()  {
		 for (Material type : Material.values()) { 
				try {
					is = null;
					is = new ItemStack(type, 2);
					if (type.ordinal()>=12) {
						fail ("Error: "+type.name()+" en tu enumerado es una herramienta o arma y amount=2. "
								+ " Debía haber lanzado la excepción StackSizeException");
					}
				} catch (StackSizeException e) {
					// OK!
				} catch (Exception e)  {
					fail ("Error: Debía haber lanzado la excepción StackSizeException y ha lanzado "+e.getClass().getName());
				}		
		 }
	}
	
	//TODO
	/* Comprueba: 
	  Si amount>1: El ctor no lanza excepción si el material es de tipo comida o bloque.*/ 
	@Test 
	public void testConstructorItemStackDoesNotThrows()  {
		try {
			ItemStack it = new ItemStack(Material.APPLE,3);
			ItemStack it2 = new ItemStack(Material.BEDROCK,3);
		} catch (StackSizeException e) {
			fail("No puede llegar aqui");
		}
	}
	
	//TODO
	@Test
	public void testConstructorCopiaItemStack() {
		ItemStack it,it2;
		try {
			it = new ItemStack(Material.BEEF,3);
			it2 = new ItemStack(it);
			assertEquals(it,it2);
		} catch (StackSizeException e) {
			e.printStackTrace();
		}
	}

	//TODO
	/* Comprueba que setAmount acepta en amount valores > 1 para todos los materiales que son
	   material de bloque o comida y no lanza ninguna excepción */
	@Test
	public void testSetAmountOk() {
		try {
			ItemStack it = new ItemStack(Material.BEDROCK,2);
			ItemStack it2 = new ItemStack(Material.APPLE,2);
		} catch (StackSizeException e) {
			fail("No puede llegar aqui");
		}
	}

	//TODO
	/* Comprueba que setAmount acpeta en amount valores == 1 para herramientas o armas y no lanza ninguna excepción */
	@Test
	public void testSetAmountOk2() {
		try {
			ItemStack it = new ItemStack(Material.IRON_PICKAXE,1);
			ItemStack it2 = new ItemStack(Material.WOOD_SWORD,1);
		} catch (StackSizeException e) {
			// TODO Auto-generated catch block
			fail("No puede llegar aqui");
		}
	}

	//TODO
	// Prueba la excepción StackSizeException de setAmount para amount <1
	@Test (expected = StackSizeException.class)
	public void testSetAmountException1() throws StackSizeException {
	
			ItemStack it2 = new ItemStack(Material.APPLE,-2);
			fail("No puede llegar aqui");
	}
	
	//Prueba la excepcion StackSizeException de setAmount para amount>MAX_STACK_SIZE
	@Test (expected = StackSizeException.class)
	public void testSetAmountException2() throws StackSizeException {
		
			ItemStack it2 = new ItemStack(Material.APPLE,ItemStack.MAX_STACK_SIZE+2);
			fail("No puede llegar aqui");

	}
	

	// Test para equals
	@Test
	public void testEqualsObject() {

			try {
				ItemStack isApple = new ItemStack(Material.APPLE, 50);
				ItemStack isOtherApple = new ItemStack(Material.APPLE, 50);
				Material type = Material.BEDROCK;
			
				assertFalse(isApple.equals(null));
				assertTrue(isApple.equals(isApple));
				assertTrue(isApple.equals(isOtherApple));
				
				isApple.setAmount (40);
				assertFalse(isApple.equals(isOtherApple));  // amount distintos
				assertFalse(isApple.equals(type));
			} catch (Exception e) {
				fail ("Error: No debió lanzarse la excepción "+e.getClass().getName());
			}
	}
	
	//Test para hasCode()
	@Test
	public void testHashCode() {
		try {
			ItemStack isApple = new ItemStack(Material.APPLE, 50);
			ItemStack isOtherApple = new ItemStack(Material.APPLE, 50);
			ItemStack isDirt = new ItemStack(Material.DIRT, 50);
		
			assertEquals("codes iguales",isApple.hashCode(), isOtherApple.hashCode());
			assertNotEquals ("codes distintos por types distintos", isApple.hashCode(),isDirt.hashCode());
			isOtherApple.setAmount(40);
			assertNotEquals ("codes distintos por values distintos", isApple.hashCode(),isOtherApple.hashCode());
		} catch (Exception e) {
			fail ("Error: No debió lanzarse la excepción "+e.getClass().getName());
		}
	}
	
	//TODO
	//Test para toString()
	@Test
	public void testToString() {
		try {
			ItemStack it = new ItemStack(Material.APPLE,50);
			assertEquals(it.toString(),"(APPLE,50)");
		} catch (StackSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
