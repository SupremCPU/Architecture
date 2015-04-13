package frontPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;

import companent.CPU;
import companent.IODeviceDriver;
import companent.Keyboard;
import module.IODevice;

public class PrinterPanel extends JPanel implements IODevice{

	/**
	 * @author you zhao  yufei zhou
	 */
	private static final long serialVersionUID = -6614008801609108682L;
	JTextPane text = new JTextPane();
	Keyboard keyboard=(Keyboard) IODeviceDriver.getInstance().getKeyboard();
	private IODevice self;
	public PrinterPanel() {
		TitledBorder nameTitle = new TitledBorder("Printer Section");
		self = this;
		this.setBorder(nameTitle);

		this.setLayout(new GridLayout());

		text = new JTextPane();
		text.setBounds(650, 205, 211, 155);
		text.setBackground(Color.white);
		text.setForeground(Color.black);
		// text.setEditable(false);

		// this.add(text);
		JScrollPane js = new JScrollPane(text);
		js.setBackground(Color.black);
		this.add(js);
		
        
        /* this.addKeyListener(
        		new KeyListener(){

					@Override
					public void keyTyped(KeyEvent e) {
						//TODO
					}

					@Override
					public void keyPressed(KeyEvent e) {
						//TODO
						
						
						int value = (int)e.getKeyChar();
						if(value>0&&value<128)
							keyboard.activeEvent(value);
					}

					@Override
					public void keyReleased(KeyEvent e) {
						//TODO
					}
        			
        		});*/
		
	}
	protected JTextPane getTextPane()
	{
		return this.text;
	}
	@Override
	public void save(int value) {
		SimpleAttributeSet attrSet = new SimpleAttributeSet();
		Document doc = text.getDocument();
		try {
			doc.insertString(doc.getLength(),(char)(value)+"",attrSet);
			text.setSelectionStart(text.getText().length());
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int getDevid() {
		return 1;
	}
	
	public void update(){
		//List<Integer> data = CPU.getInstance().getOutputBuffer().checkForSignal(1);
		//for(Integer value:data)
			//save(value);
		
		CPU.getInstance().getOutputBuffer().checkForSignal(self);
		
	}

	public void clearText() {
		text.setText("");
	}


	
}
