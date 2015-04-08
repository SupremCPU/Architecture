package edu.gwu.computerarchitecture.ui;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import edu.gwu.computerarchitecture.Device.Keyboard;
import edu.gwu.computerarchitecture.Device.Printer;
import edu.gwu.computerarchitecture.cpu.CPU;
import edu.gwu.computerarchitecture.entity.WordType;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * This frame is for users to set up the simulator.
 * And start the main frame of the simulator.
 * @author liyaoxing Oct 1, 2013
 * @see
 * @since 1.0
 */
public class StartFrame extends JFrame {

	
	private static final long serialVersionUID = 1L;

	/**
	 * CPU
	 */
	public CPU cpu;
	
	//Components in the frame
	private JPanel contentPane;

	// A file chooser
	private JFileChooser fc;

	/**
	 * Create the frame.
	 */
	public StartFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Welcome");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		fc = new JFileChooser();
		
		JLabel lblWelcome = new JLabel("Welcome to this simulation!");
		lblWelcome.setBounds(89, 56, 250, 41);
		contentPane.add(lblWelcome);

		JLabel lblRemain = new JLabel("Please choose the buttons you want.");
		lblRemain.setBounds(89, 109, 250, 16);
		contentPane.add(lblRemain);

		JButton btnExit = new JButton("Exit");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// exit
				System.exit(EXIT_ON_CLOSE);
			}
		});
		btnExit.setBounds(218, 172, 117, 29);
		contentPane.add(btnExit);

		JButton btnStart = new JButton("Power up");
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Choose file to load memory
				disappear();
				
				//Boot Loading.
                LoadingBootBar loadRootFrame = new LoadingBootBar(getSelf());
                loadRootFrame.setVisible(true);
                loadRootFrame.loadRootAndIPL();
				
 

			}
		});
		btnStart.setBounds(89, 172, 117, 29);
		contentPane.add(btnStart);
	}
	
	public CPU getCpu() {
		return cpu;
	}

	public void setCpu(CPU cpu) {
		this.cpu = cpu;		
	}

	/**
	 * Make this frame disappear.
	 */
	private void disappear() {
		this.setVisible(false);
	}

	 /**
     * Make this frame appear.
     */
	public void appear() {
		this.setVisible(true);
	}
	
	/**
	 * Open VirtualKeyboard according to the system.
	 */
	public void openVirtualKeyboard(){
	       try
	        {
	            Properties prop = System.getProperties();

	            String os = prop.getProperty("os.name");
	            System.out.println(os);

	            //Open Virtual Keyboard on Win.
	            if (os.startsWith("win") || os.startsWith("Win"))
	            {
	                System.out.println("Win");

	                String sysroot = System.getenv("SystemRoot");
	                System.out.print(sysroot);

	                Runtime.getRuntime().exec(
	                        "cmd /c " + sysroot + "\\system32\\osk.exe");
	            }
	            //Open Virtual Keyboard on Mac.
	            if (os.startsWith("Mac") || os.startsWith("mac"))
	            {
	                System.out.println("Mac");
	                String [] exes={"open","/System/Library/Input Methods/KeyboardViewer.app"};
	                Runtime.getRuntime().exec(exes);
	            }

	        }
	        catch (Exception e)
	        {            
	            e.printStackTrace();
	        }
	}
	
	private StartFrame getSelf(){
	    return this;
	}
}
