/**
 * 
 */
package csci310;

/**
 * @author Eric Manning
 *
 */
import org.mindrot.jbcrypt.BCrypt;

public class Password {
	
	// Hash a password for the first time
	public static String hash(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt(12));
	}	
	
	public static Boolean matching(String password, String hashedPass) {
		// Check that an unencrypted password matches one that has
		// previously been hashed
		if (BCrypt.checkpw(password, hashedPass)) {
			return true;
		}
		else {
			return false; 
		}
	}
}
