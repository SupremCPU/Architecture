package edu.gwu.computerarchitecture.operation.execute;

import edu.gwu.computerarchitecture.alu.ALU;
import edu.gwu.computerarchitecture.cpu.CPUUnits;
import edu.gwu.computerarchitecture.entity.Register;
import edu.gwu.computerarchitecture.entity.WordType;
import edu.gwu.computerarchitecture.operation.Instruction;

/**
 * 
 * implement the execution of JMP instruction
 *
 * <p>detailed comment
 * @author Erzheng Dai 2013年10月14日
 * @see Execute
 * @since 1.0
 * @version 1.0
 */
public class ExecuteJMP extends Execute
{
    public ExecuteJMP(CPUUnits units, Instruction instr)
    {
        super(units, instr);
        // TODO Auto-generated constructor stub
    }
    /**
     * 
     * implement the JMP
     * 
     * @see edu.gwu.computerarchitecture.operation.execute.Execute#execute()
     */
    public void execute(){   
    	calculateEA();
        cpuunits.pc.setRegister(cpuunits.mar.getRegister());
    }
}