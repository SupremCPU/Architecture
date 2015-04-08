package edu.gwu.computerarchitecture.operation.execute;

import edu.gwu.computerarchitecture.alu.ALU;
import edu.gwu.computerarchitecture.cpu.CPUUnits;
import edu.gwu.computerarchitecture.entity.Register;
import edu.gwu.computerarchitecture.operation.Instruction;

/**
 * 
 * implement the execution of AND instruction
 *
 * <p>detailed comment
 * @author Erzheng Dai 2013年10月11日
 * @see Execute
 * @since 1.0
 * @version 1.0
 */
public class ExecuteAND extends Execute
{
    public ExecuteAND(CPUUnits units, Instruction instr)
    {
        super(units, instr);
        // TODO Auto-generated constructor stub
    }
    /**
     * 
     * implement the AND
     * 
     * @see edu.gwu.computerarchitecture.operation.execute.Execute#execute()
     */
    public void execute(){  
        Register rsr1 = rfRegister(instruction.rsr1);
        Register rsr2 = rfRegister(instruction.rsr2);
        alu = new ALU(rsr1.getRegister(), rsr2.getRegister(), cpuunits);
        alu.and();
        instruction.dataToRegister = alu.getResult1_WordType().getWord();
    }
}