package basic_rules;



import basic_rules.Word;

/**@author yuma you zhao yufei zhou
 * 
 */


public class Word {
	public final static boolean FOR_INT=true;
	public final static boolean FOR_FLOAT=false;
	public boolean Float=false;
	
	
	public final static int SIZE=18;
	public final static int SIZEM1=SIZE-1;
	public int[] data;
	public static void donothing()
	{}
	public Word(){
		data = new int[SIZE];
	}
	public Word(Word w){
		data = w.getData().clone();
	}
	
	public Word(int[] data){
		this.data = new int[2048];
		this.setValue(data);
	}
	public Word(int[] data,boolean ForInt){
		if(ForInt)
		{
			this.data = new int[2048];
			this.setValue(data);
		}
		else
		{
			this.Float=true;
			this.data=data;
		}	
	}
	public Word clone(){
		return new Word(this);
	}
	
	public void setIntValue(int value){
		for(int i=0;i<SIZE;i++){
			data[SIZEM1-i] = value%2;
			value = value/2;
		}
	}
	
	public static void setIntValue(int[] data,int value){
		for(int i=0;i<SIZE;i++){
			data[SIZEM1-i] = value%2;
			value = value/2;
		}
	}
	
	public static void setIntValueForFloat(int[] data,int value){
		for(int i=0;i<16;i++){
			data[15-i] = value%2;
			value = value/2;
		}
	}
	
	public Word(int value){
		data = new int[SIZE];
		this.setIntValue(value);
	}
	
	public int[] getData(){
		//System.out.println("data "+data);
		return data;	
	}
	
	public int intValue(){
		
			int val = 0;
			for(int i=0;i<data.length;i++){
				val = val*2 + data[i];
			}
			return val;
		}
	public static int intValueOfHalfFloat(int[]data){
		
		int val = 0;
		for(int i=0;i<16;i++){
			val = val*2 + data[i];
		}
		return val;
	}	
	public int intValue(int start, int end){
		
		int val = 0;
		int len=Math.min(end, data.length);
		if(start>=len)
		{
			System.out.println("Dbg: stroke error");
		}
		for(int i=start;i<len;i++){
			val = val*2 + data[i];
		}
		return val;
	}
	public static int intValue(int[] data,int start, int end){
		
		int val = 0;
		int len=Math.min(end, data.length);
		if(start>=len)
		{
			System.out.println("Dbg: stroke error");
		}
		for(int i=start;i<len;i++){
			val = val*2 + data[i];
		}
		return val;
	}
	public static int intValue(int[] data){
		
		int val = 0;
		for(int i=0;i<data.length;i++){
			val = val*2 + data[i];
		}
		return val;
	}	
	
	
	public void setValue(Word word){
		int n[] = word.getData();
		for(int i=0;i<SIZE;i++){
			data[i] = n[i];
		}
	}
	public void setValue(int[]data){
		for(int i=0;i<SIZE;i++){
			this.data[SIZEM1-i] = (i<data.length)?(data[data.length-1-i]):0;
		}
	}
}
