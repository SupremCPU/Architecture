package companent;

import caculation.computering;

/**
 * 
 * @author YouZHao YuMa
 *
 */
public class register {

	protected String name;
	
	protected int data[];
	
	protected int size;
	
	
	
	
	
	
	public register(String name,int size){
		this.name = name;
		
		this.size = size;
		
		this.data = new int[size];
	}
	
	public int[] read(){
		return data;
	}
	
	/*
	 * write input information into Register
	 * if in.length<size: copy all bits from valid and set the left to 0
	 * if in.length>size: copy only the size bits of valid and ignore the others
	 * 
	 */
	
	public void write(int in[]){
		//TODO :exception
		for(int i=0;i<size;i++){
			if(i<in.length)
				data[size - i - 1] = in[in.length-1-i];
			else
				data[size-i-1] = 0;
		}
	}
	
	public int intValue(){
		return computering.getIntValueFromBits(data);
	}
	
	public void setValueByInt(int value){
		int val = value;
		for(int i=0;i<size;i++){
			data[size-i-1] = val%2;
			val = val/2;
		}
	}
	
	public void addValueByInt(int value){
		this.setValueByInt(this.intValue()+value);
	}
	
	public int[] subValue(int index,int size){
		int[] sub = new int[size];
		for(int i=0;i<size;i++)
			sub[i] = data[index+i];
		return sub;
	}
	public String getBinaryString(){
		StringBuffer buf = new StringBuffer();
		for(int i=0;i<size;i++)
			buf.append(data[i]);
		return buf.toString();
	}
	public int getSize(){
		return size;
	}
}
