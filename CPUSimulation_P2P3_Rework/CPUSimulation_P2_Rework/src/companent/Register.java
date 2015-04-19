package companent;

import caculation.Util;


public class Register {

	protected String name;
	protected int data[];
	protected int size;
	
	public Register(String name,int size){
		this.name = name;
		this.size = size;
		this.data = new int[size];
		for(int i=0;i<size;i++)
		{
			this.data[i]=0;
		}
	}
	
	public int[] read(){
		return data;
	}
	public void writeBit(int value, int Bit)
	{
		if (Bit>=this.data.length)
		{
			System.out.println("Error");
		}
		this.data[Bit]=value;
	}
	
	/**
	 * write input information into Register
	 * <br>if in.length<size: copy all bits from $in$ and set the left to 0
	 * <br>if in.length>size: copy only the $size$ bits of $in$ and ignore the others
	 * @param in
	 * @author  you zhao yuma
	 */
	public void write(int in[]){
		//TODO :exception
		//System.out.println("write in");
		for(int i=0;i<size;i++){
			if(i<in.length)
				data[size - i - 1] = in[in.length-1-i];
			else
				data[size-i-1] = 0;
		}
		//System.out.println("write out");
	}
	public void writeFloat(int in[]){ //same operation but could be changed potentially
		//TODO :exception
		//System.out.println("write in");
		for(int i=0;i<size;i++){
			if(i<in.length)
				data[size - i - 1] = in[in.length-1-i];
			else
				data[size-i-1] = 0;
		}
		//System.out.println("write out");
	}
	public int intValue(){
		return Util.getIntValueFromBits(data);
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
