package edu.gwu.computerarchitecture.operation.execute;

import edu.gwu.computerarchitecture.cpu.CPUUnits;
import edu.gwu.computerarchitecture.entity.IDevice;
import edu.gwu.computerarchitecture.entity.Register;
import edu.gwu.computerarchitecture.entity.WordType;
import edu.gwu.computerarchitecture.operation.Instruction;

/**
 * 
 * Check Execution
 *
 * <p>detailed comment
 * @author Bin Zhang 2013年11月5日
 * @see
 * @since 3.0
 * @version 1.0
 */
public class ExecuteCHK extends Execute
{

    public ExecuteCHK(CPUUnits units, Instruction instr)
    {
        super(units, instr);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void execute()
    {
        // TODO Auto-generated method stub
        Register r = rfRegister(instruction.rsr1);
        IDevice device = cpuunits.device.getDevice(instruction.ioDevID);
        if (device != null){
            r.setRegister(new WordType(device.GetStatus()));
        } else{
            // TODO: handle the device ID error
        }
    }

}
