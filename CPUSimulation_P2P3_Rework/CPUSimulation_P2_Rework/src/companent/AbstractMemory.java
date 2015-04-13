package companent;

import exception.IllegalMemoryAddressException;
import basic_rules.Word;


public interface AbstractMemory {
public abstract Word read(int index) throws IllegalMemoryAddressException;
	
	public abstract void write(int index,Word word) throws IllegalMemoryAddressException;

}
