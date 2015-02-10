package edu.gwu.computerarchitecture.cpu;

import edu.gwu.computerarchitecture.entity.Memory;
import edu.gwu.computerarchitecture.entity.Register;

public class CPUUnits
{
	public static Register pc;
	public static Register ir;	
    public static Register mar;
    public static Register mdr;
    public static Register x0;
    public static Memory memory;
    public static Register r0;
    public static Register r1;
    public static Register r2;
    public static Register r3;
    public static void initializeUnits(){
    	pc = new Register();
    	ir = new Register();	
        mar = new Register();
        mdr = new Register();
        x0 = new Register();
        memory = new Memory();
        r0 = new Register();
        r1 = new Register();
        r2 = new Register();
        r3 = new Register();
    }
}
