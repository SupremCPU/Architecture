
public class Bus {
	//According to professor, bus and any mechanism related to it is not necessary to simulate.
	// But as long as the structure of code is designed, things here are remained as an interface of object mapping.
	//No real function is done in this class.
	
	
	
	private CPU CPU;
	private Memory Memory;
	//private Disk Disk;
	Bus()
	{
		
	}
	public void Init(CPU CPU,Memory Memory) //Important
	{
		this.CPU=CPU;
		this.Memory=Memory;
	}
	
	
	public Memory AccessMemory()
	{
		return this.Memory;
	}
	public CPU CPU()
	{
		return this.CPU;
	}
}
