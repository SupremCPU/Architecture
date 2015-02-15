
public class Memory {
	public static int Range=4096;
	private int Size=2048;
	private int bottomReserve=6; //0~5 is reserved
	private short[] Word;  //Word Addressable, thus the memory will fetch the complete word from memory.  
	private Bus Bus;
	//A static array is enough.
	Memory(int load,Bus Bus)
	{
		if(load<=0 ||load>4096)load=2048; //If invalid size, then let it be default value.
		this.Size=load;
		Word=new short[4096]; //this.Size is the throttle of memory control which simulate the current maximum address range (within the addressable range).
		this.Bus=Bus;
	}
	Memory(Bus Bus)
	{
		Word=new short[4096];	
		this.Bus=Bus;
	}
	CPU CPU() //if need to trace back something, unfortunately.
	{
		return this.Bus.CPU();
	}
	
	
	public void adjustSize(int newSize) //Expand ||Shrink but keep the original data.
	{	if(newSize<=0||newSize>4096||newSize==this.Size)return;
		if(newSize<this.Size)  //If Shrink then the data out of range must be erased. Assume there is a backup mechanism which allows old data can be kept as much as possible.
		{
			for(this.Size--;this.Size==newSize;this.Size--) //use this.Size as the counter of loop which is also the index of array, so -1 at the beginning and -1 each step, but recover by +1 at last.
			{
				Word[this.Size]=0;
			}
			this.Size++;
		}
		else
		{
			this.Size=newSize;
		}	
	}
	
	public void addrFail()
	{
		//Other Implementation
		System.out.println("Alert: Addr Failure: out of range.");
	}
	public void dataAddrFail()
	{
		//Other Implementation
		System.out.println("Alert: Addr Failure: Violet Access.");		
	}	
	
	public boolean addrCheck(int address) //if valid in memory. Here the address is from 0~Size-1
	{
		if(address>=this.Size||address<0) //invalid. PS: Here the address is from 0~Size-1;  
		{
			//Some other implementation like a throw or a print log.
			//Otherwise I will use things like return !(address>=this.Size||address<0).
			this.addrFail();
			return false;
		}
		return true;
	}
	public boolean dataAddrCheck(int address) //not only check if valid in memory but also accessible.
	{
		if(!addrCheck(address)||address<this.bottomReserve)
		{
			this.dataAddrFail();
			return false;
		}
		return true;
	}
		
	
	public short fetch(int address) //Force to fetch everything including protected area.
	{
		if(addrCheck(address))
		{
			return Word[address];
		}
		//Throw some exception here please.
		return 0;
	}
	public short fetchData(int address) //only fetch "Data" excluding the reserved area.
	{			
				//Whether it is valid address is checked here but whether it is indirect/direct is done in class CPU.
		if(dataAddrCheck(address)) //Throws something here.
		{
			return Word[address];
		}
		//Throws something here.
		return 0; //Just remind that this 0 cannot be used to check if it is a failure. Things must be thrown before, like exception. But the program should not terminate here unless it is fatal.
	}
	public boolean write(int address,short value)
	{
		if(addrCheck(address))
		{
			 Word[address]=value;
			 return true;
		}		
		System.out.println("Debug: Class Memory- write failure");
		return false;
	}
	public boolean writeData(int address,short value) throws InterruptedException
	{
		if(dataAddrCheck(address))
		{
			 Word[address]=value;
			 Thread.sleep(this.CPU().clockFreq);
			 return true;
		}		
		return false;		
	}	
	
}
