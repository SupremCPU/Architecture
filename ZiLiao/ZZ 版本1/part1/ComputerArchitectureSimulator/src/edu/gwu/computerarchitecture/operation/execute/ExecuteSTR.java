package edu.gwu.computerarchitecture.operation.execute;

import edu.gwu.computerarchitecture.cpu.CPUUnits;
import edu.gwu.computerarchitecture.entity.WordType;
import edu.gwu.computerarchitecture.operation.Instruction;

/**
 * 
 * impement the execution of STR instruction
 *
 * <p>detailed comment
 * @author Erzheng Dai 
 * @see Execute
 * @since 1.0
 * @version 1.0
 */
public class ExecuteSTR extends Execute
{
    public ExecuteSTR(Instruction instr)
    {
        super(instr);
        // TODO Auto-generated constructor stub
    }
    /**
     * 
     * implement the STR
     * 
     * @see edu.gwu.computerarchitecture.operation.execute.Execute#execute()
     */
    public void execute(){
        int ea;
        ea = EA();   
        WordType data = new WordType();
        switch(this.instruction.rsr1)
        {
        case 0:
        	data = CPUUnits.r0.getRegister();
        	break;
        case 1:
        	data = CPUUnits.r1.getRegister();
        	break;
        case 2:
        	data = CPUUnits.r2.getRegister();
        	break;
        case 3:
        	data = CPUUnits.r3.getRegister();
        	break;
        default:
        	break;
        }
        CPUUnits.mar.setRegister(new WordType(this.instruction.address));
        CPUUnits.mdr.setRegister(data);
        CPUUnits.memory.StoreMemory();
    }
}
