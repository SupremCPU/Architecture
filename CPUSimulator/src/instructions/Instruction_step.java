package instructions;

import java.util.ArrayList;
import java.util.List;

public class Instruction_step {
	private List<Instruction> processing;
	
	private static Instruction_step _start;
	
	  public Instruction_step()
	  {
		
		  processing = new ArrayList<Instruction>();
		
	  }
	
	  // CPU execute NEXTInstruction one step after one step
	
	public void addCircle(Instruction circle){
		processing.add(circle);
	}
	  //when one processing finished, The PC+1
	public void addCommonEndingprocessing(){
		processing.add(new Instruction(Instruction.PC_plus));
	}
	  // Instructions are consist as a array list
	public List<Instruction> getprocessing(){
		return this.processing;
	}
	   /*
	    * in the beginning, the system starts as order
	    * this processing records in the log
	    */
	public static Instruction_step getCommonBeginning(){
		if(_start==null){
			_start = new Instruction_step();
			//insert common processing at beginning
			_start.addCircle(new Instruction(Instruction.register_to_register,"MAR","PC"));
			_start.addCircle(new Instruction(Instruction.memory_to_register,"MBR","MAR"));
			_start.addCircle(new Instruction(Instruction.register_to_register,"IR","MBR"));
			_start.addCircle(new Instruction(Instruction.getcode));
			
		}
		return _start;
	}
	
}
