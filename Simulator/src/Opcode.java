
public class Opcode {
public int Opcode=0;
public int ParaNum=0;
public boolean takeI;
public int[] BitLength;//That is actually used for locating not for range checking/
public int[] Size; //here is the range check.  <= check of no-more-than 

//A data package for parsing. 

//If there are some bits IN¡¡THE MIDDLE not used/ignored,  simply expand the right side bit`s length data might work to compensate by treating the "hole" as one part of this parameter.

Opcode(int Op,int ParaNum,boolean takeI,int[] Length,int[]Size)
{
this.Opcode=Op;
this.ParaNum=ParaNum;
this.takeI=takeI;
this.BitLength=Length;
this.Size=Size;
}
}
