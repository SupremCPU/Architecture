package edu.gwu.computerarchitecture.operation.execute;

import edu.gwu.computerarchitecture.alu.ALU;
import edu.gwu.computerarchitecture.cpu.CPUUnits;
import edu.gwu.computerarchitecture.entity.Register;
import edu.gwu.computerarchitecture.entity.WordType;
import edu.gwu.computerarchitecture.operation.Instruction;

/**
 * 
 * implement the execution of AIR instruction
 *
 * <p>detailed comment
 * @author Erzheng Dai 2013年10月11日
 * @see Execute
 * @since 1.0
 * @version 1.0
 */
public class ExecuteAIR extends Execute
{
    public ExecuteAIR(CPUUnits units, Instruction instr)
    {
        super(units, instr);
        // TODO Auto-generated constructor stub
    }
    /**
     * 
     * implement the AIR
     * 
     * @see edu.gwu.computerarchitecture.operation.execute.Execute#execute()
     */
    public void execute(){        
        Register rsr1 = rfRegister(instruction.rsr1);
        alu = new ALU(rsr1.getRegister(), new WordType(instruction.address), cpuunits);
        alu.add();
        instruction.dataToRegister = alu.getResult1_WordType().getWord();
    }
}