package frontPanel;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;

import companent.CPU;
import module.IODevice;

public class PrinterPanel extends JPanel implements IODevice {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6614008801609108682L;
	JTextPane text = new JTextPane();
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
