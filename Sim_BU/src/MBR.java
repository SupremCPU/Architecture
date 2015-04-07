
public class MBR extends Register {
	
	
		MBR(CPU CPU)
		{
			super(CPU);
		}
		
		
		//Actually it is exactly Register type. Distinguish them for "Register" should never be pass through each other in coding, due to data transfer limitation (I mean cannot direct transform) of different registers.
}
