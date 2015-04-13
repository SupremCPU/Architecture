package companent;

import java.util.Observable;

import basic_rules.Word;
import exception.IllegalDeviceStatusException;

public abstract class IODevice extends Observable {

	public static final int DEVID_KEYBOARD = 0;

	public static final int DEVID_PRINTER = 1;

	public static final int DEVID_EDITOR = 31;
	
	protected int devid;


	public abstract void write(Word value) throws IllegalDeviceStatusException;


	public abstract Word read() throws IllegalDeviceStatusException;


	public abstract void reset() throws IllegalDeviceStatusException;


	public int getDevid() {
		return devid;
	}
}
