package companent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import caculation.computering;
//import caculation.computering;
import instructions.Instruction;
import instructions.Instruction_step;
import instructions.InstructionSet;
import companent.memory;
import frontPanel.Log;

public class CPU {

	private static CPU _instance;
	
	// when memory is null,CPU start;
	public static CPU getInstance(){
		if(_instance==null)
		 _instance = new CPU();
		return _instance;
	}
	

	/*
	 * all registers are stored here
	 */
	private Map<String,register> registers;
	/*
	 * just used to save the order of registers
	 */
	private List<String> registersOrder;
	
	private memory memory = companent.memory.getInstance();
	
	private Log log = Log.getInstance();
	/*
	 *Registers Address store in Map
	 */
	public CPU(){
		registers = new HashMap<String,register>();
		registersOrder = new ArrayList<String>();

		
		/*
		 * General Purpose Registers
		 * <br>16-bit
		 */
		addRegister("R0",16);
		addRegister("R1",16);
		addRegister("R2",16);
		addRegister("R3",16);
		
		/*
		 * index registers: contains a 12-bit base address that supports base register addressing of memory.
		 * <br>12-bit
		 */
		addRegister("X1",12);
		addRegister("X2",12);
		addRegister("X3",12);
		
		/*
		 * Program Counter: address of next instruction to be executed
		 * <br>12-bit
		 */
		addRegister("PC",12);
		
		/*
		 * Condition Code: set when arithmetic/logical operations are executed; 
		 * it has four 1-bit elements: overflow, underflow, division by zero, equal-or-not. 
		 * <br>4-bit
		 */	
		addRegister("CC",4);
		
		/*
		 * Instruction Register: holds the instruction to be executed
		 * <br>16-bit
		 */
		addRegister("IR",16);
		
		/*
		 * Memory Address Register: holds the address of the word to be fetched from memory
		 * <br>12-bit
		 */
		addRegister("MAR",12);
		
		/*
		 * Memory Buffer Register: holds the word just fetched from or stored into memory
		 * <br>16-bit
		 */
		addRegister("MBR",16);
		
		/*
		 * Machine Status Register: certain bits record the status of the health of the machine
		 * <br>16-bit
		 */
		addRegister("MSR",16);
		
		/*
		 * Machine Fault Register: contains the ID code if a machine fault after it occurs
		 * <br>4-bit
		 */
		addRegister("MFR",4);
		
		addRegister("OPCODE",6);
		addRegister("R",2);
		addRegister("I",1);
		addRegister("IX",2);
		addRegister("ADDR",8);
		addRegister("EA",8);
		
		addRegister("OP1",16);
		addRegister("OP2",16);
		addRegister("RES",16);
	}
	    /*
	     * addRegister: store name & size into registers, rank the register name into registerOrder 
	     */
	private void addRegister(String name,int size){
		registers.put(name, new register(name,size));
		registersOrder.add(name);
	}
	
	
	/**
	 * run until finished or error
	 */
	public void execute(){
	
		try {
			while(true){
				log.console("--------------------START");
				executeNextInstruction();
				log.console("--------------------END"); }}
			

			catch (Exception e) {
		}
		
	}
	
	/*
	 * run single step when click  run
	 */
	public void executeSingleStep(){
		try {
			log.console("--------------------START");
			executeNextInstruction();
			log.console("--------------------END");}

			catch (Exception e) {
		}
	}
	
	private void executeNextInstruction(){
		try {
			/*
			 * execute from begin steps,value from register to register, value from memory to register,
			 * value from register to register, value decode.
			 * 
			 */
			Instruction_step beginning = Instruction_step.getCommonBeginning();
			
			for(Instruction process:beginning.getprocessing())
			{
				execute(process);
			}
			
			/*
			 * execute the different processs for another instruction, by identify the OPCODE, decide execute in whice instruction  
			 * (after last steps, we can know which instruction is executing)
			 */
			Instruction_step content = InstructionSet.getprocesses(getRegisterByRealName("OPCODE").intValue());
			
			for(Instruction process:content.getprocessing()){
				execute(process);
			}
			
		  } catch (Exception e) {
			
			
		}

}
		
	/*
	 * If not single step, will execute this common steps
	 * 
	 */
	private void execute(Instruction process){ 
		try{
		/*
		 * used to save log information
		 */
		StringBuffer msg = new StringBuffer();
		
		register dest,source;
		
		/*
		 *	recognize different TYPE to execute different work  
		 */
		switch(process.type){
		
		/*
		 * get value from register to another register, process use ArrayList<Instruction> to manage data order
		 */
		
		case(Instruction.register_to_register):
			dest = getRegister(process.args[0]);
			source = getRegister(process.args[1]);
			dest.write(source.read());
			msg.append(dest.name).append(" = (").append(source.name).append(")");
			break;
			
			/*
			 * get value from register to memory, the destination and source position judge by the Instruction
			 */
			
		case(Instruction.register_to_memory):
			//TODO
			dest = getRegister(process.args[1]);
			source = getRegister(process.args[0]);
			memory.writeWord(dest.intValue(), source.read());
			msg.append("MEM(").append(dest.name).append(") = ").append(source.name);
			break;
			
			/*
			 * get value from memory to register,the destination and source position judge by the Instruction
			 */
			
		case(Instruction.memory_to_register):
			source = getRegister(process.args[1]);
			dest = getRegister(process.args[0]);
			dest.write(memory.readWord(source.intValue()));
			msg.append(dest.name).append(" = MEM(").append(source.name).append(")");
			break;
			
			/*
			 * decomposing the enter value, from string to binary
			 */
			
		case(Instruction.getcode):
			doCpuDecode(msg);
			break;
		    /*
		     * calculate the effective address of the simulator C(x/r)+ address
		     */
		
		case(Instruction.effecative_address):
			int ix = getRegisterByRealName("IX").intValue();
			//get address from ADDR
			int address = getRegisterByRealName("ADDR").intValue();
			//add address by Xn if n(IX)>0
			if(ix != 0){
				address += getRegisterByRealName("R"+ix).intValue();
			}
			//get value of I-register
			int i = getRegisterByRealName("I").intValue();
			//change address to the value of memory at $address$ if i==1
			if(i==1)
				address = computering.getIntValueFromBits(memory.readWord(address));
			//save address into EA-register
			getRegisterByRealName("EA").setValueByInt(address);
			
			msg.append("EA = (ADDR,IX,I)");
			break;
         
		 
		case(Instruction.memory_bufer_register):
			
			break;
          /*
           *The calculation system in the cpu, it could add/sub operation.  
           *It  recognizes with the OPcode what type from register buffer reader
           */ 
		case(Instruction.ALU):
   			int op1 = getRegisterByRealName("OP1").intValue();
   			int op2 = getRegisterByRealName("OP2").intValue();
   			int opcode = getRegisterByRealName("OPCODE").intValue();
   			int value;
   			
   			msg.append("RES = OP1 ");
   			switch(opcode){
   			case(4):
   			case(6):
   				value = op1+op2;
   				msg.append("+");
   			break;
   			case(5):
   			case(7):
   				value = op1-op2;
   				msg.append("-");
   			break;
   			default:
   				value = 0;
   			}
   			msg.append(" OP2");
   			getRegisterByRealName("RES").setValueByInt(value);
   			
   			
   			
   			break;

			/*
			 *  this defines the operation action, from where to which destination
			 */
		case(Instruction.OP1andOP2):
			dest = getRegister(process.args[0]);
			source = getRegister(process.args[1]);
			getRegisterByRealName("OP1").write(dest.read());
			getRegisterByRealName("OP2").write(source.read());
			msg.append("OP1 = (").append(dest.name)
				.append("), OP2 = (").append(source.name).append(")");
			break;
			
			/*
			 * PC calculation add 1,when by finish one order
			 */
			
		case(Instruction.PC_plus):
			dest = getRegisterByRealName("PC");
			dest.addValueByInt(1);
			msg.append("PC = (PC) + 1");
			break;
			
			
		default:
			
			break;
			
			//::
		}
		log.console(msg.toString());
		//log.console("one process["+process.type+"] finished...");
		
		}catch (Exception e) {
			
		}
	}
	
	/*
	 *  
	 * get the condition code from panel (text)
	 * judge the source.subValue that know this oder is which kind operation
	 * massage record add the operate information by useing append
	 */
	private void doCpuDecode(StringBuffer msg){ 
        try{
        	register source = getRegister("IR");
		getRegisterByRealName("OPCODE").write(source.subValue(0, 6));
		getRegisterByRealName("IX").write(source.subValue(6, 2));
		getRegisterByRealName("R").write(source.subValue(8, 2));
		getRegisterByRealName("I").write(source.subValue(10, 1));
		getRegisterByRealName("ADDR").write(source.subValue(11, 5));
		msg.append("OPCODE,IX,R,I,ADDR = (").append(source.name).append(")");
	}catch (Exception e) {
			
	}
	}
	/*
	 * this method return the register by specific name
	 * if name="R", the R(n) will be returned (n is decided by R-register's value)
	 * if name="IX", the IX(n) will be returned (n is decided by IX-register's value)
	 * 
	 * R-register and IX-register can be got by 'getRegisterByRealName(name)'
	 * return Register of $registerRealName$
	 */
	private register getRegister(String name){
		//find the real name for register
		try{
		String registerRealName;
		if("R".equals(name)){
			int rVal = registers.get(name).intValue();
			registerRealName = ("R"+rVal);
		}
		else if("IX".equals(name)){
			int iVal = registers.get(name).intValue();
			registerRealName = ("X"+iVal);
		}
		else{
			registerRealName = name;
		}
		return registers.get(registerRealName);
		//return getRegisterByRealName(registerRealName);
		
	}
		catch (Exception e) {
			//e.printStackTrace();
			
		}
		return null;
	}
	
	/*
	 * @author YouZHao YuMa
	 * registerRealName
	 *  Register of $registerRealName
	 * By identify the key value in the java.util.map, could find the required RealName
	 */
	private register getRegisterByRealName(String registerRealName){
		try{
		if(registers.keySet().contains(registerRealName))
			return registers.get(registerRealName);
		
	}catch (Exception e) {
	}
		return null;
	}

	/*
	 * get the map of register
	 * @return
	 */
	public Map<String,register> getRegisters() {
		return registers;
	}

	/*
	 * get the amount of registers
	 * @return
	 */
	public int getRegisterSize(){
		return registers.size();
	}
	
	/*
	 * get the name list (keep the order in which registers are added)
	 * @return
	 */
	public List<String> getRegisterNames(){
		return this.registersOrder;
	}
	
	
	
}