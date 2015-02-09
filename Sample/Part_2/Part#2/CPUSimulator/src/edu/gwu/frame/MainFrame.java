package edu.gwu.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import edu.gwu.core.CPU;
import edu.gwu.core.Cache;
import edu.gwu.core.Log;
import edu.gwu.core.MainMemory;

/**
 * 
 * @author 
 *
 */
public class MainFrame extends JFrame implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9058808062307751300L;
	
	
	private JPanel codeLoaderPanel;
	
	private JPanel actionPanel;
	
	private ConsolePanel consolePanel;
	
	private RegisterPanel registerPanel;
	private MemoryPanel memoryPanel;
	private KeyboardPanel keyboardPanel;
	
	/**
	 * the panel to show cache information
	 */
	private CachePanel cachePanel;
	private PrinterPanel printerPanel;
	
	private JButton reset,run,runSingleStep;
	
	private JRadioButton radioModeRun;
    private JRadioButton radioModeDebugInstr;
    private JRadioButton radioModeDebugCycle;
    
    
	public MainFrame(){
		super("CPU Simulator");
		
		this.setSize(865,636);
		this.setLocation(200, 120);
		
		this.setLayout(null);
		
		codeLoaderPanel = new CodeLoader();
		codeLoaderPanel.setBounds(5, 0, 480, 200);
		this.add(codeLoaderPanel);
		
		consolePanel = new ConsolePanel();
		consolePanel.setBounds(5, 205, 360, 200);
		
		actionPanel = new JPanel();
		actionPanel.setBorder(new TitledBorder("Action"));
		Box box = Box.createVerticalBox();
		reset = new JButton("Reset");
		run = new JButton("run");
		box.add(reset);
		
		radioModeRun = new JRadioButton("Run");
		radioModeRun.setSelected(true); 
		radioModeDebugInstr = new JRadioButton("Debug(SingleStep)");
		radioModeDebugCycle = new JRadioButton("Debug(SingleCycle)");
		
		ButtonGroup group = new ButtonGroup();
		group.add(radioModeRun);
		group.add(radioModeDebugInstr);
		group.add(radioModeDebugCycle);
		box.add(radioModeRun);
		box.add(radioModeDebugInstr);
		box.add(radioModeDebugCycle);
		
		//reset.setVisible(false);
		
		runSingleStep = new JButton("Continue");
		
		
		box.add(run);
		box.add(runSingleStep);
		runSingleStep.setVisible(false);
		
		actionPanel.add(box);
		actionPanel.setBounds(490, 0, 155, 200);
		this.add(actionPanel);
		
		this.add(consolePanel);
		
		registerPanel = new RegisterPanel();
		registerPanel.setBounds(5, 410, 360, 200);
		this.add(registerPanel);
		
		memoryPanel = new MemoryPanel();
		memoryPanel.setBounds(375, 205, 270, 405);
		this.add(memoryPanel);
		
	
		cachePanel = new CachePanel();
		cachePanel.setBounds(650, 365, 211, 245);
		this.add(cachePanel);
		

		keyboardPanel = new KeyboardPanel();
		keyboardPanel.setBounds(650, 0, 211, 200);
    	add(keyboardPanel);
		
    	printerPanel = new PrinterPanel();
    	printerPanel.setBounds(650, 205, 211, 155);
    	add(printerPanel);

		this.setVisible(true);
		//this.pack();
		this.setResizable(false);
		this.addListener();
		
	}
	
	private void addListener(){
		run.addMouseListener(
				new MouseListener(){

					@Override
					public void mouseClicked(MouseEvent e) {
						CPU.getInstance().execute();
					}

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
				});
		
		runSingleStep.addMouseListener(
				new MouseListener(){

					@Override
					public void mouseClicked(MouseEvent e) {
						CPU.getInstance().continueRun(CPU.SIGNAL_DEBUG);
					}

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
				});
		
		radioModeRun.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						if(radioModeRun.isSelected())
							CPU.getInstance().setRunMode(CPU.MODE_RUN);
					}
				});
		radioModeDebugInstr.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						if(radioModeDebugInstr.isSelected())
							CPU.getInstance().setRunMode(CPU.MODE_DEBUG_INSTRUCTION);
					}
				});
		radioModeDebugCycle.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						if(radioModeDebugCycle.isSelected())
							CPU.getInstance().setRunMode(CPU.MODE_DEBUG_CIRCLE);
					}
				});
		
		reset.addActionListener(
				new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						MainMemory.getInstance().clear();
						Cache.getInstance().clear();
						CPU.getInstance().clear();
						printerPanel.clearText();
						Log.getInstance().console("Reset registers,memory,cache");
					}
					
				});
	}
	
	public static void main(String[] args){
		MainFrame frame = new MainFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Log log = Log.getInstance();
		log.addObserver(frame);
		
		
	}

	@Override
	public void update(Observable o, Object arg) {
		consolePanel.update();
		memoryPanel.update();
		registerPanel.update();
		cachePanel.update();
		printerPanel.update();
		
		if(CPU.getInstance().getState()==CPU.STATE_IDLE){
			run.setVisible(true);
			runSingleStep.setVisible(false);
		}else{
			run.setVisible(false);
			runSingleStep.setVisible(true);
		}
		
	}
}
