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

import basic_rules.Word;
import caculation.Util;
import companent.Memory;
import companent.ShortFloat;
import caculation.ProgramDecoder;
import companent.Cache;
import exception.IllegalMemoryAddressException;
import companent.Log;

public class Loader extends JPanel{

	/**
	 * @author you zhao, yu ma, yufei zhou
	 */
	private static final long serialVersionUID = -7881020205292583683L;

	private JTextArea insCode;
	private JTextArea binaInsCode;
	private JTextArea lineText;
	private JScrollPane scroll;
	private JPanel action;
	private JButton resetButton,loadButton;
	private Log log = Log.getInstance();
	
	public Loader(){
		TitledBorder nameTitle =new TitledBorder("Input field"); 	
		this.setBorder(nameTitle);
		
		insCode = new JTextArea();
		/*insCode.append("STR 0,0,126");
		insCode.append("\nLDR 0,0,126");
		insCode.append("\nAIR 0,127");
		insCode.append("\nAIR 3,21");
		insCode.append("\nSTR 0,0,127");
		insCode.append("\nLDX 1,127");
		insCode.append("\nLDR 0,0,126");
		insCode.append("\nSTR 0,1,0");
		insCode.append("\nLDR 0,0,126");
		insCode.append("\nIN 1,0");
		insCode.append("\nSTR 1,1,1");
		insCode.append("\nOUT 1,1");
		insCode.append("\nSIR 1,13");
		insCode.append("\nJZ 1,0,23");
		insCode.append("\nSIR 1,35");
		insCode.append("\nLDR 2,0,126");
		insCode.append("\nAIR 2,10");
		insCode.append("\nLDR 0,1,0");
		insCode.append("\nMLT 0,2");
		insCode.append("\nSTR 0,1,0");
		insCode.append("\nAMR 1,1,0");
		insCode.append("\nSTR 1,1,0");
		insCode.append("\nJMP 0,8");
		insCode.append("\nSTX 1,125");
		insCode.append("\nLDR 0,0,126");
		insCode.append("\nAIR 0,1");
		insCode.append("\nAMR 0,0,125");
		insCode.append("\nSTR 0,0,125");
		insCode.append("\nLDX 1,125");
		insCode.append("\nLDR 0,0,126");
		insCode.append("\nSTR 0,1,0");
		insCode.append("\nAIR 0,10");
		insCode.append("\nOUT 0,1");
		insCode.append("\nSOB 3,0,8");
		insCode.append("\nLDR 0,0,126");
		insCode.append("\nAIR 0,127");
		insCode.append("\nSTR 0,0,125");
		insCode.append("\nLDX 2,125");
		insCode.append("\nSTX 1,125");
		insCode.append("\nLDR 0,0,125");
		insCode.append("\nSIR 0,1");
		insCode.append("\nSTR 0,0,125");
		insCode.append("\nLDX 3,125");
		insCode.append("\nLDR 3,0,126");
		insCode.append("\nAIR 3,20");
		insCode.append("\nLDR 0,2,0");
		insCode.append("\nSMR 0,3,0");
		insCode.append("\nSTR 0,1,0");
		insCode.append("\nLDR 2,0,126");
		insCode.append("\nAIR 2,1");
		insCode.append("\nSTX 1,125");
		insCode.append("\nAMR 2,0,125");
		insCode.append("\nSTR 2,0,125");
		insCode.append("\nLDX 1,125");
		insCode.append("\nLDR 2,0,126");
		insCode.append("\nAIR 2,1");
		insCode.append("\nSTX 2,125");
		insCode.append("\nAMR 2,0,125");
		insCode.append("\nSTR 2,0,125");
		insCode.append("\nLDX 2,125");
		insCode.append("\nSOB 3,0,45");
		insCode.append("\nLDR 2,0,126");
		insCode.append("\nAIR 2,1");
		insCode.append("\nSTX 2,125");
		insCode.append("\nAMR 2,0,125");
		insCode.append("\nSTR 2,0,125");
		insCode.append("\nLDX 2,125");
		insCode.append("\nSTX 2,125");
		insCode.append("\nLDR 2,0,125");
		insCode.append("\nLDR 1,0,126");
		insCode.append("\nAMR 1,2,0");
		insCode.append("\nLDR 3,0,126");
		insCode.append("\nAIR 3,19");
		insCode.append("\nSTX 2,125");
		insCode.append("\nLDR 0,0,125");
		insCode.append("\nAIR 0,1");
		insCode.append("\nSTR 0,0,125");
		insCode.append("\nLDX 2,125");
		insCode.append("\nSTR 1,0,125");
		insCode.append("\nLDR 0,0,125");
		insCode.append("\nSMR 0,2,0");
		insCode.append("\nJCC 0,0,85");
		insCode.append("\nLDR 1,2,0");
		insCode.append("\nSTX 2,125");
		insCode.append("\nLDR 2,0,125");
		insCode.append("\nSOB 3,0,73");
		insCode.append("\nSIR 2,21");
		insCode.append("\nSTR 2,0,125");
		insCode.append("\nLDX 1,125");
		insCode.append("\nLDR 1,0,126");
		insCode.append("\nAIR 1,10");
		insCode.append("\nOUT 1,1");
		insCode.append("\nLDR 1,0,126");
		insCode.append("\nAIR 1,100");
		insCode.append("\nLDR 2,0,126");
		insCode.append("\nAIR 2,100");
		insCode.append("\nMLT 2,1");
		insCode.append("\nLDR 0,1,0");
		insCode.append("\nDVD 0,2");
		insCode.append("\nJNE 0,0,101");
		insCode.append("\nJZ 3,0,104");
		insCode.append("\nAIR 0,48");
		insCode.append("\nOUT 0,1");
		insCode.append("\nAIR 3,1");
		insCode.append("\nLDR 0,0,126");
		insCode.append("\nAIR 0,10");
		insCode.append("\nSTR 3,0,125");
		insCode.append("\nDVD 2,0");
		insCode.append("\nLDR 3,0,125");
		insCode.append("\nSTR 1,0,125");
		insCode.append("\nLDR 0,0,125");
		insCode.append("\nJNE 2,0,98");
		*/

		//self test:
		/*insCode.append("IN 0,3");
		insCode.append("\nIN 0,3");
		insCode.append("\nIN 0,3");
		insCode.append("\nIN 0,3");
		insCode.append("\nIN 0,3"); */
		//insCode.append("JCC 3,3,87");
		//insCode.append("AIR 0,0,40\n");
		insCode.append("FSUB 0,0,40\n");  //40 as loaded
		//PROGRAM2
		
		/*insCode.append("LDR 0,0,8");
		insCode.append("\nAIR 0,127");
		insCode.append("\nAIR 0,127");
		insCode.append("\nSTR 0,0,40");
		insCode.append("\nLDX 1,40");
		insCode.append("\nIN 0,3");
		
		insCode.append("\nJZ 0,0,70");
		insCode.append("\nOUT 0,1");
		insCode.append("\nSTR 0,1,0");
		insCode.append("\nSTX 1,50");
		insCode.append("\nLDR 1,0,50");
		insCode.append("\nAIR 1,1");
		insCode.append("\nSTR 1,0,50");
		insCode.append("\nLDX 1,50");
		insCode.append("\nJMP 0,60");
		insCode.append("\nSTX 1,41");
		insCode.append("\nLDR 0,0,8");
		insCode.append("\nAIR 0,10");
		insCode.append("\nOUT 0,1");
		insCode.append("\nLDR 3,0,8");
		insCode.append("\nAIR 3,10");
		
		
		
		
		
		insCode.append("\nIN 0,0"); //0 0
		insCode.append("\nOUT 0,1");
		insCode.append("\nSTR 0,1,0");
		insCode.append("\nTRR 0,3");
		insCode.append("\nJCC 3,0,87");
		insCode.append("\nSTX 1,50");
		insCode.append("\nLDR 1,0,50");
		insCode.append("\nAIR 1,1");
		insCode.append("\nSTR 1,0,50");
		insCode.append("\nLDX 1,50");
		insCode.append("\nJMP 0,76");
		insCode.append("\nSTX 1,42");
		insCode.append("\nSMR 1,0,41");
		insCode.append("\nSTR 1,0,43");
		insCode.append("\nLDR 3,0,8");
		insCode.append("\nLDR 0,0,8");
		insCode.append("\nAIR 0,100");
		insCode.append("\nSTR 0,0,50");
		insCode.append("\nLDX 3,50");
		insCode.append("\nLDX 1,40");
		insCode.append("\nLDX 2,41");
		insCode.append("\nLDR 1,0,41");
		insCode.append("\nAIR 3,0");
		insCode.append("\nLDR 0,0,50");
		insCode.append("\nTRR 0,1");
		insCode.append("\nJCC 3,3,78");
		insCode.append("\nLDR 0,1,0");
		insCode.append("\nLDR 1,2,0");
		insCode.append("\nSTR 0,0,50");
		insCode.append("\nSIR 0,32");
		insCode.append("\nJZ 0,3,28");
		insCode.append("\nSIR 0,12");
		insCode.append("\nJZ 0,3,28");
		insCode.append("\nSIR 0,2");
		insCode.append("\nJZ 0,3,28");
		insCode.append("\nLDR 0,0,50");
		insCode.append("\nTRR 0,1");
		insCode.append("\nJCC 3,3,15");
		insCode.append("\nJMP 3,55");
		insCode.append("\nSTX 1,50");
		insCode.append("\nLDR 0,0,8");
		insCode.append("\nAIR 0,1");
		insCode.append("\nAMR 0,0,50");
		insCode.append("\nSTR 0,0,50");
		insCode.append("\nLDX 1,50");
		insCode.append("\nSTX 2,50");
		insCode.append("\nLDR 0,0,8");
		insCode.append("\nAIR 0,1");
		insCode.append("\nAMR 0,0,50");
		insCode.append("\nSTR 0,0,50");
		insCode.append("\nLDX 2,50");
		insCode.append("\nJMP 0,102");
		insCode.append("\nLDR 0,0,50");
		insCode.append("\nSIR 0,10");
		insCode.append("\nJNE 0,3,33");
		insCode.append("\nAIR 2,1");
		insCode.append("\nJMP 3,34");
		insCode.append("\nAIR 3,1");
		insCode.append("\nSTX 2,50");
		insCode.append("\nLDR 0,0,50");
		insCode.append("\nLDR 1,0,42");
		insCode.append("\nTRR 0,1");
		insCode.append("\nJCC 3,3,40");
		insCode.append("\nJMP 3,55");
		insCode.append("\nSTR 2,0,50");
		insCode.append("\nAIR 2,49");
		insCode.append("\nOUT 2,1");
		insCode.append("\nLDR 2,0,8");
		insCode.append("\nAIR 2,46");
		insCode.append("\nOUT 2,1");
		insCode.append("\nLDR 2,0,50");
		insCode.append("\nSTR 3,0,50");
		insCode.append("\nAIR 3,48");
		insCode.append("\nOUT 3,1");
		insCode.append("\nLDR 3,0,8");
		insCode.append("\nAIR 3,10");
		insCode.append("\nOUT 3,1");
		insCode.append("\nLDR 3,0,50");
		insCode.append("\nJMP 3,55");
		insCode.append("\nLDX 2,41");
		insCode.append("\nSTX 1,50");
		insCode.append("\nLDR 1,0,8");
		insCode.append("\nAIR 1,1");
		insCode.append("\nAMR 1,0,50");
		insCode.append("\nSTR 1,0,50");
		insCode.append("\nLDX 1,50");
		insCode.append("\nLDR 0,1,0");
		insCode.append("\nSTR 0,0,50");
		insCode.append("\nSTR 1,0,51");
		insCode.append("\nSTX 1,52");
		insCode.append("\nLDR 0,0,52");
		insCode.append("\nLDR 1,0,41");
		insCode.append("\nSIR 1,1");
		insCode.append("\nTRR 0,1");
		insCode.append("\nJCC 3,3,87");
		insCode.append("\nLDR 0,0,50");
		insCode.append("\nLDR 1,0,51");
		insCode.append("\nSIR 0,10");
		insCode.append("\nJNE 0,3,78");
		insCode.append("\nLDR 3,0,8");
		insCode.append("\nAIR 2,1");
		insCode.append("\nJMP 0,96");
		insCode.append("\nSIR 0,22");
		insCode.append("\nJZ 0,3,85");
		insCode.append("\nSIR 0,12");
		insCode.append("\nJZ 0,3,85");
		insCode.append("\nSIR 0,2");
		insCode.append("\nJZ 0,3,85");
		insCode.append("\nJMP 0,96");
		insCode.append("\nAIR 3,1");
		insCode.append("\nJMP 3,56"); */
	
		
		///////////////////////////////////
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		insCode.setBounds(0, 0, 400, 200);
		insCode.setFont(new Font("Serif", Font.BOLD, 22));
		binaInsCode = new JTextArea("101010  10  10 1 1010101");
		binaInsCode.setBounds(0, 0, 400, 200);
		binaInsCode.setBackground(Color.gray);
		
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
						try {
							loadBinaryCodeIntoMemory();
						} catch (IllegalMemoryAddressException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
				});
	}
	
	private void updateBinaryCode(){
		BufferedReader reader = new BufferedReader(new StringReader(insCode.getText()));
		try {
			binaInsCode.setText("");
			String line = reader.readLine();
			int count = 0;
			while(line!=null){
				
				int[] bin = ProgramDecoder.decodeInstruction(line);
				binaInsCode.append(Util.getBinaryStringFormBitsInInstructionFormat(bin));
				binaInsCode.append("\n");
				count++;
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
	public void loadBinaryCodeIntoMemory() throws IllegalMemoryAddressException{
		if(binaInsCode.getText().contains("-")){
			
		}
		BufferedReader reader = new BufferedReader(new StringReader(insCode.getText()));
		try {
			binaInsCode.setText("");
			String line = reader.readLine();
			Memory mem = Memory.getInstance();
			int index = 55; //RESERVED 
			int count = 0;
			
			int[] FLT=new int[Word.SIZE];
			Word.setIntValueForFloat(FLT, 16717);
			mem.write(40, FLT);
			System.out.println(ShortFloat.toFloat(FLT));
			while(line!=null){
				
				//if(line.indexOf("-")>=0){
				int[] bin = ProgramDecoder.decodeInstruction(line);
				//LOAD IT TO BIN[]
				binaInsCode.append(Util.getBinaryStringFormBitsInInstructionFormat(bin));
				binaInsCode.append("\n");
				count++;
				if(bin!=null){
					mem.write(index, bin);
					//System.out.println("test2");
					index++;
				}else{
					log.console("[ERROR] line:"+count+" cannot compile into binary code, ignored!");
				}
	
				line = reader.readLine();
			}
			System.out.println("test3");
			log.console("Load completed");
			Cache.getInstance().clear();
		} catch (IOException  |IllegalMemoryAddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
