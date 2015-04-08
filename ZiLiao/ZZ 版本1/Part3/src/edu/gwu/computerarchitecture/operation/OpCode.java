package edu.gwu.computerarchitecture.operation;
/**
 * 
 * The OpCode class set the OpCode with the number.
 *
 * <p>detailed comment
 * @author Bin Zhang 2013年9月16日
 * @see
 * @since 1.0
 * @version 2.2
 */
public class OpCode {
	public static final int HLT = 0;
	public static final int TRAP = 30;
	public static final int LDR = 1;
	public static final int STR = 2;
	public static final int LDA = 3;
	public static final int LDX = 41;
	public static final int STX = 42;
	public static final int JZ = 10;
	public static final int JNE = 11;
	public static final int JCC = 12;
	public static final int JMP = 13;
	public static final int JSR = 14;
	public static final int RFS = 15;
	public static final int SOB = 16;
	public static final int JGE = 17;
	public static final int JLE = 18;
	public static final int AMR = 4;
	public static final int SMR = 5;
	public static final int AIR = 6;
	public static final int SIR = 7;
	public static final int MUL = 20;
	public static final int DIV = 21;
	public static final int TER = 22;
	public static final int AND = 23;
	public static final int ORR = 24;
	public static final int NOT = 25;
	public static final int SRC = 31;
	public static final int RRC = 32;
	public static final int IN = 61;
	public static final int OUT = 62;
	public static final int CHK = 63;
	public static final int FADD = 33;
	public static final int FSUB = 34;
	public static final int VADD = 35;
	public static final int VSUB = 36;
	public static final int CNVRT = 37;	
}
