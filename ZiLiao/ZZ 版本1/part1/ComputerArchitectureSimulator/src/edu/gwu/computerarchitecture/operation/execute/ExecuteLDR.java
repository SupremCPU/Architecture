package edu.gwu.computerarchitecture.operation.execute;

import edu.gwu.computerarchitecture.cpu.CPUUnits;
import edu.gwu.computerarchitecture.entity.WordType;
import edu.gwu.computerarchitecture.operation.Instruction;

/**
 * 
 * impement the execution of LDR instruction
 *
 * <p>detailed comment
 * @author Erzheng Dai
 * @see Execute
 * @since 1.0
 * @version 1.0
 */
public class ExecuteLDR extends Execute
{
    public ExecuteLDR(Instruction instr)
    {
        super(instr);
        // TODO Auto-generated constructor stub
    }
    /**
     * 
     * implement the LDR
     * 
     * @see edu.gwu.computerarchitecture.operation.execute.Execute#execute()
     */
    public void execute(){
        int ea;
        ea = EA();
        int data = CPUUnits.memory.getMemory(ea);
        WordType reg_data = new WordType(data);
        switch(this.instruction.rsr1)
        {
        case 0:
        	CPUUnits.r0.setRegister(reg_data);
        	break;
        case 1:
        	CPUUnits.r1.setRegister(reg_data);
        	break;
        case 2:
        	CPUUnits.r2.setRegister(reg_data);
        	break;
        case 3:
        	CPUUnits.r3.setRegister(reg_data);
        	break;
        default:
        	break;
        }
    }
}
