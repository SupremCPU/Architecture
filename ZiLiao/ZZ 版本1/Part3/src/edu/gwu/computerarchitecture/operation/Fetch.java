package edu.gwu.computerarchitecture.operation;

import edu.gwu.computerarchitecture.entity.WordType;
import edu.gwu.computerarchitecture.cpu.*;
/**
 * 
 * The Fetch class is used for getting the instruction.
 *
 * <p>detailed comment
 * @author Erzheng dai
 * @see 
 * @since 1.0
 * @version 1.0
 */
public class Fetch
{	
/**
 * 
 *  fetch the instruction from memory while construct it
 * 
 * @param cpuunits
 * @since 1.0
 */
	public Fetch(CPUUnits cpuunits)
	{
		pcToMar(cpuunits);
		fetchMarToMdr(cpuunits);
		mdrToIr(cpuunits);
		
	}
/**
 * 
 *  pass the value of PC to MAR 
 * 
 * @param cpu
 * @since 1.0
 */
	public void pcToMar(CPUUnits cpu)
	{
		cpu.mar.setRegister(cpu.pc.getRegister());
	}
/**
 * 
 *  pass the value of M(MAR) to MDR 
 * 
 * @param cpu
 * @since 1.0
 */
	public void fetchMarToMdr(CPUUnits cpu)
	{
		cpu.mdr.setRegister(new WordType(cpu.memory.getMemory(cpu.mar.getRegister().getWord())));
	}
/**
 * 
 *  pass the value of MDR to IR 
 * 
 * @param cpu
 * @since 1.0
 */
	public void mdrToIr(CPUUnits cpu)
	{
		cpu.ir.setRegister(cpu.mdr.getRegister());
	}
}