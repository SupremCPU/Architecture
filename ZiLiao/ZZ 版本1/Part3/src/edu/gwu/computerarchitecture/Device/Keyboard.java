package edu.gwu.computerarchitecture.Device;

import edu.gwu.computerarchitecture.entity.IDevice;
import edu.gwu.computerarchitecture.ui.ConsoleFrame;

/**
 * 
 * Console Keyboard.
 * 
 * Used for IN, OUT CHK
 * @author liyaoxing Nov 5, 2013
 * @see
 * @since 1.0
 */
public class Keyboard implements IDevice
{
    ConsoleFrame console;

    public ConsoleFrame getConsole()
    {
        return console;
    }

    public void setConsole(ConsoleFrame console)
    {
        this.console = console;
    }

    /**
     * Read one character from the console keyboard.
     * @see edu.gwu.computerarchitecture.entity.IDevice#ReadChar()
     */
    @Override
    public Character ReadChar()
    {
        Character c=null;
        
        if(console!=null){
            c=console.readChar();
        }
        
        return c;
    }

    /**
     * This is a useless method for Keyboard.
     * @see edu.gwu.computerarchitecture.entity.IDevice#WriteChar(char)
     */
    @Override
    public boolean WriteChar(char c)
    {
        // Do nothing.
        return false;
    }

    /**
     * Get The status of keyboard.
     * @see edu.gwu.computerarchitecture.entity.IDevice#GetStatus()
     */
    @Override
    public int GetStatus()
    {
        int i=0;
        
        
        if(console!=null){
            i=console.getConsoleKeyboardStatus().getWord();
        }
        
        return i;
    }

}
