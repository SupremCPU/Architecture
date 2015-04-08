package edu.gwu.computerarchitecture.operation.execute;

import edu.gwu.computerarchitecture.cpu.CPUUnits;
import edu.gwu.computerarchitecture.entity.Register;
import edu.gwu.computerarchitecture.operation.Instruction;

/**
 * 
 * TER instruction's implementation
 *
 * <p>detailed comment
 * @author Bin Zhang 2013年10月15日
 * @see
 * @since 1.0
 */
public class ExecuteTER extends Execute
{

    public ExecuteTER(CPUUnits units, Instruction instr)
    {
        super(units, instr);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void execute()
    {
        // TODO Auto-generated method stub
        Register rx = rfRegister(instruction.rsr1);
        Register ry = rfRegister(instruction.rsr2);
        
        if (rx.getRegister().getWord() == ry.getRegister().getWord()){
            cpuunits.cc.setRegister(1);
        } else {
            cpuunits.cc.setRegister(0);
        }
    }

}
