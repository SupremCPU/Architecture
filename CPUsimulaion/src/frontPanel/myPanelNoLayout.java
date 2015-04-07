package frontPanel;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class myPanelNoLayout extends JPanel {
	myPanelNoLayout(int x, int y, int width, int height, Color color, String s) {
		// super();
		setLayout(null);
		setBounds(x, y, width, height);
		setBackground(color);
		setBorder(new TitledBorder(s));
		setVisible(true);
	}
}