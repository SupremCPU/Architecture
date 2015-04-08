package edu.gwu.computerarchitecture.ui;

import java.awt.Dialog;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import edu.gwu.computerarchitecture.Device.Keyboard;
import edu.gwu.computerarchitecture.Device.Printer;
import edu.gwu.computerarchitecture.cpu.CPU;
import edu.gwu.computerarchitecture.entity.Register;
import edu.gwu.computerarchitecture.entity.WordType;

public class LoadingBootBar extends JFrame
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private JPanel contentPane;

    private JProgressBar progressBar;

    private JLabel lblLoadingBoot;

    private StartFrame sf;

    public CPU cpu;

    private int programNo;

    // A file chooser
    private JFileChooser fc;

    /**
     * Create the frame.
     */
    public LoadingBootBar(StartFrame sf)
    {
        this.sf = sf;
        this.cpu = sf.cpu;

        fc = new JFileChooser();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        lblLoadingBoot = new JLabel("Boot Loading......");
        lblLoadingBoot.setBounds(68, 42, 339, 16);
        contentPane.add(lblLoadingBoot);

        progressBar = new JProgressBar();
        progressBar.setBounds(68, 119, 339, 20);
        progressBar.setStringPainted(true);
        contentPane.add(progressBar);
    }

    /**
     * Do with root loading, show the bar.
     */
    public void loadRootAndIPL()
    {
        // TODO: deal with root.
        new Thread(new Runnable()
        {
            public void run()
            {
                for (int i = 0; i < 101; i++)
                {
                    progressBar.setValue(i);
                    try
                    {
                        java.util.concurrent.TimeUnit.MILLISECONDS.sleep(10);
                    }
                    catch (Exception e)
                    {

                    }
                }
                lblLoadingBoot
                        .setText("Boot Loading Complete! Wait for loading Memory......");
                try
                {
                    java.util.concurrent.TimeUnit.MILLISECONDS.sleep(100);
                }
                catch (Exception e)
                {

                }
                setInvisible();
                // Memory loading.
                int returnVal = fc.showOpenDialog(LoadingBootBar.this);
                if (returnVal == JFileChooser.APPROVE_OPTION)
                {
                    File file = fc.getSelectedFile();
                    // Got file path.
                    String filepath = file.getPath();
                    System.out.println("The path of the file is: " + filepath);
                    // Load memory TODO
                    cpu.cpuunits.memory.loadFile(filepath);

                    String filename = file.getName();
                    if (filename.startsWith(("program1")))
                    {
                        programNo = 1;
                        cpu.cpuunits.x0.setRegister(new WordType(200));
                    }
                    if (filename.equals("Instructions.txt"))
                    {
                        programNo = 10;
                    }
                    System.out.println(filename);

                    // Set the starting address
                    JOptionPane pane = new JOptionPane("Starting Address(PC):",
                            JOptionPane.INFORMATION_MESSAGE);
                    pane.setWantsInput(true);
                    JDialog dialog = pane.createDialog(pane,
                            "Please input the number of the starting address");
                    dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                    dialog.setVisible(true);
                    String startAddressString = (String) pane.getInputValue();
                    System.out.println("The starting address is: "
                            + startAddressString);

                    // Set the beginning address.
                    cpu.cpuunits.pc.setRegister(new WordType(Integer
                            .parseInt(startAddressString)));

                    // After all settings are done, launch the simulator UI.
                    Toolkit tool = getToolkit();
                    Dimension dim = tool.getScreenSize();

                    // Start the simulator frame.
                    SimulatorUI simulatorFrame = new SimulatorUI();
                    simulatorFrame.setLocation(100, 100);
                    simulatorFrame.setCpu(cpu);
                    simulatorFrame.setVisible(true);

                    // Start the console frame.
                    ConsoleFrame consoleFrame = new ConsoleFrame();
                    consoleFrame.setLocation(dim.width / 2, 100);
                    consoleFrame.setCpu(cpu);
                    consoleFrame.setProgramNo(programNo);
                    consoleFrame.setVisible(true);

                    // Open Keyboard.
                    sf.openVirtualKeyboard();
                    /**
                     * Link to each other.
                     */
                    simulatorFrame.setConsoleUI(consoleFrame);
                    consoleFrame.setSimulatorui(simulatorFrame);

                    // Set log to cpu for printing.
                    cpu.setLog(consoleFrame);

                    // Initialization Device.
                    Keyboard keyborad = (Keyboard) cpu.cpuunits.device
                            .getDevice(0);
                    keyborad.setConsole(consoleFrame);
                    Printer printer = (Printer) cpu.cpuunits.device
                            .getDevice(1);
                    printer.setConsole(consoleFrame);

                }
                else
                {
                    sf.appear();
                    System.out.println("Open command cancelled by user...");
                }
            }
        }).start();
    }

    public void setInvisible()
    {
        this.setVisible(false);
    }

}
