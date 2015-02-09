package frontPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import caculation.computering;
import companent.memory;
import caculation.getCode;
//import exception.IllegalMemoryAddressException;

public class Typer extends JPanel{

	
	private static final long serialVersionUID = -7881020205292583683L;

	private JTextArea insCode;
	private JTextArea binaInsCode;
	private JScrollPane scroll;
	private JPanel action;
	private JButton resetButton,loadButton;
	private Log log = Log.getInstance();
	
	public Typer(){
		TitledBorder nameTitle =new TitledBorder("Input Order"); 	
		this.setBorder(nameTitle);
		
		/*
		 * input the test order into the typer panel
		 */
		insCode = new JTextArea("AIR 2,0,0,10\nSTR 2,0,0,2\nAIR 1,0,0,50\nAMR 1,0,0,20 ");
		insCode.setBounds(0, 0, 400, 200);
		insCode.setFont(new Font("Serif", Font.BOLD, 22));
		binaInsCode = new JTextArea("101010  10  10 1 1010101");
		binaInsCode.setBounds(0, 0, 400, 200);
		binaInsCode.setBackground(Color.gray);
		
		/*
		 * Declare the action of the typer panel
		 */
		JPanel in = new JPanel();
		in.setLayout(new BorderLayout());
		in.setBackground(Color.GRAY);
		in.add(insCode,BorderLayout.CENTER);
		//in.add(binaInsCode,BorderLayout.EAST);
		binaInsCode.setEditable(false);
		scroll = new JScrollPane(in);
		this.setLayout(new GridLayout());
		action = new JPanel();
		action.setLayout(new GridLayout(1,1));
		Box box=Box.createVerticalBox();
		resetButton = new JButton("Clear");
		resetButton.setVisible(false);
		loadButton = new JButton("Load");
		loadButton.setVisible(false);
		box.add(resetButton);
		box.add(loadButton);
		action.add(box);
		
		this.setLayout(new BorderLayout());
		this.add(scroll,BorderLayout.CENTER);
		this.add(action,BorderLayout.EAST);
		
		this.addListener();
		updateBinaryCode();
	}
	/*
	 * add the listener event into the typer panel
	 * e.getKeyCode()==8 means press the confirm function
	 */
	private void addListener(){
		insCode.addKeyListener(
				new KeyListener(){

					@Override
					public void keyTyped(KeyEvent e) {
						
					}

					@Override
					public void keyPressed(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void keyReleased(KeyEvent e) {
						// TODO Auto-generated method stub
						if(e.getKeyCode()==8||e.getKeyCode()==10)
							updateBinaryCode();
					}
					
				});
		loadButton.addMouseListener(
				new MouseAdapter(){

					@Override
					public void mouseClicked(MouseEvent e) {
						loadBinaryCodeIntoMemory();
					}
					
				});
	}
	/*
	 * reader get the string data in the panel
	 * by using the getBinaryStringFormBitsInInstructionFormat translate into binary string data
	 */
	private void updateBinaryCode(){
		BufferedReader reader = new BufferedReader(new StringReader(insCode.getText()));
		try {
			binaInsCode.setText("");
			String line = reader.readLine();
			while(line!=null){
				
				int[] bin = getCode.decodeInstruction(line);
				binaInsCode.append(computering.getBinaryStringFormBitsInInstructionFormat(bin));
				binaInsCode.append("\n");
				line = reader.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getText(){
		return insCode.getText();
	}
	//using the method binaInsCode transfer string code into binary code
	public void loadBinaryCodeIntoMemory(){
		if(binaInsCode.getText().contains("-")){
			
		}
		
		BufferedReader reader = new BufferedReader(new StringReader(insCode.getText()));
		try {
			binaInsCode.setText("");
			String line = reader.readLine();
			memory mem = memory.getInstance();
			int index = 0;
			int count = 0;
			while(line!=null){
				
				//if(line.indexOf("-")>=0){
				
			/*
			 * Using the Class's Instruction format to find the OPcode
			 * by compare the input OPcode with different operation in CPU to decide use which way
			 */
				int[] bin = getCode.decodeInstruction(line);
				binaInsCode.append(computering.getBinaryStringFormBitsInInstructionFormat(bin));
				binaInsCode.append("\n");
				count++;
				if(bin!=null){
					mem.writeWord(index, bin);
					System.out.println("test2");
					index++;
				}else{
					log.console("[ERROR] line:"+count+" cannot compile into binary code, ignored!");
				}
	
				line = reader.readLine();
			}
			log.console("Load completed");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
