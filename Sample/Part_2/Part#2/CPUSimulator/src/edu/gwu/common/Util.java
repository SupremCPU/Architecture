package edu.gwu.common;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Util {

	private static Format PERCENTAGE_FORMAT = new DecimalFormat("0.00%");
	
	//Transfer bits to int value
	public static int getIntValueFromBits(int[]bits){
		int val = 0;
		for(int i=0;i<bits.length;i++){
			val = val*2 + bits[i];
		}
		return val;
	}
	//Transfer int value to bits
	public static int[] getBitsFromIntValue(int value,int size){
		int[] data = new int[size];
		for(int i=0;i<size;i++){
			data[size-1-i] = value%2;
			value = value/2;
		}
		return data;
	}
	//Transfer Bits to Binary String
	public static String getBinaryStringFromBits(int[]bits){
		StringBuffer buf = new StringBuffer();
		for(int i=0;i<bits.length;i++){
			buf.append(bits[i]);
			if(i%4==3)
				buf.append(' ');
		}
		return buf.toString();
	}
	//Transfer Bits Instruction to Bianry String
	public static String getBinaryStringFormBitsInInstructionFormat(int[]bits){
		if(bits==null)
			return "------  --  --  -  -------";
		StringBuffer buf = new StringBuffer();
		for(int i=0;i<bits.length;i++){
			buf.append(bits[i]);
			if(i==5||i==7||i==9||i==10)
				buf.append("  ");
		}
		return buf.toString();
	}
	//Get current time
	private static Format timeFormat = new SimpleDateFormat("HH:mm:ss");
	public static String getCurrentTime(){
		return timeFormat.format(new Date());
	}
	//Transfer Binary String to Bits
	public static int[] getBitsFromBinaryString(String str){
		int[] result = new int[str.length()];
		for(int i=0;i<result.length;i++)
			result[i] = Integer.parseInt(String.valueOf(str.charAt(i)));
		return result;
	}
	//Merge list
	public static void mergeList(List<Integer> array,int[] data){
		for(int i=0;i<data.length;i++)
			array.add(data[i]);
	}
	
	
	public static String formatPercentage(double value){
		return PERCENTAGE_FORMAT.format(value);
	}
}
