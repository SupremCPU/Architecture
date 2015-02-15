import java.util.ArrayList;
public class Instruction extends Message {
	//Callbacks are implemented in CPU as some concrete set information.
	//But still needs some branch code here for checking the number of parameters.
	public String codeName; //The code name of the opCode, like AMR.
	public String FullCommand; //Could be null. Just a carrier of a fetched command.  --------loaded by Readline
	public short Inst=0; //a 2 bytes Inst
	public CPU cpu=null;
	public int OPCODE=0;
	Instruction(CPU cpu)
	{
		super();
		this.cpu=cpu;
	}
	Instruction(int Type,int Property,short whichInstr,CPU cpu)
	{
		super();
		this.cpu=cpu;
	}
	Instruction(short whichInstr,CPU cpu)
	{
		super(Instr,Normal);
		this.Inst=whichInstr;
		this.cpu=cpu;
	}
	
	Instruction(String CommandLine,CPU cpu)
	{
		super(Instr,Normal);
		this.FullCommand=CommandLine;
		this.ParseInst();
		this.cpu=cpu;
	}
	
	
	//It is defined that parameters are separated by space,not commas.
	public boolean ParseInst() //Parse the String FullCommand to codeName and Inst(Binary). If it is not recognized then return a false..otherwise return true;
	{	String FullCommand=new String(this.FullCommand);
		if(FullCommand=="")return false;  //All parameter must be strictly separate by one space.
		int i=FullCommand.indexOf(" ");
		String COMM=FullCommand.substring(0, i);
		FullCommand=FullCommand.substring(i+1); //Rest: parameters
		this.codeName=new String(COMM);
		//System.out.println("Parameter List: "+FullCommand);
		this.Inst=0;
		
		Opcode CodeInfo=CPU.StrList.get(COMM);
		short CodeID=(short)CodeInfo.Opcode;
		this.OPCODE=CodeID;
	//	System.out.println("Code "+Integer.toBinaryString(CodeID));
		if(CodeID<=0)
		{
			System.out.println("unrecognizable opcode");
			return false;
		}
		this.Inst=(short)(CodeID<<10); //First Step
		int Para=CodeInfo.ParaNum;
		int j;
		int left=10; 
		for(j=0;j<Para-1;j++)
		{
			i=FullCommand.indexOf(" ");
			  COMM=FullCommand.substring(0, i);
			  short P=(short)Integer.parseInt(COMM);
			  if(P>CodeInfo.Size[j])
			  {	
				  System.out.println("Alert! Parameter Out of Range. Current: "+P+" Limited by "+CodeInfo.Size[j]);
				  return false;
			  }
			  left=left-CodeInfo.BitLength[j];
			 this.Inst+=P<<(left);
			 FullCommand=FullCommand.substring(i+1);
			 //throw a parse error.
		}
			COMM=FullCommand;
			int space=FullCommand.indexOf(" ");
		 if(CodeInfo.takeI==false ||space==-1)
		 {
			 short P=(short)Integer.parseInt(COMM);
			 left=left-CodeInfo.BitLength[j];
			 this.Inst+=P<<(left);
		 }
		 else
		 {	
			 short P=(short)Integer.parseInt(COMM.substring(0,space));
			 left=left-CodeInfo.BitLength[j];
			 FullCommand=FullCommand.substring(space+1);
			 this.Inst+=P<<(left);			 
			 boolean I=FullCommand.charAt(0)=='I'&&FullCommand.length()<=1;
			 if(I)
			 {
				 this.Inst+=1<<5;
			 }
			 
		 }	 
		//	System.out.println("Print: Instruction parsed: "+this.Inst);
		return true;
	}
	
	public void Exec()
	{
		CodeExecutor Execution=(CPU.Command.get(this.OPCODE));
		if(Execution==null)
		{
			System.out.println("Call back not found. OPCode: "+OPCODE+" ");
			return;
		}
		Execution.Init(this.Inst,this.cpu);
		Execution.ExecuteFunc();
		this.cpu.PC.Increment();
	}
	

	
	public static void main(String[]args){
		Bus Bus=new Bus();
		CPU CPUU=new CPU(Bus);
		Memory Memory=new Memory(Bus);
		Bus.Init(CPUU, Memory);
		Instruction test=new Instruction((short)0,CPUU);
		test.FullCommand="LDR 1 2 3 I";
		test.ParseInst();
		CodeExecutor A=(CPU.Command.get(3));
		CPUU.R[1].write((short)20);
		Memory.write(30, (short) 1000);
		Memory.write(1000, (short) 909);
		A.Init(1,0,0,30,CPUU);
		A.ExecuteFunc();

		System.out.println(Memory.fetch((short)30));
	}
}
