package frontPanel;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import companent.CPU;

/**
 * @author you zhao 
 */


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
	
	private Font buttonFont = new Font("Helvetica", Font.BOLD, 18);
	
	private int devId = 0;
	
	public KeyboardPanel(){	
		
		
		
		TitledBorder nameTitle =new TitledBorder("Keyboard Section)"); 	
		this.setBorder(nameTitle);
		
		 this.setLayout(new GridLayout(4,4));
        
//        
//        this.add(aB);
//        aB.setBounds(5, 18, 50, 35);
//        aB.setFont(buttonFont);
//        
//        this.add(bB);
//        bB.setBounds(55, 18, 50, 35);
//        bB.setFont(buttonFont);
//        
//        this.add(cB);
//        cB.setBounds(105, 18, 50, 35);
//        cB.setFont(buttonFont);
//        
//        this.add(addB);
//        addB.setBounds(155, 18, 50, 35);
//        addB.setFont(buttonFont);
        
        this.add(oneB);
        this.add(twoB);
        this.add(threeB);
        this.add(fourB);
        this.add(fiveB);
        this.add(sixB);
        this.add(sevenB);
        this.add(eightB);
        this.add(nineB);
        this.add(zeroB);
        this.add(dotB);
        this.add(addB);
        this.add(minusB);
        this.add(multiplyB);
        this.add(divideB);
        this.add(enterB);
   
        aB.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent f) {
            	CPU.getInstance().inSignal(devId,65);
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


