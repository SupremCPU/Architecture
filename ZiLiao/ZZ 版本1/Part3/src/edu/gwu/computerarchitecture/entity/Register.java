package edu.gwu.computerarchitecture.entity;
/**
 * Realize 16bit register
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
        return content;
    }
    public boolean setRegister(WordType word)
    {
        if(content.setWord(word.getWord()))
            return true;
        else 
            return false;
    }

}
