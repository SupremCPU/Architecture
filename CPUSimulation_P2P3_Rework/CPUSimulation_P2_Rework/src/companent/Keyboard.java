package companent;
import java.util.Scanner;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import basic_rules.Word;



public class Keyboard extends IODevice{

	private int keyEvent;

	private Thread waitingThread = null;
	
	public Keyboard(){
		this.devid = IODevice.DEVID_KEYBOARD;
		
		
	}

	@Override
	public void reset() {
		if(waitingThread!=null){
			synchronized(waitingThread){
				this.keyEvent = 0;
				waitingThread.notify();
				waitingThread = null;
			}
		}
	}

	@Override
	public void write(Word value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Word read() {
		
		/*
		while(!keyEventFlag){
			try {
				System.out.println("NOthing, wait...");
				Thread.sleep(100);
				//Thread.currentThread().wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Something, "+keyEvent);
		*/
		
		//tag current thread then make it wait for INPUT
		this.waitingThread = Thread.currentThread();
		//e=new KeyEvent();
		this.keyEvent=0;
		/*Scanner ReadIn = new Scanner(new InputStreamReader(System.in));
		int value = (int)ReadIn.nextByte();  //e.getKeyChar();
		ReadIn.close();
		if(value>0&&value<128)
		{
			this.keyEvent=(value);
		}
		Log.getInstance().console("wait for keyboard");*/
		//System.out.println("Start waiting...");
		try {
			synchronized (waitingThread) {
				Log.getInstance().console("wait for keyboard");
				this.setChanged();
				this.notifyObservers(5);//
				waitingThread.wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//waiting will be terminated by activeEvent which is called when user makes an input
		//input value will be stored in keyEvent
		//System.out.println("Signal coming...");
		
		return new Word(keyEvent);
	}

	/**
	 * answer when there is an input from iodevice
	 * <br> first check if there is a thread waiting for input
	 * <br> then store the input value
	 * <br> awake that thread
	 * @param event
	 */
	public void activeEvent(int event){
		if(waitingThread!=null){
			synchronized(waitingThread){
				this.keyEvent = event;
				waitingThread.notify();
				waitingThread = null;
				this.setChanged();
				this.notifyObservers(8);//MVCMsg.MSG_KEYBOARD_PRESSED
			}
		}
	}

}
