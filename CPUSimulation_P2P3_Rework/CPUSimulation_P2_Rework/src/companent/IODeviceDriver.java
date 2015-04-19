package companent;
import exception.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import basic_rules.Word;


public class IODeviceDriver { // implements Observer
private Map<Integer,IODevice> DeviceMap;
	
	/**
	 * an instance
	 */
	private static IODeviceDriver _instance;
	public static IODeviceDriver getInstance(){
		if(_instance==null)
			_instance = new IODeviceDriver();
		return _instance;
	}
	
	/**
	 * constructor
	 */
	public IODeviceDriver(){
		DeviceMap = new HashMap<Integer,IODevice>();
		
		//keyboard
		Keyboard keyboard = new Keyboard();
		DeviceMap.put(keyboard.devid, keyboard);
		
		//printer
		Printer printer = new Printer();
		DeviceMap.put(printer.devid, printer);
		
		//file: devid:3
		CardReader file3 = new CardReader(3,CardReader.IOTYPE_INPUT);
		file3.setDefaultFile("TestC.txt");
		DeviceMap.put(file3.devid, file3);
		
		
		//file: devid:4
		CardReader file4 = new CardReader(4,CardReader.IOTYPE_OUTPUT);
		file4.setDefaultFile("./devid4.txt");
		DeviceMap.put(file4.devid, file4);
	
		CodePanel CodePanel = new CodePanel();
		DeviceMap.put(CodePanel.devid, CodePanel);
	}
	
	/**
	 * for CPU to write data into Device
	 * @param devid
	 * @param value
	 * @throws IllegalDeviceStatusException: Device not found, Device not ready, Device error, etc
	 */
	public void output(int devid,Word value) throws IllegalDeviceStatusException{
		//find the device
		IODevice dev = DeviceMap.get(devid);
		if(dev==null){
			throw new IllegalDeviceStatusException("Device("+devid+") not found");
		}
		//use device to write data, Exception may be thrown
		dev.write(value);
	}
	/**
	 * for CPU to read data from Device
	 * @param devid
	 * @return
	 * @throws IllegalDeviceStatusException: Device not found, Device not ready, Device error, etc
	 */
	public Word input(int devid) throws IllegalDeviceStatusException{
		//find the device
		IODevice dev = DeviceMap.get(devid);
	//	System.out.println("======INPUT!!!!!!!!!!!!!!!!!!======="+devid);
		if(dev==null){
			System.out.println("======INPUTERROR!!!!======="+devid);
			throw new IllegalDeviceStatusException("Device("+devid+") not found");
		}
		//fetching data from device, the fetching may cause CPU waiting. Exception may be thrown
		return dev.read();
	}
	
	/**
	 * get Keyboard device
	 * @return
	 */
	public IODevice getKeyboard(){
		return DeviceMap.get(IODevice.DEVID_KEYBOARD);
	}
	
	/**
	 * get Printer device
	 * @return
	 */
	public IODevice getPrinter(){
		return DeviceMap.get(IODevice.DEVID_PRINTER);
	}
	
	public CardReader getCardReader(int devid){
		return (CardReader)DeviceMap.get(devid);
	}
	
	public CodePanel getCodePanel(){
		return (CodePanel)DeviceMap.get(IODevice.DEVID_EDITOR);
	}
	
	public void resetAll(){
		try {
			for(IODevice dev:DeviceMap.values()){
				dev.reset();
			}
		} catch (IllegalDeviceStatusException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}*/
}
