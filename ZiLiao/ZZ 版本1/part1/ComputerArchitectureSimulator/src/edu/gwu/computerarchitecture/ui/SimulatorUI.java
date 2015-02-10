package edu.gwu.computerarchitecture.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JButton;

import edu.gwu.computerarchitecture.cpu.CPUUnits;
import edu.gwu.computerarchitecture.entity.WordType;
import edu.gwu.computerarchitecture.operation.Decode;
import edu.gwu.computerarchitecture.operation.Fetch;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JScrollPane;

/**
 * 
 * The main frame of the simulator.
 * 
 * @author liyaoxing Sep 18, 2013
 * @see
 * @since 1.0
 */
public class SimulatorUI extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * CPU Units
	 */
	private CPUUnits CPUunits;



	/**
	 * components in the frame
	 */
	private JPanel contentPane;
	private JTextField textFieldPC;
	private JTextField textFieldIR;
	private JTextField textFieldMAR;
	private JTextField textFieldMDR;
	private JTextField textFieldR0;
	private JTextField textFieldR1;
	private JTextField textFieldR2;
	private JTextField textFieldR3;
	
	private JScrollPane scrollPane ;
	private int pcInt = 2;
	private int count = 8;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		CPUUnits.initializeUnits();
		CPUUnits.memory.loadFile("F:/test.txt");
		CPUUnits.memory.showMemory();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SimulatorUI frame = new SimulatorUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SimulatorUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 643, 549);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel labelPC = new JLabel("PC");
		labelPC.setBounds(41, 288, 31, 16);
		contentPane.add(labelPC);

		textFieldPC = new JTextField();
		textFieldPC.setBounds(78, 282, 155, 28);
		contentPane.add(textFieldPC);
		textFieldPC.setColumns(10);
		textFieldPC.setEditable(false);
		textFieldPC.setText(intToSixteenString(0));

		JLabel labelIR = new JLabel("IR");
		labelIR.setBounds(41, 330, 31, 16);
		contentPane.add(labelIR);

		textFieldIR = new JTextField();
		textFieldIR.setBounds(78, 324, 155, 28);
		contentPane.add(textFieldIR);
		textFieldIR.setColumns(10);
		textFieldIR.setEditable(false);
		textFieldIR.setText(intToSixteenString(0));

		JLabel labelMAR = new JLabel("MAR");
		labelMAR.setBounds(287, 288, 48, 16);
		contentPane.add(labelMAR);

		textFieldMAR = new JTextField();
		textFieldMAR.setBounds(347, 282, 155, 28);
		contentPane.add(textFieldMAR);
		textFieldMAR.setColumns(10);
		textFieldMAR.setEditable(false);
		textFieldMAR.setText(intToSixteenString(0));

		JLabel labelMDR = new JLabel("MDR");
		labelMDR.setBounds(287, 330, 41, 16);
		contentPane.add(labelMDR);

		textFieldMDR = new JTextField();
		textFieldMDR.setBounds(347, 324, 155, 28);
		contentPane.add(textFieldMDR);
		textFieldMDR.setColumns(10);
		textFieldMDR.setEditable(false);
		textFieldMDR.setText(intToSixteenString(0));

		JLabel labelR0 = new JLabel("R0");
		labelR0.setBounds(41, 368, 31, 16);
		contentPane.add(labelR0);

		textFieldR0 = new JTextField();
		textFieldR0.setBounds(78, 364, 155, 28);
		contentPane.add(textFieldR0);
		textFieldR0.setColumns(10);
		textFieldR0.setEditable(false);
		textFieldR0.setText(intToSixteenString(0));

		JLabel labelR1 = new JLabel("R1");
		labelR1.setBounds(287, 368, 48, 16);
		contentPane.add(labelR1);

		textFieldR1 = new JTextField();
		textFieldR1.setBounds(347, 362, 155, 28);
		contentPane.add(textFieldR1);
		textFieldR1.setColumns(10);
		textFieldR1.setEditable(false);
		textFieldR1.setText(intToSixteenString(0));


		JLabel labelR2 = new JLabel("R2");
		labelR2.setBounds(41, 412, 41, 16);
		contentPane.add(labelR2);

		textFieldR2 = new JTextField();
		textFieldR2.setBounds(78, 404, 155, 28);
		contentPane.add(textFieldR2);
		textFieldR2.setColumns(10);
		textFieldR2.setEditable(false);
		textFieldR2.setText(intToSixteenString(0));


		JLabel labelR3 = new JLabel("R3");
		labelR3.setBounds(287, 412, 41, 16);
		contentPane.add(labelR3);

		textFieldR3 = new JTextField();
		textFieldR3.setBounds(347, 406, 155, 28);
		contentPane.add(textFieldR3);
		textFieldR3.setColumns(10);
		textFieldR3.setEditable(false);
		textFieldR3.setText(intToSixteenString(0));

/*
		JButton btnStartOrNext = new JButton("Start/Next");
		btnStartOrNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//TODO
			}
		});
		btnStartOrNext.setBounds(507, 196, 117, 29);
		contentPane.add(btnStartOrNext);
		*/

		JButton btnGo = new JButton("Go");
		btnGo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//TODO		

			    while(count>0)
				{
			    CPUUnits.pc.setRegister(new WordType(pcInt));
			    Fetch.pcToMar();
			    Fetch.fetchMarToMdr();
			    Fetch.mdrToIr();
			    Decode decode = new Decode(CPUUnits.ir.getRegister().getWord());
			    pcInt++;
			    count--;
				}
			    refresh();
			    CPUUnits.initializeUnits();
			}
		});
		btnGo.setBounds(507, 55, 117, 29);
		contentPane.add(btnGo);

		JButton btnOpByOp = new JButton("Op by Op");
		btnOpByOp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//TODO
				 CPUUnits.pc.setRegister(new WordType(pcInt));
				 Fetch.pcToMar();
				 Fetch.fetchMarToMdr();
				 Fetch.mdrToIr();
				 Decode decode = new Decode(CPUUnits.ir.getRegister().getWord());
				 refresh();
				 pcInt++;
			}
		});
		btnOpByOp.setBounds(507, 96, 117, 29);
		contentPane.add(btnOpByOp);
/*
		JButton btnStepByStep = new JButton("Step by Step");
		btnStepByStep.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//TODO
			}
		});
		btnStepByStep.setBounds(507, 137, 117, 29);
		contentPane.add(btnStepByStep);
*/
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				refresh();
			}
		});
		btnRefresh.setBounds(507, 239, 117, 29);
		contentPane.add(btnRefresh);
		
		/**
		 * For Memory
		 */
		scrollPane = new JScrollPane();
		scrollPane.setBounds(68, 30, 303, 225);
		
		
		DefaultListModel<String> memoryList= new DefaultListModel<String>();
		
		for(int i=0;i<2048;i++){
			memoryList.addElement(intToSixteenString(0));
		}
		
		JList<String> list= new JList<String>(memoryList);
		
		scrollPane.setViewportView(list);
		
		contentPane.add(scrollPane);
	}

	/**
	 * Refresh the frame from the CPUUnits.
	 */
	public void refresh() {

	
	//	if (CPUUnits != null){
			
			textFieldPC.setText(intToSixteenString(CPUUnits.pc.getRegister().getWord()));
			
			textFieldIR.setText(intToSixteenString(CPUUnits.ir.getRegister().getWord()));
			textFieldMAR.setText(intToSixteenString(CPUUnits.mar.getRegister().getWord()));
			textFieldMDR.setText(intToSixteenString(CPUUnits.mdr.getRegister().getWord()));
			textFieldR0.setText(intToSixteenString(CPUUnits.r0.getRegister().getWord()));
			textFieldR1.setText(intToSixteenString(CPUUnits.r1.getRegister().getWord()));
			textFieldR2.setText(intToSixteenString(CPUUnits.r2.getRegister().getWord()));
			textFieldR3.setText(intToSixteenString(CPUUnits.r3.getRegister().getWord()));
			
			DefaultListModel<String> memoryList= new DefaultListModel<String>();
			
			for(int i=0;i<2048;i++){
				memoryList.addElement(intToSixteenString(CPUUnits.memory.getMemory(i)));
			}
			
			JList<String> list= new JList<String>(memoryList);
			
			scrollPane.setViewportView(list);
			
	//	}
	}
	/*
	public CPUUnits getCPUunits() {
		return CPUunits;
	}

	public void setCPUunits(CPUUnits cPUunits) {
		CPUunits = cPUunits;
		refresh();
	}
	*/
	/**
	 * Change Integer to 16 Characters String
	 * @param i
	 * @return String
	 */
	public  String intToSixteenString(int i){
		String result="";
		for (int index=0;index<16;index++){
			
			if(i>=(Math.pow(2,15-index))){
				i=i-(int)Math.pow(2,15-index);
				result=result+"1";
			}else{
				result=result+"0";
			}
		}		
		return result;
	}
}
