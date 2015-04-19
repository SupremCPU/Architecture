package companent;
import exception.FloatOverflowException;
 
import exception.FloatUnderflowException;
import basic_rules.*;
public class ShortFloat {
	public Word Stroke;
	boolean ZF=false;
	
	public final static int BIAS_JAVA=127;
	public final static int BIAS_SIM=63;	
	public final static int BIAS_OFF=64;
	
	public final static int MIN_EXP=1-BIAS_SIM;	
	
	public final static int MgnEnd=7;
	public final static int FRAC=MgnEnd+1;
	public final static int LEN=16;
	public final static int END=LEN-1;
	
	
	public final static int TYPE_NaN=3;
	public final static int TYPE_INFINITY=2;	
	public final static int TYPE_NORM=0;
	public final static int TYPE_DeNORM=1;	
	
	public float varF;
	
	private void checkZero(Word whichWord) //inln
	{
		int[] data=whichWord.data;
		int len=Math.min(END, data.length);
		this.ZF=true;
		for(int i=0;i<len;i++)
		{
			if(data[i]==1)
			{
				ZF=false;
				break;
			}
		}
		System.out.println("ZF "+ZF);
	}
	
	private static boolean checkZero(int[] data) //inln
	{
		
		int len=Math.min(16, data.length);
		boolean ZF=true;
		for(int i=0;i<len;i++)
		{
			if(data[i]>=1)
			{
				ZF=false;
				break;
			}
		}
		System.out.println("ZF "+ZF);
		return ZF;
	}
	
	ShortFloat(Word whichWord)
	{
		this.Stroke=new Word(whichWord.data.clone());
		checkZero(whichWord);
	}
	
	
	//FADD By Bit Operation
/*	public Word FADD(ShortFloat F1,ShortFloat F2) //Fr0  +Fr1(0)--> No really data manipulation.
	{ 	Word W1=F1.Stroke;
		Word W2=F2.Stroke;
		int[] data;
		if (F1.ZF)
		{
			return new Word(F2.Stroke.data.clone());
		}
		else if(F2.ZF)
		{
			return F1.Stroke;
		}	
		//Do FADD.
		//Check Exp
		int E1=W1.intValue(EXP,LEN);
		int E2=W2.intValue(EXP,LEN);	
		if(E1==E2) //M+M
		{
			data=new int[Word.SIZE];
			
		}
	}*/
	//intValue End = End index+1
	// Start= start index;
	public static boolean Assertion()
	{
		System.out.println("Non-standarlized Word of float");
		return false;
	}
	public static float toFloat(int[]dataA)
	{	
		//java.lang.Float.intBitsToFloat(bits)
		//java.lang.Integer.parseUnsignedInt)
		//int[]data=new int[dataA.length];
		if(checkZero(dataA))
		{
			System.out.println("Zero!!");
			return Float.intBitsToFloat(0);

		}
		
		int e1=Word.intValue(dataA, 1, MgnEnd+1);
		int e2=e1+BIAS_OFF; //Float
		int f=Word.intValue(dataA, FRAC, LEN);
		int f2=f<<15;
		
		int fr=f2+(e2<<23); // Damn java for sign bit! 
		System.out.println(Integer.toBinaryString(fr));
		System.out.println("dbg fr"+fr+" "+Float.intBitsToFloat(fr));
		
		if(dataA[0]==1)return Float.intBitsToFloat(fr);
		
		return -1*Float.intBitsToFloat(fr);

	}
	
	
	public static int[] toHalf(Float Source) throws Exception
	{
		int [] data=new int[Word.SIZE];
		int Bits=Float.floatToIntBits(Source);
		//System.out.println(Bits+"   ps: "+Float.floatToRawIntBits(Source));
		//System.out.println(Integer.toBinaryString(Bits));
		//System.out.println(Float.intBitsToFloat(1046046801));
		//80000000 for sign bit mask
		
		if(Float.floatToIntBits(Source)==0)
		{
			System.out.println("Zero!!");
			int[] lazy={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
			return lazy;

		}
		
		
		int frac=(Bits & 0x7FFFFF)>>>15;
		//System.out.println("frac "+frac);
		int exponent= ((Bits & 0x7F800000)>>>23);//origin exp   //-64;
		if(exponent-127 <MIN_EXP-1) //Underflow
		{
			throw new FloatUnderflowException("Underflow "+Source);
		}
		if(exponent-127==MIN_EXP-1) //Denorm
		{	//System.out.println("denorm can`t fix");
			exponent=0;
			frac= 0x80+(frac>>>1);
		}
		else if(exponent-127==MIN_EXP)
		{	//System.out.println("denorm fix");
			exponent=2;
			frac= 0x80+(frac>>>1);
		}
		else{
		//	System.out.println("norm");
			exponent=exponent-64;	
		}
	
	//	System.out.println("ex "+exponent);
		int S= (Bits & 0x80000000)>>31;     //logical!!!
		//System.out.println("S "+S+" ps"+Integer.toBinaryString(S));
		 S= (Source>(-0.))? 0:1;
		/*if(S!=debug)
		{
			System.out.println("debug!! check conversion- signbit");
			
			}*/
		int Val=frac+(exponent<<8)+(S<<15);
	//	System.out.println("V "+Val);
		Word.setIntValueForFloat(data, Val);
		if(exponent>=127)
		{	
			if(S==0) throw new FloatOverflowException("Overflow "+Source);
			throw new FloatUnderflowException("Underflow "+Source);
		}
		
		return data;
	}
	
	public static Word FADD(ShortFloat F1,ShortFloat F2) throws Exception //Fr0  +Fr1(0)--> No really data manipulation.
	{ 	Word W1=F1.Stroke;
		Word W2=F2.Stroke;
		int[] data;
		assert ((W1.Float && W2.Float)||Assertion()) :  "Assert Failure of floating points notation";
		if(!(W1.Float && W2.Float)||Assertion())
		{;}
		if (F1.ZF)
		{
			return new Word(F2.Stroke.data.clone());
		}
		else if(F2.ZF)
		{
			return F1.Stroke; //Overwrite, so no new value returned.
		}	
		int[] data1=W1.data;
		int[] data2=W2.data;
		float f1=toFloat(data1);
		float f2=toFloat(data2);
		float f3=f1+f2;
		System.out.println(toFloat(data1)+"+"+toFloat(data2)+" ="+f3);
		data=toHalf(f3);
		System.out.println("Test Float"+toFloat(data));
		return new Word(data,false);
		
	}
	
	public static Word FSUB(ShortFloat F1,ShortFloat F2) throws Exception //Fr0  +Fr1(0)--> No really data manipulation.
	{ 	Word W1=F1.Stroke;
		Word W2=F2.Stroke;
		int[] data;
		if(!(W1.Float && W2.Float)||Assertion())
		{;}
		if (F1.ZF) //negation
		{	data=F2.Stroke.data.clone();
			data[0]=1;
			return new Word(data,Word.FOR_FLOAT);
		}
		else if(F2.ZF)
		{
			return F1.Stroke; //Overwrite, so no new value returned.
		}	
		int[] data1=W1.data;
		int[] data2=W2.data;
		float f1=toFloat(data1);
		float f2=toFloat(data2);
		float f3=f1-f2;
		data=toHalf(f3);
		return new Word(data,Word.FOR_FLOAT);
		
	}
	

	
	public static Word FADD(int[] data1,int[] data2) throws Exception //Fr0  +Fr1(0)--> No really data manipulation.
	{ 	//Word W1=F1.Stroke;
		//Word W2=F2.Stroke;
		int[] data;
		/*assert ((W1.Float && W2.Float)||Assertion()) :  "Assert Failure of floating points notation";
		if(!(W1.Float && W2.Float)||Assertion())
		{;}
		if (F1.ZF)
		{
			return new Word(F2.Stroke.data.clone());
		}
		else if(F2.ZF)
		{
			return F1.Stroke; //Overwrite, so no new value returned.
		}	
		int[] data1=W1.data;
		int[] data2=W2.data;*/
		float f1=toFloat(data1);
		float f2=toFloat(data2);
		float f3=f1+f2;
		System.out.println(toFloat(data1)+"+"+toFloat(data2)+" ="+f3);
		data=toHalf(f3);
		System.out.println("Test Float"+toFloat(data));
		return new Word(data,Word.FOR_FLOAT);
		
	}
	
	public static Word FSUB(int[] data1,int[] data2) throws Exception //Fr0  +Fr1(0)--> No really data manipulation.
	{ 	//Word W1=F1.Stroke;
		//Word W2=F2.Stroke;
		int[] data;
		/*if(!(W1.Float && W2.Float)||Assertion())
		{;}
		if (F1.ZF) //negation
		{	data=F2.Stroke.data.clone();
			data[0]=1;
			return new Word(data);
		}
		else if(F2.ZF)
		{
			return F1.Stroke; //Overwrite, so no new value returned.
		}	
		int[] data1=W1.data;
		int[] data2=W2.data;*/
		float f1=toFloat(data1);
		float f2=toFloat(data2);
		float f3=f1-f2;
		data=toHalf(f3);
		System.out.println("Test Float W[][]"+toFloat(data));
		return new Word(data,false);
		
	}
	
	public static int[] FADD(int[] data1,int[] data2,int Decorate) throws Exception //Fr0  +Fr1(0)--> No really data manipulation.
	{ 	//Word W1=F1.Stroke;
		//Word W2=F2.Stroke;
		int[] data;
		/*assert ((W1.Float && W2.Float)||Assertion()) :  "Assert Failure of floating points notation";
		if(!(W1.Float && W2.Float)||Assertion())
		{;}
		if (F1.ZF)
		{
			return new Word(F2.Stroke.data.clone());
		}
		else if(F2.ZF)
		{
			return F1.Stroke; //Overwrite, so no new value returned.
		}	
		int[] data1=W1.data;
		int[] data2=W2.data;*/
		float f1=toFloat(data1);
		float f2=toFloat(data2);
		float f3=f1+f2;
		System.out.println(toFloat(data1)+"+"+toFloat(data2)+" ="+f3);
		data=toHalf(f3);
		System.out.println("Test Float[][][]"+toFloat(data));
		return data;
		
	}
	
	public static int[] FSUB(int[] data1,int[] data2,int Decorate) throws Exception //Fr0  +Fr1(0)--> No really data manipulation.
	{ 	//Word W1=F1.Stroke;
		//Word W2=F2.Stroke;
		int[] data;
		/*if(!(W1.Float && W2.Float)||Assertion())
		{;}
		if (F1.ZF) //negation
		{	data=F2.Stroke.data.clone();
			data[0]=1;
			return new Word(data);
		}
		else if(F2.ZF)
		{
			return F1.Stroke; //Overwrite, so no new value returned.
		}	
		int[] data1=W1.data;
		int[] data2=W2.data;*/
		float f1=toFloat(data1);
		float f2=toFloat(data2);
		float f3=f1-f2;
		data=toHalf(f3);
		return data;
		
	}
	public static void main(String[]args) throws Exception
	{ boolean dbg=true;
		
		Float a=0.0f; //5.212412f;
		Float b=2.0222f;
		Float c=-5.555f;
		float d=5.555f;
		System.out.println(a);
	//	System.out.println(Integer.toBinaryString((Float.floatToIntBits(c))));
	//	System.out.println(Integer.toBinaryString((Float.floatToIntBits(d))));
		int[] data=toHalf(a);
		if(dbg){for(int i=0;i<Word.SIZE;i++)
		{
			System.out.print(data[i]+" ");
		}
		System.out.println();
		}
		
		System.out.println("a: "+toFloat(data));
		System.out.println("a:array "+Word.intValueOfHalfFloat(data));
		int[]data2=toHalf(b);
		System.out.println("b: "+toFloat(data2));
		
		Word W1=new Word(data,false);
		Word W2=new Word(data2,false);
		
		ShortFloat F1=new ShortFloat(W1);
		ShortFloat F2=new ShortFloat(W2);		
		 Word W3=FADD(F1,F2);
		 int[] f3=(W3.data).clone();
		 System.out.println("result "+toFloat(f3));
		 
		 Word W4=FSUB(F1,F2);
		 int[] f4=(W4.data).clone();
		 System.out.println("result "+toFloat(f4));
	}
}
