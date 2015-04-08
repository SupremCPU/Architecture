package edu.gwu.computerarchitecture.cpu;

import java.util.HashMap;
import java.util.Map;

import edu.gwu.computerarchitecture.Device.CardReader;
import edu.gwu.computerarchitecture.Device.Printer;
import edu.gwu.computerarchitecture.Device.Keyboard;
import edu.gwu.computerarchitecture.entity.IDevice;

/**
 * 
 * Device includes the input and output device in simulator
 *
 * <p>detailed comment
 * @author Bin Zhang 2013年11月5日
 * @see
 * @since 1.0
 */
public class Device
{
    private IDevice keyboard;
    private IDevice printer;
    private IDevice cardreader;
    public Map<Integer, IDevice> deviceMap;
    
    public Device(){
        keyboard = new Keyboard();
        printer = new Printer();
        cardreader = new CardReader();
        deviceMap = new HashMap<Integer, IDevice>();
        deviceMap.put(0, keyboard);
        deviceMap.put(1, printer);
        deviceMap.put(2, cardreader);
    }
    
    public IDevice getDevice(int num){
        return deviceMap.get(num);
    }
}
