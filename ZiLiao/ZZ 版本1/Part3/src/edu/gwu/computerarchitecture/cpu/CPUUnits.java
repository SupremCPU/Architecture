package edu.gwu.computerarchitecture.cpu;

import edu.gwu.computerarchitecture.entity.Memory;
import edu.gwu.computerarchitecture.entity.Register;
import edu.gwu.computerarchitecture.entity.Register4Bits;

public class CPUUnits {
	public Register4Bits cc;
	public Register4Bits mfr;
	public Register pc;
	public Register ir;
	public Register r0, r1, r2, r3;
	public Register mar, mdr, msr;
	public Register x0;
	public Memory memory;
	public int mode;
	public Device device;

	public CPUUnits() {
		cc = new Register4Bits();
		mfr = new Register4Bits();
		pc = new Register();
		ir = new Register();
		r0 = new Register();
		r1 = new Register();
		r2 = new Register();
		r3 = new Register();
		mar = new Register();
		mdr = new Register();
		msr = new Register();
		x0 = new Register();
		memory = new Memory(this.mdr,this.mar);
		device = new Device();
	}
	
}
