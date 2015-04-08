package edu.gwu.computerarchitecture.operation.execute;

import edu.gwu.computerarchitecture.cpu.CPUUnits;
import edu.gwu.computerarchitecture.entity.IDevice;
import edu.gwu.computerarchitecture.entity.Register;
import edu.gwu.computerarchitecture.entity.WordType;
import edu.gwu.computerarchitecture.operation.Instruction;

/**
 * 
 * implement the IN execution
 *
 * <p>detailed comment
 * @author Bin Zhang 2013年11月5日
 * @see
 * @since 3.0
 * @version 1.0
 */
public class ExecuteIN extends Execute
{

    public ExecuteIN(CPUUnits units, Instruction instr)
    {
        super(units, instr);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void execute()
    {
        // TODO Auto-generated method stub
        System.out.println("***Enter ExecuteIN***");
        Register r = rfRegister(instruction.rsr1);
        IDevice device = cpuunits.device.getDevice(instruction.ioDevID);
        
        
        if (device != null){
            System.out.println("Device not null");
            Character c = device.ReadChar();
            
            while(c == null){
                System.out.println("IN WAITING FOR INPUTING");
                
                try
                {
                    java.util.concurrent.TimeUnit.MILLISECONDS.sleep(100);
                }
                catch (InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
                c = device.ReadChar();
            }
            
            r.setRegister(new WordType((int)c));
        } else{
            // TODO: handle the device ID error
            System.out.println("Device null");
        }
    }

}
