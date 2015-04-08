package edu.gwu.computerarchitecture.alu;

import edu.gwu.computerarchitecture.cpu.CPUUnits;
import edu.gwu.computerarchitecture.entity.WordType;

/**
 * ALU of the simulator is used for arithmetic operation and logical calculus
 * 
 * @author Erzheng Dai
 * @since v1.0
 */
public class ALU {
	/*
	 * TX1 is a temporary register used to store operand
	 */
	private WordType TX1;
	/*
	 * TX2 is a temporary register used to store operand
	 */
	private WordType TX2;
	/*
	 * result1 is the output of ALU
	 */
	private short result1;
	/*
	 * result2 is the part output of some instructions, such as MUL, DIV
	 */
	private short result2;
	/*
	 * cpuunits is the CPU's registers & memory.
	 */
	public CPUUnits cpuunits;
	/*
	 * equal_flag is true when TX1 equals to TX2
	 */
	private final boolean equal_flag;
	/*
	 * overFlow is true when the result overflows
	 */
	private final boolean overFlow;
	/*
	 * divZero is true when TX1 is divided by zeros
	 */
	private final boolean divByZero;

	/*
	 * Constructor Initializes TX1, TX2, result
	 */
	public ALU(WordType tx1, WordType tx2, CPUUnits unites) {
		cpuunits = unites;
		TX1 = tx1;
		TX2 = tx2;
		result1 = 0;
		result2 = 0;
		divByZero = false;
		overFlow = false;
		equal_flag = false;
	}

	/*
	 * Set the value of TX1
	 */
	public void setTX1(WordType tx1) {
		TX1 = tx1;
	}

	/*
	 * Set the value of TX2
	 */
	public void setTX2(WordType tx2) {
		TX2 = tx2;
	}

	/*
	 * Set the value of overFlow
	 */
	public void setOverFlow(boolean overflow) {
		if (overflow == true) {
			cpuunits.cc.setRegister(cpuunits.cc.getRegister() | 0x0001);
		} else {
			cpuunits.cc.setRegister(cpuunits.cc.getRegister() & 0xfffffffe);
		}
	}

	/*
	 * Set the value of underFlow
	 */
	public void setUnderFlow(boolean underflow) {
		if (underflow == true) {
			cpuunits.cc.setRegister(cpuunits.cc.getRegister() | 0x0002);
		} else {
			cpuunits.cc.setRegister(cpuunits.cc.getRegister() & 0xfffffffd);
		}
	}

	/*
	 * Set the value of divByZero
	 */
	public void setDivByZero(boolean divbyzero) {
		if (divbyzero == true) {
			cpuunits.cc.setRegister(cpuunits.cc.getRegister() | 0x0004);
		} else {
			cpuunits.cc.setRegister(cpuunits.cc.getRegister() & 0xfffffffb);
		}
	}

	/*
	 * Set the value of equalornot
	 */
	public void setEqualOrNot(boolean equal) {
		if (equal == true) {
			cpuunits.cc.setRegister(cpuunits.cc.getRegister() | 0x0008);
		} else {
			cpuunits.cc.setRegister(cpuunits.cc.getRegister() & 0xfffffff7);
		}
	}

	/*
	 * Calculation of TX1 plus TX2
	 */
	public void add() {
		if ((TX1.getWord() + TX2.getWord()) > 32767) {
			setOverFlow(true);
		} else {
			setOverFlow(false);
		}
		result1 = (short) (TX1.getWord() + TX2.getWord());
		return;
	}

	/*
	 * Calculation of TX1 subtract TX2
	 */
	public void sub() {
		if ((TX1.getWord() - TX2.getWord()) < -32768) {
			setUnderFlow(true);
		} else {
			setUnderFlow(false);
		}
		result1 = (short) (TX1.getWord() - TX2.getWord());
		return;
	}

	/*
	 * Calculation of TX1 multiply TX2
	 */
	public void mul() {
		int i = TX1.getWord() * TX2.getWord();
		result1 = (short) (i & (0xffff));
		result2 = (short) (i >>> 16);
		return;
	}

	/*
	 * Calculation of divide TX1 by TX2
	 */
	public void div() {
		if (TX2.getWord() == 0) {
			setDivByZero(true);
		} else {
			setDivByZero(false);
			result1 = (short) (TX1.getWord() / TX2.getWord());
			result2 = (short) (TX1.getWord() % TX2.getWord());
		}
		return;
	}

	/*
	 * Check if TX1 equals to TX2
	 */
	public void equal() {
		if (TX1.getWord() == TX2.getWord()) {
			setEqualOrNot(true);
		} else {
			setEqualOrNot(false);
		}
		return;
	}

	/*
	 * Calculation of TX1 AND TX2
	 */
	public void and() {
		result1 = (short) (TX1.getWord() & TX2.getWord());
		return;
	}

	/*
	 * Calculation of TX1 OR TX2
	 */
	public void or() {
		result1 = (short) (TX1.getWord() | TX2.getWord());
		return;
	}

	/*
	 * Calculation of NOT TX1
	 */
	public void not() {
		result1 = (short) (~TX1.getWord());
		return;
	}

	/*
	 * Operation of shifting TX1
	 */
	public void shift(int digit, int LR, int AL) {
		switch (LR) {
		case 1:
			result1 = (short) (TX1.getWord() << digit);
			break;
		case 0:
			if (AL == 1) { // Logically shift
				result1 = (short) ((TX1.getWord() >>> digit) & (~(0x80000000 >> (15 + digit))));
			} else if (AL == 0) { // Arithmetically shift
				result1 = (short) (TX1.getWord() >> digit);
			}
			break;
		}
	}

	/*
	 * Operation of rotating TX1
	 */
	public void rotate(int digit, int LR, int AL) {
		int TX1_L = TX1.getWord() & 0x0000ffff;
		int TX1_A = TX1.getWord() & 0x00007fff;
		int sign_bit = TX1.getWord() & 0x00008000;
		switch (LR) {
		case 1:
			if (AL == 1) { // Logically rotate
				result1 = (short) (((TX1_L << digit | TX1_L >>> (32 - digit)) & 0x0000ffff) | (((TX1_L << digit | TX1_L >>> (32 - digit)) & 0xffff0000) >>> 16));
			} else if (AL == 0) {
				result1 = (short) (((TX1_A << (digit + 1) | TX1_A >>> (31 - digit)) & 0x00007fff)
						| (((TX1_A << (digit + 1) | TX1_A >>> (31 - digit)) & 0xffff8000) >>> 15) | sign_bit);
			}
			break;
		case 0:
			if (AL == 1) { // Arithmetically rotate
				result1 = (short) (((TX1_L << (32 - digit) | TX1_L >>> digit) & 0x0000ffff) | (((TX1_L << (32 - digit) | TX1_L >>> digit) & 0xffff0000) >>> 16));
			} else if (AL == 0) {
				result1 = (short) (((TX1_A << (31 - digit) | TX1_A >>> (digit + 1)) & 0x00007fff)
						| (((TX1_A << (31 - digit) | TX1_A >>> (digit + 1)) & 0xffff8000) >>> 15) | sign_bit);
			}
			break;
		}
	}

	/*
	 * Get the value of result1 as WordType format
	 */
	public WordType getResult1_WordType() {
		return new WordType(result1);
	}

	/*
	 * Get the value of result2 as WordType format
	 */
	public WordType getResult2_WordType() {
		return new WordType(result2);
	}

	/*
	 * Get the value of result1 as short format
	 */
	public short getResult1_short() {
		return result1;
	}

	/*
	 * Get the value of result2 as short format
	 */
	public short getResult2_short() {
		return result2;
	}

	/*
	 * Get the status of overflow
	 */
	public boolean getOverFlow() {
		return overFlow;
	}

	/*
	 * Get the status of divByZero
	 */
	public boolean getDivByZero() {
		return divByZero;
	}

	/*
	 * Get the status of equal_flag
	 */
	public boolean getEqual() {
		return equal_flag;
	}
}