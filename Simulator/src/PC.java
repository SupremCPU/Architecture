
public class PC {
	private short PC=0; //Use integer so the comparison will not be related with overflow stuffs.
	private CPU Father;
	private static int BitLimit=4096;
	PC(CPU CPU)
	{
		this.Father=CPU;
	}
	public void set(short PC)
	{
		if(PC>=0 && PC<BitLimit)
		{
			this.PC=PC; //for set and get it does not check current MemorySize but do check the 12 bits limit
			//Detail checks will be built inside register or cpu class.
		}
	}
	public short get()
	{
		return this.PC;
	}
	public void Increment()
	{
		this.PC++;
		if(PC<0)
		{	PC=6;
			System.out.println("PC overflowed");
		}
	}
	public CPU CPU()
	{
		return Father;
	}
}
