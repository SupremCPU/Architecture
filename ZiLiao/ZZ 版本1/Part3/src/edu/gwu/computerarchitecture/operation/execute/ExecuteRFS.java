package edu.gwu.computerarchitecture.operation.execute;

import edu.gwu.computerarchitecture.cpu.CPUUnits;
import edu.gwu.computerarchitecture.entity.WordType;
import edu.gwu.computerarchitecture.operation.Instruction;

/**
 * 
 * The RFS instruction's implementation
 *
 * <p>detailed comment
 * @author Bin Zhang 2013年10月15日
 * @see
 * @since 1.0
 */
public class ExecuteRFS extends Execute
{

    public ExecuteRFS(CPUUnits units, Instruction instr)
    {
        super(units, instr);
        // TODO Auto-generated constructor stub
    }

    /**
     * 
     * @see edu.gwu.computerarchitecture.operation.execute.Execute#execute()
     */
    @Override
    public void execute()
    {
        // TODO Auto-generated method stub
        cpuunits.r0.setRegister(new WordType(instruction.address));
        cpuunits.pc.setRegister(cpuunits.r3.getRegister());
    }

}
