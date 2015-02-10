package edu.gwu.computerarchitecture.entity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**Memory
 * @author Jinxin Ni
 * @version 1.0
 */

public class Memory {
	
	private int[] wordfile;
	public Register mdr;
	public Register mar;
	public Memory()
	{
		wordfile =new int [2048]; 

		mdr= new Register();
		mar= new Register();
	}
	
	/**
	 * read fileto memory
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
			System.out.println("Can't find file");
		} 
		catch (IOException e) 
		{
			System.out.println("Error");
	    }
			
	}
	/**
	 * print memory
	 */
	public void showMemory()
	{
		int i=0;
		for(i=0;i<2048;i++)
			System.out.println(wordfile[i]);
	}
	
    /**
     * read from memory, and save it to mdr
     */
    public void loadMemory()
    {
    	mdr.setRegister(new WordType(wordfile[mar.getRegister().getWord()]));
    }
    /**
     * store mdr to memory
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
}


