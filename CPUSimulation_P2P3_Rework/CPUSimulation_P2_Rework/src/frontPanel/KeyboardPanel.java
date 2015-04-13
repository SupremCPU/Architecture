package frontPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import companent.CPU;
import companent.IODeviceDriver;

/**
 * @author you zhao, yufei zhou
 */


public class KeyboardPanel extends JPanel  {

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
	JButton RealKeyboard=new JButton("Real");
	boolean RealFlag= false;
	private companent.Keyboard keyboard;
	private Font buttonFont = new Font("Helvetica", Font.BOLD, 18);
	
	private int devId = 0;
	
	public KeyboardPanel(){	
		
		
		
		TitledBorder nameTitle =new TitledBorder("Keyboard Section)"); 	
		this.setBorder(nameTitle);
		
		 this.setLayout(new GridLayout(4,4));
        this.keyboard=(companent.Keyboard)(IODeviceDriver.getInstance().getKeyboard());
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
        this.add(RealKeyboard);    
        this.add(oneB);
        this.oneB.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub	
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				int value = (int)'1';
					keyboard.activeEvent(value);
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
        	
        	
        	
        });
        
        this.add(twoB);
        this.twoB.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub	
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				int value = (int)'2';
					keyboard.activeEvent(value);
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
        	
        	
        	
        });
        
        
        this.add(threeB);
        this.threeB.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub	
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				int value = (int)'3';
					keyboard.activeEvent(value);
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
        	
        	
        	
        });
        
        
        
        
        this.add(fourB);
        this.fourB.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub	
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				int value = (int)'4';
					keyboard.activeEvent(value);
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
        	
        	
        	
        });
        
        
        
        
        this.add(fiveB);
        this.fiveB.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub	
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				int value = (int)'5';
					keyboard.activeEvent(value);
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
        	
        	
        	
        });
        
        
        
        
        
        
        
        
        
        
        
        this.add(sixB);
        this.sixB.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub	
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				int value = (int)'6';
					keyboard.activeEvent(value);
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
        	
        	
        	
        });
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        this.add(sevenB);
        this.sevenB.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub	
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				int value = (int)'7';
					keyboard.activeEvent(value);
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
        	
        	
        	
        });
        
        
        
        
        
        
        
        
        
        
        
        
        this.add(eightB);
        this.eightB.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub	
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				int value = (int)'8';
					keyboard.activeEvent(value);
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
        	
        	
        	
        });
        
        
        
        
        
        
        
        
        this.add(nineB);
        this.nineB.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub	
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				int value = (int)'9';
					keyboard.activeEvent(value);
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
        	
        	
        	
        });
        
        
        
        
        
        
        
        
        
        
        
        
        this.add(zeroB);
        this.zeroB.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub	
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				int value = (int)'0';
					keyboard.activeEvent(value);
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
        	
        	
        	
        });
        
        
        
        
        
        
        
        
        
        
        
        
        
        this.add(dotB);
        this.dotB.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub	
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				int value = (int)'.';
					keyboard.activeEvent(value);
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
        	
        	
        	
        });
        
        
        
        
        
        
        this.add(addB);
        this.addB.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub	
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				int value = (int)'+';
					keyboard.activeEvent(value);
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
        	
        	
        	
        });
        
        
        
        
        
        
        
        
        
        this.add(minusB);
        this.minusB.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub	
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				int value = (int)'-';
					keyboard.activeEvent(value);
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
        	
        	
        	
        });
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        this.add(multiplyB);
        this.multiplyB.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub	
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				int value = (int)'*';
					keyboard.activeEvent(value);
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
        	
        	
        	
        });
        
        
        
        
        
        
        
        
        
        
        this.add(divideB);
        this.divideB.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub	
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				int value = (int)47;
					keyboard.activeEvent(value);
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
        	
        	
        	
        });
        
        
        
        //ENTER
        this.add(enterB);
        this.enterB.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub	
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				int value = (int)'\n';
					keyboard.activeEvent(value);
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
        	
        	
        	
        });
        
        
    	RealKeyboard.setBackground(Color.BLUE);
        RealKeyboard.setBounds(0, 0, 320, 15);
     //  RealKeyboard.set
     //   this.add(RealKeyboard); moved to upward
        RealKeyboard.addKeyListener(
        		new KeyListener(){

					@Override
					public void keyTyped(KeyEvent e) {
						//System.out.println("Keyboard typed: "+e.getKeyCode());
					}

					@Override
					public void keyPressed(KeyEvent e) {
						//System.out.println("Keyboard pressed: "+e.getKeyCode());
						
						if(RealFlag)
						{
							int value = (int)e.getKeyChar();
							if(value>0&&value<128)
							keyboard.activeEvent(value);
						}
					}

					@Override
					public void keyReleased(KeyEvent e) {
						//System.out.println("Keyboard released: "+e.getKeyCode());
					}
        			
        		});
       /* RealKeyboard.addFocusListener(
        		new FocusListener(){

					@Override
					public void focusGained(FocusEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void focusLost(FocusEvent e) {
						RealKeyboard.setSelected(false);
					}
        			
        		});*/
        this.RealKeyboard.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub	
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				RealFlag =!RealFlag;
				if(RealFlag)
				{
					RealKeyboard.setBackground(Color.MAGENTA);
				}
				else
				{
					RealKeyboard.setBackground(Color.BLUE);
				}
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
        	
        	
        	
        });
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


