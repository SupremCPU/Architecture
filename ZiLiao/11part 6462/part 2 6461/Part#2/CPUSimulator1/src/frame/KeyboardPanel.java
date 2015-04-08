package frame;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import companent.CPU;

public class KeyboardPanel extends JPanel{

	private static final long serialVersionUID = 5783683260444893043L;
    
	private JButton aB = new JButton("A");
	private JButton bB = new JButton("B");
	private JButton cB = new JButton("C");
	private JButton oneB = new JButton("1");
	private JButton twoB = new JButton("2");
	private JButton threeB = new JButton("3");
	private JButton fourB = new JButton("4");
	private JButton fiveB = new JButton("5");   
	private JButton sixB = new JButton("6");
	private JButton sevenB = new JButton("7");
	private JButton eightB = new JButton("8");
	private JButton nineB = new JButton("9");
	private JButton zeroB = new JButton("0"); 
	private JButton dotB = new JButton(".");
	private JButton enterB = new JButton("Enter");
	private JButton addB = new JButton("+"); 
	private JButton minusB = new JButton("-"); 
	private JButton divideB = new JButton("/");
	private JButton multiplyB = new JButton("*");
	
	private Font buttonFont = new Font("Times New Roman", Font.BOLD, 17);
	
	private int devId = 0;
	
	public KeyboardPanel(){	
		
		
		
		TitledBorder nameTitle =new TitledBorder("Keyboard (DEVID:0)"); 	
		this.setBorder(nameTitle);
		
        this.setLayout(null);
        
        //
        this.add(aB);
        aB.setBounds(5, 18, 50, 35);
        aB.setFont(buttonFont);
        
        this.add(bB);
        bB.setBounds(55, 18, 50, 35);
        bB.setFont(buttonFont);
        
        this.add(cB);
        cB.setBounds(105, 18, 50, 35);
        cB.setFont(buttonFont);
        
        this.add(addB);
        addB.setBounds(155, 18, 50, 35);
        addB.setFont(buttonFont);
        
        //
        this.add(oneB);
        oneB.setBounds(5, 53, 50, 35);
        oneB.setFont(buttonFont);       
        
        this.add(twoB);
        twoB.setBounds(55, 53, 50, 35);
        twoB.setFont(buttonFont);

        this.add(threeB);
        threeB.setBounds(105, 53, 50, 35);
        threeB.setFont(buttonFont);
        
        this.add(minusB);
        minusB.setBounds(155, 53, 50, 35);
        minusB.setFont(buttonFont);
        
        //
        this.add(fourB);
        fourB.setBounds(5, 88, 50, 35);
        fourB.setFont(buttonFont);
       
        this.add(fiveB);
        fiveB.setBounds(55, 88, 50, 35);
        fiveB.setFont(buttonFont);
        
        this.add(sixB);
        sixB.setBounds(105, 88, 50, 35);
        sixB.setFont(buttonFont);
   
        this.add(multiplyB);
        multiplyB.setBounds(155, 88, 50, 35);
        multiplyB.setFont(buttonFont);
        
        //
        this.add(sevenB);
        sevenB.setBounds(5, 123, 50, 35);
        sevenB.setFont(buttonFont);
        
        this.add(eightB);
        eightB.setBounds(55, 123, 50, 35);
        eightB.setFont(buttonFont);
        
        this.add(nineB);
        nineB.setBounds(105, 123, 50, 35);
        nineB.setFont(buttonFont);
        
        this.add(divideB);
        divideB.setBounds(155, 123, 50, 35);
        divideB.setFont(buttonFont); 
        
        //
        this.add(zeroB);
        zeroB.setBounds(5, 158, 50, 35);
        zeroB.setFont(buttonFont);               
        
        this.add(dotB);
        dotB.setBounds(55, 158, 50, 35);
        dotB.setFont(buttonFont);
  
        this.add(enterB);
        enterB.setBounds(105, 158, 100, 35);
        enterB.setFont(buttonFont);
   
        aB.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent f) {
            	CPU.getInstance().inSignal(devId,65);//65 is decimal ASCII value for A
            }
        });
        
        bB.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent f) {
            	CPU.getInstance().inSignal(devId,66);//66 is decimal ASCII value for B
            }
        });
        
        cB.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent f) {
            	CPU.getInstance().inSignal(devId,67);//67 is decimal ASCII value for C
            }
        });
        
        addB.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent f) {
            	CPU.getInstance().inSignal(devId,43);//43 is decimal ASCII value for +
            }
        });
        
        oneB.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent f) {
            	CPU.getInstance().inSignal(devId,49);//49 is decimal ASCII value for 1
            }
        });
        
        twoB.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent f) {
            	CPU.getInstance().inSignal(devId,50);//50 is decimal ASCII value for 2
            }
        });
        
        threeB.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent f) {
            	CPU.getInstance().inSignal(devId,51);//51 is decimal ASCII value for 3
            }
        });
        
        minusB.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent f) {
            	CPU.getInstance().inSignal(devId,45);//45 is decimal ASCII value for -
            }
        });
        
        fourB.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent f) {
            	CPU.getInstance().inSignal(devId,52);//52 is decimal ASCII value for 4
            }
        });
        
        fiveB.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent f) {
            	CPU.getInstance().inSignal(devId,53);//53 is decimal ASCII value for 5
            }
        });
        
        sixB.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent f) {
            	CPU.getInstance().inSignal(devId,54);//54 is decimal ASCII value for 6
            }
        });
        
        multiplyB.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent f) {
            	CPU.getInstance().inSignal(devId,42);//42 is decimal ASCII value for *
            }
        });
        
        sevenB.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent f) {
            	CPU.getInstance().inSignal(devId,55);//55 is decimal ASCII value for 7
            }
        });
        
        eightB.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent f) {
            	CPU.getInstance().inSignal(devId,56);//56 is decimal ASCII value for 8
            }
        });
        
        nineB.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent f) {
            	CPU.getInstance().inSignal(devId,57);//57 is decimal ASCII value for 9
            }
        });
        
        divideB.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent f) {
            	CPU.getInstance().inSignal(devId,47);//47 is decimal ASCII value for/
            }
        });
        
        zeroB.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent f) {
            	CPU.getInstance().inSignal(devId,48);//30 is decimal ASCII value for 0
            }
        });
        
        dotB.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent f) {
            	CPU.getInstance().inSignal(devId,46);//46 is decimal ASCII value for .
            }
        });
        
        enterB.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent f) {
            	CPU.getInstance().inSignal(devId,13);//13 is decimal ASCII value for Enter
            }
        });
	}	

}


