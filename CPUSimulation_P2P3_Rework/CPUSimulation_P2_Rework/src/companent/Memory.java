package companent;



import exception.IllegalMemoryAddressException;
import basic_rules.Word;
import companent.AbstractMemory;

public class Memory implements AbstractMemory{

	private static Memory _instance;
	private final int ROW = 1024;
	private final int WIDTH = 18;
	
	private Word memData[];
	
	public Memory(){
		this.memData = new Word[2048];
		for(int i=0;i<2048;i++)
			memData[i] = new Word();
	}
	
	
	public Word read(int index) throws IllegalMemoryAddressException{
		if(index<0||index>2048)
			throw new IllegalMemoryAddressException(index);
		return this.memData[index].clone();
	}
	
	/**
	 * turn int[]data into Word before write
	 * @param index
	 * @param data
	 * @throws IllegalMemoryAddressException
	 * @author yuma youzhao
	 */
	public void write(int index,int[] data) throws IllegalMemoryAddressException{
		if(index<0||index>2048)
			throw new IllegalMemoryAddressException(index);
		
		this.memData[index].setValue(data);
	}
	
	/**
	 * 
	 * @param index
	 * @param word
	 * @throws IllegalMemoryAddressException
	 */
	public void write(int index,Word word) throws IllegalMemoryAddressException{
		if(index<0||index>2048)
			throw new IllegalMemoryAddressException(index);
		
		this.memData[index].setValue(word);
		
	}
	
	public static Memory getInstance(){
		if(_instance == null){
			initInstance();
		}
		return _instance;
	}
	
	private static void initInstance(){
		_instance = new Memory();
	}


	public void clear() {
		for(Word word:memData)
			word.setIntValue(0);
	}
	
}
