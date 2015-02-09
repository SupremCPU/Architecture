package frontPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import companent.CPU;
import frontPanel.Log;
import frontPanel.Typer;

public class MainUi extends JFrame implements ActionListener, Observer {
	private/* JPanel */Typer instructionPanel = null;
	private ConsolePanel displayPanel = null;
	private registerPanel registerPanel;
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
		runStepButton = new myButton(80, 60, "Run by step");

		runButton.addActionListener(this);
		loadButton.addActionListener(this);
		clearButton.addActionListener(this);
		runStepButton.addActionListener(this);

		// Create a framethis
		myMainFrame myMainFrame = new myMainFrame(500, 200, 620, 650,
				Color.WHITE);
		myPanel loadPanel = new myPanel(310, 420, 250, 200, Color.gray,
				"Implementation");
		myMainFrame.add(loadPanel);
		myPanel controlPanel = new myPanel(50, 420, 250, 200, Color.gray,
				"Control");
		myMainFrame.add(controlPanel);

		controlPanel.add(runButton);
		controlPanel.add(runStepButton);
		loadPanel.add(loadButton);
		loadPanel.add(clearButton);

		// set displayPanel
		displayPanel = new ConsolePanel();
		displayPanel.setBounds(50, 220, 220, 160);
		myMainFrame.add(displayPanel);

		instructionPanel = new Typer();
		instructionPanel.setBounds(50, 20, 380, 160);
		myMainFrame.add(instructionPanel);

		// set registerPanel
		registerPanel = new registerPanel();
		registerPanel.setBounds(315, 200, 220, 200);
		myMainFrame.add(registerPanel);

		
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
		instructionPanel.loadBinaryCodeIntoMemory();
		break;
		case("Run by step"):
			System.out.println("Listener: " + txt);
			CPU.getInstance().executeSingleStep();
		default:
			break;
		}
	}

	   /*
	    * Load Log into the JAVA.swing.Panel
	    */
	public static void main(String args[]) {

		MainUi simuMainUi = new MainUi();
		simuMainUi.setVisible(true);
		Log log = Log.getInstance();
		log.addObserver(simuMainUi);
	}
	// build update method the displayPanel and	registerPanel Correspondence with each other
	public void update(Observable o, Object arg) {
		displayPanel.update();
		registerPanel.update();
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
	

	
	private static final long serialVersionUID = 1L;

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
	
	   private static final long serialVersionUID = 1L;
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