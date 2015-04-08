package edu.gwu.computerarchitecture.operation;

import edu.gwu.computerarchitecture.cpu.CPUUnits;

/**
 * 
 * Memorystage is the stage of memory operation
 *
 * <p>detailed comment
 * @author Bin Zhang 2013年10月11日
 * @see
 * @since 2.0
 * @version 1.0
 */
public class MemoryStage
{
    /*
     * cpuunits is the CPU's entities like register, memory.
     */
    public CPUUnits cpuunits;
    
    public MemoryStage(CPUUnits units){
        cpuunits = units;
    }
    
    /**
     *  implement the Memory Stage that write the data to memory
     */
    public void Execute(){
        cpuunits.memory.StoreMemory();
    }
}
