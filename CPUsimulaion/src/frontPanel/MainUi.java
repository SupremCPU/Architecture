package frontPanel;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import companent.CPU;
import frontPanel.KeyboardPanel;
import exception.IllegalMemoryAddressException;
import companent.Log;
import frontPanel.Loader;
import companent.Memory;

/**
 * you zhao yuma
 */

public class MainUi extends JFrame implements ActionListener, Observer {
	private/* JPanel */Loader instructionPanel = null;
	private ConsolePanel displayPanel = null;
	//private JTextArea instructionCode = null;
	private Memory ram = new Memory();
	private registerPanel registerPanel;
	private CachePanel cachePanel;
	private KeyboardPanel keyboardPanel;
	private PrinterPanel printerPanel;
	private myButton runButton = null;
	private myButton loadButton = null;
	private myButton clearButton = null;
	private myButton runStepButton = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = 4383050828385488678L;

	public MainUi() {

		// Declare buttons
		runButton = new myButton(80, 60, "Run");
		loadButton = new myButton(80, 60, "Load");
		clearButton = new myButton(80, 60, "Reset");
		runStepButton = new myButton(80, 60, "Run by Step");

		runButton.addActionListener(this);
		loadButton.addActionListener(this);
		clearButton.addActionListener(this);
		runStepButton.addActionListener(this);

		// Create a framethis
		myMainFrame myMainFrame = new myMainFrame(500, 200, 890, 850,
				Color.WHITE);
		myPanel loadPanel = new myPanel(310, 590, 250, 100, Color.gray,
				"Implementation");
		myMainFrame.add(loadPanel);
		myPanel controlPanel = new myPanel(50, 590, 250, 100, Color.gray,
				"Control");
		myMainFrame.add(controlPanel);

		controlPanel.add(runButton);
		controlPanel.add(runStepButton);
		loadPanel.add(loadButton);
		loadPanel.add(clearButton);

		// set displayPanel
		
		displayPanel = new ConsolePanel();
		displayPanel.setBounds(50, 200, 240, 350);
		myMainFrame.add(displayPanel);
		
		
		instructionPanel = new Loader();
		instructionPanel.setBounds(50, 20, 480, 160);
		myMainFrame.add(instructionPanel);

		// set registerPanel
		registerPanel = new registerPanel();
		registerPanel.setBounds(315, 200, 220, 350);
		myMainFrame.add(registerPanel);
		
		printerPanel = new PrinterPanel();
		printerPanel.setBounds(560,20, 300, 160);
    	myMainFrame.add(printerPanel);
    	
		cachePanel = new CachePanel();
		cachePanel.setBounds(560, 400, 260, 385);
		myMainFrame.add(cachePanel);
		
		keyboardPanel = new KeyboardPanel();
		keyboardPanel.setBounds(560,200, 194, 194);
    	myMainFrame.add(keyboardPanel);
		
		// Create input field
		// instructionPanel =
		// new myPanelNoLayout(50, 20, 480,
		// 150, Color.white, "Instruction");
		// myMainFrame.add(instructionPanel);
		// instructionCode =
		// new JTextArea("Input instrucions...");
		// instructionPanel.add(instructionCode);
		// instructionCode.setBounds(10, 20, 460, 121);
		// instructionCode.setFont(new Font("Serif", Font.BOLD, 22));
		// instructionCode.setForeground(Color.BLACK);
		
		
		myMainFrame.setVisible(true);

	}

	public void actionPerformed(ActionEvent ae) {
		String buttonCommand = ae.getActionCommand();
		String txt = instructionPanel.getText();
		//System.out.println(ae.getActionCommand());
		switch(buttonCommand){
		case("Load"):
		// String instruction = instructionCode.getText();
		// System.out.println(instruction);
		// ram.writeWord(Integer.parseInt(instruction));
			System.out.println("Listener: " + txt);
			try {
				instructionPanel.loadBinaryCodeIntoMemory();
			} catch (IllegalMemoryAddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		break;
		case("Run"):
			System.out.println("Listener: " + txt);
			//CPU.getInstance().executeSingleStep();
			CPU.getInstance().execute();
		case("Run by Step"):
			System.out.println("Listener: " + txt);
			CPU.getInstance().continueRun(CPU.SIGNAL_DEBUG);
		default:
			break;
		}
	}

	public static void main(String args[]) {

		MainUi simuMainUi = new MainUi();
		simuMainUi.setVisible(true);
		Log log = Log.getInstance();
		log.addObserver(simuMainUi);
	}
	
	public void update(Observable o, Object arg) {
		displayPanel.update();
		cachePanel.update();
		registerPanel.update();
		printerPanel.update();
	}
}

class myMainFrame extends JFrame {
	private static final long serialVersionUID = 9058807962307911300L;

	myMainFrame(int x, int y, int width, int height, Color color) {
		super("CPU Simulation");
		setBounds(x, y, width, height);
		setBackground(color);
		// setLayout(null);
		setLayout(null);
		setResizable(true);
	}
}

class myPanel extends JPanel {
	myPanel(int x, int y, int width, int height, Color color, String s) {
		// super(s);
		setLayout(new GridLayout(2, 1));
		setBounds(x, y, width, height);
		setBackground(color);
		setBorder(new TitledBorder(s));
		setVisible(true);
	}
}

class myButton extends JButton {
	myButton(int x, int y, String s) {
		super(s);
		setPreferredSize(new Dimension(x, y));
		setContentAreaFilled(true);
		setMargin(new Insets(0, 0, 0, 0));
		setBackground(Color.LIGHT_GRAY);
		// setBorder(BorderFactory.createLoweredBevelBorder());
		// UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	}
}