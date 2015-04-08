package edu.gwu.computerarchitecture.entity;

/**
 * 
 * implement the register for CC
 *
 * <p>detailed comment
 * @author Bin Zhang 2013年10月15日
 * @see
 * @since 2.0
 * @version 1.1
 */
public class Register4Bits
{
    private byte content;
    
    /**
     * 
     */
    public Register4Bits(){
        content = 0;
    }
    
    /**
     * set the register content
     * 
     * @param data
     * @return
     */
    public boolean setRegister(int data){
        if (data > 15 || data < 0){
            return false;
        } else {
            content = (byte)data;
            return true;
        }
    }
    
    /**
     * get the register content
     * 
     * @return content
     */
    public int getRegister(){
        return (int)content;
    }
}
