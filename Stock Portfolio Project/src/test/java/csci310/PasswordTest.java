/**
 * 
 */
package csci310;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Eric Manning
 *
 */
public class PasswordTest {
	private static String test = Password.hash("test");
	private static String test2 = Password.hash("test2");

	@Test
	public void testhash() {
		assertTrue(test.length() > 20);
		assertTrue(test2.length() > 20);
	}
	
	@Test
	public void testmatching() {
		Boolean matchTrue1 = Password.matching("test", test);
		Boolean matchFalse1 = Password.matching("test2", test);
		Boolean matchTrue2 = Password.matching("test2", test2);
		Boolean matchFalse2 = Password.matching("test", test2);
		
		assertTrue(matchTrue1);
		assertTrue(!matchFalse1);
		assertTrue(matchTrue2);
		assertTrue(!matchFalse2);
	}

}
