
public class StatusFlag {
	/*private byte Zero=0;
	private byte Carry=0;
	private byte Sign=0;
	private byte Overflow=0;  */
	private byte[] Flags={0,0,0,0};
	public static int ZF=0;
	public static int CF=1;
	public static int SF=2;
	public static int OF=3;	
	StatusFlag(byte Z, byte C, byte S, byte O)
	{
		  Flags[ZF]=Z;
		  Flags[CF]=C;
		  Flags[SF]=S;
		  Flags[OF]=O;		
	}
	
	public byte getZeroFlag()
	{
		return Flags[ZF];
	}
	public byte getCarryFlag()
	{
		return Flags[CF];
	}	
	public byte getSignFlag()
	{
		return Flags[SF];
	}	
	public byte getOverflowFlag()
	{
		return Flags[OF];
	}	
	public void Zero(byte value)  //Well I agree that duplicated code are stupid things.
	{
		value=(byte)(value>0?1:0);
		Flags[ZF]=value;
	}
	public void Carry(byte value) //But maybe the name is good and apparent enough to forget about that evil.
	{
		value=(byte)(value>0?1:0);
		Flags[CF]=value;
	}
	
	public void Sign(byte value)
	{
		value=(byte)(value>0?1:0);
		Flags[OF]=value;
	}
	public void Overflow(byte value)  //Still hate it.
	{
		value=(byte)(value>0?1:0);
		Flags[OF]=value;
	}
	
	public boolean checkValid(int whichFlag)
	{
		if(whichFlag<0 ||whichFlag>3)
		{
			System.out.print("Dbg: Bad value for set flag "+whichFlag);
			return false;
		}
		return true;
	}
	
	
	public void SetFlag(byte value, int whichFlag)  
	{	if(checkValid(whichFlag))
		{
			value=(byte)(value>0?1:0);
			Flags[whichFlag]=value;
		}
	}
	
	public void Switch(int whichFlag) //I am done code duplicating !
	{
		if(checkValid(whichFlag))
		{
			Flags[whichFlag]-=1;
			Flags[whichFlag]*=-1;			// (0-1)*-1=1;  (1-1)*-1=0
		}		
	}
	public byte[] ZCSO()
	{	//byte[] Pack=Flags.clone(); //I am thinking about prevent object duplication.
		return Flags;
	}
	
}
