package edu.gwu.computerarchitecture.entity;
/**
 * define 16bit word type
 * @author Jinxin Ni
 * @version 1.0
 */
public class WordType {
	private short word;
	
	public WordType()
	{
		word=0;
	}
	public WordType(int word)
	{
		this.word=(short)word;
	}
	public void showInBinary()
	{
		System.out.println(Integer.toBinaryString(word));
	}
	public short getWord()
	{
		return word;
	}
	public boolean setWord(int word)
	{
		if(word<65536 & word>-65536)
		{
		this.word=(short)word;
		return true;
		}
		else
			return false;
	}
	public int getOpcodeBits()
	{
		int res;
		res=(int) (word & Integer.parseInt(new String("1111110000000000"),2));
		res=(int) (res >>10);
		return res;
	}
	public int getI()
	{
		int res;
		res=(int) (word & Integer.parseInt(new String("0000001000000000"),2));
		res=(int) (res >>9);
		return res;
	}
	public int getIX()
	{
		int res;
		res=(int) (word & Integer.parseInt(new String("0000000100000000"),2));
		res=(int) (res >>8);
		return res;
	}
	public int getR()
	{
		int res;
		res=(int) (word & Integer.parseInt(new String("0000000011000000"),2));
		res=(int) (res >>6);
		return res;
	}
	public int getAddress()
	{
		int res;
		res=(int) (word & Integer.parseInt(new String("0000000000111111"),2));
		return res;
	}
}

