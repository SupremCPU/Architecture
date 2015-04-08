package companent;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Queue;

/**
 * 
 * @author yangmang
 * 
 */
public class Log extends Observable{

	
	private static Log _instance;
	public static Log getInstance(){
		if(_instance==null)
			_instance = new Log();
		return _instance;
	}
	
	/**
	 * all message will be stored here
	 * <br>
	 */
	private Queue<String> consoleMessage;
	
	
	
	public Log(){
		consoleMessage = new LinkedList<String>();
	}
	
	public void console(String info){
		// do something
		consoleMessage.add(info);
		
		this.setChanged();
		this.notifyObservers();
		
	}
	
	public void changeState(){
		this.setChanged();
		this.notifyObservers();
	}
	
	public Queue<String> getConsoleMessage() {
		return consoleMessage;
	}
}
