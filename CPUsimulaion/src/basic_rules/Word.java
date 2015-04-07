package basic_rules;


import basic_rules.Word;

/**@author yuma you zhao
 * 
 */

public class Word {

	public int[] data;
	
	public Word(){
		data = new int[18];
	}
	public Word(Word w){
		data = w.getData().clone();
	}
	
	public Word(int[] data){
		this.data = new int[2048];
		this.setValue(data);
	}
	
	public Word clone(){
		return new Word(this);
	}
	
	public void setIntValue(int value){
		for(int i=0;i<18;i++){
			data[17-i] = value%2;
			value = value/2;
		}
	}
	
	public int[] getData(){
		return data;	
	}
	
	public int intValue(){
		return 0;
	}
	public void setValue(Word word){
		int n[] = word.getData();
		for(int i=0;i<18;i++){
			data[i] = n[i];
		}
	}
	public void setValue(int[]data){
		for(int i=0;i<18;i++){
			this.data[18-i-1] = (i<data.length)?(data[data.length-1-i]):0;
		}
	}
}
