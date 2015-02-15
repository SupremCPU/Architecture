
public class Message {
	public static int Miscellaneous=1;
	public static int Normal=2;	
	 
	public static int Instr=0;
	public static int Signal=1;
	public static int Others=2;
	
	public int Type=Instr;     //Default;
	public int Property=Normal;
	Message()
	{
		
	}
	Message(int type, int property)
	{
		this.Type=type;
		this.Property=property;
	}
}
