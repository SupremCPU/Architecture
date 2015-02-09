package frontPanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Queue;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;



public class ConsolePanel extends JPanel{


	/*
	 * Log panel get the data from the system log
	 * @author YouZHao YuMa
	 */
	private static final long serialVersionUID = -6949194368450428114L;
	private Log log = Log.getInstance();
	private JTextPane text;

	public ConsolePanel(){
		
		// Log display the current time of computer
		TitledBorder nameTitle =new TitledBorder("Display"); 
		
		this.setBorder(nameTitle);
		
		this.setLayout(new GridLayout());
		// define the panel style
		text = new JTextPane();
		text.setBounds(0, 0, 400, 200);
		text.setBackground(Color.white);
		text.setForeground(Color.black);
		text.setEditable(false);
		
		//this.add(text);
		JScrollPane scroll = new JScrollPane(text);
		scroll.setBackground(Color.black);
		this.add(scroll);
		this.setVisible(true);
		
	}
	
	   // update depend the message order
	public void update(){
		Queue<String> msgs = log.getConsoleMessage();
		while(!msgs.isEmpty()){
			addMessage(msgs.poll());
		}
	}
	   /*
	    * If system occurs [ERROR], the error message transfer as content into the setForeground block
	    */
	private void addMessage(String content){
		SimpleAttributeSet attrSet = new SimpleAttributeSet();
		if(content.contains("[ERROR]"))
			StyleConstants.setForeground(attrSet, Color.red);
		Document doc = text.getDocument();
		try {
			doc.insertString(doc.getLength(),content+"\n",attrSet);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
