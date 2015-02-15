
public class ArithRegister extends DataRegister {

	
	
	public StatusFlag  Adder(short Origin,short Value) //As it is accumulate, a=a+?; no way to overflow from negative to max_short.
	{	this.DataWord=Origin;
		this.SyncB();
		short Temp=Origin; //A temp int for accumulation.
		short Result=(short)(Origin+Value);
		int Z=0;/////////////////////////////////////////Actually I have question about those details
		
		int S=(Result>>>15);
		int O=((DataMat[0]>>>7==0 && S==1)||(DataMat[0]>>>7==1 && S==0))?1:0;
		
		int C= ((Temp & Value)!=0)?1:0; //If there are "1" in same position between Temp and Value, there must be carry.
		
		if(Result==0)
		{
			Z=1;//SetZeroFlag
		}
		this.DataWord=Result;
		this.SyncB();
	//	System.out.println(Z+" "+C+" "+S+" "+O);
		
		return new StatusFlag((byte)Z,(byte)C,(byte)S,(byte)O);
	}	
	
	
	
	
	public StatusFlag  Sub(short Origin,short Value) //As it is accumulate, a=a+?; no way to overflow from negative to max_short.
	{	this.DataWord=Origin;
		this.SyncB();
		short Temp=Origin; //A temp int for accumulation.
		Value= (short) ~Value;
		short Result=(short)(Origin+Value+1);
		int Z=0;/////////////////////////////////////////Actually I have question about those details
		
		int S=(Result>>>15);
		int O=((DataMat[0]>>>7==0 && S==1)||(DataMat[0]>>>7==1 && S==0))?1:0;
		
		int C= ((Temp & Value)!=0)?0:1; // Just exactly reverse to "Adder" 
		
		if(Result==0)
		{
			Z=1;//SetZeroFlag
		}
		this.DataWord=Result;
		this.SyncB();
		//System.out.println(Z+" "+C+" "+S+" "+O);
		
		return new StatusFlag((byte)Z,(byte)C,(byte)S,(byte)O);
	}	
	
	
ArithRegister(int i,CPU CPU)
{
	super(i,CPU);
	super.Name="A";
}
	public static void main(String[]args)
	{
		ArithRegister t=new ArithRegister(1,null);
		StatusFlag test=t.Adder((short)3, (short)1);
		System.out.println(t.DataWord);
		System.out.println("abcd"+"abcd".indexOf("a"));
		System.out.println(test.getZeroFlag()+" "+test.getCarryFlag()+" "+test.getSignFlag()+" "+test.getOverflowFlag());
	
	}
}
