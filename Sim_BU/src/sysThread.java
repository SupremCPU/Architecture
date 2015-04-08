
public class sysThread extends Thread {
	public CPU Father;
	private int Sleep;
	public void runSingle()
	{
		Father.ExecuteInstwithPC(Father.IR.read());//Execute it and PC++	
	}
	public void run() //For now-->Only for single step. Add some parameter to fit normal run process.
	
	{	while(true)  //For now. 1 Time Only
	//System.out.println("run");	
            {
		
				try
				{
					//Father.MAR.write((short) Father.PC.get()); //Send PC to MAR;
					//Father.MBR.write(Father.readMemDirect(Father.MAR.read())); //MBR carries instruction.
					//Father.IR.write(Father.MBR.read());
					this.runSingle();
					sleep(Sleep);
				} 
				catch (InterruptedException e) {
					
					e.printStackTrace();
				}
		}	
             
	}
	
	sysThread(CPU cpu)
	{
		this.Father=cpu;
		Sleep=this.Father.clockFreq;
	}
	
	public static void main(String[] args)
	{
		//sysThread test=new sysThread();
		//test.start();
	}
		
}
