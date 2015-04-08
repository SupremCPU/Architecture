package edu.gwu.computerarchitecture.operation.execute;

import edu.gwu.computerarchitecture.cpu.CPUUnits;
import edu.gwu.computerarchitecture.entity.Register;
import edu.gwu.computerarchitecture.operation.Instruction;

/**
 * 
 * implement the execution of STR instruction
 *
 * <p>detailed comment
 * @author Erzheng Dai 2013年10月08日
 * @see Execute
 * @since 1.0
 * @version 1.0
 */
public class ExecuteSTR extends Execute
{
    public ExecuteSTR(CPUUnits units, Instruction instr)
    {
        super(units, instr);
        // TODO Auto-generated constructor stub
    }
    /**
     * 
     * implement the STR
     * 
     * @see edu.gwu.computerarchitecture.operation.execute.Execute#execute()
     */
    public void execute(){
        calculateEA();
        Register rsr1 = rfRegister(instruction.rsr1);       
        cpuunits.mdr.setRegister(rsr1.getRegister());
    }
}