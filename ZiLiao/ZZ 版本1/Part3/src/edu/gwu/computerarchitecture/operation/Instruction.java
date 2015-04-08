package edu.gwu.computerarchitecture.operation;
/**
 * 
 * The Instruction class is used for store the structure of the Instruction.
 *
 * <p>detailed comment
 * @author Bin Zhang 2013年9月16日
 * @see OpCode, InstructionType
 * @since 1.0
 * @version 1.2
 */
public class Instruction {

	/*
	 * opcode is the value of an instruction's opcode.
	 * The opcode integer determination is in the OpCode class.
	 */
	public int opcode;
	/*
	 * rsr1 is the source 1 register index number.
	 * rsr2 is the source 2 register index number.
	 */
	public int rsr1;
	public int rsr2;
	/*
	 * iTag the indirect tag. If indirect then iTag = 1, else iTag = 0.
	 */
	public int iTag;
	/*
	 * ixTag the index tag. If index then ixTag = 1, else ixTag = 0.
	 */
	public int ixTag;
	/*
	 * address is the value of address of Load/Store type instructions.
	 * it is also the value of immediate of I-Type instruction.
	 */
	public int address;
	/*
	 * srLR is the tag that indicate the shift/rotate operation move left or right.
	 */
	public int srLR;
	/*
	 * srAL is the tag that indicate the shift/rotate operation move logic or arithmetic.
	 */
	public int srAL;
	/*
	 * srCount is shift/rotate operation move bits.
	 */
	public int srCount;
	/*
	 * ioDevID is the I/O operation DevID.
	 */
	public int ioDevID;
	/*
	 * instrString is the instruction's name, used for toString
	 */
	public String instrString;
	/*
	 * instrType is the instruction's type:
	 * R-type, I-type, Load/Store instruction, Transfer instruction and other.
	 */
	public int instrType;
	/*
	 * dataToRegister & dataToRegisterLowis the data for writeback
	 */
	public int dataToRegister;
	public int dataToRegisterLow;
	
	/**
	 * 
	 * default constructor, the default OP is HLT.
	 * @param null
	 * @since 1.0
	 */
	public Instruction(){
		opcode = OpCode.HLT;		
	}
	
	/**
	 * 
	 * used for printing the instruction
     * @param null
     * @return String
     * @since 1.0
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String str = instrString;
		switch(instrType){
		    case InstructionType.LSType:
		        str += ": I-" + iTag + "\tIX-" + ixTag + "\tR-" + rsr1 + "\tAddress-" + address;
		        break;
		    case InstructionType.RType:
	              str += ": R1-" + rsr1 + "\tR2-" + rsr2;
	              break;
		    case InstructionType.IType:
	              str += ": I-" + iTag + "\tIX-" + ixTag + "\tR-" + rsr1 + "\tImmediate-" + address;
		        break;
		    case InstructionType.TransferType:
	              str += ": I-" + iTag + "\tIX-" + ixTag + "\tR-" + rsr1 + "\tAddress-" + address;
		        break;
		    case InstructionType.OtherType:
		        break;
		    case InstructionType.SRType:
		        str += ": L/R-" + (srLR==1?"Left":"Right") + "\tR-" + rsr1 + "\tA/L-" + (srAL==1?"Logically":"Arithmetically") + "\tCount-" + srCount;
		        break;
		    case InstructionType.IOType:
		        str += ": R1-" + rsr1 + "\tDevID-" + ioDevID;
		        break;
		    case InstructionType.FType:
                str += ": I-" + iTag + "\tIX-" + ixTag + "\tR-" + rsr1 + "\tAddress-" + address;
		        break;
		    default:
		        str += "\tError Decoding!\n";		            
		}
	    
	    return str;
	}
	
}
