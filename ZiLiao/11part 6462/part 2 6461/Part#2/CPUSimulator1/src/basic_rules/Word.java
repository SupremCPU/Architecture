package basic_rules;

import caculation.Util;
import basic_rules.Setting;

public class Word {

	private int[] data;
	
	public Word(){
		data = new int[Setting.WORD_SIZE];
	}
	
	public Word(Word w){
		data = w.getData().clone();
	}
	
	public Word(int[] data){
		this.data = new int[Setting.WORD_SIZE];
		this.setValue(data);
	}
	
	public int intValue(){
		
		return Util.getIntValueFromBits(data);
	}
	
	public void setIntValue(int value){
		for(int i=0;i<Setting.WORD_SIZE;i++){
			data[Setting.WORD_SIZE-1-i] = value%2;
			value = value/2;
		}
	}
	
	public int[] getData(){
		return data;
	}
	
	/**
	 * return a copy of Word
	 */
	public Word clone(){
		return new Word(this);
	}
	
	/**
	 * copy the bit values in word into current data
	 * @param word
	 */
	public void setValue(Word word){
		int n[] = word.getData();
		for(int i=0;i<Setting.WORD_SIZE;i++){
			data[i] = n[i];
		}
	}
	
	/**
	 * if data.length > 18
	 * 		write the last 18 bits of data;
	 * if data.length < 18
	 * 		use 0 to fill the empty position in the front
	 * @param data
	 */
	public void setValue(int[]data){
		for(int i=0;i<Setting.WORD_SIZE;i++){
			this.data[Setting.WORD_SIZE-i-1] = (i<data.length)?(data[data.length-1-i]):0;
		}
	}
}
