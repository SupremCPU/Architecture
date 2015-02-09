package caculation;

import java.util.StringTokenizer;

import caculation.computering;
import instructions.InstructionSet;

public class getCode {

	/*
	 * decode the language code into binary code
	 */
	public static int[] decodeInstruction(String ins){
		try {
			
			StringTokenizer tokenizer = new StringTokenizer(ins," ,");
			
			//while(tokenizer.hasMoreElements()){
			//	System.out.println(tokenizer.nextToken());
			//}
			
			
			
			//OPCODE
			int opcodeValue = InstructionSet.getCodeOfInstruction(tokenizer.nextToken());
			
			int[]ops = computering.getBitsFromIntValue(opcodeValue, 6);
			//R
			int rValue = Integer.parseInt(tokenizer.nextToken());
			
			int[] rs = computering.getBitsFromIntValue(rValue, 2);
			//IX
			int ixValue = Integer.parseInt(tokenizer.nextToken());
			
			int[] ixs = computering.getBitsFromIntValue(ixValue, 2);
			//I
			int iValue = Integer.parseInt(tokenizer.nextToken());
			
			int[] is = computering.getBitsFromIntValue(iValue, 1);
			//ADDR
			int addrValue = Integer.parseInt(tokenizer.nextToken());
			
			int[] addrs = computering.getBitsFromIntValue(addrValue, 5);
			
			int[]result = new int[16];
			
			for(int i=0;i<ops.length;i++)
				result[i] = ops[i];
			
			for(int i=0;i<ixs.length;i++)
				result[i+6] = ixs[i];
			
			for(int i=0;i<rs.length;i++)
				result[i+8] = rs[i];
			
			for(int i=0;i<is.length;i++)
				result[i+10] = is[i];
			
			for(int i=0;i<addrs.length;i++)
				result[i+11] = addrs[i];
			
			
			return result;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	
}
