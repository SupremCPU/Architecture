package edu.gwu.computerarchitecture.operation.execute;

import edu.gwu.computerarchitecture.cpu.CPUUnits;
import edu.gwu.computerarchitecture.entity.Register;
import edu.gwu.computerarchitecture.entity.WordType;
import edu.gwu.computerarchitecture.operation.Instruction;
/**
 * 
 * simple introduction
 *
 * <p>detailed comment
 * @author Bin Zhang 2013年11月4日
 * @see
 * @since 1.0
 */
public class ExecuteJGE extends Execute
{

    public ExecuteJGE(CPUUnits units, Instruction instr)
    {
        super(units, instr);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void execute()
    {
        // TODO Auto-generated method stub
        Register r = rfRegister(instruction.rsr1);
        if (r.getRegister().getWord() >= 0){
            calculateEA();
            cpuunits.pc.setRegister(cpuunits.mar.getRegister());
        } else {
            cpuunits.pc.setRegister(new WordType(cpuunits.pc.getRegister().getWord() + 1));
        }
    }

}
