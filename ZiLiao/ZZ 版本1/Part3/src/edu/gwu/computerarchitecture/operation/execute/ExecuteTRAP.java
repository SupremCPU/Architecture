package edu.gwu.computerarchitecture.operation.execute;

import edu.gwu.computerarchitecture.cpu.CPUUnits;
import edu.gwu.computerarchitecture.entity.Register;
import edu.gwu.computerarchitecture.entity.WordType;
import edu.gwu.computerarchitecture.operation.Instruction;

/**
 * 
 * TRAP Execution
 *
 * <p>detailed comment
 * @author Bin Zhang 2013年11月5日
 * @see
 * @since 3.0
 * @version 1.0
 */
public class ExecuteTRAP extends Execute
{

    public ExecuteTRAP(CPUUnits units, Instruction instr)
    {
        super(units, instr);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void execute()
    {
        // TODO Auto-generated method stub
        int tableAddress = cpuunits.memory.getMemory(0);
        Register r = rfRegister(instruction.rsr1);
        int index = r.getRegister().getWord();
        if(index >= 0 && index < 16){
            cpuunits.r3.setRegister(cpuunits.pc.getRegister());
            cpuunits.pc.setRegister(new WordType(tableAddress + index));
        } else {    //Machine fault occurs.
            cpuunits.mfr.setRegister(1);            
            //cpuunits.pc.setRegister(new WordType(1));
        }
    }

}
