package edu.gwu.computerarchitecture.operation;
/**
 * 
 * The Instruction class is used for store the structure of the Instruction.
 *
 * <p>detailed comment
 * @author Bin Zhang 2013年9月16日
 * @see OpCode, InstructionType
 * @since 1.0
 * @version 1.0
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
	 * instrString is the instruction's name, used for toString
	 */
	public String instrString;
	/*
	 * instrType is the instruction's type:
	 * R-type, I-type, Load/Store instruction, Transfer instruction and other.
	 */
	public int instrType;
	
	/**
	 * 
	 * default constructor, the default OP is HLT.
	 * @param null
	 * @since 1.0
	 */
	public Instruction(){
		opcode = OpCode.HLT;		
	}
	
	/*
	 * used for printing the instruction
	 * @param null
	 * @return String
	 * @since 1.0
	 */
	/**
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return instrString;
	}
	
}
