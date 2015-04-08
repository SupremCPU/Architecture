package edu.gwu.computerarchitecture.operation.execute;

import edu.gwu.computerarchitecture.alu.ALU;
import edu.gwu.computerarchitecture.cpu.CPUUnits;
import edu.gwu.computerarchitecture.entity.Register;
import edu.gwu.computerarchitecture.entity.WordType;
import edu.gwu.computerarchitecture.operation.Instruction;

/**
 * 
 * implement the execution of JCC instruction
 *
 * <p>detailed comment
 * @author Erzheng Dai 2013年10月14日
 * @see Execute
 * @since 1.0
 * @version 1.0
 */
public class ExecuteJCC extends Execute
{
    public ExecuteJCC(CPUUnits units, Instruction instr)
    {
        super(units, instr);
        // TODO Auto-generated constructor stub
    }
    /**
     * 
     * implement the JCC
     * 
     * @see edu.gwu.computerarchitecture.operation.execute.Execute#execute()
     */
    public void execute(){  
    	calculateEA();
        if(((0x0001<<instruction.rsr1)&(cpuunits.cc.getRegister())) != 0){
        	cpuunits.pc.setRegister(cpuunits.mar.getRegister());
        }
        else{
        	cpuunits.pc.setRegister(new WordType(cpuunits.pc.getRegister().getWord()+1));
        }
    }
}