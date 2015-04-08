package edu.gwu.computerarchitecture.main;

import java.awt.EventQueue;

import edu.gwu.computerarchitecture.cpu.CPU;
import edu.gwu.computerarchitecture.ui.StartFrame;

/**
 * 
 * The main of the simulator.
 * 
 * @author liyaoxing Sep 18, 2013
 * @see
 * @since 1.0
 */
public class Main {

	/**
	 * launch this simulator app.
	 * You have to set this simulator by the GUI when you start it.
	 * @param args None
	 */
	public static void main(String[] args) {

        
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Create CPU.
					CPU cpu=new CPU();cpu.CPU_Initialization();
					
					//Show the GUI.
					StartFrame frame = new StartFrame();
					frame.setCpu(cpu);
					frame.setVisible(true);
					

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
