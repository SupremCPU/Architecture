package edu.gwu.core;

import edu.gwu.core.basic.Word;
import edu.gwu.exception.IllegalMemoryAddressException;

public interface Memory {

	public abstract Word read(int index) throws IllegalMemoryAddressException;
	
	public abstract void write(int index,Word word) throws IllegalMemoryAddressException;
}
