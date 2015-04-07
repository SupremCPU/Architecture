import java.util.ArrayList;
import java.util.Hashtable;
//Declaration : For all parameters, of which the range is only limited by the bit length of the input, the check of the range is put there merely for debug thing to check if there is code mistakes. 
//Actually those check are not actively done by the machine. 


public class CPU {
	//========================Mapping of Instruction String --> Opcode and Opcode --> Callback 
	public static Hashtable<Integer,CodeExecutor> Command=new Hashtable<Integer,CodeExecutor>();
	public static Hashtable<String,Opcode> StrList=new Hashtable<String,Opcode>();
	
	//======================A central cycle====== Need more implementation.
	public int clockFreq=1000;
	
	//=========================Mimic Inst as a Message Queue=========
	public ArrayList<Message> MessageQue=null;
	//========================An Agent to execute and manage all inst. But possibly useless. Let`s see.
	public sysThread OperatingSystem=new sysThread(this);
	
	
	//=====================Just A Const.
	public final static int byteSize=8;
	
	
	//Definition of All Registers.  Extends from class "Register". 
	public MBR MBR=new MBR(this);
	public MAR MAR=new MAR(this); //All CPU parameter in the constructors of registers: mapping register to the CPU they work for.
	public GPR[] R={new GPR(0,this),new GPR(1,this),new GPR(2,this),new GPR(3,this)};
	public IX[] X={new IX(0,this,ReadOnly),new IX(1,this,WriteAble),new IX(2,this,WriteAble),new IX(3,this,WriteAble)}; //IX is from X1~X3, 0 will always contains nothing--> convenience for the array indexing.
	public MFR MFR=new MFR(this);
	public MSR MSR=new MSR(this);
	public IR IR=new IR(this);
	
	public final static boolean ReadOnly=true; 
	public final static boolean WriteAble=false; 	
	public final static int Indirect=1;
	public final static int Direct=0;
	public byte[] CC=(new StatusFlag((byte)0,(byte)0,(byte)0,(byte)0)).ZCSO(); //StatusFlag is a signal pack.
	public PC PC; //PC and bus initiate in constructor
	public Bus Bus;  //Initiate it.
					 //Bus is actually removed from this project.  
					 //this bus does not really work like bus, but something like an port. Keeping it here assures less change in the code.

	
	public ArithRegister[] AL={new ArithRegister(0,this),new ArithRegister(1,this),new ArithRegister(2,this)};
	
	
	
	
	
	
	
	
	
	//==============================Definition of Instruction=============================================================		
	public CodeExecutor LDR =new CodeExecutor() 
	{
		public short RIndex;
		public int IXIndex;
		public int I;
		public int Mem;
		public CPU Father;
		public MAR MAR;//public int ValA;
		public MBR MBR;//public int ValB=0;
		public GPR GPR;
		public IX XX;
		public void Init(int R1,int IX1,int I1,int Mem1,CPU CPU)//Pass the values
		{
			RIndex=(short)R1;
			IXIndex=IX1;
			I=I1;
			Mem=Mem1;
			Father=CPU;
			MAR=Father.MAR;
			MBR=Father.MBR;
			System.out.println("Debug I:"+I);
		}
		public void Init(short Instruct,CPU CPU)
		{
			RIndex=(short)(Instruct<<(22)>>>30);
			IXIndex=Instruct<<24>>>30;
			I=(Instruct&32)>>>5;
			Mem=Instruct&0x1F;
			Father=CPU;
			MAR=Father.MAR;
			MBR=Father.MBR;	
			System.out.println("Debug I:"+I);
			//System.out.println("DbgMem "+Mem);
		}
		public void Fetch()
		{
            System.out.println("Current PC: "+Father.PC.get()); //We need some idea to deal with this mess.
            System.out.println ("Load things from Memory to Register....");
		
			GPR=Father.R[RIndex];
			//IX is moved to CPU implement, automatically;
			XX=IXIndex<=0||IXIndex>3?null:Father.X[IXIndex];
			System.out.println("Target Register: R"+RIndex+". The IX Value is "+IXIndex);
			
			
		}
		public void Decode()
		{	MAR.write((short) getEAByIX(Mem,I,IXIndex));
			System.out.println("Effective Target Memory Address is "+MAR.read()+" Write it to MAR");
			MBR.write((short) Father.readMemDirect(MAR.read()))  ;         //Father.readEffecientMemory(Mem,I,IXIndex); //the index of the
			System.out.println("Access the memory pointed by MAR. Load the data to MBR. Data value is:"+MBR.read());			
		}
		public void Execute()
		{
			//For Load , actually +0 or donothing;
		}
		public void WriteBack()
		{
			GPR.write(MBR.read());
			System.out.println("Write data value back to R"+RIndex+" from MBR. The value is: "+GPR.read()); //Use GPR.read instead of MBR.read will be able tell us if the transfer is succeeded.
			
		}
		public void Memory()
		{		
		}
		public void ExecuteFunc()
		{
			Fetch();
			Decode();
			//Execute();
			WriteBack();
			Father.PC.Increment();
		}
	};
	public CodeExecutor STR=new CodeExecutor()
	{
		public int RIndex;
		public int IXIndex;
		public int I;
		public int Mem;
		public CPU Father;
		public MAR MAR;//public int ValA;
		public MBR MBR;
		public GPR GPR;
		public IX XX;
		public void Init(int R1,int IX1,int I1,int Mem1,CPU CPU)//Pass the values
		{
			RIndex=R1;
			IXIndex=IX1;
			I=I1;
			Mem=Mem1;
			Father=CPU;
			MAR=Father.MAR;
			MBR=Father.MBR;
		}	
		public void Init(short Instruct,CPU CPU)
		{
			RIndex=Instruct<<22>>>30;
			IXIndex=Instruct<<24>>>30;
			I=(Instruct&32)>>>5;
			Mem=Instruct&0x1F;
			Father=CPU;
			MAR=Father.MAR;
			MBR=Father.MBR;	
		}		
		public void Fetch()
		{	
			GPR=Father.R[RIndex];
			//IX is moved to CPU implement, automatically;
			XX=IXIndex<=0||IXIndex>3?null:Father.X[IXIndex];	
			System.out.println("Current PC is "+Father.PC.get());
			System.out.println("Store Data in Register Back to Memory.");
			System.out.println("Source Register is R"+RIndex+". IX="+IXIndex);
			System.out.println("Data in Source Register is"+this.GPR.read());
		}
		public void Decode()
		{	MAR.write((short) getEAByIX(Mem,I,IXIndex));
			//ValA=GPR.read()  ;         //Father.readEffecientMemory(Mem,I,IXIndex); //the index of the	
			System.out.println("Target Memory is "+MAR.read()+". Loaded to MAR");
		}	
		public void Execute()
		{
			
		}
		public void WriteBack()
		{
			
		}
		public void Memory()
		{	this.MBR.write(this.GPR.read());
			System.out.println("Load data to MBR. Value:"+this.MBR.read());
			this.Father.Memory().write(this.MAR.read(), this.GPR.read());
			System.out.println("Write Data in R"+RIndex+" Back to Memory pointed by MAR:"+this.MAR.read()+". Data value in Memory: "+this.Father.Memory().fetch(this.MAR.read()));
		}
		public void ExecuteFunc()
		{
			Fetch();
			Decode();
			Memory();
			Father.PC.Increment();
		}
	};
	public CodeExecutor LDA=new CodeExecutor(){
		public int RIndex;
		public int IXIndex;
		public int I;
		public int Mem;
		public CPU Father;
		//public MAR MAR;//public int ValA;
		public MBR MBR;//public int ValB=0;
		public GPR GPR;
		public IX XX;
		int ValA;
		public void Init(int R1,int IX1,int I1,int Mem1,CPU CPU)//Pass the values
		{
			RIndex=R1;
			IXIndex=IX1;
			I=I1;
			Mem=Mem1;
			Father=CPU;
			//MAR=Father.MAR;
			MBR=Father.MBR;
			
		}
		public void Init(short Instruct,CPU CPU)
		{
			RIndex=Instruct<<22>>>30;
			IXIndex=Instruct<<24>>>30;
			I=(Instruct&32)>>>5;
			Mem=Instruct&0x1F;
			Father=CPU;
			//MAR=Father.MAR;
			MBR=Father.MBR;	
		}
		public void Fetch()
		{
			GPR=Father.R[RIndex];
			//IX is moved to CPU implement, automatically;
			XX=IXIndex<=0||IXIndex>3?null:Father.X[IXIndex];
			System.out.println("Current PC: "+this.Father.PC.get());
			System.out.println("Load Register with Address");
			System.out.println("Target Register is R"+RIndex+". IX="+IXIndex);
		}
		public void Decode()
		{	//MAR.write((short) getEAByIX(Mem,I,IXIndex));
			//MBR.write(Father.readMemDirect(MAR.read()))  ;         //Father.readEffecientMemory(Mem,I,IXIndex); //the index of the
			ValA=Father.getEAByIX(Mem, I, IXIndex);
			System.out.println("Decode the Address value to be loaded: "+ValA);
		}
		public void Execute()
		{
			
		}
		public void WriteBack()
		{
			GPR.write((short) ValA);
			System.out.println("Write the Address to R"+RIndex+". Value: "+GPR.read());
		}
		public void Memory()
		{		
		}
		public void ExecuteFunc()
		{
			Fetch();
			Decode();
			
			WriteBack();
			Father.PC.Increment();
		}
	};
	
	public CodeExecutor LDX=new CodeExecutor()
	{
		public int IX1;
		public int IX2;
		public int I;
		public int Mem;
		public CPU Father;
		public MAR MAR;//public int ValA;
		public MBR MBR;//public int ValB=0;
		//public GPR GPR;
		public IX X1;
		public IX X2;
		int ValA;
		public void Init(int I1,int I2,int II,int Mem1,CPU CPU)//Pass the values
		{
			IX1=I1;
			IX2=I2;
			I=II;
			Mem=Mem1;
			Father=CPU;
			MAR=Father.MAR;
			MBR=Father.MBR;
			
		}
		public void Init(short Instruct,CPU CPU)
		{
			IX1=Instruct<<22>>>30;
			IX2=Instruct<<24>>>30;
			I=(Instruct&32)>>>5;
			Mem=Instruct&0x1F;
			Father=CPU;
			MAR=Father.MAR;
			MBR=Father.MBR;	
		}
		
		
		public void Fetch()
		{
			X1=IX1==0?null:Father.X[IX1];
			if(X1==null)
			{
				//Throw something here;
				System.out.println("Fatal Error in LDX, null X1 register");
			}
			X2=IX2==0?null:Father.X[IX2];
			System.out.println("Current PC: "+this.Father.PC.get());
			System.out.println("Load Data in Memory to Index Register");
			System.out.println("Target Register is IX"+X1+". Indexing:"+X2);
		}
		public void Decode()
		{
			Father.MAR.write((short) Father.getEAByIX(Mem, I,IX2));
			System.out.println("Source Memory Address is "+MAR.read()+". Loaded to MAR");
		}
		public void Execute()
		{}
		public void Memory()
		{}
		public void WriteBack()
		{
			Father.MBR.write(Father.readMemDirect(Father.MAR.read()));
			System.out.println("Load Data to MBR. Value: "+MBR.read());
			this.X1.write(this.MBR.read());
			System.out.println("Load data from MBR to X"+IX1+". Data is "+X1.read());
		}
		public void ExecuteFunc()
		{
			Fetch();
			Decode();
			WriteBack();
			Father.PC.Increment();
		}
	};
	
	
	
	
	
	
	public CodeExecutor STX=new CodeExecutor()
	{
		public int IX1;
		public int IX2;
		public int I;
		public int Mem;
		public CPU Father;
		public MAR MAR;//public int ValA;
		public MBR MBR;//public int ValB=0;
		//public GPR GPR;
		public IX X1;
		public IX X2;
		int ValA;
		public void Init(int I1,int I2,int II,int Mem1,CPU CPU)//Pass the values
		{
			IX1=I1;
			IX2=I2;
			I=II;
			Mem=Mem1;
			Father=CPU;
			MAR=Father.MAR;
			MBR=Father.MBR;
			
		}
		public void Init(short Instruct,CPU CPU)
		{
			IX1=Instruct<<22>>>30;
			IX2=Instruct<<24>>>30;
			I=(Instruct&32)>>>5;
			Mem=Instruct&0x1F;
			Father=CPU;
			MAR=Father.MAR;
			MBR=Father.MBR;	
		}
		

		
		
		public void Fetch()
		{
			X1=IX1==0?null:Father.X[IX1];
			if(X1==null)
			{
				//Throw something here; ---Or it cause Null pointer access
				System.out.println("Fatal Error in STX, null X1 register");
			}
			X2=IX2==0?null:Father.X[IX2];
			System.out.println("Current PC: "+this.Father.PC.get());
			System.out.println("Store Data in Index Register to Memory.");
			System.out.println("Source Index Register is X"+IX1);
		}
		public void Decode()
		{	Father.MBR.write(X1.read());
			System.out.println("Load data to MBR. Value:"+MBR.read());
			Father.MAR.write((short) Father.getEAByIX(Mem, I,IX2));
			System.out.println("Target Memory is: "+MAR.read()+". Loaded into MAR");
			
			
		}
		public void Execute()
		{}
		public void Memory()
		{
			Father.Memory().write(Father.MAR.read(),MBR.read());
			System.out.println("Load data buffered in MBR to Memory pointed by MAR: "+this.MAR.read() +". Data Value in Memory:"+this.Father.Memory().fetch(this.MAR.read()));
			
		}
		public void WriteBack()
		{
			//Father.MBR.write(Father.readMemDirect(Father.MAR.read()));
		}
		public void ExecuteFunc()
		{
			Fetch();
			Decode();
			Memory();
			Father.PC.Increment();
		}
	};	
	
	public CodeExecutor AMR=new CodeExecutor()
	{
		
		public int RIndex;
		public int IXIndex;
		public int I;
		public int Mem;
		public CPU Father;
		public MAR MAR;//public int ValA;
		public MBR MBR;//public int ValB=0;
		public GPR GPR;
		public IX XX;
		public int ValA;
		public int ValB;
		public int ValC;
		ArithRegister Result;
		public void Init(int R1,int IX1,int I1,int Mem1,CPU CPU)//Pass the values
		{
			RIndex=R1;
			IXIndex=IX1;
			I=I1;
			Mem=Mem1;
			Father=CPU;
			MAR=Father.MAR;
			MBR=Father.MBR;
			Result=Father.AL[0];
		}
		public void Init(short Instruct,CPU CPU)
		{
			RIndex=Instruct<<22>>>30;
			IXIndex=Instruct<<24>>>30;
			I=(Instruct&32)>>>5;
			Mem=Instruct&0x1F;
			Father=CPU;
			MAR=Father.MAR;
			MBR=Father.MBR;	
			Result=Father.AL[0];
		}		
		public void Fetch()
		{
			GPR=Father.R[RIndex];
			//IX is moved to CPU implement, automatically;
			XX=IXIndex<=0||IXIndex>3?null:Father.X[IXIndex];
			System.out.println("Current PC: "+this.Father.PC.get());
			System.out.println("Add data from Memory to Register.");
			System.out.println("Target Register is R"+RIndex+". IX="+IXIndex);
			
		}		
		public void Decode()
		{
			MAR.write((short) Father.getEAByIX(Mem, I, IXIndex));
			System.out.println("Source Memory Address is: "+MAR.read()+". Loaded into MAR");
			MBR.write(Father.readMemDirect(MAR.read())); //MBR for what should be add
			System.out.println("Load data in mem to MBR. Value:"+MBR.read());
			ValA=GPR.read();
			ValB=MBR.read();
			
		}
		public void Execute()
		{	
			Father.CC=Result.Adder((short)ValA,(short)ValB).ZCSO();
			System.out.println("Load data into the Adder and calulate: "+ValA+"+"+ValB);
			System.out.println("Set CC.");
			System.out.println("Debug. Just remember to check this CC later");
		}
		public void Memory()
		{
			
		}
		public void WriteBack()
		{
			GPR.write(Result.read());
			System.out.println("Load the sum in Result Register to R"+RIndex+". Result is"+GPR.read());
		}
		public void ExecuteFunc()
		{
			Fetch();
			Decode();
			Execute();
			WriteBack();
			Father.PC.Increment();
		}
	};
	
	public CodeExecutor SMR=new CodeExecutor()
	{
		
		public int RIndex;
		public int IXIndex;
		public int I;
		public int Mem;
		public CPU Father;
		public MAR MAR;//public int ValA;
		public MBR MBR;//public int ValB=0;
		public GPR GPR;
		public IX XX;
		public int ValA;
		public int ValB;
		public int ValC;
		ArithRegister Result;
		public void Init(int R1,int IX1,int I1,int Mem1,CPU CPU)//Pass the values
		{
			RIndex=R1;
			IXIndex=IX1;
			I=I1;
			Mem=Mem1;
			Father=CPU;
			MAR=Father.MAR;
			MBR=Father.MBR;
			Result=Father.AL[0];
		}
		public void Init(short Instruct,CPU CPU)
		{
			RIndex=Instruct<<22>>>30;
			IXIndex=Instruct<<24>>>30;
			I=(Instruct&32)>>>5;
			Mem=Instruct&0x1F;
			Father=CPU;
			MAR=Father.MAR;
			MBR=Father.MBR;	
			Result=Father.AL[0];
		}	
		public void Fetch()
		{
			GPR=Father.R[RIndex];
			//IX is moved to CPU implement, automatically;
			XX=IXIndex<=0||IXIndex>3?null:Father.X[IXIndex];
			System.out.println("Current PC: "+this.Father.PC.get());
			System.out.println("Subtract  Memory from Register.");
			System.out.println("Target Register is R"+RIndex+". IX="+IXIndex);
		}		
		public void Decode()
		{
			MAR.write((short) Father.getEAByIX(Mem, I, IXIndex));
			System.out.println("Target Address is "+MAR.read()+". Loaded into MAR");
			MBR.write(Father.readMemDirect(MAR.read())); //MBR for what should be add
			System.out.println("Load data stored in memory pointed by MAR to MBR."+" Value is "+MBR.read());
			ValA=GPR.read();
			ValB=MBR.read();

		}
		public void Execute()
		{
			Father.CC=Result.Sub((short) (ValA),(short)ValB).ZCSO();
			System.out.println("Load data to Subtractor and calculate: "+ValA+"-"+ValB);
			System.out.println("Set CC");
			System.out.println("Debug: checkCC later");
			
		}
		public void Memory()
		{
			
		}
		public void WriteBack()
		{
			GPR.write(Result.read());
			System.out.println("Write back the result from Result Register to R"+RIndex+". Result is "+GPR.read());
		}
		public void ExecuteFunc()
		{
			Fetch();
			Decode();
			Execute();
			WriteBack();
			Father.PC.Increment();
		}
	};
	
	
	public CodeExecutor AIR=new CodeExecutor(){
		public int RIndex;
		public int IXIndex;
		public int I;
		public int Mem;
		public CPU Father;
		public MAR MAR;//public int ValA;
		public MBR MBR;//public int ValB=0;
		public GPR GPR;
		public IX XX;
		public int ValA;
		public int ValB;
		public int ValC;
		
		public boolean skip=false;
		ArithRegister Result;
		public void Init(int R1,int IX1,int I1,int Mem1,CPU CPU)//Pass the values
		{
			RIndex=R1;
			 
			Mem=Mem1; //Immediate value. Other things are disgarded
			Father=CPU;
			
			//MBR=Father.MBR;
			Result=Father.AL[0];
		}	
		public void Init(short Instruct,CPU CPU)
		{
			RIndex=Instruct<<22>>>30;
			//IXIndex=Instruct<<8>>>6;
			//I=Instruct&32>>>5;
			Mem=Instruct&0x1F; //Immediate
			Father=CPU;
			//MAR=Father.MAR;
			//MBR=Father.MBR;	
			Result=Father.AL[0];
		}	
		public void Fetch()
		{
			GPR=Father.R[RIndex];
			System.out.println("Current PC: "+this.Father.PC.get());
			System.out.println("Add immediate number to Register.");
			System.out.println("Target Register is R"+RIndex);			
		}
		public void Decode()
		{
			ValA=GPR.read();
			ValB=Mem;
		}
		public void Execute()
		{
			if(ValA!=0){
				Father.CC=Result.Adder((short)ValA, (short)ValB).ZCSO();
				System.out.println("Non Zero in R"+RIndex+" Detected. Load Data to Adder and calculate: "+ValA+"+"+ValB);
			}
			else
			{
				skip=true;
				System.out.println("Zero in R"+RIndex+" Detected. Skip calculation.");
			}	
		}
		public void Memory()
		{
			
		}
		public void WriteBack()
		{	if(skip)
			{	
				GPR.write((short) Mem); //Directly load without adder
				System.out.println("Calculation skipped. Direct Load Immediate Number: "+GPR.read()+" to R"+RIndex);
			}
			else
			{
				GPR.write(Result.read()); //Normal steps
				System.out.println("Write result from Result Register Back to R"+RIndex+". Result is "+GPR.read());
			}
		}
		public void ExecuteFunc()
		{	if(Mem==0){
			System.out.println("AIR Check: Add 0 and Do nothing"); //Quit
		}
			Fetch();
			Decode();
			Execute();
			WriteBack();
			Father.PC.Increment();
		}
		
		
		
		
	};
	
	
	public CodeExecutor SIR=new CodeExecutor(){
		public int RIndex;
		public int IXIndex;
		public int I;
		public int Mem;
		public CPU Father;
		public MAR MAR;//public int ValA;
		public MBR MBR;//public int ValB=0;
		public GPR GPR;
		public IX XX;
		public int ValA;
		public int ValB;
		public int ValC;
		
		public boolean skip=false;
		ArithRegister Result;
		public void Init(int R1,int IX1,int I1,int Mem1,CPU CPU)//Pass the values
		{
			RIndex=R1;
			 
			Mem=Mem1; //Immediate value. Other things are disgarded
			Father=CPU;
			
			//MBR=Father.MBR;
			Result=Father.AL[0];
		}	
		
		public void Init(short Instruct,CPU CPU)
		{
			RIndex=Instruct<<22>>>30;
			//IXIndex=Instruct<<8>>>6;
			//I=Instruct&32>>>5;
			Mem=Instruct&0x1F;  //Here the "Mem" is actually the Immediate value rather than things in memory.
			Father=CPU;
			//MAR=Father.MAR;
			//MBR=Father.MBR;	
			Result=Father.AL[0];
		}	
		public void Fetch()
		{
			GPR=Father.R[RIndex];
			System.out.println("Current PC: "+this.Father.PC.get());
			System.out.println("Subtract immediate number from Register.");
			System.out.println("Target Register is R"+RIndex);		
		}
		public void Decode()
		{
			ValA=GPR.read();
			ValB=Mem;
		}
		public void Execute()
		{
			if(ValA!=0){
				Father.CC=Result.Sub((short)ValA, (short)ValB).ZCSO();
				System.out.println("Non Zero in R"+RIndex+" Detected. Load Data to Subtractor and calculate: "+ValA+"-"+ValB);
			}
			else
			{
				skip=true;
				System.out.println("Zero in R"+RIndex+" Detected. Skip calculation.");
			}	
		}
		public void Memory()
		{
			
		}
		public void WriteBack()
		{	if(skip)
			{
				GPR.write( (short)(Mem*-1)); //Directly load without adder
				System.out.println("Calculation Skipped. Direct Load -1*Immediate Number: "+GPR.read()+" to R"+RIndex);
			}
			else
			{
				GPR.write(Result.read()); //Normal steps
				System.out.println("Write result from Result Register Back to R"+RIndex+". Result is "+GPR.read());
			}
		}
		public void ExecuteFunc()
		{	if(Mem==0){
			System.out.println("SIR Check: Subtract 0 and Do nothing"); //Quit
		}
			Fetch();
			Decode();
			Execute();
			WriteBack();
			Father.PC.Increment();
		}	
	};
//==============================Definition of Instruction End=============================================================	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	CPU(Bus Bus)
	{
		this.Bus=Bus; //Use this "bus" will connect CPU to memory. empty bus-->load cpu and memory
		this.PC=new PC(this);
		int[] l={2,2,6}; //exactly 2 2 5 , but the I-bit will be the last to fill, expand it to 6 ensure the right location of parameters.
		int[] size={3,3,31};
		Command.put(1,LDR);	StrList.put("LDR", new Opcode(1,3,true, l,size));
		Command.put(2,STR);	StrList.put("STR", new Opcode(2,3,true, l,size));
		Command.put(3,LDA);	StrList.put("LDA", new Opcode(3,3,true, l,size));
		Command.put(41,LDX);StrList.put("LDX", new Opcode(41,3,true, l,size));
		Command.put(42,STX);StrList.put("STX", new Opcode(42,3,true, l,size));
		//String a;
		Command.put(4,AMR);StrList.put("AMR", new Opcode(4,3,true, l,size));
		Command.put(5,SMR);StrList.put("SMR",  new Opcode(5,3,true, l,size));
		int[]L2={2,8};//expand 5-->8 to make sure the hole is remained. 
		int[]size2={3,31};
		Command.put(6,AIR);StrList.put("AIR",  new Opcode(6,2,false, L2,size2));
		Command.put(7,SIR);StrList.put("SIR",  new Opcode(7,2,false, L2,size2));
	}
	
	private Memory Memory()
	{
		return this.Bus.AccessMemory();
	}
	
	//Attention!!!!!!!!!!: Cache Function should be imported in all memory access method when Cache is defined.
	public boolean checkIX(int IndexIX)
	{
		if(IndexIX<=0||IndexIX>3)
		{
		//	System.out.println("Address Index:  No Index.");
			return false;
		}
		return true;
	}
	
	
	//This read is a complicated version

	
	
	public short readMemoryByIX(int Address,int Method,int IndexIX) //THe difference between this and readEfficientMemory is, to use this method you need to fetch the IX outside. So if there are specific requirements about
	{																				//every step should be exactly and accurately 
		
		if(Method==CPU.Direct)		
		{	
			if(!checkIX(IndexIX))return this.Memory().fetch(Address);
			return this.Memory().fetch(Address+X[IndexIX].read()); 
		}	
		else if(Method==CPU.Indirect)			
		{	
			if(!checkIX(IndexIX)){
				Address=this.Memory().fetch(Address); //Get the EA
				return this.Memory().fetch(Address);	
			}
			Address=this.Memory().fetch(Address+X[IndexIX].read());
			return this.Memory().fetch(Address);
		}	
		else
		{
			//Throw something here. But according to the usage of this method it is highly impossible to go into this branch, as the value put here is only 1-bit length.
			return Short.MIN_VALUE;
			//Just remind you again this "MIN_VALUE" cannot be used to check if it is an invalid return.
			//But if I use things like "int" and control the range of that value in normal calculation limited in 16 bit, I can define an invalid one simply by set it to be larger than 16 bit.
		}	
	}
	
	
	public short readEffecientMemory(int Address,int Method,int IndexIX) //Not used but left for future usage.
	{							//Should throw something out, as there is always memory violation operations.
								//As Mentioned, IX[0] is a empty thing (which actually does not exist but always return 0), so we can directly use IndexIX as array Index. Not worrying about getting wrong values.
		//if IX==0, which means no indexing, the c(X[0]) yields 0, which will not affect anything.
		if(!checkIX(IndexIX))
		{
			return Short.MIN_VALUE;
		}
		
		if(Method==CPU.Direct)		
		{
			return this.Memory().fetch(Address+X[IndexIX].read());
		}	
		else if(Method==CPU.Indirect)
		{	Address=this.Memory().fetch(Address+X[IndexIX].read()); //Get the EA
			return this.Memory().fetch(Address);
		}	
		else
		{
			//Throw something here. But according to the usage of this method it is highly impossible to go into this branch, as the value put here is only 1-bit length.
			return Short.MIN_VALUE;
			//Just remind you again this "MIN_VALUE" cannot be used to check if it is an invalid return.
			//But if I use things like "int" and control the range of that value in normal calculation limited in 16 bit, I can define an invalid one simply by set it to be larger than 16 bit.
		}	
	}
	
	
	
	
	public boolean writeMemory(int Address,short value,int Method,int IndexIX)  //Write Memory Func for things such like UI stuff. Manipulate the memory Directly.
	{	System.out.println("UI Set Memory- Address:"+Address+". Data:"+(short)value+". I:"+Method+". IX:"+IndexIX);
		/*if(!checkIX(IndexIX))
		{	System.out.println("Debug: Failure of IX check");
			return false;
		}*/
	//Actually I and IndexIX here is no more than test-parameters.
		
		if(Method==CPU.Direct)		
		{	System.out.println("Debug: Direct Write");
			return this.Memory().write(Address+X[IndexIX].read(),value);
		}	
		else if(Method==CPU.Indirect)
			
		{	
			System.out.println("Debug: Indirect Write");
			Address=this.Memory().fetch(Address+X[IndexIX].read()); //Get the EA
			return this.Memory().write(Address,value);
		}	
		else
		{
			//Throw something here. But according to the usage of this method it is highly impossible to go into this branch, as the value put here is only 1-bit length.
			return false;
			//Just remind you again this "MIN_VALUE" cannot be used to check if it is an invalid return.
			//But if I use things like "int" and control the range of that value in normal calculation limited in 16 bit, I can define an invalid one simply by set it to be larger than 16 bit.
		}			
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public short readMemDirect(int Address) //The very basic Version of memory read.
	{
		return this.Memory().fetch(Address);
	}
	public int getEAByIX(int Address,int Method,int IndexIX) //THe difference between this and readEfficientMemory is, to use this method you need to fetch the IX outside. So if there are specific requirements about
	{																				//every step should be exactly and accurately 
		System.out.println(" Access Memory... LEA mode: Address"+Address+", IndexBit: "+Method+", IX value: "+IndexIX); //Long string. Not Good.
		if(Method==CPU.Direct)		
		{	
			if(!checkIX(IndexIX))
			{
				System.out.println("Direct Access without Indexing. EA is: "+Address);
				return Address;
			}
			
			//this.MAR.write((short) (Address+this.X[IndexIX].read()));  //load MAR is not necessary here.
			System.out.println("Value in X"+IndexIX+" is"+this.X[IndexIX].read());
			System.out.println("Direct Access with Indexing. EA is: "+this.MAR.read());
			return Address+this.X[IndexIX].read();    //Another MAR moving will be outside
		}	
		else if(Method==CPU.Indirect)			
		{	
			if(!checkIX(IndexIX)){
				this.MAR.write((short)Address);
				Address=(this.readMemDirect(this.MAR.read())); //Get the EA
				System.out.println("Indirect Access without Indexing. EA is: "+Address);
				return Address;	   //Another MAR moving will be outside
			}
			this.MAR.write((short) (Address+X[IndexIX].read()));
			Address=this.readMemDirect(this.MAR.read());
			System.out.println("Indirect Access with Indexing. EA is: "+Address);			
			return Address;     //Another MAR moving will be outside
		}	
		else
		{
			//Throw something here. But according to the usage of this method it is highly impossible to go into this branch, as the value put here is only 1-bit length.
			System.out.println("Debug: Violet Access method. Check IndexBit and IX.");
			return Short.MIN_VALUE;
			//Just remind you again this "MIN_VALUE" cannot be used to check if it is an invalid return.
			//But if I use things like "int" and control the range of that value in normal calculation limited in 16 bit, I can define an invalid one simply by set it to be larger than 16 bit.
		}	
	}
	
	
	public void ExecuteInstwithPC(short Instruct) //Instruction Executor
	{	int OPCODE= Instruct>>>10;
            System.out.println("Debug: Instruct: "+Integer.toBinaryString(Instruct));
		CodeExecutor Execution=(CPU.Command.get(OPCODE));
		if(Execution==null)
		{
			System.out.println("Call back not found. OPCode: "+OPCODE+" ");
			return;
		}
		Execution.Init(Instruct,this);
		Execution.ExecuteFunc();
		
	}
	

	
	
	
	public static void main(String[]args) //Test
	{ /*	Bus Bus=new Bus();
		CPU CPU=new CPU(Bus);
		Memory Memory=new Memory(Bus);
		Bus.Init(CPU, Memory);
	//	CPU.writeMemory(29, (short)1000, 0, 0);
		System.out.println(CPU.readEffecientMemory(29, 0,0));
		*/
	}
}





