
public class DataRegister extends Register  {
	private int Index=0;
	public final static int Size=16;
	public final static int HIGH=0;
	public final static int LOW=1;
	public byte[] DataMat=new byte[Size/CPU.byteSize]; //Split it into 2 registers. But cause inefficiency if the 1-byte register is not commonly used.
			
	//May be it could be useful like Rax-Eax-ax stuffs. 
													//Actually according to the definitions in the superclass, it is no more than a duplicate:)
													
														
				
	DataRegister(int index,CPU CPU)
	{	super(CPU);
		super.Name="R"; // Name as the main category of registers, then index.    //.concat(Integer.toString(Index));
		this.Index=index;
		
		this.DataMat[0]=0;
		this.DataMat[1]=0;
	}
	public int getIndex()
	{
		return Index;
	}
	
	//==================Operation for matrix data============================
	public byte readHigh()
	{
		return DataMat[0];
	}
	public byte readLow()
	{
		return DataMat[1];
	}
	public byte readPos(int where)
	{	if(where>HIGH||where<LOW)
		{
			where=LOW;
		}
		return DataMat[where];
	}
	public byte[] readByte()       
	{	byte[] B=new byte[2];
		B[0]=DataMat[0];
		B[1]=DataMat[1];
		return B;
	}
	//================================================================
	public void Sync()
	{
		this.DataWord= (short) ((((short)(DataMat[0]))<<8)+(short)(DataMat[1]));
	}
	public void SyncB()
	{
		this.DataMat[0]=(byte)(this.DataWord>>>8);
		this.DataMat[1]=(byte)(this.DataWord<<8>>>8);
	}
	public void writeHigh(byte data)
	{
		this.DataMat[0]=data;
		this.Sync();
	}
	public void writeLow(byte data)
	{
		this.DataMat[1]=data;
		this.Sync();	
	}
	public void writePos(byte data,int where)
	{	if(where>HIGH||where<LOW)
		{
			where=LOW;
		}
		DataMat[where]=data;
		this.Sync();
	}
	public void writeByte(byte[] B)
	{
		if(B.length<2)
		{	System.out.println("Debug: Insufficient Length of writeByte in GPR "+Name);
			return;
		}
		DataMat[0]=B[0];
		DataMat[1]=B[1];
		this.Sync();
	}
	@Override
	public void write(short Data)  
	{
		this.DataWord=Data;
		try {
			Thread.sleep(this.Father.clockFreq);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.SyncB();
		
	}
	public void Shuffle()
	{	byte t=DataMat[0];
		DataMat[0]=DataMat[1];
		DataMat[1]=t;
		this.Sync();
	}
	public void AccumFailure()
	{
		System.out.println("Accumulate invalid value");
	}
	public StatusFlag Accumulate(short Value) //As it is accumulate, a=a+?; no way to overflow from negative to max_short.
	{	if(Value<0)
		{
			AccumFailure();
			return null;  //Check null.
		}
		short Temp=(short)(DataMat[0]<<8+DataMat[1]); //A temp int for accumulation.
		short Result=(short)(Temp+Value);
		int Z=0;
		
		int S=(Result>>>15);
		int O=(DataMat[0]>>>7==0 && S==1)?1:0;
		
		int C= ((Temp & Value)!=0)?1:0; //If there are "1" in same position between Temp and Value, there must be carry.
		
		if(Result==0)
		{
			Z=1;//SetZeroFlag
		}
		this.DataWord=Result;
		this.SyncB();
		
		
		return new StatusFlag((byte)Z,(byte)C,(byte)S,(byte)O);
	}
	
	
	
	
	
	
	
	

	
	
	
	
	public static void main(String[] args)
	{
		int i=2<<16;
		short j=(short)((short)i+(short)0);
		byte a=1;
		short j2=(short)(a<<9+a);
		System.out.println(i+" "+j+" "+j2+" "+((-1)>>>31));
	}
	
}
