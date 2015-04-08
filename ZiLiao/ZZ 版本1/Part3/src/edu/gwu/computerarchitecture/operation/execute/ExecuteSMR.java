package edu.gwu.computerarchitecture.operation.execute;

import edu.gwu.computerarchitecture.alu.ALU;
import edu.gwu.computerarchitecture.cpu.CPUUnits;
import edu.gwu.computerarchitecture.entity.Register;
import edu.gwu.computerarchitecture.operation.Instruction;

/**
 * 
 * implement the execution of SMR instruction
 *
 * <p>detailed comment
 * @author Erzheng Dai 2013年10月11日
 * @see Execute
 * @since 1.0
 * @version 1.0
 */
public class ExecuteSMR extends Execute
{
    public ExecuteSMR(CPUUnits units, Instruction instr)
    {
        super(units, instr);
        // TODO Auto-generated constructor stub
    }
    /**
     * 
     * implement the SMR
     * 
     * @see edu.gwu.computerarchitecture.operation.execute.Execute#execute()
     */
    public void execute(){
        calculateEA();   
        Register rsr1 = rfRegister(instruction.rsr1);
        alu = new ALU(rsr1.getRegister(), cpuunits.mdr.getRegister(), cpuunits);
        alu.sub();
        instruction.dataToRegister = alu.getResult1_short();
    }
}