package instructions;

import java.util.HashMap;
import java.util.Map;

/*
 * All the instructions will be defined in this class, including:
 *  name,code and every step(process)
 * @author You Zhao   YU Ma
 *
 */
public class InstructionSet {

	private static InstructionSet _instance;
	
	// public a static value as a unique recognition
	public static InstructionSet getInstance()
	{
		if(_instance==null)
			_instance = new InstructionSet();
		return _instance;
	}
	
	/*
	 * map for finding code by name
	 */
	private Map<String,Integer> nametocode;
	/*
	 * map for finding Instruction processes by code
	 */
	private Map<Integer,Instruction_step> codetoinstruction;
	
	/*
	 * use map array the operation order, and use hashmap that help find the fit Operation
	 */
	public InstructionSet(){
		
		nametocode = new HashMap<String,Integer>();
		codetoinstruction = new HashMap<Integer,Instruction_step>();
		
		setLDR();
		
		setSTR();
		
		setLDA();
		
		setAMR();
		setSMR();
		
		setAIR();
		setSIR();
		
		
		
		setLDX();
		setSTX();
	}
	
	/*
	 *  LDR: Load Register From Memory
	 *  01
	 */
	private void setLDR(){
		Instruction_step processes = new Instruction_step();
		processes.addCircle(new Instruction(Instruction.register_to_register,"MAR","ADDR"));
		processes.addCircle(new Instruction(Instruction.memory_to_register,"MBR","MAR"));
		processes.addCircle(new Instruction(Instruction.register_to_register,"R","MBR"));
		processes.addCommonEndingprocessing();
		
		nametocode.put("LDR", 1);
		codetoinstruction.put(1, processes);
	}
	
	/*
	 * Store Register To Memory
	 * start from load the address value to effecative_address(EA)
	 * MAR holds the data fetched from memory
	 * update the EA
	 * the OPcode of STR is 2 
	 */
	private void setSTR(){
		Instruction_step processes = new Instruction_step();
		processes.addCircle(new Instruction(Instruction.effecative_address));
		processes.addCircle(new Instruction(Instruction.register_to_register,"MAR","effecative_address"));
		processes.addCircle(new Instruction(Instruction.register_to_memory,"R","effecative_address"));
		processes.addCommonEndingprocessing();
		
		nametocode.put("STR", 2);
		codetoinstruction.put(2, processes);
		
	}
	
	/*
	 * Load Register with Address 
	 * LDA OPcode is 3
	 */
	private void setLDA(){
		
	}
	
	/*
	 * Add Memory value To Register
	 * start from load the address value to effecative_address(EA)
	 * memory buffer reader get the address, update the EA
	 * one register holds data in OP1, another holds in OP2
	 * ALU add two data and output the add result
	 * AMR OPcode is4
	 */
	private void setAMR(){
		Instruction_step processes = new Instruction_step();
		processes.addCircle(new Instruction(Instruction.effecative_address));
		processes.addCircle(new Instruction(Instruction.memory_to_register,"MBR","effecative_address"));
		processes.addCircle(new Instruction(Instruction.OP1andOP2,"R","MBR"));
		processes.addCircle(new Instruction(Instruction.ALU));
		processes.addCircle(new Instruction(Instruction.register_to_register,"R","RES"));
		processes.addCommonEndingprocessing();

		nametocode.put("AMR", 4);
		codetoinstruction.put(4, processes);
	}
	
	/*
	 * Subtract Memory value From Register
	 * start from load the address value to effecative_address(EA)
	 * memory buffer reader get the address, update the EA
	 * one register holds data in OP1, another holds in OP2
	 * ALU Subtract two data and output the Subtract result
	 * SMR OPcode is 5
	 */
	private void setSMR(){
		Instruction_step processes = new Instruction_step();
		processes.addCircle(new Instruction(Instruction.effecative_address));
		processes.addCircle(new Instruction(Instruction.memory_to_register,"MBR","effecative_address"));
		processes.addCircle(new Instruction(Instruction.OP1andOP2,"R","MBR"));
		processes.addCircle(new Instruction(Instruction.ALU));
		processes.addCircle(new Instruction(Instruction.register_to_register,"R","RES"));
		processes.addCommonEndingprocessing();

		nametocode.put("SMR", 5);
		codetoinstruction.put(5, processes);
	}
	
	/*
	 * Add  Immediate value to Register
	 * Subtract Immediate value From Register
	 * start from load the address value to effecative_address(EA)
	 * memory buffer reader get the address, update the EA
	 * one register holds data in OP1, another holds in OP2
	 * ALU add two data and output the add result
	 * AIR OPcode is 6
	 */
	private void setAIR(){
		Instruction_step processes = new Instruction_step();
		processes.addCircle(new Instruction(Instruction.OP1andOP2,"R","ADDR"));
		processes.addCircle(new Instruction(Instruction.ALU));
		processes.addCircle(new Instruction(Instruction.register_to_register,"R","RES"));
		processes.addCommonEndingprocessing();

		nametocode.put("AIR", 6);
		codetoinstruction.put(6, processes);
	}
	
	/*
	 * Subtract  Immediate value from Register
	 * start from load the address value to effecative_address(EA)
	 * memory buffer reader get the address, update the EA
	 * one register holds data in OP1, another holds in OP2
	 * ALU subtract two data and output the subtract result
	 * AIR OPcode is 7
	 */
	private void setSIR(){
		Instruction_step processes = new Instruction_step();
		processes.addCircle(new Instruction(Instruction.OP1andOP2,"R","ADDR"));
		processes.addCircle(new Instruction(Instruction.ALU));
		processes.addCircle(new Instruction(Instruction.register_to_register,"R","RES"));
		processes.addCommonEndingprocessing();

		nametocode.put("SIR", 7);
		codetoinstruction.put(7, processes);
	}
	//Load index value in register from memory
	private void setLDX(){
		
	}
	//Store index value in register from memory
	private void setSTX(){
		
	}
	
	
	
	
	public Instruction_step getprocesses2(String name){
		return codetoinstruction.get(nametocode.get(name));
	}
	public Instruction_step getprocesses2(int code){
		return codetoinstruction.get(code);
	}
	public static Instruction_step getprocesses(String name){
		return InstructionSet.getInstance().codetoinstruction.get(
				InstructionSet.getInstance().nametocode.get(name));
	}
	public static Instruction_step getprocesses(int code){
		return InstructionSet.getInstance().codetoinstruction.get(code);
	}
	
	
	public static int getCodeOfInstruction(String name){
		return InstructionSet.getInstance().nametocode.get(name);
	}
}
