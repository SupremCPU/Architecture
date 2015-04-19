package exception;

public class FloatOverflowException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4365147104161021334L;
	public FloatOverflowException(String msg){
		super("FloatOverflowException: "+msg);
	}
}
