package caculation;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class computering {

	 /*
	  * integer value = (i)^2, 
	  */
	public static int getIntValueFromBits(int[]bits){
		int val = 0;
		for(int i=0;i<bits.length;i++){
			val = val*2 + bits[i];
		}
		return val;
	}
	   /*
	    * value%2 will get 0 or 1, and add these divide value is the int value
	    */
	public static int[] getBitsFromIntValue(int value,int size){
		int[] data = new int[size];
		for(int i=0;i<size;i++){
			data[size-1-i] = value%2;
			value = value/2;
		}
		return data;
	}
	    /*
	     * transfer bits[] to string
	     */
	public static String getBinaryStringFromBits(int[]bits){
		StringBuffer buf = new StringBuffer();
		for(int i=0;i<bits.length;i++){
			buf.append(bits[i]);
			if(i%4==3)
				buf.append(' ');
		}
		return buf.toString();
	}
	
	public static String getBinaryStringFormBitsInInstructionFormat(int[]bits){
		if(bits==null)
			return "------  --  --  -  -------";
		StringBuffer buf = new StringBuffer();
		for(int i=0;i<bits.length;i++){
			buf.append(bits[i]);

		}
		return buf.toString();
	}
	
	private static Format timeFormat = new SimpleDateFormat("HH:mm:ss");
	public static String getCurrentTime(){
		return timeFormat.format(new Date());
	}
	
	public static int[] getBitsFromBinaryString(String str){
		int[] result = new int[str.length()];
		for(int i=0;i<result.length;i++)
			result[i] = Integer.parseInt(String.valueOf(str.charAt(i)));
		return result;
	}
}
