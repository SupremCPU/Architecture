
public interface CodeExecutor {
	
	public boolean Decode=true;
	public boolean Execute=true;
	public boolean Memory=true;
	public boolean WriteBack=true;
	
	public void Fetch(); //get target address/register/.etc
	public void Decode(); //get value
	public void Execute();//any operation 
	public void Memory(); //to memory
	public void WriteBack(); //to Register
	public void ExecuteFunc();
	
	public void Init(int IX1,int IX2,int I1,int Mem1,CPU CPU); //This is used to initialize the interface member manually, but not it is oriented to code testing. 
	public void Init(short Instruct,CPU CPU);

}
