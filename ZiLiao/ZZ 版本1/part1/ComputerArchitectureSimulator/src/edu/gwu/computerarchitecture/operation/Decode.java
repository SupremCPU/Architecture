package edu.gwu.computerarchitecture.operation;

import edu.gwu.computerarchitecture.cpu.CPUUnits;
import edu.gwu.computerarchitecture.operation.execute.Execute;
import edu.gwu.computerarchitecture.operation.execute.ExecuteLDR;
import edu.gwu.computerarchitecture.operation.execute.ExecuteSTR;

/**
 * 
 * The Decode class is used for decompsing the instruction.
 *
 * <p>detailed comment
 * @author Bin Zhang 2013年9月16日
 * @see Instruction, Execute
 * @since 1.0
 * @version 1.0
 */
public class Decode {
	/*
	 * instructionInt is the 16 bits.
	 */
	public int instructionInt;
	/*
	 * instruction is the decomposed instruction.
	 */
	public Instruction instruction;
	/*
	 * cpuunits is the CPU's regeister & memroy..
	 */
//	public CPUUnits cpuunits;
	/*
	 * execution construct the extra execute substance.
	 */
	public Execute execution;
	
	/**
	 * 
	 * constructor that decompose the instruction while construct it.
	 * 
	 * @param intstr
	 * @param units
	 * @since 1.0
	 */
	public Decode(int intstr){
		//cpuunits = units;
	    instructionInt = intstr;
		instruction = decomposeInstruction(instructionInt);
	}
	/**
	 * 
	 * decompose the instruction into structure Instruction.
	 * 
	 * @param instrInt
	 * @return Instruction
	 * @since 1.0
	 */
	private Instruction decomposeInstruction(int instrInt){
		Instruction instr = new Instruction();
		
		instr.opcode = instrInt >> 10;	//get the first 6-bits OpCode
		//according to the OpCode, determining the instruction type and the instruction string.
		switch (instr.opcode){
		case OpCode.HLT:
			instr.instrString = "HLT";
			instr.instrType = InstructionType.OtherType;
			break;
		case OpCode.LDA:
			instr.instrString = "LDA";
			instr.instrType = InstructionType.LSType;
			break;
		case OpCode.LDR:
			instr.instrString = "LDR";
			instr.instrType = InstructionType.LSType;	
			instr.iTag = (instrInt&0x200)>>9;		
			instr.ixTag = (instrInt&0x100)>>8;
			instr.rsr1 = (instrInt&0xc0)>>6;
			instr.address = instrInt&0x3f;
			execution = new ExecuteLDR(instr);	
			execution.execute();
			break;
		case OpCode.STR:
			instr.instrString = "STR";
			instr.instrType = InstructionType.LSType;
			instr.iTag = (instrInt&0x200)>>9;		
			instr.ixTag = (instrInt&0x100)>>8;
			instr.rsr1 = (instrInt&0xc0)>>6;
			instr.address = instrInt&0x3f;
			execution = new ExecuteSTR(instr);	
			execution.execute();
			break;
		case OpCode.TRAP:
			instr.instrString = "TRAP";
			instr.instrType = InstructionType.OtherType;
			break;	
		}
		
		//according to the instruction type, determining the other Instruction variable value.
		switch(instr.instrType){
		case InstructionType.LSType:
			instr.iTag = (instrInt>>9) - (instrInt>>10<<1);
			instr.ixTag = (instrInt>>8) - (instrInt>>9<<1);
			instr.rsr1 = (instrInt>>6) - (instrInt>>8<<2);
			instr.address = instrInt - (instrInt>>6<<6);
			break;
		case InstructionType.RType:
			
			break;
		case InstructionType.IType:
			instr.iTag = (instrInt>>9) - (instrInt>>10<<1);
			instr.ixTag = (instrInt>>8) - (instrInt>>9<<1);
			instr.rsr1 = (instrInt>>6) - (instrInt>>8<<2);
			instr.address = instrInt - (instrInt>>6<<6);
			break;
		case InstructionType.TransferType:
			instr.iTag = (instrInt>>9) - (instrInt>>10<<1);
			instr.ixTag = (instrInt>>8) - (instrInt>>9<<1);
			instr.rsr1 = (instrInt>>6) - (instrInt>>8<<2);
			instr.address = instrInt - (instrInt>>6<<6);
			break;
		case InstructionType.OtherType:
			
			break;
			
		}
		return instr;
	}
	
}
