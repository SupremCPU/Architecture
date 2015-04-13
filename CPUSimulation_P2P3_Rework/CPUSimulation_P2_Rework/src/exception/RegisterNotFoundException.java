package exception;

public class RegisterNotFoundException extends Exception{


	/**
	 * 
	 */
	private static final long serialVersionUID = -7337665803990213768L;

	public RegisterNotFoundException(String name){
		super("Register Not Found Exception: try to user register:"+name);
	}
}
