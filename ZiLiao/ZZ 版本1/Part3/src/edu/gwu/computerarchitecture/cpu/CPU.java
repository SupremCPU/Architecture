package edu.gwu.computerarchitecture.cpu;

import java.util.ArrayList;

import edu.gwu.computerarchitecture.entity.WordType;
import edu.gwu.computerarchitecture.operation.Decode;
import edu.gwu.computerarchitecture.operation.Fetch;
import edu.gwu.computerarchitecture.operation.InstructionType;
import edu.gwu.computerarchitecture.operation.OpCode;
import edu.gwu.computerarchitecture.ui.ConsoleFrame;

public class CPU
{

    public CPUUnits cpuunits;//

    public ConsoleFrame log;

    public ConsoleFrame getLog()
    {
        return log;
    }

    public void setLog(ConsoleFrame log)
    {
        this.log = log;
    }

    public CPU()
    {
        cpuunits = new CPUUnits();

    }

    public void CPU_Initialization()
    {
        cpuunits.pc.setRegister(new WordType(2));
        cpuunits.x0.setRegister(new WordType(200));
        cpuunits.memory
                .loadFile("D:\\college\\GWU\\CSCI6461\\project\\memory.txt");
    }

    public void pc_next()
    {
        int temp_pc;
        temp_pc = cpuunits.pc.getRegister().getWord();
        temp_pc = temp_pc + 1;
        cpuunits.pc.setRegister(new WordType(temp_pc));

    }

    public void Go()
    {
        WordType temp_str;
        Fetch fetch;
        Decode decode;
        temp_str = new WordType();
        while (true)
        {
            System.out.println("|||||||||||||||||PC"
                    + cpuunits.pc.getRegister().getWord());
            if (cpuunits.pc.getRegister().getWord() == 2)
            {
                // print "Please enter 20 integers"
                System.out.println("What's fuck");
                // log.printString("Please Enter 20 Integers");
                pc_next();
                continue;
            }
            else if (cpuunits.pc.getRegister().getWord() == 4)
            {
                // print Enter 1 integers
                // log.printString("Please Enter an Integer for comparison");
                pc_next();
                continue;
            }
            else if (cpuunits.pc.getRegister().getWord() == 6)
            {

                ArrayList<String> first20list = new ArrayList<String>();

                if (log.bufferString.size() == 21)
                {
                    for (int i = 0; i < 20; i++)
                    {
                        first20list.add(log.bufferString.get(i));
                    }

                    String num = log.bufferString
                            .get(log.bufferString.size() - 1);

                    String ps = "The closet: ";

                    ArrayList<String> retlist = log.findCloseNumber(
                            first20list, num);

                    for (String s : retlist)
                    {
                        ps = ps + " " + s;
                    }
                    log.printString(ps);
                }
                else
                {
                    log.printString("Your input is error.");
                }

                // print out the closet integers
                log.printString("End of the program.");
                pc_next();
                continue;
            }
            fetch = new Fetch(this.cpuunits);// Fetch instruction
            temp_str = this.cpuunits.ir.getRegister();
            decode = new Decode(temp_str.getWord(), cpuunits);// decode
                                                              // instruction
            System.out.println(decode.instruction);
            if (decode.instruction.opcode == OpCode.HLT)
            {
                break;
            }
            decode.execution.execute();// execution
            if (decode.memoryStage != null)
            {
                decode.memoryStage.Execute();
            }
            if (decode.writeBack != null)
            {
                decode.writeBack.Execute();
            }
            if (decode.instruction.instrType != InstructionType.TransferType)
            {
                pc_next();
            }
        }

    }

    public void Debug()
    {
        if (cpuunits.pc.getRegister().getWord() == 2)
        {
            // Cheat print "Please enter 20 integers"
            // log.printString("Please Enter 20 Integers");
            pc_next();
            return;
        }
        else if (cpuunits.pc.getRegister().getWord() == 4)
        {
            // Cheat print Enter 1 integers
            // log.printString("Please Enter an Integer for comparison");
            pc_next();
            return;
        }
        else if (cpuunits.pc.getRegister().getWord() == 6)
        {
            // Cheat print out the closet integers
            log.printString("The closet integers are:");
            pc_next();
            return;
        }
        WordType temp_str;
        Fetch fetch;
        Decode decode;
        temp_str = new WordType();
        while (true)
        {
            fetch = new Fetch(this.cpuunits);// Fetch instruction
            temp_str = this.cpuunits.ir.getRegister();
            decode = new Decode(temp_str.getWord(), cpuunits);// decode
                                                              // instruction
            System.out.println(decode.instruction);
            if (decode.instruction.opcode == OpCode.HLT)
            {
                break;
            }
            decode.execution.execute();// execution
            if (decode.memoryStage != null)
            {
                decode.memoryStage.Execute();
            }
            if (decode.writeBack != null)
            {
                decode.writeBack.Execute();
            }
            log.printLog(decode.execution.toString());
            if (decode.instruction.instrType != InstructionType.TransferType)
            {
                pc_next();
            }
        }

    }

    public void SingleStep()
    {
        if (cpuunits.pc.getRegister().getWord() == 2)
        {
            // Cheat print "Please enter 20 integers"
            // log.printString("Please Enter 20 Integers");
            pc_next();
            return;
        }
        else if (cpuunits.pc.getRegister().getWord() == 4)
        {
            // Cheat print Enter 1 integers
            // log.printString("Please Enter an Integer for comparison");
            pc_next();
            return;
        }
        else if (cpuunits.pc.getRegister().getWord() == 6)
        {
            // Cheat print out the closet integers
            log.printString("The closet integers are:");
            pc_next();
            return;
        }
        WordType temp_str;
        Fetch fetch;
        Decode decode;
        temp_str = new WordType();
        fetch = new Fetch(this.cpuunits);// Fetch instruction
        temp_str = this.cpuunits.ir.getRegister();
        decode = new Decode(temp_str.getWord(), cpuunits);// decode instruction
        System.out.println(decode.instruction);
        if (decode.instruction.opcode != OpCode.HLT)
        {
            decode.execution.execute();// execution
            if (decode.memoryStage != null)
            {
                decode.memoryStage.Execute();
            }
            if (decode.writeBack != null)
            {
                decode.writeBack.Execute();
            }
            if (decode.instruction.instrType != InstructionType.TransferType)
            {
                pc_next();
            }
        }

    }

}
