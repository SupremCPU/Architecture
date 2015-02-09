package frontPanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import companent.CPU;
import companent.register;
import caculation.computering;

public class registerPanel extends JPanel {

	/*
	 *link with source data in CPU class 
	 */
	private static final long serialVersionUID = 6343475821460940391L;
	private CPU CPU = companent.CPU.getInstance();
	private JScrollPane scroll;
	private JTable table;

	public registerPanel() {
		TitledBorder name = new TitledBorder("Register");
		this.setBorder(name);
		this.setLayout(new BorderLayout());

		/*
		 * enter the Panel style, register table correspond accurate Column 
		 */
	    table = new RegisterTable(CPU.getRegisterSize(),2);
		
		String[] tableHeads = new String[] { "Value", "Register" };
		DefaultTableModel defaulTableM = (DefaultTableModel) table.getModel();
		defaulTableM.setColumnIdentifiers(tableHeads);

		TableColumn col = table.getColumnModel().getColumn(0);
		table.getTableHeader().setResizingColumn(col);
		col.setWidth(140);
		col = table.getColumnModel().getColumn(1);
		// Set colum of talbe
		table.getTableHeader().setResizingColumn(col);
		col.setWidth(80);

		table.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				

				// update the enter e.point after one click
				if (e.getClickCount() > 1) {
					
					int row = (table.rowAtPoint(e.getPoint()));
					int column = (table.columnAtPoint(e.getPoint()));

					// transfer the enter date into string
					String registerName = String.valueOf(table.getValueAt(row,1));
					register register = CPU.getRegisters().get(registerName);

					// how many bits does that register has
					int bitSize = register.getSize();
					// the max value is bit[i], i^2-1
					int maxvalue = (int) Math.pow(2, bitSize) - 1;
					 
				

					// which type of value are editing
					String msg = null;
					 if (column == 0) {
						msg = "Please input a new value(binary) for"
								+ registerName;
					}
					if (msg != null) {
						try {
							// get the input string
							String outValue = JOptionPane.showInputDialog(null,
									msg, "Edit Register",
									JOptionPane.PLAIN_MESSAGE);
							// turn into decimal value
					   	int decimalValue=0 ;
							if (column == 0)
								// if the enter value is bit, transfer to integer
								decimalValue = computering.getIntValueFromBits(computering
										.getBitsFromBinaryString(outValue));


							// check if value if legal
							
							  if(decimalValue>=0&&decimalValue<=maxvalue){
							  //set register and update panel
							  CPU.getRegisters()
							  .get(registerName).setValueByInt(decimalValue);
							  update(); }
							 
						} catch (HeadlessException e1) {
							// e1.printStackTrace();
						} catch (NumberFormatException e1) {
							// e1.printStackTrace();
						}

					}
				}

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});
	     // if panel listen the table set changed, update the table set
		table.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				// System.out.println("e");
			}

		});

		update();
		scroll = new JScrollPane(table);
		this.add(scroll, BorderLayout.CENTER);
	}
            /*
             * use the map's row value store Register name achieved from class CPU
             * transfer the string register name to int value into table
             */
	public void update() {
		Map<String, register> regs = CPU.getRegisters();
		int i = 0;
		for (String key : CPU.getRegisterNames()) {
			table.setValueAt(key, i, 1);
			register reg = regs.get(key);
			table.setValueAt(reg.getBinaryString(), i, 0);
			i++;
		}
		System.out.println("test update register");
	}
              // new class  RegisterTable inherit JTable in Javax.swing
	 class RegisterTable extends JTable {

		/*
		 *private a new UID as identify of enter  
		 */
		private static final long serialVersionUID = -615934872701302160L;

		public RegisterTable() {
			super();
		}

		public RegisterTable(int rows, int cols) {
			super(rows, cols);
		}

		public boolean isCellEditable(int row, int column) {
			return false;
		}

	}
}
