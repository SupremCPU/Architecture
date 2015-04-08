package edu.gwu.computerarchitecture.Device;

import edu.gwu.computerarchitecture.entity.IDevice;
import edu.gwu.computerarchitecture.ui.ConsoleFrame;

/**
 * 
 * Console Printer.
 * 
 * Used for IN, OUT CHK
 * @author liyaoxing Nov 5, 2013
 * @see
 * @since 1.0
 */
public class Printer implements IDevice
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
     * This is a useless method for Printer.
     * @see edu.gwu.computerarchitecture.entity.IDevice#ReadChar()
     */
    @Override
    public Character ReadChar()
    {
        // Do nothing.
        return 0;
    }

    @Override
    public boolean WriteChar(char c)
    {
        if (console != null)
        {
            console.printChar(c);
        }
        return false;
    }

    /**
     * Get the status of printer.
     * @see edu.gwu.computerarchitecture.entity.IDevice#GetStatus()
     */
    @Override
    public int GetStatus()
    {
        int i= 0;
        
        if(console!=null){
            i=console.getConsolePrinterStatus().getWord();
        }
        
        return i;
    }

}
