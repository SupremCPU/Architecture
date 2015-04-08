package edu.gwu.computerarchitecture.operation.execute;

import edu.gwu.computerarchitecture.cpu.CPUUnits;
import edu.gwu.computerarchitecture.operation.Instruction;

/**
 * 
 * impement the execution of LDR instruction
 *
 * <p>detailed comment
 * @author Bin Zhang 2013年9月18日
 * @see Execute
 * @since 1.0
 * @version 1.0
 */
public class ExecuteLDR extends Execute
{
    public ExecuteLDR(CPUUnits units, Instruction instr)
    {
        super(units, instr);
        // TODO Auto-generated constructor stub
    }
    /**
     * 
     * implement the LDR
     * 
     * @see edu.gwu.computerarchitecture.operation.execute.Execute#execute()
     */
    public void execute(){
        calculateEA();
        //super.dataToRegister = cpuunits.mdr.getRegister();
        super.instruction.dataToRegister = cpuunits.mdr.getRegister().getWord();
        /*
         * test
         */
        System.out.println("Enter ExecuteLDR: data" + super.instruction.dataToRegister);
      //  Register rsr1 = rfRegister(instruction.rsr1);
                   
      //  rsr1.setRegister(cpuunits.mdr.getRegister());
    }
}
