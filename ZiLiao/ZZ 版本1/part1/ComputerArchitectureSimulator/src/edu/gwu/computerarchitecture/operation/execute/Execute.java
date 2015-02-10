package edu.gwu.computerarchitecture.operation.execute;

import edu.gwu.computerarchitecture.cpu.CPUUnits;
import edu.gwu.computerarchitecture.operation.Instruction;

/**
 * 
 * simple introduction
 *
 * <p>detailed comment
 * @author Bin Zhang 2013年9月17日
 * @see Instruction, InstructionType
 * @since 1.0
 * @version 1.0
 */
public abstract class Execute
{
    /*
     * cpuunits is the CPU's entities like register, memory.
     */
   // public CPUUnits cpuunits;
    /*
     * instruction is the decoded instruction.
     */
    public Instruction instruction;
    
    /**
     * 
     * @param units
     * @param instr
     */
    public Execute(Instruction instr){
       // cpuunits = units;
        instruction = instr;
    }
    /**
     * execute is a abstract method.
     */
    public abstract void execute();
    
    public int EA(){
        int ea; //effective address
        
        if (instruction.iTag == 0) {
            if (instruction.ixTag ==0) {
                ea = instruction.address;
            } else {
                ea = CPUUnits.x0.getRegister().getWord() + instruction.address;
            }
        } else {
            if (instruction.ixTag == 0){
                ea = CPUUnits.memory.getMemory(instruction.address);
            } else {
                ea = CPUUnits.memory.getMemory(CPUUnits.x0.getRegister().getWord() + instruction.address);
            }
        }
        
        return ea;
    }
    
    
}
