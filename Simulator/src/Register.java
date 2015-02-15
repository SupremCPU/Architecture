
public class Register {
	public String Name;
	//A super class to manage all different classes of registers.
	//Distinguish them by name.
	public short DataWord;
	
	public CPU Father;

	Register(CPU whichCPU)
	{
		this.Father=whichCPU;
		this.DataWord=0;
	}
	public short read()
	{
		return DataWord;
	}
	public void write(short Data)
	{
		this.DataWord=Data;
	}
	public byte[] toBytes()
	{	byte high=(byte)(DataWord>>>8);   
		byte low= (byte)((DataWord<<8)>>>8);
		byte[] B={high,low};
		return B;
	}
	public String getRegisterName()
	{
		return this.Name;
	}
	public static void main(String[]args)
	{
		Register here=new Register(null);
		here.DataWord=2<<9;
		System.out.println(here.DataWord+" "+(byte)here.DataWord+" "+(byte)(here.DataWord>>>8));
		
	}
}
