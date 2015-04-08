package edu.gwu.computerarchitecture.operation;

import edu.gwu.computerarchitecture.cpu.CPUUnits;
import edu.gwu.computerarchitecture.entity.Register;
import edu.gwu.computerarchitecture.entity.WordType;

/**
 * 
 * WriteBack is the stage of writeback operation
 *
 * <p>detailed comment
 * @author Bin Zhang 2013年10月11日
 * @see
 * @since 2.0
 * @version 1.2
 */
public class WriteBack
{
    /*
     * cpuunits is the CPU's entities like register, memory.
     */
    public CPUUnits cpuunits;
    /*
     * instruction is the decoded instruction.
     */
    public Instruction instruction;
    /*
     * data is the data write in the register
     */
    public WordType data;
    /*
     * data is the 16bits low data of 32bits data for MUL, DIV 
     */
    public WordType dataLow;
    
    /**
     * 
     * @param units
     * @param instr
     */
    public WriteBack(CPUUnits units, Instruction instr){
        cpuunits = units;
        instruction = instr;
    }
    
    /**
     * implement the Write Back stage that write data to Register
     */
    public void Execute(){
        Register rf = rfRegister(instruction.rsr1);
        /*
        if(data == null){
            return ;
        }
        */
        /*
         * test
         */
        System.out.println("Enter WriteBack: data:" + instruction.dataToRegister);
        if(instruction.opcode == OpCode.LDX){
            rf = cpuunits.x0;
        }
        if(instruction.opcode == OpCode.MUL || instruction.opcode == OpCode.DIV){
            Register rfLow = rfRegister(instruction.rsr1+1);
            rf.setRegister(new WordType(instruction.dataToRegister));
            rfLow.setRegister(new WordType(instruction.dataToRegisterLow));
        }
        /*
        if(instruction.opcode == OpCode.LDR){
            rf.setRegister(cpuunits.mdr.getRegister());
            return ;
        }
        */
        rf.setRegister(new WordType(instruction.dataToRegister));   
    }
    
    /**
     * 
     * used to return the Register the rf point to 
     * @param rf
     * @return Register
     */
    public Register rfRegister(int rf){
        switch (rf){
            case 0:
                return cpuunits.r0;
            case 1:
                return cpuunits.r1;
            case 2:
                return cpuunits.r2;
            case 3:
                return cpuunits.r3;
            default:
                return null;
        }
    }
}
