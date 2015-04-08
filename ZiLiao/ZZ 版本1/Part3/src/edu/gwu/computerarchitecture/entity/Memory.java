/**
 * 
 */
package edu.gwu.computerarchitecture.entity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**璇ョ被瀹炵幇memory
 * @author Jinxin Ni
 * @version 1.0
 */

public class Memory {
	
	private int[] wordfile;
	public Register mdr;
	public Register mar;
	public Register mdr1;
	public Register mar1;
	public Register mdr2;
	public Register mar2;
	public Register mdr3;
	public Register mar3;
	public Register mdr4;
	public Register mar4;
	public Memory()
	{
		wordfile =new int [2048]; //memory涓瘡涓暣鏁板氨鏄竴涓獁ord
		mdr= new Register();
		mar= new Register();
		mdr1= new Register();
		mar1= new Register();
		mdr2= new Register();
		mar2= new Register();
		mdr3= new Register();
		mar3= new Register();
		mdr4= new Register();
		mar4= new Register();
	}
	
	public Memory(Register mdr, Register mar){
	    this();
	    this.mdr = mdr;
	    this.mar = mar;
	}
	
	/**
	 * 浠庡悕涓篺ilename鐨勬枃浠朵腑璇诲叆鎸囦护鍒癿emory鐨刬nt 鏁扮粍涓??
	 * @param filename
	 */
	public void loadFile(String filename)
	{
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try
		{
			String str="";
			fis= new FileInputStream(filename);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			int i=0;
			while ((str = br.readLine()) != null) 
			{
			    wordfile[2+i]=Integer.parseInt(str,2);
			    i++;
			    if(i>2047)
			    break;
			}
		 }
		catch 
		(
			FileNotFoundException e) 
		{
			System.out.println("鎵句笉鍒版寚瀹氭枃浠??");
		} 
		catch (IOException e) 
		{
			System.out.println("璇诲彇鏂囦欢澶辫触");
	    }
			
	}
	/**
	 * 鐜板疄memory涓殑姣忔潯鍐呭 璋冭瘯鐢??
	 */
	public void showMemory()
	{
		int i=0;
		for(i=0;i<2048;i++)
			System.out.println(wordfile[i]);
	}
	
    /**
     * 浠巑emory涓鍙栦竴涓獁ord鍒癿dr涓?? 鍦板潃鍦╩ar涓??
     */
    public void loadMemory()
    {
    	mdr.setRegister(new WordType(wordfile[mar.getRegister().getWord()]));
    }
    /**
     * 鍦╩emory瀛樹竴涓竴涓獁ord鍒癿dr涓?? 鍦板潃鍦╩ar涓??
     */
    public boolean StoreMemory()
    {
    	if(mdr.getRegister().getWord()<65536)
    	{
    	wordfile[mar.getRegister().getWord()]=mdr.getRegister().getWord();
    	    return true;
    	}
    	else
    		return false;
    }
    public int getMemory(int i){
        return wordfile[i];
    }
    public void setMemory(int address, int word)
    {
    	wordfile[address]=word;
    }
    
    public void bankLoadMemory(int add1,int addr2,int addr3, int addr4)
    {
    	mdr1.setRegister(new WordType(wordfile[mar1.getRegister().getWord()]));
    	mdr2.setRegister(new WordType(wordfile[mar2.getRegister().getWord()]));
    	mdr3.setRegister(new WordType(wordfile[mar3.getRegister().getWord()]));
    	mdr4.setRegister(new WordType(wordfile[mar.getRegister().getWord()]));    	
    }
    public boolean bankStoreMemory(int add1,int addr2,int addr3, int addr4)
    {
    	if(mdr1.getRegister().getWord()<65536 && mdr1.getRegister().getWord()<65536&&mdr1.getRegister().getWord()<65536 &&mdr1.getRegister().getWord()<65536)
    	{
    		wordfile[mar1.getRegister().getWord()]=mdr1.getRegister().getWord();
    		wordfile[mar2.getRegister().getWord()]=mdr2.getRegister().getWord();
    		wordfile[mar3.getRegister().getWord()]=mdr3.getRegister().getWord();
    		wordfile[mar4.getRegister().getWord()]=mdr4.getRegister().getWord();
    		return true;
    	}
    	else 
    		return false;
    }
    
}


