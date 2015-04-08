package edu.gwu.computerarchitecture.operation.execute;

import edu.gwu.computerarchitecture.alu.ALU;
import edu.gwu.computerarchitecture.cpu.CPUUnits;
import edu.gwu.computerarchitecture.entity.Register;
import edu.gwu.computerarchitecture.entity.WordType;
import edu.gwu.computerarchitecture.operation.Instruction;

/**
 * 
 * simple introduction
 *
 * <p>detailed comment
 * @author Bin Zhang 2013年9月17日
 * @see Instruction, InstructionType
 * @since 1.0
 * @version 1.1
 */
public abstract class Execute
{
    /*
     * cpuunits is the CPU's entities like register, memory.
     */
    public CPUUnits cpuunits;
    /*
     * alu is for arithmetic operation and logical calculus.
     */
    public ALU alu;
    /*
     * instruction is the decoded instruction.
     */
    public Instruction instruction;
    /*
     * dataToRegister store the result written to the register
     */
    //public WordType dataToRegister;
    /*
     * dataToRegisterLow is the 16bits low data write to the register
     */
    //public WordType dataToRegisterLow;
    
    /**
     * 
     * @param units
     * @param instr
     */
    public Execute(CPUUnits units, Instruction instr){
        cpuunits = units;
        instruction = instr;
        //dataToRegister = new WordType();
        //dataToRegisterLow = new WordType();
    }
    /**
     * execute is a abstract method.
     */
    public abstract void execute();
        
    /**
     * used to calculate the efficient address, put the address in mar, put the data in mdr
     */
    public void calculateEA(){
        int ea; //effective address
        
        ea = instruction.address;
        if(instruction.ixTag == 1){
            ea = ea + cpuunits.x0.getRegister().getWord();
        }
        cpuunits.mar.setRegister(new WordType(ea));
        cpuunits.mdr.setRegister(new WordType(cpuunits.memory.getMemory(cpuunits.mar.getRegister().getWord())));
        if(instruction.iTag == 1){
            cpuunits.mar.setRegister(cpuunits.mdr.getRegister());
            cpuunits.mdr.setRegister(new WordType(cpuunits.memory.getMemory(cpuunits.mar.getRegister().getWord())));
        }
    }
    
    /**
     * 
     * used to return the Register the rf point to 
     * @param rf
     * @return
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
