package Instructions;

/**
 * youzhao yuma
 */

public class InstructionCircle {

	
	public static final int TYPE_REG2REG = 0x1;
	public static final int TYPE_MEM2REG = 0x2;
	public static final int TYPE_REG2MEM = 0x3;
	public static final int TYPE_DECODE = 0x4;
	public static final int TYPE_CALCULATE_EA_TO_MAR = 0x5;
	public static final int TYPE_ALU_EXECUTE = 0x6;
	public static final int TYPE_PC_PLUS = 0x7;
	
	public static final int TYPE_CALCULATE_MBR_FOR_EXCUTE = 0x8;
	public static final int TYPE_LOAD_OP1_AND_OP2 = 0x9;
	
	public static final int TYPE_JUMP_WITHCONDITION = 0x10;
	public static final int TYPE_DEV2REG = 0x11;	
	public static final int TYPE_REG2DEV = 0x12;	
	public static final int TYPE_CHK_DEV = 0x13;
	public static final int TYPE_LOAD_OP1 = 0x14;
	public static final int TYPE_MinusOne= 0x15;
	public static final int TYPE_CALCULATE_EA_TO_MAR_NO_IX = 0x16;
	public static final int	TYPE_LOAD_F1_AND_F2=0x17;
	public static final int	TYPE_FALU_EXECUTE=0x18;	
	
	public int type;
	public String[] args;

	
	public InstructionCircle(int type,String...args){
		this.type = type;
		this.args = args.clone();
	}
}
