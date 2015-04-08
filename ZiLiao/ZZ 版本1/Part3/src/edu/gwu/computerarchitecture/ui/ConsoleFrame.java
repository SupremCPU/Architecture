package edu.gwu.computerarchitecture.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

import edu.gwu.computerarchitecture.cpu.CPU;
import edu.gwu.computerarchitecture.entity.Register;
import edu.gwu.computerarchitecture.entity.WordType;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * The frame is the console of this simulator. User can see the screen of output
 * and use the keyboard to input.
 * @author liyaoxing Oct 1, 2013
 * @see
 * @since 1.0
 */

public class ConsoleFrame extends JFrame
{

    private static final long serialVersionUID = 1L;

    /**
     * Console Printer Status
     */
    private WordType consolePrinterStatus;

    /**
     * Console Keyboard Status
     */
    private WordType consoleKeyboardStatus;

    public ArrayList<Character> buffer;

    public ArrayList<String> bufferString;

    // Components in the frame
    private JPanel contentPane;

    private JTextField textFieldInput;

    private JTextArea textAreaConsole;

    private SimulatorUI simulatorui;

    /**
     * CPU
     */
    private CPU cpu;
    
    private int programNo;

    /**
     * Create the frame.
     */
    public ConsoleFrame()
    {

        // initialization of the status
        consolePrinterStatus = new WordType(0);
        consoleKeyboardStatus = new WordType(0);

        // initialization of the buffer
        buffer = new ArrayList<Character>();

        bufferString = new ArrayList<String>();
        
        programNo=1000;

        // Initialize the frame
        setTitle("Console");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 616, 435);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textAreaConsole = new JTextArea();
        textAreaConsole.setEditable(false);
        String consoleText = "Console:";
        textAreaConsole.setText(consoleText);
        
        
        

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(18, 29, 579, 295);
        scrollPane.setViewportView(textAreaConsole);
        contentPane.add(scrollPane);

        textFieldInput = new JTextField();
        // Deal with the input String.
        textFieldInput.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    handleInputValue();
                }
            }
        });
        textFieldInput.setBounds(18, 351, 475, 28);
        contentPane.add(textFieldInput);
        textFieldInput.setColumns(10);

        JButton btnEnter = new JButton("Enter");
        // Deal with the input String.
        btnEnter.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                handleInputValue();
            }
        });
        btnEnter.setBounds(499, 351, 98, 29);
        contentPane.add(btnEnter);

        JLabel lblInput = new JLabel("Input:");
        lblInput.setBounds(18, 334, 61, 16);
        contentPane.add(lblInput);
    }

    /**
     * TODO: deal with input value. Add input instruction to the end of
     * console's text field.
     * @param s
     */
    private void handleInputValue()
    {
        String s = textFieldInput.getText();
        textFieldInput.setText("");
        // echo input.
        String logString = textAreaConsole.getText();
        logString = logString + "\nInput: " + s;
        textAreaConsole.setText(logString);
        // add input into buffer.
        char[] cn = s.toCharArray();
        for (Character c : cn)
        {
            buffer.add(c);
        }
        // add the end of String into buffer.
        buffer.add('\0');
        
        consoleKeyboardStatus = new WordType(1);

        // add input into bufferString
        bufferString.add(s);
    }

    /**
     * Add output log to the end of console's text field. Used by debugging.
     * @param s
     */
    public void printLog(String s)
    {
        String logString = textAreaConsole.getText();
        logString = logString + "\nDebug: " + s;
        textAreaConsole.setText(logString);
    }

    /**
     * Add output String to the end of console's text field.
     * @param s
     */
    public void printString(String s)
    {
        String logString = textAreaConsole.getText();
        logString = logString + "\nOutput: " + s;
        textAreaConsole.setText(logString);
    }

    /**
     * Add output character to the end of console's text field.
     * @param c
     */
    public void printChar(char c)
    {
        String logString = textAreaConsole.getText();
        String[] lines = logString.split("\n");
        if (lines[lines.length - 1].startsWith("Output:"))
        {
            logString = logString + c;
        }
        else
        {
            logString = logString + "\nOutput:" + c;
        }
        textAreaConsole.setText(logString);
    }

    /**
     * The method is used to get a character from buffer by CPU.
     * @return the first character
     */
    public Character readChar()
    {
        Character resultCharacter = null;// TODO
        if (buffer.isEmpty())
        {
            // Nothing to do.
        }
        else
        {
            resultCharacter = buffer.get(0);
            buffer.remove(0);
        }
        if (buffer.isEmpty())
        {
            consoleKeyboardStatus = new WordType(0);
        }
        return resultCharacter;
    }

    /**
     * Find the closest number in a list according to the number given.
     * @param al
     * @param numS
     * @return
     */
    public ArrayList<String> findCloseNumber(ArrayList<String> al,
            String numS)
    {
        // Result list.
        ArrayList<String> ret = new ArrayList<String>();

        int num = Integer.parseInt(numS);

        if (al == null)
        {
            // do nothing.
        }
        else
        {

            // Set distance to the Max.
            int dis = Integer.MAX_VALUE;
            for (int i = 0; i < al.size(); i++)
            {
                int inum = Integer.parseInt(al.get(i));
                int dis2 = Math.abs(num - inum);
                if (dis2 < dis)
                {
                    // clear list and add the number into it.
                    ret.clear();
                    ret.add(al.get(i));
                    // Set the distance to this.
                    dis = dis2;
                }
                else
                {
                    if (dis2 == dis)
                    {
                        // add the number into the list.
                        ret.add(al.get(i));
                    }
                }

            }

        }

        return ret;
    }

    public CPU getCpu()
    {
        return cpu;
    }

    public void setCpu(CPU cpu)
    {
        this.cpu = cpu;
    }

    public SimulatorUI getSimulatorui()
    {
        return simulatorui;
    }

    public void setSimulatorui(SimulatorUI simulatorui)
    {
        this.simulatorui = simulatorui;
    }

    public WordType getConsolePrinterStatus()
    {
        return consolePrinterStatus;
    }

    public void setConsolePrinterStatus(WordType consolePrinterStatus)
    {
        this.consolePrinterStatus = consolePrinterStatus;
    }

    public WordType getConsoleKeyboardStatus()
    {
        return consoleKeyboardStatus;
    }

    public void setConsoleKeyboardStatus(WordType consoleKeyboardStatus)
    {
        this.consoleKeyboardStatus = consoleKeyboardStatus;
    }

    public int getProgramNo()
    {
        return programNo;
    }

    public void setProgramNo(int programNo)
    {
        this.programNo = programNo;
        
        String consoleText = textAreaConsole.getText();
     
        
        switch(programNo){
            case 1:
                consoleText=consoleText+"\nOutput: Please input 21 integers(This program will find the closet number to the last integer in the first 20 integers):";
                break;
            case 2:
                break;
            case 3:
                break;
            case 10:
                consoleText=consoleText+"\nOutput: Please wait for the result......";
                break;
            default:
                //do nothing.
                break;
        }
        
        textAreaConsole.setText(consoleText);
    }

}
