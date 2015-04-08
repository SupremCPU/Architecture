package edu.gwu.computerarchitecture.operation.execute;

import edu.gwu.computerarchitecture.alu.ALU;
import edu.gwu.computerarchitecture.cpu.CPUUnits;
import edu.gwu.computerarchitecture.entity.Register;
import edu.gwu.computerarchitecture.entity.WordType;
import edu.gwu.computerarchitecture.operation.Instruction;

/**
 * 
 * implement the execution of JNE instruction
 *
 * <p>detailed comment
 * @author Erzheng Dai 2013年10月14日
 * @see Execute
 * @since 1.0
 * @version 1.0
 */
public class ExecuteJNE extends Execute
{
    public ExecuteJNE(CPUUnits units, Instruction instr)
    {
        super(units, instr);
        // TODO Auto-generated constructor stub
    }
    /**
     * 
     * implement the JNE
     * 
     * @see edu.gwu.computerarchitecture.operation.execute.Execute#execute()
     */
    public void execute(){ 
    	calculateEA();
        Register rsr1 = rfRegister(instruction.rsr1);
        if(rsr1.getRegister().getWord() == 0){
        	cpuunits.pc.setRegister(new WordType(cpuunits.pc.getRegister().getWord()+1));        	
        }
        else{
        	cpuunits.pc.setRegister(cpuunits.mar.getRegister());
        }
    }
}