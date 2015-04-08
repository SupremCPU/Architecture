/**
 * 
 */
package edu.gwu.computerarchitecture.cache;

import edu.gwu.computerarchitecture.entity.WordType;;

/**
 * @author Administrator
 *
 */
public class Block {
	private int tag;
	private boolean dirty;
	private WordType[] content;
	
	public Block()
	{
		tag=0;
		dirty=false;
		content=new WordType[4];
		for(int i=0;i<4;i++)
		{
			content[i]=new WordType();
		}
	}
	
    public WordType[] getBlock()
    {
    	return content;
    }
    
    public void setBlock(WordType[] newContent)
    {	
    	for(int i=0;i<4;i++)
    	{
    		content[i].setWord(newContent[i].getWord());
    	}
    }
    
    
    public boolean compareTag(int tag)
    {
    	return(this.tag==tag);
    }
    
    public void setTag (int tag)
    {
    	this.tag=tag;
    }
    
    public int getTag()
    {
    	return this.tag;
    }
    
    public boolean isDirty()
    {
    	return dirty;
    }
    
    public void setDirty(boolean isDirty)
    {
    	dirty=isDirty;
    }
}
