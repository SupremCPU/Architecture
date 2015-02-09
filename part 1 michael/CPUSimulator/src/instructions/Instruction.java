package instructions;

public class Instruction {

	/*
	 * Public different operation type, this equal the OPcode
	 * public each type operation in a array block, the column is string arg, the row is type
	 * each type is static value which is unique identify
	 */
	public static final int register_to_register = 0x1;
	public static final int memory_to_register = 0x2;
	public static final int register_to_memory = 0x3;
	public static final int getcode = 0x4;
	public static final int effecative_address = 0x5;
	public static final int ALU = 0x6;
	public static final int PC_plus = 0x7;
	
	public static final int memory_bufer_register = 0x8;
	public static final int OP1andOP2 = 0x9;
	
	
	public int type;
	public String[] args;

	//public each type operation in a array block
	public Instruction(int type,String...args){
		this.type = type;
		this.args = args.clone();
	}
}
