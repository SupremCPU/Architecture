package edu.gwu.computerarchitecture.operation.execute;

import edu.gwu.computerarchitecture.cpu.CPUUnits;
import edu.gwu.computerarchitecture.operation.Instruction;

/**
 * 
 * implement the execution of STX instruction
 *
 * <p>detailed comment
 * @author Erzheng Dai 2013年10月11日
 * @see Execute
 * @since 1.0
 * @version 1.0
 */
public class ExecuteSTX extends Execute
{
    public ExecuteSTX(CPUUnits units, Instruction instr)
    {
        super(units, instr);
        // TODO Auto-generated constructor stub
    }
    /**
     * 
     * implement the STX
     * 
     * @see edu.gwu.computerarchitecture.operation.execute.Execute#execute()
     */
    public void execute(){
        calculateEA();   
        cpuunits.mdr.setRegister(cpuunits.x0.getRegister());
    }
}