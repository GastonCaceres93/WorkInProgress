package src.test.java;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.TestCase;
import src.main.java.com.gcaceres.Hello;

public class HelloTest extends TestCase {

	private Hello hello;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		// instanciacion de objetos, apertura de archivos
		this.hello = new Hello();
	}

	@After
	public void tearDown() throws Exception {
		// limpieza de memoria, cerrar archivos, etc
	}

	@Test
	public void testConnectToAndQueryDatabase() {
		// fail("Not yet implemented");
	}

}
