
package edu.gwu.computerarchitecture.cache;

import edu.gwu.computerarchitecture.cache.Block;

/**
 * @author Administrator
 *
 */
public class Set {
    private Block[] blockSet;
    private int[] Lru;
    
    public Set()
    {
   	 blockSet=new Block[4];
   	 for(int i=0;i<4;i++)
   	 {
   		 blockSet[i]=new Block();
   	 }
   	 Lru= new int[4];
   	 for(int i=0;i<4;i++)
   	 {
   		Lru[i]=i;
   	 }
    }
    
    public int findBlock(int targetTag)
    {
   	 int i;
   	 for(i=0;i<4;i++)
   	 {
   		 if(blockSet[i].compareTag(targetTag))
   		     			 break;
   	 } 
   	 return i;
    }
    public boolean existBlock(int targetTag)
    {
   	 int i;
   	 for(i=0;i<4;i++)
   	 {
   		 if(blockSet[i].compareTag(targetTag))
   		     			 return true;
   	 } 
   	 return false;
    }
    public Block getSet(int blockNum)
    {
   	  int temp=Lru[blockNum];
         Lru[blockNum]=0;
         for(int i=0;i<4;i++)
         {
            if(Lru[i]<temp&&i!=blockNum)
           	 Lru[i]=Lru[i]+1;
         }
         return blockSet[blockNum];
    }
    
    public void setSet(int blockNum, Block b)
    {
  	     int temp=Lru[blockNum];
        Lru[blockNum]=0;
        for(int i=0;i<4;i++)
        {
          if(Lru[i]<temp&&i!=blockNum)
       	  Lru[i]=Lru[i]+1;
        }
   	 blockSet[blockNum].setBlock(b.getBlock());
   	 blockSet[blockNum].setTag(b.getTag());
   	 blockSet[blockNum].setDirty(true);
    }
    
    public int findLru()
    {
   	 int i;
   	 for(i=0;i<4;i++)
   	 {
   		 if(Lru[i]==3)
   		 break;
   	 }
   	 return i;
    }
    
}
