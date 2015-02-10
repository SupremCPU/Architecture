package edu.gwu.computerarchitecture.operation;

import edu.gwu.computerarchitecture.entity.Register;
import edu.gwu.computerarchitecture.entity.WordType;
import edu.gwu.computerarchitecture.cpu.*;
/**
 * 
 * The Fetch class is used for getting the instruction.
 *
 * <p>detailed comment
 * @author 
 * @see 
 * @since 1.0
 * @version 1.0
 */
public class Fetch
{	
	public Fetch()
	{
		
	}
	public static void pcToMar()
	{
		CPUUnits.mar = CPUUnits.pc;
	}
	public static void fetchMarToMdr()
	{
		CPUUnits.mdr.setRegister(new WordType(CPUUnits.memory.getMemory(CPUUnits.mar.getRegister().getWord())));
	}
	public static void mdrToIr()
	{
		CPUUnits.ir.setRegister(CPUUnits.mdr.getRegister());
	}
}