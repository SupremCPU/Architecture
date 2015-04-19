package companent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import caculation.Util;
import Instructions.InstructionCircle;
import Instructions.InstructionCircles;
import Instructions.InstructionSet;
import basic_rules.Word;
import companent.Register;
import Instructions.InstructionInfo;
import Instructions.InstructionSetInfo;
import module.CPUOutputSignalBuffer;
import companent.CPU;
import companent.Memory;
import exception.IllegalDeviceStatusException;
import exception.IllegalInstructionCodeException;
import exception.IllegalMemoryAddressException;
import exception.InstructionExecuteException;
import exception.RegisterNotFoundException;
import companent.Log;
import companent.Cache;

public class CPU {

	/**
	 * different modes for run instructions <br>
	 * <b>MODE_RUN</b>: run till error or end <br>
	 * <b>MODE_DEBUG_INSTRUCTION</b>: run an instruction then pause (waiting) <br>
	 * <b>MODE_DEBUG_CIRCLE</b>: run an circle of an instruction then pause
	 * (waiting)
	 * @author yuma youzhao yufei zhou
	 */
	public final int OFFSET=2;
	public static final int MODE_RUN = 0;
	public static final int MODE_DEBUG_INSTRUCTION = 1;
	public static final int MODE_DEBUG_CIRCLE = 2;

	public static final int STATE_IDLE = 0;
	public static final int STATE_RUNNING = 1;
	public static final int STATE_FINISHED = 2;
	public static final int PROGRAM_OFFSET=55;
	/**
	 * 
	 */
	public static final int SIGNAL_DEBUG = 0;
	public static final int SIGNAL_IN = 1;
	public static final int SIGNAL_OUT = 2;

	/**
	 * the thread that simulating the cpu will be held for some
	 * reasons(DEBUG,IN,OUT) <br>
	 * only the same signal can awake the cpu thread
	 * 
	 * when cpu thread is set WAIT, waitingSignal must be set to a signal type
	 * (SIGNAL_DEBUG,SIGNAL_IN,SIGNAL_OUT); <br>
	 * the method continueRun can wake up cpu thread with the same signal type
	 * 
	 * <br>
	 * that means, when cpu is waiting for a IN device (such as keyboard),
	 * clicking the button(CONTINUE/RUN) can awake the thread to run
	 * 
	 */
	private int waitingSignal;
	/**
	 * to tell which device is cpu waiting for
	 */
	// no need, because device id is store in Register(DEVID)
	// private int waitingDevice;
	/**
	 * to store the value of comming signal
	 */
	private int commingSignalValue;

	/**
	 * when cpu execute OUT instruction, send the signal information into buffer <br>
	 * DEVICEs will check this buffer and get the signal for themselves
	 */
	private CPUOutputSignalBuffer outputBuffer;

	public CPUOutputSignalBuffer getOutputBuffer() {
		return outputBuffer;
	}

	private int runMode = MODE_RUN;
	private int state;

	public void setRunMode(int runMode) {
		this.runMode = runMode;
	}

	private static CPU _instance;

	public static CPU getInstance() {
		if (_instance == null)
			_instance = new CPU();
		return _instance;
	}

	/**
	 * all registers are stored here
	 */
	private Map<String, Register> registers;
	/**
	 * just used to save the order of registers
	 */
	private List<String> registersOrder;

	/**
	 * disable or enable cache here
	 */
	
	
	private IODeviceDriver ioDevices = IODeviceDriver.getInstance();
	
	
	
	private AbstractMemory memory =Cache.getInstance();
	
	// private Memory memory = MainMemory.getInstance();

	private Log log = Log.getInstance();

	// private boolean isDebug = true;
	// private int leftStep = 0;

	public CPU() {

		outputBuffer = new CPUOutputSignalBuffer();
		registers = new HashMap<String, Register>();
		registersOrder = new ArrayList<String>();
		// init all registers
		// regR0 = new Register("R0",18);
		// regR1 = new Register("R1",18);
		// regR2 = new Register("R2",18);
		// regR3 = new Register("R3",18);
		// regX1 = new Register("X1",12);
		// regX2 = new Register("X2",12);
		// regX3 = new Register("X3",12);
		// regPC = new Register("PC",12);
		// regCC = new Register("CC",4);
		// regIR = new Register("IR",18);
		// regMAR = new Register("MAR",12);
		// regMBR = new Register("MBR",18);
		// regMSR = new Register("MSR",18);
		// regMFR = new Register("MFR",4);
		//
		// regOPCODE = new Register("OPCODE",6);
		// regR = new Register("R",2);
		// regI = new Register("I",1);
		// regIX = new Register("IX",2);
		// regADDR = new Register("ADDR",8);
		// regEA = new Register("EA",8);

		/*
		 * General Purpose Registers <br>18-bit
		 */
		addRegister("R0", 18);
		addRegister("R1", 18);
		addRegister("R2", 18);
		addRegister("R3", 18);

		/*
		 * index registers: contains a 12-bit base address that supports base
		 * register addressing of memory. <br>12-bit
		 */
		addRegister("X1", 12);
		addRegister("X2", 12);
		addRegister("X3", 12);

		/*
		 * Program Counter: address of next instruction to be executed
		 * <br>12-bit
		 */
		addRegister("PC", 12);

		/*
		 * Condition Code: set when arithmetic/logical operations are executed;
		 * it has four 1-bit elements: overflow, underflow, division by zero,
		 * equal-or-not. <br>4-bit
		 */
		addRegister("CC", 4);

		/*
		 * Instruction Register: holds the instruction to be executed <br>18-bit
		 */
		addRegister("IR", 18);

		/*
		 * Memory Address Register: holds the address of the word to be fetched
		 * from memory <br>12-bit
		 */
		addRegister("MAR", 12);

		/*
		 * Memory Buffer Register: holds the word just fetched from or stored
		 * into memory <br>18-bit
		 */
		addRegister("MBR", 18);

		/*
		 * Machine Status Register: certain bits record the status of the health
		 * of the machine <br>18-bit
		 */
		addRegister("MSR", 18);

		/*
		 * Machine Fault Register: contains the ID code if a machine fault after
		 * it occurs <br>4-bit
		 */
		addRegister("MFR", 4);

		addRegister("OPCODE", 6);
		addRegister("R", 2);
		addRegister("RX", 2);
		addRegister("RY", 2);
		addRegister("I", 1);
		addRegister("IX", 2);
		addRegister("ADDR", 8);
		addRegister("EA", 8);

		addRegister("OP1", 18);  
		addRegister("OP2", 18);
		addRegister("RES", 18);

		addRegister("fOP1", 18);  
		addRegister("fOP2", 18); //Floating point unit.
		addRegister("fRES", 18);		
		
		addRegister("DEVID", 5);
		
		addRegister("FR", 2);
		addRegister("FR0", 18);		
		addRegister("FR1", 18);			
	}

	private void addRegister(String name, int size) {
		registers.put(name, new Register(name, size));
		registersOrder.add(name);
	}

	/**
	 * thread for simulating the cpu
	 */
	private Thread thread;
	public Thread getThread()
	{
		return this.thread;
	}
	/**
	 * run until finished or error
	 */
	public void execute() {

		/**
		 * unable to run when cpu not idle
		 */
		if (state != STATE_IDLE) {
			return;
		}

		thread = new Thread() {
			@Override
			public void run() {
				try {
					while (true) {

						state = STATE_RUNNING;
						sleep(1);
						log.console("--------------------START");
						executeNextInstruction();
						
						log.console("--------------------END");

						// if debug, wait for CONTINUE button
						if (runMode == MODE_DEBUG_INSTRUCTION)
							waitForSignal(SIGNAL_DEBUG);

					}
				} catch (InstructionExecuteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.console("[ERROR] " + e.getMessage());
				} catch (IllegalInstructionCodeException e) {
					// TODO Auto-generated catch block

					try {
						if (getRegisterByRealName("OPCODE").intValue() == 0) {
							log.console("[END] blank instruction");
						} else {
							e.printStackTrace();
							log.console("[ERROR] " + e.getMessage());
						}
					} catch (RegisterNotFoundException e1) {
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();

				} finally {
					state = STATE_IDLE;
					log.changeState();
				}
			}
		};

		thread.start();

	}

	/**
	 * run single step
	 */
	// public void executeSingleStep(){
	// try {
	// log.console("--------------------START");
	// executeNextInstruction();
	// log.console("--------------------END");
	// } catch (InstructionExecuteException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// log.console("[ERROR] "+ e.getMessage());
	// } catch (IllegalInstructionCodeException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// log.console("[ERROR] "+ e.getMessage());
	// }
	// synchronized(thread){
	// thread.notify();
	// }

	// }

	/**
	 * notify thread to run next instruction or cycle
	 */
	private void msg2(String str)
	{
		log.console(str);
	}
	public void continueRun(int signal) {

		if (signal != waitingSignal)
			return;

		if (thread == null)
			return;
		synchronized (thread) {
			thread.notify();
		}
	}

	private void executeNextInstruction()
			throws Exception {
		//System.out.println("thread in");
		try {
			/*
			 * execute first 4 steps
			 */
			InstructionCircles beginning = InstructionCircles
					.getCommonBeginning();
			for (InstructionCircle circle : beginning.getCircles()) {
				executeOneCircle(circle);
			}

			/*
			 * execute the different circles for specific instruction (after
			 * last 4 steps, we can know which instruction is executing)
			 */
			InstructionCircles content = InstructionSet
					.getCircles(getRegisterByRealName("OPCODE").intValue());
			if (content == null) {
				throw new IllegalInstructionCodeException("CODE:"
						+ getRegisterByRealName("OPCODE").intValue());
			}
			for (InstructionCircle circle : content.getCircles()) {
				executeOneCircle(circle);
			}
		} catch (IllegalMemoryAddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new InstructionExecuteException("OPCODE: "
					+ registers.get("OPCODE"));
		} catch (RegisterNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param circle
	 * @throws Exception 
	 */
	private void executeOneCircle(InstructionCircle circle)
			throws Exception {

		/**
		 * used to save log information
		 */
		StringBuffer msg = new StringBuffer();

		Register dest, source;
	//	System.out.println("test for thread");
		/*
		 * do different work according to the TYPE
		 */
		switch (circle.type) {

		case (InstructionCircle.TYPE_REG2REG):
			dest = getRegister(circle.args[0]);
			source = getRegister(circle.args[1]);
			dest.write(source.read());
			msg.append(dest.name).append(" = (").append(source.name).append(")");
			log.console((dest.name)+(" = (")+(source.name)+(")"));
			break;

		case (InstructionCircle.TYPE_REG2MEM):
			// TODO
			dest = getRegister(circle.args[0]);
			source = getRegister(circle.args[1]);
			memory.write(dest.intValue(), new Word(source.read()));
			msg.append("MEM(").append(dest.name).append(") = ")	.append(source.name);
			msg2(("MEM(")+(dest.name)+(") = ")+(source.name));
			break;
			
		case (InstructionCircle.TYPE_MEM2REG):
			System.out.println("Code: Mem to Reg");
			source = getRegister(circle.args[1]);
			dest = getRegister(circle.args[0]);
			dest.write(memory.read(source.intValue()).getData());
			System.out.println(dest.name+" "+source.name);
					
			msg.append(dest.name).append(" = MEM(").append(source.name).append(")");
			msg2((dest.name)+(" = MEM(")+(source.name)+(")"));
			break;

		case (InstructionCircle.TYPE_DECODE):
			doCPUDecode(msg);
			break;
		/*
		 * calculate EA by XI,i
		 */
		case (InstructionCircle.TYPE_CALCULATE_EA_TO_MAR):
			int ix = getRegisterByRealName("IX").intValue();
			System.out.println("current ix:"+ix+" value");
			// get address from ADDR
			int address = getRegisterByRealName("ADDR").intValue();
			// add address by Xn if n(IX)>0
			if (ix != 0) {
				System.out.println("current ix:"+ix+" value"+getRegisterByRealName("X" + ix).intValue());
				address += getRegisterByRealName("X" + ix).intValue();
			}
			// get value of I-register
			int i = getRegisterByRealName("I").intValue();
			// change address to the value of memory at $address$ if i==1
			if (i == 1)
				address = Util.getIntValueFromBits(memory.read(address)
						.getData());
			// save address into EA-register
			getRegisterByRealName("EA").setValueByInt(address);
			getRegisterByRealName("MAR").setValueByInt(address);
			System.out.println("EA: "+address);
			msg.append("MAR = (ADDR,IX,I)");
			msg2(("MAR = (ADDR,IX,I)"));
			break;

		case (InstructionCircle.TYPE_CALCULATE_EA_TO_MAR_NO_IX):
			// get address from ADDR
			address = getRegisterByRealName("ADDR").intValue();

			// get value of I-register
			i = getRegisterByRealName("I").intValue();
			// change address to the value of memory at $address$ if i==1
			if (i == 1)
				address = Util.getIntValueFromBits(memory.read(address)
						.getData());
			// save address into EA-register
			getRegisterByRealName("EA").setValueByInt(address);
			getRegisterByRealName("MAR").setValueByInt(address);

			msg.append("MAR = (ADDR,IX,I)");
			msg2("MAR = (ADDR,IX,I)");
			break;

		case (InstructionCircle.TYPE_CALCULATE_MBR_FOR_EXCUTE):

			break;

		case (InstructionCircle.TYPE_ALU_EXECUTE):
			int op1 = getRegisterByRealName("OP1").intValue();
			int op2 = getRegisterByRealName("OP2").intValue();
			int opcode = getRegisterByRealName("OPCODE").intValue();
			int value;
			Register cc=this.getRegister("CC");
			msg.append("RES = OP1 ");
			msg2("RES = OP1");
			switch (opcode) {

			// AMR,AIR
			case (4):
			case (6):
				value = op1 + op2;
				msg.append("+");
				msg2("+");
				break;

			// SMR,SIR
			case (5):
			case (7):
				value = op1 - op2;
				msg.append("-");
				msg2("-");
				break;

			// MLT
			case (20):
				value = op1 * op2;
				//OVERFLOW
				msg2("*");
				break;

			case (21):
				value = op1 / op2;
				int left = op1 % op2;
				int rx = getRegisterByRealName("RX").intValue();
				getRegisterByRealName("R" + rx).setValueByInt(value);
				rx++;
				getRegisterByRealName("R" + rx).setValueByInt(left);
				msg2("/");
				if(op2==0)
				{
					cc.writeBit(1, 2); //Write 1 at cc(2) for DEVZERO
				}
				
				break;
			case(22):
			{	
				if(op1==op2)
				{
					cc.writeBit(1, 3);
					value = 0;
				}
				else
				{
					cc.writeBit(0, 3);
					value = 0;
				}
				msg2("EQ");
				
				break;
			}

			default:
				value = 0;
			}



			msg.append(" OP2");
			msg2(" OP2");
			getRegisterByRealName("RES").setValueByInt(value);

			break;
		case (InstructionCircle.TYPE_FALU_EXECUTE):
			int[] data1 = getRegisterByRealName("fOP1").data;
			int[] data2 = getRegisterByRealName("fOP2").data;
			int FOPCODE = getRegisterByRealName("OPCODE").intValue();
			int Val;
			switch(FOPCODE)
			{
			case(33): //FADD
				Word W1=new Word(data1,Word.FOR_FLOAT);
				Word W2=new Word(data2,Word.FOR_FLOAT);
				ShortFloat F1=new ShortFloat(W1);
				ShortFloat F2=new ShortFloat(W2);
				//System.out.println(ShortFloat.toFloat(data1)+" "+ShortFloat.toFloat(data2));
				
				for(int iii=0;iii<16;iii++)
				{
					System.out.print(" "+data2[iii]);
				}
				System.out.println();
				for(int iii=0;iii<16;iii++)
				{
					System.out.print(" "+data1[iii]);
				}
				System.out.println();
				Word W3= ShortFloat.FADD(data1,data2);
				getRegisterByRealName("fRES").writeFloat(W3.data);;
				break;
			case(34):	//FSUB
				 W1=new Word(data1,Word.FOR_FLOAT);
				 W2=new Word(data2,Word.FOR_FLOAT);
					for(int iii=0;iii<16;iii++)
					{
						System.out.print(" "+data2[iii]);
					}
					System.out.println();
					for(int iii=0;iii<16;iii++)
					{
						System.out.print(" "+data1[iii]);
					}
					System.out.println();
				 W3= ShortFloat.FSUB(data1, data2);
				 getRegisterByRealName("fRES").writeFloat(W3.data);;				
				break;
			default:
				break;
			}

			break;
		case (InstructionCircle.TYPE_LOAD_OP1_AND_OP2):
			dest = getRegister(circle.args[0]);
			source = getRegister(circle.args[1]);
			getRegister("OP1").write(dest.read());
			getRegister("OP2").write(source.read());
			msg.append("OP1 = (").append(dest.name).append("), OP2 = (").append(source.name).append(")");
			msg2(("OP1 = (")+(dest.name)+("), OP2 = (")+(source.name)+(")"));
			break;
		case (InstructionCircle.TYPE_LOAD_F1_AND_F2): //F2= Memory
			System.out.println("load frs");
			dest = getRegister(circle.args[0]);
			source = getRegister(circle.args[1]);
			System.out.println(circle.args[0]+" "+circle.args[1]);
			getRegister("fOP1").write(dest.read());
			getRegister("fOP2").write(source.read());
			msg.append("fOP1 = (").append(dest.name).append("), fOP2 = (").append(source.name).append(")");
			msg2(("fOP1 = (")+(dest.name)+("), fOP2 = (")+(source.name)+(")"));
			break;
		case (InstructionCircle.TYPE_PC_PLUS):
			//System.out.println("AHHHH=======Code++  : ");
			dest = getRegisterByRealName("PC");
			dest.addValueByInt(1);
			msg.append("PC = (PC) + 1");
			msg2("PC = (PC) + 1");
			break;

		case (InstructionCircle.TYPE_JUMP_WITHCONDITION):
			// MAR
			
			source = getRegister("MAR");
			dest = getRegisterByRealName("PC");
			// get the condition value
			//cc=getRegister("CC");
			//cc.writeBit(1, 0);
			
			int conValue = getRegister(circle.args[0]).intValue();
			opcode = getRegister("OPCODE").intValue();
			System.out.println("Code: Jump Cond "+opcode+" value"+conValue+" tochar"+(char)conValue);
			//System.out.println("rrr"+circle.args.length);
			//System.out.println("rr"+circle.args[circle.args.length-1]);
			cc = this.getRegister("CC");
			//System.out.println("Code: JUMP- CC loaded");
			boolean jump = false;

			// check if jump
			// JZ && c(r)=0
			if (opcode == 10 && conValue == 0)
				{
					jump = true;
				}

			// JNE && c(r)!=0
			else if (opcode == 11 && conValue != 0)
				{
					jump = true;
				}

			// SOB && c(r)>0
			else if (opcode == 16 && conValue > 0)
				{
					jump = true;
				}

			// JMP
			else if (opcode == 13)
				{
					jump = true;
				}

			// JSR
			else if (opcode == 14)
				{
					jump = true;
				}

			else if (opcode == 12)
				{
					conValue = registers.get("R").intValue(); // it is R IX i ADDR type and the cc is "R" here
				
				if(cc.data[conValue]==1)
					{
						jump = true;
					}
				}
				else
					{
						jump = false;
					}
				System.out.println("Code: JUMP- jump checked");	

			System.out.println("PC JUMP Old:"+dest.intValue());
			if (jump) {
				// PC <- MAR
				System.out.println("do jump");
				dest.write(source.read());
				System.out.println("PC JUMP new:"+dest.intValue());
			} else {
				// PC = PC + 1
				System.out.println("NO jump");
				dest.addValueByInt(1);
				System.out.println("PC  new:"+dest.intValue());
			}

			msg.append("PC = (MAR)");
			msg2("PC = (MAR)");
			break;

		// case(InstructionCircle.TYPE_JZ):
		// //MAR
		// source = getRegister(circle.args[1]);
		// dest = getRegisterByRealName("PC");
		// if(getRegister(circle.args[0]).intValue()==0){
		// //PC <- MAR
		// dest.write(source.read());
		// }else{
		// //PC = PC + 1
		// dest.addValueByInt(1);
		// }
		// break;

		case (InstructionCircle.TYPE_DEV2REG):
			// save devid
			// waitingDevice = getRegister(circle.args[1]).intValue();

			// waiting for device input
			//waitForSignal(SIGNAL_IN);

			// signal in, save value into R
		System.out.println("Code: Input");
		
		
		
		/*	dest = getRegister(circle.args[0]);
			dest.setValueByInt(commingSignalValue);
		break;*/
			//msg.append(dest.name + " = IN_VALUE(" + commingSignalValue + ")");
			
			
			//====reverse
		int inDevice = getRegister("DEVID").intValue();
			Word word=null;
			try {
				word = ioDevices.input(inDevice);
			//	System.out.println("Test 3  : ");
			} catch (IllegalDeviceStatusException e) {
				// TODO Auto-generated catch block
				Word.donothing();
				e.printStackTrace();
			}
		
			//System.out.println("Test4  : ");
			//signal in, save value into R
			dest = getRegister(circle.args[0]);
			
			//System.out.println("11 "+word.getData());
			dest.write(word.getData());
			System.out.println("Test 5  : "+(char)word.intValue()+" "+(char)dest.intValue());

			msg.append(dest.name+" = IN_VALUE("+word.intValue()+")");
			msg2((dest.name+" = IN_VALUE("+word.intValue()+")"));

			//continueRun(waitingSignal);
			break;

			case (InstructionCircle.TYPE_REG2DEV):
			;
			/*get output value and device id
			source = getRegister(circle.args[0]);
			int devid = getRegister("DEVID").intValue();
			// send signal
			outputBuffer.outputSignal(devid, source.intValue());
			log.changeState();
			//
			break;*/
			//msg.append("DEV(" + devid + ") = " + source.name);
			System.out.println("Code: Output");
			//get output value and device id
			source = getRegister(circle.args[0]);
			int devid = getRegister("DEVID").intValue();
			//send signal
			try {
				ioDevices.output(devid, new Word(source.data));
			} catch (IllegalDeviceStatusException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			outputBuffer.outputSignal(devid, source.intValue());
			log.changeState();
			msg.append("DEV("+devid+") = "+source.name);
			msg2(("DEV("+devid+") = "+source.name));
		break;
			
			
		case (InstructionCircle.TYPE_MinusOne):
			dest = getRegister(circle.args[0]);
			dest.setValueByInt(dest.intValue() - 1);
			msg.append(dest.name + " = " + dest.name + " - 1");
			msg2(dest.name + " = " + dest.name + " - 1");
			break;

		default:

			break;

		}
		//log.console(msg.toString());

		if (runMode == MODE_DEBUG_CIRCLE) {
			waitForSignal(SIGNAL_DEBUG);
		}

		// log.console("one circle["+circle.type+"] finished...");
	}

	/**
	 * 
	 * @param msg
	 * @throws RegisterNotFoundException
	 * @throws IllegalInstructionCodeException
	 */
	private void doCPUDecode(StringBuffer msg)
			throws RegisterNotFoundException, IllegalInstructionCodeException {

		Register source = getRegister("IR");
		getRegisterByRealName("OPCODE").write(source.subValue(0, 6));

		int opcode = Util.getIntValueFromBits(source.subValue(0, 6));

		// get InstructionInfo by Name
		InstructionInfo insInfo = InstructionSetInfo.getInstance()
				.getInstrInfoByCode(opcode);

		if (insInfo == null)
			throw new IllegalInstructionCodeException("CODE:"
					+ getRegisterByRealName("OPCODE").intValue());

		switch (insInfo.getBinaryType()) {
		case (InstructionSetInfo.BINARY_STYLE_R_AL_LR_COUNT):
			// OPCODE(6),(2),R(2),A/L(1),L/R(1),(1),Count(5)
			getRegisterByRealName("R").write(source.subValue(8, 2));
			getRegisterByRealName("AL").write(source.subValue(10, 1));
			getRegisterByRealName("LR").write(source.subValue(11, 1));
			getRegisterByRealName("COUNT").write(source.subValue(13, 5));
			break;

		case (InstructionSetInfo.BINARY_STYLE_R_DEVID):
			// OPCODE(6),(2),R(2),(3),DevId(5)
			getRegisterByRealName("R").write(source.subValue(8, 2));
			getRegisterByRealName("DEVID").write(source.subValue(13, 5));
			break;

		case (InstructionSetInfo.BINARY_STYLE_R_IX_I_ADD):
			// OPCODE(6),R(2),IX(2),I(1),ADD(7)
			getRegisterByRealName("R").write(source.subValue(6, 2));
			getRegisterByRealName("IX").write(source.subValue(8, 2));
			getRegisterByRealName("I").write(source.subValue(10, 1));
			getRegisterByRealName("ADDR").write(source.subValue(11, 7));
			break;

		case (InstructionSetInfo.BINARY_STYLE_RX_RY):
			// OPCODE(6),Rx(2),Ry(2),(8)
			getRegisterByRealName("RX").write(source.subValue(6, 2));
			getRegisterByRealName("RY").write(source.subValue(8, 2));
			break;
		case (InstructionSetInfo.BINARY_STYLE_FR_IX_I_ADD):	
			{
			getRegisterByRealName("FR").write(source.subValue(6, 2));
			getRegisterByRealName("IX").write(source.subValue(8, 2));
			getRegisterByRealName("I").write(source.subValue(10, 1));
			getRegisterByRealName("ADDR").write(source.subValue(11, 7));	
			break;
		
			}
		
		}

		msg.append("OPCODE,IX,R,I,ADDR = (").append(source.name).append(")");
		msg2(("OPCODE,IX,R,I,ADDR = (")+(source.name)+(")"));
	}

	/**
	 * this method return the register by specific name <br>
	 * if name="R", Rn will be returned (n is decided by R-register's value) <br>
	 * so does "IX"
	 * 
	 * <br>
	 * R-register and IX-register can be got by 'getRegisterByRealName(name)'
	 * 
	 * @param name
	 * @return Register of $registerRealName$
	 * @throws RegisterNotFoundException
	 *             if no register is found with $registerRealName$
	 */
	private Register getRegister(String name) throws RegisterNotFoundException {
		// find the real name for register
		String registerRealName;
		if ("R".equals(name)) {
			int rVal = registers.get(name).intValue();
			registerRealName = ("R" + rVal);
		} else if ("RX".equals(name)) {
			int rVal = registers.get(name).intValue();
			registerRealName = ("R" + rVal);
		} else if ("RY".equals(name)) {
			int rVal = registers.get(name).intValue();
			registerRealName = ("R" + rVal);
		} else if ("IX".equals(name)) {
			int iVal = registers.get(name).intValue();
			registerRealName = ("X" + iVal);
			
		} 
		else if("FR".equals(name))
		{
			int iVal = registers.get(name).intValue();
			registerRealName = ("FR" + iVal);			
		}
		else {
			registerRealName = name;
		}

		return getRegisterByRealName(registerRealName);
	}

	/**
	 * 
	 * @param registerRealName
	 * @return Register of $registerRealName$
	 * @throws RegisterNotFoundException
	 *             if no register is found with $registerRealName$
	 */
	public Register getRegisterByRealName(String registerRealName)
			throws RegisterNotFoundException {
		if (registers.keySet().contains(registerRealName))
			return registers.get(registerRealName);
		else
			throw new RegisterNotFoundException(registerRealName);
	}

	/**
	 * get the map of register
	 * 
	 * @return
	 */
	public Map<String, Register> getRegisters() {
		return registers;
	}

	/**
	 * get the amount of registers
	 * 
	 * @return
	 */
	public int getRegisterSize() {
		return registers.size();
	}

	/**
	 * get the name list (keep the order in which registers are added)
	 * 
	 * @return
	 */
	public List<String> getRegisterNames() {
		return this.registersOrder;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	private void waitForSignal(int signal) {
		try {
			synchronized (thread) {
				waitingSignal = signal;
				thread.wait();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * used for device with devid to send input information <br>
	 * successful sending happens only when cpu thread is waiting for message
	 * from the device with the same devid
	 * 
	 * @param devid
	 * @param value
	 */
	public void inSignal(int devid, int value) {
		if (waitingSignal != SIGNAL_IN)
			return;

		try {
			if (devid != getRegister("DEVID").intValue())
				return;
		} catch (RegisterNotFoundException e) {
			e.printStackTrace();
		}

		commingSignalValue = value;

		continueRun(SIGNAL_IN);

	}

	/**
	 * enable or disable Cache
	 * 
	 * @param enable
	 */
	public void setCahceEnable(boolean enable) {
		if (enable) {
			memory =Cache.getInstance();
			Cache.getInstance().clear();
		} else {
			memory = Memory.getInstance();
		}

	}

	public void clear() {
		for (Register reg : registers.values())
			reg.setValueByInt(0);
	}

}

