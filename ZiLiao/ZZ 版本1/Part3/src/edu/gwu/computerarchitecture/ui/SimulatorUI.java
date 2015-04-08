package edu.gwu.computerarchitecture.ui;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dialog;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import edu.gwu.computerarchitecture.cpu.CPU;
import edu.gwu.computerarchitecture.cpu.CPUUnits;
import edu.gwu.computerarchitecture.entity.WordType;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * The main window frame of the simulator.
 * @author liyaoxing Sep 18, 2013
 * @see
 * @since 1.0
 */
public class SimulatorUI extends JFrame
{

    private static final long serialVersionUID = 1L;

    // A file chooser
    private JFileChooser fc;

    /**
     * CPU Units
     */
    private CPUUnits CPUunits;

    /**
     * CPU
     */
    private CPU cpu;

    /**
     * console GUI
     */
    private ConsoleFrame consoleUI;

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

    private JScrollPane scrollPane;

    private JTextField textFieldX0;

    private JTextField textFieldFR0;

    private JTextField textFieldFR1;

    private JList<String> list;

    private JTextField textFieldMSR;

    private JTextField textFieldMFR;

    /**
     * Create the frame. And initialize components.
     */
    public SimulatorUI()
    {
        // Initialize the frame.
        setTitle("Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 685, 583);
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

        JButton btnGo = new JButton("Run");
        btnGo.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                // CPU.GO method + refresh
                cpu.Go();
                refresh();
            }
        });
        btnGo.setBounds(393, 27, 117, 29);
        contentPane.add(btnGo);

        JButton btnDebug = new JButton("Debug");
        btnDebug.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                // CPU.Debug method + refresh

                cpu.Debug();

                refresh();
            }
        });
        btnDebug.setBounds(393, 187, 117, 29);
        contentPane.add(btnDebug);

        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                refresh();
                // This refresh action is done.                
            }
        });
        btnRefresh.setBounds(393, 155, 117, 29);
        contentPane.add(btnRefresh);

        /**
         * For Memory
         */
        scrollPane = new JScrollPane();
        scrollPane.setBounds(68, 30, 303, 225);

        DefaultListModel<String> memoryList = new DefaultListModel<String>();

        for (int i = 0; i < 2048; i++)
        {
            String ele = Integer.toString(i) + ": " + intToSixteenString(0);
            memoryList.addElement(ele);
        }

        list = new JList<String>(memoryList);

        list.setSelectedIndex(2);

        scrollPane.setViewportView(list);

        contentPane.add(scrollPane);

        JLabel labelX0 = new JLabel("X0");
        labelX0.setBounds(41, 449, 31, 16);
        contentPane.add(labelX0);

        textFieldX0 = new JTextField();
        textFieldX0.setBounds(78, 444, 155, 28);
        textFieldX0.setText(intToSixteenString(0));
        textFieldX0.setEditable(false);
        contentPane.add(textFieldX0);
        textFieldX0.setColumns(10);

        JLabel lblMemory = new JLabel("Memory:");
        lblMemory.setBounds(41, 6, 61, 16);
        contentPane.add(lblMemory);

        JLabel lblFR0 = new JLabel("FR0");
        lblFR0.setBounds(41, 492, 31, 16);
        contentPane.add(lblFR0);

        textFieldFR0 = new JTextField();
        textFieldFR0.setBounds(78, 486, 272, 28);
        textFieldFR0.setText(intTo32String(0));
        textFieldFR0.setEditable(false);
        contentPane.add(textFieldFR0);
        textFieldFR0.setColumns(10);

        JLabel lblFR1 = new JLabel("FR1");
        lblFR1.setBounds(41, 526, 31, 16);
        contentPane.add(lblFR1);

        textFieldFR1 = new JTextField();
        textFieldFR1.setBounds(78, 520, 272, 28);
        textFieldFR1.setText(intTo32String(0));
        textFieldFR1.setEditable(false);
        contentPane.add(textFieldFR1);
        textFieldFR1.setColumns(10);

        JLabel lblMsr = new JLabel("MSR");
        lblMsr.setBounds(287, 449, 48, 16);
        contentPane.add(lblMsr);

        textFieldMSR = new JTextField();
        textFieldMSR.setBounds(347, 443, 155, 28);
        textFieldMSR.setText(intToSixteenString(0));
        textFieldMSR.setEditable(false);
        contentPane.add(textFieldMSR);
        textFieldMSR.setColumns(10);

        JLabel lblMfr = new JLabel("MFR");
        lblMfr.setBounds(393, 228, 31, 16);
        contentPane.add(lblMfr);

        textFieldMFR = new JTextField();
        textFieldMFR.setBounds(432, 222, 70, 28);
        textFieldMFR.setText(intToFourString(0));
        textFieldMFR.setEditable(false);
        contentPane.add(textFieldMFR);
        textFieldMFR.setColumns(10);

        JButton btnHalt = new JButton("Halt");
        btnHalt.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                // TODO: HALT
                System.out.println("Simulator Halt.");
            }
        });
        btnHalt.setBounds(393, 59, 117, 29);
        contentPane.add(btnHalt);

        JButton btnSingleStep = new JButton("Single Step");
        btnSingleStep.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                System.out.println("Single Step.");
                cpu.SingleStep();
                refresh();
            }
        });
        btnSingleStep.setBounds(393, 91, 117, 29);
        contentPane.add(btnSingleStep);

        JButton btnIpl = new JButton("IPL");
        btnIpl.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                System.out.println("IPL");
                cpu = new CPU();
                cpu.CPU_Initialization();
                // Choose file to load memory
                disappear();
                JOptionPane.showMessageDialog(null,
                        "Choose a file to load memory", "Load Memory:",
                        JOptionPane.INFORMATION_MESSAGE);
                fc = new JFileChooser();
                int returnVal = fc.showOpenDialog(SimulatorUI.this);
                if (returnVal == JFileChooser.APPROVE_OPTION)
                {
                    File file = fc.getSelectedFile();
                    // Got file path.
                    String filepath = file.getPath();
                    System.out.println("The path of the file is: " + filepath);
                    // Load memory TODO
                    cpu.cpuunits.memory.loadFile(filepath);

                    // Set the starting address
                    JOptionPane pane = new JOptionPane("Starting Address:",
                            JOptionPane.INFORMATION_MESSAGE);
                    pane.setWantsInput(true);
                    JDialog dialog = pane.createDialog(pane,
                            "Please input the number of the starting address");
                    dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                    dialog.setVisible(true);
                    String startAddressString = (String) pane.getInputValue();
                    System.out.println("The starting address is: "
                            + startAddressString);

                    // Set the beginning address TODO
                    cpu.cpuunits.pc.setRegister(new WordType(Integer
                            .parseInt(startAddressString)));

                    // After all settings are done, launch the simulator UI
                    Toolkit tool = getToolkit();
                    Dimension dim = tool.getScreenSize();

                    // Start the simulator frame.
                    SimulatorUI simulatorFrame = new SimulatorUI();
                    simulatorFrame.setLocation(100, 100);
                    simulatorFrame.setCpu(cpu);
                    simulatorFrame.setVisible(true);

                    // Start the console frame
                    ConsoleFrame consoleFrame = new ConsoleFrame();
                    consoleFrame.setLocation(dim.width / 2, 100);
                    consoleFrame.setCpu(cpu);
                    consoleFrame.setVisible(true);

                    /**
                     * Link to each other.
                     */
                    simulatorFrame.setConsoleUI(consoleFrame);
                    consoleFrame.setSimulatorui(simulatorFrame);

                }
                else
                {
                    appear();
                    System.out.println("Open command cancelled by user...");
                }
            }
        });
        btnIpl.setBounds(393, 123, 117, 29);
        contentPane.add(btnIpl);

        JButton btnInputData = new JButton("Input Data");
        btnInputData.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                // Read Input Field
                JOptionPane pane = new JOptionPane(
                        "Field Name(Memory is available):",
                        JOptionPane.INFORMATION_MESSAGE);
                pane.setWantsInput(true);
                JDialog dialog = pane
                        .createDialog(pane,
                                "Please input the name of the field you want to change:");
                dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                dialog.setVisible(true);
                String fieldname = (String) pane.getInputValue();
                System.out.println("The changed field is: " + fieldname);
                if (fieldname == null)
                {
                    // Input nothing.
                    JOptionPane.showMessageDialog(null,
                            "Sorry, the field name is not availbale",
                            "Not Available", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    //To upper case.
                    fieldname=fieldname.toUpperCase();
                    
                    switch (fieldname)
                    {
                        case "PC":
                            // Deal with PC change.
                            JOptionPane panePC = new JOptionPane(
                                    "PC (Please input a integer):",
                                    JOptionPane.INFORMATION_MESSAGE);
                            panePC.setWantsInput(true);
                            JDialog dialogPC = panePC
                                    .createDialog(panePC,
                                            "Please input the number of PC you want to change:");
                            dialogPC.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                            dialogPC.setVisible(true);
                            String PCString = (String) panePC.getInputValue();
                            System.out
                                    .println("The changed PC is: " + PCString);
                            cpu.cpuunits.pc.setRegister(new WordType(Integer
                                    .parseInt(PCString)));
                            refresh();
                            break;
                            
                        case "X0":
                            // Deal with X0 change.
                            JOptionPane paneX0 = new JOptionPane(
                                    "X0 (Please input a integer):",
                                    JOptionPane.INFORMATION_MESSAGE);
                            paneX0.setWantsInput(true);
                            JDialog dialogX0 = paneX0
                                    .createDialog(paneX0,
                                            "Please input the number of X0 you want to change:");
                            dialogX0.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                            dialogX0.setVisible(true);
                            String X0String = (String) paneX0.getInputValue();
                            System.out
                                    .println("The changed X0 is: " + X0String);
                            cpu.cpuunits.x0.setRegister(new WordType(Integer
                                    .parseInt(X0String)));
                            refresh();
                            break;

                        case "R0":
                            // Deal with R0 change.
                            JOptionPane paneR0 = new JOptionPane(
                                    "R0 (Please input a integer):",
                                    JOptionPane.INFORMATION_MESSAGE);
                            paneR0.setWantsInput(true);
                            JDialog dialogR0 = paneR0
                                    .createDialog(paneR0,
                                            "Please input the number of R0 you want to change:");
                            dialogR0.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                            dialogR0.setVisible(true);
                            String R0String = (String) paneR0.getInputValue();
                            System.out
                                    .println("The changed R0 is: " + R0String);
                            cpu.cpuunits.r0.setRegister(new WordType(Integer
                                    .parseInt(R0String)));
                            refresh();
                            break;
                            
                        case "R1":
                            // Deal with R1 change
                            JOptionPane paneR1 = new JOptionPane(
                                    "R1 (Please input a integer):",
                                    JOptionPane.INFORMATION_MESSAGE);
                            paneR1.setWantsInput(true);
                            JDialog dialogR1 = paneR1
                                    .createDialog(paneR1,
                                            "Please input the number of R1 you want to change:");
                            dialogR1.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                            dialogR1.setVisible(true);
                            String R1String = (String) paneR1.getInputValue();
                            System.out
                                    .println("The changed R1 is: " + R1String);
                            cpu.cpuunits.r1.setRegister(new WordType(Integer
                                    .parseInt(R1String)));
                            refresh();
                            break;
                            
                        case "R2":
                            // Deal with R2 change
                            JOptionPane paneR2 = new JOptionPane(
                                    "R2 (Please input a integer):",
                                    JOptionPane.INFORMATION_MESSAGE);
                            paneR2.setWantsInput(true);
                            JDialog dialogR2 = paneR2
                                    .createDialog(paneR2,
                                            "Please input the number of R2 you want to change:");
                            dialogR2.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                            dialogR2.setVisible(true);
                            String R2String = (String) paneR2.getInputValue();
                            System.out
                                    .println("The changed R2 is: " + R2String);
                            cpu.cpuunits.r2.setRegister(new WordType(Integer
                                    .parseInt(R2String)));
                            refresh();
                            break;
                            
                        case "R3":
                            // Deal with R3 change
                            JOptionPane paneR3 = new JOptionPane(
                                    "R3 (Please input a integer):",
                                    JOptionPane.INFORMATION_MESSAGE);
                            paneR3.setWantsInput(true);
                            JDialog dialogR3 = paneR3
                                    .createDialog(paneR3,
                                            "Please input the number of R3 you want to change:");
                            dialogR3.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                            dialogR3.setVisible(true);
                            String R3String = (String) paneR3.getInputValue();
                            System.out
                                    .println("The changed R3 is: " + R3String);
                            cpu.cpuunits.r3.setRegister(new WordType(Integer
                                    .parseInt(R3String)));
                            refresh();
                            break;
                            
                        case "MEMORY":
                            // Deal with Memory change.

                            // Read memory address from input.
                            JOptionPane paneAddress = new JOptionPane(
                                    "Memory address (Please input a integer from 0 to 2047):",
                                    JOptionPane.INFORMATION_MESSAGE);
                            paneAddress.setWantsInput(true);
                            JDialog dialogAddress = paneAddress
                                    .createDialog(paneAddress,
                                            "Please input the number of momery address you want to change:");
                            dialogAddress
                                    .setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                            dialogAddress.setVisible(true);
                            String AddressString = (String) paneAddress
                                    .getInputValue();

                            // Read memory data from input
                            JOptionPane paneData = new JOptionPane(
                                    "Memory Data (like 0000010010001000):",
                                    JOptionPane.INFORMATION_MESSAGE);
                            paneData.setWantsInput(true);
                            JDialog dialogData = paneData
                                    .createDialog(paneData,
                                            "Please input the number of momery data you want to change:");
                            dialogData
                                    .setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                            dialogData.setVisible(true);
                            String DataString = (String) paneData
                                    .getInputValue();
                            cpu.cpuunits.memory.setMemory(Integer.parseInt(AddressString), Integer.parseInt(DataString,2));
                            System.out.println("The changed Data for address "
                                    + AddressString + " is: " + DataString);

                            // TODO:

                            refresh();
                            break;
                        default:
                            // Do not match any key word, so input is not
                            // available.
                            JOptionPane.showMessageDialog(null,
                                    "Sorry, the field name is not availbale",
                                    "Not Available",
                                    JOptionPane.INFORMATION_MESSAGE);
                            break;

                    }
                }
            }
        });
        btnInputData.setBounds(522, 59, 117, 29);
        contentPane.add(btnInputData);
        
        JButton btnPower = new JButton("Power Off");
        btnPower.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //Power off.
                System.exit(EXIT_ON_CLOSE);
            }
        });
       
        btnPower.setBounds(522, 27, 117, 29);
        contentPane.add(btnPower);
    }

    /**
     * Refresh the frame from the CPUUnits.
     */
    public void refresh()
    {

        if (CPUunits != null)
        {

            textFieldPC.setText(intToSixteenString(CPUunits.pc.getRegister()
                    .getWord()));

            textFieldIR.setText(intToSixteenString(CPUunits.ir.getRegister()
                    .getWord()));
            textFieldMAR.setText(intToSixteenString(CPUunits.mar.getRegister()
                    .getWord()));
            textFieldMDR.setText(intToSixteenString(CPUunits.mdr.getRegister()
                    .getWord()));
            textFieldR0.setText(intToSixteenString(CPUunits.r0.getRegister()
                    .getWord()));
            textFieldR1.setText(intToSixteenString(CPUunits.r1.getRegister()
                    .getWord()));
            textFieldR2.setText(intToSixteenString(CPUunits.r2.getRegister()
                    .getWord()));
            textFieldR3.setText(intToSixteenString(CPUunits.r3.getRegister()
                    .getWord()));
            textFieldX0.setText(intToSixteenString(CPUunits.x0.getRegister()
                    .getWord()));
            // TODO:
            // textFieldFR0.setText(intTo32String(CPUunits.fr0.getRegister()
            // .getWord()));
            // textFieldFR1.setText(intTo32String(CPUunits.fr1.getRegister()
            // .getWord()));
            textFieldMSR.setText(intToSixteenString(CPUunits.msr.getRegister()
                    .getWord()));
            // TODO
            // textFieldMFR.setText(intToFourString(CPUunits.mfr.getRegister()
            // .getWord()));

            DefaultListModel<String> memoryList = new DefaultListModel<String>();

            for (int i = 0; i < 2048; i++)
            {
                String ele = Integer.toString(i) + ": "
                        + intToSixteenString(CPUunits.memory.getMemory(i));
                memoryList.addElement(ele);
            }

            list = new JList<String>(memoryList);

            // Add mouse listener to deal with click event.
            list.addMouseListener(new MouseAdapter()
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    // Always set selected item to the pc number.
                    list.setSelectedIndex(CPUunits.pc.getRegister().getWord());
                }
            });

            list.setSelectedIndex(CPUunits.pc.getRegister().getWord());

            scrollPane.setViewportView(list);

            // Set the verticalScrollBar to pc index.
            Point p = list.indexToLocation(CPUunits.pc.getRegister().getWord());
            scrollPane.getVerticalScrollBar().setValue(p.y);

        }
    }

    public CPUUnits getCPUunits()
    {
        return CPUunits;
    }

    public void setCPUunits(CPUUnits cPUunits)
    {
        CPUunits = cPUunits;
        refresh();
    }

    public CPU getCpu()
    {
        return cpu;
    }

    public void setCpu(CPU cpu)
    {
        this.cpu = cpu;
        setCPUunits(cpu.cpuunits);
    }

    public ConsoleFrame getConsoleUI()
    {
        return consoleUI;
    }

    public void setConsoleUI(ConsoleFrame consoleUI)
    {
        this.consoleUI = consoleUI;
    }

    /**
     * Change Integer to 4 Characters String
     * @param i
     * @return String
     */
    public static String intToFourString(int i)
    {
        String result = "";
        if (i >= 0)
        {
            for (int index = 0; index < 4; index++)
            {

                if (i >= (Math.pow(2, 3 - index)))
                {
                    i = i - (int) Math.pow(2, 3 - index);
                    result = result + "1";
                }
                else
                {
                    result = result + "0";
                }
            }
        }
        else
        {
            String binaryStringI = Integer.toBinaryString(i);
            result = binaryStringI.substring(28, 32);//4  bits
        }

        return result;
    }

    /**
     * Change Integer to 16 Characters String
     * @param i
     * @return String
     */
    public static String intToSixteenString(int i)
    {
        String result = "";
        if (i >= 0)
        {
            for (int index = 0; index < 16; index++)
            {

                if (i >= (Math.pow(2, 15 - index)))
                {
                    i = i - (int) Math.pow(2, 15 - index);
                    result = result + "1";
                }
                else
                {
                    result = result + "0";
                }
            }
        }
        else
        {
            String binaryStringI = Integer.toBinaryString(i);
            result = binaryStringI.substring(16, 32);//16 bits
        }

        return result;
    }

    /**
     * Change Integer to 32 Characters String
     * @param i
     * @return String
     */
    public static String intTo32String(int i)
    {
        String result = "";
        if (i >= 0)
        {
            for (int index = 0; index < 32; index++)
            {

                if (i >= (Math.pow(2, 31 - index)))
                {
                    i = i - (int) Math.pow(2, 31 - index);
                    result = result + "1";
                }
                else
                {
                    result = result + "0";
                }
            }
        }
        else
        {
            String binaryStringI = Integer.toBinaryString(i);
            result = binaryStringI.substring(0, 32);//32 bits
        }

        return result;
    }

    /**
     * Make this frame disappear.
     */
    private void disappear()
    {
        this.setVisible(false);
        this.consoleUI.setVisible(false);
    }

    /**
     * Make this frame appear.
     */
    private void appear()
    {
        this.setVisible(true);
    }
}
