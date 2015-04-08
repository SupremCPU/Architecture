package edu.gwu.computerarchitecture.operation;

import edu.gwu.computerarchitecture.cpu.CPUUnits;
import edu.gwu.computerarchitecture.operation.execute.Execute;
import edu.gwu.computerarchitecture.operation.execute.ExecuteAIR;
import edu.gwu.computerarchitecture.operation.execute.ExecuteAMR;
import edu.gwu.computerarchitecture.operation.execute.ExecuteAND;
import edu.gwu.computerarchitecture.operation.execute.ExecuteCHK;
import edu.gwu.computerarchitecture.operation.execute.ExecuteCNVRT;
import edu.gwu.computerarchitecture.operation.execute.ExecuteDIV;
import edu.gwu.computerarchitecture.operation.execute.ExecuteFADD;
import edu.gwu.computerarchitecture.operation.execute.ExecuteFSUB;
import edu.gwu.computerarchitecture.operation.execute.ExecuteIN;
import edu.gwu.computerarchitecture.operation.execute.ExecuteJCC;
import edu.gwu.computerarchitecture.operation.execute.ExecuteJGE;
import edu.gwu.computerarchitecture.operation.execute.ExecuteJLE;
import edu.gwu.computerarchitecture.operation.execute.ExecuteJMP;
import edu.gwu.computerarchitecture.operation.execute.ExecuteJNE;
import edu.gwu.computerarchitecture.operation.execute.ExecuteJSR;
import edu.gwu.computerarchitecture.operation.execute.ExecuteJZ;
import edu.gwu.computerarchitecture.operation.execute.ExecuteLDA;
import edu.gwu.computerarchitecture.operation.execute.ExecuteLDR;
import edu.gwu.computerarchitecture.operation.execute.ExecuteLDX;
import edu.gwu.computerarchitecture.operation.execute.ExecuteMUL;
import edu.gwu.computerarchitecture.operation.execute.ExecuteNOT;
import edu.gwu.computerarchitecture.operation.execute.ExecuteORR;
import edu.gwu.computerarchitecture.operation.execute.ExecuteOUT;
import edu.gwu.computerarchitecture.operation.execute.ExecuteRFS;
import edu.gwu.computerarchitecture.operation.execute.ExecuteRRC;
import edu.gwu.computerarchitecture.operation.execute.ExecuteSIR;
import edu.gwu.computerarchitecture.operation.execute.ExecuteSMR;
import edu.gwu.computerarchitecture.operation.execute.ExecuteSOB;
import edu.gwu.computerarchitecture.operation.execute.ExecuteSRC;
import edu.gwu.computerarchitecture.operation.execute.ExecuteSTR;
import edu.gwu.computerarchitecture.operation.execute.ExecuteSTX;
import edu.gwu.computerarchitecture.operation.execute.ExecuteTER;
import edu.gwu.computerarchitecture.operation.execute.ExecuteTRAP;
import edu.gwu.computerarchitecture.operation.execute.ExecuteVADD;
import edu.gwu.computerarchitecture.operation.execute.ExecuteVSUB;

/**
 * 
 * The Decode class is used for decompsing the instruction.
 *
 * <p>detailed comment
 * @author Bin Zhang 2013年9月16日
 * @see Instruction, Execute
 * @since 1.0
 * @version 2.6
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
	 * cpuunits is the CPU's register & memory.
	 */
	public CPUUnits cpuunits;
	/*
	 * execution construct the extra execute substance.
	 */
	public Execute execution;
	/*
	 * memoryStage construct the MemoryStage substance.
	 */
	public MemoryStage memoryStage;
	/*
	 * writeBack construct the WriteBack substance.
	 */
	public WriteBack writeBack;
	/**
	 * 
	 * constructor that decompose the instruction while construct it.
	 * 
	 * @param intstr
	 * @param units
	 * @since 1.0
	 */
	public Decode(int intstr, CPUUnits units){
		cpuunits = units;
	    instructionInt = intstr&0xFFFF;
		instruction = decomposeInstruction(instructionInt);
	}
	/**
	 * 
	 * decompose the instruction into structure Instruction.
	 * 
	 * @param instrInt
	 * @return Instruction
	 * @since 1.1
	 */
	private Instruction decomposeInstruction(int instrInt){
		Instruction instr = new Instruction();
		instrInt = instrInt & 0xffff;
		int opInt = instrInt >> 10;	//get the first 6-bits OpCode
		instr.opcode = opInt;
		
		//according to the OpCode, determining the instruction type and the instruction string.
		switch (opInt){
		case OpCode.HLT:
			instr.instrString = "HLT";
			instr.instrType = InstructionType.OtherType;
			execution = null;
			memoryStage = null;
			writeBack = null;
			break;
		case OpCode.TRAP:
			instr.instrString = "TRAP";
			instr.instrType = InstructionType.OtherType;
			execution = new ExecuteTRAP(cpuunits, instr);
            memoryStage = null;
            writeBack = null;
			break;
		case OpCode.LDR:
			instr.instrString = "LDR";
			instr.instrType = InstructionType.LSType;
			execution = new ExecuteLDR(cpuunits, instr);
			memoryStage = null;
			writeBack = new WriteBack(cpuunits, instr);
			break;
		case OpCode.STR:
		    instr.instrString = "STR";
		    instr.instrType = InstructionType.LSType;
		    execution = new ExecuteSTR(cpuunits, instr);
            memoryStage = new MemoryStage(cpuunits);
            writeBack = null;
		    break;
		case OpCode.LDA:
			instr.instrString = "LDA";
			instr.instrType = InstructionType.LSType;
			execution = new ExecuteLDA(cpuunits, instr);
			memoryStage = null;
			writeBack = new WriteBack(cpuunits, instr);
			break;
		case OpCode.LDX:
            instr.instrString = "LDX";
            instr.instrType = InstructionType.LSType;
            execution = new ExecuteLDX(cpuunits, instr);
            memoryStage = null;
            writeBack = new WriteBack(cpuunits, instr);
            break;
		case OpCode.STX:
            instr.instrString = "STX";
            instr.instrType = InstructionType.LSType;
            execution = new ExecuteSTX(cpuunits, instr);
            memoryStage = new MemoryStage(cpuunits);
            writeBack = null;
            break;
		case OpCode.JZ:
            instr.instrString = "JZ";
            instr.instrType = InstructionType.TransferType;
            execution = new ExecuteJZ(cpuunits, instr);
            memoryStage = null;
            writeBack = null;
            break;
		case OpCode.JNE:
            instr.instrString = "JNE";
            instr.instrType = InstructionType.TransferType;
            execution = new ExecuteJNE(cpuunits, instr);
            memoryStage = null;
            writeBack = null;
            break;
		case OpCode.JCC:
            instr.instrString = "JCC";
            instr.instrType = InstructionType.TransferType;
            execution = new ExecuteJCC(cpuunits, instr);
            memoryStage = null;
            writeBack = null;
            break;
		case OpCode.JMP:
            instr.instrString = "JMP";
            instr.instrType = InstructionType.TransferType;
            execution = new ExecuteJMP(cpuunits, instr);
            memoryStage = null;
            writeBack = null;
            break;
		case OpCode.JSR:
            instr.instrString = "JSR";
            instr.instrType = InstructionType.TransferType;
            execution = new ExecuteJSR(cpuunits, instr);
            memoryStage = null;
            writeBack = new WriteBack(cpuunits, instr);
            break;
		case OpCode.RFS:
            instr.instrString = "RFS";
            instr.instrType = InstructionType.TransferType;
            execution = new ExecuteRFS(cpuunits, instr);
            memoryStage = null;
            writeBack = new WriteBack(cpuunits, instr);
            break;
		case OpCode.SOB:
            instr.instrString = "SOB";
            instr.instrType = InstructionType.TransferType;
            execution = new ExecuteSOB(cpuunits, instr);
            memoryStage = null;
            writeBack = null;
            //= new WriteBack(cpuunits, instr);
            break;
		case OpCode.JGE:
            instr.instrString = "JGE";
            instr.instrType = InstructionType.TransferType;
            execution = new ExecuteJGE(cpuunits, instr);
            memoryStage = null;
            writeBack = null;
            break;
		case OpCode.JLE:
		    instr.instrString = "JLE";
		    instr.instrType = InstructionType.TransferType;
		    execution = new ExecuteJLE(cpuunits, instr);
		    memoryStage = null;
		    writeBack = null;
		    break;
		case OpCode.AMR:
            instr.instrString = "AMR";
            instr.instrType = InstructionType.IType;
            execution = new ExecuteAMR(cpuunits, instr);
            memoryStage = null;
            writeBack = new WriteBack(cpuunits, instr);
            break;
		case OpCode.SMR:
            instr.instrString = "SMR";
            instr.instrType = InstructionType.IType;
            execution = new ExecuteSMR(cpuunits, instr);
            memoryStage = null;
            writeBack = new WriteBack(cpuunits, instr);
            break;
		case OpCode.AIR:
            instr.instrString = "AIR";
            instr.instrType = InstructionType.IType;
            execution = new ExecuteAIR(cpuunits, instr);
            memoryStage = null;
            writeBack = new WriteBack(cpuunits, instr);
            break;
		case OpCode.SIR:
            instr.instrString = "SIR";
            instr.instrType = InstructionType.IType;
            execution = new ExecuteSIR(cpuunits, instr);
            memoryStage = null;
            writeBack = new WriteBack(cpuunits, instr);
            break;
		case OpCode.MUL:
            instr.instrString = "MUL";
            instr.instrType = InstructionType.RType;
            execution = new ExecuteMUL(cpuunits, instr);
            memoryStage = null;
            writeBack = new WriteBack(cpuunits, instr);
            break;
		case OpCode.DIV:
            instr.instrString = "DIV";
            instr.instrType = InstructionType.RType;
            execution = new ExecuteDIV(cpuunits, instr);
            memoryStage = null;
            writeBack = new WriteBack(cpuunits, instr);
            break;
		case OpCode.TER:
            instr.instrString = "TER";
            instr.instrType = InstructionType.RType;
            execution = new ExecuteTER(cpuunits, instr);
            memoryStage = null;
            writeBack = null;
            break;
		case OpCode.AND:
            instr.instrString = "AND";
            instr.instrType = InstructionType.RType;
            execution = new ExecuteAND(cpuunits, instr);
            memoryStage = null;
            writeBack = new WriteBack(cpuunits, instr);
            break;
		case OpCode.ORR:
            instr.instrString = "ORR";
            instr.instrType = InstructionType.RType;
            execution = new ExecuteORR(cpuunits, instr);
            memoryStage = null;
            writeBack = new WriteBack(cpuunits, instr);
            break;
		case OpCode.NOT:
            instr.instrString = "NOT";
            instr.instrType = InstructionType.RType;
            execution = new ExecuteNOT(cpuunits, instr);
            memoryStage = null;
            writeBack = new WriteBack(cpuunits, instr);
            break;
		case OpCode.SRC:
            instr.instrString = "SRC";
            instr.instrType = InstructionType.SRType;
            execution = new ExecuteSRC(cpuunits, instr);
            memoryStage = null;
            writeBack = new WriteBack(cpuunits, instr);
            break;
		case OpCode.RRC:
            instr.instrString = "RRC";
            instr.instrType = InstructionType.SRType;
            execution = new ExecuteRRC(cpuunits, instr);
            memoryStage = null;
            writeBack = new WriteBack(cpuunits, instr);
            break;
		case OpCode.IN:
            instr.instrString = "IN";
            instr.instrType = InstructionType.IOType;
            execution = new ExecuteIN(cpuunits, instr);
            memoryStage = null;
            writeBack = null;
            break;
		case OpCode.OUT:
            instr.instrString = "OUT";
            instr.instrType = InstructionType.IOType;
            execution = new ExecuteOUT(cpuunits, instr);
            memoryStage = null;
            writeBack = null;
            break;
		case OpCode.CHK:
            instr.instrString = "CHK";
            instr.instrType = InstructionType.IOType;
            execution = new ExecuteCHK(cpuunits, instr);
            memoryStage = null;
            writeBack = null;
            break;
		case OpCode.FADD:
            instr.instrString = "FADD";
            instr.instrType = InstructionType.FType;
            execution = new ExecuteFADD(cpuunits, instr);
            break;
		case OpCode.FSUB:
            instr.instrString = "FSUB";
            instr.instrType = InstructionType.FType;
            execution = new ExecuteFSUB(cpuunits, instr);
            break;
		case OpCode.VADD:
            instr.instrString = "VADD";
            instr.instrType = InstructionType.FType;
            execution = new ExecuteVADD(cpuunits, instr);
            break;
		case OpCode.VSUB:
            instr.instrString = "VSUB";
            instr.instrType = InstructionType.FType;
            execution = new ExecuteVSUB(cpuunits, instr);
            break;
		case OpCode.CNVRT:
            instr.instrString = "CNVRT";
            instr.instrType = InstructionType.FType;
            execution = new ExecuteCNVRT(cpuunits, instr);
            break;
        default:
            instr.instrString = "NULL Instrution";
            instr.instrType = InstructionType.WType;
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
			instr.rsr1 = (instrInt>>8) - (instrInt>>10<<2);
			instr.rsr2 = (instrInt>>6) - (instrInt>>8<<2);
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
			instr.rsr1 = (instrInt>>6) - (instrInt>>8<<2);
			break;
		case InstructionType.SRType:
		    instr.srLR = (instrInt>>9) - (instrInt>>10<<1);
		    instr.rsr1 = (instrInt>>7) - (instrInt>>9<<2);
		    instr.srAL = (instrInt>>6) - (instrInt>>7<<1);
		    instr.srCount = instrInt - (instrInt>>4<<4);
		    break;
		case InstructionType.IOType:
		    instr.rsr1 = (instrInt>>7) - (instrInt>>9<<2);
		    instr.ioDevID = instrInt - (instrInt>>5<<5);
		    break;
		case InstructionType.FType:
		    instr.iTag = (instrInt>>9) - (instrInt>>10<<1);
            instr.ixTag = (instrInt>>8) - (instrInt>>9<<1);
            instr.rsr1 = (instrInt>>6) - (instrInt>>8<<2);
            instr.address = instrInt - (instrInt>>6<<6);
            break;			
		}
		return instr;
	}
	
}
