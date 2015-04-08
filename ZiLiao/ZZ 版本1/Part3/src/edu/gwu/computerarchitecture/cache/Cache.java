/**
 * 
 */
package edu.gwu.computerarchitecture.cache;

import edu.gwu.computerarchitecture.cache.Block;
import edu.gwu.computerarchitecture.entity.Memory;
import edu.gwu.computerarchitecture.cache.Set;
import edu.gwu.computerarchitecture.entity.WordType;

/**
 * @author Administrator
 *
 */
public class Cache {
	
	private Set[] setArray;
	private Memory memory;
    public Cache()
    {
    	setArray =new Set[32];
    	for(int i=0;i<64;i++)
    		setArray[i]=new Set();
    }
    
    public WordType readCache(int address)
    {
    	int offset=(int)(address & Integer.parseInt(new String("0000000000000011"),2));
    	int index=(int)(address & Integer.parseInt(new String("0000000001111100"),2));
    	index=index >>2;
    	int tag=(int)(address & Integer.parseInt(new String("1111111110000000"),2));
    	int blockaddress=(int)(address & Integer.parseInt(new String("1111111111111100"),2));
    	tag=tag>>4;
    	if(setArray[index].existBlock(tag))
    	{
    		int blockNum= setArray[index].findBlock(tag);
    		return setArray[index].getSet(blockNum).getBlock()[offset];
    	} 
    	else
    	{
    		int lru=setArray[index].findLru();
    		Block lruBlock= setArray[index].getSet(lru);
    		lruBlock.setTag(tag);
    		for(int i=0;i<3;i++)
    		{
    			lruBlock.getBlock()[i].setWord(memory.getMemory(blockaddress+i));
    		}
    		return lruBlock.getBlock()[offset];
    	}
    }
    public void writeCache(int address, WordType word)
    {
    	int offset=(int)(address & Integer.parseInt(new String("0000000000000011"),2));
    	int index=(int)(address & Integer.parseInt(new String("0000000001111100"),2));
    	index=index >>2;
    	int tag=(int)(address & Integer.parseInt(new String("1111111110000000"),2));
    	tag=tag>>4;
    	int blockaddress=(int)(address & Integer.parseInt(new String("1111111111111100"),2));
    	if(setArray[index].existBlock(tag))
    	{
    		int blockNum= setArray[index].findBlock(tag);
    		setArray[index].getSet(blockNum).getBlock()[offset].setWord(word.getWord());
            memory.setMemory(address, word.getWord());
 
    	} 
    	else
    	{
    		int lru=setArray[index].findLru();
    		Block lruBlock= setArray[index].getSet(lru);
    		lruBlock.setTag(tag);
    		for(int i=0;i<3;i++)
    		{
    			lruBlock.getBlock()[i].setWord(memory.getMemory(blockaddress+i));
    		}
       
            memory.setMemory(address, word.getWord());
    	}
    }
    
}