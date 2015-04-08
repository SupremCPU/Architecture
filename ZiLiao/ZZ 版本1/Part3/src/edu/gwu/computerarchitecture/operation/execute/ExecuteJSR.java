package edu.gwu.computerarchitecture.operation.execute;

import edu.gwu.computerarchitecture.cpu.CPUUnits;
import edu.gwu.computerarchitecture.entity.Register;
import edu.gwu.computerarchitecture.entity.WordType;
import edu.gwu.computerarchitecture.operation.Instruction;

/**
 * 
 * implement the JSR instruction's execution
 *
 * <p>detailed comment
 * @author Bin Zhang 2013年10月15日
 * @see Execute
 * @since 2.0
 * @version 1.0
 */
public class ExecuteJSR extends Execute
{

    public ExecuteJSR(CPUUnits units, Instruction instr)
    {
        super(units, instr);
        // TODO Auto-generated constructor stub
    }

    /**
     * implment the JSR execute
     * 
     * @see edu.gwu.computerarchitecture.operation.execute.Execute#execute()
     */
    @Override
    public void execute()
    {
        // TODO Auto-generated method stub
        Register r3 = rfRegister(3);
        r3.setRegister(new WordType(cpuunits.pc.getRegister().getWord() + 1));
        calculateEA();
        cpuunits.pc.setRegister(cpuunits.mar.getRegister());
        
    }

}
