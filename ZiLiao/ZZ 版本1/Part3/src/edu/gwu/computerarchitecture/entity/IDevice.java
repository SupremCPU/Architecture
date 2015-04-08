package edu.gwu.computerarchitecture.entity;

/**
 * 
 * Device is the interface of the Device which provide the basic method
 *
 * <p>detailed comment
 * @author Bin Zhang 2013年11月5日
 * @see
 * @since 3.0
 * @version 1.0
 */
public interface IDevice
{
    public Character ReadChar();
    public boolean WriteChar(char c);
    public int GetStatus();
}
