package edu.gwu.computerarchitecture.operation.execute;

import edu.gwu.computerarchitecture.cpu.CPUUnits;
import edu.gwu.computerarchitecture.operation.Instruction;

/**
 * 
 * implement the execution of LDA instruction
 *
 * <p>detailed comment
 * @author Erzheng Dai 2013年10月11日
 * @see Execute
 * @since 1.0
 * @version 1.0
 */
public class ExecuteLDA extends Execute
{
    public ExecuteLDA(CPUUnits units, Instruction instr)
    {
        super(units, instr);
        // TODO Auto-generated constructor stub
    }
    /**
     * 
     * implement the LDA
     * 
     * @see edu.gwu.computerarchitecture.operation.execute.Execute#execute()
     */
    public void execute(){
        calculateEA();
        instruction.dataToRegister = cpuunits.mar.getRegister().getWord();
    }
}