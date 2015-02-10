package edu.gwu.computerarchitecture.entity;
/**
 * 该类实现了16位寄存器
 * @author Jinxin Ni
 * @version 1.0
 */
public class Register {
    private WordType content;
    
    public Register()
    {
        content=new WordType();
    }
    
    public WordType getRegister()
    {
        //return new WordType(content.getWord());
    	return this.content;
    }
    public boolean setRegister(WordType word)
    {
        if(content.setWord(word.getWord()))
            return true;
        else 
            return false;
    }

}
