package edu.gwu.computerarchitecture.entity;

/**
 * 
 * implement the 32 bits register
 *
 * <p>detailed comment
 * @author Bin Zhang 2013年10月15日
 * @see
 * @since 1.0
 */
public class Register32Bits
{
    private int content;
    
    /**
     * 
     */
    public Register32Bits(){
        content = 0;
    }
    
    /**
     * set the register content
     * 
     * @param data
     * @return
     */
    public void setRegister(int data){
            content = data;
    }
    
    /**
     * get the register content
     * 
     * @return content
     */
    public int getRegister(){
        return content;
    }
}
