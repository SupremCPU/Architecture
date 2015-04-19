package exception;

public class FloatUnderflowException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8292066627268551874L;
	public FloatUnderflowException(String msg){
		super("FloatUnderflowException: "+msg);
	}
}
