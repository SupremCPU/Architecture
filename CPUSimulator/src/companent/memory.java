package companent;



public class memory {

	private static memory _instance;
	
	private int memoryData[][];
	
	// The memory data start with original value
	
	public memory(){
		this.memoryData = new int[initial_size.mem_word_length][initial_size.word_size];
	}
	// the reader not read the invalid value
	
	public int[] readWord(int index){
		try{
		if(index<0||index>initial_size.mem_word_length)
			
			return null;
		return this.memoryData[index];
	}catch (Exception e) {
		
			
	}
		return null;
	}
	  // write the word by the processing one chart after one chart
	public void writeWord(int index,int[] data){
		try{
		if(index<0||index>initial_size.mem_word_length)
			
		  return;
		for(int i=0;i<initial_size.word_size;i++){
			this.memoryData[index][initial_size.word_size-i-1] = (i<data.length)?(data[data.length-1-i]):0;
		}
		}catch (Exception e) {
			//e.printStackTrace();
			
		}
	}
	
	public static memory getInstance(){
		if(_instance == null){
			initInstance();
		}
		return _instance;
	}
	
	private static void initInstance(){
		_instance = new memory();
	}
	
}
