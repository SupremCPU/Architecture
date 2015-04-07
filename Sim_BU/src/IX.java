
public class IX extends DataRegister{
	private boolean ReadOnly=false;
	IX(int index,CPU CPU,boolean RO)
	{
		super(index,CPU);
		this.ReadOnly=RO;
		this.DataMat[0]=0;
		this.DataMat[1]=0;
		this.DataWord=0;
	}
	@Override
	public void writeHigh(byte data)
	{	if(ReadOnly)return;
		this.DataMat[0]=data;
		this.Sync();
	}
	@Override
	public void writeLow(byte data)
	{		if(ReadOnly)return;
		this.DataMat[1]=data;
		this.Sync();	
	}
	@Override
	public void writePos(byte data,int where)
	{		if(ReadOnly)return;
		if(where>HIGH||where<LOW)
		{
			where=LOW;
		}
		DataMat[where]=data;
		this.Sync();
	}
	@Override
	public void writeByte(byte[] B)
	{	if(ReadOnly)return;
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
	{	if(ReadOnly)return;
		this.DataWord=Data;
		try {
			Thread.sleep(Father.clockFreq);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.SyncB();
		
	}
	
	
}
